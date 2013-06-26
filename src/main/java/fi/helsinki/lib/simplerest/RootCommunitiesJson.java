/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.lib.simplerest;

import com.google.gson.Gson;
import common.Logger;
import java.io.Serializable;
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
        
        /*Community class from DSpace-api won't work for Serialization to json,
        so we use StubCommunity, and use a slow loop to create new StubCommunity array,
        which will be Serializable and converted to json. */
        Gson gson = new Gson();
        StubCommunity[] toJsonCommunities = new StubCommunity[communities.length];
        for(int i = 0; i < communities.length; i++){
            toJsonCommunities[i] = new StubCommunity(communities[i].getID(), communities[i].getName());
        }
                
        return gson.toJson(toJsonCommunities);
    }
}

class StubCommunity implements Serializable{
    
    private int id;
    private String name;
    
    public StubCommunity(int id, String name){
        id = this.id;
        name = this.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
