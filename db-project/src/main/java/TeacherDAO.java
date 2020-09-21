import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDAO {
    private Connection con;

    public TeacherDAO() throws ClassNotFoundException, SQLException {
        // 1. โหลด JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. กำหนด URL สำหรับติดต่อกับฐานข้อมูล
        String dbURL = "jdbc:mysql://localhost/registration?characterEncoding=utf-8";

        // 3. เชื่อมต่อกับฐานข้อมูล
        con = DriverManager.getConnection(dbURL, "root", "1234");
    }

    public Teacher findOne(String id) throws SQLException {
        PreparedStatement pStatement = con.prepareStatement("SELECT * FROM teacher WHERE tid = ?");
        pStatement.setString(1, id);
        ResultSet resultSet = pStatement.executeQuery();

        Teacher t = null;
        if (resultSet.next()) {
            t = new Teacher(resultSet.getString("tid"), resultSet.getString("tname"), resultSet.getString("status"));
        }
        return t;

    }

    public ArrayList<Teacher> findAll() throws SQLException {
        PreparedStatement pStatement = con.prepareStatement("SELECT * FROM teacher");
        ResultSet resultSet = pStatement.executeQuery();

        ArrayList<Teacher> list = new ArrayList<Teacher>();
        while (resultSet.next()) {
            // new Teacher เก็บไว้ในออปเจค t
            Teacher t = new Teacher(resultSet.getString("tid"), resultSet.getString("tname"),
                    resultSet.getString("status"));
            // เพิ่มออปเจค t ใสใน list
            list.add(t);
        }
        return list;
    }

    public void save(Teacher t) throws SQLException {

        PreparedStatement pStatement = con.prepareStatement("insert into  teacher (tname,status) values(?,?) ");
        pStatement.setString(1, t.getTeacherName());
        pStatement.setString(2, t.getStatus());

        pStatement.executeUpdate();
    }

    public void save(Teacher t, String id) throws SQLException {
        PreparedStatement pStatement = con.prepareStatement("update  teacher set tname=?, status=? where tid = ?");
        pStatement.setString(1, t.getTeacherName());
        pStatement.setString(2, t.getStatus());
        pStatement.setString(3, id);

        pStatement.executeUpdate();
    }

    public void closeConnect() throws SQLException {
        con.close();
    }

    public void delete(String id) throws SQLException {
        PreparedStatement pStatement = con.prepareStatement("delete from  teacher  where tid = ?");
        pStatement.setString(1, id);

        pStatement.executeUpdate();
    }

    public static void main(String[] args) {

        try {
            TeacherDAO dao = new TeacherDAO();
            dao.delete("8");
            /*
             * Teacher kom = new Teacher("", "คมเคียว", "m"); dao.save(kom);
             * 
             * Teacher beam = new Teacher("", "บีม", "s"); dao.save(beam, "4");
             * 
             * Teacher t = dao.findOne("1"); System.out.println("....." + t.getTeacherName()
             * + " " + t.getStatus());
             * 
             * ArrayList<Teacher> list = dao.findAll(); for (Teacher teacher : list) {
             * System.out.println(teacher.getTeacherName()); }
             */

            dao.closeConnect();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
