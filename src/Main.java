
import java.util.Scanner;

public class Main {


    public static Scanner sc = new Scanner(System.in);
    static DBoperation objDBO = new DBoperation();

    public static void main(String[] args) {


        boolean isRunning = true;
        while (isRunning) {
            showOptions();

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addBooks();
                    break;
                case 2:
                    //updatingbook();
                    break;
                case 3:
                    Removebook();
                    break;
                case 4:
                    //bookAdd();
                    break;

                case 5:
                    lendingbook();
                    break;

                case 6:


                    //AvailableBooks();
                    break;
                case 7:
                    System.out.println("Exiting Library Management System.");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }


    }
    public static void showOptions () {
        System.out.println("Welcome to the Library Mnagemnt System UOK!");
        System.out.println("Please choose an option");
        System.out.println("1.Add a new book.");
        System.out.println("2.Update an existing book.");
        System.out.println("3.Remove an existing book.");
        System.out.println("4.Lend a book.");
        System.out.println("5.return a book.");
        System.out.println("6.List all avaliable book.");
        System.out.println("7.Exit");
    }

    public static void addBooks(){

        Books books = new Books();
        System.out.println("----Add a new book----");
        System.out.println("Enter the isbn :");
        books.setIsbn(sc.nextInt());
        System.out.println("Enter the Title : ");
        sc.nextLine();
        books.setTitle(sc.nextLine());
        System.out.println("Enter the Category : ");
        books.setCategory(sc.nextLine());
        System.out.println("Enter the Author : ");
        books.setAuthor(sc.nextLine());

        objDBO.addBook(books);


    }

    public static void Removebook(){
        System.out.println("Remove and existing book.");
        System.out.println("REnter isbn number.");
        int x =sc.nextInt();
        System.out.println("\nAre you sure remove this book(y/n): ");
        String confirm = sc.next();
        if(confirm.equals("y")){
            objDBO.remove(x);
        }else{
            System.out.println("Did not clear data!");
        }
    }

    public static void lendingbook(){
        System.out.println("Lending a book.");
        System.out.println("REnter isbn number.");
        int x =sc.nextInt();
        objDBO.lend(x);
    }

    public static void updatingbook(){
        System.out.println("Lending a book.");
        System.out.println("REnter isbn number.");
        int x =sc.nextInt();
        objDBO.lend(x);
    }
    public static void availblebook(){


    }





}
















