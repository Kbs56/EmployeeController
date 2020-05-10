package sample;

import javafx.beans.property.*;

public class Employee {

    private IntegerProperty id;
    private StringProperty fname;
    private StringProperty lname;
    private IntegerProperty siteId;
    private IntegerProperty testScore;
    private IntegerProperty testPassed;

    public Employee() {
        this.id = new SimpleIntegerProperty();
        this.fname = new SimpleStringProperty();
        this.lname = new SimpleStringProperty();
        this.siteId = new SimpleIntegerProperty();
        this.testScore = new SimpleIntegerProperty();
        this.testPassed = new SimpleIntegerProperty();
    }


    public int getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(Integer.parseInt(id));
    }

    public String getFname() {
        return fname.get();
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public String getLname() {
        return lname.get();
    }

    public void setLname(String lname) {
        this.lname.set(lname);
    }

    public int getSiteId() {
        return siteId.get();
    }

    public void setSiteId(String siteId) {
        this.siteId.set(Integer.parseInt(siteId));
    }

    public int getTestScore() {
        return testScore.get();
    }

    public void setTestScore(int testScore) {
        this.testScore.set(testScore);
    }

    public String getTestPassed() {
        if (testPassed.get() == 0) {
            return "No";
        } else {
            return "Yes";
        }
    }

    public void setTestPassed(int testPassed) {
        this.testPassed.set(testPassed);
    }
}
