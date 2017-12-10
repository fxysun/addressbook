package com.reece.addressbook;

import java.io.Console;

/**
 * create, delete, and select address book, 
 * change the address book name
 * and display all unique contacts on selected address book.
 * create, print all, delete contacts.
 * @author Sean Fu
 *
 */
public class Main {
	public static void main(String[] args) {
		Console console = System.console();
		if (console == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		CommandLineParser parser = new CommandLineParser();
		parser.setCurrentStatus(AppStatus.ADDRESSBOOK);
		while (true) {
			// list all address books and ask for one selection
			try {
				System.out.println("");
				switch (parser.getCurrentStatus()) {
				case ADDRESSBOOK:
					System.out.println("current existing address book:");
					parser.printAllAddrBooks();
					System.out.println("");
					System.out.println("create one addressbook by 'create <addressbook name>'");
					System.out.println("select one addressbook by 'select <addressbook name>'");
					System.out.println("delete one addressbook by 'delete <addressbook name>'");
					System.out.println(
							"change the name of addressbook by 'change <old addressbook name> <new addressbook name>'");
					System.out.println(
							"print a unique set of all contacts across multiple address books by 'print <addressbook name1> <addressbook name2>...'");
					System.out.println("or 'quit' to quit");
					break;
				case CONTACT:
					System.out.println("on selected address book "+parser.getSelectedAddr());
					System.out.println("");
					System.out.println("create one contact by 'create <name,phonenum>'");
					System.out.println("delete one contact by 'delete <name,phonenum>'");
					System.out.println("print all contacts by 'printall'");
					System.out.println("go back to addressbook menu by 'back'");
					break;
				default:
					System.exit(1);break;
				}
				String issuedCommand = console.readLine(": ");
				parser.parseCommandLine(issuedCommand);
			} catch (AddrBookException ex) {
				System.err.println(ex.getLocalizedMessage());
			}
		}

	}

}
