package com.example.bookit.bookingapi;

public interface BookingViewFetchMessage {
    void onUpdateSuccess(BookingModel message);
    void onUpdateFailure(String message);
}
