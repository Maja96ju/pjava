package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class autor extends Table {
    @Entity(type = "INTEGER", size = 11, primary = true)
    int id;

    @Entity(type = "VARCHAR", size = 30, isnull = false)
    String ime;

    @Entity(type = "VARCHAR", size = 30, isnull = false)
    String prezime;

    @Entity(type = "VARCHAR", size = 30, isnull = false)
    String datum_rodjenja;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getDatum_rodjenja() {
        return datum_rodjenja;
    }

    public void setDatum_rodjenja(LocalDate datum_rodjenja) {
        this.datum_rodjenja = datum_rodjenja.toString();
    }

    public static int dohvatiAutora(String ime, String prezime) throws Exception {
        String sql = "SELECT * FROM autor WHERE ime = ? AND prezime = ?";
        PreparedStatement query = Database.CONNECTION.prepareStatement(sql);
        query.setString(1, ime);
        query.setString(2, prezime);
        ResultSet rs = query.executeQuery();

        if(rs.next()){
            return rs.getInt(1);
        } else {
            return 0;
        }
    }
}
