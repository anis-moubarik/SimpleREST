/**
 * A RESTful web service on top of DSpace.
 * Copyright (C) 2010-2013 National Library of Finland
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


package fi.helsinki.lib.simplerest.TestServlets;

import fi.helsinki.lib.simplerest.GroupResource;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import org.dspace.eperson.Group;
import static org.mockito.Mockito.*;

/**
 *
 * @author moubarik
 */
public class GroupServlet extends HttpServlet{
    
    private Group mockedGroup;
    private GroupResource gr;
    private static Logger log = Logger.getLogger(GroupServlet.class);
    
    @Override
    public void init(ServletConfig config){
        mockedGroup = mock(Group.class);
    }
}