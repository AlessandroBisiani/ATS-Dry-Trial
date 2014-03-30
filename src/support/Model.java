/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.support;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author samatkuandykov
 */
public class Model {
    private int lastAddressID;
    private int lastDiscountPlanID;
    
    private ArrayList<ChangeListener> listeners;
    
    public ArrayList<Customers> getCustomers() throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Customers> list = new ArrayList();
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            stmt = con.prepareStatement("SELECT * FROM Customer");
            rs = stmt.executeQuery();
            while(rs.next()){
                Customers c = new Customers();
                c.setCustomerID(rs.getInt("customerID"));
                c.setFName(rs.getString("firstName"));
                c.setLName(rs.getString("lastName"));
                c.setTitle(rs.getString("title"));
                c.setType(rs.getString("type"));
                c.setAddressID(rs.getInt("addressID"));
                c.setDiscountPlanID(rs.getInt("discountPlanID"));
                list.add(c);
            }
        }finally {
            if(rs != null) try {rs.close();}catch (SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
            if(con != null) try {con.close();}catch(SQLException se){}
        }
        
        return list;
    }
    
    public void addCustomer(String fname, String lname, String title, String type, int addressID, int discountPlanID) throws SQLException{
        String qry = "INSERT INTO Customer VALUES (null, '"+fname+"', '" + lname+"', '" + title+"', '" + type+"', " + addressID+", " + discountPlanID +")";
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            stmt = con.prepareStatement(qry);
            stmt.executeUpdate();
        }finally{
            if(con != null) try {con.close();}catch(SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
        }
        notifyListeners();
    }
    
    public void addAddress(String aline1, String aline2, String county, String city, String country, String pcode) throws SQLException{
        String qry = "INSERT INTO Address VALUES (null, '" + aline1+"', '" + aline2+"', '" + county+"', '" + city+"', '" + country+"', '" + pcode +"')";
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            stmt = con.prepareStatement(qry, new String[] {"addressID"});
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (null != generatedKeys && generatedKeys.next()) {
                lastAddressID = generatedKeys.getInt(1);
            }
        }finally{
            if(con != null) try {con.close();}catch(SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
        }
        notifyListeners();
    }
    
    public int getLastAddressID(){
        return lastAddressID;
    }
    
    public void addDiscountPlan(String percentage, String discountType, String howAwarded, int payLaterOption) throws SQLException{
        String qry = "INSERT INTO DiscountPlan(percentage, discountType, howAwarded, payLaterOption) VALUES (" + percentage+", '" + discountType+"', '" + howAwarded+"', " + payLaterOption +")";
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            stmt = con.prepareStatement(qry, new String[] {"discountPlanID"});
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (null != generatedKeys && generatedKeys.next()) {
                lastDiscountPlanID = generatedKeys.getInt(1);
            }
        }finally{
            if(con != null) try {con.close();}catch(SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
        }
        notifyListeners();
    }
    
    public void findAddress(String addressID) throws SQLException{
        String qry = "SELECT * FROM Address WHERE addressID = " + addressID +")";
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            stmt = con.prepareStatement(qry, new String[] {"discountPlanID"});
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (null != generatedKeys && generatedKeys.next()) {
                lastDiscountPlanID = generatedKeys.getInt(1);
                System.out.println(lastDiscountPlanID);
            }
        }finally{
            if(con != null) try {con.close();}catch(SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
        }
        notifyListeners();
    }
    
    public int getLastDiscountPlanID(){
        return lastDiscountPlanID;
    }
    
    public void addUser(String username, String password, String fname, String lname, String role) throws SQLException{
        String qry = "INSERT INTO `User` VALUES ('" + null+"', '" + username+"', MD5('" + password+"'), '" + fname+"', '" + lname+"', '" + role +"')";
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            stmt = con.prepareStatement(qry);
            stmt.executeUpdate();
        }finally{
            if(con != null) try {con.close();}catch(SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
        }
        notifyListeners();
    }
    
    public void deleteCustomer(int id) throws SQLException{
        String qry = "DELETE FROM Customer WHERE customerID='" + id + "'";
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            con.prepareStatement(qry).executeUpdate();
            stmt.executeUpdate();
        }catch(Exception e){}finally{
            if(con != null) try {con.close();}catch(SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
        }
        notifyListeners();
    }
    
    public void addListener (ChangeListener c){
        if(listeners == null){
            listeners = new ArrayList<ChangeListener>();
        }
        listeners.add(c);
    }
    
    public void notifyListeners(){
        for(ChangeListener c : listeners){
            c.stateChanged(new ChangeEvent(this));
        }
    }
}
