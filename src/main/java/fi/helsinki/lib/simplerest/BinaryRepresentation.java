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

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import org.restlet.representation.OutputRepresentation;
import org.restlet.data.MediaType;

import org.apache.commons.io.IOUtils;

public class BinaryRepresentation extends OutputRepresentation {

    private InputStream inputStream;

    public BinaryRepresentation(MediaType mediaType, InputStream inputStream) {
        super(mediaType);
        this.inputStream = inputStream;
    }
    
    public void write(OutputStream outputStream) throws IOException {
        IOUtils.copy(this.inputStream, outputStream);
    }
}