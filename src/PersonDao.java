import bean.Person;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Елена on 15.12.2015.
 */
public class PersonDao {
    public PersonDao(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    public Map<String, String> getFromDb(int id) throws ServletException, IOException {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_second", "root", "Passw0rd4SQL");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from persons where id = " + id);

            Map<String, String> ps = new LinkedHashMap();
            while(rs.next()) {
                for(int i=1;i<=rs.getMetaData().getColumnCount();i++) {
                    ps.put(rs.getMetaData().getColumnName(i),rs.getString(i));
                }
            }
            rs.close();
            st.close();
            con.close();
            return ps;
        } catch (SQLException ex) {
            ex.toString();
        }
        return null;
    }

public ArrayList<Map<String,String>> getFromEditDb(){
    try{
        ArrayList<Map<String,String>> list=new ArrayList<Map<String, String>>();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_second", "root", "Passw0rd4SQL");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from persons;");
        while (rs.next()){
            Map<String, String> ps = new LinkedHashMap();
            for(int i=1;i<=rs.getMetaData().getColumnCount();i++) {
                ps.put(rs.getMetaData().getColumnName(i),rs.getString(i));
            }
            list.add(ps);
        }
        rs.close();
        st.close();
        con.close();
        return list;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public List<Person> getFromDb() throws ServletException, IOException {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_second", "root", "Passw0rd4SQL");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select id,name,surname from persons;");

            List<Person> ps = new ArrayList();
            while(rs.next()) {
                ps.add(new Person(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3)));
            }
            rs.close();
            st.close();
            con.close();
            return ps;
        } catch (SQLException ex) {
            ex.toString();
        }
        return null;
    }

    public List<String> getColumnsToDB (){
        List<String> col = new ArrayList<String>();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_second", "root", "Passw0rd4SQL");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='persons';");
            while(rs.next()) {
                col.add(rs.getString(1));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            ex.toString();
        }
        return col;
    }
    public void updateToDb(Map<String, String[]> params, String id,int i) {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_second", "root", "Passw0rd4SQL");
            Statement st = con.createStatement();
            String query=null;

            query = "update persons set name ='" + params.get("name")[i] + "',surname ='" + params.get("surname")[i] + "',birthday='"+params.get("birthday")[i]+"',passportSeries="+params.get("passportSeries")[i]+",passportNumber="+params.get("passportNumber")[i]+",gradeBookId="+params.get("gradeBookId")[i]+",averageScore="+params.get("averageScore")[i]+" where id = " + id+";";

            st.executeUpdate(query);

            st.close();
            con.close();

        } catch (SQLException ex) {
            ex.toString();
        }
    }
    public void updateToDb(Map<String,String[]> params,String id){
        String query="update persons set";
        for (String key: params.keySet()){
            if (!key.equals("meth") && !key.equals("id"))
                if (!params.get(key)[0].equals("")){
                    if (key.equals("name") || key.equals("surname") || key.equals("birthday")){
                        query += " " + key + "='" + params.get(key)[0] + "',";
                    }
                    else {
                        query += " " + key + "=" + params.get(key)[0] + ",";
                    }
                }
        }
        try{
            if (!query.equals("update persons set")){
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_second", "root", "Passw0rd4SQL");
                Statement st = con.createStatement();
                query=query.substring(0,query.length()-1)+" where id="+id+";";
                st.executeUpdate(query);
                st.close();
                con.close();
            }
        } catch (SQLException ex) {
            ex.toString();
        }
    }
    public void updateToDb(Map<String, String[]> params){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_second", "root", "Passw0rd4SQL");
            Statement st = con.createStatement();
            String query=null;
            query = "insert into persons(surname,name,birthday,passportNumber,passportSeries,gradeBookId,averageScore) value('"+params.get("surname")[0]+"','"+params.get("name")[0]+"','"+params.get("birthday")[0]+"',"+params.get("passportNumber")[0]+","+params.get("passportSeries")[0]+","+params.get("gradeBookId")[0]+","+params.get("averageScore")[0]+");";
            st.executeUpdate(query);

            st.close();
            con.close();

        } catch (SQLException ex) {
            ex.toString();
        }
    }

    public void updateToDb(String id){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_second", "root", "Passw0rd4SQL");
            Statement st = con.createStatement();
            String query = "delete from persons where id="+id+";";
            st.executeUpdate(query);

            st.close();
            con.close();

        } catch (SQLException ex) {
            ex.toString();
        }
    }

    public void updateToDb(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_second", "root", "Passw0rd4SQL");
            Statement st = con.createStatement();
            String query = "delete from persons;";
            st.executeUpdate(query);

            st.close();
            con.close();

        } catch (SQLException ex) {
            ex.toString();
        }
    }
}
