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
import java.sql.SQLException;
import java.util.Date;

import org.dspace.content.Bundle;
import org.dspace.content.DCValue;
import org.dspace.content.Item;

/**
 *
 * @author moubarik
 */
public class StubItem implements Serializable{
    private int owningCollectionId;
    private String[] collections;
    private StubBundle[] bundles;
    private boolean in_archive;
    private boolean withdrawn;
    private DCValue[] metadata;
    private Date lastModified;
    private int Id;
    
    public StubItem(Item i) throws SQLException{
        this.Id = i.getID();
        if(i.getBundles() != null){
            this.bundles = new StubBundle[i.getBundles().length];
            for(int j = 0; j < this.bundles.length; j++){
                this.bundles[j] = new StubBundle(i.getBundles()[j]);
            }
        }
        if(i.getCollections() != null){
            this.collections = new String[i.getCollections().length];
            for(int j = 0; j < this.collections.length; j++){
                this.collections[j] = i.getCollections()[j].getName();
            }
        }
        if(i.getOwningCollection() != null){
            this.owningCollectionId = i.getOwningCollection().getID();
        }
        this.in_archive = i.isArchived();
        this.withdrawn = i.isWithdrawn();
        this.metadata = i.getMetadata(Item.ANY, Item.ANY, Item.ANY, Item.ANY);
        this.lastModified = i.getLastModified();
    }
    
    public int getOwningCollectionID(){
        return owningCollectionId;
    }
    
    public String[] getCollections(){
        return collections;
    }
    
    public StubBundle[] getBundles(){
        return bundles;
    }
    
    public boolean in_archive(){
        return in_archive;
    }
    
    public boolean withdrawn(){
        return withdrawn;
    }

    public int getOwningCollectionId() {
        return owningCollectionId;
    }

    public void setOwningCollectionId(int owningCollectionId) {
        this.owningCollectionId = owningCollectionId;
    }

    public Date getLastModified() { return lastModified; }

    public boolean isIn_archive() {
        return in_archive;
    }

    public void setIn_archive(boolean in_archive) {
        this.in_archive = in_archive;
    }

    public boolean isWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(boolean withdrawn) {
        this.withdrawn = withdrawn;
    }

    public DCValue[] getMetadata() {
        return metadata;
    }

    public void setMetadata(DCValue[] metadata) {
        this.metadata = metadata;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
}
