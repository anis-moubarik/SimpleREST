package fi.helsinki.lib.simplerest;

import com.google.gson.Gson;
import fi.helsinki.lib.simplerest.options.GetOptions;
import fi.helsinki.lib.simplerest.stubs.StubItem;
import org.dspace.content.ItemIterator;
import org.dspace.core.Context;
import org.dspace.content.Item;

import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

/**
 * Created by moubarik on 2.6.15.
 */

public class HandleResource extends BaseResource{

    private static Logger log = Logger.getLogger(HandleResource.class);

    private String handle;
    private Item item;
    private Context context;

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
        String s = (String)getRequest().getAttributes().get("handle");
        this.handle = s;
    }

    @Get
    public String toJson(){
        GetOptions.allowAccess(getResponse());
        ItemIterator it = null;
        try{
            it = Item.findByMetadataField(context, "dc", "identifier", "urn", handle);
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
                return "{\"error\": \"Item not found\"}";
            }
        }catch(SQLException e){
            if(context != null){
                context.abort();
            }
            log.log(Priority.INFO, e);
        }


        StubItem stub = null;
        try{
            stub = new StubItem(this.item);
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

        return gson.toJson(stub);

    }

}
