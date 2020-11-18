package com.bits.wilp.chandyhaasmisra;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Process implements DistributedProcess{
	private int pid;
	private long noOfQueryMessages;
	boolean wait;
	
	private List<Process> dependentProcess;
	private Queue<Message> queryMessages;
	
	public Process(int pid) {
		this.pid = pid;
		this.noOfQueryMessages = 0;
		this.wait = false;
		this.queryMessages = new ArrayDeque<Message>();
	}
	
	private Message createMessage(Process to,MessageType msgType) {
		Message message = new Message();
		message.setMsgType(msgType);
		message.setInitator(WaitForGraph.getInstance().getInitiator());
		message.setFrom(this);
		message.setTo(to);
		return message;
	}
	
	private Message createReplyMessage(Process initiator, Process from, Process to) {
		Message reply = new Message();
		reply.setMsgType(MessageType.REPLY_MESSAGE);
		reply.setInitator(initiator);
		reply.setFrom(from);
		reply.setTo(to);
		return reply;
	}
	
	@Override
	public void initiateDeadlockDetection() {
		System.out.println("Initiating Deadlock Detection from Process P"+this.getPid());
		Message message = null;
		if(dependentProcess != null && !dependentProcess.isEmpty()) {
			this.setWait(true);
			this.setNoOfQueryMessages(dependentProcess.size());
			for(Process p:dependentProcess) {
				if(p != null) {
					message = createMessage(p,MessageType.QUERY_MESSAGE);
					sendMessage(message);
				}
			}
		}
	}
	
	@Override
	public void processQueryMessage(Message m) {
		System.out.println("Processing Query Message From P"+m.getFrom().getPid()+" in process P"+m.getTo().getPid());
		if(this.queryMessages.size() == 0 || !this.isWait()) {
			m.setEngagagingMessage(true);
			queryMessages.add(m);
			initiateDeadlockDetection();
		}else{
			System.out.println("Sending Reply from "+m.getFrom().getPid()+" to "+m.getTo().getPid());
			Message reply = createReplyMessage(m.getInitator(),m.getFrom(),m.getTo());
			replyToMessage(reply);
		}
	}
	
	@Override
	public void replyToMessage(Message m) {
		Process p = m.getTo();
		if(p.isWait()) {
			long noOfQueryMsg = p.getNoOfQueryMessages()-1;
			p.setNoOfQueryMessages(noOfQueryMsg);
			if(noOfQueryMsg == 0) {
				if(p.getPid() == m.getInitator().getPid()) {
					WaitForGraph.getInstance().setDeadlock(true);
					WaitForGraph.getInstance().getDeadlockProcess().add(m.getInitator());
					WaitForGraph.getInstance().getDeadlockProcess().add(m.getFrom());
				}else {
					Message reply = createReplyMessage(m.getInitator(),m.getTo(),m.getFrom());
					replyToMessage(reply);
				}
			}
		}
	}
	@Override
	public void sendMessage(Message message) {
		System.out.println("Sending Query Message from Process P"+message.getFrom().getPid()+" to Process P"+message.getTo().getPid());
		message.getTo().processQueryMessage(message);
	}
	
	@Override
	public String toString() {
		StringBuffer stb = new StringBuffer();
		StringBuffer pList = new StringBuffer();
		stb.append("Process Id: "+pid);
		stb.append("\n");
		stb.append("Dependent Processes are: ");
		if(dependentProcess != null && !dependentProcess.isEmpty()) {
			for(Process p : dependentProcess) {
				if(pList.length() > 0) {
					pList.append(",");
				}
				pList.append(p.getPid());
			}
		}
		stb.append(pList);
		stb.append("\n");
		return stb.toString();
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public long getNoOfQueryMessages() {
		return noOfQueryMessages;
	}
	public void setNoOfQueryMessages(long noOfQueryMessages) {
		this.noOfQueryMessages = noOfQueryMessages;
	}
	public boolean isWait() {
		return wait;
	}
	public void setWait(boolean wait) {
		this.wait = wait;
	}
	public List<Process> getDependentProcess() {
		return dependentProcess;
	}
	public void setDependentProcess(List<Process> dependantProcess) {
		this.dependentProcess = dependantProcess;
	}
}
