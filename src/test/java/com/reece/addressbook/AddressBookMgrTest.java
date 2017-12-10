package com.reece.addressbook;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddressBookMgrTest {
    
	@Test
    public void testPrintUniqueSetOfContactsAcrossMultiAddrBooks() throws AddrBookException {
		AddressBookMgr addressBookMgr = new AddressBookMgr();
		addressBookMgr.createAddrBook("testname");
		addressBookMgr.createAddrBook("testname2");
		addressBookMgr.getAddressBook("testname").get().addContact("john", "0402233322");
		addressBookMgr.getAddressBook("testname2").get().addContact("john", "0403333322");
		addressBookMgr.getAddressBook("testname2").get().addContact("john", "0402233322");
		assertEquals(addressBookMgr.getAddressBook("testname2").get().getContactList().size(), 2);
		assertEquals(addressBookMgr.getAddressBook("testname2").get().getContactList().first(), new Contact("john", "0402233322"));
		assertEquals(addressBookMgr.getAddressBook("testname").get().getContactList().size(), 1);
		addressBookMgr.printUniqueSetOfContactsAcrossMultiAddrBooks("testname", "testname2");
	}
	
	//@Test(expected = EngineException.class)
	@Test
	public void testCreateAddrBook() throws AddrBookException {
		AddressBookMgr addressBookMgr = new AddressBookMgr();
		addressBookMgr.createAddrBook("testname");
		addressBookMgr.createAddrBook("testname2");
		assertEquals(addressBookMgr.getAllAddressBooks().size(), 2);
		assertEquals(addressBookMgr.getAllAddressBooks().first().getName(), "testname");
		assertEquals(addressBookMgr.getAllAddressBooks().last().getName(), "testname2");
	}
	
	@Test
	public void testDeleteAddrBook() throws AddrBookException {
		AddressBookMgr addressBookMgr = new AddressBookMgr();
		addressBookMgr.createAddrBook("testname");
		addressBookMgr.createAddrBook("testname2");
		assertEquals(addressBookMgr.getAllAddressBooks().size(), 2);
		addressBookMgr.deleteAddrBook("testname");
		assertEquals(addressBookMgr.getAllAddressBooks().size(), 1);
		assertEquals(addressBookMgr.getAllAddressBooks().first().getName(), "testname2");
	}
	
	@Test
	public void testChangeAddrBook() throws AddrBookException {
		AddressBookMgr addressBookMgr = new AddressBookMgr();
		addressBookMgr.createAddrBook("testname");
		addressBookMgr.createAddrBook("testname2");
		addressBookMgr.changeAddrBook("testname2", "testname3");
		assertEquals(addressBookMgr.getAllAddressBooks().size(), 2);
		assertEquals(addressBookMgr.getAllAddressBooks().last().getName(), "testname3");
	}
	

}
