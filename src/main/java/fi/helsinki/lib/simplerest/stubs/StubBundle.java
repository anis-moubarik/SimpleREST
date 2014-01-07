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
import org.dspace.content.Bitstream;

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
            bitstreamUrls[i] = "bitstreams/" + bitstreams[i].getID();
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
