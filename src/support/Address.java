/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.support;

/**
 *
 * @author samatkuandykov
 */
public class Address {
    private int addressID;
    private String aline1;
    private String aline2;
    private String county;
    private String city;
    private String country;
    private String pcode;
    
    public int getAddressID(){
        return addressID;
    }
    
    public String getAline1(){
        return aline1;
    }
    
    public String getAline2(){
        return aline2;
    }
    
    public String getCounty(){
        return county;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getCountry(){
        return country;
    }
    
    public String getPCode(){
        return pcode;
    }
    
    public void setAddressID(int a){
        addressID = a;
    }
    
    public void setAline1(String a){
        aline1 = a;
    }
    
    public void setAline2(String a){
        aline2 = a;
    }
    
    public void setCounty(String a){
        county = a;
    }
    
    public void setCity(String a){
        city = a;
    }
    
    public void setCountry(String a){
        country = a;
    }
    
    public void setPcode(String a){
        pcode = a;
    }
}
