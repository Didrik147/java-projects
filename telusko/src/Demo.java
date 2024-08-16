import java.sql.*;
import static java.lang.System.*;

public class Demo {
    public static void main(String[] args) throws Exception {

        String sql = "SELECT name FROM product WHERE id=3";
        String url = "jdbc:postgresql://localhost:5432/telusko";
        String username = "postgres";
        String password = "aldri";

        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        rs.next();
        String name = rs.getString(1);
        out.println(name);
  }
}
