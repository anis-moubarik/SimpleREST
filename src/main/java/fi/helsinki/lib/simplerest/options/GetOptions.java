/**
 * A RESTful web service on top of DSpace.
 * Copyright (C) 2010-2013 National Library of Finland
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


package fi.helsinki.lib.simplerest.options;

import org.restlet.Response;
import org.restlet.engine.header.Header;
import org.restlet.engine.header.HeaderConstants;
import org.restlet.util.Series;

/**
 *
 * @author moubarik
 */
public abstract class GetOptions {
    public static void allowAccess(Response resp){
        if(resp == null)
            return;
        Series<Header> responseHeaders = (Series<Header>) resp.getAttributes().get(HeaderConstants.ATTRIBUTE_HEADERS);
        if (responseHeaders == null) {
            responseHeaders = new Series(Header.class);
            resp.getAttributes().put(HeaderConstants.ATTRIBUTE_HEADERS,
                    responseHeaders);
        }
        responseHeaders.add(new Header("Access-Control-Allow-Origin", "*"));
    }
}
