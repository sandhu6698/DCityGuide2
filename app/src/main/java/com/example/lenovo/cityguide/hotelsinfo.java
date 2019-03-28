package com.example.lenovo.cityguide;

public class hotelsinfo {
    public String name,address,contact,website,url;
    private static final String NO_INFO_PROVIDED = "1";
    private String info = NO_INFO_PROVIDED;

    public hotelsinfo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public hotelsinfo(String name, String address, String contact, String website, String info, String url) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.website = website;
        this.info = info;
        this.url = url;
    }

    public hotelsinfo(String name, String address, String contact, String website, String url) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.website = website;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }



    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean hasinfo(){
        return info != NO_INFO_PROVIDED;
    }
}
