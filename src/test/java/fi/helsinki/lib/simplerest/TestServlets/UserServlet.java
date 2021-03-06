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

import fi.helsinki.lib.simplerest.UserResource;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dspace.eperson.EPerson;
import static org.mockito.Mockito.*;

/**
 *
 * @author moubarik
 */
public class UserServlet extends HttpServlet{
    
    private EPerson mockedEperson;
    private UserResource ur;
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        mockedEperson = mock(EPerson.class);
        when(mockedEperson.getID()).thenReturn(1);
        when(mockedEperson.getEmail()).thenReturn("test@test.com");
        when(mockedEperson.getLanguage()).thenReturn("fi");
        when(mockedEperson.getNetid()).thenReturn("1");
        when(mockedEperson.getFullName()).thenReturn("testi testaaja");
        when(mockedEperson.getFirstName()).thenReturn("testi");
        when(mockedEperson.getLastName()).thenReturn("testaaja");
        when(mockedEperson.canLogIn()).thenReturn(true);
        when(mockedEperson.getRequireCertificate()).thenReturn(false);
        when(mockedEperson.getSelfRegistered()).thenReturn(true);
        when(mockedEperson.getMetadata("password")).thenReturn("password1");
        
        ur = new UserResource(mockedEperson, mockedEperson.getID());
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        if(req.getPathInfo().equals("/xml")){
            xmlTest(resp);
        }else if(req.getPathInfo().equals("/json")){
            jsonTest(resp);
        }
    }
    
    public void xmlTest(HttpServletResponse resp) throws IOException{
        PrintWriter out = resp.getWriter();  
        
        out.write(ur.toXml().getText());
    }

    private void jsonTest(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.write(ur.toJson());
    }
}
