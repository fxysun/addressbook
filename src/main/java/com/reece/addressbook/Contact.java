package com.reece.addressbook;

public class Contact implements Comparable<Contact> {
	private String phNumber;
	private String name;
	
	public Contact(String name, String phNumber) {
		this.name = name;
		this.phNumber = phNumber;
	}
	public String getPhNumber() {
		return phNumber;
	}
	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public int compareTo(Contact o) {
		int firstComparation = this.name.compareTo(o.getName());
		if(firstComparation != 0) {
			return firstComparation;
		}
		else {
			return this.phNumber.compareTo(o.getPhNumber());
		}
	}
	@Override
	public String toString() {
		return "Contact [name=" + name + ", phNumber=" + phNumber + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phNumber == null) ? 0 : phNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phNumber == null) {
			if (other.phNumber != null)
				return false;
		} else if (!phNumber.equals(other.phNumber))
			return false;
		return true;
	}
}
