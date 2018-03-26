package comm_library.xmpp.message;

/**
 * Created by bree on 2018/3/22.
 */

public interface ReceiveMessageListener<M> {
    void onReceive(M m);
}
