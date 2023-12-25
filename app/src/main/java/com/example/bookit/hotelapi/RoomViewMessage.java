package com.example.bookit.hotelapi;

public interface RoomViewMessage {

    void onUpdateFailure(String message);
    void onUpdateSuccess(String message);
}
