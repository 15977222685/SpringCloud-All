package com.hlp.message;

/**
 * @author 黄林潘
 * QQ 1677873521
 * @date 2022/1/17 21:47
 */
public class Message {

    private String msgtype;
    private MessageInfo text;
    public String getMsgtype() {
        return msgtype;
    }
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
    public MessageInfo getText() {
        return text;
    }
    public void setText(MessageInfo text) {
        this.text = text;
    }
}
