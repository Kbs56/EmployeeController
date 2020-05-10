package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;

public class Helper {

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/employer_track?useLegacyDatetimeCode=false&serverTimezone=UTC";

        return DriverManager.getConnection(url, "root", "******");
    }

    public static ObservableList<Employee> getAllRecords() throws Exception {
        String sql = "select * from Employee";
        try {
            Connection conn = getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            ObservableList<Employee> empList = getEmployeeObjects(rs);
            return empList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Employee> getEmployeeObjects(ResultSet rs) throws SQLException {

       try {
           ObservableList<Employee> emplist = FXCollections.observableArrayList();

           while(rs.next()) {
               Employee emp = new Employee();
               emp.setId(rs.getString("id"));
               emp.setFname(rs.getString("fname"));
               emp.setLname(rs.getString("lname"));
               emp.setSiteId(rs.getString("siteid"));
               emp.setTestScore(rs.getInt("testScore"));
               emp.setTestPassed(rs.getInt("testPassed"));
               emplist.add(emp);
           }
           return emplist;
       } catch (Exception e) {
           e.printStackTrace();
           throw e;
       }
    }

    public String[] getNames() throws Exception {
        Connection conn = getConnection();

        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = stmt.executeQuery("SELECT fname, lname FROM Employee");

        resultSet.last();
        int numRows = resultSet.getRow();
        resultSet.first();

        String[] listData = new String[numRows];

        for (int index = 0; index < numRows; index++) {
            listData[index] = resultSet.getString(1) + " " + resultSet.getString(2);
            resultSet.next();
        }
        conn.close();
        stmt.close();
        return listData;
    }

    public String[] sortNames() throws Exception {
        Connection conn = getConnection();

        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = stmt.executeQuery("SELECT fname, lname FROM Employee");

        resultSet.last();
        int numRows = resultSet.getRow();
        resultSet.first();

        String[] listData = new String[numRows];

        for (int index = 0; index < numRows; index++) {
            listData[index] = resultSet.getString(1) + " " + resultSet.getString(2);
            resultSet.next();
        }

        Arrays.sort(listData);

        conn.close();
        stmt.close();
        return listData;
    }

    public String[] sortByLast() throws Exception {
        Connection conn = getConnection();

        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = stmt.executeQuery("SELECT fname, lname FROM Employee");

        resultSet.last();
        int numRows = resultSet.getRow();
        resultSet.first();

        String[] listData = new String[numRows];

        for (int index = 0; index < numRows; index++) {
            listData[index] = resultSet.getString(1) + " " + resultSet.getString(2);
            resultSet.next();
        }

        Arrays.sort(listData, Collections.reverseOrder());

        conn.close();
        stmt.close();
        return listData;
    }

    public String getID(String name) throws Exception {
        String id = "";

        // Create a connection to the database.
        Connection conn = getConnection();

        // Create a Statement object for the query.
        Statement stmt = conn.createStatement();

        // Execute the query.
        ResultSet resultSet = stmt.executeQuery(
                "SELECT id " +
                        "FROM Employee " +
                        "WHERE fname = '" +
                        name + "'");

        if (resultSet.next())
            id = resultSet.getString(1);

        // Close the Connection and Statement objects.
        conn.close();
        stmt.close();

        return id;
    }

    public String getFName(String name) throws Exception {
        String fname = "";

        // Create a connection to the database.
        Connection conn = getConnection();

        // Create a Statement object for the query.
        Statement stmt = conn.createStatement();

        // Execute the query.
        ResultSet resultSet = stmt.executeQuery(
                "SELECT fname " +
                        "FROM Employee " +
                        "WHERE fname = '" +
                        name + "'");

        if (resultSet.next())
            fname = resultSet.getString(1);

        // Close the Connection and Statement objects.
        conn.close();
        stmt.close();

        return fname;
    }

    public String getLName(String name) throws Exception {
        String lname = "";

        // Create a connection to the database.
        Connection conn = getConnection();

        // Create a Statement object for the query.
        Statement stmt = conn.createStatement();

        // Execute the query.
        ResultSet resultSet = stmt.executeQuery(
                "SELECT lname " +
                        "FROM Employee " +
                        "WHERE fname = '" +
                        name + "'");

        if (resultSet.next())
            lname = resultSet.getString(1);

        // Close the Connection and Statement objects.
        conn.close();
        stmt.close();

        return lname;
    }

    public String getSite(String name) throws Exception {
        String site = "";

        // Create a connection to the database.
        Connection conn = getConnection();

        // Create a Statement object for the query.
        Statement stmt = conn.createStatement();

        // Execute the query.
        ResultSet resultSet = stmt.executeQuery(
                "SELECT siteid " +
                        "FROM Employee " +
                        "WHERE fname = '" +
                        name + "'");

        if (resultSet.next())
            site = resultSet.getString(1);

        // Close the Connection and Statement objects.
        conn.close();
        stmt.close();

        return site;
    }

    public String getTest(String name) throws Exception {
        String test = "";

        // Create a connection to the database.
        Connection conn = getConnection();

        // Create a Statement object for the query.
        Statement stmt = conn.createStatement();

        // Execute the query.
        ResultSet resultSet = stmt.executeQuery(
                "SELECT testPassed " +
                        "FROM Employee " +
                        "WHERE fname = '" +
                        name + "'");

        if (resultSet.next())
            test = resultSet.getString(1);

        // Close the Connection and Statement objects.
        conn.close();
        stmt.close();

        return test;
    }

    public void setFirstName(int id, String newFname) throws Exception {
        Connection conn = getConnection();

        Statement stmt = conn.createStatement();

        stmt.executeUpdate("UPDATE Employee " +
                "SET fname = '" +
                newFname +
                "' WHERE id = '" +
                id + "'");

        conn.close();
        stmt.close();
    }

    public void setLastName(int id, String newLname) throws Exception {
        Connection conn = getConnection();

        Statement stmt = conn.createStatement();

        stmt.executeUpdate("UPDATE Employee " +
                "SET lname = '" +
                newLname +
                "' WHERE id = '" +
                id + "'");

        conn.close();
        stmt.close();
    }

    public void setSiteId(int id, String newSite) throws Exception {
        Connection conn = getConnection();

        Statement stmt = conn.createStatement();

        stmt.executeUpdate("UPDATE Employee " +
                "SET siteid = '" +
                newSite +
                "' WHERE id = '" +
                id + "'");

        conn.close();
        stmt.close();
    }

    public void setTestPassed(int id, int testPassed) throws Exception {
        Connection conn = getConnection();

        Statement stmt = conn.createStatement();

        stmt.executeUpdate("UPDATE Employee " +
                "SET testPassed = '" +
                testPassed +
                "' WHERE id = '" +
                id + "'");

        conn.close();
        stmt.close();
    }

    public void setTestScore(int id, int testScore) throws Exception {
        Connection conn = getConnection();

        Statement stmt = conn.createStatement();

        stmt.executeUpdate("UPDATE Employee " +
                "SET testScore = '" +
                testScore +
                "' WHERE id = '" +
                id + "'");

        conn.close();
        stmt.close();
    }


    public void addEntry(int id, String fname, String lname, String siteid, String testPassed) throws Exception {
        Connection conn = getConnection();
        int testScore = 0;

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO Employee VALUES ('" +
                id + "', '" +
                fname + "', '" +
                lname + "', '" +
                siteid + "', '" +
                testScore + "', '" +
                testPassed + "')");

        conn.close();
        stmt.close();
    }

    public void deleteEntry(String name) throws Exception
    {
        // Create a connection to the database.
        Connection conn = getConnection();

        // Create a Statement object for the query.
        Statement stmt = conn.createStatement();

        // Execute the query.
        stmt.executeUpdate("DELETE FROM Employee WHERE fname = '" +
                name + "'");

        // Close the connection and statement objects.
        conn.close();
        stmt.close();
    }
}

