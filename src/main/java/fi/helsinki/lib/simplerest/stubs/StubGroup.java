/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
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
