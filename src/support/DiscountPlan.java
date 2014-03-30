/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.support;

/**
 *
 * @author samatkuandykov
 */
public class DiscountPlan {
    private int discountPlanID;
    private double percentage;
    private String discountType;
    private String howAwarded;
    private Boolean payLaterOption;
    
    public int getDiscountPlanID(){
        return discountPlanID;
    }
    
    public double getPercentage(){
        return percentage;
    }
    
    public String getDiscountType(){
        return discountType;
    }
    
    public String getHowAwarded(){
        return howAwarded;
    }
    
    public Boolean getPayLaterOption(){
        return payLaterOption;
    }
    
    public void setDiscountPlanID(int a){
        discountPlanID = a;
    }
    
    public void setPercentage(double a){
        percentage = a;
    }
    
    public void setDiscountType(String a){
        discountType = a;
    }
    
    public void setHowAwarded(String a){
        howAwarded = a;
    }
    
    public void setPayLaterOption(Boolean a){
        payLaterOption = a;
    }
}
