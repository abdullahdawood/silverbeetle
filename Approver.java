package com.silverbeetle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Approver {
    private String ID, firstName, lastName;  // just some search parameters to look for the 'Person'
    protected Person person;

    Approver(Person person) {

        this.person = new Person(person.getFirstName(), person.getLastName());
        this.ID = UUID.randomUUID().toString(); // when a Person becomes an 'Approver' they get a unique ID as an Approver.
        //need to create a new Person if the Person doesn't exist in the Person table, so search for it and add it here, or create a new Person
    }

    public Person getPerson(){
        return this.person;
    }
}
