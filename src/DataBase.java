import java.sql.*;

public class DataBase {
    // Variable de connexió a la BBDD
    Connection c;

    // Variable de consulta
    Statement query;

    // Dades de connexió (user, password, nom de la base de dades)
    String user, password, databaseName;

    // Estat de la connexió
    boolean connectat = false;

    public DataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public void connect(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:8889/"+databaseName, user, password);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD! :) ");
            connectat = true;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    // Retorna la informació d'una casella

    public String getInfo(String nomTaula, String nomColumna, String nomClau, String identificador){
        try{
            String q =  "SELECT " + nomColumna +
                    " FROM " + nomTaula +
                    " WHERE "+ nomClau  + " = '" + identificador + "' ";
            System.out.println(q);
            ResultSet rs= query.executeQuery(q);
            rs.next();
            return rs.getString(nomColumna);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return "";
    }

    // Retorna el número total de files d'una taula

    public int getNumFilesTaula(String nomTaula){
        String q = "SELECT COUNT(*) AS num FROM "+ nomTaula;
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // Retorna totes les caselles d'una columna

    public String[] getInfoArray(String nomTaula, String nomColumna){
        int n = getNumFilesTaula(nomTaula);
        String[] info = new String[n];
        String q = "SELECT "+ nomColumna +
                " FROM " + nomTaula +
                " ORDER BY " + nomColumna + " ASC";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int f=0;
            while(rs.next()){
                info[f] = rs.getString(nomColumna);
                f++;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return info;
    }

    // Retorna totes les caselles (files i columnes) d'una taula

    public String[][] getInfoArray2DUnitat(){
        int nf = getNumFilesTaula("unitat");
        String[][] info = new String[nf][3];
        String q = "SELECT numero, nom, curs FROM unitat ORDER BY numero ASC";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int f=0;
            while(rs.next()){
                info[f][0] = String.valueOf( rs.getInt("numero"));
                info[f][1] = rs.getString("nom");
                info[f][2] = String.valueOf( rs.getInt("curs"));
                f++;
            }
            return info;
        }
        catch(Exception e){
            System.out.println(e);
        }

        return info;
    }

    // Retorna el número total de files d'una taula

    public int getNumFilesMatchQuery(String q){
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("n");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // Retorna dades de dues taules relacionades






    // Retorna el número de files d'una taula
    public int getNumRowsTaula(String nomTaula){
        try {
            ResultSet rs = query.executeQuery( "SELECT COUNT(*) AS n FROM "+ nomTaula );
            rs.next();
            int numRows = rs.getInt("n");
            return numRows;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    // Retorna el número de files que retornaria una query SELECT qualsevol amb valor "n"
    // Per exemple: SELECT COUNT(*) AS n FROM ...
    public int getNumRowsQuery(String q){
        try {
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getInt('n');
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public void printArray1D(String[] info){
        System.out.println();
        for(int i = 0; i<info.length; i++){
            System.out.printf("%d:", i);
            System.out.printf("%s. \t", info[i]);
            System.out.println();
        }
    }

    public void printArray2D(String[][] info){
        System.out.println();
        for(int i = 0; i<info.length; i++){
            System.out.printf("%d:", i);
            for(int j = 0; j<info[i].length; j++) {
                System.out.printf("%s. \t", info[i][j]);
            }
            System.out.println();
        }
    }

    public String getIdUsuarioConContrasena(String contrasena){
        String q = "SELECT id FROM Usuario WHERE contrasena='"+contrasena+"'";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            String nom = rs.getString("id");
            return nom;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public String[] getIdTodosUsuarios(){
        String q = "SELECT id FROM Usuario ORDER BY id ASC";
        System.out.println(q);
        try{
            int numFilas = getNumFilesTaula("Usuario");
            String[] info = new String[numFilas];
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while(rs.next()){
                info[f] = rs.getString("id");
                f++;
            }
            return info;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public String[][] getInfoTodosUsuarios(){
        String q = "SELECT contrasena, id FROM Usuario ORDER BY id ASC";
        System.out.println(q);
        try{
            int numFilas = getNumFilesTaula("Usuario");
            String[][] info = new String[numFilas][2];
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while(rs.next()){
                info[f][0] = rs.getString("id");
                info[f][1] = rs.getString("contrasena");
                f++;
            }
            return info;

        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public String[][] getInfoTodasEspecies(){
        String q = "SELECT nombreComun, nombreCientifico, descripcion, ubicacion, masInfo, comportamiento, tallaMin " +
                "FROM Especie " +
                "ORDER BY nombreComun ASC";
        System.out.println(q);
        try{
            int numFilas = getNumFilesTaula("Especie");
            String[][] info = new String[numFilas][7];
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while(rs.next()){
                info[f][0] = rs.getString("nombreComun");
                info[f][1] = rs.getString("nombreCientifico");
                info[f][2] = rs.getString("descripcion");
                info[f][3] = rs.getString("ubicacion");
                info[f][4] = rs.getString("masInfo");
                info[f][5] = rs.getString("comportamiento");
                info[f][6] = rs.getString("tallaMin");

                f++;
            }
            return info;

        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    /*public String[][] getInfoTodasCapturas(){
        String q = "SELECT c.peso, c.tamano, c.ubicacion, c.fecha, c.senuelo, c.notas, e.nombreComun " +
                "FROM Captura c, Especie e " +
                "WHERE " +
                "ORDER BY nombreComun ASC";
        System.out.println(q);
        try{
            int numFilas = getNumFilesTaula("Captura");
            String[][] info = new String[numFilas][7];
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while(rs.next()){
                info[f][0] = rs.getString("nombreComun");
                info[f][1] = rs.getString("nombreCientifico");
                info[f][2] = rs.getString("descripcion");
                info[f][3] = rs.getString("ubicacion");
                info[f][4] = rs.getString("masInfo");
                info[f][5] = rs.getString("comportamiento");
                info[f][6] = rs.getString("tallaMin");

                f++;
            }
            return info;

        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }*/

    public String[][] getCapturasUsuario(String usuario){
        String qf = "SELECT COUNT(*) AS n " +
                "FROM Captura c, Usuario u, Especie e " +
                "WHERE c.Usuario_id=u.id AND u.id='"+usuario+"' AND c.Especie_numero=e.numero "+
                "ORDER BY c.fecha ASC";
        System.out.println(qf);

        int nf = getNumFilesMatchQuery(qf);
        String[][] info = new String[nf][8];


        String q = "SELECT c.numero, c.fecha, e.nombreComun, c.peso, c.tamano, c.ubicacion, c.senuelo, c.notas \n" +
                "FROM Captura c, Usuario u, Especie e \n" +
                "WHERE c.Usuario_id=u.id AND u.id='"+usuario+"' AND c.Especie_numero=e.numero \n" +
                "ORDER BY c.fecha DESC";


        System.out.println(q);

        try{
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while(rs.next()){
                info[f][0] = rs.getString("nombreComun");
                info[f][1] = rs.getString("peso");
                info[f][2] = rs.getString("tamano");
                info[f][3] = rs.getString("ubicacion");
                info[f][4] = rs.getString("fecha");
                info[f][5] = rs.getString("senuelo");
                info[f][6] = rs.getString("notas");
                info[f][7] = rs.getString("numero");


                f++;

            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return info;

    }

    public boolean loginCorrecte (String nom, String password){
        String q = "SELECT COUNT(*) AS N " +
                "FROM Usuario " +
                "WHERE id = '" +nom+ "' AND contrasena = '"+password+"'";
        System.out.println(q);

        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            int n = rs.getInt("N");
            return (n==1);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public String getIdEspecie(String nombre){
        String q = "SELECT numero FROM Especie WHERE nombreComun ='"+nombre+"'";
        try{
            ResultSet rs =query.executeQuery(q);
            rs.next();
            return String.valueOf(rs.getInt("numero"));
        }
        catch(Exception e){}
        return null;
    }

    public void insertCaptura(float peso, float tamano, String ubicacion, int dia, int mes, int ano, String senuelo, String notas, String especie, String usuario){

        String idEspecie = getIdEspecie(especie);

        String q = "INSERT INTO Captura (`peso`, `tamano`, `ubicacion`, `fecha`, `senuelo`, `notas`, `Especie_numero`, `Usuario_id`) " +
                "VALUES ('"+peso+"','"+tamano+"','"+ubicacion+"','"+ano+"-"+mes+"-"+dia+"','"+senuelo+"','"+notas+"','"+idEspecie+"', '"+usuario+"')";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void updateCaptura(int id, float peso, float tamano, String ubicacion, int dia, int mes, int ano, String senuelo, String notas, String especie) {
        String idEspecie = getIdEspecie(especie);
        String q = "UPDATE Captura SET peso=" + peso + ", tamano=" + tamano + ", ubicacion='" + ubicacion + "', fecha='" + ano + "-" + mes + "-" + dia + "', senuelo='" + senuelo + "', notas='" + notas + "', Especie_numero=" + idEspecie + " WHERE numero=" + id;
        System.out.println(q);
        try {
            query.execute(q);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteCaptura(int id) {
        String q = "DELETE FROM Captura WHERE numero=" + id;
        System.out.println(q);
        try {
            query.execute(q);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertImagen(String nombre){
        int n = getMaxCaptura();
        String q = "INSERT INTO `Imagen`(`nombre`, `Especie_numero`, `Captura_numero`) " +
                "VALUES ('"+nombre+"', NULL, "+n+")";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public String getImagenPorCapturaId(int capturaId) {
        String q = "SELECT nombre FROM Imagen WHERE Captura_numero=" + capturaId;
        try {
            ResultSet rs = query.executeQuery(q);
            if (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateImagenCaptura(int capturaId, String nombreImagen) {
        String testQ = "SELECT COUNT(*) FROM Imagen WHERE Captura_numero=" + capturaId;
        try {
            ResultSet rs = query.executeQuery(testQ);
            rs.next();
            if (rs.getInt(1) > 0) {
                String updateQ = "UPDATE Imagen SET nombre='" + nombreImagen + "' WHERE Captura_numero=" + capturaId;
                System.out.println(updateQ);
                query.execute(updateQ);
            } else {
                String insertQ = "INSERT INTO `Imagen`(`nombre`, `Especie_numero`, `Captura_numero`) VALUES ('" + nombreImagen + "', NULL, " + capturaId + ")";
                System.out.println(insertQ);
                query.execute(insertQ);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public int getMaxCaptura(){
        String q = "SELECT MAX(numero) FROM Captura";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }


}
