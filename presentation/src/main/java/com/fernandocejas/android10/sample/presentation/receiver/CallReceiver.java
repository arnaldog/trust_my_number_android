package com.fernandocejas.android10.sample.presentation.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fernandocejas.android10.sample.presentation.service.ChatHeadService;

import java.util.Date;

/**
 * Created by arnaldo on 24-07-16.
 */
public class CallReceiver extends PhoneCallReceiver {
    private static final String TAG = "CallReceiver";

    @Override
    protected void onIncomingCallReceived(Context ctx, String number, Date start) {
        Log.d(TAG, "onIncomingCallReceived() called with: " + "ctx = [" + ctx + "], number = [" + number + "], start = [" + start + "]");
        ctx.startService(new Intent(ctx, ChatHeadService.class));
    }

    @Override
    protected void onIncomingCallAnswered(Context ctx, String number, Date start) {
        Log.d(TAG, "onIncomingCallAnswered() called with: " + "ctx = [" + ctx + "], number = [" + number + "], start = [" + start + "]");
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        Log.d(TAG, "onIncomingCallEnded() called with: " + "ctx = [" + ctx + "], number = [" + number + "], start = [" + start + "], end = [" + end + "]");
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        Log.d(TAG, "onOutgoingCallStarted() called with: " + "ctx = [" + ctx + "], number = [" + number + "], start = [" + start + "]");
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        Log.d(TAG, "onOutgoingCallEnded() called with: " + "ctx = [" + ctx + "], number = [" + number + "], start = [" + start + "], end = [" + end + "]");
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        Log.d(TAG, "onMissedCall() called with: " + "ctx = [" + ctx + "], number = [" + number + "], start = [" + start + "]");
    }
}
