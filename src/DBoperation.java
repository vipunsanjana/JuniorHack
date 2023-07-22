import java.io.*;
import java.sql.*;



public class DBoperation {
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
            System.out.println("Connection problem Check it!");

        }

    }

    public void remove(int isbn){
        try{
            String deleteQuery = "DELETE FROM books WHERE isbn = ?";

            PreparedStatement statement=mycon.getMyConnection().prepareStatement(deleteQuery);
            statement.setInt(1,isbn);
            int rowsUpdate = statement.executeUpdate();
            if(rowsUpdate>0){
                System.out.println("A record was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lend(int isbn){
        try{
            String updateQuery = "UPDATE books SET title = ? WHERE isbn = ?";

            PreparedStatement statement=mycon.getMyConnection().prepareStatement(updateQuery);
            String newCellValue = "Lended book";
            statement.setString(1, newCellValue);
            int rowsUpdate = statement.executeUpdate();
            if(rowsUpdate>0){
                System.out.println("The book is lended!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnbook(int isbn) {
        try {
            String updateQuery = "UPDATE books SET title = ? WHERE isbn = ?";
            Books b = new Books();
            PreparedStatement statement = mycon.getMyConnection().prepareStatement(updateQuery);
            String newCellValue = "avilable" + b.getTitle();
            statement.setString(1, newCellValue);
            int rowsUpdate = statement.executeUpdate();
            if (rowsUpdate > 0) {
                System.out.println("The book is lended!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void displayAvailableBooks() {
        try  {
            String selectQuery = "SELECT * FROM books WHERE isbn = ?";
            PreparedStatement preparedStatement = mycon.getMyConnection().prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Available Books:");
            System.out.println("------------------------------");
            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String catagary = resultSet.getString("category");
                String author = resultSet.getString("author");
                System.out.println("Book ID: " + bookId + ", Title: " + title +"category: "+ catagary+ "Author: " + author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
