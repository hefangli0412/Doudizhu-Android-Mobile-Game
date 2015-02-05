package com.example.cards;

import java.util.HashMap;

import android.app.Activity;
import android.widget.Toast;

import com.example.cards.CardAlg.Card;


public class Utils {

	public static String userName = "";
	
	
	public static float getPercentFromValue(float number, float amount){
		float percent = (number/amount)*100;
		return percent;
	}
	
	public static float getValueFromPercent(float percent, float amount){
		float value = (percent/100)*amount;
		return value;
	}
	
	public static void showToastAlert(Activity ctx, String alertMessage){
		Toast.makeText(ctx, alertMessage, Toast.LENGTH_SHORT).show();
	}
	
	public static void showToastOnUIThread(final Activity ctx, final String alertMessage){
		ctx.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(ctx, alertMessage, Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	public static Card getCardByString(String cardstring)
	{
		String rank;
		String suit;
		int int_rank;
		int int_suit;
		rank = cardstring.split(" of ")[0];
		suit = cardstring.split(" of ")[1];
        switch ( suit ) {
        case "spades":   int_suit = Card.SPADES;break;
        case "hearts":   int_suit = Card.HEARTS;break;
        case "diamonds": int_suit = Card.DIAMONDS;break;
        default:    int_suit = Card.CLUBS;
        }
        switch ( rank ) {
        case "ace":  int_rank = 1;break;
        case "2":   int_rank = 2;break;
        case "3":   int_rank = 3;break;
        case "4":   int_rank = 4;break;
        case "5":   int_rank = 5;break;
        case "6":   int_rank = 6;break;
        case "7":   int_rank = 7;break;
        case "8":   int_rank = 8;break;
        case "9":   int_rank = 9;break;
        case "10":  int_rank = 10;break;
        case "jack":  int_rank = 11;break;
        case "queen": int_rank = 12;break;
        default:  int_rank = 13;
        }
        Card returncard = new Card(int_rank,int_suit);
        return returncard;
	}
	public static int getCardValueinGame(String cardstring)
	{
		String rank;
		int int_rank;
		rank = cardstring.split(" of ")[0];

        switch ( rank ) {
        case "ace":  int_rank = 9;break;
        case "2":   int_rank = 11;break;
        case "3":   int_rank = 10;break;
        case "4":   int_rank = 1;break;
        case "5":   int_rank = 12;break;
        case "6":   int_rank = 2;break;
        case "7":   int_rank = 13;break;
        case "8":   int_rank = 3;break;
        case "9":   int_rank = 4;break;
        case "10":  int_rank = 5;break;
        case "jack":  int_rank = 6;break;
        case "queen": int_rank = 7;break;
        default:  int_rank = 8;
        }
        
        return int_rank;
	}
	public static int bonusScore(String cards_string) {
		Card[] cards = new Card[5];
		int i = 0;
		String[] tmpcard_array = cards_string.split(";");
		for(i = 0; i <5 ;i ++)
		{
			cards[i] = getCardByString(tmpcard_array[i]);
		}
		int score = 0;
		HashMap<Integer, Integer> buckets = new HashMap<>();
		for (i = 0; i < cards.length; i++) {
			if(buckets == null || !(buckets.containsKey(cards[i].getValue())) ) {
				buckets.put(cards[i].getValue(), 1);
			}
			else {
				int currCount = buckets.get(cards[i].getValue());
				currCount++;
				buckets.put(cards[i].getValue(), currCount);
			}
		}
		for (Integer val : buckets.values()) {
			if(val == 2) {
				score += 5;
			}
			else if(val == 3) {
				score += 10;
			}
			else if(val == 4) {
				score += 20;
			}
		}

		return score;
	}
}
