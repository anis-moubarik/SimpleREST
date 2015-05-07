package fi.helsinki.lib.simplerest.TestServlets;

import fi.helsinki.lib.simplerest.UserResource;
import fi.helsinki.lib.simplerest.UsersResource;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dspace.eperson.EPerson;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class UsersResourceServlet extends HttpServlet {

    private EPerson mockedEperson, mockedEperson2;
    @Mock private EPerson[] epersons;
    private UsersResource urs; // = spy(new UsersResource(epersons));
    private UserResource ur;

    @Override
    public void init(ServletConfig config) throws ServletException {
        mockedEperson = mock(EPerson.class);
        mockedEperson2 = mock(EPerson.class);
        when(mockedEperson.getID()).thenReturn(12341);
        when(mockedEperson.getEmail()).thenReturn("test1@test.com");
        when(mockedEperson.getLanguage()).thenReturn("fi");
        when(mockedEperson.getNetid()).thenReturn("1");
        when(mockedEperson.getFullName()).thenReturn("testi testaaja1");
        when(mockedEperson.getFirstName()).thenReturn("testi1");
        when(mockedEperson.getLastName()).thenReturn("testaaja1");
        when(mockedEperson.canLogIn()).thenReturn(true);
        when(mockedEperson.getRequireCertificate()).thenReturn(false);
        when(mockedEperson.getSelfRegistered()).thenReturn(true);
        when(mockedEperson.getMetadata("password")).thenReturn("password1");

        when(mockedEperson2.getID()).thenReturn(12342);
        when(mockedEperson2.getEmail()).thenReturn("test2@test.com");
        when(mockedEperson2.getLanguage()).thenReturn("fi");
        when(mockedEperson2.getNetid()).thenReturn("2");
        when(mockedEperson2.getFullName()).thenReturn("testi testaaja2");
        when(mockedEperson2.getFirstName()).thenReturn("testi2");
        when(mockedEperson2.getLastName()).thenReturn("testaaja2");
        when(mockedEperson2.canLogIn()).thenReturn(true);
        when(mockedEperson2.getRequireCertificate()).thenReturn(false);
        when(mockedEperson2.getSelfRegistered()).thenReturn(true);
        when(mockedEperson2.getMetadata("password")).thenReturn("password2");

        epersons = new EPerson[2];
        epersons[0] = mockedEperson;
        epersons[1] = mockedEperson2;

        urs = new UsersResource(epersons);

        /*
        doReturn(epersons)
                .when(urs)
                .makeUsers(any(EPerson[].class));
        */
    }

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().equals("/xml")) {
            xmlTest(resp);
        }else if(req.getPathInfo().equals("/json")){
            jsonTest(resp);
        }
    }

    public void xmlTest(HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        out.write(urs.toXml().getText());
    }
    
    public void jsonTest(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.write(urs.toJson());
    }
}