import java.io.*;
import java.sql.*;



public class DBoperation {
    static Connetion mycon = new Connetion();

    public boolean isRegister() {
        String sql = "SELECT * FROM userDetails";

        try {
            Statement statement = mycon.getMyConnection().createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
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
}
