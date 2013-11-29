package com.example.hangman;

import android.content.Context;


public class SaveData{  
	
	public DataManipulator dh;
	public SettingsDataManipulator sdh; 
	public Context SaveContext = null;
	
    public SaveData(Context context){	
	  this.SaveContext = context;
    }
    
	void save(String user,String score){
		String myEditText1=user;
		String myEditText2=score;
		this.dh = new DataManipulator(SaveContext);
		this.dh.insert(myEditText1,myEditText2);
	
	}
	
	void saveSettings(String length,String lives){
	    String myEditText1=length;
	    String myEditText2=lives;
		this.sdh = new SettingsDataManipulator(SaveContext);
		this.sdh.insert(myEditText1,myEditText2);
		
	}


}

