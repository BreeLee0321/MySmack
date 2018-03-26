package comm_library.xmpp.message;

/**
 * Created by bree on 2018/3/22.
 * 对外发送借口类
 */

public class SendMessage {


    private Send send;
    public SendMessage(String from,String to){
        send = new SendSmackMessage(from,to);
    }

    /**
     * 发送字符串
     * @param content
     */

    public void send(String content){
       send.send(content);
    }

    /**
     * 发送十六进制的字节数组
     * @param bytes
     */
    public void sendHexByteArray(byte[] bytes){
        send.sendHexByteArray(bytes);
    }
}
