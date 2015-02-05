package com.example.cards;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;


public class AnimatedSpritesExample extends SimpleBaseGameActivity {
	
	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 320;

	private BuildableBitmapTextureAtlas mBitmapTextureAtlas;

	private TiledTextureRegion mULoseTextureRegion;
	private TiledTextureRegion mUWinTextureRegion;
	private TiledTextureRegion mCurtainTextureRegion;
	private TiledTextureRegion mCryTextureRegion;
	ITextureRegion mBackgroundTextureRegion;
	private TiledTextureRegion mJumpTextureRegion;
	private ITextureRegion mWinBackgroundTextureRegion;
	private BuildableBitmapTextureAtlas mBitmapTextureAtlas2;
	private BuildableBitmapTextureAtlas mBitmapTextureAtlas3;
	private BuildableBitmapTextureAtlas mBitmapTextureAtlas4;

	@Override
	public EngineOptions onCreateEngineOptions() {
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		System.out.println("lose:1");
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}

	@Override
	public void onCreateResources() {

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		System.out.println("lose:2");
		this.mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 512,512, TextureOptions.NEAREST);
		this.mBitmapTextureAtlas2 = new BuildableBitmapTextureAtlas(this.getTextureManager(), 512,512, TextureOptions.NEAREST);
		this.mBitmapTextureAtlas3 = new BuildableBitmapTextureAtlas(this.getTextureManager(), 512,512, TextureOptions.NEAREST);
		this.mBitmapTextureAtlas4 = new BuildableBitmapTextureAtlas(this.getTextureManager(), 512,512, TextureOptions.NEAREST);
		
		this.mCurtainTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "curtain4.png", 5, 5);
		if(this.mCurtainTextureRegion==null){
			System.out.println("this.mCurtainTextureRegion is null");
		}
		
		this.mCryTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas2, this, "cry2.png", 4, 4);
		if(this.mCryTextureRegion==null){
			System.out.println("mCryTextureRegion is null");
		}
		
//		this.mUWinTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "uwin4.png", 2, 2);
//		if(this.mUWinTextureRegion==null){
//			System.out.println("mUWinTextureRegion is null");
//		}
		
		this.mULoseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas3, this, "ulose2.png", 2, 2);
		if(this.mULoseTextureRegion==null){
			System.out.println("mULoseTextureRegion is null");
		}
		
		this.mJumpTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas4, this, "jump3.png", 2, 1);
		if(this.mJumpTextureRegion==null){
			System.out.println("this.mJumpTextureRegion is null");
		}
		
		try{
		ITexture backgroundTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/sky2.png");
						}
					});

		backgroundTexture.load();
		this.mBackgroundTextureRegion = TextureRegionFactory
				.extractFromTexture(backgroundTexture);
		
		ITexture winBackgroundTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/light.png");
						}
					});
		

		winBackgroundTexture.load();
		this.mWinBackgroundTextureRegion = TextureRegionFactory
				.extractFromTexture(winBackgroundTexture);
		
		}catch(Exception e){
			Debug.e(e);
		}
		
		try {
			this.mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			this.mBitmapTextureAtlas.load();
			
			this.mBitmapTextureAtlas2.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			this.mBitmapTextureAtlas2.load();
			
			this.mBitmapTextureAtlas3.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			this.mBitmapTextureAtlas3.load();
			
			this.mBitmapTextureAtlas4.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			this.mBitmapTextureAtlas4.load();
			
			
		} catch (Exception e) {
			Debug.e(e);
		}
	}

	@Override
	public Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		System.out.println("lose:3");
		final Scene scene = new Scene();
		
		try
		{
		
			final Sprite backgroundSprite = new Sprite(0, 0,this.mBackgroundTextureRegion,getVertexBufferObjectManager())
				{
				@Override
				public boolean onAreaTouched(TouchEvent pTouchEvent,
						float pTouchAreaLocalX, float pTouchAreaLocalY) {
					if (pTouchEvent.isActionUp()) {
						System.out.println("ends!!");
						AnimatedSpritesExample.this.finish();
					}
					return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
							pTouchAreaLocalY);
				}
			};
			scene.registerTouchArea(backgroundSprite);
			scene.attachChild(backgroundSprite);
			
			
			final AnimatedSprite lose = new AnimatedSprite(140,60, this.mULoseTextureRegion, this.getVertexBufferObjectManager());
			lose.animate(300);
			scene.attachChild(lose);
			System.out.println("ends!!2");
			
			final AnimatedSprite cry = new AnimatedSprite(200,140, this.mCryTextureRegion, this.getVertexBufferObjectManager());
			cry.animate(100);
			scene.attachChild(cry);
			System.out.println("ends!!3");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return scene;
	}
}
