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


package fi.helsinki.lib.simplerest.options;

import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.engine.header.Header;
import org.restlet.engine.header.HeaderConstants;
import org.restlet.engine.util.DateUtils;
import org.restlet.util.Series;

import java.util.Date;

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
        responseHeaders.add(new Header("Connection", "Keep-Alive"));
        responseHeaders.add(new Header("Keep-Alive", "timeout=10000"));
    }

    public static void lastModified(Response resp, Date lastModified){
        if(resp == null)
            return;
        Series<Header> responseHeaders = (Series<Header>) resp.getAttributes().get(HeaderConstants.ATTRIBUTE_HEADERS);
        if (responseHeaders == null) {
            responseHeaders = new Series(Header.class);
            resp.getAttributes().put(HeaderConstants.ATTRIBUTE_HEADERS,
                    responseHeaders);
        }
        responseHeaders.add(new Header("Last-Modified", DateUtils.format(lastModified)));
        responseHeaders.add(new Header("Connection", "Keep-Alive"));
        responseHeaders.add(new Header("Keep-Alive", "timeout=10000"));
    }
}
