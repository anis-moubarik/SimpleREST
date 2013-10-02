
package fi.helsinki.lib.simplerest;

import com.google.gson.Gson;
import fi.helsinki.lib.simplerest.stubs.StubCollection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.dspace.content.Collection;
import org.dspace.content.Community;
import org.dspace.core.Context;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Options;


/**
 *
 * @author moubarik
 */
public class AllCollectionsResource extends BaseResource{
    
    private static Logger log = Logger.getLogger(AllCollectionsResource.class);
    
    private Collection[] allCollections;
    private Context context;
    
    @Options
    public void doOptions(Representation entity) {
    Form responseHeaders = (Form) getResponse().getAttributes().get("org.restlet.http.headers"); 
    if (responseHeaders == null) { 
        responseHeaders = new Form(); 
        getResponse().getAttributes().put("org.restlet.http.headers", responseHeaders); 
    } 
    responseHeaders.add("Access-Control-Allow-Origin", "*"); 
} 
    
    public AllCollectionsResource(Collection[] collections){
        this.allCollections = collections;
    }
    
    public AllCollectionsResource(){
        this.allCollections = null;
        try{
            this.context = new Context();
        }catch(SQLException e){
            log.log(Priority.INFO, e);
        }
        try{
            this.allCollections = Collection.findAll(context);
        }catch(Exception e){
            log.log(Priority.INFO, e);
        }finally{
            context.abort();
        }
    }
    
    static public String relativeUrl(int dummy){
        return "collections";
    }
    
    @Get("json")
    public String toJson() throws SQLException{
        Gson gson = new Gson();
        ArrayList<StubCollection> toJsonCollections = new ArrayList<StubCollection>(25);
        for(Collection c : allCollections){
            toJsonCollections.add(new StubCollection(c));
        }
        
        try{
            context.abort();
        }catch(NullPointerException e){
            log.log(Priority.INFO, e);
        }
        
        return gson.toJson(toJsonCollections);
    }
}
