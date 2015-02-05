package com.example.cards;


import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Waiting_room extends Activity implements ConnectionRequestListener
{	
	private Context context;
	private Button client_button, server_button;
	private Waiting_room_page Waiting_room_view;
	BluetoothAdapter mBluetoothAdapter;
	private WarpClient theClient;
	private EditText nameEditText;
    private ProgressDialog progressDialog;
    private boolean isConnected = false;
	private int selectedMonster;
	static int selectedMonster2;
	private String model;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);	
		setContentView(R.layout.waiting_room);
		context = this;
		nameEditText = (EditText)findViewById(R.id.nameEditText);
		selectedMonster = -1;
		model = "Client";
		init();
    	RadioGroup group = (RadioGroup)this.findViewById(R.id.radioGroup);

		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) { // TODO
																		// Auto-generated
																		// method
																		// stub

				int radioButtonId = arg0.getCheckedRadioButtonId();
				RadioButton rb = (RadioButton) Waiting_room.this
						.findViewById(radioButtonId);
				if (rb.getText().equals("Client")) {
					model = "Client";
				} else {
					model = "Server";
				}
			}
		});
	}
//	public void onMonsterClicked(View view){
//		switch (view.getId()) {
//			case R.id.chatBubble:{
//				selectedMonster = 1;
//				Waiting_room.selectedMonster2=selectedMonster; 
//			break;}
//			case R.id.imageView2:{
//				selectedMonster = 2;
//				Waiting_room.selectedMonster2=selectedMonster;
//			break;}
//			case R.id.imageView3:{
//				selectedMonster = 3;
//				Waiting_room.selectedMonster2=selectedMonster;
//			break;}
//			case R.id.imageView4:{
//				selectedMonster = 4;
//				Waiting_room.selectedMonster2=selectedMonster;
//			break;}
//		}
//	}
	
	public void onMonsterClicked(View view){
		int duration=1000;
		ImageView imageView = (ImageView) view;
		TranslateAnimation anim = new TranslateAnimation(
				0,0,0,-15);
		anim.setDuration(duration);
		//		anim.setFillAfter(true);
		imageView.startAnimation(anim);
		if(selectedMonster != -1) {
			ImageView oldMonster = null;
			switch (selectedMonster) {
			case 1:
				oldMonster = (ImageView) findViewById(R.id.chatBubble);
				oldMonster.setImageResource(R.drawable.monster1);
				break;
			case 2:
				oldMonster = (ImageView) findViewById(R.id.imageView2);
				oldMonster.setImageResource(R.drawable.monster2);
				break;
			case 3:
				oldMonster = (ImageView) findViewById(R.id.imageView3);
				oldMonster.setImageResource(R.drawable.monster3);
				break;
			case 4:
				oldMonster = (ImageView) findViewById(R.id.imageView4);
				oldMonster.setImageResource(R.drawable.monster4);
				break;
			default:
				break;
			}
		}
		switch (view.getId()) {
		case R.id.chatBubble:{
			selectedMonster = 1;
			Waiting_room.selectedMonster2=selectedMonster;
			imageView.setImageResource(R.drawable.monster1new);
			break;}
		case R.id.imageView2:{
			selectedMonster = 2;
			Waiting_room.selectedMonster2=selectedMonster;
			imageView.setImageResource(R.drawable.monster2new);
			break;}
		case R.id.imageView3:{ 
			selectedMonster = 3;
			Waiting_room.selectedMonster2=selectedMonster;
			imageView.setImageResource(R.drawable.monster3new);
			break;}
		case R.id.imageView4:{
			selectedMonster = 4;
			Waiting_room.selectedMonster2=selectedMonster;
			imageView.setImageResource(R.drawable.monster4new);
			break;}
		}
	}
	
	
	public void onPlayGameClicked(View view){
		String nameInput = nameEditText.getText().toString().trim();
		if(nameInput.equals("")){
			Toast.makeText(this, "Please input player name.", Toast.LENGTH_LONG).show();
			return;
		}
		if(selectedMonster == -1) {
			Toast.makeText(this, "Please choose a monster.", Toast.LENGTH_LONG).show();
			return;
		}
		
		String userName = nameInput + "_@" + selectedMonster;
		Utils.userName = userName;
		Log.d("Name to Join", ""+userName);
		theClient.connectWithUserName(userName);
		progressDialog =  ProgressDialog.show(this, "", "connecting to appwarp");
		
	}
	private void init(){
		WarpClient.initialize(Constants.apiKey, Constants.secretKey);
        try {
            theClient = WarpClient.getInstance();
        } catch (Exception ex) {
        	Utils.showToastAlert(this, "Exception in Initilization");
        }
        theClient.addConnectionRequestListener(this); 
    }
	public void onConnectDone(final ConnectEvent event) {
		Log.d("onConnectDone", event.getResult()+"");
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if(progressDialog!=null){
					progressDialog.dismiss();
					progressDialog=null;
				}
			}
		});
		if(event.getResult() == WarpResponseResultCode.SUCCESS){// go to room  list 
			isConnected = true;
			Intent intent = new Intent(Waiting_room.this, RoomListActivity.class);
			intent.putExtra("model", model);
			startActivity(intent);
		}else{
			isConnected = false;
			Utils.showToastOnUIThread(Waiting_room.this, "connection failed");
		}
	}
	@Override
	public void onDisconnectDone(final ConnectEvent event) {
		Log.d("onDisconnectDone", event.getResult()+"");
		isConnected = false;
	}
	@Override
	public void onInitUDPDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}
}