package com.reece.addressbook;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandLineParserTest {
	@Test
	public void testChangeAddrCommand() throws AddrBookException {
		CommandLineParser parser = new CommandLineParser();
		parser.parseCommandLine("create testadd1");
		parser.parseCommandLine("create testadd2");
		parser.parseCommandLine("change testadd2 testadd3");

		assertEquals(parser.getAddrBookMgr().getAllAddressBooks().size(), 2);
		assertEquals(parser.getAddrBookMgr().getAllAddressBooks().first().getName(), "testadd1");
		assertEquals(parser.getAddrBookMgr().getAllAddressBooks().last().getName(), "testadd3");
	}

	@Test
	public void testPrintUniqueSet() throws AddrBookException{
		CommandLineParser parser = new CommandLineParser();
		parser.parseCommandLine("create testname");
		parser.parseCommandLine("create testname2");
		parser.parseCommandLine("select testname");
		parser.parseCommandLine("create john,0402233322");
		parser.parseCommandLine("create john,0403333322   ");
		parser.parseCommandLine("back   ");
		parser.parseCommandLine("select testname2");
		parser.parseCommandLine("create john,045222333   ");
		parser.parseCommandLine("back   ");
		parser.parseCommandLine("print testname   testname2   ");
		parser.parseCommandLine("print testname");

		assertEquals(parser.getAddrBookMgr().getAllAddressBooks().last().getName(), "testname2");
		assertEquals(parser.getAddrBookMgr().getAddressBook("testname").get().getContactList().size(), 2);
		assertEquals(parser.getAddrBookMgr().getAddressBook("testname").get().getContactList().first(), 
				new Contact("john","0402233322"));
		assertEquals(parser.getAddrBookMgr().getAddressBook("testname2").get().getContactList().size(), 1);
		assertEquals(parser.getAddrBookMgr().getAddressBook("testname2").get().getContactList().first(), 
				new Contact("john","045222333"));
	}

	@Test
	public void testDelAddrCommand() throws AddrBookException {
		CommandLineParser parser = new CommandLineParser();
		parser.parseCommandLine("create testadd1");
		parser.parseCommandLine("create testadd2");
		parser.parseCommandLine("delete testadd2 ");

		assertEquals(parser.getAddrBookMgr().getAllAddressBooks().size(), 1);
		assertEquals(parser.getAddrBookMgr().getAllAddressBooks().first().getName(), "testadd1");

	}
	
	@Test
	public void testDelContactCommand() throws AddrBookException {
		CommandLineParser parser = new CommandLineParser();
		parser.parseCommandLine("create testaddr");
		parser.parseCommandLine("select testaddr");
		parser.parseCommandLine("create amy,0452233322");
		parser.parseCommandLine("create john,0483333322   ");
		parser.parseCommandLine("delete john,0483333322   ");
		parser.parseCommandLine("    back   ");
		assertEquals(parser.getAddrBookMgr().getAllAddressBooks().size(), 1);
		assertEquals(parser.getAddrBookMgr().getAddressBook("testaddr").get().getContactList().first(), 
				new Contact("amy","0452233322"));
	}
}
