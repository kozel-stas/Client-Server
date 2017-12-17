package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

public class Main extends Application {
    private static Gson Json= new GsonBuilder().create();
    @Override
    public void start(Stage primaryStage) throws Exception{
//        ArrayList <Person> a=new ArrayList<>();
//        a.add(new Person("","lox","sdw","ewd"));
//        a.add(new Person("","loxwd","sdw685","ewd"));
//        a.add(new Person("","loxq","sdwrtg","ewd"));
//        a.add(new Person("","loxq","sdwrtg","ewd"));
//        a.add(new Person("","loxq","sdwrtg","ewd"));
//        a.add(new Person("","loxq","sdwrtg","ewd"));
//        a.add(new Person("","loxq","sdwrtg","ewd"));
//        String b=JSonModified.toJSonModif(a);
//        a=JSonModified.FromJsonModified(b);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Mobri");
        Image ico = new Image("ostis.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setScene(new Scene(root, 520, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static ArrayList<Person> ServerConnect (Person b){
        String a=Json.toJson(b);
        try {
            Socket socket = new Socket("localhost", 1408);
            InputStream sin=socket.getInputStream();
            OutputStream sout=socket.getOutputStream();
            DataInputStream in=new DataInputStream(sin);
            DataOutputStream out=new DataOutputStream(sout);
            out.writeUTF(a);
            a=in.readUTF();
        }
        catch (Exception x){
            x.printStackTrace();
        }
        return JSonModified.FromJsonModified(a);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
