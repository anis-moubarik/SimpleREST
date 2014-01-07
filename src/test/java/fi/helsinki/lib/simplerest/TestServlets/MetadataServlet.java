/**
 * A RESTful web service on top of DSpace.
 * Copyright (C) 2010-2014 National Library of Finland
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


package fi.helsinki.lib.simplerest.TestServlets;

import fi.helsinki.lib.simplerest.MetadataFieldResource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.dspace.content.MetadataField;
import org.dspace.content.MetadataSchema;
import static org.mockito.Mockito.*;

/**
 *
 * @author moubarik
 */
public class MetadataServlet extends HttpServlet{
    private MetadataField mockedMetadatafield;
    private MetadataSchema mockedMetadataschema;
    private MetadataFieldResource mfr;
    private Logger log;
    
    @Override
    public void init(){
        log = Logger.getLogger(MetadataServlet.class);
        mockedMetadataschema = mock(MetadataSchema.class);
        when(mockedMetadataschema.getName()).thenReturn("dckk");
        
        mockedMetadatafield = mock(MetadataField.class);
        when(mockedMetadatafield.getElement()).thenReturn("testElement");
        when(mockedMetadatafield.getQualifier()).thenReturn("testQualifier");
        when(mockedMetadatafield.getFieldID()).thenReturn(1);
        when(mockedMetadatafield.getScopeNote()).thenReturn("Description");
        
        mfr = new MetadataFieldResource(mockedMetadataschema, mockedMetadatafield, 1);
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        if(req.getPathInfo().equals("/xml")){
            xmlTest(resp);
        }else if(req.getPathInfo().equals("/json")){
            try {
                jsonTest(resp);
            } catch (SQLException ex) {
                log.log(Priority.FATAL, null, ex);
            }
        }
    }

    private void xmlTest(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.write(mfr.toXml().getText());
    }

    private void jsonTest(HttpServletResponse resp) throws SQLException, IOException {
        PrintWriter out = resp.getWriter();
        out.write(mfr.toJson());
    }
}
