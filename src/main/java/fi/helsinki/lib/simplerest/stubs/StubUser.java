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

/**
 *
 * @author moubarik
 */
public class StubUser implements Serializable{
    private int id;
    private String email;
    private String language;
    private String netid;
    private String fullname;
    private String firstname;
    private String lastname;
    private boolean can_login;
    private boolean require_certificate;
    private boolean self_registered;

    public StubUser(int id, String email, String language, String netid, String fullname, String firstname, String lastname, boolean can_login, boolean require_certificate, boolean self_registered) {
        this.id = id;
        this.email = email;
        this.language = language;
        this.netid = netid;
        this.fullname = fullname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.can_login = can_login;
        this.require_certificate = require_certificate;
        this.self_registered = self_registered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNetid() {
        return netid;
    }

    public void setNetid(String netid) {
        this.netid = netid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isCan_login() {
        return can_login;
    }

    public void setCan_login(boolean can_login) {
        this.can_login = can_login;
    }

    public boolean isRequire_certificate() {
        return require_certificate;
    }

    public void setRequire_certificate(boolean require_certificate) {
        this.require_certificate = require_certificate;
    }

    public boolean isSelf_registered() {
        return self_registered;
    }

    public void setSelf_registered(boolean self_registered) {
        this.self_registered = self_registered;
    }
}
