import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;

public class Teacher {
    public static Connection connection(){
        String url = "jdbc:mysql://localhost:3306/dbjava";
        String username = "root";
        String password = "";
        try{
            return DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args){
        String name;
        String gender;
        String phoneNumber;
        Scanner sc = new Scanner(System.in);
        int op;
        do{
            System.out.println("------  MENU  -------");
            System.out.println("1. INSERT ");
            System.out.println("2. SELECT ");
            System.out.println("3. SEARCH by ID ");
            System.out.println("4. SEARCH by NAME ");
            System.out.println("5. UPDATE BY ID ");
            System.out.println("6. DELETE by ID ");
            System.out.println("7. SORT bY ID DESC");
            System.out.println("8. SORT by NAME ASC");
            System.out.print("Enter option : ");
            op = sc.nextInt();
            switch (op){
                case 1->{
                    System.out.println("------  INSERT  ------");
                    System.out.print("Enter number of teacher ");
                    int n = sc.nextInt();
                    for(int i = 0; i < n; i++) {
                        System.out.println("------  Teacher No: " + (i + 1) + " ------");
                        System.out.print("Entre name : ");
                        sc.nextLine();
                        name = sc.nextLine();
                        System.out.print("Enter gender : ");
                        gender = sc.nextLine();
                        System.out.print("Enter phone number : ");
                        phoneNumber = sc.nextLine();
                        String sql = "insert into teacher (name,gender,phone) values(?,?,?)";
                        try{
                            Connection con = connection();
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setString(1,name);
                            ps.setString(2,gender);
                            ps.setString(3,phoneNumber);
                            ps.executeUpdate();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("INSERT SUCCESSFULLY");
                }
                case 2->{
                    System.out.println("------  SELECT  ------");
                    try{
                        Connection con  = connection();
                        String sql = "SELECT * FROM teacher";
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while (rs.next()){
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("gender = "+rs.getString("gender"));
                            System.out.println("phone Number : "+rs.getString("phone"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 3->{
                    System.out.println("------  SEARCH by ID  ------");
                    System.out.print("Enter id to search : ");
                    int id = sc.nextInt();
                    String sql = "SELECT * FROM teacher WHERE id = ?";
                    try{
                        Connection con = connection();
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setInt(1,id);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()){
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("Phone Number = "+rs.getString("phone"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 4->{
                    System.out.println("------  SEARCH by Name  ------");
                    System.out.print("Enter name to search : ");
                    sc.nextLine();
                    String names = sc.nextLine();
                    String sql = "SELECT * FROM teacher WHERE  name LIKE?";
                    try{
                        Connection con = connection();
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1,"%"+names+"%");
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()){
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("Phone number = "+rs.getString("phone"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 5->{
                    System.out.println("------  UPDATE by ID  ------");
                    System.out.print("Enter id to update : ");
                    int id = sc.nextInt();
                    System.out.print("Enter name : ");
                    sc.nextLine();
                    name =sc.nextLine();
                    System.out.print("Enter gender : ");
                    gender = sc.nextLine();
                    System.out.print("Enter phone number : ");
                    phoneNumber = sc.nextLine();
                    String sql = "UPDATE teacher SET name=?,gender=?,phone =? WHERE id=?";
                    try{
                        Connection con = connection();
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1,name);
                        ps.setString(2,gender);
                        ps.setString(3,phoneNumber);
                        ps.executeUpdate();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 6->{
                    System.out.println("------  DELETE  by ID  ------");
                    System.out.print("Enter id to delete : ");
                    int id = sc.nextInt();
                    String sql = "DELETE FROM studentInfo WHERE id = "+id;
                    try{
                        Connection con = connection();
                        Statement st = con.createStatement();
                        st.execute(sql);
                        System.out.println("Delete successfully");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 7->{
                    System.out.println("------  SORT by ID DESC  ------");
                    String sql = "SELECT * FROM teacher ORDER BY id DESC";
                    try{
                        Connection con = connection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while (rs.next()){
                            System.out.println("ID : "+rs.getInt("id"));
                            System.out.println("Name : "+rs.getString("name"));
                            System.out.println("Gender : "+rs.getString("gender"));
                            System.out.println("Phone number : "+rs.getString("phone"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 8->{
                    System.out.println("------  SORT by NAME ------");
                    String sql  = "SELECT * FROM teacher ORDER BY name ASC";
                    try{
                        Connection con = connection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        System.out.println("ID : "+rs.getInt("id"));
                        System.out.println("Name : "+rs.getString("name"));
                        System.out.println("Gender : "+rs.getString("phone"));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }while (true);
    }
}
