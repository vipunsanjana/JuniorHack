import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connetion {

        public Connection getMyConnection(){
            String path="jdbc:mysql://localhost:3306/librarymanegment";
            String user="root";
            String password="";
            Connection con= null;
            try {
                con = DriverManager.getConnection(path,user,password);
            } catch (SQLException e) {
                System.out.println("There is some problem to connect with database");
            }
            return con;
        }


}
