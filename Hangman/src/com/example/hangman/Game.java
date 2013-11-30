package com.example.hangman;



import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;


import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;

import android.widget.ImageView;

import android.widget.TextView;

//words lezen van xml
//words stoppen in sql lite

//design pattern, design doc aanpassen

//features saven


public class Game extends Activity {

	private Button RestartButton;
	private Button ExitButton;
	private Button highscoreButton;
	private Button settings;

	private Gameplay gameLogic;
	private Settings gameSettings;
	private Exit gameExit;
	private Highscores gameHighscores;
	private SaveData defaultSetting;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		defaultSetting = new SaveData(this);
        defaultSetting.saveSettings("7", "10");
		gameLogic = new Gameplay(this);

        
		
		RestartButton = (Button) findViewById(R.id.button2);
		RestartButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
                //gameRestart = new Restart(Game.this);
				final Dialog dialog = new Dialog(Game.this);
				dialog.setContentView(R.layout.dialog);
				dialog.setTitle("Restart");

				// set the custom dialog components - text, image and button
				TextView text = (TextView) dialog.findViewById(R.id.textDialog);
				text.setText("Are you sure you want to restart? You will loose your current progress.");
				ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
				image.setImageResource(R.drawable.restart);

				dialog.show();
				
				Button acceptButton = (Button) dialog.findViewById(R.id.AcceptButton);
				// if button is clicked, close the custom dialog
				acceptButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						
						gameLogic.Restart();
						dialog.dismiss();
						
					}
				});


				Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
				// if button is clicked, close the custom dialog
				declineButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();

					}
				});
                
			}

		});
        
		ExitButton = (Button) findViewById(R.id.button1);
		ExitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				
				gameExit = new Exit(Game.this);
                
			}

		});

		highscoreButton = (Button) findViewById(R.id.highscores);
		highscoreButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				gameHighscores = new Highscores(Game.this);

			}

		});
		
		
		settings = (Button) findViewById(R.id.settings);
		settings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// custom dialog
				
				gameSettings = new Settings(Game.this);


			}

		});
		

	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}




}
