package com.example.hangman;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import java.util.Random;



public class Game extends Activity {
	public TextView Moves;
	public TextView Theword;
	public String word;
	public String tempword;
	public String guessedL;
	
	public int evil = 0;
	public int movesLeft = 10;
	public int guess = 0;
	public TextView guessesMade;
	public TextView guessed;
	private Button buttonClick;
	private Button buttonClick2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		//moves left
		Moves = (TextView) findViewById(R.id.something);
		Moves.setText("MovesLeft : " + movesLeft);
	
		//the word
		getWord("");
		tempword ="";
        for(int i = 0;i<word.length();i++){
        	tempword = tempword + "_ ";
        }
		Theword = (TextView) findViewById(R.id.theword);
		Theword.setText(tempword);
		
		
		
		
		//guesses made
		guessesMade = (TextView) findViewById(R.id.guesses);
		guessesMade.setText("Guesses made : " + guess);
		
		//guessed letters
		guessedL = "";		
		guessed = (TextView) findViewById(R.id.guessedLetters);
		guessed.setText("Guessed letters : ");
	    final EditText editText = (EditText) findViewById(R.id.input);
		editText.setOnEditorActionListener(new OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		        boolean handled = false;
		        if (actionId == EditorInfo.IME_ACTION_SEND) {
		        	String input = editText.getText().toString();
		        	guessedL = guessedL + input;
		        	
		        	Algorithm(input);

		        	editText.setText("", TextView.BufferType.EDITABLE);
		        	
		            handled = true;
		        }
		        return handled;
		    }

		
		});
		
		buttonClick = (Button) findViewById(R.id.button2);

		// add listener to button 
		buttonClick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// custom dialog
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
						dialog.dismiss();
						Restart();
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
		
		buttonClick2 = (Button) findViewById(R.id.button1);

		// add listener to button 
		buttonClick2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// custom dialog
				final Dialog dialog = new Dialog(Game.this);
				dialog.setContentView(R.layout.dialog);
				dialog.setTitle("Exit");

				// set the custom dialog components - text, image and button
				TextView text = (TextView) dialog.findViewById(R.id.textDialog);
				text.setText("Are you sure you want to exit? You will loose your current progress.");
				ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
				image.setImageResource(R.drawable.image0);

				dialog.show();
				Button acceptButton = (Button) dialog.findViewById(R.id.AcceptButton);
				// if button is clicked, close the custom dialog
				acceptButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						finish();
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
		
		
	}
	
	
	
	
	
	
	
    void doMove() {
		movesLeft = movesLeft - 1;
		
		Moves.setText("MovesLeft : " + movesLeft);
		
		
		
		if(movesLeft==0){
			final Dialog dialog = new Dialog(Game.this);
			dialog.setContentView(R.layout.continue_dialog);
			dialog.setTitle("Lost game");

			// set the custom dialog components - text, image and button
			TextView text = (TextView) dialog.findViewById(R.id.textDialog);
			text.setText("Sadly you did not guess the word. The game will reset.");
			ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
			image.setImageResource(R.drawable.image0);

			dialog.show();
			Button acceptButton = (Button) dialog.findViewById(R.id.ContinueButton);
			// if button is clicked, close the custom dialog
			acceptButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					Restart();
				}
			});
			
			
			
		}
		
	}
    
    void Restart(){
      movesLeft = 10;
      guess = 0;
  	  Moves.setText("MovesLeft : " + movesLeft);
  	  guessedL = "";
  	  getWord("");
  	  tempword ="";
      for(int i = 0;i<word.length();i++){
    	tempword = tempword + "_ ";
      }
      Theword.setText(tempword);
  	  guessesMade.setText("Guesses made : " + guess);
  	  guessed.setText("Guessed letters : " + guessedL);
  	  
  	  
    }
    
	void Algorithm(String letter){
	  guess = guess + 1;
	  guessesMade.setText("Guesses made : " + guess);
	  
	  guessed.setText("Guessed letters : " + guessedL);
	  if(evil==1){
		  word = evil(letter);
	  }
	  if(word.contains(letter)){
        doWord();		  
	  }
	  else{
	    doMove();
	  }
	}
	void doWord(){
		//vul woord in
		int i = word.length();
		tempword = "";
		char [] arr = word.toCharArray();
		
		for(int j =0;j<i;j++){
			if(guessedL.indexOf(arr[j])>-1){
			  tempword = new StringBuilder().append(tempword).append(arr[j]).toString();
			}
			else{
			  tempword = tempword + " _";
			}
		}
		Theword.setText(tempword);
		if(!tempword.contains("_")){
			final Dialog dialog = new Dialog(Game.this);
			dialog.setContentView(R.layout.continue_dialog);
			dialog.setTitle("Won game!");

			// set the custom dialog components - text, image and button
			TextView text = (TextView) dialog.findViewById(R.id.textDialog);
			text.setText("Congratulations you have won the game! The game will reset.");
			ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
			image.setImageResource(R.drawable.image0);

			dialog.show();
			Button acceptButton = (Button) dialog.findViewById(R.id.ContinueButton);
			// if button is clicked, close the custom dialog
			acceptButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					Restart();
				}
			});
		}
	}
		
	
	
	
	
	void getWord(String letter){
			//pick a random word
		    //words moeten misschien nog to lowercase
			String [] wordDatabase;
			wordDatabase= new String[10];
			wordDatabase[0] = "jehova";
			wordDatabase[1] = "devoon";
			wordDatabase[2] = "vissig";
			wordDatabase[3] = "hoofdje";
			wordDatabase[4] = "prefix";
			wordDatabase[5] = "voyant";
			wordDatabase[6] = "bezweet";
			wordDatabase[7] = "concaaf";
			wordDatabase[8] = "clublid";
			wordDatabase[9] = "zwengel";
			
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(100);
			randomInt = randomInt % wordDatabase.length;
			word = wordDatabase[randomInt];
	}
	
	String evil(String letter){
		String theword = "somethingevil";
		return theword;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	
	
	public void EvilSwitch(View v) {
	  //pop up yes-> continue no->zet knop terug en stop
	  if(evil==1){
		evil = 0;
	  }
	  else{
		evil = 1;
	  }
	  Restart();
	}
	

}
