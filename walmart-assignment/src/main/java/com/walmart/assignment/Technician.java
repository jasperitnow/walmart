package com.walmart.assignment;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

@Resource
@Scope("prototype")
public class Technician {

	
	private String techName;
	private String primaryTechType;
	private int rating; // Can be used for assigning the task based on rating preferences if requested
							// by Issues
	private List<Integer> secondaryCapabilities = new ArrayList<>();
	private String emailId ;
	

	public Technician(String techName, String primaryTechType, int rating,
									List<Integer> secondaryCapabilities, String emailId) {
								super();
								this.techName = techName;
								this.primaryTechType = primaryTechType;
								this.rating = rating;
								this.secondaryCapabilities = secondaryCapabilities;
								this.emailId = emailId;
							}

	public String getTechName() {
		return techName;
	}

	public void setTechName(String techName) {
		this.techName = techName;
	}

	public String getPrimaryTechType() {
		return primaryTechType;
	}

	public void setPrimaryTechType(String primaryTechType) {
		this.primaryTechType = primaryTechType;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
	}

	public List<Integer> getSecondaryCapabilities() {
		return secondaryCapabilities;
	}

	public void setSecondaryCapabilities(List<Integer> secondaryCapabilities) {
		this.secondaryCapabilities = secondaryCapabilities;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	 

}
