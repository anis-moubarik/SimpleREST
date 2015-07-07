/**
 * A RESTful web service on top of DSpace.
 * Copyright (C) 2010-2014 National Library of Finland
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package fi.helsinki.lib.simplerest;

import java.sql.SQLException;
import org.dspace.core.Context;
import org.dspace.eperson.Group;

import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.data.MediaType;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class GroupsResource extends BaseResource {

    private static Logger log = Logger.getLogger(GroupsResource.class);
    private Group[] groups;
    private Context c;

    static public String relativeUrl(int dummy) {
        return "groups";
    }

    public GroupsResource(Group[] groups) {
        this.c = null;
        this.groups = groups;
    }

    public GroupsResource() {
        try {
            this.c = new Context();
        } catch (SQLException e) {
            log.log(Priority.FATAL, e);
        }
    }
    
    @Get("xml")
    public Representation toXml() {
        DomRepresentation representation = null;
        Document d = null;
        
        try {
            representation = new DomRepresentation(MediaType.TEXT_HTML);
            d = representation.getDocument();

            try {
                groups = Group.findAll(c, 0);
            } catch (Throwable e) {
                if (this.groups.length == 0 || this.groups == null) {
                    return errorInternal(c, e.toString());
                }
            }

        } catch (Exception e) {
            return errorInternal(c, e.toString());
        }

        Element html = d.createElement("html");
        d.appendChild(html);

        Element head = d.createElement("head");
        html.appendChild(head);

        Element title = d.createElement("title");
        title.appendChild(d.createTextNode("Groups"));
        head.appendChild(title);

        Element body = d.createElement("body");
        html.appendChild(body);


        Element ul = d.createElement("ul");
        ul.setAttribute("id", "groups");
        body.appendChild(ul);

        for (Group group : groups) {
            Element li = d.createElement("li");
            Element a = d.createElement("a");
            ul.appendChild(li);
            li.appendChild(a);
            String url = baseUrl()
                    + GroupResource.relativeUrl(group.getID());
            a.setAttribute("href", url);
            Text text = d.createTextNode(group.getName());
            a.appendChild(text);
        }

        if (c != null) {
            c.abort(); // Same as c.complete() because we didn't modify the db.
        }
        return representation;
    }

}
