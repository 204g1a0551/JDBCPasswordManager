import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.Scanner;

public class Main extends DBMS{

    public static void main(String[] args) throws SQLException {
        String url="jdbc:postgresql://localhost:5432/Demo";
        String username="postgres";
        String password="Mahesh#13";
        Scanner scanner =new Scanner(System.in);
        Connection connection= DriverManager.getConnection(url,username,password);
        while(true) {
            message();
            int n=scanner.nextInt();
            switch (n) {
                case 1:
                    insert(connection);
                    break;
                case 2:
                    fetch(connection);
                    break;
                case 3:
                    update(connection);
                    break;
                case 4:
                    delete(connection);
                    break;
                default:
                    connection.close();
                    System.exit(0);
            }
        }

    }
}
