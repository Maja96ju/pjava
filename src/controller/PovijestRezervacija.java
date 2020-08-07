package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import main.Main;
import model.rezervacija;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.ResourceBundle;


public class PovijestRezervacija implements Initializable {
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

    @FXML
    TableView<model.rezervacija> tableView;
    @FXML
    TableColumn<rezervacija, Integer> idLbl;
    @FXML
    TableColumn<model.rezervacija, String> korImeLbl;
    @FXML
    TableColumn<rezervacija, String> nazivLbl;
    @FXML
    TableColumn<rezervacija, String> autorLbl;
    @FXML
    TableColumn<rezervacija, LocalDate> datum_odLbl;
    @FXML
    TableColumn<rezervacija, LocalDate> datum_doLbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.LoggedUserlbl.setText(
                Login.loggedInKorisnik.getIme() +
                        " " +
                        Login.loggedInKorisnik.getPrezime()
        );

        this.idLbl.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.korImeLbl.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().korisnik()));
        this.nazivLbl.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().knjiga()));
        this.datum_odLbl.setCellValueFactory(new PropertyValueFactory<>("datum_od"));
        this.datum_doLbl.setCellValueFactory(new PropertyValueFactory<>("datum_do"));

        this.populateTableView();
    }

    private void populateTableView() {
        try {
            this.tableView.getItems().setAll((Collection<? extends rezervacija>) model.rezervacija.list(rezervacija.class));

            this.tableView.setEditable(true);
        } catch (Exception e) {
            System.out.println("Nismo uspjeli dohvatiti podatke" + e.getMessage());
        }
    }

    @FXML
    public void logout (ActionEvent ev) throws Exception{
        Login.loggedInKorisnik = null;
        Main.showWindow(
                getClass(),
                "../view/Login.fxml",
                "Prijavite se na sustav", 600, 300
        );
    }
    @FXML
    public void ucitajAutore(ActionEvent e) throws IOException {
        Main.showWindow(
                getClass(),
                "../view/Autori.fxml",
                "Autori", 600, 400 );
        border_pane.setCenter(autori);
    }
    @FXML
    public void ucitajKnjige(ActionEvent e) throws IOException {
        Main.showWindow(
                getClass(),
                "../view/Knjige.fxml",
                "Knjige", 600, 400 );
    }

    @FXML
    public void ucitajClanove(ActionEvent e) throws IOException {
        Main.showWindow(
                getClass(),
                "../view/Clanovi.fxml",
                "Clanovi", 600, 400 );
    }
    @FXML
    public void ucitajRezervacije(ActionEvent e) throws IOException {
        Main.showWindow(
                getClass(),
                "../view/PovijestRezervacija.fxml",
                "Povijest rezervacija", 600, 400 );
    }
}