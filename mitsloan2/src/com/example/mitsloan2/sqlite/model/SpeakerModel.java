package com.example.mitsloan2.sqlite.model;

public class SpeakerModel {
	
	int ID;
	String speaker_name;
	String speaker_title;
	String speaker_description;
	String speaker_contacts;
	String speaker_photo;
	
	// constructors
    public SpeakerModel() {
    }
 
    public SpeakerModel(String speaker_name, String speaker_title, String speaker_description, String speaker_contacts, String photo){
        this.speaker_name = speaker_name;
        this.speaker_title = speaker_title;
        this.speaker_description =  speaker_description;
        this.speaker_contacts = speaker_contacts;
        this.speaker_photo = speaker_photo;
    }
	
 
    // setters
    public void setId(int id) {
        this.ID = id;
    }
 
    public void setName(String name) {
        this.speaker_name = name;
    }
 
    public void setTitle(String title) {
        this.speaker_title = title;
    }
     
    public void setDescription(String description){
        this.speaker_description = description;
    }
    
    public void setContacts(String contacts){
        this.speaker_contacts = contacts;
    }
    
    public void setPhoto(String photo){
        this.speaker_photo = photo;
    }
 
    // getters
    public long getId() {
        return this.ID;
    }
 
    public String getName() {
        return this.speaker_name;
    }
 
    public String getTitle() {
        return this.speaker_title;
    }
    
    public String getDescription() {
        return this.speaker_description;
    }
    
    public String getPhoto() {
        return this.speaker_photo;
    }
    
    public String getContacts() {
        return this.speaker_contacts;
    }
  
}


