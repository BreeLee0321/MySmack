package com.example.xmpp_library.xmpp.chat;

import org.jivesoftware.smack.packet.ExtensionElement;

/**
 * Created by bree on 2018/3/22.
 */

public class MyMessage implements ExtensionElement {

    private static final String NAME_SPACE = "extension";
    private static final String ELEMENT_NAME = "cmd";


    //用户地址元素名称
    private String cmdElement = "url";
    //用户地址元素文本(对外开放)
    private byte[] b={(byte)0xB8,(byte)0xDF,(byte)0xCB,(byte)0xD9};;

    @Override
    public String getNamespace() {
        return NAME_SPACE;
    }

    @Override
    public String getElementName() {
        return ELEMENT_NAME;
    }

    @Override
    public CharSequence toXML() {
        StringBuilder sb = new StringBuilder();

        sb.append("<").append(ELEMENT_NAME).append(" xmlns=\"").append(NAME_SPACE).append("\">");
        sb.append("<" + cmdElement + ">").append(b).append("</"+cmdElement+">");
        sb.append("</"+ELEMENT_NAME+">");
        return sb.toString();
    }
}
