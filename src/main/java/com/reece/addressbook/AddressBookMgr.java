package com.reece.addressbook;

import java.util.Objects;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * manage all address and contact relation operation on repository
 * @author Sean Fu
 *
 */
public class AddressBookMgr {
	private Optional<AddressBook> selectedAddressBook = Optional.empty();
	private SortedSet<AddressBook> allAddressBooks = new TreeSet<>();

	private static final String NO_EQUAL_ERROR_MSG = 
			"old addressbook name and new addressbook name can't be equal";
	private static final String NO_EXIST_ERROR_MSG = 
			" no exist, pls select again";
	private static final String EXIST_ERROR_MSG = 
			" exist, pls select again";
	
	public void printAllAddrBooks() {
		allAddressBooks.stream().forEach(System.out::println);
	}

	public void createAddrBook(String addrBookName) throws AddrBookException {
		assertAddrBookNoExist(addrBookName);
		allAddressBooks.add(new AddressBook(addrBookName));
	}

	public void deleteAddrBook(String addrBookName) throws AddrBookException {
		assertAddrBookExist(addrBookName);
		allAddressBooks.removeIf(item -> item.getName().equals(addrBookName));
	}

	public void changeAddrBook(String oldAddrBookName, String newAddrBookName) throws AddrBookException {
		if (Objects.equals(oldAddrBookName, newAddrBookName)) {
			throw new AddrBookException(NO_EQUAL_ERROR_MSG);
		}
		assertAddrBookExist(oldAddrBookName);
		assertAddrBookNoExist(newAddrBookName);
		allAddressBooks.stream().filter(item -> item.getName().equals(oldAddrBookName)).findFirst()
				.ifPresent(item -> item.setName(newAddrBookName));
	}

	public void selectAddrBook(String addrBookName) throws AddrBookException {
		assertAddrBookExist(addrBookName);
		selectedAddressBook = allAddressBooks.stream().filter(item -> item.getName().equals(addrBookName)).findFirst();
	}

	private void assertAddrBookExist(String addrBookName) throws AddrBookException {
		if (!allAddressBooks.stream().anyMatch(item -> item.getName().equals(addrBookName))) {
			throw new AddrBookException(addrBookName + NO_EXIST_ERROR_MSG);
		}
	}

	private void assertAddrBookNoExist(String addrBookName) throws AddrBookException {
		if (allAddressBooks.stream().anyMatch(item -> item.getName().equals(addrBookName))) {
			throw new AddrBookException(addrBookName + EXIST_ERROR_MSG);
		}
	}

	public void printUniqueSetOfContactsAcrossMultiAddrBooks(String... addrBooks) throws AddrBookException {
		SortedSet<Contact> uniqueContacts = new TreeSet<>();
		for (String addBookName : addrBooks) {
			assertAddrBookExist(addBookName);
			uniqueContacts.addAll(allAddressBooks.stream().filter(item -> item.getName().equals(addBookName))
					.findFirst().get().getContactList());
		}
		uniqueContacts.stream().forEach(System.out::println);
	}

	public SortedSet<AddressBook> getAllAddressBooks() {
		return allAddressBooks;
	}
	
	public AddressBook getSelectedAddressBook() {
		return this.selectedAddressBook.orElse(new AddressBook(""));
	}
	
	public Optional<AddressBook> getAddressBook(String addrBookName) {
		return allAddressBooks.stream().filter(item -> item.getName().equals(addrBookName)).findFirst();
	}
	
	public void createContact(String name, String phNum) {
		selectedAddressBook.get().addContact(name, phNum);
	}
	
	public void delContact(String name, String phNum) {
		selectedAddressBook.get().delContact(name, phNum);
	}
	
	public void printallContactsOfSelectedAddrBook() {
		selectedAddressBook.get().getContactList().stream().forEach(System.out::println);
	}
	
	public void goBackToAddressbooks() {
		selectedAddressBook = Optional.empty();
	}
}
