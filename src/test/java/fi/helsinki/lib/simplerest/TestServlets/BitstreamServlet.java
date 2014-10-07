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

import fi.helsinki.lib.simplerest.BitstreamResource;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dspace.content.Bitstream;
import static org.mockito.Mockito.*;

/**
 *
 * @author moubarik
 */
public class BitstreamServlet extends HttpServlet{
    
    private Bitstream mockedBitstream;
    private BitstreamResource br;
    
    @Override
    public void init(ServletConfig config){
        mockedBitstream = mock(Bitstream.class, RETURNS_DEEP_STUBS);
        when(mockedBitstream.getID()).thenReturn(1);
        when(mockedBitstream.getName()).thenReturn("testi.pdf");
        when(mockedBitstream.getSize()).thenReturn(1337L);
        when(mockedBitstream.getUserFormatDescription()).thenReturn("");
        when(mockedBitstream.getDescription()).thenReturn("");
        when(mockedBitstream.getSource()).thenReturn("testi.pdf");
        
        br = new BitstreamResource(mockedBitstream, 1);
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        if(req.getPathInfo().equals("/xml")){
            xmlTest(resp);
        }else if(req.getPathInfo().equals("/json")){
            jsonTest(resp);
        }
    }

    private void xmlTest(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.write(br.get().getText());
    }

    private void jsonTest(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.write(br.toJson());
    }
}
