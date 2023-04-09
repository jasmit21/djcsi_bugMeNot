package com.example.yourlinkapp.models;

public class Contact implements android.os.Parcelable {
	public static final Creator<Contact> CREATOR = new Creator<Contact>() {
		@Override
		public Contact createFromParcel(android.os.Parcel in) {
			return new Contact(in);
		}
		
		@Override
		public Contact[] newArray(int size) {
			return new Contact[size];
		}
	};
	private String contactName;
	private String contactNumber;
	
	public Contact() {
	
	}
	
	public Contact(String contactName, String contactNumber) {
		this.contactName = contactName;
		this.contactNumber = contactNumber;
	}
	
	protected Contact(android.os.Parcel in) {
		contactName = in.readString();
		contactNumber = in.readString();
	}
	
	public String getContactName() {
		return contactName;
	}
	
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(android.os.Parcel parcel, int i) {
		parcel.writeString(contactName);
		parcel.writeString(contactNumber);
	}
}
