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

import fi.helsinki.lib.simplerest.CommunityResource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dspace.content.Community;
import static org.mockito.Mockito.*;
import org.restlet.representation.InputRepresentation;
import org.restlet.representation.Representation;

/**
 *
 * @author moubarik
 */
public class CommunityServlet extends HttpServlet{
    
    private Community mockedCommunity;
    private CommunityResource cr;
    private final Logger log = Logger.getLogger(CommunityServlet.class.getName());
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        mockedCommunity = mock(Community.class);
        when(mockedCommunity.getID()).thenReturn(1);
        when(mockedCommunity.getName()).thenReturn("test");
        when(mockedCommunity.getType()).thenReturn(10);
        when(mockedCommunity.getMetadata("short_description")).thenReturn("testi kuvaus");
        when(mockedCommunity.getMetadata("introductory_text")).thenReturn("testi intro");
        when(mockedCommunity.getMetadata("copyright_text")).thenReturn("testi copyright");
        when(mockedCommunity.getMetadata("side_bar_text")).thenReturn("testi sidebar");
        when(mockedCommunity.getLogo()).thenReturn(null);
        
        cr = new CommunityResource(mockedCommunity, mockedCommunity.getID());
    }
    
    /**
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        if(req.getPathInfo().equals("/xml")){
            xmlTest(resp);
        }else if(req.getPathInfo().equals("/json")){
            jsonTest(resp);
        }
    }
    
    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Representation rep = cr.toXml();
        
        //Empty Community with new CommunityResource
        Community origComm = mock(Community.class);
        when(origComm.getID()).thenReturn(2);
        when(origComm.getName()).thenReturn("");
        when(origComm.getType()).thenReturn(0);
        when(origComm.getMetadata("short_description")).thenReturn("");
        when(origComm.getMetadata("introductory_text")).thenReturn("");
        when(origComm.getMetadata("copyright_text")).thenReturn("");
        when(origComm.getMetadata("side_bar_text")).thenReturn("");
        when(origComm.getLogo()).thenReturn(null);
        CommunityResource originalCr = new CommunityResource(origComm, 2);
        
        InputRepresentation ir = new InputRepresentation(rep.getStream());
        
        //Edit the originalCr Community by passing the mockedCommunity cr representation to it.
        PrintWriter out = resp.getWriter();
        if(req.getPathInfo().equals("/edit")){
            out.write(originalCr.edit(ir).getText());
            out.write(originalCr.toXml().getText());
        }
    }
    
    public void xmlTest(HttpServletResponse resp) throws IOException{
        PrintWriter out = resp.getWriter();
        try {
            out.write(cr.toXml().getText());
        } catch (Exception ex) {
            log.log(Level.INFO, null, ex);
        } 
    }

    private void jsonTest(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        try{
            out.write(cr.toJson());
        }catch(Exception ex) {
            log.log(Level.INFO, null, ex);
        }
    }
}
