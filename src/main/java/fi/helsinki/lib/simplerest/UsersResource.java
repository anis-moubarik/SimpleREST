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

import com.google.gson.Gson;
import fi.helsinki.lib.simplerest.options.GetOptions;
import fi.helsinki.lib.simplerest.stubs.StubUser;
import java.sql.SQLException;
import org.dspace.core.Context;
import org.dspace.eperson.EPerson;

import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.data.MediaType;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class UsersResource extends BaseResource {

    private static Logger log = Logger.getLogger(UsersResource.class);

    static public String relativeUrl(int dummy) {
        return "users";
    }
    
    private EPerson[] epersons;
    private Context c;

    public UsersResource(EPerson[] epersons) {
        this.c = null;
        this.epersons = epersons;
    }

    public UsersResource() {
        this.epersons = null;
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
            this.epersons = EPerson.findAll(c, 0);
        } catch (Throwable e) {
            if (this.epersons.length == 0 || this.epersons == null) {
                return errorInternal(c, e.toString());
            }
        }

        try {
            representation = new DomRepresentation(MediaType.TEXT_HTML);
            d = representation.getDocument();
        } catch (Exception e) {
            return errorInternal(c, e.toString());
        }


        Element html = d.createElement("html");
        d.appendChild(html);

        Element head = d.createElement("head");
        html.appendChild(head);

        Element title = d.createElement("title");
        title.appendChild(d.createTextNode("Users"));
        head.appendChild(title);

        Element body = d.createElement("body");
        html.appendChild(body);

        Element ul = d.createElement("ul");
        ul.setAttribute("id", "users");
        body.appendChild(ul);

        for (EPerson eperson : epersons) {
            Element li = d.createElement("li");
            Element a = d.createElement("a");
            ul.appendChild(li);
            li.appendChild(a);
            String url = baseUrl()
                    + UserResource.relativeUrl(eperson.getID());
            a.setAttribute("href", url);
            Text text = d.createTextNode(eperson.getFullName());
            a.appendChild(text);
        }

        if (c != null) {
            c.abort(); // Same as c.complete() because we didn't modify the db.
        }
        return representation;
    }
    
    @Get("json")
    public String toJson() {
        GetOptions.allowAccess(getResponse());
        try {
            epersons = EPerson.findAll(c, 0);
        } catch (Throwable e) {
            if (this.epersons.length == 0 || this.epersons == null) {
                return errorInternal(c, e.toString()).getText();
            }
        } finally {
            if (c != null) {
                c.abort();
            }
        }

        Gson gson = new Gson();
        StubUser[] toJsonUsers = new StubUser[epersons.length];

        for (int i = 0; i < epersons.length; i++) {
            toJsonUsers[i] = new StubUser(epersons[i].getID(), epersons[i].getEmail(), epersons[i].getLanguage(),
                    epersons[i].getNetid(), epersons[i].getFullName(), epersons[i].getFirstName(), epersons[i].getLastName(),
                    epersons[i].canLogIn(), epersons[i].getRequireCertificate(), epersons[i].getSelfRegistered());
        }

        return gson.toJson(toJsonUsers);
    }
}
