package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import main.Main;
import model.autor;
import model.korisnik;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;


public class Clanovi implements Initializable {
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
    Button rezervacije;
    @FXML
    TextField ime;
    @FXML
    TextField prezime;
    @FXML
    TextField korisnicko;
    @FXML
    TextField email;
    @FXML
    TextField lozinka;
    @FXML
    BorderPane border_pane;
    @FXML
    TableView<korisnik> tableView;
    @FXML
    TableColumn<korisnik, Integer> idLbl;
    @FXML
    TableColumn<korisnik, String> imeClanaLbl;
    @FXML
    TableColumn<korisnik, String> prezimeClanaLbl;
    @FXML
    TableColumn<korisnik, String> korImeClanaLbl;
    @FXML
    TableColumn<korisnik, String> emailClanaLbl;


    @FXML
    public void addKorisnikToDatabase (ActionEvent e) throws Exception{
        if(!this.ime.getText().equals("") &&
                !this.prezime.getText().equals("") &&
                !this.korisnicko.getText().equals("") &&
                !this.email.getText().equals("") &&
                !this.lozinka.getText().equals(""))
            {
            korisnik k = new korisnik();
            k.setIme(this.ime.getText());
            k.setPrezime(this.prezime.getText());
            k.setKorisnicko_ime(this.korisnicko.getText());
            k.setEmail(this.email.getText());
            k.setSifra(this.lozinka.getText());
            k.setId_tipa_korisnika(2);

            k.save();
            this.populateTableView();

            this.ime.setText("");
            this.prezime.setText("");
            this.korisnicko.setText("");
            this.email.setText("");
            this.lozinka.setText("");

        }

    }

    @FXML
    public void deleteClanFromDatabase(ActionEvent evt) throws Exception {
        korisnik k = tableView.getSelectionModel().getSelectedItem();
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
        this.imeClanaLbl.setCellValueFactory(new PropertyValueFactory<>("ime"));
        this.prezimeClanaLbl.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        this.korImeClanaLbl.setCellValueFactory(new PropertyValueFactory<>("korisnicko_ime"));
        this.emailClanaLbl.setCellValueFactory(new PropertyValueFactory<>("email"));

        this.imeClanaLbl.setEditable(true);
        /* Evo šta nam je falilo :-) */
        this.imeClanaLbl.setCellFactory(TextFieldTableCell.forTableColumn());

        this.prezimeClanaLbl.setEditable(true);
        /* Evo šta nam je falilo :-) */
        this.prezimeClanaLbl.setCellFactory(TextFieldTableCell.forTableColumn());

        this.korImeClanaLbl.setEditable(true);
        this.korImeClanaLbl.setCellFactory(TextFieldTableCell.forTableColumn());

        this.emailClanaLbl.setEditable(true);
        this.emailClanaLbl.setCellFactory(TextFieldTableCell.forTableColumn());


        this.populateTableView();
    }

    @FXML
    public void editFirstnameClanaToDatabase(TableColumn.CellEditEvent<korisnik, String> evt) throws Exception {
        korisnik k = evt.getRowValue();
        k.setIme(evt.getNewValue());
        k.update();
    }

    @FXML
    public void editLastnameClanaToDatabase(TableColumn.CellEditEvent<korisnik, String> evt) throws Exception {
        korisnik k = evt.getRowValue();
        k.setPrezime(evt.getNewValue());
        k.update();
    }

    @FXML
    public void editKorImeClanaToDatabase(TableColumn.CellEditEvent<korisnik, String> evt) throws Exception {
        korisnik k = evt.getRowValue();
        k.setKorisnicko_ime(evt.getNewValue());
        k.update();
    }

    @FXML
    public void editEmailClanaToDatabase(TableColumn.CellEditEvent<korisnik, String> evt) throws Exception {
        korisnik k = evt.getRowValue();
        k.setEmail(evt.getNewValue());
        k.update();
    }

    private void populateTableView() {
        try {
            this.tableView.getItems().setAll((Collection<? extends korisnik>) korisnik.list(korisnik.class));
            this.tableView.setEditable(true);
        } catch (Exception e) {
            System.out.println("Nismo uspjeli dohvatiti podatke" + e.getMessage());
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