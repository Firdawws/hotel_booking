package com.example.bookit.hotelapi;

public interface RoomViewFetchMessage {
    void onUpdateSuccess(RoomModel message);
    void onUpdateFailure(String message);

}
