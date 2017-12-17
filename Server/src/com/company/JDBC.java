package com.company;

import java.sql.*;
abstract class JDBC {

    public static  final String url = "jdbc:mysql://localhost:3306/pivs";
    public static String user = "root";
    public static String password = "root";
    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;

    public static void connect(String query){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void connect1(String query){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

}
