package fi.helsinki.lib.simplerest.TestServlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.dspace.content.Community;
import static org.mockito.Mockito.*;

/**
 * The test class makes a GET request to here and we'll return mocked
 * Community class.
 * @author moubarik
 */
public class CommunityResourceServlet implements Servlet{
    
    private Community c;

    @Override
    public void init(ServletConfig sc) throws ServletException {
        c = mock(Community.class);
        when(c.getName()).thenReturn("Testi");
        when(c.getID()).thenReturn(1);
    }

    @Override
    public ServletConfig getServletConfig() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void service(ServletRequest sr, ServletResponse sr1) throws ServletException, IOException {
        sr1.getWriter().append(Integer.toString(c.getID()) + " " + c.getName());
    }

    @Override
    public String getServletInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}