package fi.helsinki.lib.simplerest;

import com.google.gson.Gson;
import fi.helsinki.lib.simplerest.options.GetOptions;
import fi.helsinki.lib.simplerest.stubs.StubItem;
import org.dspace.content.ItemIterator;
import org.dspace.core.Context;
import org.dspace.content.Item;

import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.data.Status;
import org.restlet.engine.util.DateUtils;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.util.Series;

/**
 * Created by moubarik on 2.6.15.
 */

public class HandleResource extends BaseResource{

    private static Logger log = Logger.getLogger(HandleResource.class);

    private String handle;
    private Item item;
    private Context context;
    private Date ifModifiedSince;
    private String host;

    public HandleResource(){
        this.handle = "";
        this.item = null;
        try{
            this.context = new Context();
        }catch(SQLException e){
            log.log(Priority.FATAL, e);
        }
    }

    static public String relativeUrl(String handle) {return "handle/10024/"+handle; }

    @Override
    protected void doInit() throws ResourceException {

        host = (String)getRequest().getResourceRef().getHostIdentifier();
        String s = (String)getRequest().getAttributes().get("handle");
        this.handle = s;
        this.handle = this.handle.replaceAll("\\+", "/");

        Series headers = (Series)getRequestAttributes().get("org.restlet.http.headers");
        String date = headers.getFirstValue("If-Modified-Since");
        if(date != null) {
            this.ifModifiedSince = DateUtils.parse(date);
        }
    }

    @Get
    public Representation toJson(){
        GetOptions.allowAccess(getResponse());
        ItemIterator it = null;
        try{
            it = Item.findByMetadataField(context, "dc", "identifier", "uri", host+"handle/"+this.handle);
        }catch(Exception e){
            if(context != null){
                context.abort();
            }
            log.log(Priority.INFO, e);
        }

        try{
            if(it.hasNext()){
                this.item = it.next();
            }else{
                return new StringRepresentation("{\"error\": \"Item not found\", \"status\": 404, \"url\": \""+host+"handle/"+this.handle+"\" }", MediaType.APPLICATION_JSON);
            }
        }catch(SQLException e){
            if(context != null){
                context.abort();
            }
            log.log(Priority.INFO, e);
        }

        GetOptions.lastModified(getResponse(), this.item.getLastModified());

        StubItem stub = null;
        try{
            stub = new StubItem(this.item);

            if(this.ifModifiedSince != null) {
                if (this.ifModifiedSince.after(stub.getLastModified())) {
                    getResponse().setStatus(Status.REDIRECTION_NOT_MODIFIED);
                    if(context!= null){
                        context.complete();
                    }
                    return new EmptyRepresentation();
                }
            }
        }catch(SQLException e){
            if(context != null){
                context.abort();
            }
            log.log(Priority.INFO, e);
        }

        try{
            if(context != null){
                context.complete();
            }
        }catch (SQLException e){
            log.log(Priority.ERROR, e);
        }

        Gson gson = new Gson();

        return new StringRepresentation(gson.toJson(stub), MediaType.APPLICATION_JSON);

    }

}
