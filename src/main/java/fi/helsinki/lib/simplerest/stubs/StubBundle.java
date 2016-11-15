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
import org.dspace.content.Bundle;
import org.dspace.core.ConfigurationManager;

/**
 *
 * @author moubarik
 */
public class StubBundle implements Serializable{
    private int id;
    private String bundleName;
    private int primarybitstreamid;
    private String[] bitstreamUrls;

    public StubBundle(int id, String bundleName, int primarybitstreamid, Bitstream[] bitstreams) {
        this.id = id;
        this.bundleName = bundleName;
        this.primarybitstreamid = primarybitstreamid;
        bitstreamUrls = new String[bitstreams.length];
        for(int i = 0; i < bitstreams.length; i++){
            bitstreamUrls[i] = "siimplerest/bitstream/" + bitstreams[i].getID()+"?media=json";
        }
    }

    public StubBundle(Bundle b){
        this.id = b.getID();
        this.bundleName = b.getName();
        this.primarybitstreamid = b.getPrimaryBitstreamID();
        Bitstream[] bitstreams = b.getBitstreams();
        bitstreamUrls = new String[bitstreams.length];
        for(int i = 0; i < bitstreams.length; i++){
            bitstreamUrls[i] = ConfigurationManager.getProperty("dspace.url")+"/simplerest/bitstream/" + bitstreams[i].getID()+"?media=json";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public int getPrimarybitstreamid() {
        return primarybitstreamid;
    }

    public void setPrimarybitstreamid(int primarybitstreamid) {
        this.primarybitstreamid = primarybitstreamid;
    }

    public String[] getBitstreamUrls() {
        return bitstreamUrls;
    }

    public void setBitstreamUrls(String[] bitstreamUrls) {
        this.bitstreamUrls = bitstreamUrls;
    }
    
}
