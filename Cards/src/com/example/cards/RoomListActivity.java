package com.example.cards;

import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.AllRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.AllUsersEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveUserInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.MatchedRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ZoneRequestListener;

public class RoomListActivity extends Activity implements ZoneRequestListener {

	private WarpClient theClient;
	private RoomListAdapter roomlistAdapter;
	private ListView listView;
	private ProgressDialog progressDialog = null;
	private String model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);	
	  	setContentView(R.layout.room_list);
		listView = (ListView) findViewById(R.id.roomList);
		roomlistAdapter = new RoomListAdapter(this);
		init();
	}

	private void init() {
		try {
			theClient = WarpClient.getInstance();
			Intent intent = getIntent();
			model = intent.getStringExtra("model");
			if (model.equals("Client")) {
				ImageButton btn = (ImageButton) RoomListActivity.this
						.findViewById(R.id.joinnewroom);
				btn.setVisibility(4);
			}
		} catch (Exception ex) {
			Utils.showToastAlert(this, "Exception in Initilization");
		}
	}

	public void onStart() {
		super.onStart();
		theClient.addZoneRequestListener(this);
		theClient.getRoomInRange(1, 4);// trying to get room with at least one
										// user
	}

	public void onStop() {
		super.onStop();
		theClient.removeZoneRequestListener(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		theClient.disconnect();
	}

	public void joinRoom(String roomId) {
		if (roomId != null && roomId.length() > 0) {
			goToGameScreen(roomId);
		} else {
			Log.d("joinRoom", "failed:" + roomId);
		}
	}

	public void onJoinNewRoomClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "Pleaes wait...");
		progressDialog.setCancelable(true);
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("topLeft", "");
		properties.put("topRight", "");
		properties.put("bottomLeft", "");
		properties.put("bottomRight", "");
		theClient.createRoom("" + System.currentTimeMillis(), "Saurav", 4,
				properties);
		
	}

	@Override
	public void onCreateRoomDone(final RoomEvent event) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (progressDialog != null) {
					progressDialog.dismiss();
					progressDialog = null;
				}
				if (event.getResult() == WarpResponseResultCode.SUCCESS) {// if
																			// room
																			// created
																			// successfully
					String roomId = event.getData().getId();
					joinRoom(roomId);
					Log.d("onCreateRoomDone", event.getResult() + " " + roomId);
				} else {
					Utils.showToastAlert(RoomListActivity.this,
							"Room creation failed...");
				}
			}
		});
	}

	@Override
	public void onDeleteRoomDone(RoomEvent event) {

	}

	@Override
	public void onGetAllRoomsDone(AllRoomsEvent event) {

	}

	@Override
	public void onGetLiveUserInfoDone(LiveUserInfoEvent event) {

	}

	@Override
	public void onGetMatchedRoomsDone(final MatchedRoomsEvent event) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				RoomData[] roomDataList = event.getRoomsData();
				if (roomDataList != null && roomDataList.length > 0) {
					roomlistAdapter.setData(roomDataList);
					listView.setAdapter(roomlistAdapter);
				} else {
					roomlistAdapter.clear();
				}
			}
		});
	}

	@Override
	public void onGetOnlineUsersDone(AllUsersEvent arg0) {

	}

	@Override
	public void onSetCustomUserDataDone(LiveUserInfoEvent arg0) {

	}

	private void goToGameScreen(String roomId) {
		if (model.equals("Client")) {
			Intent intent = new Intent(this, Client_part.class);
			intent.putExtra("roomId", roomId);
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, tablet_cards.class);
			intent.putExtra("roomId", roomId);
			startActivity(intent);
		}

	}

}
