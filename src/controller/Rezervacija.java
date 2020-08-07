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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import main.Main;
import model.autor;
import model.knjiga;
import model.korisnik;
import model.rezervacija;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;


public class Rezervacija implements Initializable {


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
    TableView<knjiga> tableView;
    @FXML
    TableColumn<knjiga, String> nazivLbl;
    @FXML
    TableColumn<knjiga, String> autorLbl;
    @FXML
    TableColumn<knjiga, String> vrstaLbl;
    @FXML
    DatePicker datumOd;
    @FXML
    DatePicker datumDo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.LoggedUserlbl.setText(
                Login.loggedInKorisnik.getIme() +
                        " " +
                        Login.loggedInKorisnik.getPrezime()
        );

        this.nazivLbl.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        this.autorLbl.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().autor_ime()));
        this.vrstaLbl.setCellValueFactory(new PropertyValueFactory<>("vrsta"));

        this.populateTableView();
    }

    private void populateTableView() {
        try {
            this.tableView.getItems().setAll((Collection<? extends knjiga>) knjiga.list(knjiga.class));
            this.tableView.setEditable(true);
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


    public void rezervirajKnjigu() throws Exception{
        knjiga odabranaKnjiga = tableView.getSelectionModel().getSelectedItem();
        if(odabranaKnjiga != null){
            rezervacija novaRezervacija = new rezervacija();
            novaRezervacija.setDatum_od(this.datumOd.getValue().toString());
            novaRezervacija.setDatum_do(this.datumDo.getValue().toString());
            novaRezervacija.setId_korisnika(Login.loggedInKorisnik.getId());
            novaRezervacija.setId_knjige(odabranaKnjiga.getId());
            novaRezervacija.save();

            this.datumOd.setValue(null);
            this.datumDo.setValue(null);
        }
    }
}