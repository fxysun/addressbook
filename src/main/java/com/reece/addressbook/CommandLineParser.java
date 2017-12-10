package com.reece.addressbook;

import java.util.Collections;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * parse all command line by utilizing regular expression, then call related
 * functions of AddressBookMgr
 * 
 * @author Sean Fu
 *
 */
public class CommandLineParser {
	private AddressBookMgr addrBookMgr = new AddressBookMgr();
	private static Pattern addrCreateP = Pattern.compile("^\\s*create\\s+(\\w+)\\s*$");
	private static Pattern addrSelectP = Pattern.compile("^\\s*select\\s+(\\w+)\\s*$");
	private static Pattern addrDeleteP = Pattern.compile("^\\s*delete\\s+(\\w+)\\s*$");
	private static Pattern addrChangeP = Pattern.compile("^\\s*change\\s+(\\w+)\\s+(\\w+)\\s*$");
	private static Pattern addrPrintP = Pattern.compile("^\\s*print\\s+(\\w+\\s+)*(\\w+)\\s*$");
	private static Pattern addrQuitP = Pattern.compile("^\\s*quit\\s*$");

	private static Pattern contactAddP = Pattern.compile("^\\s*create\\s+(\\w+),(\\d+)\\s*$");
	private static Pattern contactDelP = Pattern.compile("^\\s*delete\\s+(\\w+),(\\d+)\\s*$");
	private static Pattern contactPrintAllP = Pattern.compile("^\\s*printall\\s*$");
	private static Pattern contactBackP = Pattern.compile("^\\s*back\\s*$");
	private AppStatus currentStatus = AppStatus.ADDRESSBOOK;

	public AppStatus getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(AppStatus currentStatus) {
		this.currentStatus = currentStatus;
	}

	public void parseCommandLine(String issuedCommand) throws AddrBookException {
		if (currentStatus == AppStatus.ADDRESSBOOK) {
			processCommandForAddressbook(issuedCommand);
		} else if (currentStatus == AppStatus.CONTACT) {
			processCommandForContact(issuedCommand);
		}
	}

	private void processCommandForAddressbook(String issuedCommand) throws AddrBookException {
		// for performance, avoid round-robin try on pattern
		Matcher matcher = addrCreateP.matcher(issuedCommand);
		if (matcher.matches()) {
			String addrBookName = matcher.group(1);
			addrBookMgr.createAddrBook(addrBookName);
			return;
		}
		matcher = addrSelectP.matcher(issuedCommand);
		if (matcher.matches()) {
			String addrBookName = matcher.group(1);
			addrBookMgr.selectAddrBook(addrBookName);
			currentStatus = AppStatus.CONTACT;
			return;
		}
		matcher = addrDeleteP.matcher(issuedCommand);
		if (matcher.matches()) {
			String addrBookName = matcher.group(1);
			addrBookMgr.deleteAddrBook(addrBookName);
			return;
		}
		matcher = addrChangeP.matcher(issuedCommand);
		if (matcher.matches()) {
			String oldAddrBookName = matcher.group(1);
			String newAddrBookName = matcher.group(2);
			addrBookMgr.changeAddrBook(oldAddrBookName, newAddrBookName);
			return;
		}
		matcher = addrPrintP.matcher(issuedCommand);
		if (matcher.matches()) {
			String addrBooks = issuedCommand.substring(issuedCommand.indexOf("print") + 5, issuedCommand.length());
			String[] books = extractAllAddressBooks(addrBooks);
			addrBookMgr.printUniqueSetOfContactsAcrossMultiAddrBooks(books);
			return;
		}
		matcher = addrQuitP.matcher(issuedCommand);
		if (matcher.matches()) {
			System.exit(0);
		}
	}

	private String[] extractAllAddressBooks(String addrBooks) {
		return Collections.list(new StringTokenizer(addrBooks, " ")).stream().map(token -> (String) token)
				.collect(Collectors.toList()).stream().toArray(String[]::new);
	}

	private void processCommandForContact(String issuedCommand) {
		Matcher matcher = contactAddP.matcher(issuedCommand);
		if (matcher.matches()) {
			addrBookMgr.createContact(matcher.group(1), matcher.group(2));
			return;
		}
		matcher = contactDelP.matcher(issuedCommand);
		if (matcher.matches()) {
			addrBookMgr.delContact(matcher.group(1), matcher.group(2));
			return;
		}
		matcher = contactPrintAllP.matcher(issuedCommand);
		if (matcher.matches()) {
			addrBookMgr.printallContactsOfSelectedAddrBook();
			return;
		}
		matcher = contactBackP.matcher(issuedCommand);
		if (matcher.matches()) {
			addrBookMgr.goBackToAddressbooks();
			currentStatus = AppStatus.ADDRESSBOOK;
		}
	}

	public void printAllAddrBooks() {
		addrBookMgr.printAllAddrBooks();
	}

	public AddressBookMgr getAddrBookMgr() {
		return this.addrBookMgr;
	}

	public AddressBook getSelectedAddr() {
		return addrBookMgr.getSelectedAddressBook();
	}
}
