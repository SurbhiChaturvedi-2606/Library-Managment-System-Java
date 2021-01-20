
import java.util.Scanner;

public class Main
{
    BookDetails bo = new BookDetails();

    public static void main(String[] args)
    {
        Main lib = new Main();
        lib.welcome();                              // Welcome interface

    }

    // Welcome Screen
    public void welcome()
    {
        System.out.println("********************************************************");
        System.out.println("*********                                      *********");
        System.out.println("********* Welcome to library management system *********");
        System.out.println("**********                                       *********");
        System.out.println("********************************************************");
        loginDetails();                           // Login interface
    }

    // Librarian  login Account only
    public void loginDetails()
    {
        System.out.print(" Librarian's account:");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.print("Login password:");
        Scanner scanner1 = new Scanner(System.in);
        String password = scanner1.nextLine();

        int check = bo.login(username, password);                    // login verification if it is correct or not
        switch (check)
        {
            case 0:// password error
                System.out.println("Wrong password, please re-enter.");
                loginDetails();
                break;

            case 1:// verified
                System.out.println("Login successful!");
                Menu();// After successful login, jump to menu function management
                break;
            case -1:// account does not exist
                System.out.println("The administrator account you entered does not exist, please confirm and enter again.");
                loginDetails();
                break;
        }
    }

    // Main menu display List all function are in list
    public void Menu()
    {
        System.out.println("Please Select from the following Options :");
        System.out.println("\n 1:  All book information");
        System.out.println("\n 2 : Add new book");
        System.out.println("\n 3: Modify the information of the book");
        System.out.println("\n 4: Delete book ");
        System.out.println("\n 5: Return Book ");
        System.out.println("\n 6 :Exit the system");
        System.out.print("\n Please enter the Any One option :");

        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();//
        switch (a)
        {
            case 1:                                      // book information
                showBook();
                returnMenu();                            // Return to the main menu
                break;
            case 2:                                       // Enter new book
                addBook();
                break;
            case 3:                                      // changes in the basic information of the book
                modifyBookDetail();
                break;
            case 4:                                      // delete book
                deleteBook();
                break;
            case 5:                                     // return book
                returnBook();
                break;
            case 6:                                     // Exit
                System.exit(0);
                break;
        }
    }

    // Back to the main menu function
    public void returnMenu()
    {
        System.out.print("Press the ENTER key to again Back to the main menu:");
        Scanner scanner = new Scanner(System.in);
        String i = scanner.nextLine();
        Menu();
    }

    // All Book information display
    public void showBook()
    {
        bo.showTotalBook();
    }

    // Adding a new book function
    public void addBook()
    {

        System.out.print("\n enter the name of the book to be added:");
        Scanner scanner = new Scanner(System.in);
        String bookname = scanner.nextLine();

        System.out.print("\nPlease enter" + bookname + " author: ");                                      // Author
        Scanner scanner1 = new Scanner(System.in);
        String author = scanner1.nextLine();

        System.out.print("Please enter " + bookname + " the publication date in format of 2000-01-01):");    // publication date
        Scanner scanner2 = new Scanner(System.in);
        String pubdate = scanner2.nextLine();

        System.out.print("Please enter" + bookname + " total number of pages (pages):");                     //total number of pages
        Scanner scanner3 = new Scanner(System.in);
        String sumpagination = scanner3.nextLine();

        int check = bo.addBook(bookname, author, pubdate, sumpagination);

        // 1 means success, 0 means the new book already exists in the library, -1 means the warehouse is full
        switch (check)
        {
            case 1:
                System.out.println("new book" + bookname + "added successfully, the library currently has" + bo.remainSpace() + "locations for storing new books.");
                returnMenu();
                // The return function is for that,you can choose to continue adding or return to the main menu
                break;
            case 0:
                System.out.println("Failed to add book," + bookname + "Already exists in this library!");
                returnMenu();// if book is not added,you can choose to continue adding or return to the main menu
                break;
            case -1:
                System.out.println("Adding failed, the library is full, please delete some books before adding them.");
                Menu();
                break;
        }
    }

    // Changes in book information
    public void modifyBookDetail()
    {
        System.out.print("Please enter the title of the book you want to operate:");
        Scanner scanner = new Scanner(System.in);
        String bookname = scanner.nextLine();

        // Find related book information based on the title
        int check = bo.selectBookChanges(bookname);
        switch (check)
        {
            case 0:
                System.out.print("Related books are not found, enter 0 and press Enter to enter to continue, and enter 1 and press Enter to return to the main menu.");
                int a = scanner.nextInt();
                if (a == 0)
                {
                    modifyBookDetail();
                }
                else if (a == 1)
                {
                    Menu();
                }
                break;

            case -1:
                System.out.println("The title of the book is empty, enter 0 for continue, enter 1 and press Enter to return to the  main menu.");
                int a1 = scanner.nextInt();
                if (a1 == 0)
                {
                    modifyBookDetail();
                } else
                    {
                    Menu();
                    }
                break;

            case 1:
                //print all information
                int a2 = scanner.nextInt();      // 1 title 2 author 3 publication date 4 total pages
                if (a2 > 0 && a2 < 6)              // if case for the changes
                {
                    System.out.print("Please enter what you want to changes" + sortName(a2) + ":");
                    Scanner scanner2 = new Scanner(System.in);
                    String value = scanner2.nextLine();
                    int index = bo.selectIndex(bookname);
                    bo.modinfo(a2, value, index);
                    System.out.println(bookname + "The related information of this book has been modified successfully!");
                    returnMenu();
                }
                else
                    {
                    System.out.println("The number you entered is invalid. Please try again!");
                    modifyBookDetail();
                    }
                break;

        }
    }

    // Modified category name
    public String sortName(int aa)
    {
        if (aa == 1)
            return "Book Title";
        if (aa == 2)
            return "Author";
        if (aa == 3)
            return "Publication Date";
        if (aa == 4)
            return "Total Pages";
        else
            return "borrower";
    }

    // Delete book function
    public void deleteBook()
    {
        System.out.print("Please enter the name of the book to be deleted:");
        Scanner scanner = new Scanner(System.in);
        String bookname = scanner.nextLine();

        int check = bo.testReader(bookname);
        // This book exists or not
        if (check == 0) {
            bo.deleteBook(bookname);
            System.out.println(bookname + "Successfully removed from the library! Enter 0 to return to the main menu, enter 1 to continue to delete");
            deleteReturn();
        }
        /*else if (check == 1) {// Someone borrowed
            System.out.println("This book has been lent, please wait for it to be returned before deleting. Enter 0 to return to the main menu, enter 1 to continue to delete");
            deleteReturn();*/

        else
            {          // This book does not exist
            System.out.println("This book does not exist in this library, please compare it carefully. Enter 0 to return to the main menu, enter 1 to continue to delete");
            deleteReturn();
           }

    }

    // Deleted return operation
    public void deleteReturn()
    {

        Scanner scanner1 = new Scanner(System.in);
        int i = scanner1.nextInt();
        if (i == 0)
        {
            Menu();
        } else if (i == 1)
        {
            deleteBook();
        }
    }

    // Book return function
    public void returnBook()
    {
        System.out.print("Enter the name of the book to be returned:");
        Scanner scanner = new Scanner(System.in);
        String bookname = scanner.nextLine();

        int bb = bo.testReader(bookname);
        if (bb == 0)
        {
            System.out.println(bookname + "Not yet taken out.");
            System.out.print("Enter 0 and press Enter to return to the main menu, enter other keys to continue to return the book");
            String a = scanner.nextLine();

            if (a.equals("0"))
            {
                Menu();
            } else
                {
                returnBook();
                }
        }
        else if (bb == -1)
        {
            System.out.println(bookname + "does not exist in this library.");
            System.out.print("Enter 0 and press Enter to return to the main menu, enter other keys to continue to return the book");
            String a = scanner.nextLine();

            if (a.equals("0"))
            {
                Menu();
            }
            else
                {
                returnBook();
               }
           }
       }
}
       /* else if (bb == 1)
        {
            System.out.println(bo.getBorrowReader(bookname) + "The book has been returned successfully!");
            bo.setBorrowReader(bookname);
            System.out.print("Enter 0 and press Enter to return to the main menu, enter other keys to continue to return the book");
            String a = scanner.nextLine();
            if(a.equals("0"))
            {
                Menu();
            }
            else
                {
                returnBook();
            }
        }
    }

    // Book borrowing function
    public void borrowBook()
    {
        System.out.print("Please enter the name of the book to be borrowed:");
        Scanner scanner = new Scanner(System.in);
        String bookname = scanner.nextLine();
        int flag = bo.testReader(bookname);
        switch (flag)
        {
            case 0:      //There is a situation where no one borrows this book

                System.out.println("Please enter the name of the borrower:");
                String readername = scanner.nextLine();
                bo.borrow(bookname,readername);
                System.out.println(readername+"The book named "+bookname+" was borrowed from the library.");
                System.out.print("Enter 0 and press Enter to return to the main menu, and enter 1 and press Enter to continue borrowing books:");
                int a0 = scanner.nextInt();
                if(a0==0)
                    Menu();
                if(a0==1)
                    borrowBook();
                break;

            case 1:           //There is a situation where this book is borrowed
                System.out.println("Book borrowing failed," + bookname + "Already borrowed!");
                System.out.print("Enter 0 and press Enter to return to the main menu, and enter 1 and press Enter to continue borrowing books:");
                int a = scanner.nextInt();
                switch (a)
                {
                    case 0:
                        Menu();
                        break;

                    case 1:
                        borrowBook();
                        break;

                }
            case -1:// does not exist in this book
                System.out.println("Book borrowing failed," + bookname + "does not exist in the library.");
                System.out.print("Enter 0 and press Enter to return to the main menu, and enter 1 and press Enter to continue borrowing books:");
                int a1 = scanner.nextInt();
                switch (a1) {
                    case 0:
                        Menu();
                        break;

                    case 1:
                        borrowBook();
                        break;

                }
        }
    } */

