/**
 * A RESTful web service on top of DSpace.
 * Copyright (C) 2010-2011 National Library of Finland
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
/*
 * @(#)CommunityResourceTest.java
 */
package fi.helsinki.lib.simplerest;

import java.io.IOException;
import fi.helsinki.lib.simplerest.TestServlets.CommunityResourceServlet;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;


import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;


/**
 * JUnit test.
 * <ul>
 * <li>http://www.junit.org/</li>
 * <li>http://junit.sourceforge.net/doc/faq/faq.htm</li>
 * </ul>
 * Testing the methods of <code>CommunityResource</code> class.
 * @author Markos Mevorah
 * @version %I%, %G%
 * @see fi.helsinki.lib.simplerest.CommunityResource
 */
public class CommunityResourceTest {

    /**
     * @see fi.helsinki.lib.simplerest.CommunityResource
     */
    private CommunityResource communityResource;
    private HttpTester request;
    private HttpTester response;
    private ServletTester tester;

    public CommunityResourceTest() {
    }

    /**
     * JUnit method annotated with {@link org.junit.Before}.
     * Initializing the test resources.
     */
    @Before
    public void setUp() throws Exception {
        this.communityResource = new CommunityResource();
        this.tester = new ServletTester();
        this.tester.setContextPath("/");
        this.tester.addServlet(CommunityResourceServlet.class, "/");
        this.tester.start();
        
        this.request = new HttpTester();
        this.response = new HttpTester();
        this.request.setMethod("GET");
        this.request.setHeader("Host", "tester");
        this.request.setVersion("HTTP/1.0");
    }
    
    @Test
    public void testGet() throws IOException, Exception{
        this.request.setURI("/");
        this.response.parse(tester.getResponses(request.generate()));
        assertTrue(this.response.getMethod() == null);
        assertEquals(200, this.response.getStatus());
        assertEquals("1 Testi", this.response.getContent());
    }

    /**
     * JUnit method annotated with {@link org.junit.After}.
     * Releasing the test resources.
     */
    @After
    public void tearDown() {
        this.communityResource = null;
    }

    /**
     * Test of relativeUrl method, of class CommunityResource.
     */
    @Test
    public void testRelativeUrl() {
        String actualUrl = CommunityResource.relativeUrl(23);
        assertEquals("community/23", actualUrl);
    }

    /**
     * Test of doInit method, of class CommunityResource.
     */
    @Test(expected = NullPointerException.class)
    public void testDoInit() throws Exception {
        this.communityResource.doInit();
    }

    /**
     * Test of edit method, of class CommunityResource.
     */
    @Test(expected = NullPointerException.class)
    public void testEdit() {
        StringRepresentation representation =
                             (StringRepresentation) this.communityResource.edit(null);
    }

    /**
     * Test of delete method, of class CommunityResource.
     */
    @Test
    public void testDelete() {
        StringRepresentation representation =
                             (StringRepresentation) this.communityResource.delete();
        assertEquals(MediaType.TEXT_PLAIN, representation.getMediaType());
        assertEquals("java.lang.NullPointerException", representation.getText());
    }
}
