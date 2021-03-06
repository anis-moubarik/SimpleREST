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
/*
 * @(#)CommunityLogoResourceTest.java
 */
package fi.helsinki.lib.simplerest;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;


/**
 * JUnit test.
 * <ul>
 * <li>http://www.junit.org/</li>
 * <li>http://junit.sourceforge.net/doc/faq/faq.htm</li>
 * </ul>
 * Testing the methods of <code>CollectionLogoResource</code> class.
 * @author Markos Mevorah
 * @version %I%, %G%
 * @see fi.helsinki.lib.simplerest.CollectionLogoResource
 */
public class CommunityLogoResourceTest {

    /**
     * @see fi.helsinki.lib.simplerest.CommunityLogoResource
     */
    private CommunityLogoResource communityLogoResource;

    public CommunityLogoResourceTest() {
    }

    /**
     * JUnit method annotated with {@link org.junit.Before}.
     * Initializing the test resources.
     */
    @Before
    public void setUp() {
        this.communityLogoResource = new CommunityLogoResource();
    }

    /**
     * JUnit method annotated with {@link org.junit.After}.
     * Releasing the test resources.
     */
    @After
    public void tearDown() {
        this.communityLogoResource = null;
    }

    /**
     * Test of relativeUrl method, of class CommunityLogoResource.
     */
    @Test
    public void testRelativeUrl() {
        String actualUrl = CommunityLogoResource.relativeUrl(76);
        assertEquals("community/76/logo", actualUrl);
    }

    /**
     * Test of doInit method, of class CommunityLogoResource.
     */
    @Test(expected = NullPointerException.class)
    public void testDoInit() throws Exception {
        this.communityLogoResource.doInit();
    }
    
    /**
     * Test of put method, of class CommunityLogoResource.
     */
    @Test(expected = NullPointerException.class)
    public void testPut() {
        StringRepresentation representation =
                             (StringRepresentation) this.communityLogoResource.put(null);
    }

    /**
     * Test of post method, of class CommunityLogoResource.
     */
    @Test
    public void testPost() {
        StringRepresentation representation =
                             (StringRepresentation) this.communityLogoResource.post(null);
        assertEquals(MediaType.TEXT_PLAIN, representation.getMediaType());
        assertEquals("Community logo resource does not allow POST method.",
                     representation.getText());
    }

    /**
     * Test of delete method, of class CommunityLogoResource.
     */
    @Test
    public void testDelete() {
        StringRepresentation representation =
                             (StringRepresentation) this.communityLogoResource.delete();
        assertEquals(MediaType.TEXT_PLAIN, representation.getMediaType());
        assertEquals("java.lang.NullPointerException", representation.getText());
    }
}
