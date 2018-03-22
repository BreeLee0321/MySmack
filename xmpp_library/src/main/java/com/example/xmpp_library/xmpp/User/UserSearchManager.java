package com.example.xmpp_library.xmpp.User;

import android.widget.ArrayAdapter;

import com.example.xmpp_library.utils.LogUtil;
import com.example.xmpp_library.xmpp.connect.XMPPConnection;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smackx.search.ReportedData;
import org.jivesoftware.smackx.xdata.Form;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bree on 2018/3/22.
 */

public class UserSearchManager {
    private XMPPTCPConnection connection;
    public UserSearchManager(){
        connection= XMPPConnection.getInstance().getXmpptcpConnection();
    }

}
