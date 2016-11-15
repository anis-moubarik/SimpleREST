/**
 * A RESTful web service on top of DSpace. Copyright (C) 2010-2014 National
 * Library of Finland This program is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package fi.helsinki.lib.simplerest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

import org.apache.log4j.Priority;
import org.dspace.content.Bitstream;
import org.dspace.content.Bundle;
import org.dspace.content.Item;
import org.dspace.core.ConfigurationManager;
import org.dspace.core.Context;
import org.dspace.eperson.EPerson;

import org.restlet.resource.ServerResource;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.MediaType;
import org.restlet.data.Status;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;

import java.lang.reflect.Method;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

abstract class BaseResource extends ServerResource {

    private static Logger log = Logger.getLogger(BaseResource.class);

    protected Context getAuthenticatedContext() throws SQLException {
        /* Fetches the user ID from the attributes put there by the verifier
         and returns a Context with an EPerson associated with that ID.
         Authorization is handled by individual requests by catching
         AuthorizeException */
        try {
            // Removing this breaks tests
            String s = getContext().getParameters().getFirstValue("adminID");
        } catch (NumberFormatException e) {
        }

        Context context = new Context();
        Integer id = (Integer) getRequest().getAttributes().get("currentId");
        EPerson ePerson = EPerson.find(context, id);
        context.setCurrentUser(ePerson);
        return context;
    }

    protected String makeBitstreamUrl(int id) throws UnsupportedEncodingException, SQLException{
        Context c = null;
        Bitstream bs = null;
        try{
            c = getAuthenticatedContext();
            bs = Bitstream.find(c, id);
            log.info("Bitstream: " + bs.getID());
            if(bs == null){
                log.error("Error retrieveing bitstream");
                return null;
            }
        }catch(SQLException e){
            log.error("Error retrieving bitstream: ", e);
            return "SQLException";
        }finally {
            if(c != null){
                c.abort();
            }
        }
        Bundle[] bn = bs.getBundles();
        String handle = null;
        if (bn.length > 0) {
            Item i[] = bn[0].getItems();
            if (i.length > 0) {
                handle = i[0].getHandle();
            }
        }

        if (handle != null) {
            log.log(Priority.INFO, "Found handle for bitstream url");
            return ConfigurationManager.getProperty("dspace.url")
                    + "/bitstream/"
                    + handle
                    + "/"
                    + String.valueOf(bs.getSequenceID())
                    + "/"
                    + URLEncoder.encode(bs.getName(), "UTF-8");
        }
        else {
            log.log(Priority.INFO, "Handle not found for bitstream url");
            return ConfigurationManager.getProperty("dspace.url")
                    + "/retrieve/"
                    + String.valueOf(bs.getID());
        }
    }

    protected void makeInputRow(Document d, Element form,
            String fieldName,
            String printableName) {
        makeInputRow(d, form, fieldName, printableName, "text");
    }

    protected void makeInputRow(Document d, Element form,
            String fieldName,
            String printableName,
            String type) {
        Element label = d.createElement("label");
        label.setAttribute("for", fieldName);
        label.appendChild(d.createTextNode(printableName));
        form.appendChild(label);
        Element input = d.createElement("input");
        input.setAttribute("type", type);
        input.setAttribute("name", fieldName);
        input.setAttribute("id", fieldName);
        form.appendChild(input);
        Element br = d.createElement("br");
        form.appendChild(br);
        return;
    }

    protected void setAttribute(Element el, String attribute, String value) {
        try {
            el.setAttribute(attribute, value);
        } catch (DOMException e) {
            // We trust that this never happens...
        }
    }

    protected void setClass(Element el, String className) {
        setAttribute(el, "class", className);
    }

    protected void setId(Element el, String id) {
        setAttribute(el, "id", id);
    }

    protected void addDtDd(Document d, Element dl, String key, String value) {
        Element dt = d.createElement("dt");
        dt.appendChild(d.createTextNode(key));
        dl.appendChild(dt);

        Element dd = d.createElement("dd");
        dd.appendChild(d.createTextNode(value != null ? value : ""));
        dl.appendChild(dd);
    }

    protected StringRepresentation successOk(String message) {
        setStatus(Status.SUCCESS_OK);
        return new StringRepresentation(message, MediaType.TEXT_PLAIN);
    }

    protected StringRepresentation successCreated(String message,
            String locationUri) {
        setStatus(Status.SUCCESS_CREATED);
        setLocationRef(locationUri);
        return new StringRepresentation(message, MediaType.TEXT_PLAIN);
    }

    protected StringRepresentation errorInternal(Context c, String message) {
        return error(c, message, Status.SERVER_ERROR_INTERNAL);
    }

    protected StringRepresentation errorNotFound(Context c, String message) {
        return error(c, message, Status.CLIENT_ERROR_NOT_FOUND);
    }

    protected StringRepresentation error(Context c, String message,
            Status status) {
        if (c != null) {
            c.abort();
        }
        setStatus(status);
        return new StringRepresentation(message, MediaType.TEXT_PLAIN);
    }

    // Ok, maybe this a overkill considering that all we really want to do
    // return is a constant string... which we could have put into a
    // configuration file.
    protected String baseUrl() {
        String relUrl = null;
        try {
            Method method = this.getClass().getDeclaredMethod("relativeUrl",
                    Integer.TYPE);
            relUrl = (String) method.invoke(this, 1337);
        } catch (Exception e) {
            // As long as we call baseUrl() from a class that has a correctly
            // defined relatiUrl(int) method we should not get any exceptions..
        }
        try {
            String url = getRequest().getResourceRef().getIdentifier();
            int n = relUrl.split("/").length;
            while (n-- > 0) {
                url = url.substring(0, url.lastIndexOf('/'));
            }
            return url + "/";
        } catch (Exception e) {
            
            return "/";
        }
    }
}
