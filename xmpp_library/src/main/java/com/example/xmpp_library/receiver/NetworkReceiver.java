package com.example.xmpp_library.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.xmpp_library.constant.Action;
import com.example.xmpp_library.utils.NetworkUtils;

/**
 * Created by bree on 2018/3/21.
 */

public class NetworkReceiver extends BroadcastReceiver {
    public static final String EXTRA_DATA_NAME_NETWORK_CONNECTED = "com.mstr.letschat.NetworkConnected";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
//            Intent serviceIntent = new Intent(Action.ACTION_NETWORK_STATUS, null, context, XmppService.class);
//            serviceIntent.putExtra(EXTRA_DATA_NAME_NETWORK_CONNECTED, NetworkUtils.isNetworkConnected(context));
//            context.startService(serviceIntent);
        }
    }
}