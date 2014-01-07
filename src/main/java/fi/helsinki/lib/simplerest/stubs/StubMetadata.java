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


package fi.helsinki.lib.simplerest.stubs;

import java.io.Serializable;
import org.dspace.content.MetadataSchema;

/**
 *
 * @author moubarik
 */
public class StubMetadata implements Serializable{
    private int id;
    private String schema;
    private String element;
    private String qualifier;
    private String scopeNote;

    public StubMetadata(int id, String schema, String element, String qualifier, String scopeNote) {
        this.id = id;
        this.schema = schema;
        this.element = element;
        this.qualifier = qualifier;
        this.scopeNote = scopeNote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getScopeNote() {
        return scopeNote;
    }

    public void setScopeNote(String scopeNote) {
        this.scopeNote = scopeNote;
    }
    
}
