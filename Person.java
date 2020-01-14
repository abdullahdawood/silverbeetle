package com.silverbeetle;

import java.util.UUID;

public class Person {
    private String ID;
    private Org organisation;
    private Department department;
    private Team team;
    private Project project;
    private Programme programme;
    private Veto vetoStatus;

    private String firstName;
    private String middleName="";
    private String lastName;
    private String workPhone="";
    private String mobilePhone="";
    private String email = "";
    private String manager = "";

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    //add for the first time from front end or file... then add all fields to the Person table in the db
    public Person(Org organisation, Department department, Team team, Project project, Programme programme, Veto vetoStatus, String firstName, String middleName, String lastName, String workPhone, String mobilePhone, String email, String manager) {
        this.ID = UUID.randomUUID().toString();
        this.organisation = organisation;
        this.department = department;
        this.team = team;
        this.project = project;
        this.programme = programme;
        this.vetoStatus = vetoStatus;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.workPhone = workPhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.manager = manager;
    }

    //create from a search from the db
    public Person(String firstName, String lastName) {
        //find the row from the table in the database and create the Person object
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        String s =
                "ID: "+ this.ID + "\n" +
                "ORG: " + this.organisation +"\n" +
                "DEPARTMENT:"+ this.department + "\n" +
                "TEAM:"+ this.team + "\n" +
                "PROJECT:"+this.project +"\n" +
                "PROGRAMME:"+ this.programme +"\n" +
                "VETO STATUS:"+this.vetoStatus +"\n" +
                "FIRST NAME:"+ this.firstName +"\n" +
                "MIDDLE NAME:"+ this.middleName +"\n" +
                "LAST NAME:"+ this.lastName +"\n" +
                "WORK PHONE:"+ this.workPhone +"\n" +
                "MOBILE PHONE:"+ this.mobilePhone +"\n" +
                "EMAIL:"+this.email +"\n" +
                "MANAGER:"+ this.manager + "\n";
    return s;
    }
}
