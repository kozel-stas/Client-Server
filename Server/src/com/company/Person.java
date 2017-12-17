package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Person {
    private String method;
    private String name;
    private String surname;
    private String mobnumber;
    public Person(String method, String Name, String Surname, String mobnumber){
        this.method=method;
        this.name=Name;
        this.mobnumber=mobnumber;
        this.surname=Surname;
    }

    public Person(){
    }

    public String getSurname() {
        return surname;
    }

    public String getMobnumber() {
        return mobnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setMobnumber(String mobnumber) {
        this.mobnumber = mobnumber;
    }

    public void setSurname(String surname) {
        surname = surname;
    }
}
class JSonModified {
    private static Gson Json= new GsonBuilder().create();
    public static String toJSonModif(ArrayList<Person> arrayList){
        if (arrayList==null || arrayList.size()==0){
            return "{}";
        }
        else {
            StringBuilder b = new StringBuilder("");
            b.append("{");
            for (int i = 0; i < arrayList.size(); i++) {
                b.append(Json.toJson(arrayList.get(i)));
                b.append(";");
            }
            String z = b.toString();
            if (z.charAt(z.length() - 1) != '{')
                z = z.substring(0, z.length() - 1);
            z = z + "}";
            return z;
        }
    };

    public static ArrayList<Person> FromJsonModified(String a) {
        ArrayList<Person> b = new ArrayList<>();
        if (a.equals("{}")) {
            return null;
        } else {
            int i = a.indexOf(';');
            if (i == -1 && a.length() > 10)
                b.add(Json.fromJson(a.substring(1,a.length()-1), Person.class));
            else {
                int z = 1;
                a = a.substring(z, a.length());
                while (i != -1) {
                    String x = a.substring(0, i);
                    a = a.substring(i, a.length());
                    if (a.charAt(a.length() - 1) == ';') a = a.substring(0, a.length() - 1);
                    if (a.charAt(0) == ';') a = a.substring(1, a.length());
                    i = a.indexOf(';');
                    if (x.charAt(x.length() - 1) == ';') x = x.substring(0, x.length() - 1);
                    if (x.charAt(0) == ';') x = x.substring(1, x.length());
                    if (i == -1 && a.length() > 10)
                        b.add(Json.fromJson(a.substring(0, a.length() - 1), Person.class));
                    b.add(Json.fromJson(x, Person.class));
                }
            }
        }
        return b;
    }
}
