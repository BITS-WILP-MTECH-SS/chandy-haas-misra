package com.bits.wilp.chandyhaasmisra;

public class Message {
	private int msgId;
	private Process initator;
	private Process from;
	private Process to;
	private MessageType msgType;
	private boolean isEngagagingMessage;
	
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public Process getInitator() {
		return initator;
	}
	public void setInitator(Process initator) {
		this.initator = initator;
	}
	public Process getFrom() {
		return from;
	}
	public void setFrom(Process from) {
		this.from = from;
	}
	public Process getTo() {
		return to;
	}
	public void setTo(Process to) {
		this.to = to;
	}
	public boolean isEngagagingMessage() {
		return isEngagagingMessage;
	}
	public void setEngagagingMessage(boolean isEngagagingMessage) {
		this.isEngagagingMessage = isEngagagingMessage;
	}
	public MessageType getMsgType() {
		return msgType;
	}
	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}
}
