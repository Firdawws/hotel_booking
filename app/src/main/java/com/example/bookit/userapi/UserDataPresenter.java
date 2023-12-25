package com.example.bookit.userapi;

import android.app.Activity;

public interface UserDataPresenter {
    void onSuccessUpdate(Activity activity, String name, String email, String password);

}
