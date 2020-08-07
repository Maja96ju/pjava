package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        /* korisnik admin = new korisnik();
        admin.setIme("Admin");
        admin.setPrezime("Admin");
        admin.setKorisnicko_ime("admin1");
        admin.setSifra("1234");
        admin.setEmail("admin@fpmoz.sum.ba");
        admin.setId_tipa_korisnika(1);
        admin.save();

        //tip_korisnika tk = new tip_korisnika();
        //tk.setIme_uloge("Admin");
        //tk.save();

        //tip_korisnika tk = new tip_korisnika();
        //tk.setIme_uloge("Korisnik");
       // tk.save();

        /*

        //tip_korisnika tk = new tip_korisnika();
        //tk.setIme_uloge("Admin");
        //tk.save();

        //tip_korisnika tk = new tip_korisnika();
        //tk.setIme_uloge("Korisnik");
        //tk.save();

        Table.create(tip_korisnika.class);
         Table.create(korisnik.class);
                Table.create(autor.class);
                Table.create(knjiga.class);
                Table.create(rezervacija.class);




        //Table.create(Course.class);
        /*

        Table.create(Teacher.class);
        Table.create(Course.class);



        Teacher t = (Teacher) Teacher.get(Teacher.class, 1);
        t.setLastname("Vasić");
        t.update();

        Teacher t = (Teacher) Teacher.get(Teacher.class, 1);
        t.delete();
         */

        Main.primaryStage = primaryStage;
        Main.showWindow(
                getClass(),
                "../view/Login.fxml",
                "Prijavte se na sustav", 600, 300);
    }

    public static void showWindow(Class windowClass, String viewName, String title, int w, int h) throws IOException {
        Parent root = FXMLLoader.load(windowClass.getResource(viewName));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, w, h));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
