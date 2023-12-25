package com.example.bookit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.util.Log;
import com.example.bookit.R;
import com.example.bookit.adapter.BestOfferAdapter;
import com.example.bookit.auth.LoginPage;
import com.example.bookit.hotelapi.RoomFetchData;
import com.example.bookit.hotelapi.RoomModel;
import com.example.bookit.hotelapi.RoomViewFetchMessage;

import java.util.ArrayList;
import java.util.Objects;


public class HomePageActivity extends AppCompatActivity implements RoomViewFetchMessage {
    private RecyclerView ListDataView;
    private BestOfferAdapter bestOfferAdapter;

    ArrayList<RoomModel> roomModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide (); //This Line hides the action bar
        setContentView(R.layout.activity_home_page);

        ListDataView = findViewById(R.id.ListView);

        RoomFetchData roomFetchData = new RoomFetchData(this, this);

        RecyclerViewMethod();
        roomFetchData.onSuccessUpdate(this);

    }
    public void RecyclerViewMethod() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ListDataView.setLayoutManager(manager);
        ListDataView.setItemAnimator(new DefaultItemAnimator());
        ListDataView.setHasFixedSize(true);

        bestOfferAdapter = new BestOfferAdapter(this, roomModelArrayList);
        ListDataView.setAdapter(bestOfferAdapter);
        ListDataView.invalidate();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onUpdateSuccess(RoomModel message) {
        if(message != null){
            RoomModel roomModel = new RoomModel(message.getId(),message.getTitle(),message.getDescription(),message.getIsAvailable(),
                    message.getLocation(),message.getImageUrl(),message.getPrice());
            roomModelArrayList.add(roomModel);

        }
        bestOfferAdapter.notifyDataSetChanged();
    }


    public void onViewAllItems(View view) {
        Intent intent = new Intent(HomePageActivity.this, ViewAllRoom.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void onProfilePage(View view) {
        Intent intent = new Intent(HomePageActivity.this, UserProfile.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void onMenuClick(View view) {
        Intent intent = new Intent(HomePageActivity.this, UserMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }


    @Override
    public void onUpdateFailure(String message) {
        Toast.makeText(HomePageActivity.this, message, Toast.LENGTH_LONG).show();

    }
}