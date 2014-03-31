/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.support;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    
    public void addBlank(String blankID, String blankStatus, String blankTypeID, String username) throws SQLException{
        String qry = "INSERT INTO Blank VALUES ('"+blankID+"', '" + blankStatus+"', '" + blankTypeID+"', '" + username +"')";
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
    
    public void addBlankType(String blankTypeID, String blankType, String numberOfCoupons) throws SQLException{
        String qry = "INSERT INTO BlankType VALUES ('"+blankTypeID+"', '" + blankType+"', " + numberOfCoupons +")";
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
    
    public void addCommission(String percentage, String commissionType) throws SQLException{
        String qry = "INSERT INTO Commission VALUES (null, '"+percentage+"', '" + commissionType +"')";
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
    
    public void addReport(String salesOfficePlace, Date periodStartDate, Date periodEndDate, String type) throws SQLException{
        String qry = "INSERT INTO Report VALUES (null, '"+salesOfficePlace+"', '" + periodStartDate+"', '" + periodEndDate+"', '" + type +")";
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
    
    public void addSale(String price, String currency, String valueInUSD, Date date, Time time, String paymentType, String customerID, String username, String blankID, int commissionID, int refunded) throws SQLException{
        String qry = "INSERT INTO Sale VALUES (null, "+price+", '" + currency+"', " + valueInUSD+", '" + date+"', '" + time+
        "', " + paymentType+"', " + customerID+"', " + username + "', '" + blankID + "', " + commissionID + ", " + refunded +")";
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
    
    public void addSaleReport(int saleID, int reportID) throws SQLException{
        String qry = "INSERT INTO Sale_Report VALUES (null, " + saleID + ", " + reportID +")";
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
    
    public int getLastDiscountPlanID(){
        return lastDiscountPlanID;
    }
    
    public void addUser(String username, String password, String fname, String lname, String role) throws SQLException{
        String qry = "INSERT INTO `User` VALUES ('"+ username+"', '" + password+"', '" + fname+"', '" + lname+"', '" + role +"')";
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
    
    public void deleteCustomer(String id) throws SQLException{
        String q = "UPDATE Sale SET customerID = null WHERE customerID = " +id;
        String qry = "DELETE FROM Customer WHERE customerID=" + id;
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            con.prepareStatement(q).executeUpdate();
            con.prepareStatement(qry).executeUpdate();
        }catch(Exception e){}finally{
            if(con != null) try {con.close();}catch(SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
        }
    }
    
    public void deleteAddress(String id) throws SQLException{
        String qry = "DELETE FROM Address WHERE addressID=" + id;
        JOptionPane.showMessageDialog(null, qry);
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            con.prepareStatement(qry).executeUpdate();
        }catch(Exception e){}finally{
            if(con != null) try {con.close();}catch(SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
        }
    }
    
    public void deleteDiscountPlan(String id) throws SQLException{
        String qry = "DELETE FROM DiscountPlan WHERE discountPlanID=" + id;
        JOptionPane.showMessageDialog(null, qry);
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            con.prepareStatement(qry).executeUpdate();
        }catch(Exception e){}finally{
            if(con != null) try {con.close();}catch(SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
        }
        notifyListeners();
    }
    
    public void updateCustomer(String id, String fname, String lname, String title, String type, int addressID, int discountPlanID) throws SQLException{
        String qry = "UPDATE Customer SET " + " WHERE addressID=" + id;
        JOptionPane.showMessageDialog(null, qry);
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            con.prepareStatement(qry).executeUpdate();
        }catch(Exception e){}finally{
            if(con != null) try {con.close();}catch(SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
        }
    }
    
    public void updateAddress(String id, String aline1, String aline2, String county, String city, String country, String pcode) throws SQLException{
        String qry = "UPDATE Address SET addressLine1 = '" + aline1 + "', addressLine2 = '" + aline2 + "', county = '" + county + "', city = '" + 
                city + "', country = '" + country + "', postCode = '" + pcode + "' WHERE addressID=" + id;
        JOptionPane.showMessageDialog(null, qry);
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            con.prepareStatement(qry).executeUpdate();
        }catch(Exception e){}finally{
            if(con != null) try {con.close();}catch(SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
        }
    }
    
    public void updateDiscountPlan(String id, String percentage, String discountType, String howAwarded, int payLaterOption) throws SQLException{
        String qry = "UPDATE DiscountPlan SET percentage = '" + percentage + "', discountType = '" +discountType + "', howAwarded = '"
                +percentage + "', discountType = '" + percentage + "' WHERE addressID=" + id;
        JOptionPane.showMessageDialog(null, qry);
        Connection con = null;
        PreparedStatement stmt = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/ATS?user=root&password=root");
            con.prepareStatement(qry).executeUpdate();
        }catch(Exception e){}finally{
            if(con != null) try {con.close();}catch(SQLException se){}
            if(stmt != null) try {stmt.close();}catch(SQLException se){}
        }
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
