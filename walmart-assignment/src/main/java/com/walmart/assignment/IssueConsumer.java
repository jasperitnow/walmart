package com.walmart.assignment;

import java.util.concurrent.BlockingQueue;

public class IssueConsumer implements Runnable {
    private BlockingQueue<Issue> queue;
      
    public IssueConsumer(BlockingQueue<Issue> queue) {
        this.queue = queue;
    }
    public void run() {
        try {
            while (true) {
            	
                Issue issue = queue.take();
                
                                
                
                System.out.println(Thread.currentThread().getName() + " result: " + issue.getIssueName());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}