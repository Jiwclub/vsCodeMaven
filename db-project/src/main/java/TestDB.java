import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.mysql.cj.xdevapi.PreparableStatement;

public class TestDB {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbURL = "jdbc:mysql://localhost/blueshop?characterEncoding=utf-8";
            Connection con = DriverManager.getConnection(dbURL, "root", "1234");

            // ส่งคำสั่ง SQL ไปยังฐานข้อมูล
            PreparedStatement pstmt = con.prepareStatement("select * from product where price > ?");
            pstmt.setInt(1, 700);

            // ประการตัวแปรของ class ResultSet
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("pname"));
                System.out.println(rs.getString("price"));

            }
            con.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("สวัสดี");
    }
}