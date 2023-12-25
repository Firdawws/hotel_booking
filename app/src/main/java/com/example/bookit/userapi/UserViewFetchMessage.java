package com.example.bookit.userapi;

public interface UserViewFetchMessage {
    void onUpdateSuccess(UserModel message);
    void onUpdateFailure(String message);
}
