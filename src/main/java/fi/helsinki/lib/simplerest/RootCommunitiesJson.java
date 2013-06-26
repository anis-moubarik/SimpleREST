/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.lib.simplerest;

import com.google.gson.Gson;
import common.Logger;
import org.dspace.content.Community;
import org.dspace.core.Context;
import org.restlet.resource.Get;

/**
 *
 * @author moubarik
 */
public class RootCommunitiesJson extends BaseResource{
    
    private static Logger log = Logger.getLogger(RootCommunitiesJson.class);
    
    static public String relativeUrl(int dummy){
        return "rootcommunities/json";
    }
    
    @Get("json")
        public String toJson() {
        Community[] communities;
        Context c = null;
        try{
            c = new Context();
            communities = Community.findAllTop(c);
        }catch(Exception e){
            return errorInternal(c, e.toString()).getText();
        }
        
        Gson gson = new Gson();
                
        return gson.toJson(communities[0]);
    }
}
