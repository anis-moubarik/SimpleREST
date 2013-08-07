/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.lib.simplerest;

import fi.helsinki.lib.simplerest.TestServlets.ItemsResourceServlet;
import java.io.IOException;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moubarik
 */
public class ItemsResourceTest {
    
    private ItemsResource ir;
    
    private ServletTester tester;
    
    @Before
    public void setUp() throws Exception{
        tester = new ServletTester();
        tester.setContextPath("/");
        tester.addServlet(ItemsResourceServlet.class, "/items/*");
        tester.start();
    }
    
    @Test
    public void testGetXml(){
        
    }
    
    @Test
    public void testCreateItem() throws IOException, Exception{
        HttpTester req = new HttpTester();
        HttpTester resp = new HttpTester();
        
        req.setMethod("POST");
        req.setHeader("HOST", "tester");
        req.setURI("/items/add");
        resp.parse(tester.getResponses(req.generate()));
        assertEquals(200, resp.getStatus());
    }
    
}
