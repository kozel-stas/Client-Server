package com.company;

import java.util.ArrayList;

public class Methods {
    public static ArrayList<Person>  FindName(Person a) throws Exception{
        ArrayList<Person> b=new ArrayList<>();
        String Jquery="select * from server where name1 = '"+a.getName()+'\'';
        JDBC.connect(Jquery);
        while (JDBC.rs.next()){
            b.add(new Person(null,JDBC.rs.getString(2),JDBC.rs.getString(3),JDBC.rs.getString(4)));
        }
        return b;
    }
    public static ArrayList<Person> FindsurName(Person a) throws Exception{
        ArrayList<Person> b=new ArrayList<>();
        String Jquery="select * from server where surname = '"+a.getSurname()+'\'';
        JDBC.connect(Jquery);
        while (JDBC.rs.next()){
            b.add(new Person(null,JDBC.rs.getString(2),JDBC.rs.getString(3),JDBC.rs.getString(4)));
        }
        return b;
    }

    public static ArrayList<Person> FindsurNameSur(Person a) throws Exception{
        ArrayList<Person> b=new ArrayList<>();
        String Jquery="select * from server where surname = '"+a.getSurname()+"\' and name1 = '"+a.getName()+'\'';
        JDBC.connect(Jquery);
        while (JDBC.rs.next()){
            b.add(new Person(null,JDBC.rs.getString(2),JDBC.rs.getString(3),JDBC.rs.getString(4)));
        }
        return b;
    }

    public static ArrayList<Person> Findmobnumber(Person a) throws Exception{
        ArrayList<Person> b=new ArrayList<>();
        String Jquery="select * from server where mobnumber= '"+a.getMobnumber()+'\'';
        JDBC.connect(Jquery);
        while (JDBC.rs.next()){
            b.add(new Person(null,JDBC.rs.getString(2),JDBC.rs.getString(3),JDBC.rs.getString(4)));
        }
        return b;
    }

    public static boolean AddPerson(Person a) throws Exception{
        String Jquery="insert into server (name1,surname,mobnumber) values ('"+a.getName()+"\','"+a.getSurname()+"\','"+a.getMobnumber()+"\')";
        JDBC.connect1(Jquery);
        return true;
    }

    public static boolean Delete(Person a){
        String Jquery="DELETE FROM server WHERE name1 = '"+a.getName()+"\' and surname = '"+a.getSurname()+"'";
        JDBC.connect1(Jquery);
        return true;
    }


}
