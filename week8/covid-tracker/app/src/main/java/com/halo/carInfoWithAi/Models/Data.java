package com.halo.carInfoWithAi.Models;

public class Data {


   private String cname;
   private String age;
   private String date;
    private String contact;
    private String address;
    private String profession;
    private String highContact;
    private String lowContact;
    private String familyMember;
    private String id;

    public Data() {
    }

    public Data( String cname, String age, String date,String contact, String address, String profession, String highContact,String lowContact, String familyMember,String id) {
        this.cname = cname;
        this.age = age;
        this.date = date;
        this.contact = contact;
        this.address = address;
        this.profession = profession;
        this.highContact = highContact;
        this.lowContact = lowContact;
        this.familyMember = familyMember;
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getHighContact() {
        return highContact;
    }
    public void setHighContact(String highContact) {
        this.highContact = highContact;
    }

    public String getFamilyMember() {
        return familyMember;
    }
    public void setFamilyMember(String familyMember) {
        this.familyMember = familyMember;
    }

    public String getLowContact() {
        return lowContact;
    }
    public void setLowContact(String lowContact) {
        this.lowContact = lowContact;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
