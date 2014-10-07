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

/**
 *
 * @author moubarik
 */
public class StubBitstream implements Serializable{
    private int id;
    private String name;
    private String mimetype;
    private String description;
    private String userformatdescription;
    private int sequenceid;
    private Long sizebytes;

    public StubBitstream(int id, String name, String mimetype, String description, String userformatdescription, int sequenceid, Long sizebytes) {
        this.id = id;
        this.name = name;
        this.mimetype = mimetype;
        this.description = description;
        this.userformatdescription = userformatdescription;
        this.sequenceid = sequenceid;
        this.sizebytes = sizebytes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserformatdescription() {
        return userformatdescription;
    }

    public void setUserformatdescription(String userformatdescription) {
        this.userformatdescription = userformatdescription;
    }

    public int getSequenceid() {
        return sequenceid;
    }

    public void setSequenceid(int sequenceid) {
        this.sequenceid = sequenceid;
    }

    public Long getSizebytes() {
        return sizebytes;
    }

    public void setSizebytes(Long sizebytes) {
        this.sizebytes = sizebytes;
    }
   
}
