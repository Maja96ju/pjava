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


public class Admin implements Initializable {
    @FXML
    Label LoggedUserlbl;

    @FXML
    Button odjava;

    @FXML
    Button autori;

    @FXML
    Button knjige;

    @FXML
    Button clanovi;

    @FXML
    BorderPane border_pane;

    @FXML
    Button rezervacija;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.LoggedUserlbl.setText(
                Login.loggedInKorisnik.getIme() +
                        " " +
                Login.loggedInKorisnik.getPrezime()
        );
    }

    @FXML
    public void logout(ActionEvent ev) throws IOException {
        Login.loggedInKorisnik = null;
        Main.showWindow(
                getClass(),
                "../view/Login.fxml",
                "Prijavite se na sustav", 600, 300
        );
    }

    @FXML
    public void ucitajAutore(ActionEvent e) throws IOException{
        Main.showWindow (
                getClass(),
                "../view/Autori.fxml",
                "Autori", 600, 400 );
    }

    @FXML
    public void ucitajKnjige(ActionEvent e) throws IOException{
        Main.showWindow (
                getClass(),
                "../view/Knjige.fxml",
                "Knjige", 600, 400 );
    }
    @FXML
    public void ucitajClanove(ActionEvent e) throws IOException{
        Main.showWindow (
                getClass(),
                "../view/Clanovi.fxml",
                "Clanovi", 600, 400 );
    }
    @FXML
    public void ucitajRezervacije(ActionEvent e) throws IOException{
        Main.showWindow (
                getClass(),
                "../view/PovijestRezervacija.fxml",
                "Povijest rezervacija", 600, 400 );
    }
}
