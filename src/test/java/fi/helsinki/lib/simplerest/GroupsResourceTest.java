/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.lib.simplerest;

import fi.helsinki.lib.simplerest.TestServlets.GroupsResourceServlet;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.*;
import static org.junit.Assert.*;

public class GroupsResourceTest {
    private ServletTester tester;
    
    @Before
    public void setUp() throws Exception {
        tester = new ServletTester();
        tester.setContextPath("/");
        tester.addServlet(GroupsResourceServlet.class, "/groups/*");
        tester.start();
        
    }
    
    @Test
    public void testGetXml() throws Exception {
        HttpTester req = new HttpTester();
        HttpTester res = new HttpTester();
        
        req.setMethod("GET");
        req.setHeader("HOST", "tester"); 
        req.setURI("/groups/xml");
        res.parse(tester.getResponses(req.generate()));
        System.out.println(res.getContent());
        assertEquals(200, res.getStatus());
        
        String[] attributes = {"id","href"};
        for(String attribute : attributes){
            assertEquals(res.getContent().contains(attribute), true);
        }
        
        String[] values = {"testGroup", "/group/1"};
        for(String value : values){
            assertEquals(res.getContent().contains(value), true);
        }
    }
    
}
