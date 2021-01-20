


public class BookInformation
{
    //Book list
    public BookInformation()
    {
        {
            String noreaders= "No";
            for (int i = 0; i < borrowreaders.length; i++)
            {
                borrowreaders[i]=noreaders;
            }
            for (int i = 0; i < 5; i++)
            {

                switch (i)                                         //Using switch case
                {
                    case 0:
                        booknames[i]="JAVA Language ";
                        authors[i]="James Gosling";
                        pubdates[i]="2020-12-05";
                        sumpaginations[i]="1654";
                        break;
                    case 1:
                        booknames[i]="Data Structure and Algorithms";
                        authors[i]="Alfred V. Aho";
                        pubdates[i]="1999-07-25";
                        sumpaginations[i]="656";
                        break;
                    case 2:
                        booknames[i]="Operating Systems";
                        authors[i]="Gray J Nutt";
                        pubdates[i]="2016-01-31";
                        sumpaginations[i]="1120";
                        break;
                    case 3:
                        booknames[i]="Let Us C";
                        authors[i]="Yashavant Kanetkar";
                        pubdates[i]="2017-04-04";
                        sumpaginations[i]="1310";
                        break;
                    case 4:
                        booknames[i]="Algorithms";
                        authors[i]="Thomas H Cormen";
                        pubdates[i]="2012-08-20";
                        sumpaginations[i]="954";
                        break;
                }
            }

        }
    }

    public String[] booknames = new String[10];         // for add a new book here we write book name
    public String[] authors = new String[10];            // Author
    public String[] pubdates = new String[10];           // Publication date
    public String[] sumpaginations = new String[10];     // Total number of pages
    public String[] borrowreaders = new String[10];     // borrower

}
