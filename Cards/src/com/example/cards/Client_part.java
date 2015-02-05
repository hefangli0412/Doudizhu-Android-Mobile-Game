package com.example.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cards.CardAlg.Card;
import com.example.cards.CardAlg.CardValue;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;
import com.shephertz.app42.gaming.multiplayer.client.listener.RoomRequestListener;

public class Client_part extends Activity implements IOnSceneTouchListener,
		RoomRequestListener, NotifyListener {
	private static final int MAX_NUM_CARDS = 5;
	private ArrayList<ImageView> cardList;
	private static Map<CardStruct, ImageView> cardMap;
	private TextView information;
	private TextView scoreMessage;
	private TextView goldCoinMessage;
	private Button skip;
	private Button instruction;
	private Button exchange;
	private Button quit_button;
	private int Minvalue = 0;
	private Context context;
	private CardPosition[] positionArray;
	private GestureDetector gdt;
	private TextView username;
	private int goldCoins = 10;
	private int Score = 0;
	private WarpClient theClient;
	public String status = "0";
	private String roomId = "";
	private int exchange_flag = 0;
	private int currentStatus = 0; // this status is used for the message
	// recevied from the server....Updtaed in
	// the chatre...
	float left = 0;
	float gap = 0;
	float right = 0;
	private RelativeLayout mLayout;

	// 2014/11/05
	static SoundPool sounds;
	static int wrongsound;
	static int triumphsound;
	static int swipesound;

	// public static int status21in=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.client_part_layout);

		cardList = new ArrayList<ImageView>();
		cardMap = new HashMap<CardStruct, ImageView>();
		information = (TextView) findViewById(R.id.information);
		scoreMessage = (TextView) findViewById(R.id.scoreView);
		goldCoinMessage = (TextView) findViewById(R.id.coinsView);
		skip = (Button) findViewById(R.id.skipbutton);
		instruction = (Button) findViewById(R.id.instructionbutton);
		exchange =  (Button) findViewById(R.id.exchangebutton);
		quit_button = (Button) findViewById(R.id.quitbutton);
		positionArray = new CardPosition[5];
		gdt = new GestureDetector(new GestureListener());
		mLayout = (RelativeLayout) findViewById(R.id.client_layout);
		username = (TextView)findViewById(R.id.nameTextView);
		
		// yan's 2014/11/05
		sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		wrongsound = sounds.load(this.getApplicationContext(), R.raw.wrong, 1);
		triumphsound = sounds.load(this.getApplicationContext(), R.raw.triumph,
				1);
		swipesound = sounds.load(this.getApplicationContext(), R.raw.swipe, 1);

		addUserIcon();
		initCardMap();
		initPositionArray();
		this.context = this;
		skip.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentStatus = 0;
				sendUpdateEvent("skip", null);
				InformPart("Skipped!");
				Client_part.this.runOnUiThread(new Runnable() {
					public void run() {
						skip.setTextColor(Color.WHITE);
					}
				});
			}
		});
		instruction.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog settingsDialog = new Dialog(context);
				settingsDialog.getWindow().requestFeature(
						Window.FEATURE_NO_TITLE);
				settingsDialog.setContentView(getLayoutInflater().inflate(
						R.layout.image_layout, null));
				settingsDialog.show();
			}
		});
		exchange.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//2014/11/22
				if(goldCoins<5){			
						InformPart("Less than 5 gold coins. Cannot exchange cards.");
						exchange_flag = 0;
				}
				else{
					InformPart("Pick a card to change.");
					exchange_flag = 1;
					skip.setTextColor(Color.WHITE);
				}
			}
		});
		quit_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				quit_function();
			}
		});
		try {
			theClient = WarpClient.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Intent intent = getIntent();
		roomId = intent.getStringExtra("roomId");
		init(roomId);
	}

	private void InformPart(final String st) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				information.setText(st);
			}

		});

	}

	private void scorePart(final int score2) {
		System.out.println("current score: " + score2);
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				scoreMessage.setText(String.format("%d", score2));
			}

		});

	}

	private void coinPart(final int goldCoins2) {
		// System.out.println("current coins: "+);
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				goldCoinMessage.setText(String.format("%d", goldCoins2));
				
				if (goldCoins2 >= 5) {
					exchange.setTextColor(Color.WHITE);
				} else {
					exchange.setTextColor(Color.BLACK);
				}
				
			}

		});

	}

	private void addUserIcon() {
		// hefang 2014/11/15
		String name = Utils.userName.split("_@")[0];
		String monsterChoosed = Utils.userName.split("_@")[1];
		username.setText(name);
		
		ImageView userIcon = new ImageView(this);
		if (monsterChoosed.equals("1")) {
			userIcon.setImageResource(R.drawable.monster1);
		} else if (monsterChoosed.equals("2")) {
			userIcon.setImageResource(R.drawable.monster2);
		} else if (monsterChoosed.equals("3")) {
			userIcon.setImageResource(R.drawable.monster3);
		} else {
			userIcon.setImageResource(R.drawable.monster4);
		}
		mLayout.addView(userIcon);
		userIcon.setX(250);
		userIcon.setY(5);
		userIcon.getLayoutParams().height = 150;
		userIcon.getLayoutParams().width = 200;
	}

	private void init(String roomId) {
		if (theClient != null) {
			theClient.addRoomRequestListener(this);
			theClient.addNotificationListener(this);
			theClient.joinRoom(roomId);
		}
	}

	public void dismissListener(View view) {

	}
	
	private void filterCards(int min_value) {
		// Change color of cards smaller than Minvalue
		boolean shouldSkip = true;
		for (int i = 0; i < MAX_NUM_CARDS; i++) {
			ImageView cardView = positionArray[i].card;
			if (cardView == null) continue;
			
			Object tag = cardView.getTag();
			int tagPrime = Integer
					.parseInt(tag.toString());
			CardStruct cs = getCardStruct(tagPrime);
			runOnUiThread(new RunnableClass2(cardView, 0));
			
			Card tmp = new Card(cs.value, 1);
			int rank = CardValue.values.get(tmp);
			if (rank <= min_value) {
				runOnUiThread(new RunnableClass2(cardView, 1));
			} else {
				shouldSkip = false;
			}
		}
		
		if (shouldSkip) {
			Client_part.this.runOnUiThread(new Runnable() {
				public void run() {
					skip.setTextColor(Color.RED);
				}
			});
		}
	}


	private void refillCards(String cardsFromServer) {

		System.out.println(Utils.userName + " in refill cards(): "
				+ cardsFromServer);
		String[] tmpcard_array = cardsFromServer.split(";");
		int cardindex = 0;
		for (int i = 0; i < MAX_NUM_CARDS; i++) {
			ImageView cardView = positionArray[i].card;
			if (cardView == null) {

				System.out.println(Utils.userName + " " + i
						+ "th card is empty");
				try {
					final Card c = Utils
							.getCardByString(tmpcard_array[cardindex]);
					System.out.println(Utils.userName + ": get " + cardindex
							+ "th card from server");
					if (c == null) {
						// System.out.println("this is in the refill, and the card is null. card index+"
						// + cardindex);
						System.out
								.println(Utils.userName
										+ ": "
										+ cardindex
										+ "th card from server is null, refillcards() done!");
						return;
					}

					cardindex++;
					ImageView cv = cardMap.get(new CardStruct(c.getValue(), c
							.getSuit()));

					// System.out.println(cv.toString());
					// what is cardlist?????????????????????????????????????
					cardList.add(cv);
					
					System.out.println(Utils.userName
							+ ": calling runOnUIThread() for " + i
							+ "th card position.");

					runOnUiThread(new RunnableClass(i, cv, mLayout, c));

					System.out.println(Utils.userName
							+ ": runOnUIThread() for " + i
							+ "th card position is called");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
				
		System.out.println(Utils.userName + ": refillcards() done!!");
	}

	private void clearCards() {
		
		for (int i = 0; i < MAX_NUM_CARDS; i++) {
			ImageView cardView = positionArray[i].card;
			if (cardView != null) {
				cardView.setVisibility(View.GONE);
			}
		}

		positionArray = new CardPosition[5];
		initPositionArray();
		cardMap = new HashMap<CardStruct, ImageView>();
		initCardMap();
	}
	
	private void initPositionArray() {

		CardPosition cp0 = new CardPosition(230, 7);
		CardPosition cp1 = new CardPosition(230, 7 + 130);
		CardPosition cp2 = new CardPosition(230, 7 + 130 * 2);
		CardPosition cp3 = new CardPosition(230, 7 + 130 * 3);
		CardPosition cp4 = new CardPosition(230, 7 + 130 * 4);
		positionArray[0] = cp0;
		positionArray[1] = cp1;
		positionArray[2] = cp2;
		positionArray[3] = cp3;
		positionArray[4] = cp4;
	}

	private CardStruct getCardStruct(int id) {
		CardStruct cs;

		switch (id) {
		case R.drawable.clubs_10:
			cs = new CardStruct(10, 4);
			break;
		case R.drawable.clubs_2:
			cs = new CardStruct(2, 4);
			break;
		case R.drawable.clubs_3:
			cs = new CardStruct(3, 4);
			break;
		case R.drawable.clubs_4:
			cs = new CardStruct(4, 4);
			break;
		case R.drawable.clubs_5:
			cs = new CardStruct(5, 4);
			break;
		case R.drawable.clubs_6:
			cs = new CardStruct(6, 4);
			break;
		case R.drawable.clubs_7:
			cs = new CardStruct(7, 4);
			break;
		case R.drawable.clubs_8:
			cs = new CardStruct(8, 4);
			break;
		case R.drawable.clubs_9:
			cs = new CardStruct(9, 4);
			break;
		case R.drawable.clubs_ace:
			cs = new CardStruct(1, 4);
			break;
		case R.drawable.clubs_jack:
			cs = new CardStruct(11, 4);
			break;
		case R.drawable.clubs_king:
			cs = new CardStruct(13, 4);
			break;
		case R.drawable.clubs_queen:
			cs = new CardStruct(12, 4);
			break;

		case R.drawable.diamonds_10:
			cs = new CardStruct(10, 3);
			break;
		case R.drawable.diamonds_2:
			cs = new CardStruct(2, 3);
			break;
		case R.drawable.diamonds_3:
			cs = new CardStruct(3, 3);
			break;
		case R.drawable.diamonds_4:
			cs = new CardStruct(4, 3);
			break;
		case R.drawable.diamonds_5:
			cs = new CardStruct(5, 3);
			break;
		case R.drawable.diamonds_6:
			cs = new CardStruct(6, 3);
			break;
		case R.drawable.diamonds_7:
			cs = new CardStruct(7, 3);
			break;
		case R.drawable.diamonds_8:
			cs = new CardStruct(8, 3);
			break;
		case R.drawable.diamonds_9:
			cs = new CardStruct(9, 3);
			break;
		case R.drawable.diamonds_ace:
			cs = new CardStruct(1, 3);
			break;
		case R.drawable.diamonds_jack:
			cs = new CardStruct(11, 3);
			break;
		case R.drawable.diamonds_king:
			cs = new CardStruct(13, 3);
			break;
		case R.drawable.diamonds_queen:
			cs = new CardStruct(12, 3);
			break;

		case R.drawable.hearts_10:
			cs = new CardStruct(10, 2);
			break;
		case R.drawable.hearts_2:
			cs = new CardStruct(2, 2);
			break;
		case R.drawable.hearts_3:
			cs = new CardStruct(3, 2);
			break;
		case R.drawable.hearts_4:
			cs = new CardStruct(4, 2);
			break;
		case R.drawable.hearts_5:
			cs = new CardStruct(5, 2);
			break;
		case R.drawable.hearts_6:
			cs = new CardStruct(6, 2);
			break;
		case R.drawable.hearts_7:
			cs = new CardStruct(7, 2);
			break;
		case R.drawable.hearts_8:
			cs = new CardStruct(8, 2);
			break;
		case R.drawable.hearts_9:
			cs = new CardStruct(9, 2);
			break;
		case R.drawable.hearts_ace:
			cs = new CardStruct(1, 2);
			break;
		case R.drawable.hearts_jack:
			cs = new CardStruct(11, 2);
			break;
		case R.drawable.hearts_king:
			cs = new CardStruct(13, 2);
			break;
		case R.drawable.hearts_queen:
			cs = new CardStruct(12, 2);
			break;

		case R.drawable.spades_10:
			cs = new CardStruct(10, 1);
			break;
		case R.drawable.spades_2:
			cs = new CardStruct(2, 1);
			break;
		case R.drawable.spades_3:
			cs = new CardStruct(3, 1);
			break;
		case R.drawable.spades_4:
			cs = new CardStruct(4, 1);
			break;
		case R.drawable.spades_5:
			cs = new CardStruct(5, 1);
			break;
		case R.drawable.spades_6:
			cs = new CardStruct(6, 1);
			break;
		case R.drawable.spades_7:
			cs = new CardStruct(7, 1);
			break;
		case R.drawable.spades_8:
			cs = new CardStruct(8, 1);
			break;
		case R.drawable.spades_9:
			cs = new CardStruct(9, 1);
			break;
		case R.drawable.spades_ace:
			cs = new CardStruct(1, 1);
			break;
		case R.drawable.spades_jack:
			cs = new CardStruct(11, 1);
			break;
		case R.drawable.spades_king:
			cs = new CardStruct(13, 1);
			break;
		case R.drawable.spades_queen:
			cs = new CardStruct(12, 1);
			break;

		default:
			cs = null;
		}

		return cs;
	}

	private void initCardMap() {
		ImageView clubs_10 = new ImageView(this);
		clubs_10.setImageResource(R.drawable.clubs_10);
		clubs_10.setTag(R.drawable.clubs_10);
		cardMap.put(new CardStruct(10, 4), clubs_10);

		ImageView clubs_2 = new ImageView(this);
		clubs_2.setImageResource(R.drawable.clubs_2);
		clubs_2.setTag(R.drawable.clubs_2);
		cardMap.put(new CardStruct(2, 4), clubs_2);

		ImageView clubs_3 = new ImageView(this);
		clubs_3.setImageResource(R.drawable.clubs_3);
		clubs_3.setTag(R.drawable.clubs_3);
		cardMap.put(new CardStruct(3, 4), clubs_3);

		ImageView clubs_4 = new ImageView(this);
		clubs_4.setImageResource(R.drawable.clubs_4);
		clubs_4.setTag(R.drawable.clubs_4);
		cardMap.put(new CardStruct(4, 4), clubs_4);

		ImageView clubs_5 = new ImageView(this);
		clubs_5.setImageResource(R.drawable.clubs_5);
		clubs_5.setTag(R.drawable.clubs_5);
		cardMap.put(new CardStruct(5, 4), clubs_5);

		ImageView clubs_6 = new ImageView(this);
		clubs_6.setImageResource(R.drawable.clubs_6);
		clubs_6.setTag(R.drawable.clubs_6);
		cardMap.put(new CardStruct(6, 4), clubs_6);

		ImageView clubs_7 = new ImageView(this);
		clubs_7.setImageResource(R.drawable.clubs_7);
		clubs_7.setTag(R.drawable.clubs_7);
		cardMap.put(new CardStruct(7, 4), clubs_7);

		ImageView clubs_8 = new ImageView(this);
		clubs_8.setImageResource(R.drawable.clubs_8);
		clubs_8.setTag(R.drawable.clubs_8);
		cardMap.put(new CardStruct(8, 4), clubs_8);

		ImageView clubs_9 = new ImageView(this);
		clubs_9.setImageResource(R.drawable.clubs_9);
		clubs_9.setTag(R.drawable.clubs_9);
		cardMap.put(new CardStruct(9, 4), clubs_9);

		ImageView clubs_ace = new ImageView(this);
		clubs_ace.setImageResource(R.drawable.clubs_ace);
		clubs_ace.setTag(R.drawable.clubs_ace);
		cardMap.put(new CardStruct(1, 4), clubs_ace);

		ImageView clubs_jack = new ImageView(this);
		clubs_jack.setImageResource(R.drawable.clubs_jack);
		clubs_jack.setTag(R.drawable.clubs_jack);
		cardMap.put(new CardStruct(11, 4), clubs_jack);

		ImageView clubs_king = new ImageView(this);
		clubs_king.setImageResource(R.drawable.clubs_king);
		clubs_king.setTag(R.drawable.clubs_king);
		cardMap.put(new CardStruct(13, 4), clubs_king);

		ImageView clubs_queen = new ImageView(this);
		clubs_queen.setImageResource(R.drawable.clubs_queen);
		clubs_queen.setTag(R.drawable.clubs_queen);
		cardMap.put(new CardStruct(12, 4), clubs_queen);

		ImageView diamonds_10 = new ImageView(this);
		diamonds_10.setImageResource(R.drawable.diamonds_10);
		diamonds_10.setTag(R.drawable.diamonds_10);
		cardMap.put(new CardStruct(10, 3), diamonds_10);

		ImageView diamonds_2 = new ImageView(this);
		diamonds_2.setImageResource(R.drawable.diamonds_2);
		diamonds_2.setTag(R.drawable.diamonds_2);
		cardMap.put(new CardStruct(2, 3), diamonds_2);

		ImageView diamonds_3 = new ImageView(this);
		diamonds_3.setImageResource(R.drawable.diamonds_3);
		diamonds_3.setTag(R.drawable.diamonds_3);
		cardMap.put(new CardStruct(3, 3), diamonds_3);

		ImageView diamonds_4 = new ImageView(this);
		diamonds_4.setImageResource(R.drawable.diamonds_4);
		diamonds_4.setTag(R.drawable.diamonds_4);
		cardMap.put(new CardStruct(4, 3), diamonds_4);

		ImageView diamonds_5 = new ImageView(this);
		diamonds_5.setImageResource(R.drawable.diamonds_5);
		diamonds_5.setTag(R.drawable.diamonds_5);
		cardMap.put(new CardStruct(5, 3), diamonds_5);

		ImageView diamonds_6 = new ImageView(this);
		diamonds_6.setImageResource(R.drawable.diamonds_6);
		diamonds_6.setTag(R.drawable.diamonds_6);
		cardMap.put(new CardStruct(6, 3), diamonds_6);

		ImageView diamonds_7 = new ImageView(this);
		diamonds_7.setImageResource(R.drawable.diamonds_7);
		diamonds_7.setTag(R.drawable.diamonds_7);
		cardMap.put(new CardStruct(7, 3), diamonds_7);

		ImageView diamonds_8 = new ImageView(this);
		diamonds_8.setImageResource(R.drawable.diamonds_8);
		diamonds_8.setTag(R.drawable.diamonds_8);
		cardMap.put(new CardStruct(8, 3), diamonds_8);

		ImageView diamonds_9 = new ImageView(this);
		diamonds_9.setImageResource(R.drawable.diamonds_9);
		diamonds_9.setTag(R.drawable.diamonds_9);
		cardMap.put(new CardStruct(9, 3), diamonds_9);

		ImageView diamonds_ace = new ImageView(this);
		diamonds_ace.setImageResource(R.drawable.diamonds_ace);
		diamonds_ace.setTag(R.drawable.diamonds_ace);
		cardMap.put(new CardStruct(1, 3), diamonds_ace);

		ImageView diamonds_jack = new ImageView(this);
		diamonds_jack.setImageResource(R.drawable.diamonds_jack);
		diamonds_jack.setTag(R.drawable.diamonds_jack);
		cardMap.put(new CardStruct(11, 3), diamonds_jack);

		ImageView diamonds_king = new ImageView(this);
		diamonds_king.setImageResource(R.drawable.diamonds_king);
		diamonds_king.setTag(R.drawable.diamonds_king);
		cardMap.put(new CardStruct(13, 3), diamonds_king);

		ImageView diamonds_queen = new ImageView(this);
		diamonds_queen.setImageResource(R.drawable.diamonds_queen);
		diamonds_queen.setTag(R.drawable.diamonds_queen);
		cardMap.put(new CardStruct(12, 3), diamonds_queen);

		ImageView hearts_10 = new ImageView(this);
		hearts_10.setImageResource(R.drawable.hearts_10);
		hearts_10.setTag(R.drawable.hearts_10);
		cardMap.put(new CardStruct(10, 2), hearts_10);

		ImageView hearts_2 = new ImageView(this);
		hearts_2.setImageResource(R.drawable.hearts_2);
		hearts_2.setTag(R.drawable.hearts_2);
		cardMap.put(new CardStruct(2, 2), hearts_2);

		ImageView hearts_3 = new ImageView(this);
		hearts_3.setImageResource(R.drawable.hearts_3);
		hearts_3.setTag(R.drawable.hearts_3);
		cardMap.put(new CardStruct(3, 2), hearts_3);

		ImageView hearts_4 = new ImageView(this);
		hearts_4.setImageResource(R.drawable.hearts_4);
		hearts_4.setTag(R.drawable.hearts_4);
		cardMap.put(new CardStruct(4, 2), hearts_4);

		ImageView hearts_5 = new ImageView(this);
		hearts_5.setImageResource(R.drawable.hearts_5);
		hearts_5.setTag(R.drawable.hearts_5);
		cardMap.put(new CardStruct(5, 2), hearts_5);

		ImageView hearts_6 = new ImageView(this);
		hearts_6.setImageResource(R.drawable.hearts_6);
		hearts_6.setTag(R.drawable.hearts_6);
		cardMap.put(new CardStruct(6, 2), hearts_6);

		ImageView hearts_7 = new ImageView(this);
		hearts_7.setImageResource(R.drawable.hearts_7);
		hearts_7.setTag(R.drawable.hearts_7);
		cardMap.put(new CardStruct(7, 2), hearts_7);

		ImageView hearts_8 = new ImageView(this);
		hearts_8.setImageResource(R.drawable.hearts_8);
		hearts_8.setTag(R.drawable.hearts_8);
		cardMap.put(new CardStruct(8, 2), hearts_8);

		ImageView hearts_9 = new ImageView(this);
		hearts_9.setImageResource(R.drawable.hearts_9);
		hearts_9.setTag(R.drawable.hearts_9);
		cardMap.put(new CardStruct(9, 2), hearts_9);

		ImageView hearts_ace = new ImageView(this);
		hearts_ace.setImageResource(R.drawable.hearts_ace);
		hearts_ace.setTag(R.drawable.hearts_ace);
		cardMap.put(new CardStruct(1, 2), hearts_ace);

		ImageView hearts_jack = new ImageView(this);
		hearts_jack.setImageResource(R.drawable.hearts_jack);
		hearts_jack.setTag(R.drawable.hearts_jack);
		cardMap.put(new CardStruct(11, 2), hearts_jack);

		ImageView hearts_king = new ImageView(this);
		hearts_king.setImageResource(R.drawable.hearts_king);
		hearts_king.setTag(R.drawable.hearts_king);
		cardMap.put(new CardStruct(13, 2), hearts_king);

		ImageView hearts_queen = new ImageView(this);
		hearts_queen.setImageResource(R.drawable.hearts_queen);
		hearts_queen.setTag(R.drawable.hearts_queen);
		cardMap.put(new CardStruct(12, 2), hearts_queen);

		ImageView spades_10 = new ImageView(this);
		spades_10.setImageResource(R.drawable.spades_10);
		spades_10.setTag(R.drawable.spades_10);
		cardMap.put(new CardStruct(10, 1), spades_10);

		ImageView spades_2 = new ImageView(this);
		spades_2.setImageResource(R.drawable.spades_2);
		spades_2.setTag(R.drawable.spades_2);
		cardMap.put(new CardStruct(2, 1), spades_2);

		ImageView spades_3 = new ImageView(this);
		spades_3.setImageResource(R.drawable.spades_3);
		spades_3.setTag(R.drawable.spades_3);
		cardMap.put(new CardStruct(3, 1), spades_3);

		ImageView spades_4 = new ImageView(this);
		spades_4.setImageResource(R.drawable.spades_4);
		spades_4.setTag(R.drawable.spades_4);
		cardMap.put(new CardStruct(4, 1), spades_4);

		ImageView spades_5 = new ImageView(this);
		spades_5.setImageResource(R.drawable.spades_5);
		spades_5.setTag(R.drawable.spades_5);
		cardMap.put(new CardStruct(5, 1), spades_5);

		ImageView spades_6 = new ImageView(this);
		spades_6.setImageResource(R.drawable.spades_6);
		spades_6.setTag(R.drawable.spades_6);
		cardMap.put(new CardStruct(6, 1), spades_6);

		ImageView spades_7 = new ImageView(this);
		spades_7.setImageResource(R.drawable.spades_7);
		spades_7.setTag(R.drawable.spades_7);
		cardMap.put(new CardStruct(7, 1), spades_7);

		ImageView spades_8 = new ImageView(this);
		spades_8.setImageResource(R.drawable.spades_8);
		spades_8.setTag(R.drawable.spades_8);
		cardMap.put(new CardStruct(8, 1), spades_8);

		ImageView spades_9 = new ImageView(this);
		spades_9.setImageResource(R.drawable.spades_9);
		spades_9.setTag(R.drawable.spades_9);
		cardMap.put(new CardStruct(9, 1), spades_9);

		ImageView spades_ace = new ImageView(this);
		spades_ace.setImageResource(R.drawable.spades_ace);
		spades_ace.setTag(R.drawable.spades_ace);
		cardMap.put(new CardStruct(1, 1), spades_ace);

		ImageView spades_jack = new ImageView(this);
		spades_jack.setImageResource(R.drawable.spades_jack);
		spades_jack.setTag(R.drawable.spades_jack);
		cardMap.put(new CardStruct(11, 1), spades_jack);

		ImageView spades_king = new ImageView(this);
		spades_king.setImageResource(R.drawable.spades_king);
		spades_king.setTag(R.drawable.spades_king);
		cardMap.put(new CardStruct(13, 1), spades_king);

		ImageView spades_queen = new ImageView(this);
		spades_queen.setImageResource(R.drawable.spades_queen);
		spades_queen.setTag(R.drawable.spades_queen);
		cardMap.put(new CardStruct(12, 1), spades_queen);
	}

	/* helper class -- gesture listener */

	private static final int SWIPE_MIN_DISTANCE = 10;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;

	private class GestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				// System.out.println("Left");
				return true; // Right to left
			} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				// System.out.println("Right");
				return true; // Left to right
			}

			if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
				// System.out.println("Top");
				return true; // Bottom to top
			} else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
				// System.out.println("Bottom");
				return true; // Top to bottom
			}
			return false;
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// System.out.println("tapped!");
			return super.onSingleTapConfirmed(e);
		}

	}

	/* helper class -- card structure */

	private class CardStruct {
		public int value;
		public int suit;

		public CardStruct(int v, int s) {
			value = v;
			suit = s;
		}

		@Override
		public int hashCode() {
			return value * 10 + suit;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null || this.getClass() != obj.getClass()) {
				return false;
			}
			CardStruct other = (CardStruct) obj;

			return (this.value == other.value && this.suit == other.suit);
		}
	}

	// * helper class -- card position structure

	private class CardPosition {
		public int top;
		public int left;
		public ImageView card;

		public CardPosition(int top, int left) {
			this.top = top;
			this.left = left;
			this.card = null;
		}
	}

	@Override
	public void onChatReceived(ChatEvent event) {

		// TODO Auto-generated method stub
		String sender = event.getSender();
		String message = event.getMessage();
		// System.out.println(message);
		if (!sender.equals(Utils.userName)) {
			String model;
			try {

				final JSONObject jsonObject = new JSONObject(message);
				String username = (String) jsonObject.get("username");
				model = (String) jsonObject.get("model");
				System.out.println(Utils.userName
						+ ": Message received, " + sender
						+ " sends " + message);

				if (username.equals(Utils.userName)) {

					if (model.equals("1")) {
						System.out.println(Utils.userName
								+ ": Message received from server! " + sender
								+ " sends " + message);

						this.status = (String) jsonObject.get("status"); // must
						// be
						// here.
						if (status.equals("5")) {
							// /////this part is used for information ""
							InformPart("It is your turn!");
							this.currentStatus = 5;
							Minvalue = (int) jsonObject.get("roundmin");
							this.Score = (int) jsonObject.get("currentscore");
							scorePart(this.Score);
							// System.out.println("minvalue is updated :"+Minvalue);
							//2014/11/23
							filterCards(Minvalue);
						}
						if (status.equals("3")) {

							// * mainview.newhand = tmpplayer;
							// * mainview.Resetcardsposition();

						}
						if (status.equals("20"))// ask player to add a new card
						{

							Client_part.this.runOnUiThread(new Runnable() {
								public void run() {
									Button dia_ok;
									
									final EditText edit;
									LayoutInflater mInflater = LayoutInflater
											.from(Client_part.this);
									ViewGroup rootView = (ViewGroup) mInflater
											.inflate(R.layout.popup, null);
									rootView.setLayoutParams(new LayoutParams(
											LayoutParams.WRAP_CONTENT,
											LayoutParams.WRAP_CONTENT));
									final Dialog dialog = new Dialog(
											Client_part.this);
									WindowManager.LayoutParams params = dialog
											.getWindow().getAttributes();
									params.width = WindowManager.LayoutParams.WRAP_CONTENT;
									params.height = WindowManager.LayoutParams.WRAP_CONTENT;
									dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
								
									dialog.setContentView(rootView);
									dia_ok = (Button) dialog
											.findViewById(R.id.ok_button);
									
									edit = (EditText) dialog
											.findViewById(R.id.coins_input);
									final TextView hint = (TextView)dialog.findViewById(R.id.hint_text);
									runOnUiThread(new Runnable() {

										@Override
										public void run() {

											// 2014/11/05
											// positionArray[i].card
											// = null
											hint.setText("How many gold coins do you bet (1 ~ "+Client_part.this.goldCoins+")");
											// cardView2.setVisibility(0);
										}

									});
									dia_ok.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											String input = edit.getText().toString().trim();
											if (input.equals("")) {
												Toast.makeText(Client_part.this, "Please choose a value between 1 and "+ Client_part.this.goldCoins, Toast.LENGTH_LONG).show();
											} 
											else if (Integer.valueOf(input) > 0 && Integer.valueOf(input) <= Client_part.this.goldCoins) {

												int remain = 0;
												for (int i = 0; i < MAX_NUM_CARDS; i++) {
													ImageView cardView = positionArray[i].card;
													if (cardView != null)
														remain += 1;
												}

												String tmpcard = null;
												try {
													tmpcard = (String) jsonObject.get("card");
												} catch (Exception e) {
													System.out.println(e
															.toString());
												}

												final int bonus_score = Utils
														.bonusScore(tmpcard);
												sendUpdateEvent("updateinformation",Integer.valueOf(edit.getText().toString())+ ";"+ bonus_score);// ////////5
												scorePart(Score);
												coinPart(goldCoins);
												
												InformPart("Game begin!");
												
												System.out.println(Utils.userName
																+ ": in onchatreceived(), status is 20, going to refill cards ");
												Client_part.this.refillCards(tmpcard);
																								
												dialog.cancel();
												createADialog(bonus_score);
											}
											else
											{
												Toast.makeText(Client_part.this, "Please choose a value between 1 and "+ Client_part.this.goldCoins, Toast.LENGTH_LONG).show();
											}
										}

									});
								
									dialog.setCanceledOnTouchOutside(false);
									int[] xy = calcPopupXY(rootView, rootView);

									dialog.getWindow().setGravity(
											Gravity.CENTER_HORIZONTAL
													| Gravity.CENTER_VERTICAL);
									params.x = xy[0];
									params.y = xy[1];

									clearCards(); 
									
									dialog.show();
								}
							});

						}

						// 2014/11/10
						if (status.equals("24"))
						{
							System.out.println("changed card is here.");
							Client_part.this.runOnUiThread(new Runnable() {
								public void run() {

									String tmpcard = null;
									try {
										tmpcard = (String) jsonObject
												.get("card");
										System.out
												.println("changed card: "
														+ tmpcard);
									} catch (Exception e) {
										System.out.println(e.toString());
									}

									Client_part.this.refillCards(tmpcard);
									System.out
											.println("changed card refilled!");

									filterCards(Minvalue);
									filterCards(Minvalue);
									filterCards(Minvalue);
									filterCards(Minvalue);
									filterCards(Minvalue);
								}
							});

						}

						if (status.equals("22")) {
							String tmpcard = (String) jsonObject.get("card");
							this.goldCoins = (int) jsonObject.get("goldcoin");
							coinPart(this.goldCoins);
							InformPart(tmpcard);
							if(tmpcard.equals(Utils.userName))
							{
								winAnimation();
							}
							else
							{
								loseAnimation();
							}
								
							
						}

						if (status.equals("23")) {
						/*	Client_part.this.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(Client_part.this, "QUIT!",
											Toast.LENGTH_LONG).show();

								}
							});*/
							try
							{
							Client_part.this.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									AlertDialog.Builder builder = new AlertDialog.Builder(Client_part.this);
									  
									  builder.setTitle("QUIT");
									  builder.setMessage("Server cancel the game!?")
									  .setCancelable(false)
									  .setPositiveButton("OK",new DialogInterface.OnClickListener() 
									  {
										  public void  onClick(DialogInterface dialog,int id) { //here you should end the game, return to screen ///send end message to all devices, include server and client..It is better to use a function...
												Intent intent = new Intent();
												intent.setClass(context, Waiting_room.class);
												context.startActivity(intent);
												((Activity) context).finish();
												theClient.disconnect();
										  } }) ; 
									  AlertDialog alert = builder.create(); 
									  alert.show(); 

								}
							});
							} catch (Exception e)
							{
								e.printStackTrace();
							}
							
						}
					}

				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	void sendUpdateEvent(String action, String sendcard) {
		try {
			JSONObject json = new JSONObject();
			Map<String, Object> map = new HashMap<String, Object>();
			json.put("model", "2");
			json.put("username", Utils.userName);
			if (action.equals("skip")) {
				json.put("action", "skip");
				json.put("card", null);
			} else if (action.equals("sendcard")) {
				json.put("action", "sendcard");
				json.put("card", sendcard);
			} else if (action.equals("updateinformation")) {
				json.put("action", "updateinformation");
				json.put("card", sendcard);
			} else if (action.equals("Finish")) {
				json.put("action", "Finish");
				json.put("card", sendcard);
			} else if (action.equals("QUIT")) {
				json.put("action", "QUIT");
				json.put("card", null);
			}else if (action.equals("Exchange")) {
				json.put("action", "Exchange");
				json.put("card", null);
			}
			System.out.println(json.toString());
			theClient.sendChat(json.toString());
		} catch (Exception e) {
			Log.d("sendUpdateEvent", e.getMessage());
		}
	}

	@Override
	public void onGameStarted(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameStopped(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMoveCompleted(MoveEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPrivateChatReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRoomCreated(RoomData arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRoomDestroyed(RoomData arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpdatePeersReceived(UpdateEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserChangeRoomProperty(RoomData arg0, String arg1,
			HashMap<String, Object> arg2, HashMap<String, String> arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserJoinedLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserJoinedRoom(RoomData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserLeftLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserLeftRoom(RoomData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserPaused(String arg0, boolean arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserResumed(String arg0, boolean arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetLiveRoomInfoDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onJoinRoomDone(RoomEvent event) {
		if (event.getResult() == WarpResponseResultCode.SUCCESS) {
			theClient.subscribeRoom(roomId);
		} else {
			Utils.showToastOnUIThread(this,
					"onJoinRoomDone: Failed " + event.getResult());
		}
	}

	@Override
	public void onLeaveRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLockPropertiesDone(byte arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSetCustomRoomDataDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribeRoomDone(RoomEvent event) {
		if (event.getResult() == WarpResponseResultCode.SUCCESS) {
			theClient.getLiveRoomInfo(roomId);
		} else {
			Utils.showToastOnUIThread(this, "onSubscribeRoomDone: Failed "
					+ event.getResult());
		}
	}

	@Override
	public void onUnSubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnlockPropertiesDone(byte arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpdatePropertyDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private class RunnableClass2 implements Runnable {
		private ImageView iv;
		private int mode; // 0 clear; 1 set filter
		RunnableClass2(ImageView iv, int mode) {
			this.iv = iv;
			this.mode = mode;
		}

		@Override
		public void run() {
			try {
				{if (mode == 1) {
					int tint = android.graphics.Color.parseColor("#99000000");
					PorterDuff.Mode mode = PorterDuff.Mode.SRC_ATOP;
					iv.setColorFilter(tint, mode);
				} else if (mode == 0) {
					iv.clearColorFilter();
				}}
			} catch (Exception e) {
				System.out.println("exception caught here");
			    System.err.println("Caught Exception: " + e.getMessage());
			}
		}

	}

	private class RunnableClass implements Runnable {
		private int i;
		private ImageView cv;
		private RelativeLayout mLayout;
		private Card currentCard;

		RunnableClass(int i, ImageView cv, RelativeLayout mLayout,
				Card currentCard) {
			this.i = i;
			this.cv = cv;
			this.mLayout = mLayout;
			this.currentCard = currentCard;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(Utils.userName + ": run() for " + i
					+ "th card position in runOnThread().");

			// 2014/11/10
			// view not deleted problem!!!!!!!!!!! delete after next!!!!!!!
			System.out.println(Utils.userName
					+ ": Before Removing cv from parent!");
			if (cv != null) {

				ViewGroup parent = (ViewGroup) cv.getParent();

				if (parent != null) {
					parent.removeView(cv);
					System.out.println(Utils.userName
							+ ": cv from parent is removed!");
				}

			}

			System.out.println(Utils.userName
					+ ": After Removing cv from parent!");

			mLayout.addView(cv);
			cv.setX(positionArray[i].left);
			cv.setY(positionArray[i].top);
			positionArray[i].card = cv;
			cv.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(final View view, final MotionEvent event) {

					// 2014/11/05
					int CardValueInGame = Utils.getCardValueinGame(currentCard
							.toString());
					if (currentStatus != 5) {
						InformPart("Please wait.");
					} else if (exchange_flag == 0 && CardValueInGame <= Minvalue) {
						sounds.play(wrongsound, 1.0f, 1.0f, 0, 0, 0.95f);
						InformPart("Try again!");

					} else if (gdt.onTouchEvent(event)) {

						Object tag = view.getTag();
						int id = tag == null ? -1 : Integer.parseInt(tag
								.toString());
						CardStruct cs = getCardStruct(id);

						TranslateAnimation anim = new TranslateAnimation(0, 0,
								0, -700);
						anim.setDuration(1000);
						anim.setFillEnabled(true);
						anim.setFillAfter(true);
						view.startAnimation(anim);

						sounds.play(swipesound, 1.0f, 4.0f, 0, 0, 0.95f);	
						cardList.remove(view);
						InformPart("Please wait!");
						
						
						if(exchange_flag == 1)
						{
							//2014/11/15
							sendUpdateEvent("Exchange", null);
							
							//2014/11/22
							goldCoins -= 5;
							coinPart(goldCoins);
							//2014/11/22s
							
							InformPart("Card exchanged. It is still your turn.");
							
						}
						else{
						sendUpdateEvent("sendcard", currentCard.toString());
						currentStatus = 0;
						}
						

						for (int i = 0; i < 5; i++) {
							ImageView card = positionArray[i].card;
							if (card != null) {
								Object tagPrime = card.getTag();
								int idPrime = tagPrime == null ? -1 : Integer
										.parseInt(tagPrime.toString());
								if (idPrime == id) {
									positionArray[i].card = null;
								}
							}
						}

						boolean isAllNull = true;
						for (int i = 0; i < 5; i++) {
							ImageView card = positionArray[i].card;
							if (card != null) {
								isAllNull = false;
							}
						}

						if (exchange_flag == 0 && isAllNull == true) {
							System.out
									.println(": no more cards, waiting for result.");
							InformPart("Waiting for result");
							sounds.play(triumphsound, 1.0f, 4.0f, 0, 0, 0.95f);
							sendUpdateEvent("Finish", null);
						}
						
						exchange_flag =0;
						
						
					}//get ontouch event
					return true;
				}
			});

			System.out.println(Utils.userName + ": run() for " + i
					+ "th card position in runOnThread() done!!!!!");

		}

	}

	private int[] calcPopupXY(View rootView, View anchor) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		rootView.measure(w, h);
		int popupWidth = rootView.getMeasuredWidth();
		int popupHeight = rootView.getMeasuredHeight();
		Rect anchorRect = getViewAbsoluteLocation(anchor);
		int x = anchorRect.left + (anchorRect.right - anchorRect.left) / 2
				- popupWidth / 2;
		int y = anchorRect.top - popupHeight;
		return new int[] { x, y };
	}

	public static Rect getViewAbsoluteLocation(View view) {
		if (view == null) {
			return new Rect();
		}
		// 获取View相对于屏幕的坐标
		int[] location = new int[2];
		view.getLocationOnScreen(location);// 这是获取相对于屏幕的绝对坐标，而view.getLocationInWindow(location);
											// 是获取window上的相对坐标，本例中只有一个window，二者等价
		// 获取View的宽高
		int width = view.getMeasuredWidth();
		int height = view.getMeasuredHeight();
		// 获取View的Rect
		Rect rect = new Rect();
		rect.left = location[0];
		rect.top = location[1];
		rect.right = rect.left + width;
		rect.bottom = rect.top + height;
		return rect;
	}
	private void createADialog(final int extra_score) {
		Client_part.this.runOnUiThread(new Runnable() {
			public void run() {
				
				final TextView bonus_score;
				final Button ok_button;
				LayoutInflater mInflater = LayoutInflater
						.from(Client_part.this);
				ViewGroup rootView = (ViewGroup) mInflater.inflate(
						R.layout.bonus_score, null);
				rootView.setLayoutParams(new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				final Dialog dialog = new Dialog(Client_part.this);
				WindowManager.LayoutParams params = dialog.getWindow()
						.getAttributes();
				params.width = WindowManager.LayoutParams.WRAP_CONTENT;
				params.height = WindowManager.LayoutParams.WRAP_CONTENT;
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setContentView(rootView);
				bonus_score = (TextView)dialog.findViewById(R.id.bonus_score);

				dialog.setCanceledOnTouchOutside(false);
				int[] xy = calcPopupXY(rootView, rootView);

				dialog.getWindow().setGravity(
						Gravity.CENTER_HORIZONTAL
								| Gravity.CENTER_VERTICAL);
				params.x = xy[0];
				params.y = xy[1];
				dialog.show();
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						int i = 0 ;
						String cong_string = "Congratulations!! \nYou get "+extra_score+" bonux points!!";
						for(i = 0; i < 10; i++)
						{
							bonus_score.setText(cong_string);
							if(i%2 == 0)
							bonus_score.setTextColor(Color.RED);
							else
							bonus_score.setTextColor(Color.GREEN);
					/*		runOnUiThread(new Runnable() {

								@Override
								public void run() {
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								});
						  */
						}
					}
				});
				ok_button = (Button)dialog.findViewById(R.id.button1);
				ok_button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});

				
			}
		});
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		// 2014/11/09 as well as client part

		
		  if (keyCode == KeyEvent.KEYCODE_BACK) { // your code
			  quit_function();
				Intent intent = new Intent();
				intent.setClass(context, Waiting_room.class);
				context.startActivity(intent);
				((Activity) context).finish();
				theClient.leaveRoom(roomId);
				theClient.unsubscribeRoom(roomId);
				theClient.disconnect();
				
		  }
		return super.onKeyDown(keyCode, event);

	}
	public void quit_function()
	{
		  AlertDialog.Builder builder = new AlertDialog.Builder(this);
		  
		  builder.setTitle("Back");
		  builder.setMessage("The game is running. Are you sure to quit!?")
		  .setCancelable(false)
		  .setPositiveButton("Yes,I quit..",new DialogInterface.OnClickListener() 
		  {
			  public void  onClick(DialogInterface dialog,int id) { //here you should end the game, return to screen ///send end message to all devices, include server and client..It is better to use a function...
					// Send delete room request
					
					sendUpdateEvent("QUIT", null);
					
					// Go back to waiting_room activity
					
					
			  } }) 
		  .setNegativeButton("Cancel",new
		  DialogInterface.OnClickListener() {
		  
		  @Override public void onClick(DialogInterface dialog, int which) { 
			  // TODO Auto-generated method stub
		  
		  } }); 
		  AlertDialog alert = builder.create(); 
		  alert.show(); 
	}
	private void quitGame() {

		
		// Go back to waiting_room activity
		Intent intent = new Intent();
		intent.setClass(context, Waiting_room.class);
		context.startActivity(intent);
		((Activity) context).finish();
	}
	private void loseAnimation()
	{
	
		Intent intent2 = new Intent();
	    intent2.setClass(Client_part.this, AnimatedSpritesExample.class);
	    startActivity(intent2);
	}
	private void winAnimation()
	{
	
		Intent intent2 = new Intent();
	    intent2.setClass(Client_part.this, Win.class);
	    startActivity(intent2);
	}
}