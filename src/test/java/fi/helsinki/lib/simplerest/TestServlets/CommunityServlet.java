/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.lib.simplerest.TestServlets;

import fi.helsinki.lib.simplerest.CommunityResource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dspace.content.Community;
import static org.mockito.Mockito.*;

/**
 *
 * @author moubarik
 */
public class CommunityServlet extends HttpServlet{
    
    private Community mockedCommunity;
    private CommunityResource cr;
    
    /**
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        
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
        
        if(req.getPathInfo().equals("/xml")){
            xmlTest(resp);
        }else if(req.getPathInfo().equals("/json")){
            jsonTest(resp);
        }
    }
    
    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp){
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
        
        if(req.getPathInfo().equals("/edit")){
            //Stuff here
            
        }
    }
    
    public void xmlTest(HttpServletResponse resp) throws IOException{
        PrintWriter out = resp.getWriter();
        try {
            out.write(cr.toXml().getText());
        } catch (Exception ex) {
            Logger.getLogger(CommunityServlet.class.getName()).log(Level.INFO, null, ex);
        } 
    }

    private void jsonTest(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        try{
            out.write(cr.toJson());
        }catch(Exception ex) {
            Logger.getLogger(CommunityServlet.class.getName()).log(Level.INFO, null, ex);
        }
    }
}
