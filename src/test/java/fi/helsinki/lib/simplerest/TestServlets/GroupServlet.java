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


package fi.helsinki.lib.simplerest.TestServlets;

import fi.helsinki.lib.simplerest.GroupResource;
import fi.helsinki.lib.simplerest.UserResource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.dspace.eperson.EPerson;
import org.dspace.eperson.Group;
import static org.mockito.Mockito.*;

/**
 *
 * @author moubarik
 */
public class GroupServlet extends HttpServlet{
    
    private EPerson mockedEperson;
    
    private Group mockedGroup;
    private GroupResource gr;
    private static Logger log = Logger.getLogger(GroupServlet.class);
    
    @Override
    public void init(ServletConfig config){
        
        mockedEperson = mock(EPerson.class);
        EPerson[] Epersons = new EPerson[2];
        when(mockedEperson.getID()).thenReturn(11);
        when(mockedEperson.getEmail()).thenReturn("test@test.com");
        when(mockedEperson.getLanguage()).thenReturn("fi");
        when(mockedEperson.getNetid()).thenReturn("11");
        when(mockedEperson.getFullName()).thenReturn("testi testaaja");
        when(mockedEperson.getFirstName()).thenReturn("testi");
        when(mockedEperson.getLastName()).thenReturn("testaaja");
        when(mockedEperson.canLogIn()).thenReturn(true);
        when(mockedEperson.getRequireCertificate()).thenReturn(false);
        when(mockedEperson.getSelfRegistered()).thenReturn(true);
        when(mockedEperson.getMetadata("password")).thenReturn("password1");
        Epersons[0] = mockedEperson;
        Epersons[1] = mockedEperson;
        Group[] groups = new Group[1];
        mockedGroup = mock(Group.class);
        groups[0] = mockedGroup;
        when(mockedGroup.getID()).thenReturn(11);
        when(mockedGroup.getName()).thenReturn("test");
        when(mockedGroup.getType()).thenReturn(1);
        when(mockedGroup.getMembers()).thenReturn(Epersons);
        when(mockedGroup.getMemberGroups()).thenReturn(groups);
        
        gr = new GroupResource(mockedGroup, mockedGroup.getID());
    }
    
     @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        if(req.getPathInfo().equals("/group/xml")){
            xmlTest(resp);
        }else if(req.getPathInfo().equals("/json")){
            jsonTest(resp);
        }
    }

    private void xmlTest(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        try {
            out.write(gr.toXml().getText());
        } catch (Exception ex) {
            //log.log(Level.INFO, null, ex);
        } 
    }

    private void jsonTest(HttpServletResponse resp) throws IOException {
        
        PrintWriter out = resp.getWriter();
        try{
            out.write(gr.toJson());
        }catch(Exception ex) {
            //log.log(Level.INFO, null, ex);
        }
    }
}