package com.walmart.assignment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IssueProducer implements Runnable {
	private BlockingQueue<Issue> issuesQueueFromMultipleSources = new LinkedBlockingQueue<>();

	@Value("${issueIdStart}")  	private int issueIdStart;
	@Value("${issueIdEnd}") 	private int issueIdEnd;
	private int issueId;
	Map<Integer,Issue> issueMaster = new HashMap<>();
	

 

	public void setIssueMaster(Map<Integer, Issue> issueMaster) {
		this.issueMaster = issueMaster;
	}



	public void run() {
		try {
			generateIssues();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	
	
	private void generateIssues() throws InterruptedException {
		while(true) {
			issueId = ThreadLocalRandom.current().nextInt(issueIdStart, issueIdEnd);
			issuesQueueFromMultipleSources.put(
					issueMaster.get(issueId)
			);
			System.out.println(issuesQueueFromMultipleSources.size());
		}
	}
}