/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.support;

/**
 *
 * @author samatkuandykov
 */
public class Customers {
    private int customerID;
    private String fname;
    private String lname;
    private String title;
    private String type;
    private int addressID;
    private int discountPlanID;
    
    public int getCustomerID(){
        return customerID;
    }
    
    public String getFName(){
        return fname;
    }
    
    public String getLName(){
        return lname;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getType(){
        return type;
    }
    
    public int getAddressID(){
        return addressID;
    }
    
    public int getDiscountPlanID(){
        return discountPlanID;
    }
    
    public void setCustomerID(int i){
        customerID = i;
    }
    
    public void setFName(String fn){
        fname = fn;
    }
    
    public void setLName(String ln){
        lname = ln;
    }
    
    public void setTitle(String cm){
        title = cm;
    }
    
    public void setType(String l){
        type = l;
    }
    
    public void setAddressID(int st){
        addressID = st;
    }
    
    public void setDiscountPlanID(int dp){
        discountPlanID = dp;
    }
}
