package com.bits.wilp.chandyhaasmisra;

import java.util.ArrayList;
import java.util.List;

public class WaitForGraph {
	private static WaitForGraph wfg = null;
	private List<Process> processList;
	private int numberofProcess = 0;
	private boolean isDeadlock;
	private List<Process> deadlockProcess = null;
	private Process initiator;
	
	private WaitForGraph() {
		this.processList = new ArrayList<Process>();
		this.deadlockProcess = new ArrayList<Process>();
	}
	
	public static WaitForGraph getInstance() {
		if(wfg == null) {
			wfg = new WaitForGraph();
		}
		return wfg;
	}
	
	public void initializeWfg(int numberOfProcess,int graph[][]) {
		WaitForGraph.getInstance().setNumberofProcess(numberOfProcess);
		for(int i=0;i<numberOfProcess;i++) {
			Process p = new Process(i);
			processList.add(p);
		}
		WaitForGraph.getInstance().setDependentProcess(graph);
		System.out.println(showProcessList());
	}
	
	public String showProcessList() {
		System.out.println("Show Process List");
		StringBuffer stb = new StringBuffer();
		for(Process p:processList) {
			stb.append(p.toString());
		}
		return stb.toString();
	}
	
	private void setDependentProcess(int graph[][]) {
		int[] processes = null;
		for(Process p:processList) {
			processes = graph[p.getPid()];
			List<Process> dependentProcess = new ArrayList<Process>();
			for(int process:processes) {
				if(process >=0) {
					dependentProcess.add(processList.get(process));
				}
			}
			p.setDependentProcess(dependentProcess);
		}
	}
	
	public int getNumberofProcess() {
		return numberofProcess;
	}

	public void setNumberofProcess(int numberofProcess) {
		this.numberofProcess = numberofProcess;
	}

	public List<Process> getProcessList() {
		return processList;
	}

	public void setProcessList(List<Process> processList) {
		this.processList = processList;
	}

	public boolean isDeadlock() {
		return isDeadlock;
	}

	public void setDeadlock(boolean isDeadlock) {
		this.isDeadlock = isDeadlock;
	}

	public Process getInitiator() {
		return initiator;
	}

	public void setInitiator(Process initiator) {
		this.initiator = initiator;
	}

	public List<Process> getDeadlockProcess() {
		return deadlockProcess;
	}

	public void setDeadlockProcess(List<Process> deadlockProcess) {
		this.deadlockProcess = deadlockProcess;
	}
}
