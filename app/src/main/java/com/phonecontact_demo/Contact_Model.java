package com.phonecontact_demo;

public class Contact_Model {

	// Getter and setter for contacts
	private String contactId, contactName, contactNumber, contactEmail,
			contactPhoto, contactOtherDetails;

	public Contact_Model(String contactId, String contactName,
			String contactNumber, String contactEmail, String contactPhoto,
			String contactOtherDetails) {
		this.contactId = contactId;
		this.contactName = contactName;
		this.contactEmail = contactEmail;
		this.contactNumber = contactNumber;
		this.contactPhoto = contactPhoto;
		this.contactOtherDetails = contactOtherDetails;
	}

	public String getContactID() {
		return contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getContactPhoto() {
		return contactPhoto;
	}

	public String getContactOtherDetails() {
		return contactOtherDetails;
	}
}
