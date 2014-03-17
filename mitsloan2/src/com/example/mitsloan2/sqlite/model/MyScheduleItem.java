package com.example.mitsloan2.sqlite.model;

public class MyScheduleItem {
	
	int ID;
	String start_time;
	String end_time;
	String panel_title;
	String panel_description;
	String speakers;
	String location;
	
	// constructors
    public MyScheduleItem() {
    }
 
    public MyScheduleItem(String start_time, String end_time, String panel_title, String panel_description, String speakers,
	String location) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.panel_title = panel_title;
        this.panel_description = panel_description;
        this.speakers = speakers;
        this.location = location;
    }
	
 
    // setters
    public void setId(int id) {
        this.ID = id;
    }
 
    public void setStartTime(String start_time) {
        this.start_time = start_time;
    }
 
    public void setEndTime(String end_time) {
        this.end_time = end_time;
    }
     
    public void setPanelTitle(String panel_title){
        this.panel_title = panel_title;
    }
    
    public void setPanelDescription(String panel_title){
        this.panel_title = panel_title;
    }
    
    public void setSpeakers(String speakers){
        this.speakers = speakers;
    }
    
    public void setLocation(String location){
        this.location = location;
    }
 
    // getters
    public long getId() {
        return this.ID;
    }
 
    public String getStartTime() {
        return this.start_time;
    }
 
    public String getEndTime() {
        return this.end_time;
    }
    
    public String getPanelTitle() {
        return this.panel_title;
    }
    
    public String getPanelDescription() {
        return this.panel_description;
    }
    
    public String getSpeakers() {
        return this.speakers;
    }
    
    public String getLocation() {
        return this.location;
    }
}


