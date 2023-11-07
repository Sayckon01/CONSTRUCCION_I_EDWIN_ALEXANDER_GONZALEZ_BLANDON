/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.club;

public class Invoice {
    //Attributes
    private String concept,memberName;
    private int value;
    
    //Constructor method
    public Invoice(String concept, String memberName, int value) {
        this.concept = concept;
        this.memberName = memberName;
        this.value = value;
    }
    
    //Getters and Setters
    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
}
