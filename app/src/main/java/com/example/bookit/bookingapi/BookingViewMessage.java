package com.example.bookit.bookingapi;

public interface BookingViewMessage {
    void onUpdateFailure(String message);
    void onUpdateSuccess(String message);
}
