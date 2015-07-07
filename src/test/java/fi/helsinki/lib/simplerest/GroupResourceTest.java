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

import com.google.gson.Gson;
import fi.helsinki.lib.simplerest.TestServlets.GroupServlet;
import fi.helsinki.lib.simplerest.stubs.StubGroup;
import java.io.IOException;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author moubarik
 */
public class GroupResourceTest {
    
    private ServletTester tester;
    
    @Before
    public void setUp() throws Exception{
        tester = new ServletTester();
        tester.setContextPath("/");
        tester.addServlet(GroupServlet.class, "/group/*");
        tester.start();
    }
    
    @Test
    public void testRelativeUrl() {
        String actualUrl = GroupResource.relativeUrl(11);
        assertEquals("group/11", actualUrl);
    }
    
    @Test
    public void testGetXml() throws IOException, Exception{    
        
        HttpTester req = new HttpTester();
        HttpTester resp = new HttpTester();
        
        req.setMethod("GET");
        req.setHeader("HOST", "tester");
        req.setURI("/group/xml");
        resp.parse(tester.getResponses(req.generate()));
        System.out.println("");
        System.out.println(resp.getContent());
        System.out.println("");
        assertEquals(200, resp.getStatus());
        /*String[] attributes = { "name"};        
        for(String attribute : attributes){
            assertEquals(resp.getContent().contains(attribute), true);
        }*/
        //assertEquals(true, true);
    }
   
    @Test
    public void testGetJson() throws IOException, Exception{
        HttpTester req = new HttpTester();
        HttpTester resp = new HttpTester();
        
        req.setMethod("GET");
        req.setHeader("HOST", "tester");
        req.setURI("/group/json");
        resp.parse(tester.getResponses(req.generate()));
        Gson gson = new Gson();
        System.out.println(resp.getContent());
        StubGroup sg = gson.fromJson(resp.getContent(), StubGroup.class);
    }
    
}
