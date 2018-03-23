package com.example.bree.mysmack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xmpp_library.XmppManager;
import com.example.xmpp_library.exception.SmackInvocationException;
import com.example.xmpp_library.utils.LogUtil;
import com.example.xmpp_library.utils.Hex;
import com.example.xmpp_library.xmpp.message.ReceiveMessageListener;

import org.jivesoftware.smack.packet.Message;

public class MainActivity extends AppCompatActivity {

    private XmppManager xmppMannger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xmppMannger = XmppManager.getInstance();
        xmppMannger.init(this);
        new Thread(new Runnable() {
           @Override
           public void run() {
               try {

                   xmppMannger.connect(Conf.OPENFIRE_HOST,Conf.OPENFIRE_PORT,null);
//                   xmppMannger.singup("bree03","123456");
                   xmppMannger.login("bree02","123456");
                   xmppMannger.setOnMessageReceiveListener(new ReceiveMessageListener() {
                       @Override
                       public void onReceive(Object o) {
                           Message message= (Message) o;
                           LogUtil.i("extension"+message.toString());
//                           String b = extension.getCmdStr();
//                           LogUtil.i("extension : "+b);
                       }
                   });
                   xmppMannger.sendHexByteArray("bree02@10.10.2.182","xijianjun@10.10.2.182/Spark", Data.bag);
               } catch (SmackInvocationException e) {
                   e.printStackTrace();
               } /*catch (SmackException.NoResponseException e) {
                   e.printStackTrace();
               } catch (SmackException.NotConnectedException e) {
                   e.printStackTrace();
               } catch (XMPPException.XMPPErrorException e) {
                   e.printStackTrace();
               }*/

           }
       }).start();
//
    }

}
