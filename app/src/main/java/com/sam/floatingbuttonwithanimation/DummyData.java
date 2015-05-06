package com.sam.floatingbuttonwithanimation;

public class DummyData {

    public String mTitle, mDescription;

    public DummyData(String title, String description){

        setDummyTitle(title);
        setDummyDescription(description);
    }

    public void setDummyTitle(String title){
        this.mTitle = title;
    }

    public String getDummyTitle(){
        return this.mTitle;
    }

    public void setDummyDescription(String description){
        this.mDescription = description;
    }

    public String getDummyDescription(){
        return this.mDescription;
    }
}
