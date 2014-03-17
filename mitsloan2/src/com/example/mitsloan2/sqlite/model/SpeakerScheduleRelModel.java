package com.example.mitsloan2.sqlite.model;

public class SpeakerScheduleRelModel {
	
	int ID;
	int speaker_ID;
	int  schedule_ID;
	
	
	// constructors
    public SpeakerScheduleRelModel() {
    }
 
    public SpeakerScheduleRelModel(int speaker_ID, int schedule_ID){
        this.speaker_ID = speaker_ID;
        this.schedule_ID = schedule_ID;
    }	
 
    // setters
    public void setId(int id) {
        this.ID = id;
    }
 
    public void setSpeakerID(int speaker_ID) {
        this.speaker_ID = speaker_ID;
    }
 
    public void setSCheduleID(int schedule_ID) {
        this.schedule_ID = schedule_ID;
    }
 
    // getters
    public long getId() {
        return this.ID;
    }
 
    public int getSpeakerID() {
        return this.speaker_ID;
    }
 
    public int getScheduleID() {
        return this.schedule_ID;
    }
    
    //setters
  

  
}


