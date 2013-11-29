package com.example.hangman;



import android.app.Activity;
import android.app.Dialog;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class Highscores{
	Context HighscoresContext = null;
	CheckData DataObject = null;
	public Highscores(Context context){
		
		this.HighscoresContext = context;
		Activity HighscoresActivity = (context instanceof Activity)? (Activity) context:null;
		
		DataObject = new CheckData(HighscoresContext);
		String highscores = DataObject.getHighscore();
		// custom dialog
		
		final Dialog dialog = new Dialog(HighscoresActivity);
		dialog.setContentView(R.layout.continue_dialog);
		dialog.setTitle("Highscores");

		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.textDialog);
		text.setText("Here are some highscores!!\n" + highscores);
		ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
		image.setImageResource(R.drawable.image0);

		dialog.show();
		Button acceptButton = (Button) dialog.findViewById(R.id.ContinueButton);
		// if button is clicked, close the custom dialog
		acceptButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}
	
}