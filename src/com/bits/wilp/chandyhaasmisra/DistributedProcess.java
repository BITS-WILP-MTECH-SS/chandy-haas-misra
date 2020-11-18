package com.bits.wilp.chandyhaasmisra;

public interface DistributedProcess {
	void initiateDeadlockDetection();
	void processQueryMessage(Message m);
	void replyToMessage(Message m);
	void sendMessage(Message m);
}
