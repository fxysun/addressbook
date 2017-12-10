1. development environment:
jdk 1.8.0_144
maven 3.2.1

2. unzip the src zip file, and run 'mvn install' to build it.
run 'mvn exec:java ' to run it. It is a CLI application.

3. After start the program, you are in address book CLI.
You can create, delete, and select address book, You can also change the address book name
and display all unique contacts on selected address book.

4. After selection of one address book, you can create, print all, delete contacts.

5. For all operations at address book level, there are possible existing or no existing check over address book.
Appropriate error message appear when check fail.
For simplicity, I don't include related checks on contact operations, I think it depends on business requirement.

6. I have some unit tests over some main functionalities. JUnit is only one framework imported in this code.

7. For command line, <> are just indication of variable, you don't need to input these two characters.
Any variable should be alphanumeric and underscore combination.
Variable should be avoided to use following keywords:
                              create
                              select
                              delete
                              change
                              print
                              quit
                              printall
                              back
