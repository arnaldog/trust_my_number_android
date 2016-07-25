package com.fernandocejas.android10.sample.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.fernandocejas.android10.sample.presentation.R;
import com.github.tamir7.contacts.Contact;
import com.github.tamir7.contacts.Contacts;
import com.github.tamir7.contacts.Query;

import java.util.Arrays;
import java.util.List;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity {

  private static final String TAG = "MainActivity";

  private CallbackManager callbackManager;

  @Bind(R.id.btn_LoadData) Button btn_LoadData;

  @Bind(R.id.login_button)
  LoginButton loginButton;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    callbackManager = CallbackManager.Factory.create();

    loginButton.setReadPermissions(Arrays.asList("user_friends" +
            ""));

    loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
      @Override
      public void onSuccess(LoginResult loginResult) {
        Log.d(TAG, "onSuccess() called with: " + "loginResult = [" + loginResult + "]");
        Log.d(TAG, "onSuccess: access Token :" + loginResult.getAccessToken().getToken());
        Log.d(TAG, "onSuccess: userid: " + loginResult.getAccessToken().getUserId());
      }

      @Override
      public void onCancel() {

      }

      @Override
      public void onError(FacebookException error) {
        Log.d(TAG, "onError() called with: " + "error = [" + error + "]");
      }
    });

    Query q = Contacts.getQuery();
    q.hasPhoneNumber();
    List<Contact> contacts = q.find();

    for (Contact contact : contacts) {
      if (contact.getBestPhoneNumber() != null) {
        Log.d(TAG, "onCreate: contact:" + contact.getBestPhoneNumber().getNormalizedNumber());
      }
    }
  }

  /**
   * Goes to the user list screen.
   */
  @OnClick(R.id.btn_LoadData)
  void navigateToUserList() {
    this.navigator.navigateToUserList(this);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    callbackManager.onActivityResult(requestCode, resultCode, data);
  }
}
