package bbdd;

public class DataBaseClaseConnectionTest {
    public static DataBase db;

    public static void main(String[] args) {
        db = new DataBase("admin", "12345", "myfish");
        db.connect();

        String s = db.getInfo("Usuario", "contrasena", "id", "lian");
        System.out.println(s);

        System.out.println(db.getNumFilesTaula("Usuario"));

        String[] noms = db.getInfoArray("Usuario", "id");
        for(int i = 0; i<noms.length;i++){
            System.out.println(noms[i]);
        }
    }
}
