package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

public class ThreadEchoHandler implements Runnable {
    private Socket client;
    private static Gson Json= new GsonBuilder().create();
    public ThreadEchoHandler(Socket st) {
        client = st;
    }

    @Override
    public void run() {
        try {
            Main.log.log(Level.INFO,"Somebody connect");
            InputStream sin = client.getInputStream();
            OutputStream sout = client.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            String Line = null;
            Line=in.readUTF();
            Person a=Json.fromJson(Line,Person.class);
            ArrayList<Person> b=null;
            switch (a.getMethod()) {
                case "FindName":
                    b = Methods.FindName(a);
                    if (b.size() == 0) b = null;
                    break;
                case "FindSurname":
                    b = Methods.FindsurName(a);
                    if (b.size() == 0) b = null;
                    break;
                case "FindsurNameSur":
                    b = Methods.FindsurNameSur(a);
                    if (b.size() == 0) b = null;
                    break;
                case "FindMobnumber":
                    b = Methods.Findmobnumber(a);
                    if (b.size() == 0) b = null;
                    break;
                case "Add":
                    Methods.AddPerson(a);
                    break;
                case "Delete":
                    Methods.Delete(a);
                    break;
            }
            Line=JSonModified.toJSonModif(b);
            out.writeUTF(Line);
            client.close();
            Main.log.log(Level.INFO,client+"Somebody disconnect");
        }
        catch (Exception x){
            x.printStackTrace();
            Main.log.log(Level.SEVERE,"Exception",x);
        }
    }
}
