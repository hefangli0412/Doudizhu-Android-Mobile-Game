package com.example.cards;

//sound  effect no dear, buttons
//f0ce27 button color

import org.andengine.input.touch.TouchEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ListIterator;

import android.view.KeyEvent;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
// allplayersaddcards commented

import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.Toast;

import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.IModifier.IModifierListener;
import java.io.IOException;
import java.io.InputStream;

import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.cards.CardAlg.Card;
import com.example.cards.CardAlg.Deck;
import com.example.cards.CardAlg.Hand;
import com.example.cards.CardAlg.Table;
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

public class tablet_cards extends SimpleBaseGameActivity implements
IOnSceneTouchListener, RoomRequestListener, NotifyListener {

	private static final float DEMO_VELOCITY = 100.0f;

	static BitmapTextureAtlas mBitmapTextureAtlasc1, mBitmapTextureAtlas4,
	mBitmapTextureAtlasc2, mBitmapTextureAtlasc4,
	mBitmapTextureAtlasc3, mBitmapTextureAtlasc5,
	mBitmapTextureAtlasc6, mBitmapTextureAtlasc7,
	mBitmapTextureAtlasc8, mBitmapTextureAtlasc9,
	mBitmapTextureAtlasc10, mBitmapTextureAtlascj,
	mBitmapTextureAtlascq, mBitmapTextureAtlasck,
	mBitmapTextureAtlasd1, mBitmapTextureAtlasd2,
	mBitmapTextureAtlasd3, mBitmapTextureAtlasd4,
	mBitmapTextureAtlasd5, mBitmapTextureAtlasd6,
	mBitmapTextureAtlasd7, mBitmapTextureAtlasd8,
	mBitmapTextureAtlasd9, mBitmapTextureAtlasd10,
	mBitmapTextureAtlasdj, mBitmapTextureAtlasdq,
	mBitmapTextureAtlasdk, mBitmapTextureAtlash1,
	mBitmapTextureAtlash2, mBitmapTextureAtlash3,
	mBitmapTextureAtlash4, mBitmapTextureAtlash5,
	mBitmapTextureAtlash6, mBitmapTextureAtlassq,
	mBitmapTextureAtlassj, mBitmapTextureAtlass10,
	mBitmapTextureAtlass9, mBitmapTextureAtlass8,
	mBitmapTextureAtlass7, mBitmapTextureAtlass6,
	mBitmapTextureAtlass5, mBitmapTextureAtlass4,
	mBitmapTextureAtlass3, mBitmapTextureAtlass2,
	mBitmapTextureAtlass1, mBitmapTextureAtlashk,
	mBitmapTextureAtlashq, mBitmapTextureAtlashj,
	mBitmapTextureAtlash10, mBitmapTextureAtlash9,
	mBitmapTextureAtlash8, mBitmapTextureAtlash7,
	mBitmapTextureAtlassk;
	
	static TiledTextureRegion msj, mc2, mc4, mc3, mc5, mc6, mc7, mc8, mc9,
	mc10, mcq, mck, md1, md2, md3, md4, md5, md6, md9, md10, mdj, mcj,
	mc1, mh7, mh8, mh9, mh10, mhj, mhq, mhk, ms1, ms2, ms3, ms4, ms5,
	ms6, ms9, ms10, msq, msk, mh6, mh5, mh4, mh3, mh1, mh2, mdk, mdq,
	md7, md8, ms8, ms7;

	private String roomId = "";
	private static int CAMERA_WIDTH = 800;
	private static int CAMERA_HEIGHT = 480;

	static ITextureRegion mBackgroundTextureRegion, faceregion, buttonregion,
	monsterRegion1, monsterRegion2, monsterRegion3, monsterRegion4,
	signTextureRegion, okTexture0Region, okTexture3Region,
	scoreTexture0Region, scoreTexture3Region,
	playanotherTexture0Region, playanotherTexture3Region,
	backTexture0Region, backTexture3Region, greenTextureRegion,
	quitTextureRegion, instructTextureRegion, startTextureRegion,
	tagTextureRegion, paperTextureRegion, quit3TextureRegion,
	instruct3TextureRegion, start3TextureRegion, continueTextureRegion,
	continue3TextureRegion, monstersign1TextureRegion,
	monstersign2TextureRegion, goldTextureRegion,treeTextureRegion,ruleTextureRegion,darkTextureRegion,rule1TextureRegion,rule2TextureRegion,rule3TextureRegion, rule4TextureRegion,rule5TextureRegion,bubble1TextureRegion,bubble2TextureRegion;

	
	ITextureRegion halfTextureRegion,fullTextureRegion,arrowTextureRegion;
	ITextureRegion newgameTextureRegion,scorecontinueTextureRegion,endgameTextureRegion,scoresTextureRegion,backTextureRegion;
	
	Sprite backSprite,scoresSprite,newgameSprite,scorescontinueSprite,endgameSprite;
	
	static Sprite leftHalfSprite,rightHalfSprite,fullSprite;
	
	static MoveModifier move2right;
	static MoveModifier move2left;
	static IModifierListener<IEntity> afterdis;
	
	static Text winnote,scoreline1,scoreline2,nameline1,nameline2,goldline1,goldline2;
	
	// 2014/11/06
	public Sprite signSprite, tagSprite, tag2Sprite, tag3Sprite, tag4Sprite,
	greenSprite, paperSprite, paperbackSprite, paperback3Sprite,
	monstersign1Sprite, monstersign2Sprite, lmonsterSprite,
	rmonsterSprite, lgoldSprite, rgoldSprite,treeSprite,ruleSprite,darkSprite,rule1Sprite,rule2Sprite,rule4Sprite,rule5Sprite,rule3Sprite,bubble1Sprite,bubble2Sprite,lmonsterSprite2,rmonsterSprite2, lgoldSprite2, rgoldSprite2;
	
	public ButtonSprite ok0Sprite, ok3Sprite, playanother0Sprite,
	playanother3Sprite, score0Sprite, score3Sprite, back0Sprite,
	back3Sprite, quitSprite, startSprite, instructSprite, quit3Sprite,
	start3Sprite, instruct3Sprite, continueSprite, continue3Sprite;

	BluetoothAdapter mBluetoothAdapter;
	private WarpClient theClient;
	static Scene mMainScene;
	private Random ramdom = new Random();
	private HashMap<String, User> userMap = new HashMap<String, User>();
	private HashMap<String, Sprite> objectMap = new HashMap<String, Sprite>();
	static ArrayList<String> PlayerNames = new ArrayList<String>();
	private int tmpcount = 0;
	private Context context;
	private ArrayList<Hand> players = new ArrayList<Hand>();
	private Deck deck;
	private Table table;
	public static boolean isMessageReceivedFromPlayer = false;
	public static boolean isSkipMessageFromPlayer = false;
	public static Card2 CardSelectedFromPlayer = null;
	static Hand2 currentPlayer = null;
	static Hand2 lastPlayer = null;
	private int skipCount;
	public String currPlayer;
	
	public Sound bsound;
	public Sound skipsound;
	public Sound swipesound;
	public Sound triumph;
	public Sound wrongcard;
	public Sound monstersound;

	public Music bmusic;

	public ArrayList<ITextureRegion> choosedmonsters = new ArrayList<ITextureRegion>();
	public ArrayList<String> choosedmonsternames = new ArrayList<String>();

	public Font mFont2;
	public static int changetime = 0;

	static ArrayList<String> playerList;
	static SevenFiveTwoThreeGame2 game;

	static BitmapTextureAtlas mCardTextureAtlas;// card backs images
	static TiledTextureRegion mCardTexture;

	public static IFont mFont;

	static int current_value = 0;
	static String cur_player = null;
	static ArrayList<Object> deletelist = new ArrayList<Object>();
	static ArrayList<Object> deletelist2 = new ArrayList<Object>();
	static ArrayList<Card2> card2d = new ArrayList<Card2>();

	static boolean stop = false;
	static int initial = 0;

	static int[] lastscore;
	static int[] lastgoldcoins;

	public static Font komika;
	public String servername = "";

	public BitmapTextureAtlas mAutoParallaxBackgroundTexture;
	public ITextureRegion mParallaxLayerBack;
	public ITextureRegion mParallaxLayerMid;
	public ITextureRegion mParallaxLayerFront;

	public static Text score1, score2, bet1,bet2,headgold1,headgold2;
	
	//int nextbet=0;
	int betdetached=0;

	@Override
	public EngineOptions onCreateEngineOptions() {
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		try {
			theClient = WarpClient.getInstance();
			context = this;
		} catch (Exception e) {
			e.printStackTrace();
		}

		EngineOptions en = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(
						CAMERA_WIDTH, CAMERA_HEIGHT), camera);
		en.getAudioOptions().setNeedsSound(true);
		en.getAudioOptions().setNeedsMusic(true);
		return en;
	}

	@Override
	protected void onCreateResources() {
		try {
			
			
			ITexture halfBackgroundTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/half.png");/////////
						}
					});
			
			halfBackgroundTexture.load();
			this.halfTextureRegion = TextureRegionFactory
					.extractFromTexture(halfBackgroundTexture);
			
			ITexture fullBackgroundTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/full.png");/////////
						}
					});
			
			fullBackgroundTexture.load();
			this.fullTextureRegion = TextureRegionFactory
					.extractFromTexture(fullBackgroundTexture);
			
			
			ITexture arrowTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/arrow.png");/////////
						}
					});
			
			arrowTexture.load();
			this.arrowTextureRegion = TextureRegionFactory
					.extractFromTexture(arrowTexture);
			
			
			
			
			ITexture scoresTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/scores2.png");/////////
						}
					});
			
			scoresTexture.load();
			this.scoresTextureRegion = TextureRegionFactory
					.extractFromTexture(scoresTexture);
			
			ITexture endgameTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/endgame2.png");/////////
						}
					});
			
			endgameTexture.load();
			this.endgameTextureRegion = TextureRegionFactory
					.extractFromTexture(endgameTexture);
			
			ITexture newgameTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/newgame2.png");/////////
						}
					});
			
			newgameTexture.load();
			this.newgameTextureRegion = TextureRegionFactory
					.extractFromTexture(newgameTexture);
			
			ITexture scorecontinueTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/continue2.png");/////////
						}
					});
			
			scorecontinueTexture.load();
			this.scorecontinueTextureRegion = TextureRegionFactory
					.extractFromTexture(scorecontinueTexture);
			
			ITexture backTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/back2.png");/////////
						}
					});
			
			backTexture.load();
			this.backTextureRegion = TextureRegionFactory
					.extractFromTexture(backTexture);

			final ITexture komikaTexture = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			FontFactory.setAssetBasePath("font/");
			this.komika = FontFactory.createFromAsset(this.getFontManager(),
					komikaTexture, this.getAssets(), "komika.ttf", 30f, true,
					Color.WHITE_ABGR_PACKED_INT);
			this.komika.load();
			
			ITexture bubble1Texture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/bubble1.png");
				}
			});

			bubble1Texture.load();
			tablet_cards.bubble1TextureRegion = TextureRegionFactory
					.extractFromTexture(bubble1Texture);
			
			ITexture bubble2Texture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/bubble2.png");
				}
			});

			bubble2Texture.load();
			tablet_cards.bubble2TextureRegion = TextureRegionFactory
					.extractFromTexture(bubble2Texture);

			ITexture signTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/sign4.png");
				}
			});

			signTexture.load();
			tablet_cards.signTextureRegion = TextureRegionFactory
					.extractFromTexture(signTexture);

			ITexture playanotherTexture0 = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/playanother0.png");
						}
					});

			playanotherTexture0.load();
			tablet_cards.playanotherTexture0Region = TextureRegionFactory
					.extractFromTexture(playanotherTexture0);

			ITexture playanotherTexture3 = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/playanother3.png");
						}
					});

			playanotherTexture3.load();
			tablet_cards.playanotherTexture3Region = TextureRegionFactory
					.extractFromTexture(playanotherTexture3);

			ITexture okTexture0 = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/ok0.png");
				}
			});

			okTexture0.load();
			tablet_cards.okTexture0Region = TextureRegionFactory
					.extractFromTexture(okTexture0);

			ITexture okTexture3 = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/ok3.png");
				}
			});

			okTexture3.load();
			tablet_cards.okTexture3Region = TextureRegionFactory
					.extractFromTexture(okTexture3);

			ITexture scoreTexture0 = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/scores0.png");
						}
					});

			scoreTexture0.load();
			tablet_cards.scoreTexture0Region = TextureRegionFactory
					.extractFromTexture(scoreTexture0);

			ITexture scoreTexture3 = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/scores3.png");
						}
					});

			scoreTexture3.load();
			tablet_cards.scoreTexture3Region = TextureRegionFactory
					.extractFromTexture(scoreTexture3);

			ITexture backTexture0 = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/back0.png");
				}
			});

			backTexture0.load();
			tablet_cards.backTexture0Region = TextureRegionFactory
					.extractFromTexture(backTexture0);

			ITexture backTexture3 = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/back3.png");
				}
			});

			backTexture3.load();
			tablet_cards.backTexture3Region = TextureRegionFactory
					.extractFromTexture(backTexture3);

			ITexture continueTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/continue.png");
						}
					});

			continueTexture.load();
			tablet_cards.continueTextureRegion = TextureRegionFactory
					.extractFromTexture(continueTexture);

			ITexture continue3Texture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/continue3.png");
						}
					});

			continue3Texture.load();
			tablet_cards.continue3TextureRegion = TextureRegionFactory
					.extractFromTexture(continue3Texture);

			ITexture quitTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/quit.png");
				}
			});

			quitTexture.load();
			tablet_cards.quitTextureRegion = TextureRegionFactory
					.extractFromTexture(quitTexture);

			ITexture quit3Texture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/quit3.png");
				}
			});

			quit3Texture.load();
			tablet_cards.quit3TextureRegion = TextureRegionFactory
					.extractFromTexture(quit3Texture);

			ITexture startTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/start.png");
				}
			});

			startTexture.load();
			tablet_cards.startTextureRegion = TextureRegionFactory
					.extractFromTexture(startTexture);

			ITexture start3Texture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/start3.png");
						}
					});

			start3Texture.load();
			tablet_cards.start3TextureRegion = TextureRegionFactory
					.extractFromTexture(start3Texture);

			ITexture instructTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/instructions.png");
						}
					});

			instructTexture.load();
			tablet_cards.instructTextureRegion = TextureRegionFactory
					.extractFromTexture(instructTexture);

			ITexture instruct3Texture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/instructions3.png");
						}
					});

			instruct3Texture.load();
			tablet_cards.instruct3TextureRegion = TextureRegionFactory
					.extractFromTexture(instruct3Texture);
			
			
			ITexture darkTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/dark.png");
						}
					});

			darkTexture.load();
			tablet_cards.darkTextureRegion = TextureRegionFactory
					.extractFromTexture(darkTexture);
			
			
			ITexture ruleTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/rules.png");
						}
					});

			ruleTexture.load();
			tablet_cards.ruleTextureRegion = TextureRegionFactory
					.extractFromTexture(ruleTexture);
			
			ITexture rule1Texture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/rule111.png");
						}
					});

			rule1Texture.load();
			tablet_cards.rule1TextureRegion = TextureRegionFactory
					.extractFromTexture(rule1Texture);
			
			
			ITexture rule2Texture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/rule222.png");
						}
					});

			rule2Texture.load();
			tablet_cards.rule2TextureRegion = TextureRegionFactory
					.extractFromTexture(rule2Texture);
			
			
			ITexture rule3Texture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/rule333.png");
						}
					});

			rule3Texture.load();
			tablet_cards.rule3TextureRegion = TextureRegionFactory
					.extractFromTexture(rule3Texture);
			
			
			

			ITexture rule4Texture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/rule444.png");
						}
					});

			rule4Texture.load();
			tablet_cards.rule4TextureRegion = TextureRegionFactory
					.extractFromTexture(rule4Texture);
			
			

			ITexture rule5Texture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/rule555.png");
						}
					});

			rule5Texture.load();
			tablet_cards.rule5TextureRegion = TextureRegionFactory
					.extractFromTexture(rule5Texture);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	
			ITexture treeTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/tree3.png");
						}
					});

			treeTexture.load();
			tablet_cards.treeTextureRegion = TextureRegionFactory
					.extractFromTexture(treeTexture);
					

			ITexture tagTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/tag.png");
				}
			});

			tagTexture.load();
			tablet_cards.tagTextureRegion = TextureRegionFactory
					.extractFromTexture(tagTexture);

			// 2014/11/13
			ITexture monstersign1Texture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/monstersign.png");
						}
					});

			monstersign1Texture.load();
			tablet_cards.monstersign1TextureRegion = TextureRegionFactory
					.extractFromTexture(monstersign1Texture);

			// 2014/11/13
			ITexture monstersign2Texture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/monstersign2.png");
						}
					});

			monstersign2Texture.load();
			tablet_cards.monstersign2TextureRegion = TextureRegionFactory
					.extractFromTexture(monstersign2Texture);

			ITexture goldTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/money.png");
				}
			});

			goldTexture.load();
			tablet_cards.goldTextureRegion = TextureRegionFactory
					.extractFromTexture(goldTexture);

			ITexture backgroundTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/table6.png");
						}
					});

			backgroundTexture.load();
			tablet_cards.mBackgroundTextureRegion = TextureRegionFactory
					.extractFromTexture(backgroundTexture);

			ITexture greenTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/bg4.png");
				}
			});

			greenTexture.load();
			tablet_cards.greenTextureRegion = TextureRegionFactory
					.extractFromTexture(greenTexture);

			ITexture paperTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/note2.png");
				}
			});

			paperTexture.load();
			tablet_cards.paperTextureRegion = TextureRegionFactory
					.extractFromTexture(paperTexture);

			ITexture faceTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/face1.png");
				}
			});

			faceTexture.load();
			tablet_cards.faceregion = TextureRegionFactory
					.extractFromTexture(faceTexture);

			ITexture monsterTexture1 = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/monster1.png");
						}
					});

			monsterTexture1.load();
			tablet_cards.monsterRegion1 = TextureRegionFactory
					.extractFromTexture(monsterTexture1);

			ITexture monsterTexture2 = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/monster2.png");
						}
					});

			monsterTexture2.load();
			tablet_cards.monsterRegion2 = TextureRegionFactory
					.extractFromTexture(monsterTexture2);

			ITexture monsterTexture3 = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/monster3.png");
						}
					});

			monsterTexture3.load();
			tablet_cards.monsterRegion3 = TextureRegionFactory
					.extractFromTexture(monsterTexture3);

			ITexture monsterTexture4 = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/monster4.png");
						}
					});

			monsterTexture4.load();
			tablet_cards.monsterRegion4 = TextureRegionFactory
					.extractFromTexture(monsterTexture4);

			ITexture buttonTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/button2.png");
						}
					});

			buttonTexture.load();
			tablet_cards.buttonregion = TextureRegionFactory
					.extractFromTexture(buttonTexture);

			this.mFont = FontFactory.create(this.getFontManager(),
					this.getTextureManager(), 256, 256,
					Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
			this.mFont.load();

			this.mFont2 = FontFactory.create(this.getFontManager(),
					this.getTextureManager(), 256, 256,
					TextureOptions.BILINEAR,
					Typeface.create(Typeface.DEFAULT, Typeface.ITALIC), 48,
					Color.WHITE.hashCode());
			this.mFont2.load();

			// 2014/11/13 for moving background
			// is greensprite attached or detach in the end?
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
			// 2014/11/13 for moving background
			this.mAutoParallaxBackgroundTexture = new BitmapTextureAtlas(
					this.getTextureManager(), 1024, 1024);
			this.mParallaxLayerFront = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(this.mAutoParallaxBackgroundTexture, this,
							"parallax_background_layer_front.png", 0, 0);
			this.mParallaxLayerBack = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(this.mAutoParallaxBackgroundTexture, this,
							"parallax_background_layer_back.png", 0, 188);
			this.mParallaxLayerMid = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(this.mAutoParallaxBackgroundTexture, this,
							"parallax_background_layer_mid.png", 0, 669);
			this.mAutoParallaxBackgroundTexture.load();

			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/2x/");// 64,32
			// maybe these are wrong
			mBitmapTextureAtlasc1 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mc1 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasc1, this, "Clubs Ace.png", 0, 0, 1, 1);
			mBitmapTextureAtlasc1.load();

			mBitmapTextureAtlasc2 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mc2 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasc2, this, "Clubs 2.png", 0, 0, 1, 1);
			mBitmapTextureAtlasc2.load();

			mBitmapTextureAtlasc3 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mc3 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasc3, this, "Clubs 3.png", 0, 0, 1, 1);
			mBitmapTextureAtlasc3.load();

			mBitmapTextureAtlasc4 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mc4 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasc4, this, "Clubs 4.png", 0, 0, 1, 1);
			mBitmapTextureAtlasc4.load();

			mBitmapTextureAtlasc5 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mc5 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasc5, this, "Clubs 5.png", 0, 0, 1, 1);
			mBitmapTextureAtlasc5.load();

			mBitmapTextureAtlasc6 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mc6 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasc6, this, "Clubs 6.png", 0, 0, 1, 1);
			mBitmapTextureAtlasc6.load();

			mBitmapTextureAtlasc7 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mc7 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasc7, this, "Clubs 7.png", 0, 0, 1, 1);
			mBitmapTextureAtlasc7.load();

			mBitmapTextureAtlasc8 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mc8 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasc8, this, "Clubs 8.png", 0, 0, 1, 1);
			mBitmapTextureAtlasc8.load();

			mBitmapTextureAtlasc9 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mc9 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasc9, this, "Clubs 9.png", 0, 0, 1, 1);
			mBitmapTextureAtlasc9.load();

			mBitmapTextureAtlasc10 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mc10 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasc10, this, "Clubs 10.png", 0, 0, 1, 1);
			mBitmapTextureAtlasc10.load();

			mBitmapTextureAtlascj = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mcj = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlascj, this, "Clubs Jack.png", 0, 0, 1, 1);
			mBitmapTextureAtlascj.load();

			mBitmapTextureAtlascq = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mcq = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlascq, this, "Clubs Queen.png", 0, 0, 1, 1);
			mBitmapTextureAtlascq.load();

			mBitmapTextureAtlasck = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mck = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasck, this, "Clubs King.png", 0, 0, 1, 1);
			mBitmapTextureAtlasck.load();

			mBitmapTextureAtlasd1 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			md1 = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(mBitmapTextureAtlasd1, this,
							"Diamonds Ace.png", 0, 0, 1, 1);
			mBitmapTextureAtlasd1.load();

			mBitmapTextureAtlasd2 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			md2 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasd2, this, "Diamonds 2.png", 0, 0, 1, 1);
			mBitmapTextureAtlasd2.load();

			mBitmapTextureAtlasd3 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			md3 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasd3, this, "Diamonds 3.png", 0, 0, 1, 1);
			mBitmapTextureAtlasd3.load();

			mBitmapTextureAtlasd4 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			md4 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasd4, this, "Diamonds 4.png", 0, 0, 1, 1);
			mBitmapTextureAtlasd4.load();

			mBitmapTextureAtlasd5 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			md5 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasd5, this, "Diamonds 5.png", 0, 0, 1, 1);
			mBitmapTextureAtlasd5.load();

			mBitmapTextureAtlasd6 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			md6 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasd6, this, "Diamonds 6.png", 0, 0, 1, 1);
			mBitmapTextureAtlasd6.load();

			mBitmapTextureAtlasd7 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			md7 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasd7, this, "Diamonds 7.png", 0, 0, 1, 1);
			mBitmapTextureAtlasd7.load();

			mBitmapTextureAtlasd8 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			md8 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasd8, this, "Diamonds 8.png", 0, 0, 1, 1);
			mBitmapTextureAtlasd8.load();

			mBitmapTextureAtlasd9 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			md9 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasd9, this, "Diamonds 9.png", 0, 0, 1, 1);
			mBitmapTextureAtlasd9.load();

			mBitmapTextureAtlasd10 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			md10 = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(mBitmapTextureAtlasd10, this,
							"Diamonds 10.png", 0, 0, 1, 1);
			mBitmapTextureAtlasd10.load();

			mBitmapTextureAtlasdj = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mdj = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasdj, this, "Diamonds Jack.png", 0, 0, 1,
					1);
			mBitmapTextureAtlasdj.load();

			mBitmapTextureAtlasdq = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mdq = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasdq, this, "Diamonds Queen.png", 0, 0, 1,
					1);
			mBitmapTextureAtlasdq.load();

			mBitmapTextureAtlasdk = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mdk = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlasdk, this, "Diamonds King.png", 0, 0, 1,
					1);
			mBitmapTextureAtlasdk.load();

			mBitmapTextureAtlash1 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mh1 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlash1, this, "Hearts Ace.png", 0, 0, 1, 1);
			mBitmapTextureAtlash1.load();

			mBitmapTextureAtlash2 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mh2 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlash2, this, "Hearts 2.png", 0, 0, 1, 1);
			mBitmapTextureAtlash2.load();

			mBitmapTextureAtlash3 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mh3 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlash3, this, "Hearts 3.png", 0, 0, 1, 1);
			mBitmapTextureAtlash3.load();

			mBitmapTextureAtlash4 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mh4 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlash4, this, "Hearts 4.png", 0, 0, 1, 1);
			mBitmapTextureAtlash4.load();

			mBitmapTextureAtlash5 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mh5 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlash5, this, "Hearts 5.png", 0, 0, 1, 1);
			mBitmapTextureAtlash5.load();

			mBitmapTextureAtlash6 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mh6 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlash6, this, "Hearts 6.png", 0, 0, 1, 1);
			mBitmapTextureAtlash6.load();

			mBitmapTextureAtlash7 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mh7 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlash7, this, "Hearts 7.png", 0, 0, 1, 1);
			mBitmapTextureAtlash7.load();

			mBitmapTextureAtlash8 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mh8 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlash8, this, "Hearts 8.png", 0, 0, 1, 1);
			mBitmapTextureAtlash8.load();

			mBitmapTextureAtlash9 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mh9 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlash9, this, "Hearts 9.png", 0, 0, 1, 1);
			mBitmapTextureAtlash9.load();

			mBitmapTextureAtlash10 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mh10 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlash10, this, "Hearts 10.png", 0, 0, 1, 1);
			mBitmapTextureAtlash10.load();

			mBitmapTextureAtlashj = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mhj = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlashj, this, "Hearts Jack.png", 0, 0, 1, 1);
			mBitmapTextureAtlashj.load();

			mBitmapTextureAtlashq = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mhq = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(mBitmapTextureAtlashq, this,
							"Hearts Queen.png", 0, 0, 1, 1);
			mBitmapTextureAtlashq.load();

			mBitmapTextureAtlashk = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mhk = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlashk, this, "Hearts King.png", 0, 0, 1, 1);
			mBitmapTextureAtlashk.load();

			mBitmapTextureAtlass1 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			ms1 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlass1, this, "Hearts Ace.png", 0, 0, 1, 1);
			mBitmapTextureAtlass1.load();

			mBitmapTextureAtlass2 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			ms2 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlass2, this, "Spades 2.png", 0, 0, 1, 1);
			mBitmapTextureAtlass2.load();

			mBitmapTextureAtlass3 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			ms3 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlass3, this, "Spades 3.png", 0, 0, 1, 1);
			mBitmapTextureAtlass3.load();

			mBitmapTextureAtlass4 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			ms4 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlass4, this, "Spades 4.png", 0, 0, 1, 1);
			mBitmapTextureAtlass4.load();

			mBitmapTextureAtlass5 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			ms5 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlass5, this, "Spades 5.png", 0, 0, 1, 1);
			mBitmapTextureAtlass5.load();

			mBitmapTextureAtlass6 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			ms6 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlass6, this, "Spades 6.png", 0, 0, 1, 1);
			mBitmapTextureAtlass6.load();

			mBitmapTextureAtlass7 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			ms7 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlass7, this, "Spades 7.png", 0, 0, 1, 1);
			mBitmapTextureAtlass7.load();

			mBitmapTextureAtlass8 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			ms8 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlass8, this, "Spades 8.png", 0, 0, 1, 1);
			mBitmapTextureAtlass8.load();

			mBitmapTextureAtlass9 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			ms9 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlass9, this, "Spades 9.png", 0, 0, 1, 1);
			mBitmapTextureAtlass9.load();

			mBitmapTextureAtlass10 = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			ms10 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlass10, this, "Spades 10.png", 0, 0, 1, 1);
			mBitmapTextureAtlass10.load();

			mBitmapTextureAtlassj = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			msj = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlassj, this, "Spades Jack.png", 0, 0, 1, 1);
			mBitmapTextureAtlassj.load();

			mBitmapTextureAtlassq = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			msq = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(mBitmapTextureAtlassq, this,
							"Spades Queen.png", 0, 0, 1, 1);
			mBitmapTextureAtlassq.load();

			mBitmapTextureAtlassk = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			msk = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
					mBitmapTextureAtlassk, this, "Spades King.png", 0, 0, 1, 1);
			mBitmapTextureAtlassk.load();

			mCardTextureAtlas = new BitmapTextureAtlas(
					this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
			mCardTexture = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(this.mCardTextureAtlas, this,
							"flowerback.png", 0, 0, 1, 1);
			mCardTextureAtlas.load();

			playerList = new ArrayList<String>();
			Intent intent = getIntent();
			roomId = intent.getStringExtra("roomId");
			init(roomId);
			
			SoundFactory.setAssetBasePath("snd/");
			bsound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), this, "bubble.ogg");
			skipsound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), this, "skip.ogg");
			swipesound = SoundFactory.createSoundFromAsset(
					//this.mEngine.getSoundManager(), this, "swipe.ogg");
					this.mEngine.getSoundManager(), this, "swipe.mp3");
			triumph = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), this, "triumph.ogg");
			wrongcard = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), this, "no_dear.ogg");
			monstersound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), this, "enter.mp3");
			
			//12/01/2014
			MusicFactory.setAssetBasePath("mfx/");
			try {
				this.bmusic = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(), this, "music.mp3");
				this.bmusic.setLooping(true);
			} catch (final IOException e) {
				Debug.e(e);
			}

		} catch (IOException e) {
			Debug.e(e);
		}
	}

	@Override
	protected Scene onCreateScene() {
		
		//2014/12/01
		if(!tablet_cards.this.bmusic.isPlaying()) {
			tablet_cards.this.bmusic.play();
		}
		
		
		tablet_cards.mMainScene = new Scene();
		

		final VertexBufferObjectManager vob = this
				.getVertexBufferObjectManager();
		final tablet_cards curact = this;
		
		final tablet_cards thisact = this;
		final VertexBufferObjectManager thisvbom = this
				.getVertexBufferObjectManager();
		
		leftHalfSprite=new Sprite(0,0,this.halfTextureRegion, getVertexBufferObjectManager());//////
		rightHalfSprite=new Sprite(400,0,this.halfTextureRegion, getVertexBufferObjectManager());////////
		fullSprite=new Sprite(0,0,this.fullTextureRegion, getVertexBufferObjectManager());/////
		fullSprite.setAlpha(0.5f);
		leftHalfSprite.setAlpha(0.5f);
		rightHalfSprite.setAlpha(0.5f);
		
		move2right=new MoveModifier(1.0f,-500,0,0,0);
		move2left=new MoveModifier(1.0f,900,400,0,0);
		afterdis = new IModifierListener<IEntity>() {
			@Override
			public void onModifierStarted(IModifier<IEntity> pModifier,
					IEntity pItem) {
			}

			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier,
					IEntity pItem) {
				leftHalfSprite.detachSelf();
				rightHalfSprite.detachSelf();
				tablet_cards.mMainScene.attachChild(fullSprite);
				
				tablet_cards.mMainScene.attachChild(winnote);
				tablet_cards.mMainScene.attachChild(scoresSprite);
				tablet_cards.mMainScene.attachChild(endgameSprite);
				tablet_cards.mMainScene.attachChild(newgameSprite);
				tablet_cards.mMainScene.attachChild(scorescontinueSprite);
				
				tablet_cards.mMainScene.registerTouchArea(scoresSprite);
				tablet_cards.mMainScene.registerTouchArea(endgameSprite);
				tablet_cards.mMainScene.registerTouchArea(newgameSprite);
				tablet_cards.mMainScene.registerTouchArea(scorescontinueSprite);
			}
		};
		move2left.addModifierListener(afterdis);////////////////////maybe this is not correct!!!
		
		
		scoresSprite=new Sprite(270,180,this.scoresTextureRegion, getVertexBufferObjectManager()){
			MoveModifier move2l,move2r,bmove2r;
			
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionUp()) {
					bsound.play();
					
					move2r=new MoveModifier(1.0f,-500,0,0,0);
					move2l=new MoveModifier(1.0f,900,400,0,0);
					bmove2r=new MoveModifier(1.0f,-450,50,380,380);
					
					tablet_cards.this.runOnUpdateThread(new Runnable() {
						@Override
						public void run() {
							mMainScene.unregisterTouchArea(scoresSprite);
							mMainScene.unregisterTouchArea(endgameSprite);
							mMainScene.unregisterTouchArea(newgameSprite);
							mMainScene.unregisterTouchArea(scorescontinueSprite);
							winnote.detachSelf();
							scoresSprite.detachSelf();
							endgameSprite.detachSelf();
							newgameSprite.detachSelf();
							scorescontinueSprite.detachSelf();
							
							fullSprite.detachSelf();
							
							
							leftHalfSprite.registerEntityModifier(move2r);
							rightHalfSprite.registerEntityModifier(move2l);
							mMainScene.attachChild(leftHalfSprite);
							mMainScene.attachChild(rightHalfSprite);
							
							backSprite.registerEntityModifier(bmove2r);
							mMainScene.registerTouchArea(backSprite);
							mMainScene.attachChild(backSprite);	
							
							
							lmonsterSprite2.registerEntityModifier(new MoveModifier(1.0f,-450,50,100,100));
							mMainScene.attachChild(lmonsterSprite2); //attach left score1 text
							rmonsterSprite2.registerEntityModifier(new MoveModifier(1.0f,980,480,100,100));
							mMainScene.attachChild(rmonsterSprite2);
							
//							lgoldSprite2.registerEntityModifier(move2r);
//							mMainScene.attachChild(lgoldSprite2); //attach left score1 text
//							rgoldSprite2.registerEntityModifier(move2l);
//							mMainScene.attachChild(rgoldSprite2); //attach left score1 text
					
							int playerscore1=tablet_cards.game.players.get(0).getScore() + tablet_cards.lastscore[0];
							int playerscore2=tablet_cards.game.players.get(1).getScore() + tablet_cards.lastscore[1];
							String playername1=tablet_cards.game.players.get(0).getPlayerName();
							playername1=playername1.substring(0,playername1.lastIndexOf("_"));
							String playername2=tablet_cards.game.players.get(1).getPlayerName();
							playername2=playername2.substring(0,playername2.lastIndexOf("_"));
							int playergold1=lastgoldcoins[0];
							int playergold2=lastgoldcoins[1];
							
							nameline1 = new Text(0, 0, komika, playername1,getVertexBufferObjectManager());
							nameline1.registerEntityModifier(new MoveModifier(1.0f,-450,50,180,180));
							mMainScene.attachChild(nameline1);
							
							nameline2=new Text(0, 0, komika, playername2,getVertexBufferObjectManager());
							nameline2.registerEntityModifier(new MoveModifier(1.0f,980,480,180,180));
							mMainScene.attachChild(nameline2);
							
							scoreline1=new Text(0,0, komika, "accumulated score: "+playerscore1,getVertexBufferObjectManager());
							scoreline1.registerEntityModifier(new MoveModifier(1.0f,-450,50,220,220));
							mMainScene.attachChild(scoreline1);
							
							scoreline2=new Text(0,0, komika, "accumulated score: "+playerscore2,getVertexBufferObjectManager());
							scoreline2.registerEntityModifier(new MoveModifier(1.0f,980,480,220,220));
							mMainScene.attachChild(scoreline2);
							
							
							goldline1=new Text(0,0, komika, "coins: "+playergold1,getVertexBufferObjectManager());
							goldline1.registerEntityModifier(new MoveModifier(1.0f,-450,50,260,260));
							mMainScene.attachChild(goldline1);
							
							goldline2=new Text(0,0, komika, "coins: "+playergold2,getVertexBufferObjectManager());
							goldline2.registerEntityModifier(new MoveModifier(1.0f,980,480,260,260));
							mMainScene.attachChild(goldline2);			
						}
						});
					
					
					
					
				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}	
		};
		
		backSprite=new Sprite(50,380,this.backTextureRegion, getVertexBufferObjectManager()){
			
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pTouchEvent.isActionUp()) {
					bsound.play();
					
					tablet_cards.this.runOnUpdateThread(new Runnable() {
						@Override
						public void run() {
							mMainScene.unregisterTouchArea(backSprite);
							
							//2014/11/30
							mMainScene.detachChild(nameline1); //attach left score1 text
							mMainScene.detachChild(nameline2);
							mMainScene.detachChild(scoreline1); //attach left score1 text
							mMainScene.detachChild(scoreline2); //attach left score1 text
							mMainScene.detachChild(goldline1); //attach left score1 text
							mMainScene.detachChild(goldline2); //attach left score1 text
							mMainScene.detachChild(lmonsterSprite2);
							mMainScene.detachChild(rmonsterSprite2);
//							mMainScene.detachChild(lgoldSprite2);
//							mMainScene.detachChild(rgoldSprite2);
							
							backSprite.detachSelf();
							leftHalfSprite.detachSelf();
							rightHalfSprite.detachSelf();
							
							leftHalfSprite.registerEntityModifier(move2right.deepCopy());
							MoveModifier move2L=new MoveModifier(1.0f,900,400,0,0);
							move2L.addModifierListener(afterdis);
							rightHalfSprite.registerEntityModifier(move2L);
							mMainScene.attachChild(leftHalfSprite);
							mMainScene.attachChild(rightHalfSprite);		
						}
						});	
				}
				
				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,pTouchAreaLocalY);
			}

		};
		
		
		
		endgameSprite=new Sprite(270,220,this.endgameTextureRegion, getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
				if (pTouchEvent.isActionUp()) {
					bsound.play();
					quit_function();
				}
				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,pTouchAreaLocalY);
			}
		};	
		newgameSprite=new Sprite(270,260,this.newgameTextureRegion, getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionUp()) {
					bsound.play();
					tablet_cards.this.runOnUpdateThread(new Runnable() {
						@Override
						public void run() {
							if(!scoresSprite.hasParent()){ return;}
							mMainScene.unregisterTouchArea(scoresSprite);
							mMainScene.unregisterTouchArea(endgameSprite);
							mMainScene.unregisterTouchArea(newgameSprite);
							mMainScene.unregisterTouchArea(scorescontinueSprite);
							winnote.detachSelf();
							scoresSprite.detachSelf();
							endgameSprite.detachSelf();
							newgameSprite.detachSelf();
							scorescontinueSprite.detachSelf();

							fullSprite.detachSelf();
						


							MoveModifier leave2left=new MoveModifier(1.0f,0,-500,0,0);
							MoveModifier leave2right=new MoveModifier(1.0f,400,900,0,0);
							IModifierListener<IEntity> afterleave = new IModifierListener<IEntity>() {
								@Override
								public void onModifierStarted(IModifier<IEntity> pModifier,
										IEntity pItem) {
								}

								@Override
								public void onModifierFinished(IModifier<IEntity> pModifier,
										IEntity pItem) {
									


									tablet_cards.this.runOnUpdateThread(new Runnable() {
										@Override
										public void run() {
											leftHalfSprite.detachSelf();
											rightHalfSprite.detachSelf();
												
												tablet_cards.PlayerNames.clear();
												System.out.println("PlayerNames cleared.");
												for (int i = 0; i < tablet_cards.playerList.size(); i++) {
													tablet_cards.lastscore[i] = 0;
													tablet_cards.lastgoldcoins[i] = 10;
												}

												ParallelEntityModifier para1 = new ParallelEntityModifier(
														new AlphaModifier(0.5f, 1, 0), 
														new MoveModifier(1,170,170,80,-220)

														);

												para1.addModifierListener(

														new IModifierListener<IEntity>() {
															@Override
															public void onModifierStarted(
																	IModifier<IEntity> pModifier, IEntity pItem) {
															}

															@Override
															public void onModifierFinished(
																	IModifier<IEntity> pModifier, IEntity pItem) {
																mMainScene.detachChild(score1);

																score1 = new Text(170,80, komika, String
																		.valueOf(lastscore[0]),
																		getVertexBufferObjectManager());
																mMainScene.attachChild(score1);

															}
														});

												ParallelEntityModifier para2 = new ParallelEntityModifier(
														new AlphaModifier(0.5f, 1, 0),
														new MoveModifier(1,620,620,85,-215)

														);

												para2.addModifierListener(

														new IModifierListener<IEntity>() {
															@Override
															public void onModifierStarted(
																	IModifier<IEntity> pModifier, IEntity pItem) {
															}

															@Override
															public void onModifierFinished(
																	IModifier<IEntity> pModifier, IEntity pItem) {
																mMainScene.detachChild(score2);

																score2 = new Text(620,85, komika, String
																		.valueOf(lastscore[1]),
																		getVertexBufferObjectManager());
																mMainScene.attachChild(score2);
															}
														});

												score1.registerEntityModifier(para1);
												score2.registerEntityModifier(para2);
												betdetached=0;
												
												
												
												
												ParallelEntityModifier para4 = new ParallelEntityModifier(
														new AlphaModifier(0.5f, 1, 0), 
														new MoveModifier(1,170,170,140,-160)

														);

												para4.addModifierListener(

														new IModifierListener<IEntity>() {
															@Override
															public void onModifierStarted(
																	IModifier<IEntity> pModifier, IEntity pItem) {
															}

															@Override
															public void onModifierFinished(
																	IModifier<IEntity> pModifier, IEntity pItem) {
																mMainScene.detachChild(headgold1);

																headgold1 = new Text(170,140, komika, String
																		.valueOf(lastgoldcoins[0]),
																		getVertexBufferObjectManager());
																mMainScene.attachChild(headgold1);

															}
														});

												ParallelEntityModifier para5 = new ParallelEntityModifier(
														new AlphaModifier(0.5f, 1, 0),
														new MoveModifier(1,620,620,140,-160)
														);

												para5.addModifierListener(

														new IModifierListener<IEntity>() {
															@Override
															public void onModifierStarted(
																	IModifier<IEntity> pModifier, IEntity pItem) {
															}

															@Override
															public void onModifierFinished(
																	IModifier<IEntity> pModifier, IEntity pItem) {
																mMainScene.detachChild(headgold2);

																headgold2 = new Text(620,140, komika, String
																		.valueOf(lastgoldcoins[1]),
																		getVertexBufferObjectManager());
																mMainScene.attachChild(headgold2);
															}
														});
												
												headgold1.registerEntityModifier(para4);
												headgold2.registerEntityModifier(para5);
												
												tablet_cards.game = new SevenFiveTwoThreeGame2(
														tablet_cards.playerList, thisact,
														tablet_cards.mMainScene, thisvbom);
										}});		
								}
							};

							leave2left.addModifierListener(afterleave);

							leftHalfSprite.registerEntityModifier(leave2left);
							rightHalfSprite.registerEntityModifier(leave2right);
							mMainScene.attachChild(leftHalfSprite);
							mMainScene.attachChild(rightHalfSprite);
						}
					});}
				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,pTouchAreaLocalY);
			}

		};
		
		
		scorescontinueSprite=new Sprite(270,300,this.scorecontinueTextureRegion, getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionUp()) {
					bsound.play();
					tablet_cards.this.runOnUpdateThread(new Runnable() {
						@Override
						public void run() {
							if(!scoresSprite.hasParent()){ return;}
							mMainScene.unregisterTouchArea(scoresSprite);
							mMainScene.unregisterTouchArea(endgameSprite);
							mMainScene.unregisterTouchArea(newgameSprite);
							mMainScene.unregisterTouchArea(scorescontinueSprite);
							winnote.detachSelf();
							scoresSprite.detachSelf();
							endgameSprite.detachSelf();
							newgameSprite.detachSelf();
							scorescontinueSprite.detachSelf();

							fullSprite.detachSelf();


							MoveModifier leave2left=new MoveModifier(1.0f,0,-500,0,0);
							MoveModifier leave2right=new MoveModifier(1.0f,400,900,0,0);
							IModifierListener<IEntity> afterleave = new IModifierListener<IEntity>() {
								@Override
								public void onModifierStarted(IModifier<IEntity> pModifier,
										IEntity pItem) {
								}

								@Override
								public void onModifierFinished(IModifier<IEntity> pModifier,
										IEntity pItem) {
									


									tablet_cards.this.runOnUpdateThread(new Runnable() {
										@Override
										public void run() {
											leftHalfSprite.detachSelf();
											rightHalfSprite.detachSelf();
												tablet_cards.PlayerNames.clear();
												for (int i = 0; i < tablet_cards.playerList.size(); i++) {
													tablet_cards.lastscore[i] = tablet_cards.lastscore[i]
															+ tablet_cards.game.players.get(i).getScore();
												}


												ParallelEntityModifier para1 = new ParallelEntityModifier(
														new AlphaModifier(0.5f, 1, 0), 
														new MoveModifier(1,170,170,80,-220)

														);

												para1.addModifierListener(

														new IModifierListener<IEntity>() {
															@Override
															public void onModifierStarted(
																	IModifier<IEntity> pModifier, IEntity pItem) {
															}

															@Override
															public void onModifierFinished(
																	IModifier<IEntity> pModifier, IEntity pItem) {
																mMainScene.detachChild(score1);

																score1 = new Text(170,80, komika, String
																		.valueOf(lastscore[0]),
																		getVertexBufferObjectManager());
																mMainScene.attachChild(score1);

															}
														});

												ParallelEntityModifier para2 = new ParallelEntityModifier(
														new AlphaModifier(0.5f, 1, 0),
														new MoveModifier(1,620,620,85,-215)

														);

												para2.addModifierListener(

														new IModifierListener<IEntity>() {
															@Override
															public void onModifierStarted(
																	IModifier<IEntity> pModifier, IEntity pItem) {
															}

															@Override
															public void onModifierFinished(
																	IModifier<IEntity> pModifier, IEntity pItem) {
																mMainScene.detachChild(score2);

																score2 = new Text(620,85, komika, String
																		.valueOf(lastscore[1]),
																		getVertexBufferObjectManager());
																mMainScene.attachChild(score2);
															}
														});

												score1.registerEntityModifier(para1);
												score2.registerEntityModifier(para2);
												betdetached=0;
												
												ParallelEntityModifier para4 = new ParallelEntityModifier(
														new AlphaModifier(0.5f, 1, 0), 
														new MoveModifier(1,170,170,140,-160)

														);

												para4.addModifierListener(

														new IModifierListener<IEntity>() {
															@Override
															public void onModifierStarted(
																	IModifier<IEntity> pModifier, IEntity pItem) {
															}

															@Override
															public void onModifierFinished(
																	IModifier<IEntity> pModifier, IEntity pItem) {
																mMainScene.detachChild(headgold1);

																headgold1 = new Text(170,140, komika, String
																		.valueOf(lastgoldcoins[0]),
																		getVertexBufferObjectManager());
																mMainScene.attachChild(headgold1);

															}
														});

												ParallelEntityModifier para5 = new ParallelEntityModifier(
														new AlphaModifier(0.5f, 1, 0),
														new MoveModifier(1,620,620,140,-160)
														);

												para5.addModifierListener(

														new IModifierListener<IEntity>() {
															@Override
															public void onModifierStarted(
																	IModifier<IEntity> pModifier, IEntity pItem) {
															}

															@Override
															public void onModifierFinished(
																	IModifier<IEntity> pModifier, IEntity pItem) {
																mMainScene.detachChild(headgold2);

																headgold2 = new Text(620,140, komika, String
																		.valueOf(lastgoldcoins[1]),
																		getVertexBufferObjectManager());
																mMainScene.attachChild(headgold2);
															}
														});
												
												headgold1.registerEntityModifier(para4);
												headgold2.registerEntityModifier(para5);
												
												tablet_cards.game = new SevenFiveTwoThreeGame2(
														tablet_cards.playerList, thisact,
														tablet_cards.mMainScene, thisvbom);
										}});		
								}
							};

							leave2left.addModifierListener(afterleave);

							leftHalfSprite.registerEntityModifier(leave2left);
							rightHalfSprite.registerEntityModifier(leave2right);
							mMainScene.attachChild(leftHalfSprite);
							mMainScene.attachChild(rightHalfSprite);
						}
					});}
				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,pTouchAreaLocalY);
			}
		};
		
		
		final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(
				0, 0, 0, 5);
		final VertexBufferObjectManager vertexBufferObjectManager = this
				.getVertexBufferObjectManager();
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f,
				new Sprite(0, CAMERA_HEIGHT
						- this.mParallaxLayerBack.getHeight(),
						this.mParallaxLayerBack, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-5.0f,
				new Sprite(0, 80, this.mParallaxLayerMid,
						vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-10.0f,
				new Sprite(0, CAMERA_HEIGHT
						- this.mParallaxLayerFront.getHeight(),
						this.mParallaxLayerFront, vertexBufferObjectManager)));
		mMainScene.setBackground(autoParallaxBackground);
		this.monstersign1Sprite = new Sprite(0, 0,
				tablet_cards.monstersign1TextureRegion,
				getVertexBufferObjectManager());
		this.monstersign2Sprite = new Sprite(600, 0,tablet_cards.monstersign2TextureRegion,getVertexBufferObjectManager());
		
		bubble1Sprite=new Sprite(100, 180,tablet_cards.bubble1TextureRegion,getVertexBufferObjectManager());
		bubble2Sprite=new Sprite(575, 180,tablet_cards.bubble2TextureRegion,getVertexBufferObjectManager());
		Sprite backgroundSprite = new Sprite(0, 0,
				tablet_cards.mBackgroundTextureRegion,
				getVertexBufferObjectManager());
		mMainScene.attachChild(backgroundSprite);
		deletelist.add(backgroundSprite);

		greenSprite = new Sprite(0, 0, tablet_cards.greenTextureRegion,
				getVertexBufferObjectManager());
		paperSprite = new Sprite(0, 0, tablet_cards.paperTextureRegion,
				getVertexBufferObjectManager());
		paperback3Sprite = new ButtonSprite(300, 200,
				tablet_cards.backTexture3Region, getVertexBufferObjectManager());
		paperbackSprite = new ButtonSprite(300, 200,
				tablet_cards.backTexture0Region, getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionDown()) {
					mMainScene.attachChild(paperback3Sprite);
				}

				if (pTouchEvent.isActionUp()) {
					bsound.play();
					mMainScene.detachChild(paperback3Sprite);
					mMainScene.unregisterTouchArea(paperbackSprite);
					
					//2014/11/18
					//mMainScene.detachChild(paperSprite);
					mMainScene.detachChild(darkSprite);
					mMainScene.detachChild(ruleSprite);
					mMainScene.detachChild(treeSprite);
					
					mMainScene.detachChild(paperbackSprite);

					for (int i = 0; i < deletelist.size(); i++) {
						mMainScene.attachChild((IEntity) deletelist.get(i));
					}

					mMainScene.registerTouchArea(instructSprite);
					mMainScene.registerTouchArea(startSprite);
					mMainScene.registerTouchArea(quitSprite);
				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};

		final Text detectText = new Text(80, 60, this.komika,
				"Detecting Players...", new TextOptions(HorizontalAlign.LEFT),
				vob);
		mMainScene.attachChild(detectText);
		deletelist.add(detectText);

		mMainScene.registerUpdateHandler(new TimerHandler(0.2f, true,
				new ITimerCallback() {
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				detectText.setColor(Color.YELLOW);
				detectText.setText(tablet_cards.change());
			}
		}));
		
		signSprite = new Sprite(0, 0, tablet_cards.signTextureRegion,
				getVertexBufferObjectManager());

		ok0Sprite = new ButtonSprite(0, 0, tablet_cards.okTexture0Region,
				getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionDown()) {
					mMainScene.attachChild(ok3Sprite);

				}
				if (pTouchEvent.isActionUp()) {
					mMainScene.detachChild(ok3Sprite);

					bsound.play();

					// quitGame();
				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		ok3Sprite = new ButtonSprite(300, 280, tablet_cards.okTexture3Region,
				getVertexBufferObjectManager()) {

		};


		playanother0Sprite = new ButtonSprite(0, 0,tablet_cards.playanotherTexture0Region,getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pTouchEvent.isActionDown()) {
					mMainScene.attachChild(playanother3Sprite);
				}

				if (pTouchEvent.isActionUp()) {

					bsound.play();
					mMainScene.detachChild(playanother3Sprite);

					tablet_cards.PlayerNames.clear();
					System.out.println("PlayerNames cleared.");

					for (int i = 0; i < deletelist.size(); i++) {

						mMainScene.detachChild((IEntity) deletelist.get(i));
					}
					deletelist.clear();

					mMainScene.unregisterTouchArea(playanother0Sprite);
					System.out.println("playanother0Sprite unregistered");

					for (int i = 0; i < tablet_cards.playerList.size(); i++) {
						tablet_cards.lastscore[i] = 0;
						tablet_cards.lastgoldcoins[i] = 10;
					}

					
//					float x1 = score1.getX();
//					float y1 = score1.getY();
//
//					float x2 = score2.getX();
//					float y2 = score2.getY();

					ParallelEntityModifier para1 = new ParallelEntityModifier(
							new AlphaModifier(0.5f, 1, 0),
							new MoveModifier(1, 160,160,80,-220)

							);

					para1.addModifierListener(

							new IModifierListener<IEntity>() {
								@Override
								public void onModifierStarted(
										IModifier<IEntity> pModifier, IEntity pItem) {
								}

								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier, IEntity pItem) {
									mMainScene.detachChild(score1);

									score1 = new Text(160,80, komika, String
											.valueOf(lastscore[0]),
											getVertexBufferObjectManager());
									mMainScene.attachChild(score1);

								}
							});

					ParallelEntityModifier para2 = new ParallelEntityModifier(
							//new AlphaModifier(1, 1, 0),
							new AlphaModifier(0.5f, 1, 0),
							new MoveModifier(1,640,640,90,-210)

							);

					para2.addModifierListener(

							new IModifierListener<IEntity>() {
								@Override
								public void onModifierStarted(
										IModifier<IEntity> pModifier, IEntity pItem) {
								}

								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier, IEntity pItem) {
									mMainScene.detachChild(score2);

									score2 = new Text(640,90, komika, String
											.valueOf(lastscore[1]),
											getVertexBufferObjectManager());
									mMainScene.attachChild(score2);
								}
							});

					score1.registerEntityModifier(para1);
					score2.registerEntityModifier(para2);

					//2014/11/23
					betdetached=0;
					tablet_cards.game = new SevenFiveTwoThreeGame2(tablet_cards.playerList, thisact,tablet_cards.mMainScene, thisvbom);
				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		playanother3Sprite = new ButtonSprite(300, 330,
				tablet_cards.playanotherTexture3Region,
				getVertexBufferObjectManager()) {
		};

		continueSprite = new ButtonSprite(0, 0,
				tablet_cards.continueTextureRegion,
				getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pTouchEvent.isActionDown()) {
					mMainScene.attachChild(continue3Sprite);
				}

				if (pTouchEvent.isActionUp()) {

					bsound.play();
					mMainScene.detachChild(continue3Sprite);
					tablet_cards.PlayerNames.clear();
					System.out.println("PlayerNames cleared.");

					for (int i = 0; i < deletelist.size(); i++) {

						mMainScene.detachChild((IEntity) deletelist.get(i));
					}
					deletelist.clear();

					mMainScene.unregisterTouchArea(continueSprite);
					System.out.println("continueSprite unregistered");

					for (int i = 0; i < tablet_cards.playerList.size(); i++) {
						tablet_cards.lastscore[i] = tablet_cards.lastscore[i]
								+ tablet_cards.game.players.get(i).getScore();
					}


					ParallelEntityModifier para1 = new ParallelEntityModifier(
							new AlphaModifier(0.5f, 1, 0), 
							new MoveModifier(1,160,160,80,-220)

							);

					para1.addModifierListener(

							new IModifierListener<IEntity>() {
								@Override
								public void onModifierStarted(
										IModifier<IEntity> pModifier, IEntity pItem) {
								}

								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier, IEntity pItem) {
									mMainScene.detachChild(score1);

									score1 = new Text(160,80, komika, String
											.valueOf(lastscore[0]),
											getVertexBufferObjectManager());
									mMainScene.attachChild(score1);

								}
							});

					ParallelEntityModifier para2 = new ParallelEntityModifier(
							new AlphaModifier(0.5f, 1, 0),
							new MoveModifier(1,640,640,90,-210)

							);

					para2.addModifierListener(

							new IModifierListener<IEntity>() {
								@Override
								public void onModifierStarted(
										IModifier<IEntity> pModifier, IEntity pItem) {
								}

								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier, IEntity pItem) {
									mMainScene.detachChild(score2);

									score2 = new Text(640,90, komika, String
											.valueOf(lastscore[1]),
											getVertexBufferObjectManager());
									mMainScene.attachChild(score2);
								}
							});

					score1.registerEntityModifier(para1);
					score2.registerEntityModifier(para2);
					betdetached=0;
					tablet_cards.game = new SevenFiveTwoThreeGame2(
							tablet_cards.playerList, thisact,
							tablet_cards.mMainScene, thisvbom);

				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};
		
		
		
		
		
		
		continue3Sprite = new ButtonSprite(300, 380,
				tablet_cards.continue3TextureRegion,
				getVertexBufferObjectManager());

		instructSprite = new ButtonSprite(300, 290,
				tablet_cards.instructTextureRegion,
				getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionDown()) {					
					mMainScene.attachChild(instruct3Sprite);
				}

				if (pTouchEvent.isActionUp()) {
					bsound.play();

					mMainScene.detachChild(instruct3Sprite);

					for (int i = 0; i < deletelist.size(); i++) {
						mMainScene.detachChild((IEntity) deletelist.get(i));
					}

					mMainScene.unregisterTouchArea(instructSprite);
					mMainScene.unregisterTouchArea(startSprite);
					mMainScene.unregisterTouchArea(quitSprite);
					
					//2014/11/17
					mMainScene.registerTouchArea(darkSprite);
					System.out.println("dark Sprite is registered!!!");
					mMainScene.attachChild(darkSprite);
					mMainScene.attachChild(ruleSprite);
					mMainScene.attachChild(treeSprite);
					
				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		mMainScene.attachChild(instructSprite);
		mMainScene.registerTouchArea(instructSprite);
		deletelist.add(instructSprite);

		instruct3Sprite = new ButtonSprite(300, 290,
				tablet_cards.instruct3TextureRegion,
				getVertexBufferObjectManager());

		treeSprite=new Sprite(0,40,treeTextureRegion, getVertexBufferObjectManager());		
		ruleSprite=new Sprite(180,300,ruleTextureRegion, getVertexBufferObjectManager());
		rule1Sprite=new Sprite(0,0,rule1TextureRegion, getVertexBufferObjectManager());
		rule2Sprite=new Sprite(0,0,rule2TextureRegion, getVertexBufferObjectManager());
		rule3Sprite=new Sprite(0,0,rule3TextureRegion, getVertexBufferObjectManager());
		rule4Sprite=new Sprite(0,0,rule4TextureRegion, getVertexBufferObjectManager());
		rule5Sprite=new Sprite(0,0,rule5TextureRegion, getVertexBufferObjectManager());
		
		

		float rule2x=rule2Sprite.getX();
		float rule2y=rule2Sprite.getX();
//		SequenceEntityModifier seq2=new SequenceEntityModifier();
		final ParallelEntityModifier pararule2 = new ParallelEntityModifier(
				new AlphaModifier(1f, 0, 1),
				new MoveModifier(1, rule2x,rule2x+270, rule2y+100, rule2y+30),
				new ScaleModifier(1f, 0, 1f)

				);
		
		//2014/11/17
		// deletelists1 and 2, start here button, why sound not play, how to touch screen you stupid!!!!!
		darkSprite=new Sprite(0,0,darkTextureRegion, getVertexBufferObjectManager()){
			int tap_count=0;		
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pTouchEvent.isActionUp()) {
					// 2014/11/17. instruction and play tap twice problem
					tap_count++;
					System.out.println("tap_count is "+tap_count);
					switch (tap_count) {
					case 1: {
						// 2014/11/18
						rule1Sprite.registerEntityModifier(pararule2.deepCopy());
						mMainScene.attachChild(rule1Sprite);
						break;
					}
					case 2: {
						mMainScene.detachChild(rule1Sprite);
						
						rule2Sprite.registerEntityModifier(pararule2.deepCopy());
						mMainScene.attachChild(rule2Sprite);
						break;
					}
					case 3: {
						mMainScene.detachChild(rule2Sprite);
						
						rule3Sprite.registerEntityModifier(pararule2.deepCopy());
						mMainScene.attachChild(rule3Sprite);
						break;
					}
//					case 4: 
//					{
//						mMainScene.detachChild(rule3Sprite);
//
//						mMainScene.unregisterTouchArea(darkSprite);
//						System.out.println("darkSPrite is unregistered!!!");
//						mMainScene.attachChild(paperbackSprite);
//						mMainScene.registerTouchArea(paperbackSprite);
//						tap_count=0;
//						break;
//					}
					
					
					
					//2014/11/31
					case 4: {
						mMainScene.detachChild(rule3Sprite);
						
						rule4Sprite.registerEntityModifier(pararule2.deepCopy());
						mMainScene.attachChild(rule4Sprite);
						break;
					}
					case 5: {
						mMainScene.detachChild(rule4Sprite);
						
						rule5Sprite.registerEntityModifier(pararule2.deepCopy());
						mMainScene.attachChild(rule5Sprite);
						break;
					}
					case 6: 
					{
						mMainScene.detachChild(rule5Sprite);

						mMainScene.unregisterTouchArea(darkSprite);
						System.out.println("darkSPrite is unregistered!!!");
						mMainScene.attachChild(paperbackSprite);
						mMainScene.registerTouchArea(paperbackSprite);
						tap_count=0;
						break;
					}

					}
				}
				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
			
		};
		
		quitSprite = new ButtonSprite(300, 340, tablet_cards.quitTextureRegion,
				getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionUp()) {
					quitGame();
				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		mMainScene.attachChild(quitSprite);
		mMainScene.registerTouchArea(quitSprite);
		deletelist.add(quitSprite);

		quit3Sprite = new ButtonSprite(300, 340,
				tablet_cards.quit3TextureRegion, getVertexBufferObjectManager());

		tagSprite = new Sprite(380, 10, tablet_cards.tagTextureRegion,
				getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionUp()) {
					bsound.play();
				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		mMainScene.attachChild(tagSprite);
		deletelist.add(tagSprite);

		tag2Sprite = new Sprite(450, 10, tablet_cards.tagTextureRegion,
				getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionUp()) {
					bsound.play();
				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		mMainScene.attachChild(tag2Sprite);
		deletelist.add(tag2Sprite);

		tag3Sprite = new Sprite(520, 10, tablet_cards.tagTextureRegion,
				getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionUp()) {
					bsound.play();
				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		mMainScene.attachChild(tag3Sprite);
		deletelist.add(tag3Sprite);

		tag4Sprite = new Sprite(590, 10, tablet_cards.tagTextureRegion,
				getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionUp()) {
					bsound.play();
				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		mMainScene.attachChild(tag4Sprite);
		deletelist.add(tag4Sprite);

		score0Sprite = new ButtonSprite(0, 0, tablet_cards.scoreTexture0Region,
				getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionDown()) {
					bsound.play();
					mMainScene.attachChild(score3Sprite);
				}

				if (pTouchEvent.isActionUp()) {

					mMainScene.detachChild(score3Sprite);

					MoveModifier mod5 = new MoveModifier(0.5f, 100, 100, -420,
							0);
					Sprite signSprite2 = new Sprite(0, 0,
							tablet_cards.signTextureRegion,
							getVertexBufferObjectManager());
					signSprite2.registerEntityModifier(mod5);
					mMainScene.attachChild(signSprite2);
					tablet_cards.deletelist2.add(signSprite2);

					int ii = 1;
					for (int i = 0; i < tablet_cards.game.players.size(); i++) {

						int max_count = tablet_cards.game.players.get(i)
								.getScore() + tablet_cards.lastscore[i];
						String thisplayername = tablet_cards.game.players
								.get(i).getPlayerName();
						String winner = thisplayername.substring(0,
								thisplayername.lastIndexOf("_"));

						MoveModifier mod = new MoveModifier(0.5f, 300, 300, -90
								- ii * 50, 330 - ii * 50);
						Text scorenote = new Text(0, 0, thisact.komika, winner
								+ ": " + max_count, thisvbom);
						scorenote.registerEntityModifier(mod);
						mMainScene.attachChild(scorenote);
						tablet_cards.deletelist2.add(scorenote);
						ii++;
					}

					MoveModifier mod6 = new MoveModifier(0.5f, 300, 300, -90
							- ii * 50, 330 - ii * 50);
					back0Sprite.registerEntityModifier(mod6);
 
					back3Sprite = new ButtonSprite(300, 330 - ii * 50,
							tablet_cards.backTexture3Region, thisvbom) {
					};

					mMainScene.unregisterTouchArea(playanother0Sprite);
					System.out.println("playanother0Sprite unregistered");

					mMainScene.registerTouchArea(back0Sprite);
					System.out.println("back0Sprite registered");

					mMainScene.attachChild(back0Sprite);
					tablet_cards.deletelist2.add(back0Sprite);

					System.out.println("scores pressed, size of deletelist2: "
							+ deletelist2.size());
					
				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};

		score3Sprite = new ButtonSprite(300, 230,
				tablet_cards.scoreTexture3Region,
				getVertexBufferObjectManager()) {
		};

		back0Sprite = new ButtonSprite(0, 0, tablet_cards.backTexture0Region,
				getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionDown()) {
					mMainScene.attachChild(back3Sprite);

				}

				if (pTouchEvent.isActionUp()) {
					bsound.play();
					mMainScene.detachChild(back3Sprite);

					System.out.println("back pressed, size of deletelist2: "
							+ deletelist2.size());

					for (int i = 0; i < deletelist2.size(); i++) {
						System.out.println("page element detached!!!");

						try {
							mMainScene
							.detachChild((IEntity) deletelist2.get(i));
						} catch (Exception e) {
							Debug.e(e);
						}
					}
					
					deletelist2.clear();
					System.out.println("after deletion, size of deletelist2: "+ deletelist2.size());

					try {
						mMainScene.unregisterTouchArea(this);
						System.out.println("back0sprite unregistered");

						mMainScene.registerTouchArea(playanother0Sprite);
						System.out.println("playanother0Sprite registered");

					} catch (Exception e) {
						Debug.e(e);
					}

				}

				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};

		startSprite = new ButtonSprite(300, 240,
				tablet_cards.startTextureRegion, getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pTouchEvent.isActionDown()) {
					
//					
					if(tablet_cards.this.bmusic.isPlaying()) {
						tablet_cards.this.bmusic.pause();
					} 
					
					tablet_cards.this.bsound.play();
					mMainScene.attachChild(start3Sprite);

					
				}

				if(pTouchEvent.isActionUp()){
					
				mMainScene.detachChild(start3Sprite);
							
				//2014/11/08, moved from isactiondown
				if (!playerList.isEmpty()) {
					tablet_cards.lastscore = new int[tablet_cards.playerList.size()];
					tablet_cards.lastgoldcoins = new int[tablet_cards.playerList.size()];

					for (int i = 0; i < tablet_cards.playerList.size(); i++) {
						tablet_cards.lastscore[i] = 0;
						tablet_cards.lastgoldcoins[i] = 10;
					}
					
					this.detachSelf();
					mMainScene.unregisterTouchArea(thisact.startSprite);
					mMainScene.unregisterTouchArea(thisact.quitSprite);
					mMainScene.unregisterTouchArea(thisact.instructSprite);

					mMainScene.setBackground(autoParallaxBackground);
					mMainScene.attachChild(monstersign1Sprite);
					mMainScene.attachChild(monstersign2Sprite);

					lmonsterSprite = new Sprite(30, 110,
							choosedmonsters.get(0),
							getVertexBufferObjectManager());
					rmonsterSprite = new Sprite(730, 110,
							choosedmonsters.get(1),
							getVertexBufferObjectManager());

//					lgoldSprite = new Sprite(140, 130, goldTextureRegion,
//							getVertexBufferObjectManager());
//					rgoldSprite = new Sprite(640, 130, goldTextureRegion,
//							getVertexBufferObjectManager());
					
					Text score1start=new Text(90, 80, komika,"score:",getVertexBufferObjectManager());
					mMainScene.attachChild(score1start);
					Text score2start=new Text(650, 80, komika,":score",getVertexBufferObjectManager());
					mMainScene.attachChild(score2start);
					
					lgoldSprite = new Sprite(90, 130, goldTextureRegion,getVertexBufferObjectManager());
					rgoldSprite = new Sprite(670, 130, goldTextureRegion,getVertexBufferObjectManager());
					
					
					
					
					
					//2014/11/30
					lmonsterSprite2 = new Sprite(0,0,
							choosedmonsters.get(0),
							getVertexBufferObjectManager());
					rmonsterSprite2 = new Sprite(0,0,
							choosedmonsters.get(1),
							getVertexBufferObjectManager());

					lgoldSprite2 = new Sprite(0, 0, goldTextureRegion,
							getVertexBufferObjectManager());
					rgoldSprite2 = new Sprite(0, 0, goldTextureRegion,
							getVertexBufferObjectManager());
					
					
					mMainScene.attachChild(lmonsterSprite);
					mMainScene.attachChild(rmonsterSprite);
					mMainScene.attachChild(lgoldSprite);
					mMainScene.attachChild(rgoldSprite);

					Text name1 = new Text(40, 80, komika,
							(playerList.get(0)).substring(0,
									(playerList.get(0)).lastIndexOf("_")),
									getVertexBufferObjectManager());
					mMainScene.attachChild(name1);
					Text name2 = new Text(735, 80, komika,
							(playerList.get(1)).substring(0,
									(playerList.get(1)).lastIndexOf("_")),
									getVertexBufferObjectManager());
					mMainScene.attachChild(name2);

					score1 = new Text(170, 80, komika,
							String.valueOf(lastscore[0]),
							getVertexBufferObjectManager());
					mMainScene.attachChild(score1);
					score2 = new Text(620, 85, komika,
							String.valueOf(lastscore[1]),
							getVertexBufferObjectManager());
					mMainScene.attachChild(score2);
					
					//2014/11/13
					headgold1 = new Text(170, 140, komika,String.valueOf(lastgoldcoins[0]),getVertexBufferObjectManager());
					mMainScene.attachChild(headgold1);
					headgold2 = new Text(620, 140, komika,String.valueOf(lastgoldcoins[1]),getVertexBufferObjectManager());
					mMainScene.attachChild(headgold2);
					
					
					
					
					
					
					
					
					
					
					
					
					

					for (int i = 0; i < deletelist.size(); i++) {
						mMainScene.detachChild((IEntity) deletelist.get(i));
					}

					deletelist.clear();

				}

				if (!tablet_cards.playerList.isEmpty()) {

					mMainScene.detachChild(start3Sprite);
					betdetached=0;
					tablet_cards.game = new SevenFiveTwoThreeGame2(
							tablet_cards.playerList, curact,
							tablet_cards.mMainScene, vob);
				}
			
				}
			return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};

		mMainScene.registerTouchArea(startSprite);
		mMainScene.attachChild(startSprite);
		deletelist.add(startSprite);

		start3Sprite = new ButtonSprite(300, 240,
				tablet_cards.start3TextureRegion,
				getVertexBufferObjectManager());

		return mMainScene;

	}

	private void init(String roomId) {
		if (theClient != null) {
			theClient.addRoomRequestListener(this);
			theClient.addNotificationListener(this);
			theClient.joinRoom(roomId);
		}

	}

	public void addMorePlayer(boolean isMine, String userName) {
		Log.d("userNameGame", userName);
		tmpcount++;
	}

	// public void sendUpdateEvent(String status, String username, int
	// minvalue){
	public void sendUpdateEvent(String status, String username, int minvalue,
			int score) {
		try {
			String sendmessage = "";
			JsonParser newjson = new JsonParser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("model", "1");
			map.put("status", status);
			map.put("username", username);
			map.put("roundmin", minvalue);
			map.put("currentscore", score);
			sendmessage = newjson.createJson(map);
			System.out.println("sending messgae to client: " + sendmessage);
			theClient.sendChat(sendmessage);
			System.out.println("server send message succeessfully...");
		} catch (Exception e) {
			Log.d("sendUpdateEvent", e.getMessage());
		}
	}

	public void sendUpdateEvent(String model, String status, String cardstr,
			String username, int goldcoin) {
		try {
			String sendmessage = "";
			JsonParser newjson = new JsonParser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("model", model);
			map.put("status", status);
			map.put("card", cardstr);
			map.put("username", username);
			map.put("goldcoin", goldcoin);
			sendmessage = newjson.createJson(map);
			System.out.println("sending messgae to client: " + sendmessage);
			theClient.sendChat(sendmessage);
			System.out.println("server send message succeessfully...");
		} catch (Exception e) {
			Log.d("sendUpdateEvent", e.getMessage());
		}
	}

	public void sendUpdateEvent(String model, String status, String cardstr,
			String username) {
		try {
			String sendmessage = "";
			JsonParser newjson = new JsonParser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("model", model);
			map.put("status", status);
			map.put("card", cardstr);
			map.put("username", username);
			sendmessage = newjson.createJson(map);
			System.out.println("sending messgae to client: " + sendmessage);
			theClient.sendChat(sendmessage);
			System.out.println("server send message succeessfully...");
		} catch (Exception e) {
			Log.d("sendUpdateEvent", e.getMessage());
		}
	}

	public void sendUpdateEvent(String status, String username) {// whose turn
		// is
		// this???
		try {
			String sendmessage = "";
			JsonParser newjson = new JsonParser();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("model", "1");
			map.put("status", status);
			map.put("username", username);
			sendmessage = newjson.createJson(map);
			System.out.println("sending messgae to client: " + sendmessage);
			theClient.sendChat(sendmessage);
			System.out.println("server send message succeessfully...");
		} catch (Exception e) {
			Log.d("sendUpdateEvent", e.getMessage());
		}
	}
	private void quit_function()
	{
		tablet_cards.this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					AlertDialog.Builder builder = new AlertDialog.Builder(tablet_cards.this);
					  
					  builder.setTitle("Back");
					  builder.setMessage("The game is running. Are you sure to quit!?")
					  .setCancelable(false)
					  .setPositiveButton("Yes,I quit..",new DialogInterface.OnClickListener() 
					  {
						  public void  onClick(DialogInterface dialog,int id) { //here you should end the game, return to screen ///send end message to all devices, include server and client..It is better to use a function...
								// Send delete room request
							  for (Hand2 tmp : tablet_cards.game.players) {
									sendUpdateEvent("1", "23", "QUIT", tmp.getPlayerName());
								}
								quitGame();
						  } }) 
					  .setNegativeButton("Cancel",new
					  DialogInterface.OnClickListener() {
					  
					  @Override public void onClick(DialogInterface dialog, int which) { 
						  // TODO Auto-generated method stub
					  
					  } }); 
					  AlertDialog alert = builder.create(); 
					  alert.show();
					  
					} catch (Exception e)
					{
						e.printStackTrace();
					}
			}
		});
		
	}
	@Override
	public void onChatReceived(ChatEvent event) {

		String sender = event.getSender();
		String message = event.getMessage();
		String tmpCardSelectedFromPlayer = null;

		servername=Utils.userName;

		System.out.println(servername+": message from "+sender);
		System.out.println("Utils.userName: "+Utils.userName);
		System.out.println("sender: "+sender);

		if(sender.equals(Utils.userName) == false)
		{
			System.out.println(servername+": message received from "+sender);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(message);
				currPlayer = jsonObject.getString("username");

				String model = (String)jsonObject.get("model");
				if(model.equals("2"))
				{
					String action = (String)jsonObject.get("action");
					System.out.println(servername+": "+sender+" requests "+action);
					if(action.equals("Finish"))
					{
						this.lastPlayer.addScore(this.game.current_table_count);
						System.out.println("game is ending.");
						tablet_cards.game.endgame2();
						return;
					}
					else if(action.equals("QUIT"))
					{
						quit_function();
						
						
					}
					if(action.equals("updateinformation")&&tablet_cards.PlayerNames.size() != tablet_cards.playerList.size())
					{
						if(!tablet_cards.PlayerNames.contains(sender))
						{
							
							tablet_cards.PlayerNames.add(sender);
							System.out.println("one player added.");
							//2014/11/23
							final String coin_score =  jsonObject.getString("card"); 
							System.out.println("weiyi::"+coin_score);
							tablet_cards.game.totalGoldsCoins  += Integer.valueOf(coin_score.split(";")[0]); 
							if(tablet_cards.playerList.indexOf(sender) != -1) 
							{ 
							
								tablet_cards.game.players.get(tablet_cards.playerList.indexOf(sender)).addCoin(Integer.valueOf(coin_score.split(";")[0]));
								
								//2014/11/23
								if(tablet_cards.game.players.get(tablet_cards.playerList.indexOf(sender)).right==0){
									bet1=new Text(130, 220, komika, "I bet "+Integer.valueOf(coin_score.split(";")[0]), getVertexBufferObjectManager());
									mMainScene.attachChild(bubble1Sprite);
									mMainScene.attachChild(bet1);
								//	nextbet=1;	
								}
								else{
									bet2= new Text(600, 220, komika,"I bet "+Integer.valueOf(coin_score.split(";")[0]), getVertexBufferObjectManager());
									mMainScene.attachChild(bubble2Sprite);
									mMainScene.attachChild(bet2);
								//	nextbet=0;
								}
								
								
			
								//2014/11/23
								final int currectplayer_right=tablet_cards.game.players.get(tablet_cards.playerList.indexOf(sender)).right;
								ParallelEntityModifier para3;
								if(currectplayer_right==0){
									para3 = new ParallelEntityModifier(
											new AlphaModifier(0.5f, 1, 0),
											new MoveModifier(1,170,170,80,-220)
											);	
								}
								else{
									para3 = new ParallelEntityModifier(
											new AlphaModifier(0.5f, 1, 0),
											new MoveModifier(1,620,620,85,-215)
											);
									
								}
								
								para3.addModifierListener(new IModifierListener<IEntity>() {
											@Override
											public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
											}
											@Override
											public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
												if(currectplayer_right==0){
													mMainScene.detachChild(tablet_cards.score1);
													tablet_cards.score1 = new Text(170, 80, tablet_cards.komika, String
															.valueOf(Integer.valueOf(coin_score.split(";")[1])),
															getVertexBufferObjectManager());
													mMainScene.attachChild(tablet_cards.score1);
													
												}
												else{	
													mMainScene.detachChild(tablet_cards.score2);
													tablet_cards.score2 = new Text(620,85, tablet_cards.komika, String
															.valueOf(Integer.valueOf(coin_score.split(";")[1])),
															getVertexBufferObjectManager());
													mMainScene.attachChild(tablet_cards.score2);				
													
												}
											}
										});
								
								if(currectplayer_right==0){
									tablet_cards.score1.registerEntityModifier(para3);	
								}
								else{
									tablet_cards.score2.registerEntityModifier(para3);

								}	
								
								
								//2014/11/23
								//problem: explain all instructions, client side so messy, intruction wrong! bonus UI, highlight problem, let's play cards the game name, 1-5, clients image incorrect, write player no cliet, stayed the bets!!!
//								if(nextbet==0){
//									bet1=new Text(130, 220, komika, "I bet "+Integer.valueOf(coin_score.split(";")[0]), getVertexBufferObjectManager());
//									mMainScene.attachChild(bubble1Sprite);
//									mMainScene.attachChild(bet1);
//									nextbet=1;
//								}
//								else{
//									bet2= new Text(600, 220, komika,"I bet "+Integer.valueOf(coin_score.split(";")[0]), getVertexBufferObjectManager());
//									mMainScene.attachChild(bubble2Sprite);
//									mMainScene.attachChild(bet2);
//									nextbet=0;
//								}
								
								tablet_cards.game.players.get(tablet_cards.playerList.indexOf(sender)).addScore(Integer.valueOf(coin_score.split(";")[1]));
								
								//2014/11/23
								System.out.println(tablet_cards.game.players.get(tablet_cards.playerList.indexOf(sender)).getCoin()+"+weiyi+"+tablet_cards.game.players.get(
										tablet_cards.playerList.indexOf(sender)).getScore()); 
							}			

						}
						if(tablet_cards.PlayerNames.size() == tablet_cards.playerList.size())
						{  //problem  : gamble UI
							System.out.println("all players added, starting game.");						
							(tablet_cards.game).play();

						}
						return ;
					}	
					String username = (String)jsonObject.get("username");
					final String username2=username;
					if(currentPlayer!= null && currentPlayer.getPlayerName().equals(username))// you should update currentplay in tablet_cards and you customized algorithms
					{
						if(action.equals("Exchange"))
						{
							System.out.println("exchange information from: "+username);
							String exchange_card = "";
							exchange_card = tablet_cards.game.deck.drawFromDeck().toString()+";";
							sendUpdateEvent("1", "24", exchange_card, currentPlayer.getPlayerName());
							
						}
						
						else if(action.equals("skip"))
						{
							this.skipsound.play();

							tablet_cards.game.skipCount++;
							this.currentPlayer = tablet_cards.game.chooseNextPlayer();
							tablet_cards.currentPlayer=this.currentPlayer;
							tablet_cards.game.currentPlayer = this.currentPlayer;
							tablet_cards.cur_player=username;
							tablet_cards.game.round();
						}
						else if(action.equals("sendcard"))
						{
							//problem: first card is dark, bugs,client part stops!!
							if(betdetached==0){ //problem: scores and golden coins are such a mess
							//2014/11/23, problem: dark card cannot swipe out? problem: sae position cannot change twice
								
								
								tablet_cards.this.runOnUpdateThread(new Runnable() {
							        @Override
							        public void run() {
							            // do you work here
							        	mMainScene.detachChild(bubble1Sprite);
										mMainScene.detachChild(bubble2Sprite);	
										mMainScene.detachChild(bet1);
										mMainScene.detachChild(bet2);
										betdetached=1;

							        }
							    });
								
								
//							mMainScene.detachChild(bubble1Sprite);
//							mMainScene.detachChild(bubble2Sprite);	
//							mMainScene.detachChild(bet1);
//							mMainScene.detachChild(bet2);
//							betdetached=1;
							}
							
							tmpCardSelectedFromPlayer = (String)jsonObject.get("card");//maybe here you should cut the prehand.
							Card CardSelectedFromPlayer2 = Utils.getCardByString(tmpCardSelectedFromPlayer);
							int CardValueinGame = Utils.getCardValueinGame(tmpCardSelectedFromPlayer);
							if(CardValueinGame > tablet_cards.game.highestvalue123)
							{
								tablet_cards.game.highestvalue123 = CardValueinGame;
								if( CardSelectedFromPlayer2.getValue() == 5 || CardSelectedFromPlayer2.getValue()==10 ||CardSelectedFromPlayer2.getValue()==13)
								{
									tablet_cards.game.current_table_count +=CardSelectedFromPlayer2.getValue()==13?10:CardSelectedFromPlayer2.getValue();
								}
								switch(tmpCardSelectedFromPlayer ){
								case "ace of spades":
								{
									Card2 s1 = new Card2(1,Card2.SPADES,100, 160, this,tablet_cards.ms1,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(s1);
									CardSelectedFromPlayer=s1;
									this.game.table.addCard(s1);
									tablet_cards.card2d.add(s1);
								}
								break;
								case "2 of spades":  
								{
									Card2 s2 = new Card2(2, Card2.SPADES,100, 160,this,tablet_cards.ms2,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(s2);
									CardSelectedFromPlayer=s2;
									this.game.table.addCard(s2); 
									tablet_cards.card2d.add(s2); 
								}
								break;
								case "3 of spades":   
								{
									Card2 s3 = new Card2(3, Card2.SPADES, 100, 160,this,tablet_cards.ms3,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(s3);
									CardSelectedFromPlayer=s3;
									this.game.table.addCard(s3); 
									tablet_cards.card2d.add(s3); 
								}
								break;
								case "4 of spades":   
								{
									Card2 s4 = new Card2(4, Card2.SPADES, 100, 160,this,tablet_cards.ms4,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(s4);
									CardSelectedFromPlayer=s4;
									this.game.table.addCard(s4); 
									tablet_cards.card2d.add(s4); 
								} 
								break;
								case "5 of spades":   
								{
									Card2 s5 = new Card2(5, Card2.SPADES,100, 160, this,tablet_cards.ms5,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(s5);
									CardSelectedFromPlayer=s5;
									this.game.table.addCard(s5); 
									tablet_cards.card2d.add(s5); 
								}  
								break;
								case "6 of spades":   
								{
									Card2 s6 = new Card2(6, Card2.SPADES, 100, 160,this,tablet_cards.ms6,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(s6);
									CardSelectedFromPlayer=s6;
									this.game.table.addCard(s6); 
									tablet_cards.card2d.add(s6); 
								}  
								break;
								case "7 of spades":   
								{
									Card2 s7 = new Card2(7, Card2.SPADES, 100, 160,this,tablet_cards.ms7,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(s7);
									CardSelectedFromPlayer=s7;
									this.game.table.addCard(s7); 
									tablet_cards.card2d.add(s7); 
								}  
								break;
								case "8 of spades":   
								{
									Card2 s8 = new Card2(8, Card2.SPADES, 100, 160,this,tablet_cards.ms8,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(s8);
									CardSelectedFromPlayer=s8;
									this.game.table.addCard(s8); 
									tablet_cards.card2d.add(s8); 

								}  
								break;
								case "9 of spades":  {
									Card2 s9 = new Card2(9, Card2.SPADES, 100, 160,this,tablet_cards.ms9,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(s9);
									CardSelectedFromPlayer=s9;
									this.game.table.addCard(s9); 
									tablet_cards.card2d.add(s9); 
								}  break;
								case "10 of spades":   {
									Card2 s10 = new Card2(10, Card2.SPADES, 100, 160,this,tablet_cards.ms10,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(s10);
									CardSelectedFromPlayer=s10;
									this.game.table.addCard(s10); 
									tablet_cards.card2d.add(s10); 
								}  break;
								case "jack of spades":   {
									Card2 sj = new Card2(11, Card2.SPADES, 100, 160,this,tablet_cards.msj,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(sj);
									CardSelectedFromPlayer=sj;
									this.game.table.addCard(sj); 
									tablet_cards.card2d.add(sj); 
								}  break;
								case "queen of spades":   {
									Card2 sq = new Card2(12, Card2.SPADES, 100, 160,this,tablet_cards.msq,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(sq);
									CardSelectedFromPlayer=sq;
									this.game.table.addCard(sq); 
									tablet_cards.card2d.add(sq); 
								}  break;
								case "king of spades":   {
									Card2 sk = new Card2(13, Card2.SPADES, 100, 160,this,tablet_cards.msk,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(sk);
									CardSelectedFromPlayer=sk;
									this.game.table.addCard(sk); 
									tablet_cards.card2d.add(sk); 
								}  break;

								case "ace of hearts":   {
									Card2 h1 = new Card2(1, Card2.HEARTS,  100, 160,this,tablet_cards.mh1,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(h1);
									CardSelectedFromPlayer=h1;
									this.game.table.addCard(h1); 
									tablet_cards.card2d.add(h1); 
								} break;
								case "2 of hearts":   {
									Card2 h2 = new Card2(2, Card2.HEARTS,100, 160, this,tablet_cards.mh2,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(h2);
									CardSelectedFromPlayer=h2;
									this.game.table.addCard(h2); 
									tablet_cards.card2d.add(h2); 
								}  break;
								case "3 of hearts":   {
									Card2 h3 = new Card2(3, Card2.HEARTS,100, 160, this,tablet_cards.mh3,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(h3);
									CardSelectedFromPlayer=h3;
									this.game.table.addCard(h3); 
									tablet_cards.card2d.add(h3); 
								}  break;
								case "4 of hearts":   {
									Card2 h4 = new Card2(4, Card2.HEARTS,100, 160, this,tablet_cards.mh4,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(h4);
									CardSelectedFromPlayer=h4;
									this.game.table.addCard(h4); 
									tablet_cards.card2d.add(h4); 
								}  break;
								case "5 of hearts":   {
									Card2 h5 = new Card2(5, Card2.HEARTS,100, 160, this,tablet_cards.mh5,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(h5);
									CardSelectedFromPlayer=h5;
									this.game.table.addCard(h5); 
									tablet_cards.card2d.add(h5); 
								}  break;
								case "6 of hearts":  {
									Card2 h6 = new Card2(6, Card2.HEARTS,100, 160, this,tablet_cards.mh6,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(h6);
									CardSelectedFromPlayer=h6;
									this.game.table.addCard(h6); 
									tablet_cards.card2d.add(h6); 
								}  break;
								case "7 of hearts":   {
									Card2 h7 = new Card2(7, Card2.HEARTS,100, 160, this,tablet_cards.mh7,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(h7);
									CardSelectedFromPlayer=h7;
									this.game.table.addCard(h7); 
									tablet_cards.card2d.add(h7); 
								}  break;
								case "8 of hearts":   {
									Card2 h8 = new Card2(8, Card2.HEARTS,100, 160, this,tablet_cards.mh8,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(h8);
									CardSelectedFromPlayer=h8;
									this.game.table.addCard(h8); 
									tablet_cards.card2d.add(h8); 
								}  break;
								case "9 of hearts":   {
									Card2 h9 = new Card2(9, Card2.HEARTS,100, 160, this,tablet_cards.mh9,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(h9);
									CardSelectedFromPlayer=h9;
									this.game.table.addCard(h9); 
									tablet_cards.card2d.add(h9); 
								}  break;
								case "10 of hearts":  {
									Card2 h10 = new Card2(10, Card2.HEARTS,100, 160, this,tablet_cards.mh10,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(h10);
									CardSelectedFromPlayer=h10;
									this.game.table.addCard(h10); 
									tablet_cards.card2d.add(h10); 
								}  break;
								case "jack of hearts":   {
									Card2 hj = new Card2(11, Card2.HEARTS,100, 160, this,tablet_cards.mhj,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(hj);
									CardSelectedFromPlayer=hj;
									this.game.table.addCard(hj); 
									tablet_cards.card2d.add(hj); 

								}  break;
								case "queen of hearts":   {
									Card2 hq = new Card2(12, Card2.HEARTS,100, 160, this,tablet_cards.mhq,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(hq);
									CardSelectedFromPlayer=hq;
									this.game.table.addCard(hq); 
									tablet_cards.card2d.add(hq); 
								}  break;
								case "king of hearts":  {
									Card2 hk = new Card2(13, Card2.HEARTS,100, 160, this,tablet_cards.mhk,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(hk);
									CardSelectedFromPlayer=hk;
									this.game.table.addCard(hk); 
									tablet_cards.card2d.add(hk); 
								}  break;

								case "ace of diamonds":   {
									Card2 d1 = new Card2(1,Card2.DIAMONDS,100, 160, this,tablet_cards.md1,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(d1);
									CardSelectedFromPlayer=d1;
									this.game.table.addCard(d1); 
									tablet_cards.card2d.add(d1); 
								}  break;
								case "2 of diamonds":   {
									Card2 d2 = new Card2(2,Card2.DIAMONDS,100, 160, this,tablet_cards.md2,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(d2);
									CardSelectedFromPlayer=d2;
									this.game.table.addCard(d2); 
									tablet_cards.card2d.add(d2); 
								}  break;
								case "3 of diamonds":   {
									Card2 d3 = new Card2(3,Card2.DIAMONDS,100, 160, this,tablet_cards.md3,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(d3);
									CardSelectedFromPlayer=d3;
									this.game.table.addCard(d3); 
									tablet_cards.card2d.add(d3); 
								}  break;
								case "4 of diamonds":   {
									Card2 d4 = new Card2(4,Card2.DIAMONDS,100, 160, this,tablet_cards.md4,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(d4);
									CardSelectedFromPlayer=d4;
									this.game.table.addCard(d4); 
									tablet_cards.card2d.add(d4); 
								}  break;
								case "5 of diamonds":   {
									Card2 d5 = new Card2(5,Card2.DIAMONDS,100, 160, this,tablet_cards.md5,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(d5);
									CardSelectedFromPlayer=d5;
									this.game.table.addCard(d5); 
									tablet_cards.card2d.add(d5); 
								}  break;
								case "6 of diamonds":  {
									Card2 d6 = new Card2(6,Card2.DIAMONDS,100, 160, this,tablet_cards.md6,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(d6);
									CardSelectedFromPlayer=d6;
									this.game.table.addCard(d6); 
									tablet_cards.card2d.add(d6); 
								}  break;
								case "7 of diamonds":   {
									Card2 d7 = new Card2(7,Card2.DIAMONDS,100, 160, this,tablet_cards.md7,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(d7);
									CardSelectedFromPlayer=d7;
									this.game.table.addCard(d7); 
									tablet_cards.card2d.add(d7); 
								}  break;
								case "8 of diamonds":   {
									Card2 d8 = new Card2(8,Card2.DIAMONDS,100, 160, this,tablet_cards.md8,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(d8);
									CardSelectedFromPlayer=d8;
									this.game.table.addCard(d8); 
									tablet_cards.card2d.add(d8); 
								}  break;
								case "9 of diamonds":   {
									Card2 d9 = new Card2(9,Card2.DIAMONDS,100, 160, this,tablet_cards.md9,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(d9);
									CardSelectedFromPlayer=d9;
									this.game.table.addCard(d9); 
									tablet_cards.card2d.add(d9); 
								}  break;
								case "10 of diamonds":   {
									Card2 d10 = new Card2(10,Card2.DIAMONDS,100, 160,this, tablet_cards.md10,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(d10);
									CardSelectedFromPlayer=d10;
									this.game.table.addCard(d10); 
									tablet_cards.card2d.add(d10); 
								}  break;
								case "jack of diamonds":  {
									Card2 dj = new Card2(11,Card2.DIAMONDS,100, 160, this,tablet_cards.mdj,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(dj);
									CardSelectedFromPlayer=dj;
									this.game.table.addCard(dj); 
									tablet_cards.card2d.add(dj); 
								}  break;
								case "queen of diamonds":   {
									Card2 dq = new Card2(12,Card2.DIAMONDS,100, 160, this,tablet_cards.mdq,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(dq);
									CardSelectedFromPlayer=dq;
									this.game.table.addCard(dq); 
									tablet_cards.card2d.add(dq); 
								}  break;
								case "king of diamonds":  {
									Card2 dk = new Card2(13,Card2.DIAMONDS,100, 160, this,tablet_cards.mdk,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(dk);
									CardSelectedFromPlayer=dk;
									this.game.table.addCard(dk); 
									tablet_cards.card2d.add(dk); 
								}  break;

								case "ace of clubs":   {
									//Card2 c1 = new Card2(1,Card2.CLUBS,100, 160, this,tablet_cards.mc1,getVertexBufferObjectManager());
									Card2 c1 = new Card2(1,Card2.CLUBS,100, 160, this,tablet_cards.mc1,getVertexBufferObjectManager());

									tablet_cards.mMainScene.attachChild(c1);
									CardSelectedFromPlayer=c1;
									this.game.table.addCard(c1); 
									tablet_cards.card2d.add(c1); 
								}  break;
								case "2 of clubs":   {
									Card2 c2 = new Card2(2,Card2.CLUBS,100, 160, this,tablet_cards.mc2,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(c2);
									CardSelectedFromPlayer=c2;
									this.game.table.addCard(c2); 
									tablet_cards.card2d.add(c2); 
								}  break;
								case "3 of clubs":   {
									Card2 c3 = new Card2(3,Card2.CLUBS,100, 160, this,tablet_cards.mc3,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(c3);
									CardSelectedFromPlayer=c3;
									this.game.table.addCard(c3); 
									tablet_cards.card2d.add(c3); 
								}  break;
								case "4 of clubs":   {
									Card2 c4 = new Card2(4,Card2.CLUBS,100, 160, this,tablet_cards.mc4,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(c4);
									CardSelectedFromPlayer=c4;
									this.game.table.addCard(c4); 
									tablet_cards.card2d.add(c4); 
								}  break;
								case "5 of clubs":   {
									Card2 c5 = new Card2(5,Card2.CLUBS,100, 160, this,tablet_cards.mc5,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(c5);
									CardSelectedFromPlayer=c5;
									this.game.table.addCard(c5); 
									tablet_cards.card2d.add(c5); 
								}  break;
								case "6 of clubs":   {
									Card2 c6 = new Card2(6,Card2.CLUBS,100, 160, this,tablet_cards.mc6,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(c6);
									CardSelectedFromPlayer=c6;
									this.game.table.addCard(c6); 
									tablet_cards.card2d.add(c6); 
								}  break;
								case "7 of clubs":   {
									Card2 c7 = new Card2(7,Card2.CLUBS,100, 160, this,tablet_cards.mc7,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(c7);
									CardSelectedFromPlayer=c7;
									this.game.table.addCard(c7); 
									tablet_cards.card2d.add(c7); 
								}  break;
								case "8 of clubs":   {
									Card2 c8 = new Card2(8,Card2.CLUBS,100, 160, this,tablet_cards.mc8,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(c8);
									CardSelectedFromPlayer=c8;
									this.game.table.addCard(c8); 
									tablet_cards.card2d.add(c8); 
								}  break;
								case "9 of clubs":   {
									Card2 c9 = new Card2(9,Card2.CLUBS,100, 160, this,tablet_cards.mc9,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(c9);
									CardSelectedFromPlayer=c9;
									this.game.table.addCard(c9); 
									tablet_cards.card2d.add(c9); 
								}  break;
								case "10 of clubs":   {
									Card2 c10 = new Card2(10,Card2.CLUBS,100, 160, this,tablet_cards.mc10,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(c10);
									CardSelectedFromPlayer=c10;
									this.game.table.addCard(c10); 
									tablet_cards.card2d.add(c10); 
								}  break;
								case "jack of clubs":   {
									Card2 cj = new Card2(11,Card2.CLUBS,100, 160, this,tablet_cards.mcj,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(cj);
									CardSelectedFromPlayer=cj;
									this.game.table.addCard(cj); 
									tablet_cards.card2d.add(cj); 
								}  break;
								case "queen of clubs":   {
									Card2 cq = new Card2(12,Card2.CLUBS,100, 160, this,tablet_cards.mcq,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(cq);
									CardSelectedFromPlayer=cq;
									this.game.table.addCard(cq); 
									tablet_cards.card2d.add(cq); 

								}  break;
								case "king of clubs":   {
									Card2 ck = new Card2(13,Card2.CLUBS,100, 160, this,tablet_cards.mck,getVertexBufferObjectManager());
									tablet_cards.mMainScene.attachChild(ck);
									CardSelectedFromPlayer=ck;
									this.game.table.addCard(ck); 
									tablet_cards.card2d.add(ck); 
								}  break;

								}
								//2014/11/05
								this.swipesound.play();

								tablet_cards.lastPlayer = this.currentPlayer;

								this.currentPlayer = tablet_cards.game.chooseNextPlayer();
								tablet_cards.currentPlayer=this.currentPlayer;
								tablet_cards.game.currentPlayer = this.currentPlayer;
								tablet_cards.cur_player=username;
								tablet_cards.game.round();
								System.out.println("currect player is "+this.cur_player);

							}
							System.out.println(servername+": "+sender+" sends - "+tmpCardSelectedFromPlayer);

						}

					}

				}
			}catch (JSONException e) {
				e.printStackTrace();
			} 
		}
	}

	@Override
	public void onBackPressed() {

		if (theClient != null) {
			handleLeave(Utils.userName);
			theClient.leaveRoom(roomId);
			theClient.unsubscribeRoom(roomId);
			theClient.removeRoomRequestListener(this);
			theClient.removeNotificationListener(this);
		}
		super.onBackPressed();
	}

	public void handleLeave(String name) {
	}

	private void gamebegin() {
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
	public void onUserJoinedRoom(RoomData roomData, String name) {
		System.out.println("onuserjoinroom()");
		addMorePlayer(true, name);
		System.out.println("Player added: " + name);
		playerList.add(name);
		String monsterChoosed = name.split("@")[1];

		switch (monsterChoosed) {
		case "1": {

			ButtonSprite monster = new ButtonSprite(
					400 + (playerList.size() - 1) * 70, 50,
					tablet_cards.monsterRegion1,
					mEngine.getVertexBufferObjectManager());
			choosedmonsters.add(tablet_cards.monsterRegion1);
			monstersound.play();
			tablet_cards.mMainScene.attachChild(monster);
			deletelist.add(monster);
			break;
		}
		case "2": {

			ButtonSprite monster = new ButtonSprite(
					400 + (playerList.size() - 1) * 70, 50,
					tablet_cards.monsterRegion2,
					mEngine.getVertexBufferObjectManager());
			choosedmonsters.add(tablet_cards.monsterRegion2);
			monstersound.play();
			tablet_cards.mMainScene.attachChild(monster);
			deletelist.add(monster);
			break;
		}
		case "3": {

			ButtonSprite monster = new ButtonSprite(
					400 + (playerList.size() - 1) * 70, 50,
					tablet_cards.monsterRegion3,
					mEngine.getVertexBufferObjectManager());
			choosedmonsters.add(tablet_cards.monsterRegion3);
			monstersound.play();
			tablet_cards.mMainScene.attachChild(monster);
			deletelist.add(monster);
			break;
		}
		case "4": {
			ButtonSprite monster = new ButtonSprite(
					400 + (playerList.size() - 1) * 70, 50,
					tablet_cards.monsterRegion4,
					mEngine.getVertexBufferObjectManager());
			choosedmonsters.add(tablet_cards.monsterRegion4);
			monstersound.play();
			tablet_cards.mMainScene.attachChild(monster);
			deletelist.add(monster);
			break;
		}

		}
		System.out.println("onuserjoinroom() done");
	}

	@Override
	public void onUserLeftLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserLeftRoom(RoomData arg0, String name) {
		// TODO Auto-generated method stub
		handleLeave(name);
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

	static String change() {
		switch (changetime % 5) {
		case 0: {
			tablet_cards.changetime++;
			return "Detecting Players";
		}
		case 1: {
			tablet_cards.changetime++;
			return "Detecting Players.";
		}
		case 2: {
			tablet_cards.changetime++;
			return "Detecting Players..";
		}
		case 3: {
			tablet_cards.changetime++;
			return "Detecting Players...";
		}
		case 4: {
			tablet_cards.changetime++;
			return "Detecting Players....";
		}
		}
		System.out.println("changetime is "
				+ Integer.toString(tablet_cards.changetime));

		return null;

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		// 2014/11/09 as well as client part


		if (keyCode == KeyEvent.KEYCODE_BACK) { // your code
			quit_function();
		  }

		return super.onKeyDown(keyCode, event);

	}

	private void quitGame() {

		// Send delete room request
		theClient.deleteRoom(roomId);
		theClient.leaveRoom(roomId);
		theClient.disconnect();
		// Go back to waiting_room activity
		Intent intent = new Intent();
		intent.setClass(context, Waiting_room.class);
		context.startActivity(intent);
		((Activity) context).finish();
	}
}

class Card1 {
	int rank;
	int suit;

	Card1(int r, int s) {
		rank = r;
		suit = s;
	}

	public int getValue() {
		return rank;
	}

	public String getValueAsString() {
		switch (rank) {
		case 1:
			return "ace";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 6:
			return "6";
		case 7:
			return "7";
		case 8:
			return "8";
		case 9:
			return "9";
		case 10:
			return "10";
		case 11:
			return "jack";
		case 12:
			return "queen";
		default:
			return "king";
		}
	}

	@Override
	public int hashCode() {
		return getValueAsString().hashCode() * getValue();
	}

	// /

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		Card1 other = (Card1) obj;

		return (this.rank == other.rank && this.suit == other.suit);
	}
	
	public String getSuitAsString() {
		switch (suit) {
		case 1:
			return "spades";
		case 2:
			return "hearts";
		case 3:
			return "diamonds";
		default:
			return "clubs";
		}
	}
	
	public String toString() {
		return getValueAsString() + " of " + getSuitAsString();
	}
}

class Card2 extends AnimatedSprite {
	public final static int SPADES = 1;
	public final static int HEARTS = 2;
	public final static int DIAMONDS = 3;
	public final static int CLUBS = 4;

	public final static int ACE = 1; // Codes for the non-numeric cards.
	public final static int JACK = 11; // Cards 2 through 10 have their
	public final static int QUEEN = 12; // numerical values for their codes.
	public final static int KING = 13;

	public MoveModifier mod = null;

	int suit;
	int rank;
	private float X;
	private float Y;
	float xcord;
	float ycord;
	static int rel = 0;
	int fromdev = 0;

	BitmapTextureAtlas cardTextureAtlas;
	TiledTextureRegion cardTexture;
	PhysicsHandler mPhysicsHandler;
	VertexBufferObjectManager cardBufferObjectManager;
	tablet_cards maingame;

	public Card2(float vx, float vy, int theRank, int theSuit, float pX,
			float pY, tablet_cards card_activity,
			TiledTextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {

		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
		this.mPhysicsHandler.setVelocity(vx, vy);

		xcord = pX;
		ycord = pY;
		fromdev = 0;

		if (theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS
				&& theSuit != CLUBS) // && theSuit != JOKER
			throw new IllegalArgumentException("Illegal playing card suit");
		if ((theRank < 1 || theRank > 13)) // theSuit != JOKER &&
			throw new IllegalArgumentException("Illegal playing card value");
		rank = theRank;
		suit = theSuit;

		this.cardTexture = pTextureRegion;
		this.cardBufferObjectManager = pVertexBufferObjectManager;
		this.cardTextureAtlas = tablet_cards.mCardTextureAtlas;
		this.maingame = card_activity;
	}

	public Card2(int theRank, int theSuit, float pX, float pY,
			tablet_cards card_activity, TiledTextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {

		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);

		xcord = pX;
		ycord = pY;
		fromdev = 1;

		if (theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS
				&& theSuit != CLUBS) // && theSuit != JOKER
			throw new IllegalArgumentException("Illegal playing card suit");
		if ((theRank < 1 || theRank > 13)) // theSuit != JOKER &&
			throw new IllegalArgumentException("Illegal playing card value");
		rank = theRank;
		suit = theSuit;

		this.cardTexture = pTextureRegion;
		this.cardBufferObjectManager = pVertexBufferObjectManager;
		this.cardTextureAtlas = tablet_cards.mCardTextureAtlas;
		this.maingame = card_activity;

		if (fromdev == 1) {// cards swiped from clients!!!
			MoveModifier mod1;
			if (tablet_cards.game.currentPlayer.right == 0) {
				mod1 = new MoveModifier(1, this.xcord - 200, this.xcord + 90
						+ (rel++) * 60, this.ycord + 200, this.ycord);

			} else {
				mod1 = new MoveModifier(1, this.xcord + 800, this.xcord + 90
						+ (rel++) * 60, this.ycord + 200, this.ycord);

			}
			mod = mod1;
			// mod1.addModifierListener(pModifierListener);
			this.registerEntityModifier(mod1);
		}

	}

	// ///////two run on update and add listener to the card2

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		/*
		 * if(this.mX>1000||this.mY>1000) { //2014/11/06 //this.detachSelf();
		 * System.out.println("card detached from the scene"); }
		 */

		super.onManagedUpdate(pSecondsElapsed);
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionUp()) {

			/* CORRECT CODE */
			MoveModifier mod1 = new MoveModifier(1, this.xcord,
					this.xcord + 1000, this.ycord, this.ycord);
			this.registerEntityModifier(mod1);

			final tablet_cards maingame3 = this.maingame;
			final int card_value = this.rank;

			maingame3.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(maingame3, Integer.toString(card_value),
							Toast.LENGTH_SHORT).show();

				}
			});// you changed cardac to final!!

			// current value reset to zero
			if (tablet_cards.current_value != 0) {
				if (card_value > tablet_cards.current_value) {

					maingame3.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(maingame3, "player LOSES!",
									Toast.LENGTH_LONG).show();

						}
					});
				} else {

					maingame3.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(maingame3, "player wins!",
									Toast.LENGTH_LONG).show();

						}
					});

				}
				tablet_cards.current_value = 0;

			}

		}

		return true;
	}

	void setCardTexture(int front) {
	}

	public int getSuit() {
		return suit;
	}

	public int getValue() {
		return rank;
	}

	public String getSuitAsString() {
		switch (suit) {
		case SPADES:
			return "spades";
		case HEARTS:
			return "hearts";
		case DIAMONDS:
			return "diamonds";
		default:
			return "clubs";
		}
	}

	public String getValueAsString() {
		switch (rank) {
		case 1:
			return "ace";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 6:
			return "6";
		case 7:
			return "7";
		case 8:
			return "8";
		case 9:
			return "9";
		case 10:
			return "10";
		case 11:
			return "jack";
		case 12:
			return "queen";
		default:
			return "king";
		}
	}

	public String toString() {
		return getValueAsString() + " of " + getSuitAsString();
	}

	@Override
	public int hashCode() {
		return getValueAsString().hashCode() * getValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		Card2 other = (Card2) obj;

		return (this.rank == other.rank && this.suit == other.suit);
	}

	public TiledTextureRegion getcardTexture(Resources res) {
		return this.cardTexture;
	}

	public void setX(float X) {
		this.X = X;
	}

	public void setY(float Y) {
		this.Y = Y;
	}

	public float getX() {
		return this.X;
	}

	public float getY() {
		return this.Y;
	}

	public Card1 convertToCard1() {
		return new Card1(this.rank, this.suit);
	}
}

// there must be a method in deck that keeps updating the number of cards in the
// table deck
class Deck2 {
	ArrayList<Card2> cards;
	int nextcard;
	//2014/11/22
	ArrayList<Card1> dvalues = new ArrayList<Card1>();
	tablet_cards maingame;
	final tablet_cards maingame2;
	Scene cardtable; // here you add scene as attribute
	public int deckcount = 0;
	static float timeelapse = 0;
	int left = 0;

	public Deck2(tablet_cards card_activity, Scene scene,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		cards = new ArrayList<Card2>();
		maingame = card_activity;
		maingame2 = card_activity;
		int index_1, index_2;
		Random generator = new Random();
		cardtable = scene;

		//2014/11/22
//		ArrayList<Card1> dvalues = new ArrayList<Card1>();
		for (int a = 1; a <= 13; a++)
			for (int b = 1; b <= 4; b++)
				dvalues.add(new Card1(a, b));

		for (int i = 0; i < 100; i++) {
			index_1 = generator.nextInt(dvalues.size());
			index_2 = generator.nextInt(dvalues.size());

			// Card1 temp = dvalues.get( index_2 );
			int trank = dvalues.get(index_2).rank;
			int tsuit = dvalues.get(index_2).suit;
			(dvalues.get(index_2)).suit = (dvalues.get(index_1)).suit;
			(dvalues.get(index_2)).rank = (dvalues.get(index_1)).rank;
			(dvalues.get(index_1)).suit = tsuit;
			(dvalues.get(index_1)).rank = trank;
		}

		int spaceIter = 0;
		int j = 0;
		for (; j < tablet_cards.playerList.size() * 5; j++) {

			Card2 newcard = new Card2(0, 0, dvalues.get(j).rank,
					dvalues.get(j).suit, 300 + (spaceIter++) * 5, 10, maingame,
					tablet_cards.mCardTexture, pVertexBufferObjectManager);
			cards.add(newcard);
			scene.registerTouchArea(newcard);
			scene.attachChild(newcard);

		}
		
		//2014/11/22
		nextcard=j;
		
//		for (; j < 30; j++) {
//
//			Card2 newcard = new Card2(0, 0, dvalues.get(j).rank,dvalues.get(j).suit, 300 + (spaceIter++) * 5, 10, maingame,tablet_cards.mCardTexture, pVertexBufferObjectManager);
//			cards.add(newcard);
//			
//		}
	}

	public void moveCards() {// after new stack of cards
		// maybe detach first, maybe this must be implemented in a call back
		// or use wait method to make text disappear

		for (int i = 0; i < this.cards.size(); i++) {
			cards.get(i).setPosition(100f, 260f);// is these numbers valid?,
			// scene is not involved, is
			// this correct.
		}
	}

	// //yan's modification as of 2014/11/05
	public Card2 drawFromDeck(final Hand2 player) {
		System.out.println("drawfromdeck()!!!!!!");
		final Deck2 cdeck = this;

		if (cardsLeft() > 0) {

			final Card2 cardtrm = cards.remove(cards.size() - 1);
			System.out.println("timeelapse = " + timeelapse);
			
			
//			 maingame2.runOnUiThread(new Runnable() {
//			  
//			@Override public void run() { 	} });//run on ui thread	 
			
			 

			final DelayModifier dm = new DelayModifier((float) (0.1 + timeelapse), new IEntityModifierListener() {
					
						@Override
						public void onModifierStarted(
								IModifier<IEntity> pModifier, IEntity pItem) {
							System.out.println("on started!!!");

						}

						@Override
						public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
							System.out.println("on finished!!!");
							MoveModifier mod1;
							if (left == 0) {
								mod1 = new MoveModifier(0.7f, cardtrm.xcord,
										cardtrm.xcord + 1000, cardtrm.ycord,
										cardtrm.ycord + 1500);
								left = 1;
							} else {
								mod1 = new MoveModifier(0.7f, cardtrm.xcord,
										cardtrm.xcord - 1000, cardtrm.ycord,
										cardtrm.ycord + 1500);
								left = 0;

							}

							mod1.addModifierListener(new IModifierListener<IEntity>() {

								@Override
								public void onModifierStarted(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									maingame.swipesound.play();
								}

								@Override
								public void onModifierFinished(IModifier<IEntity> pModifier,IEntity pItem) {

									cdeck.deckcount++;

									if (cdeck.deckcount % 5 == 0) {
										tablet_cards.stop = true;
										maingame.sendUpdateEvent("1","20",player.cardsSendToClient2.substring(0,(player.cardsSendToClient2.length()) - 1),player.getPlayerName());
									}

								}
							});

							cardtrm.registerEntityModifier(mod1);
							System.out.println("one card sent out");

						}
					});

			
			timeelapse += 0.5;
			cardtrm.registerEntityModifier(dm);
			System.out.println("drawfromdeck done()!!!");	
			return cardtrm;

		} else {
			System.out.println("drawfromdeck done()!!!");
			return null;
		}
		
		
	}

	public String drawFromDeck() {
		//2014/11/22
//		final Card2 cardtrm = cards.remove(cards.size() - 1);		
//		return cardtrm.toString();
		
		final Card1 cardtrm = dvalues.get(nextcard);
		nextcard++;
		return cardtrm.toString();
	}
/*
	public ArrayList<Card2> drawFromDeck(int num) {

		ArrayList<Card2> tmp = new ArrayList<Card2>();
		for (int i = 0; i < num; i++) {
			if (cardsLeft() > 0) {
				tmp.add(drawFromDeck());
			} else {
				break;
			}
		}

		return tmp;
	}
*/
	public int cardsLeft() {
		return cards.size();
	}

	public Deck2 shuffle(tablet_cards cardactivity, Scene scene,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		return new Deck2(cardactivity, scene, pVertexBufferObjectManager);// is
		// this
		// position
		// correct???
	}

	public void displayAll() {
		System.out.print("displayall()");

		for (int i = 0; i < cards.size(); i++) {
			System.out.print(cards.get(i).toString() + "\t");
		}
		System.out.print("displayall() done");
	}

}

class Table2 {
	private ArrayList<Card2> cards;
	private int highestValue;
	private int score;
	Scene gamescene;

	Table2(Scene scene) {
		this.cards = new ArrayList<Card2>();
		this.highestValue = Integer.MIN_VALUE;
		this.score = 0;
		this.gamescene = scene;
		// assert isValidCards();
	}

	public void addCard(Card2 c) {
		// assert isValidAddedCard(c);
		// c.setCardTexture(1);

		this.cards.add(c);// this.highestValue =
		// CardValue2.values.get(c.convertToCard1()).intValue();
		// this.score += getCardScore(c);
		// assert isValidCards();
	}

	public int getHighestValue() {
		return this.highestValue;
	}

	public int getTableScore() {
		return this.score;
	}

	public void clear() {

		System.out.println("Size of card2d: "
				+ Integer.toString(tablet_cards.card2d.size()));
		for (int i = 0; i < tablet_cards.card2d.size(); i++) {
			// gamescene.detachChild(tablet_cards.card2d.get(i));
			tablet_cards.card2d.get(i).detachSelf();
		}
		tablet_cards.card2d.clear();
		this.cards = new ArrayList<Card2>();
		this.highestValue = Integer.MIN_VALUE;
		this.score = 0;
		// assert isValidCards();
	}

	public void displayAll() {
		System.out.println("displayall()");

		ListIterator<Card2> iter = this.cards.listIterator();
		while (iter.hasNext()) {
			Card2 c = iter.next();
			int v = CardValue2.values.get(c.convertToCard1()).intValue();
			System.out.println(c + "  " + v); // calls cards[x].toString()
		}

		System.out.println("highest value is " + this.highestValue);
		System.out.println("table score is " + this.score);
		System.out.println("card count is " + this.cards.size());
		System.out.println("displayall() done");
	}
}

class CardValue2 {
	static final public Map<Card1, Integer> values;

	static {
		values = new HashMap<Card1, Integer>();
		for (int a = 1; a <= 13; a++) {
			for (int b = 1; b <= 4; b++) {
				int value;
				switch (a) {
				case 4:
					value = 1;
					break;
				case 6:
					value = 2;
					break;
				case 8:
					value = 3;
					break;
				case 9:
					value = 4;
					break;
				case 10:
					value = 5;
					break;
				case 11:
					value = 6;
					break;
				case 12:
					value = 7;
					break;
				case 13:
					value = 8;
					break;
				case 1:
					value = 9;
					break;
				case 3:
					value = 10;
					break;
				case 2:
					value = 11;
					break;
				case 5:
					value = 12;
					break;
				default:
					value = 13;
					break;
				}
				values.put(new Card1(a, b), value);
			}
		}
	}

}

interface Game2 {

	public void play();

	public void replay();

	public ArrayList<Hand2> getWinner();
}

class Hand2 {
	private String playerName;
	public ArrayList<Card2> cards;
	private int score=0;
	private final int MAX_CARDS = 5;
	tablet_cards maingame;
	private int goldcoin;
	// 2014/11/05
	public String cardsSendToClient2 = "";

	// 2014/11/13
	public int right;

	public Hand2(tablet_cards cardactivity, String pn, Deck2 d) {
		maingame = cardactivity;
		this.playerName = pn;
		this.cards = new ArrayList<Card2>();
		goldcoin = 0;
		System.out.println("new hand2: " + pn);

		for (int i = 0; i < 5; i++) {
			Card2 drawcard = d.drawFromDeck(this);
			this.cards.add(drawcard);
			cardsSendToClient2 += drawcard.toString() + ";";
			this.score = 0;
		}

		System.out.println(maingame.servername + ": trying to update " + pn
				+ " status to 20");
	}

	public Card2 getCardAtIndex(int index) {
		if (index >= 0 && index < this.cards.size()) {
			return this.cards.get(index);
		} else {
			return null;
		}
	}

	public void addCardsFromDeck(Deck2 d) {// you should keep updating the
		// number of cards each user has
		for (int i = 0; i < MAX_CARDS - getCardCount(); i++) {
			this.cards.add(d.drawFromDeck(this));

		}

		sortByValue();
	}

	public void removeCard(Card2 c) {
		this.cards.remove(c);
	}

	public void removeCardAtIndex(int index) {
		this.cards.remove(index);
	}

	public void exchangeCard(int index_1, int index_2) {// here is this correct?
		Card2 temp = cards.get(index_2);
		cards.set(index_2, cards.get(index_1));
		cards.set(index_1, temp);
	}

	public void addScore(int s) {
		this.score += s;
	}

	public int getScore() {
		return this.score;
	}

	public void addCoin(int s) { 
		this.goldcoin += s; 
	} 

	public int getCoin() { 
		return this.goldcoin; 
	}



	public void sortByValue() {
		Collections.sort(this.cards, new CardComparator());
	}

	public int getSmallestCardValue() {
		sortByValue();

		Card2 c = this.cards.get(0);
		int v = CardValue2.values.get(c.convertToCard1()).intValue();

		System.out.println("Hand class: smallest card of " + this.playerName
				+ " is " + c.toString());

		return v;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public ListIterator<Card2> displayAll() {
		System.out.println("player " + this.playerName + " has cards:");

		ListIterator<Card2> iter = this.cards.listIterator();
		while (iter.hasNext()) {
			Card2 c = iter.next();
			int v = CardValue2.values.get(c.convertToCard1()).intValue();
			System.out.println(c + "  " + v); // calls cards[x].toString()
		}
		return iter;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		Hand2 other = (Hand2) obj;

		return (this.playerName.equals(other.playerName));
	}

	public int getCardCount() {
		return this.cards.size();
	}

}

class CardComparator implements Comparator<Card2> {
	public int compare(Card2 c1, Card2 c2) {
		int v1 = CardValue2.values.get(c1.convertToCard1()).intValue();
		int v2 = CardValue2.values.get(c2.convertToCard1()).intValue();
		return (v1 - v2);
	}
}

class SevenFiveTwoThreeGame2 implements Game2 {

	ArrayList<Hand2> players;
	public Deck2 deck;
	public Table2 table;
	int current_table_count = 0;
	Hand2 currentPlayer;
	int skipCount;
	boolean endflag = false;
	private Scanner in;

	tablet_cards maingame;
	final tablet_cards maingame2;

	VertexBufferObjectManager gameVBOM;
	int highestvalue123 = 0;
	Scene gamescene;

	public String gamewinner = "";
	public int winscore = 0;
	int totalGoldsCoins = 0;

	SevenFiveTwoThreeGame2(ArrayList<String> playerNames,
			tablet_cards cardactivity, Scene scene,
			VertexBufferObjectManager pVertexBufferObjectManager) {

		maingame = cardactivity;
		maingame2 = cardactivity;
		gameVBOM = pVertexBufferObjectManager;
		gamescene = scene;
		this.deck = new Deck2(cardactivity, scene, pVertexBufferObjectManager);
		

		this.players = new ArrayList<Hand2>();

		System.out.println("size of players: " + playerNames.size());
		for (String pn : playerNames) {

			this.players.add(new Hand2(maingame, pn, this.deck));

		}

		// 2014/11/13
		int pos = 0;
		for (Hand2 thisplayer : this.players) {
			thisplayer.right = pos;
			pos++;
		}

		System.out.println("number of cards remain: " + this.deck.cardsLeft());

		this.table = new Table2(scene);

		this.currentPlayer = null;
		tablet_cards.currentPlayer = null;
		this.skipCount = 0;

		this.in = new Scanner(System.in);
	}

	public void endgame2() {
		// TODO Auto-generated method stub
		System.out.println("endofgame2()");
		int max_count = -1;
		String winner = "";

		/*
		 * for(Hand2 tmp:this.players) { if(max_count<tmp.getScore()) {
		 * max_count = tmp.getScore(); winner = tmp.getPlayerName(); } }
		 */

		// 2014/11/11
		for (int i = 0; i < this.players.size(); i++) {
			if (max_count < this.players.get(i).getScore()
					+ tablet_cards.lastscore[i]) {
				max_count = this.players.get(i).getScore()
						+ tablet_cards.lastscore[i];
				winner = this.players.get(i).getPlayerName();
			}
		}

		int numwinners = 0;
		// 2014/11/11
		/*
		 * for(Hand2 tmp:this.players) { if(max_count == tmp.getScore()){
		 * numwinners++; } }
		 */

		// 2014/11/11
		for (int i = 0; i < this.players.size(); i++) {
			if (max_count == this.players.get(i).getScore()
					+ tablet_cards.lastscore[i]) {
				numwinners++;
			}
		}
		final int numwinners2 = numwinners;
		int mininum_gold = 100 ;
		for(int i=0; i<this.players.size();i++){ 
			if(this.players.get(i).getCoin() < mininum_gold){ 
				mininum_gold = this.players.get(i).getCoin();
			} 
		} 
		for(int i=0; i<this.players.size();i++){ 
			if(max_count == this.players.get(i).getScore()+tablet_cards.lastscore[i]){ 
				totalGoldsCoins -= this.players.get(i).getCoin()+mininum_gold;
				tablet_cards.lastgoldcoins[i] = tablet_cards.lastgoldcoins[i] + mininum_gold;
				
			} 
		} 
		for(int i=0; i<this.players.size();i++){ 
			if(max_count != this.players.get(i).getScore()+tablet_cards.lastscore[i]){ 
				tablet_cards.lastgoldcoins[i] = tablet_cards.lastgoldcoins[i] - this.players.get(i).getCoin() + totalGoldsCoins;
			} 
		} 
		
		
		

		final String winner2 = winner;
		gamewinner = winner;
		final String max_count2 = Integer.toString(max_count);
		winscore = max_count;

		maingame.triumph.play();

		maingame.runOnUpdateThread(new Runnable() {
			@Override
			public void run() {

				tablet_cards.move2right=new MoveModifier(1.0f,-500,0,0,0);
				tablet_cards.move2left=new MoveModifier(1.0f,900,400,0,0);
				tablet_cards.move2left.addModifierListener(tablet_cards.afterdis);
				
				//here this must be wrong no listener!!!!!!2014/11/30
				tablet_cards.leftHalfSprite.registerEntityModifier(tablet_cards.move2right);
				tablet_cards.rightHalfSprite.registerEntityModifier(tablet_cards.move2left);
				tablet_cards.mMainScene.attachChild(tablet_cards.leftHalfSprite);
				tablet_cards.mMainScene.attachChild(tablet_cards.rightHalfSprite);	

				if (winscore > 0) {
					if (numwinners2 > 1) {
						tablet_cards.winnote = new Text(360, 100, maingame.komika, " tie!",
								gameVBOM);

					} else {
						gamewinner = winner2.substring(0,
								winner2.lastIndexOf("_"));

						tablet_cards.winnote = new Text(360, 100, maingame.komika,
								gamewinner + " wins!", gameVBOM);
					}

				} else {
					tablet_cards.winnote = new Text(360, 100, maingame.komika,
							"no one wins!", gameVBOM);
				}
//
//				MoveModifier mod7 = new MoveModifier(1, 300, 300, -190, 230);
//				maingame.score0Sprite.registerEntityModifier(mod7);
//				gamescene.attachChild(maingame.score0Sprite);
//				gamescene.registerTouchArea(maingame.score0Sprite);
//				tablet_cards.deletelist.add(maingame.score0Sprite);
//
//				MoveModifier mod3 = new MoveModifier(1, 300, 300, -140, 280);
//				maingame.ok0Sprite.registerEntityModifier(mod3);
//				gamescene.attachChild(maingame.ok0Sprite);
//				gamescene.registerTouchArea(maingame.ok0Sprite);
//				tablet_cards.deletelist.add(maingame.ok0Sprite);
//
//				MoveModifier mod4 = new MoveModifier(1, 300, 300, -90, 330);
//				maingame.playanother0Sprite.registerEntityModifier(mod4);
//				gamescene.attachChild(maingame.playanother0Sprite);
//				gamescene.registerTouchArea(maingame.playanother0Sprite);
//				System.out.println("playanother0Sprite registered");
//				tablet_cards.deletelist.add(maingame.playanother0Sprite);
//
//				MoveModifier mod9 = new MoveModifier(1, 300, 300, -40, 380);
//				maingame.continueSprite.registerEntityModifier(mod9);
//				gamescene.attachChild(maingame.continueSprite);
//				gamescene.registerTouchArea(maingame.continueSprite);
//				System.out.println("continueSprite registered");
//				tablet_cards.deletelist.add(maingame.continueSprite);

			}
		});

		int i = 0;

		for (Hand2 tmp : this.players)// here you need separate scores for each
			// and record each person's score, "22"								// is to notify each player
		{
			//maingame.sendUpdateEvent("1","22",winner2.substring(0, winner2.lastIndexOf("_"))+" wins!",tmp.getPlayerName(),tablet_cards.lastgoldcoins[i]); 
			maingame.sendUpdateEvent("1","22",winner2,tmp.getPlayerName(),tablet_cards.lastgoldcoins[i]); 
			i++; 

		}

		maingame.runOnUpdateThread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < tablet_cards.card2d.size(); i++) {
					Card2 thiscard = tablet_cards.card2d.get(i);
					thiscard.unregisterEntityModifier(thiscard.mod);
					MoveModifier mod2 = new MoveModifier(1, 300, 300, 160, 1160);
					thiscard.registerEntityModifier(mod2);

				}
				tablet_cards.card2d.clear();
				Card2.rel = 0;
			}
		});

		System.out.println("endofgame2() done");
	}

	public void play() {

		chooseFirstPlayer();

		if (!isEndOfGame()) {
			allPlayersAddCards();
			round();
		}
	}

	public ArrayList<Hand2> getWinner() {
		ListIterator<Hand2> iter = this.players.listIterator();
		ArrayList<Hand2> winner = new ArrayList<Hand2>();
		int highestScore = 0;
		while (iter.hasNext()) {
			Hand2 p = iter.next();
			int s = p.getScore();
			if (s != 0 && s > highestScore) {
				highestScore = s;
				winner.add(p);// ////////////??????????????????
			}
		}
		return winner;
	}

	public void replay(tablet_cards cardactivity, Scene scene,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		this.deck = new Deck2(cardactivity, scene, pVertexBufferObjectManager);
		this.table.clear();
		this.currentPlayer = null;
		tablet_cards.currentPlayer = null;
		this.skipCount = 0;

		play();
	}

	void round() {
		System.out.println("round()");

		if (this.skipCount != this.players.size() - 1) {

			System.out.println("skip count is " + this.skipCount);

			currentPlayerAction();

			System.out.println("current player is "
					+ this.currentPlayer.getPlayerName());
		} else {
			tablet_cards.lastPlayer.addScore(this.table.getTableScore());
			tablet_cards.currentPlayer = tablet_cards.lastPlayer;

			final String pname = this.currentPlayer.getPlayerName();
			this.currentPlayer.addScore(current_table_count);

			current_table_count = 0;

			final int score = this.currentPlayer.getScore();

			System.out.println("Round() done");
			final int currectplayer_right=this.currentPlayer.right;
			maingame.runOnUpdateThread(new Runnable() {
				@Override
				public void run() {
					
					for (int i = 0; i < tablet_cards.card2d.size(); i++) {
						Card2 thiscard = tablet_cards.card2d.get(i);
						thiscard.unregisterEntityModifier(thiscard.mod);
						System.out.println("this is the " + i+ "th card on the table");
						MoveModifier mod2 = new MoveModifier(1, 300, 300, 160,
								1160);
						thiscard.registerEntityModifier(mod2);
					}
					
					tablet_cards.card2d.clear(); 
					Card2.rel = 0;
					
					ParallelEntityModifier para3;
					if(currectplayer_right==0){
						para3 = new ParallelEntityModifier(
								new AlphaModifier(0.5f, 1, 0),
								new MoveModifier(1,170,170,80,-220)
								);	
					}
					else{
						para3 = new ParallelEntityModifier(
								new AlphaModifier(0.5f, 1, 0),
								new MoveModifier(1,620,620,85,-215)
								);
						
					}
					
					para3.addModifierListener(new IModifierListener<IEntity>() {
								@Override
								public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
								}
								@Override
								public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
									if(currectplayer_right==0){
										gamescene.detachChild(tablet_cards.score1);
										tablet_cards.score1 = new Text(170, 80, tablet_cards.komika, String
												.valueOf(score),
												gameVBOM);
										//tablet_cards.score1.registerEntityModifier(para3.deepCopy());
										gamescene.attachChild(tablet_cards.score1);
										
									}
									else{	
										gamescene.detachChild(tablet_cards.score2);
										tablet_cards.score2 = new Text(620,85, tablet_cards.komika, String
												.valueOf(score),
												gameVBOM);
										//tablet_cards.score2.registerEntityModifier(para3.deepCopy());
										gamescene.attachChild(tablet_cards.score2);				
										
									}
								}
							});
					
					if(currectplayer_right==0){
						tablet_cards.score1.registerEntityModifier(para3);	
					}
					else{
						tablet_cards.score2.registerEntityModifier(para3);

					}
					//
					
					//2014/11/18,update scores done
			}});

			this.skipCount = 0;

			allPlayersAddCards();

			System.out.println("this round the winner "
					+ this.currentPlayer.getPlayerName() + " has score "
					+ this.currentPlayer.getScore());
			System.out.println("one round ended!!!!!!");
			this.highestvalue123 = 0;
			round();
		}

	}

	// gamestart, endround,onchatreceived()
	private void currentPlayerAction() {
		System.out.println("Currentplayeraction()");
		// maingame.sendUpdateEvent("5",this.currentPlayer.getPlayerName());



		maingame.sendUpdateEvent("5",this.currentPlayer.getPlayerName(),this.highestvalue123,this.currentPlayer.getScore());///////////////send current player's score 



		System.out.println("currentplayeraction update status2 to 5");
		System.out.println("Currentplayeraction() done");
	}

	private void allPlayersAddCards() {

		System.out.println("allPlayersAddCards()");
		ListIterator<Hand2> iter = this.players.listIterator();
		while (iter.hasNext()) {
			Hand2 player_1 = iter.next();
			if (player_1.equals(this.currentPlayer)) {
				player_1.addCardsFromDeck(this.deck);
				while (iter.hasNext()) {
					Hand2 tmp = iter.next();
					tmp.addCardsFromDeck(this.deck);
				}
			}
		}

		iter = this.players.listIterator();
		while (iter.hasNext()) {
			Hand2 player_2 = iter.next();
			if (!player_2.equals(this.currentPlayer)) {
				player_2.addCardsFromDeck(this.deck);
			} else {
				break;
			}
		}

		iter = this.players.listIterator();
		while (iter.hasNext()) {
			iter.next().displayAll();
		}
		this.deck.displayAll();
		System.out.println("allplayersaddcards() done");
	}

	public boolean isMessageReceivedFromPlayer() {
		// TODO Auto-generated method stub
		return true;
		// return false;
	}

	public boolean getSkipChoosingMessageFromPlayer() {
		return true;
	}

	public Card2 getCardChoosingMessageFromPlayer() {
		// TODO Auto-generated method stub
		return tablet_cards.CardSelectedFromPlayer;
	}

	private void chooseFirstPlayer() {
		System.out.println("choosefirstplayer()");
		Hand2 firstPlayer = null;
		int smallest = Integer.MAX_VALUE;

		ListIterator<Hand2> iter = players.listIterator(players.size());
		while (iter.hasPrevious()) {
			Hand2 tmp = iter.previous();
			int v = tmp.getSmallestCardValue();
			System.out.println("smallest card value of " + tmp.getPlayerName()
					+ " is " + v);

			if (v <= smallest) {
				smallest = v;
				firstPlayer = tmp;
			}
		}

		this.currentPlayer = firstPlayer;
		tablet_cards.currentPlayer = firstPlayer;

		// firstPlayer.turn = true;//weiyi added this
		// return firstPlayer;//weiyi added this

		final String pname = this.currentPlayer.getPlayerName();

		/*
		 * maingame2.runOnUiThread(new Runnable() {
		 * 
		 * @Override public void run() { Toast.makeText(maingame2,
		 * pname+" go first!", Toast.LENGTH_LONG).show(); } });
		 */

		System.out.println(pname + " go first!");
		System.out.println("choosefirstplayer() done");
	}

	Hand2 chooseNextPlayer() {
		System.out.println("choosenextplayer()");
		ListIterator<Hand2> iter = this.players.listIterator();
		Hand2 np = null;
		while (iter.hasNext()) {
			Hand2 p = iter.next();
			if (p.equals(this.currentPlayer)) {
				if (iter.hasNext()) {
					np = iter.next();

					final Hand2 np2 = np;
					/*
					 * maingame.runOnUiThread(new Runnable() {
					 * 
					 * @Override public void run() { Toast.makeText(maingame,
					 * np2.getPlayerName()+" plays next!",
					 * Toast.LENGTH_SHORT).show(); } });
					 */

					System.out.println("next player is" + np.getPlayerName());
					System.out.println("ChooseNextPlayer() done");

					return np;

				} else {

					/*
					 * final Hand2 np3=this.players.get(0);
					 * maingame.runOnUiThread(new Runnable() {
					 * 
					 * @Override public void run() { Toast.makeText(maingame,
					 * np3.getPlayerName()+" plays next!",
					 * Toast.LENGTH_SHORT).show(); } });
					 */
					System.out.println("ChooseNextPlayer() done");
					System.out.println("next player is"
							+ this.players.get(0).getPlayerName());
					return this.players.get(0);

				}
			}
		}
		// cannot find current player, this is wrong.
		maingame.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(maingame, "no players available!",
						Toast.LENGTH_SHORT).show();
			}
		});

		System.out.println("choosenextplayer() done");
		return null;
	}

	private boolean isEndOfGame() {
		System.out.println("IsEndOfGame()");
		ListIterator<Hand2> iter = this.players.listIterator();
		int totalScore = 0;
		while (iter.hasNext()) {
			Hand2 p = iter.next();
			totalScore += p.getScore();
		}

		System.out.println("IsEndOfGame() done");
		return (totalScore == 100);
	}

	@Override
	public void replay() {
		// TODO Auto-generated method stub

	}
	
}
