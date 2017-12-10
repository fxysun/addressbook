package com.reece.addressbook;

import java.util.SortedSet;
import java.util.TreeSet;

public class AddressBook implements Comparable<AddressBook> {
	private SortedSet<Contact> contacts = new TreeSet<>();

	private String name;
	public AddressBook(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name =  name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	public SortedSet<Contact> getContactList() {
		return contacts;
	}
	public void setContactList(SortedSet<Contact> contactList) {
		this.contacts = contactList;
	}
	
	public void addContact(String name, String phNum) {
		Contact contact = new Contact(name, phNum);
		contacts.add(contact);
	}
	
	public void delContact(String name, String phNum) {
		contacts.remove(new Contact(name, phNum));
	}
	@Override
	public int compareTo(AddressBook o) {
		return name.compareTo(o.getName());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contacts == null) ? 0 : contacts.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		AddressBook other = (AddressBook) obj;
		if (contacts == null) {
			if (other.contacts != null)
				return false;
		} else if (!contacts.equals(other.contacts))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
