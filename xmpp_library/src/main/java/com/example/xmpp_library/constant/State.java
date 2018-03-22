package com.example.xmpp_library.constant;

/**
 * Created by bree on 2018/3/21.
 */

public enum State {
    CONNECTING,

    CONNECTED,

    DISCONNECTED,

    // this is a state that client is trying to reconnect to server
    WAITING_TO_CONNECT,

    WAITING_FOR_NETWORK;
}
