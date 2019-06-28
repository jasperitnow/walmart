package com.walmart.assignment;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

@Resource
@Scope("prototype")
public class Issue {

	private int issueId;
	private String issueName;
	private String firstPrefferedVendorType;
	private String secondPrefferedVendorType;
	private int prorityOfTheIssue;// This is used for priority based algorithm
	private long maxWaitTime; // This is used for time based algorithm - first preference
	private long estimatedCompletionTime;
	private int ratingMinReq;

	@Override
	public String toString() {
		return "Issue [ID=" + issueId + ", Name=" + issueName + ", Tech1="
				+ firstPrefferedVendorType + ", Tech2=" + secondPrefferedVendorType+"]";
	}

	public Issue(int issueId, String issueName, String firstPrefferedVendorType, String secondPrefferedVendorType,
			int prorityOfTheIssue, long maxWaitTime, long estimatedCompletionTime, int ratingMinReq) {
		super();
		this.issueId = issueId;
		
		this.issueName = issueName;
		this.firstPrefferedVendorType = firstPrefferedVendorType;
		this.secondPrefferedVendorType = secondPrefferedVendorType;
		this.prorityOfTheIssue = prorityOfTheIssue;
		this.maxWaitTime = maxWaitTime;
		this.estimatedCompletionTime = estimatedCompletionTime;
		this.ratingMinReq = ratingMinReq;
	}

	public int getIssueId() {
		return issueId;
	}

	public String getIssueName() {
		return issueName;
	}

	public String getFirstPrefferedVendorType() {
		return firstPrefferedVendorType;
	}

	public String getSecondPrefferedVendorType() {
		return secondPrefferedVendorType;
	}

	public int getProrityOfTheIssue() {
		return prorityOfTheIssue;
	}

	public long getMaxWaitTime() {
		return maxWaitTime;
	}

	public long getEstimatedCompletionTime() {
		return estimatedCompletionTime;
	}

	public int getRatingMinReq() {
		return ratingMinReq;
	}

	 

}
