import org.postgresql.util.PSQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBMS {
    static void message()
    {
        System.out.println("Welcome to Password Manager Console");
        System.out.println("Select any one of the options");
        System.out.println("|--------------------------------------------------|");
        System.out.println("| 1. Save Password into the database               |");
        System.out.println("| 2. Fetch Password with UserName and Website      |");
        System.out.println("| 3. Update the Password with UserName and Website |");
        System.out.println("| 4. Delete a password from the database           |");
        System.out.println("| 5. Exit from console                             |");
        System.out.println("|--------------------------------------------------|");
    }
    public static void insert(Connection connection) throws SQLException {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter The website name: ");
        String website = scanner.next();
        System.out.println("Enter the username: ");
        String uname = scanner.next();
        System.out.println("Enter the password");
        String pass = scanner.next();
        String sql = "INSERT INTO password VALUES(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, website);
        statement.setString(2, uname);
        statement.setString(3, pass);
        statement.execute();
    }
    public static void fetch(Connection connection) throws PSQLException,SQLException{
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter The Website Name to fetch your password: ");
        String website1 = scanner.next();
        System.out.println("Enter the username to fetch your password");
        String user = scanner.next();
        String sql1 = "SELECT password FROM password WHERE website=? AND username= ?";
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        try {
            statement1.setString(1, website1);
            statement1.setString(2, user);
            statement1.execute();
            ResultSet set = statement1.getResultSet();
            set.next();
            System.out.println("Password is " + set.getString(1));
        }
        catch (Exception e)
        {
            System.out.println("The entered records are not there in database");
        }
    }
    public static void update(Connection connection) throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the website name to update your password:");
        String web2= scanner.next();
        System.out.println("Enter the username to update your password: ");
        String user1=scanner.next();
        System.out.println("Enter your old password :");
        String pass1=scanner.next();
        try {
            String sql2 = "SELECT password FROM password WHERE website=? AND username= ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setString(1, web2);
            statement2.setString(2, user1);
            statement2.execute();
            ResultSet set1 = statement2.getResultSet();
            set1.next();
            if (set1.getString(1).equals(pass1)) {
                System.out.println("Enter your new password: ");
                String newpass = scanner.next();
                String update = "UPDATE password SET password = ? WHERE website =? AND username=?";
                PreparedStatement statement3 = connection.prepareStatement(update);
                statement3.setString(1, newpass);
                statement3.setString(2, web2);
                statement3.setString(3, user1);
                statement3.execute();
                System.out.println("Update Successfully!");
            } else {
                System.out.println("Login failed");
            }
        }catch (Exception e)
        {
            System.out.println("Invalid Credientials");
        }
    }
    public static void delete(Connection connection) throws SQLException {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the website name to delete the password");
        String web2= scanner.next();
        System.out.println("Enter the username to delete the password");
        String user1=scanner.next();
        System.out.println("Enter your old password: ");
        String pass1=scanner.next();
        try {
            String sql2 = "SELECT password FROM password WHERE website=? AND username= ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setString(1, web2);
            statement2.setString(2, user1);
            statement2.execute();
            ResultSet set1 = statement2.getResultSet();
            set1.next();
            if (set1.getString(1).equals(pass1)) {
                String del = "DELETE FROM password WHERE website=? AND username=? AND password=?";
                PreparedStatement delstatement = connection.prepareStatement(del);
                delstatement.setString(1, web2);
                delstatement.setString(2, user1);
                delstatement.setString(3, pass1);
                delstatement.execute();
            } else {
                System.out.println("Invalid Credinatials");
            }
        }catch (Exception e)
        {
            System.out.println("Invalid Credinatials");
        }
    }
}
