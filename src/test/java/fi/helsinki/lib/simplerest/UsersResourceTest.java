package fi.helsinki.lib.simplerest;

import com.google.gson.Gson;
import fi.helsinki.lib.simplerest.TestServlets.UsersResourceServlet;
import fi.helsinki.lib.simplerest.stubs.StubUser;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.*;

import static org.junit.Assert.*;

public class UsersResourceTest {

    private ServletTester tester;

    @Before
    public void setUp() throws Exception {
        tester = new ServletTester();
        tester.setContextPath("/");
        tester.addServlet(UsersResourceServlet.class, "/users/*");
        tester.start();
    }

    @Test
    public void testGetXml() throws Exception {
        HttpTester req = new HttpTester();
        HttpTester res = new HttpTester();

        req.setMethod("GET");
        req.setHeader("HOST", "tester");
        req.setURI("/users/xml");
        res.parse(tester.getResponses(req.generate()));
        System.out.println(res.getContent());
        assertEquals(200, res.getStatus());

        String[] attributes = {"/user/12341", "/user/12342"};
        for (String attribute : attributes) {
            assertEquals(res.getContent().contains(attribute), true);
        }

        String[] values = {"testi testaaja1", "testi testaaja2"};

        for (String value : values) {
            assertEquals(res.getContent().contains(value), true);
        }
    }

    @Test
    public void testGetJson() throws Exception {
        HttpTester req = new HttpTester();
        HttpTester res = new HttpTester();

        req.setMethod("GET");
        req.setHeader("HOST", "tester");
        req.setURI("/users/json");
        res.parse(tester.getResponses(req.generate()));
        System.out.println(res.getContent());


        Gson gson = new Gson();
        StubUser[] su = gson.fromJson(res.getContent(), StubUser[].class);
        System.out.println(su.length);

        assertEquals(200, res.getStatus());
        assertEquals(su[0].getId(), 12341);
        assertEquals(su[0].getEmail(), "test1@test.com");
        assertEquals(su[0].getLanguage(), "fi");
        assertEquals(su[0].getNetid(), "1");
        assertEquals(su[0].getFullname(), "testi testaaja1");
        assertEquals(su[0].getFirstname(), "testi1");
        assertEquals(su[0].getLastname(), "testaaja1");
        assertEquals(su[0].isCan_login(), true);
        assertEquals(su[0].isRequire_certificate(), false);
        assertEquals(su[0].isSelf_registered(), true);
        
        assertEquals(su[1].getId(), 12342);
        assertEquals(su[1].getEmail(), "test2@test.com");
        assertEquals(su[1].getLanguage(), "fi");
        assertEquals(su[1].getNetid(), "2");
        assertEquals(su[1].getFullname(), "testi testaaja2");
        assertEquals(su[1].getFirstname(), "testi2");
        assertEquals(su[1].getLastname(), "testaaja2");
        assertEquals(su[1].isCan_login(), true);
        assertEquals(su[1].isRequire_certificate(), false);
        assertEquals(su[1].isSelf_registered(), true);
        
    }
}