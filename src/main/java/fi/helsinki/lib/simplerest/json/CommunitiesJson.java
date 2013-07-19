/**
 * A RESTful web service on top of DSpace.
 * Copyright (C) 2010-2011 National Library of Finland
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package fi.helsinki.lib.simplerest.json;

import fi.helsinki.lib.simplerest.stubs.StubCommunity;
import com.google.gson.Gson;
import fi.helsinki.lib.simplerest.CommunitiesResource;
import fi.helsinki.lib.simplerest.CommunityResource;
import org.dspace.core.Context;
import org.dspace.content.Community;
import fi.helsinki.lib.simplerest.BaseResource;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.data.Status;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class CommunitiesJson extends BaseResource {

    private static Logger log = Logger.getLogger(CommunitiesJson.class);
    private int communityId;
    private Community community;
    private Context c;
    
    static public String relativeUrl(int communityId) {
        return "community/" + communityId;
    }
    
    @Override
    protected void doInit() throws ResourceException {
        try {
            String id = (String)getRequest().getAttributes().get("communityId");
            this.communityId = Integer.parseInt(id);
        }
        catch (NumberFormatException e) {
            ResourceException resourceException =
                new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,
                                      "Community ID must be a number.");
            throw resourceException;
        }
    }
    
    @Get("json")
    public String toJson(){
        Gson gson = new Gson();
        try{
            community = Community.find(c, communityId);
        }catch(Exception e){
            Logger.getLogger(CommunityResource.class).log(null, Priority.INFO, e, e);
        }
        StubCommunity s = new StubCommunity(community.getID(), community.getName(), community.getMetadata("short_description"),
                    community.getMetadata("introductory_text"), community.getMetadata("copyright_text"), community.getMetadata("side_bar_text"));
        try{
            c.abort();
        }catch(NullPointerException e){
            Logger.getLogger(CommunitiesResource.class.getName()).log(null, Priority.INFO, e.toString(), e);
        }
        return gson.toJson(s);
    }
}
