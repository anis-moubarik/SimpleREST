/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.lib.simplerest.TestServlets;

import fi.helsinki.lib.simplerest.GroupsResource;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dspace.eperson.Group;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class GroupsResourceServlet extends HttpServlet {

    private Group mockedGroup;
    @Mock private Group[] groups;
    private GroupsResource grps;

    @Override
    public void init(ServletConfig config) throws ServletException {
        mockedGroup = mock(Group.class);
        when(mockedGroup.getID()).thenReturn(1);
        when(mockedGroup.getName()).thenReturn("testGroup");

        groups = new Group[1];
        groups[0] = mockedGroup;
        grps = new GroupsResource(groups);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().equals("/xml")) {
            xmlTest(resp);
        }
    }

    public void xmlTest(HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        out.write(grps.toXml().getText());
    }
}
