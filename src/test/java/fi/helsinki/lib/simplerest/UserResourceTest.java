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
import fi.helsinki.lib.simplerest.TestServlets.UserServlet;
import fi.helsinki.lib.simplerest.stubs.StubUser;
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
public class UserResourceTest {
        
    private ServletTester tester;
    
    @Before
    public void setUp() throws Exception{
        tester = new ServletTester();
        tester.setContextPath("/");
        tester.addServlet(UserServlet.class, "/user/*");
        tester.start();
    }
    
    @Test
    public void testGetXml() throws IOException, Exception{
        HttpTester req = new HttpTester();
        HttpTester resp = new HttpTester();
        
        req.setMethod("GET");
        req.setHeader("HOST", "tester");
        req.setURI("/user/xml");
        resp.parse(tester.getResponses(req.generate()));
        System.out.println(resp.getContent());
        
        assertEquals(200, resp.getStatus());
        String[] attributes = {"email", "id", "language", "netid", "fullname", "firstname",
        "lastname", "can login", "require certificate", "self registered"};
        for(String attribute : attributes){
            assertEquals(resp.getContent().contains(attribute), true);
        }
        
        String[] values = {"test(a)test.com", "fi", "1", "testi testaaja", "testi", "testaaja", "false", "true"};
        
        for(String value : values){
            assertEquals(resp.getContent().contains(value), true);
        }
    }
    
    @Test
    public void testGetJson() throws IOException, Exception{
        HttpTester req = new HttpTester();
        HttpTester resp = new HttpTester();
        
        req.setMethod("GET");
        req.setHeader("HOST", "tester");
        req.setURI("/user/json");
        resp.parse(tester.getResponses(req.generate()));
        
        System.out.println(resp.getContent());
        
        Gson gson = new Gson();
        StubUser su = gson.fromJson(resp.getContent(), StubUser.class);
        
        assertEquals(200, resp.getStatus());
        assertEquals(su.getId(), 1);
        assertEquals(su.getEmail(), "test@test.com");
        assertEquals(su.getLanguage(), "fi");
        assertEquals(su.getNetid(), "1");
        assertEquals(su.getFullname(), "testi testaaja");
        assertEquals(su.getFirstname(), "testi");
        assertEquals(su.getLastname(), "testaaja");
        assertEquals(su.isCan_login(), true);
        assertEquals(su.isRequire_certificate(), false);
        assertEquals(su.isSelf_registered(), true);
    }
    
}
