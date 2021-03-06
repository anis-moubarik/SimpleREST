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


package fi.helsinki.lib.simplerest.stubs;

import java.io.Serializable;
import org.dspace.eperson.EPerson;
import org.dspace.eperson.Group;

/**
 *
 * @author moubarik
 */
public class StubGroup implements Serializable{
    private Group groups[];
    private EPerson epersons[];
    private int Id;
    private String name;
   
    public StubGroup(Group g){
        this.Id = g.getID();
        this.groups = g.getMemberGroups();
        this.epersons = g.getMembers();
        this.name = g.getName();
    }

    public Group[] getGroups() {
        return groups;
    }

    public void setGroups(Group[] groups) {
        this.groups = groups;
    }

    public EPerson[] getEpersons() {
        return epersons;
    }

    public void setEpersons(EPerson[] epersons) {
        this.epersons = epersons;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
