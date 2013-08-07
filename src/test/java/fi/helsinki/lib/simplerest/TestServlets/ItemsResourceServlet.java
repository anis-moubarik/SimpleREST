/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.lib.simplerest.TestServlets;

import fi.helsinki.lib.simplerest.ItemsResource;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.dspace.content.Item;
import static org.mockito.Mockito.*;
import org.restlet.representation.InputRepresentation;
import org.restlet.representation.Representation;

/**
 *
 * @author moubarik
 */
public class ItemsResourceServlet extends HttpServlet{
    
    private Item mockedItem, mockedItem2;
    private ItemsResource ir;
    
    private final Logger log = Logger.getLogger(ItemsResourceServlet.class);
    
    @Override
    public void init(ServletConfig config){
        mockedItem = mock(Item.class);
        when(mockedItem.getID()).thenReturn(1);
        when(mockedItem.getName()).thenReturn("test");
        
        mockedItem2 = mock(Item.class);
        when(mockedItem.getID()).thenReturn(2);
        when(mockedItem.getName()).thenReturn("test2");
        
        Item[] items = new Item[2];
        items[0] = mockedItem; items[1] = mockedItem2;
        
        ir = new ItemsResource(items);
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        if(req.getPathInfo().equals("/xml")){
            xmlTest(resp);
        }else if(req.getPathInfo().equals("/json")){
            jsonTest(resp);
        }
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Representation r = ir.addItem(null);
    }
    
    public void xmlTest(HttpServletResponse resp) throws IOException{
        PrintWriter out = resp.getWriter();
        try{
            out.write(ir.toXml().getText());
        }catch(Exception ex){
            log.log(Priority.INFO, ex);
        }
    }
    
    public void jsonTest(HttpServletResponse resp) throws IOException{
        PrintWriter out = resp.getWriter();
        try{
            out.write(ir.toJson());
        }catch(Exception ex){
            log.log(Priority.INFO, ex);
        }
    }    
}
