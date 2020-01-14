package com.silverbeetle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        /* dependency in the POM.XML for core, and you will see the jar added mysql-connector-java-8.0.19.jar :
                <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.19</version>
        </dependency>
        */

        // set the Change up from front end, db or batch feed or file
        java.sql.Date currentDate = new Date(System.currentTimeMillis());

        //TAKE BELOW LIST FROM THE FRONT END OR DATA FEED /FILE
        Person person = new Person ("Abdullah","Dawood");
        Approver approver1 = new Approver(person);
        Approver approver2 = new Approver(new Person ("John","Smith"));
        Approver approver3 = new Approver(new Person ("Jilly","Dunbar"));
        Approver approver4 = new Approver(new Person ("Metal","Malleable"));

        Change newChange = new Change("Migrate db","BUSINESS","APPROVED",currentDate,"This is a demo Change being inserted by Abdullah Dawood");
        newChange.addApprovers(approver1);
        newChange.addApprovers(approver2);
        newChange.addApprovers(approver3);
        newChange.addApprovers(approver4);

        newChange.addRowToChangeTable();

        log.info(newChange.toString());
        //System.out.println(newChange.toString());

    }
}
