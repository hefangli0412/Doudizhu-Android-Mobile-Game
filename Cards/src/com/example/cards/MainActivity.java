package com.example.cards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	public TextView card_name;
	private ImageButton startbutton;
	private ImageView spades7;
	private ImageView joker;
	private ImageView hearts5;;
	private ImageView clubs2;
	private ImageView diamonds3;
	
	@Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);		
		setContentView(R.layout.activity_cards);
		
		// initialize fields
		startbutton = (ImageButton) findViewById(R.id.start_button);
		spades7 = (ImageView) findViewById(R.id.spades_7);
		joker = (ImageView) findViewById(R.id.joker);
		hearts5 = (ImageView) findViewById(R.id.hearts_5);
		clubs2 = (ImageView) findViewById(R.id.clubs_2);
		diamonds3 = (ImageView) findViewById(R.id.diamonds_3);

		rotateCards();
		
		traslateCardsFromBottom();
		
		startbutton.setOnClickListener( new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						traslateCardsToTop();
				        Intent intent = new Intent();
				        intent.putExtra("name","LeiPei");    
				        intent.setClass(v.getContext(), Waiting_room.class);
				        startActivity(intent);
				        finish();
					}
			
				});
	}
	
	private void rotateCards() {
		spades7.setRotation(-15);
		float xSpadesSeven = this.spades7.getX();
		spades7.setX(xSpadesSeven + 25);
		
		joker.setRotation(-7);
		float xJoker = joker.getX();
		joker.setX(xJoker + 10);
		
		clubs2.setRotation(7);
		float xClubsTwo = clubs2.getX();
		clubs2.setX(xClubsTwo - 25);
		
		diamonds3.setRotation(15);
		float xDiamondsThree = diamonds3.getX();
		diamonds3.setX(xDiamondsThree - 50);
	}
	
	private void traslateCardsFromBottom() {
		int duration=1700;
		int deltaY=700;
		
		TranslateAnimation anim_spades7 = new TranslateAnimation(
				300,0,deltaY,0);
		anim_spades7.setDuration(duration);
		spades7.startAnimation(anim_spades7);
				
		TranslateAnimation anim_joker = new TranslateAnimation(
				150,0,deltaY,0);
		anim_joker.setDuration(duration);
		joker.startAnimation(anim_joker);
				
		TranslateAnimation anim_hearts5 = new TranslateAnimation(
				0,0,deltaY,0);
		anim_hearts5.setDuration(duration);
		hearts5.startAnimation(anim_hearts5);
				
		TranslateAnimation anim_clubs2 = new TranslateAnimation(
				-150,0,deltaY,0);
		anim_clubs2.setDuration(duration);
		clubs2.startAnimation(anim_clubs2);
		
		TranslateAnimation anim_diamonds3 = new TranslateAnimation(
				-300,0,deltaY,0);
		anim_diamonds3.setDuration(duration);
		diamonds3.startAnimation(anim_diamonds3);
		
		TranslateAnimation anim_button = new TranslateAnimation(
				0,0,deltaY,0);
		anim_button.setDuration(duration);
		startbutton.startAnimation(anim_button);
	}
	
	private void traslateCardsToTop() {
		int duration=100;
		
		TranslateAnimation anim_spades7 = new TranslateAnimation(
				0,300,0,0);
		anim_spades7.setDuration(duration);
		anim_spades7.setFillEnabled(true);
		anim_spades7.setFillAfter(true);
		spades7.startAnimation(anim_spades7);
		
		TranslateAnimation anim_joker = new TranslateAnimation(
				0,150,0,0);
		anim_joker.setDuration(duration);
		anim_joker.setFillEnabled(true);
		anim_joker.setFillAfter(true);
		joker.startAnimation(anim_joker);
		
		TranslateAnimation anim_clubs2 = new TranslateAnimation(
				0,-150,0,0);
		anim_clubs2.setDuration(duration);
		anim_clubs2.setFillEnabled(true);
		anim_clubs2.setFillAfter(true);
		clubs2.startAnimation(anim_clubs2);
		
		TranslateAnimation anim_diamonds3 = new TranslateAnimation(
				0,-300,0,0);
		anim_diamonds3.setDuration(duration);
		anim_diamonds3.setFillEnabled(true);
		anim_diamonds3.setFillAfter(true);
		diamonds3.startAnimation(anim_diamonds3);
	}

}
