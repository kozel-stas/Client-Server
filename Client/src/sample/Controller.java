package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Person;

import java.util.ArrayList;

public class Controller {
    private Person DellPerson = null;
    private ObservableList<Person> usersData = FXCollections.observableArrayList();
    @FXML
    private TextField TextName;
    @FXML
    private TextField TextSurname;
    @FXML
    private TextField TextMobnumber;
    @FXML
    private TextField TextFind;
    @FXML
    private TextField TextNameDel;
    @FXML
    private TextField TextSurnameDel;
    @FXML
    private TextField TextMobnumberDel;
    @FXML
    private TableView<Person> Table1;
    @FXML
    private TableColumn<Person, String> Name1;
    @FXML
    private TableColumn<Person, String> Surname;
    @FXML
    private TableColumn<Person, String> Mobnumber;

    public void AddModal(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ModaleAdd.fxml"));
            primaryStage.setTitle("Добавить");
            primaryStage.setScene(new Scene(root, 420, 268));
            primaryStage.setResizable(false);
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            Image ico = new Image("ostis.png");
            primaryStage.getIcons().add(ico);
            primaryStage.show();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void Cancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        DellPerson = null;
        stage.close();
    }

    public void Add(ActionEvent actionEvent) {
        String Name = TextName.getText();
        String Surname = TextSurname.getText();
        String Mobnumber = TextMobnumber.getText();
        if (Name.equals("") == false && Surname.equals("") == false && Mobnumber.equals("") == false) {
            TextName.setText("");
            TextSurname.setText("");
            TextMobnumber.setText("");
            Person a = new Person("Add", Name, Surname, Mobnumber);
            Main.ServerConnect(a);
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Поля не должны быть пустыми!");
            alert.showAndWait();
        }
    }

    public void Alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Ничего не найдено");
        alert.showAndWait();
    }

    public void Find(ActionEvent actionEvent) {
        ArrayList<Person> z = new ArrayList<>();
        if (TextFind.getText().equals("") == false) {
            String Find = TextFind.getText();
            TextFind.setText("");
            Person b = null;
            if (Find.charAt(0) == '+' && Find.length() == 13) {
                b = new Person("FindMobnumber", "", "", Find);
                z = Main.ServerConnect(b);
                if (z == null) {
                    Alerta();
                } else {
                    for (int i = 0; i < z.size(); i++)
                        usersData.add(0, z.get(i));
                    Update();
                }
            } else {
                if (Find.indexOf(' ') == -1) {
                    b = new Person("FindName", Find, "", "");
                    z = Main.ServerConnect(b);
                    if (z == null) {
                        b = new Person("FindSurname", "", Find, "");
                        z = Main.ServerConnect(b);
                        if (z == null) {
                            Alerta();
                        } else {
                            for (int i = 0; i < z.size(); i++)
                                usersData.add(0, z.get(i));
                            Update();
                        }
                    } else {
                        for (int i = 0; i < z.size(); i++)
                            usersData.add(0, z.get(i));
                        Update();
                    }
                } else {
                    int x = Find.indexOf(' ');
                    b = new Person("FindsurNameSur", Find.substring(x + 1, Find.length()), Find.substring(0, x), "");
                    z = Main.ServerConnect(b);
                    if (z == null) {
                        b = new Person("FindsurNameSur", Find.substring(0, x), Find.substring(x + 1, Find.length()), "");
                        z = Main.ServerConnect(b);
                        if (z == null) {
                            Alerta();
                        } else {
                            for (int i = 0; i < z.size(); i++)
                                usersData.add(0, z.get(i));
                            Update();
                        }
                    } else {
                        for (int i = 0; i < z.size(); i++)
                            usersData.add(0, z.get(i));
                        Update();
                    }
                }
            }
        }
    }

    public void Update() {
        Name1.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        Surname.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
        Mobnumber.setCellValueFactory(new PropertyValueFactory<Person, String>("mobnumber"));
//        Table1.getSelectionModel().selectedItemProperty().addListener(
//                ((observable, oldValue, newValue) -> DEll(newValue))
//        );
        Table1.setItems(usersData);
    }

//    public void DEll(Person a,ActionEvent actionEvent) {
//        try {
//            DellPerson = a;
//            Stage primaryStage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("Del.fxml"));
//            primaryStage.setTitle("Удалить");
//            primaryStage.setScene(new Scene(root, 380, 91));
//            primaryStage.setResizable(false);
//            primaryStage.initModality(Modality.WINDOW_MODAL);
//            //primaryStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
//            Image ico = new Image("ostis.png");
//            primaryStage.getIcons().add(ico);
//            primaryStage.show();
//        } catch (Exception x) {
//            x.printStackTrace();
//        }
//    }


    public void Delete(ActionEvent actionEvent) {
        String Name = TextNameDel.getText();
        String Surname = TextSurnameDel.getText();
        String Mobnumber = TextMobnumberDel.getText();
        if (Name.equals("") == false && Surname.equals("") == false) {
            TextNameDel.setText("");
            TextSurnameDel.setText("");
            TextMobnumberDel.setText("");
            ArrayList<Person> b = new ArrayList<>();
            Person a = new Person("FindsurNameSur", Name, Surname, Mobnumber);
            b = Main.ServerConnect(a);
            if (b != null) {
                a.setMethod("Delete");
                Main.ServerConnect(a);
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Не удалось удалить");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Поля не должны быть пустыми!");
            alert.showAndWait();
        }
    }

    public void Del(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ModaleDelete.fxml"));
            primaryStage.setTitle("Удалить");
            primaryStage.setScene(new Scene(root, 420, 268));
            primaryStage.setResizable(false);
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            Image ico = new Image("ostis.png");
            primaryStage.getIcons().add(ico);
            primaryStage.show();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

//    public void DEL(ActionEvent actionEvent) {
//        ArrayList<Person> b = new ArrayList<>();
//        DellPerson.setMethod("FindsurNameSur");
//        b = Main.ServerConnect(DellPerson);
//        if (b != null) {
//            DellPerson.setMethod("Delete");
//            Main.ServerConnect(DellPerson);
//            usersData.removeAll(DellPerson);
//            DellPerson=null;
//            Node source = (Node) actionEvent.getSource();
//            Stage stage = (Stage) source.getScene().getWindow();
//            stage.close();
//            Update();
//        }
    //}
}