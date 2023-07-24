import java.io.*;
import java.sql.*;
import java.util.Scanner;


public class DBoperation extends Books {
    static Connetion mycon = new Connetion();


    public void addBook(Books books) {

        try {
            String sql = "INSERT INTO books VALUES (?,?,?,?)";
            PreparedStatement statement = mycon.getMyConnection().prepareStatement(sql);
            statement.setInt(1, books.getIsbn());
            statement.setString(2, books.getTitle());
            statement.setString(3, books.getCategory());
            statement.setString(4, books.getAuthor());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Congratulations! New book added.");
            }

        } catch (SQLException e) {
            //System.out.println("Connection problem Check it!");

        }

    }

    public void remove(int isbn){
        try{
            String deleteQuery = "DELETE FROM books WHERE isbn = ?";

            PreparedStatement statement=mycon.getMyConnection().prepareStatement(deleteQuery);
            statement.setInt(1,isbn);
            int rowsUpdate = statement.executeUpdate();
            if(rowsUpdate>0){
                System.out.println("The book deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lend(int isbn){



            try{



                String selectQuery = "SELECT * FROM books WHERE isbn = "+isbn;
                Statement selectStatement = mycon.getMyConnection().prepareStatement(selectQuery);;
                ResultSet resultSet = selectStatement.executeQuery(selectQuery);

                // Step 3: Print the data before deletion
                if (resultSet.next()) {
                    int is = resultSet.getInt("isbn");
                    String title = resultSet.getString("title");
                    String category = resultSet.getString("category");
                    String author = resultSet.getString("author");

                    System.out.println("Data before deletion:");
                    System.out.println("ID: " + is + ", title: " + title + ", category: " + category + ", authr: " + author);





                    String deleteQuery = "DELETE FROM books WHERE isbn = ?";

                    PreparedStatement statement=mycon.getMyConnection().prepareStatement(deleteQuery);
                    statement.setInt(1,isbn);
                    int rowsUpdate = statement.executeUpdate();
                    if(rowsUpdate>0){
                        System.out.println("The book deleted successfully!");
                    }







                    String insertQuery = "INSERT INTO removebook (isbn,title,category,author) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = mycon.getMyConnection().prepareStatement(insertQuery);


                    preparedStatement.setInt(1, is);
                    preparedStatement.setString(2, title);
                    preparedStatement.setString(3, category);
                    preparedStatement.setString(4, author);



                    int rowsInserted = preparedStatement.executeUpdate();
                    System.out.println(rowsInserted);
                    preparedStatement.close();





                }









            }catch (SQLException e) {
                System.out.println("not lend");
            }

    }

    public void returnbook(int isbn) {

        try{



            String selectQuery = "SELECT * FROM removebook WHERE isbn = "+isbn;
            Statement selectStatement = mycon.getMyConnection().prepareStatement(selectQuery);;
            ResultSet resultSet = selectStatement.executeQuery(selectQuery);

            // Step 3: Print the data before deletion
            if (resultSet.next()) {
                int is = resultSet.getInt("isbn");
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                String author = resultSet.getString("author");

                System.out.println("Data before deletion:");
                System.out.println("ID: " + is + ", title: " + title + ", category: " + category + ", authr: " + author);





                String deleteQuery = "DELETE FROM removebook WHERE isbn = ?";

                PreparedStatement statement=mycon.getMyConnection().prepareStatement(deleteQuery);
                statement.setInt(1,isbn);
                int rowsUpdate = statement.executeUpdate();
                if(rowsUpdate>0){
                    System.out.println("The book deleted successfully!");
                }







                String insertQuery = "INSERT INTO books (isbn,title,category,author) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = mycon.getMyConnection().prepareStatement(insertQuery);


                preparedStatement.setInt(1, is);
                preparedStatement.setString(2, title);
                preparedStatement.setString(3, category);
                preparedStatement.setString(4, author);



                int rowsInserted = preparedStatement.executeUpdate();
                System.out.println(rowsInserted);
                preparedStatement.close();





            }







        }catch (SQLException e) {
            System.out.println("not lend");
        }
    }

    public static void displayAvailableBooks() {
        try  {
            String selectQuery = "SELECT * FROM books WHERE isbn";
            PreparedStatement preparedStatement = mycon.getMyConnection().prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Available Books:");
            System.out.println("------------------------------");


            while (resultSet.next()) {
                int bookId = resultSet.getInt("isbn");
                String title = resultSet.getString("title");
                String catagary = resultSet.getString("category");
                String author = resultSet.getString("author");
                System.out.println("Book ISBN: " + bookId + ", Title: " + title +"category: "+ catagary+ "Author: " + author);
                System.out.println("-----------------------------");
            }


            System.out.println();
            System.out.println();
            System.out.println();
            String selectQuerys = "SELECT * FROM removebook WHERE isbn";
            PreparedStatement preparedStatements = mycon.getMyConnection().prepareStatement(selectQuerys);
            ResultSet resultSets = preparedStatements.executeQuery();

            System.out.println("Lending Books:");
            System.out.println("------------------------------");


            while (resultSets.next()) {
                int bookId = resultSets.getInt("isbn");
                String title = resultSets.getString("title");
                String catagary = resultSets.getString("category");
                String author = resultSets.getString("author");
                System.out.println("Book ISBN: " + bookId + ", Title: " + title +"category: "+ catagary+ "Author: " + author);
                System.out.println("-----------------------------");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void updating(){

        Scanner sc = new Scanner(System.in);
        int isbn;
        String title;
        String category;
        String author;

        System.out.println("Update an existing book");
        System.out.println("Enter the ISBN that need to update");
        isbn=sc.nextInt();
        System.out.println("Update the book:");
        System.out.println("Enter the new title");
        title=sc.nextLine();
        title+=sc.nextLine();
        System.out.println("Enter the new category:");
        category=sc.next();
        category+=sc.nextLine();
        System.out.println("Enter the new author:");
        author=sc.next();
        author+=sc.nextLine();


        try {
            String sql = "UPDATE books SET title = ?, category = ? , author=? WHERE isbn = ?";
            PreparedStatement statement = mycon.getMyConnection().prepareStatement(sql);

            // Set values for the placeholders (?) in the SQL query
            statement.setString(1, title);
            statement.setString(2, category); // new_value_for_column2
            statement.setString(3, author);
            statement.setInt(4, isbn);// Specify the record to update based on the 'id' column

            // Execute the update query
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data updated successfully!");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




}
