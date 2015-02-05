package com.example.cards;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class Waiting_room_page extends SurfaceView implements Callback
{

	private Context context;
	private SurfaceHolder sfh;
	private Paint paint;	
	public Waiting_room_page(Context context,AttributeSet atts)
	{
		super(context,atts);
		// TODO Auto-generated constructor stub
		this.context = context;
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
	}
	

	@Override
	
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
}