package com.example.cards;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
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

public class Win extends SimpleBaseGameActivity {
	
	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 320;

	private BuildableBitmapTextureAtlas mBitmapTextureAtlas;
	private TiledTextureRegion mUWinTextureRegion;
	private TiledTextureRegion mJumpTextureRegion;
	private ITextureRegion mWinBackgroundTextureRegion;
	private BuildableBitmapTextureAtlas mBitmapTextureAtlas2;

	@Override
	public EngineOptions onCreateEngineOptions() {
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}

	@Override
	public void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		this.mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(),512,512, TextureOptions.NEAREST);
		this.mBitmapTextureAtlas2= new BuildableBitmapTextureAtlas(this.getTextureManager(),512,512, TextureOptions.NEAREST);
		
		this.mUWinTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "uwin4.png", 2, 2);
		this.mJumpTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas2, this, "jump3.png", 2, 1);


		ITexture winBackgroundTexture = null;
		try {
			winBackgroundTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {
						@Override
						public InputStream open() throws IOException {
							return getAssets().open("gfx/light.png");
						}
					});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		winBackgroundTexture.load();
		this.mWinBackgroundTextureRegion = TextureRegionFactory
				.extractFromTexture(winBackgroundTexture);
		
		
		
		try {
			this.mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			this.mBitmapTextureAtlas.load();
			
			this.mBitmapTextureAtlas2.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			this.mBitmapTextureAtlas2.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}

	@Override
	public Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene();
		
		Sprite winBackgroundSprite = new Sprite(0, 0,
				this.mWinBackgroundTextureRegion,
				getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent pTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pTouchEvent.isActionUp()) {
					System.out.println("ends!!");
					Win.this.finish();
				}
				return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		
		scene.registerTouchArea(winBackgroundSprite);
		scene.attachChild(winBackgroundSprite);
		
		final AnimatedSprite win = new AnimatedSprite(140,40, this.mUWinTextureRegion, this.getVertexBufferObjectManager());
		win.animate(300);
		scene.attachChild(win);
				
		final AnimatedSprite jump = new AnimatedSprite(130,80, this.mJumpTextureRegion, this.getVertexBufferObjectManager());
		jump.animate(300);
		scene.attachChild(jump);

		return scene;
	}
}
