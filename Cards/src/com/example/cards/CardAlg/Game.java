package com.example.cards.CardAlg;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author hefangli
 *
 */
public interface Game {
	
	public void play();
	public void replay();
	public ArrayList<Hand> getWinner();
}
