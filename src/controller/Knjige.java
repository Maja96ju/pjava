package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;


public class Knjige implements Initializable {
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
    ComboBox<String> autorList;
    @FXML
    TableView<knjiga> tableView;
    @FXML
    TableColumn<knjiga, Integer> idLbl;
    @FXML
    TableColumn<knjiga, String> nazivLbl;
    @FXML
    TableColumn<knjiga, String> autorLbl;
    @FXML
    TableColumn<knjiga, String> vrstaLbl;
    @FXML
    TableColumn<knjiga, Integer> statusLbl;
    @FXML
    TextField IdAutora;
    @FXML
    TextField nazivKnjigeTxt;
    @FXML
    TextField vrstaKnjigeTxt;

    ObservableList<String> comboItems = FXCollections.observableArrayList();

    int idAutora = 0;


    @FXML
    public void deleteKnjigaFromDatabase(ActionEvent evt) throws Exception {
        knjiga k = tableView.getSelectionModel().getSelectedItem();
        k.delete();
        this.populateTableView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.LoggedUserlbl.setText(
                Login.loggedInKorisnik.getIme() +
                        " " +
                        Login.loggedInKorisnik.getPrezime()
        );

        this.idLbl.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nazivLbl.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        this.autorLbl.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().autor_ime()));
        this.vrstaLbl.setCellValueFactory(new PropertyValueFactory<>("vrsta"));
        this.statusLbl.setCellValueFactory(new PropertyValueFactory<>("status"));

        this.nazivLbl.setEditable(true);
        /* Evo šta nam je falilo :-) */
        this.nazivLbl.setCellFactory(TextFieldTableCell.forTableColumn());

        this.vrstaLbl.setEditable(true);
        /* Evo šta nam je falilo :-) */
        this.vrstaLbl.setCellFactory(TextFieldTableCell.forTableColumn());

        //this.statusLbl.setEditable(true);
        //this.statusLbl.setCellFactory(TextFieldTableCell.forTableColumn());


        this.populateTableView();
        Collection<autor> autori;
        try {
            autori = (Collection<autor>) autor.list(autor.class);
            autori.forEach(autor -> {
                comboItems.add(autor.getIme() + " " + autor.getPrezime());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        autorList.setItems(comboItems);

    }



    @FXML
    public void editNazivKnjigeToDatabase(TableColumn.CellEditEvent<knjiga, String> evt) throws Exception {
        knjiga k = evt.getRowValue();
        k.setNaziv(evt.getNewValue());
        k.update();
    }

    @FXML
    public void editVrstaKnijgeToDatabase(TableColumn.CellEditEvent<knjiga, String> evt) throws Exception {
        knjiga k = evt.getRowValue();
        k.setVrsta(evt.getNewValue());
        k.update();
    }


    private void populateTableView() {
        try {
            this.tableView.getItems().setAll((Collection<? extends knjiga>) knjiga.list(knjiga.class));
            this.tableView.setEditable(true);
        } catch (Exception e) {
            System.out.println("Nismo uspjeli dohvatiti podatke" + e.getMessage());
        }
    }

    public void dohvatiId() throws Exception {
        String[] odabraniAutor = autorList.getSelectionModel().getSelectedItem().split(" ");
        this.idAutora = autor.dohvatiAutora(odabraniAutor[0], odabraniAutor[1]);
    }


    public void spremiKnjigu() throws Exception{
        if(this.idAutora != 0 && this.nazivKnjigeTxt.getText() != "" && this.vrstaKnjigeTxt.getText() != ""){
            knjiga novaKnjiga = new knjiga();
            novaKnjiga.setNaziv(this.nazivKnjigeTxt.getText());
            novaKnjiga.setVrsta(this.vrstaKnjigeTxt.getText());
            novaKnjiga.setId_autora(this.idAutora);
            novaKnjiga.setStatus(10);
            novaKnjiga.save();

            // resetiramo vrijednosti nakon spremanja u bazu
            this.nazivKnjigeTxt.setText("");
            this.vrstaKnjigeTxt.setText("");
            this.autorList.setValue("Odaberite autora");
            this.idAutora = 0;
        } else {
            System.out.println("Popunite sva polja za unos!");
        }
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