package com.example.hangman;

import java.util.List;


import android.content.Context;
import android.util.Log;


public class CheckData{     

	public DataManipulator dm;
	public SettingsDataManipulator sdm;
	public Context CheckContext = null;
	public CheckData(Context context){
	this.CheckContext = context;
	//Activity logicActivity = (context instanceof Activity)? (Activity) context:null;
	}
	String getHighscore(){
		List<String[]> names2 =null;
		dm = new DataManipulator(CheckContext);
		names2 = dm.selectAll(); 
		String hSString = "";
	    for (String[] name : names2) {
		  hSString = hSString + name[1]+" - "+name[2] + "\n";
	    }
	    return hSString;
	}
	String getLength(){
		List<String[]> input =null;
		sdm = new SettingsDataManipulator(CheckContext);
		input = sdm.selectAll();
		String Length = "";
		for (String[] length : input) {
			  Length = "" + length[1];
			  Log.e("settings","lengte" + length[1]);
		}

	    return Length;
	}
	String getLives(){
		List<String[]> input =null;
		sdm = new SettingsDataManipulator(CheckContext);
		input = sdm.selectAll();
		String Lives = "";
		for (String[] name : input) {
			  Lives = "" + name[2];
		}
		
		return Lives;
	}
}
