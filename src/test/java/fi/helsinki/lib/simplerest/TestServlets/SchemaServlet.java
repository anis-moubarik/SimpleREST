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


package fi.helsinki.lib.simplerest.TestServlets;

import fi.helsinki.lib.simplerest.MetadataSchemaResource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.dspace.content.MetadataSchema;
import static org.mockito.Mockito.*;

/**
 *
 * @author moubarik
 */
public class SchemaServlet extends HttpServlet{
    private MetadataSchema mockedMetadataschema;
    private MetadataSchemaResource msr;
    private static Logger log = Logger.getLogger(SchemaServlet.class);
    
    @Override
    public void init(){
        mockedMetadataschema = mock(MetadataSchema.class);
        when(mockedMetadataschema.getSchemaID()).thenReturn(1);
        when(mockedMetadataschema.getName()).thenReturn("dckk");
        when(mockedMetadataschema.getNamespace()).thenReturn("http://kk.fi/dckk/");
        
        msr = new MetadataSchemaResource(mockedMetadataschema, 1);
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        if(req.getPathInfo().equals("/xml")){
            xmlTest(resp);
        }else if(req.getPathInfo().equals("/json")){
            try{
                jsonTest(resp);
            }catch(SQLException ex){
                log.log(Priority.INFO, ex);
            }
        }
    }

    private void xmlTest(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.write(msr.toXml().getText());
    }

    private void jsonTest(HttpServletResponse resp) throws IOException, SQLException {
        PrintWriter out = resp.getWriter();
        out.write(msr.toJson());
    }
    
}
