package com.example.bookit.userapi;

public interface UserViewMessage {
    void onUpdateFailure(String message);
    void onUpdateSuccess(String message);
}
