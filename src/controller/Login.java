package controller;

import javafx.scene.control.PasswordField;
import main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.korisnik;

import java.io.IOException;

public class Login {

    public static korisnik loggedInKorisnik;

    @FXML
    Button prijaviSeBtn;

    @FXML
    TextField korisnickoImeTxt;

    @FXML
    PasswordField lozinkaTxt;

    @FXML
    Label greskaLbl;

    @FXML
    Label uspjehLbl;

    @FXML
    public void prijaviSe(ActionEvent ev){
        String korisnickoIme = this.korisnickoImeTxt.getText().toString();
        String lozinka = this.lozinkaTxt.getText().toString();

        if (korisnickoIme.equals("") || lozinka.equals("")){
            greskaLbl.setVisible(true);
            uspjehLbl.setVisible(false);
        } else {
            greskaLbl.setVisible(false);
            uspjehLbl.setVisible(true);


            try {

                Login.loggedInKorisnik = korisnik.login(korisnickoIme, lozinka);
                if (Login.loggedInKorisnik != null){

                } else {
                    greskaLbl.setText("Unesite ispravne korisničke podatke.");
                    greskaLbl.setVisible(true);
                    uspjehLbl.setVisible(false);
                }
                if (Login.loggedInKorisnik.getId_tipa_korisnika()==1) {
                    Main.showWindow(getClass(), "../view/Admin.fxml", "Dobrodošli u administraciju", 600, 400 );
                } else {
                    Main.showWindow(getClass(), "../view/Korisnik.fxml", "Dobrodošli na sustav", 600, 400 );
                }
            } catch (Exception e) {
                System.out.println("Nastala je greska." + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}