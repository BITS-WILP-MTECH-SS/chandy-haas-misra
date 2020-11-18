package com.bits.wilp.chandyhaasmisra;

import java.util.Scanner;

public class ChandyMisraHaasOrDemo {

	public static void main(String[] args) {
		 	Scanner sc = new Scanner(System.in);
	        int graph[][];
	        System.out.println("*******************************************************");
	        System.out.println("*************CHANDY-HAAS-MISRA - OR MODEL**************");
	        System.out.println("*******************************************************");
	        System.out.println("Enter the number of processes in the WFG");
	        int n=sc.nextInt();
	        graph=new int[n][n];
	        int input = 0;
	        for(int i=0;i<n;i++){
	        	input = 0;
	        	System.out.println("*******************************************************");
	            for(int j=0;j<n;j++){
	            		if(input != -1) {
	            			System.out.println("*******************************************************");
	            			System.out.println("Enter dependent processes for Process P"+i+". If there are no dependent processes, enter -1. Please do not enter process id "+i);
			                input = sc.nextInt();
			                while((input >= n || input == i) && input != -1) {
			                	System.out.println("Please enter a process id between 0 and "+(n-1)+". Please do not enter process id "+i);
			                	input = sc.nextInt();
			                }
			                graph[i][j]=input;
	            		}
	            		/*Allowing for no dependencies*/
	            		else {
	            			graph[i][j]=-1;
	            		}
	            }
	        }
	        /*Initialize WaitForGraph from the user input*/
	        WaitForGraph wfg = WaitForGraph.getInstance();
	        wfg.initializeWfg(n,graph);
	        
	        /*Initiate Deadlock Detection*/
	        System.out.println("*******************************************************");
	        System.out.println("Enter the Process Id that should initiate deadlock detection");
	        input = sc.nextInt();
	        while(input >= n) {
            	System.out.println("Please enter a process id between 0 and "+(n-1));
            	input = sc.nextInt();
            }
	        sc.close();
	        
	        Process initiator = wfg.getProcessList().get(input);
	        wfg.setInitiator(initiator);
	        initiator.initiateDeadlockDetection();
	        System.out.println("*******************************************************");
	        System.out.println("**************DEADLOCK DETECTION COMPLETE**************");
	        System.out.println("*******************************************************");
	        System.out.println("RESULT: Is deadlock detected?  "+wfg.isDeadlock());
	        
	        if(wfg.isDeadlock()) {
	        	StringBuffer stb = new StringBuffer();
	        	for(Process p:wfg.getDeadlockProcess()) {
	        		if(stb.length() > 0) {
		        		stb.append(",");
		        	}
	        		stb.append("P"+p.getPid());
	        	}
	        	System.out.println("Processes in deadlock:  "+stb.toString());
	        }
	}

}
