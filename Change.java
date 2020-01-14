package com.silverbeetle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Change {
    protected String ID;
    protected String name;
    Date dateofChange;
    protected List <String> notes = new ArrayList<>(); //from a notes table dedicated to the Change, primary key is the ID of the Change
    protected List <Approver> approvers = new ArrayList<>();
    protected String changeDescription = "a new change";
    protected ChangeType eType;
    protected ChangePhase ePhase;
    Change (){};
    Change (String name, String sChangeType, String phase, Date dateofChange, String changeDescription){
        this.name = name;
        this.changeDescription = changeDescription;
        this.dateofChange = dateofChange;
        this.ID = UUID.randomUUID().toString();
        this.setChangeType(sChangeType);
        this.setChangePhase(phase);
        this.notes.add("Notes");
    }


    public void addApprovers(Approver approver){
        this.approvers.add(approver);
    }
    //will need a search capability for notes
    public void addNote (String note){ // from front end user input
        notes.add(note);
    }
    public void printAllNotes (){ // to the front end or to file
        for(int i=0; i<notes.size();i++){
            System.out.println(notes.get(i));
        }
    }

    public String createChangeTable(){
        String createTable = "CREATE TABLE CHANGE (ID INTEGER PRIMARY KEY, DATE TEXT NOT NULL, DESCRIPTION TEXT, CHANGETYPE TEXT NOT NULL, CHANGEPHASE TEXT NOT NULL); ";
        return createTable;
    }

    public String findChange(String changeID){
        String findChangeInTable = "SELECT * FROM CHANGE WHERE ID = " + changeID + ";";
        return findChangeInTable;
    }

    public void addRowToChangeTable(){
        String url = "jdbc:mysql://localhost:3306/silverbeetle?user=root&password=abdullah";

        System.out.println("Loading driver...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
        System.out.println("Connecting database...");
        try (Connection connection = DriverManager.getConnection(url)) {
            System.out.println("Database connected!");
            // create a Statement from the connection
            Statement statement = connection.createStatement();

            System.out.println((this.dateofChange.toString()));
// insert the data

            //name the collums to be changed, removing the auto increment ID i.e. the first column.
            String insertStatement = "INSERT INTO 'change' (description,type,phase,id,date,notes,name) " +
                    "VALUES ('change description text', 'BUSINESS', 'PROPOSED', '"+ this.ID +
                    "','" + this.dateofChange.toString() + "', '"+ this.notes.get(0) + "', '" + this.name + "')";
            System.out.println(insertStatement);
            statement.executeUpdate(insertStatement);
            connection.close();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    @Override
    public String toString() {
        String ChangeObject = this.dateofChange.toString() + "\nID: " + this.ID + " \nChange Type: "+ this.eType + " \nChange Phase: " + this.ePhase + "\nAppprovers: \n";
        for(Approver approver : approvers){
            ChangeObject+= approver.getPerson().toString();
        }
        return ChangeObject;
    }

    public void setChangePhase(String phase){
        ePhase = ChangePhase.valueOf(phase);
    }
    public void setChangeType(String changeType){
        eType = ChangeType.valueOf(changeType);
    }


    public enum ChangeType {
        BUSINESS,
        INFRASTRUCTURE,
        RELEASE,
        SUPPORT;
        protected String getChangeType (){
            String strChange = "" + this;
            return strChange;
        }
    }

    public enum ChangePhase{
        PROPOSED,
        SUBMITTED,
        APPROVAL,
        APPROVED,
        REJECTED,
        PLANNED,
        IMPLEMENT,
        SUCCEEDED,
        FAILED,
        OWNER;
             protected String getChangePhase (){
                String strPhase = "" + this;
                return strPhase;
            }
        }

    private List ChangeDependencies = new ArrayList();

    //new table of dependencies
    public void addDependency (Change change){
        ChangeDependencies.add(change);
    }
    //remove from table of dependencies
    public void removeDependency (Change change){
        ChangeDependencies.remove(change);
    }

}
