package com.company;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static Logger log=Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        try {
            Person b=new Person("","dv","ec","");
            Methods.FindsurNameSur(b);
            ServerSocket Server = new ServerSocket(1408);
            log.log(Level.INFO,"Waiting");
            while (true) {
                Socket socket = Server.accept();
                Runnable r=new ThreadEchoHandler(socket);
                Thread t=new Thread(r);
                t.start();
            }
        }
        catch (Exception c) {
            c.printStackTrace();
            log.log(Level.SEVERE,"Exception",c);
        }
    }
}
