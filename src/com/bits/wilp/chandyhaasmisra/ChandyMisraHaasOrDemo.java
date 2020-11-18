package com.bits.wilp.chandyhaasmisra;

import java.util.Scanner;

public class ChandyMisraHaasOrDemo {

	public static void main(String[] args) {
		 	Scanner sc = new Scanner(System.in);
	        int graph[][];
	        System.out.println("Enter the number of processes");
	        int n=sc.nextInt();
	        graph=new int[n][n];
	        int input = 0;
	        for(int i=0;i<n;i++){
	        	input = 0;
	            System.out.println("Enter dependent processes for Process P"+i+". If there are no dependent processes, enter -1.");
	            for(int j=0;j<n;j++){
	            		if(input != -1) {
		            		System.out.println("Enter next process id:");
			                input = sc.nextInt();
			                while(input >= n && input != -1) {
			                	System.out.println("Please enter a process id between 0 and "+(n-1));
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
	        
	        System.out.println("Is deadlock detected - "+wfg.isDeadlock());
	}

}
