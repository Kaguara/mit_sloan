package com.example.mitsloan2.android.mobilebackend.model;

public class ScheduleInstance {
	  private String title;  
	    private String description;  
	    private String duration;  
	    private String imageKey;  
	    //image?  
	    public void Item()  
	    {  
	     this.title = "";  
	     this.description = "";  
	    }  
	    public String getTitle()   
	    {  
	        return this.title;  
	    }  
	    public void setTitle(String t)   
	    {  
	        this.title = t;  
	    }  
	    public String getDescription()   
	    {  
	        return this.description;  
	    }  
	    public String getImageKey()   
	    {  
	        return this.imageKey;  
	    }      
	    public String getDuration()   
	    {  
	        return this.duration;  
	    }          
	    public void setDescription(String d)   
	    {  
	        this.description = d;  
	    }  
	    public void setImageKey(String k)   
	    {  
	        this.imageKey = k;  
	    }  
	    public void setDuration(String p)   
	    {  
	        this.duration = p;  
	    }      
	    public String toString()  
	    {  
	     return "Item (Title:" + this.title + ", Description: " + this.description + ")";  
	    }

}
