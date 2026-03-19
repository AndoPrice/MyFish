public class DataBaseClaseConnectionTest {
    public static DataBase db;

    public static void main(String[] args) {
        db = new DataBase("admin", "12345", "myfish");
        db.connect();

        String s = db.getInfo("Usuario", "contrasena", "id", "lian");
        System.out.println(s);

        String[] noms = db.getInfoArray("Usuario", "id");
        for(int i = 0; i<noms.length;i++){
            System.out.println(noms[i]);
        }

        int n = db.getNumFilesTaula("Usuario");
        System.out.printf("hay %d usuarios. \n", n);

        String idUsuario = db.getIdUsuarioConContrasena("soysigma");
        System.out.println(idUsuario);

        String[]ids = db.getIdTodosUsuarios();
        db.printArray1D(ids);

        String[][] infoUsuarios = db.getInfoTodosUsuarios();
        db.printArray2D(infoUsuarios);

        String[][] capturasPozo = db.getCapturasPozo();
        db.printArray2D(capturasPozo);


    }
}
