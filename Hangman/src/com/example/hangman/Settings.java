package com.example.hangman;



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class Settings{
	Context SettingsContext = null;
	public SeekBar sk1;
	public SeekBar sk2;
	public SaveData Data;
	public CheckData getData;
    public String word_length = "";
    public String amount_lives = "";
	public Settings(Context context){
		
		this.SettingsContext = context;
		Activity logicActivity = (context instanceof Activity)? (Activity) context:null;
	   
	final Dialog dialog = new Dialog(logicActivity);
	dialog.setContentView(R.layout.settings_dialog);
	dialog.setTitle("Settings");

	// set the custom dialog components - text, image and button
	TextView text = (TextView) dialog.findViewById(R.id.settingsDialog);
	text.setText("Configure your settings here!\n");
	TextView text2 = (TextView) dialog.findViewById(R.id.length_text);
	text2.setText("Set the length of the word:\n");
	TextView text3 = (TextView) dialog.findViewById(R.id.lives_text);
	text3.setText("Set the amount of lives:\n");
	getData = new CheckData(SettingsContext);
	sk1 =(SeekBar) dialog.findViewById(R.id.length_bar);
	if(!getData.getLength().equals("")){
	sk1.setProgress(Integer.parseInt(getData.getLength()));
	}
	else{
		sk1.setProgress(10);
	}
    sk1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {       

    @Override       
    public void onStopTrackingTouch(SeekBar seekBar) {      
        // TODO Auto-generated method stub      
    }       

    @Override       
    public void onStartTrackingTouch(SeekBar seekBar) {     
        // TODO Auto-generated method stub      
    }       

    @Override       
    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {     
        // TODO Auto-generated method stub      
    	word_length = "" + seekBar.getProgress();
    	Log.e("settings","geef door length" + word_length);
    }       
    });   
    
    sk2=(SeekBar) dialog.findViewById(R.id.lives_bar); 
    if(!getData.getLives().equals("")){
    sk2.setProgress(Integer.parseInt(getData.getLives()));
    }
    else{
    	sk2.setProgress(10);
    }
    sk2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {       
    
    @Override       
    public void onStopTrackingTouch(SeekBar seekBar) {      
        // TODO Auto-generated method stub      
    }       

    @Override       
    public void onStartTrackingTouch(SeekBar seekBar) {     
        // TODO Auto-generated method stub      
    }       

    @Override       
    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {     
        // TODO Auto-generated method stub 
    	Log.e("settings","geef door lives" + amount_lives);
    	amount_lives = "" + seekBar.getProgress();
        

    }       
    });   
    
	Button acceptButton = (Button) dialog.findViewById(R.id.ContinueButton);
	// if button is clicked, close the custom dialog
	acceptButton.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			//word_length en amount_lives sturen naar db
			Data = new SaveData(SettingsContext);
			word_length = "" + sk1.getProgress();
			amount_lives = "" + sk2.getProgress();
			
			Log.e("settings","Saved data" + amount_lives + "deze levens en " + word_length);
			Data.saveSettings(word_length, amount_lives);
			dialog.dismiss();
		}
	});
	dialog.show();
	
	}

}