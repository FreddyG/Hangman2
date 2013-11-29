package com.example.hangman;



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Exit{
	Context ExitContext = null;
	
	
	public Exit(Context context){
		
		this.ExitContext = context;
		final Activity ExitActivity = (context instanceof Activity)? (Activity) context:null;
		// custom dialog
		final Dialog dialog = new Dialog(ExitActivity);
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
				ExitActivity.finish();
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
}
