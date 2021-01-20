import java.util.Scanner;

public class BookDetails
{
    public static BookInformation book = new BookInformation();
    DataUser user = new DataUser();
    // Administrator login can verify only
    public int login(String username, String password)
    {
        if (username.equals(user.getUser()))
        {
            if (password.equals(user.getPassword()))
            {
                return 1;                        // Account and password correct then passed
            }
            else
                {
                return 0;                         // The password is wrong
                }
        }
            else
            {
            return -1;                           // The account does not exist
            }
    }

     // Menu function display list

     // Book information display function

    public void showTotalBook()
    {
        System.out.println("This library has total" + (book.booknames.length-remainSpace()) + "book");
        int a = bookinfo();
        if (a == 0)
        {  // Show all book information
            showAllBook();
        }
        else if (a > 0 && a <= book.booknames.length)
        {
            // Display related book information
             showRelatedBook(a);
        }
        else
            {
            System.out.println("The number entered is illegal!");
            showTotalBook();
            }
    }
    // Show all book information
    public void showAllBook()
    {
        for (int i = 0; i < book.booknames.length; i++)
        {
            if (book.booknames[i] != null)
            {
                System.out.println("*****************BOOK-1" + ( i + 1)
                        + "************************");
                System.out.println("\nThe book number is" + (i + 1) + "The detailed information is:");
                System.out.print("\nBook name:" + book.booknames[i] + "");
                System.out.print ( "\nAuthor:" + book.authors [i] + "");
                System.out.print("\nPublication Date:" + book.pubdates[i] + "");
                System.out.print("\nTotal pages:" + book.sumpaginations[i] + "");
                //System.out.println("borrower:" + book.borrowreaders[i]);
            }
        }
        System.out.println("***************************");

    }
    // Display related book information
    public void showRelatedBook(int a)
    {
        int i = a;
        System.out.println("*******************Boundary Line***********************************");
        System.out.println("\nBook Number" + (i) + "The detailed information is:");
        System.out.print("Book name:" + book.booknames[i-1] + "");
        System.out.println ( "OF:" + book.authors [i - 1] + "");
        System.out.print("Publication Date:" + book.pubdates[i-1] + "");
        System.out.print("Total pages:" + book.sumpaginations[i-1] + "");
       // System.out.println("borrower:" + book.borrowreaders[i-1]);
        System.out.println("**********************   Boundary Line    **************8");
    }
    //According to the number, whether to display or not
    public int bookinfo()
    {
        for (int i = 0; i < book.booknames.length; i++)
        {
            if (book.booknames[i] != null)
            {
                System.out.println("Book name number" + (i + 1) + ":" + book.booknames[i] + "");
            }
        }
        System.out.print("Enter to view the current book or (input 0 to view all information):");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        return i;
    }
    // Enter new book (if new book is added)
        public int addBook(String bookname, String author, String pubdate, String sumpagination)
        {
        int cc = 0;        // 1 means success, 0 means the new book already exists in the library, -1 means the store is full

        if (book.booknames[book.booknames.length - 1] != null)
        {
            cc = -1;                            // book store is full
        }
        if (book.booknames[book.booknames.length - 1] == null)
        {

            for (int i = 0; i < book.booknames.length; i++)
            {

                if (bookname.equals(book.booknames[i]))
                {
                    cc = 0;                                                        //  book already exists
                    break;
                }
                if (!(bookname.equals(book.booknames[i])))                        //if book is not same a given book name
                {
                    // add books
                    add(bookname, author, pubdate, sumpagination);
                    cc = 1;                                                      // success
                    break;
                }
            }
        }
        return cc;
    }
    // Enter new book
    public void add(String bookname, String author, String pubdate, String sumpagination)
    {
        for (int i = 0; i < book.booknames.length; i++)

        {
            if (book.booknames[i] == null)
            {
                book.booknames[i] = bookname;
                book.authors[i] = author;
                book.pubdates[i] = pubdate;
                book.sumpaginations[i] = sumpagination;
                break;
            }
        }
    }
    // check remaining capacity of the book

    public int remainSpace()
    {
        int count = 0;                                    //using 'e' exception handling
        try {
            for (int i = 0; i < book.booknames.length; i++)
            {
                if (book.booknames[i] == null)
                {
                    ++count;
                }
            }
        } catch (Exception e)
        {

        }
        return count;
    }
    // Find relevant book information according to the book title when modifying
    public int selectBookChanges(String bookname)
    {
        int dd = 0;
        for (int i = 0; i < book.booknames.length; i++)
        {
            if (bookname.equals(""))
            {
                dd = -1;// -1 means the book title is not entered
                break;
            }
            // In other cases, it matches the books in the library
            if (bookname.equals(book.booknames[i]))
            {
                dd = 1;// 1Find the related book title
                showRelatedBook(i + 1);
                break;
            }
            if (!bookname.equals(book.booknames[i]))
            {
                if (i == book.booknames.length - 1)
                {
                    dd = 0;// 0 means not found
                }
            }
        }
        return dd;
    }
    // Find the index based on the title of the book, return to its index if there is this book, if not return -1
    public int selectIndex(String bookname)
    {
        int ff = -1;
        for (int i = 0; i < book.booknames.length; i++)
        {
            if (bookname.equals(book.booknames[i]))
            {
                ff = i;
            }
        }
        return ff;// Can't find this book
    }
    // Modify book information 1 Title 2 Author 3 Publication date 4 Total pages 5 Borrowers
    public void modinfo(int a, String value, int index)
    {

        switch (a)
        {
            case 1:
                book.booknames[index] = value;
                break;

            case 2:
                book.authors[index] = value;
                break;
            case 3:
                book.pubdates[index] = value;
                break;
            case 4:
                book.sumpaginations[index] = value;
                break;
          /*  case 5:
                book.borrowreaders[index] = value;
                break;*/
        }

    }
    // Delete book function
    public void deleteBook(String bookname)
    {
        int a = selectIndex(bookname);
        book.booknames[a] = null;
        book.authors[a] = null;
        book.pubdates[a] = null;
        book.sumpaginations[a] = null;
        book.booknames[a] = null;

    }
    // Check if the book exists in the library and if it has been Taken in past
    public int testReader(String bookname)
    {

        // There is this book in the library, find the index of this book
        int a = selectIndex(bookname);
        // This book is in the library
        if (a >= 0)                                            // There is the book a>0,not return -1
        {
            if ((book.borrowreaders[a].equals(" ") || book.borrowreaders[a].equals("")))
                // No one takes this book, "" is the direct return value of the scanner
                return 0;//Not borrowed
            else
                return 1;//borrowed
        }
        else
            {
            return -1;// There is no such book in the library
        }

    }
    //Set the borrower to none or empty
    public void setBorrowReader(String bookname)
    {
        int a = selectIndex(bookname);
        book.borrowreaders[a]="None";
    }
    //Query the borrower of the specified book
    public String getBorrowReader(String bookname)
    {
        int a = selectIndex(bookname);
        return book.borrowreaders[a];
    }

    //Book borrowing function
    public void borrow(String bookname,String readername)
    {
        int a = selectBookChanges(bookname);
        book.borrowreaders[a]=readername;
    }
}