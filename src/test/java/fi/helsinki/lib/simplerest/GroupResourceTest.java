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

import fi.helsinki.lib.simplerest.TestServlets.GroupServlet;
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
        tester.addServlet(GroupServlet.class, "/groups/*");
        tester.start();
    }
    
    @Test
    public void testGetXml(){
        assertEquals(true, true);
    }
    
    @Test
    public void testGetJson(){
        
    }
    
}
