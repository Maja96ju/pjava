package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import main.Main;
import model.knjiga;
import model.korisnik;
import model.rezervacija;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;


public class KorisnickeRezervacije implements Initializable {


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
    @FXML
    TableView<rezervacija> korisnickeRezervacijeTable;
    @FXML
    TableColumn<rezervacija, String> nazivCol;
    @FXML
    TableColumn<rezervacija, String> autorCol;
    @FXML
    TableColumn<rezervacija, String> vrstaCol;
    @FXML
    TableColumn<rezervacija, String> datumOdCol;
    @FXML
    TableColumn<rezervacija, String> datumDoCol;


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

        this.nazivCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().knjiga()));
        this.autorCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().autor()));
        this.vrstaCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().vrsta_knjige()));

        this.datumOdCol.setCellValueFactory(new PropertyValueFactory<>("datum_od"));
        this.datumDoCol.setCellValueFactory(new PropertyValueFactory<>("datum_do"));

        this.populateTableView();
    }

    private void populateTableView() {
        try {
            Collection<rezervacija> rezervacije = (Collection<rezervacija>) rezervacija.list(rezervacija.class);
            rezervacije.removeIf(rez -> rez.getId_korisnika() != Login.loggedInKorisnik.getId());
            this.korisnickeRezervacijeTable.getItems().setAll(rezervacije);
            this.korisnickeRezervacijeTable.setEditable(true);
        } catch (Exception e) {
            System.out.println("Nismo uspjeli dohvatiti podatke" + e.getMessage());
        }
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