package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import main.Main;
import model.korisnik;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Profil implements Initializable {


    @FXML
    Label LoggedUserlbl;
    @FXML
    Button profil;
    @FXML
    Button knjige;
    @FXML
    Button povijest;
    @FXML
    Button odjava;
    @FXML
    BorderPane border_pane;



   /* @FXML
    public void addUserToDatabase (ActionEvent e) throws Exception{
        korisnik t = new korisnik();
        t.setIme(this.firstname.getText());
        t.setPrezime(this.lastname.getText());
        t.setEmail(this.email.getText());
        t.setSifra(this.password.getText());
        t.save();
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.LoggedUserlbl.setText(
                Login.loggedInKorisnik.getIme() +
                        " " +
                        Login.loggedInKorisnik.getPrezime()
        );
    }
    @FXML
    public void logout (ActionEvent ev) throws IOException {

        Login.loggedInKorisnik = null;
        Main.showWindow(
                getClass(),
                "../view/Login.fxml",
                "Prijavite se na sustav", 600, 300
        );
    }
    public void ucitajProfil(ActionEvent e) throws IOException{
        Main.showWindow(
                getClass(),
                "../view/Profil.fxml",
                "O nama",600,400
        );
    }

    public void ucitajKnjige(ActionEvent e) throws IOException{
        Main.showWindow(
                getClass(),
                "../view/Rezervacija.fxml",
                "Knjige",600,400
        );
    }


    public void ucitajRezervacije(ActionEvent e) throws IOException{
        Main.showWindow(
                getClass(),
                "../view/KorisnickeRezervacije.fxml",
                "Rezervacije",600,400
        );
    }

}