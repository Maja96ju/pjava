package model;

public class tip_korisnika extends Table {
    @Entity(type="INTEGER", size = 11, primary = true)
    int id;

    @Entity(type = "VARCHAR", size = 15, isnull = false)
    String ime_uloge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme_uloge() {
        return ime_uloge;
    }

    public void setIme_uloge(String ime_uloge) {
        this.ime_uloge = ime_uloge;
    }
}
