package com.example.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Gameplay{

	//TextViews
	public TextView Moves;
	public TextView Guesses;
	public TextView Letters;
	public TextView TheWord;

	//Integers
	public int movesLeft = 10;
	public int evil = 0;
	public int guessesMade = 0;

	//Strings
	public String guessedL = "";
	public String word = "";
	public String tempword ="";

	private ArrayList<String> words;  
	Context GameplayContext = null;
    public SaveData SaveObject;
    public CheckData getData;
    public Activity GameplayActivity;
	public Gameplay(Context context){
		this.GameplayContext = context;
		Activity GameplayActivity = (context instanceof Activity)? (Activity) context:null;

		//TextViews invullen
		Moves = (TextView) GameplayActivity.findViewById(R.id.something);
		Moves.setText("MovesLeft : " + movesLeft);

		Guesses = (TextView) GameplayActivity.findViewById(R.id.guesses);
		Guesses.setText("Guesses made : 0");

		Letters = (TextView) GameplayActivity.findViewById(R.id.guessedLetters);
		Letters.setText("Guessed letters : ");
		getData = new CheckData(GameplayContext);
		int numword = Integer.parseInt(getData.getLength());
		Log.e("settings","numword = " + numword);
		loadWords(numword);
		//woord invullen
		getWord();
		tempword ="";
		for(int i = 0;i<word.length();i++){
			tempword = tempword + "_ ";
		}
		TheWord = (TextView) GameplayActivity.findViewById(R.id.theword);
		TheWord.setText(tempword);

		//input mogelijk maken
		
		final EditText editText = (EditText) GameplayActivity.findViewById(R.id.input);
		
		editText.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				if(editText.getText().length() >0){
					boolean isLetter = arg0.toString().matches("[a-zA-Z]+");
			        boolean hasLowercase = !arg0.toString().equals(arg0.toString().toUpperCase());
					if(hasLowercase&&isLetter){
						guessedL = guessedL + arg0.toString();
						Algorithm(arg0.toString());
						
					}
					editText.setText("", TextView.BufferType.EDITABLE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {	

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {	

			}

		}); 

	}
    
	void doMove() {
		movesLeft = movesLeft - 1;
		Moves.setText("MovesLeft : " + movesLeft);

		if(movesLeft==0){
			final Dialog dialog = new Dialog(GameplayContext);
			dialog.setContentView(R.layout.continue_dialog);
			dialog.setTitle("Lost game");

			// set the custom dialog components - text, image and button
			TextView text = (TextView) dialog.findViewById(R.id.textDialog);
			text.setText("Sadly you did not guess the word:" + word +"\nThe game will reset." );
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
	void Algorithm(String letter){
		guessesMade = guessesMade + 1;
		Guesses.setText("Guesses made : " + guessesMade);

		Letters.setText("Guessed letters : " + guessedL);
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
		TheWord.setText(tempword);
		if(!tempword.contains("_")){
			SaveObject = new SaveData(GameplayContext);
			String user = "Freddy";
			String score = "" + movesLeft;
			SaveObject.save(user,score);
			final Dialog dialog = new Dialog(GameplayContext);
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
	void getWord(){
		
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		randomInt = randomInt % words.size();
		//loadWords(randomInt);
		word = words.get(randomInt).toLowerCase();
		
		
		
	}
	void loadWords(int length)
    {
            words = new ArrayList<String>();
            try
            {
                    InputStream stream = GameplayContext.getAssets().open("words" + length + ".txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(stream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    
                    String line;
                    while((line = bufferedReader.readLine()) != null)
                    {
                            words.add(line);
                    }
                    
                    bufferedReader.close();
            }
            catch(IOException e)
            {
                    Log.e("loadWords", e.getMessage());
            }
    }
	
	
	String evil(String letter){
		String theword = "somethingevil";
		return theword;
	}

	void Restart(){
		getData = new CheckData(GameplayContext);
		
		
		
		if(!getData.getLives().equals("")){
		  
		  //int newLength = Integer.parseInt(getData.getLength());
		  int newLives = Integer.parseInt(getData.getLives());
		  movesLeft = newLives;
		  Log.e("settings","gebeurd iets" + newLives + "aantal levens");
		}
		else{
			movesLeft = 10;
		}
		Log.e("settings","gebeurd iets aantal levens" + getData.getLives());
		
		
		guessesMade = 0;
		Moves.setText("MovesLeft : " + movesLeft);
		guessedL = "";
		int numword = Integer.parseInt(getData.getLength());
		Log.e("settings","numword = " + numword);
		loadWords(numword);
		getWord();
		tempword ="";
		for(int i = 0;i<word.length();i++){
			tempword = tempword + "_ ";
		}
		TheWord.setText(tempword);
		Guesses.setText("Guesses made : " + guessesMade);
		Letters.setText("Guessed letters : " + guessedL);


	}
}