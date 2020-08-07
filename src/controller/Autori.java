package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main.Main;
import model.autor;
import model.korisnik;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;


public class Autori implements Initializable {


        @FXML
        AnchorPane urediAutora;

        @FXML
        Label LoggedUserlbl;
        @FXML
        Button autori;
        @FXML
        Button knjige;
        @FXML
        Button clanovi;
        @FXML
        Button odjava;
        @FXML
        TextField ime;
        @FXML
        TextField prezime;
        @FXML
        DatePicker datumRodjenja;
        @FXML
        BorderPane border_pane;
        @FXML
        TableView<autor> tableView;
        @FXML
        TableColumn<autor, Integer> idLbl;
        @FXML
        TableColumn<autor, String> imeAutoraLbl;
        @FXML
        TableColumn<autor, String> prezimeAutoraLbl;
        @FXML
        TableColumn<autor, String> datumRodjenjaLbl;


        @FXML
        public void addAutorToDatabase (ActionEvent e) throws Exception{
            if(!this.ime.getText().equals("") &&
                    !this.prezime.getText().equals("") && !this.datumRodjenja.getValue().equals(""))
                {
                autor a = new autor();
                a.setIme(this.ime.getText());
                a.setPrezime(this.prezime.getText());
                a.setDatum_rodjenja(this.datumRodjenja.getValue());


                a.save();
                this.populateTableView();

                this.ime.setText("");
                this.prezime.setText("");
                //this.datumRodjenja.setValue();
            }

        }

        @FXML
        public void deleteAutorFromDatabase(ActionEvent evt) throws Exception {
            autor a = tableView.getSelectionModel().getSelectedItem();
            a.delete();
            this.populateTableView();
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                this.LoggedUserlbl.setText(
                        Login.loggedInKorisnik.getIme() +
                                " " +
                                Login.loggedInKorisnik.getPrezime()
                );
                this.idLbl.setCellValueFactory(new PropertyValueFactory<>("id"));
                this.imeAutoraLbl.setCellValueFactory(new PropertyValueFactory<>("ime"));
                this.prezimeAutoraLbl.setCellValueFactory(new PropertyValueFactory<>("prezime"));
                this.datumRodjenjaLbl.setCellValueFactory(new PropertyValueFactory<>("datum_rodjenja"));

                this.imeAutoraLbl.setEditable(true);
                /* Evo šta nam je falilo :-) */
                this.imeAutoraLbl.setCellFactory(TextFieldTableCell.forTableColumn());

                this.prezimeAutoraLbl.setEditable(true);

                /* Evo šta nam je falilo :-) */
                this.prezimeAutoraLbl.setCellFactory(TextFieldTableCell.forTableColumn());

                this.populateTableView();

        }

        @FXML
        public void editFristnameAutorToDatabase(TableColumn.CellEditEvent<autor, String> evt) throws Exception {
                autor a = evt.getRowValue();
                a.setIme(evt.getNewValue());
                a.update();
        }

        @FXML
        public void editLastnameAutorToDatabase(TableColumn.CellEditEvent<autor, String> evt) throws Exception {
                autor a = evt.getRowValue();
                a.setPrezime(evt.getNewValue());
                a.update();
        }


        private void populateTableView() {
                try {
                    this.tableView.getItems().setAll((Collection<? extends autor>) autor.list(autor.class));
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

        public void ucitajAutore (ActionEvent e) throws IOException {
                    Main.showWindow(
                            getClass(),
                            "../view/Autori.fxml",
                            "Autori", 600, 400
                    );
        }

        public void ucitajKnjige (ActionEvent e) throws IOException {
                Main.showWindow(
                        getClass(),
                        "../view/Knjige.fxml",
                        "Knjige", 600, 400
                );
        }

        public void ucitajClanove (ActionEvent e) throws IOException {
                Main.showWindow(
                        getClass(),
                        "../view/Clanovi.fxml",
                        "Članovi", 600, 400
                );
        }

        public void ucitajRezervacije (ActionEvent e) throws IOException {
                Main.showWindow(
                        getClass(),
                        "../view/PovijestRezervacija.fxml",
                        "Povijet rezervacija", 600, 400
                );
        }

}