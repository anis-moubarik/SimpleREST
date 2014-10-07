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
import org.dspace.content.Bitstream;
import org.dspace.content.Collection;
import org.dspace.content.DCValue;

/**
 *
 * @author moubarik
 */
public class StubCollection implements Serializable{
    private int id;
    private String collectionName;
    private String short_description;
    private String introductory_text;
    private String provenance_description;
    private String license;
    private String copyright_text;
    private String side_bar_text;
    private Bitstream logo;

    public StubCollection(int id, String collectionName, String short_description, String introductory_text,
            String provenance_description, String license, String copyright_text, String side_bar_text, 
            Bitstream logo) {
        this.id = id;
        this.collectionName = collectionName;
        this.short_description = short_description;
        this.introductory_text = introductory_text;
        this.provenance_description = provenance_description;
        this.license = license;
        this.copyright_text = copyright_text;
        this.side_bar_text = side_bar_text;
        this.logo = logo;
    }
    
    public StubCollection(Collection c){
         this.id = c.getID();
         this.collectionName = c.getName();
         this.short_description = c.getMetadata("short_description");
         this.introductory_text = c.getMetadata("introductory_text");
         this.provenance_description = c.getMetadata("provenance_description");
         this.license = c.getLicense();
         this.copyright_text = c.getMetadata("copyright_text");
         this.side_bar_text = c.getMetadata("side_bar_text");
         this.logo = c.getLogo();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getIntroductory_text() {
        return introductory_text;
    }

    public void setIntroductory_text(String introductory_text) {
        this.introductory_text = introductory_text;
    }

    public String getProvenance_description() {
        return provenance_description;
    }

    public void setProvenance_description(String provenance_description) {
        this.provenance_description = provenance_description;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCopyright_text() {
        return copyright_text;
    }

    public void setCopyright_text(String copyright_text) {
        this.copyright_text = copyright_text;
    }

    public String getSide_bar_text() {
        return side_bar_text;
    }

    public void setSide_bar_text(String side_bar_text) {
        this.side_bar_text = side_bar_text;
    }
}
