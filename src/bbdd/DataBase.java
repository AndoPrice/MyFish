package bbdd;

import jdk.jshell.spi.ExecutionControlProvider;

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
            return rs.getInt("num");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // Retorna les dades (enunaciat, opcions i correcte) de totes les preguntes de dificultat 2
    public String[][] getInfoPreguntaDificil(){
        String qNF = "SELECT COUNT(*) AS num FROM pregunta WHERE dificultat='2' ";
        int nf = getNumFilesMatchQuery(qNF);
        String[][] info = new String[nf][5];
        String q = " SELECT enunciat, opcioA, opcioB, opcioC, correcte " +
                " FROM pregunta " +
                " WHERE dificultat = '2' ";
        System.out.println(q);
        try {
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while(rs.next()){
                info[n][0] = rs.getString("enunciat");
                info[n][1] = rs.getString("opcioA");
                info[n][2] = rs.getString("opcioB");
                info[n][3] = rs.getString("opcioC");
                info[n][4] = rs.getString("correcte");
                n++;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return info;
    }

    // Retorna dades de dues taules relacionades
    public String[][] getInfoPreguntesUnitats(){
        String qNF = " SELECT COUNT(*) AS num " +
                " FROM pregunta p, unitat u " +
                " WHERE p.unitat = u.numero ";
        int nf = getNumFilesMatchQuery(qNF);
        String[][] info = new String[nf][2];

        String q = " SELECT p.enunciat, u.nom " +
                " FROM pregunta p, unitat u " +
                " WHERE p.unitat = u.numero ";

        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int n=0;
            while(rs.next()){
                info[n][0] = rs.getString("p.enunciat");
                info[n][1] = rs.getString("u.nom");
                n++;
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return info;
    }


    // Retorna el càlcul (MAX) sobre una columna numèrica (punts) d'una taula ( puntuacio).

    public int getMaxPuntuacioUsuari(String nomUsuari){
        String q = " SELECT MAX(punts) AS maxima " +
                " FROM puntuacio " +
                " WHERE persona = '" + nomUsuari + "' ";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("maxima");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // Retorna true si el nom d'usuari i password estan a la taula (usuario)
    public boolean isUserOk(String nom, String password){
        String q = "SELECT COUNT(*) AS n" +
                " FROM usuario "+
                " WHERE nombre='" + nom + "' AND password='" + password + "' ";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("n")==1;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    // Inserta un usuari a la taula usuario amb nombre i password
    // INSERT INTO `usuario` (`nombre`, `password`) VALUES ('bel', 'qwerty');
    public void insertaUsuario(String n, String p){
        String q = "INSERT INTO usuario (nombre, password) " +
                "VALUES ('"+n+"', '"+p+"')";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    // Esborra un usuari de la taula usuario
    // DELETE FROM `usuario` WHERE `usuario`.`nombre` = \'pep\'"
    public void deleteUsuario(String nom){
        String q = "DELETE FROM usuario WHERE nombre ='" + nom + "'";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    // UPDATE `usuario` SET `nombre` = 'paquito', `password` = 'abcdefghi' WHERE `usuario`.`nombre` = 'paco';
    // Modifica les dades d'un usuari
    public void updateUsuario(String nomActual, String nouNom, String nouPassword){
        String q = "UPDATE usuario SET " +
                " nombre = '"+nouNom+"' , "+
                " password = '"+nouPassword+ "' " +
                " WHERE nombre='"+nomActual+"'";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    // Cercador de Preguntes
    // SELECT * FROM pregunta WHERE enunciat LIKE '%Quin%'
    public String[][] preguntesCercador(String clauCerca){

        String qNF = "SELECT COUNT(*) AS num FROM pregunta WHERE enunciat LIKE '%"+ clauCerca+"%'";
        int nf = getNumFilesMatchQuery(qNF);
        String[][] info = new String[nf][2];
        String q = "SELECT numero, enunciat FROM pregunta WHERE enunciat LIKE '%"+ clauCerca+"%'";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int n=0;
            while(rs.next()){
                info[n][0] = String.valueOf(rs.getInt("numero"));
                info[n][1] = rs.getString("enunciat");
                n++;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return info;
    }





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
            return rs.getInt("n");
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    // Retorna el número de columnes d'una taula de la base de dades
    public int getNumColsTaula(String nomTaula){
        try {
            String q = "SELECT count(*) as n FROM information_schema.columns WHERE table_name ='"+ nomTaula +"' AND table_schema='"+databaseName+"'";
            System.out.println(q);
            ResultSet rs = query.executeQuery( q);
            rs.next();
            int numCols = rs.getInt("n");
            return numCols;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }



    // Retorna totes les dades d'una taula en concret
    public String[][] getInfoTaulaUnitat(){
        int numFiles = getNumRowsTaula("unitat");
        int numCols  = 2;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT * FROM unitat");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("numero"));
                info[nr][1] = rs.getString("nom");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Retorna les dades bidimensionals d'una query en concret (Dades de les unitats d'un curs en concret).
    public String[][] getInfoTaulaUnitatCurs(int curs){
        int numFiles = getNumRowsQuery("SELECT COUNT(*) AS n FROM unitat WHERE curs = '"+curs+"'");
        int numCols  = 3;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT numero, nom, curs FROM unitat WHERE curs= '"+curs+"'");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("numero"));
                info[nr][1] = rs.getString("nom");
                info[nr][2] = String.valueOf(rs.getInt("curs"));
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Retorna les dades unidimensionals d'una query en concret (Cursos diferents)
    public String[] getInfoColumnaCursTaulaUnitat(){
        int numFiles = getNumRowsQuery("SELECT COUNT(DISTINCT curs) AS n FROM unitat");
        String[] info = new String[numFiles];
        try {
            ResultSet rs = query.executeQuery( "SELECT DISTINCT(curs) AS curs FROM unitat");
            int nr = 0;
            while (rs.next()) {
                info[nr] = rs.getString("curs");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Retorna les dades de la columna NOM de la taula UNITAT
    public String[] getColumnaNomTaulaUnitat(){
        int numFiles = getNumRowsTaula("unitat");
        String[] info = new String[numFiles];
        try {
            ResultSet rs = query.executeQuery( "SELECT nom FROM unitat ORDER BY nom ASC");
            int nr = 0;
            while (rs.next()) {
                info[nr] = rs.getString("nom");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Retorna el valor de la Columna NUMERO de la taula UNITAT per aquella fila amb NOM
    public String getNumeroFromTaulaUnitat(String nom)  {
        try {
            ResultSet rs = query.executeQuery( "SELECT numero FROM unitat WHERE nom = '"+nom+"'");
            rs.next();
            return String.valueOf(rs.getInt("numero"));
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public boolean isValidUser(String userName, String password){
        String q = "SELECT COUNT(*) AS n FROM usuario WHERE nom = '"+userName+"' AND password='"+password+"'";
        try {
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getInt("n")==1;
        }
        catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String getClaveFromTabla(String nombreTable, String nombreClave, String nombreColumna, String valorColumna){
        try {
            String q = "SELECT "+nombreClave+" AS clave FROM "+nombreTable+" WHERE "+nombreColumna+"='"+valorColumna+"'";
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getString("clave");
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }


    // INSERTS

    // Inserta les dades a la taula Unitat

    void insertInfoTaulaUnitat(String num, String nom){
        try {
            String sNom = nom.replace("\'", "\\'");
            String q = "INSERT INTO unitat (numero, nom) VALUES ('" + num + "','" + sNom + "')";
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }


    // UPDATES

    // Actualitza les dades a la taula Unitat

    void updateInfoTaulaUnitat(String id, String num, String nom){
        try {
            String q = "UPDATE unitat SET numero='"+num+"', nom='"+nom+"' WHERE numero='"+id+"'";
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    // DELETES

    // Esborra la fila de la taula Unitat amb el número concret
    void deleteInfoTaulaUnitat(String id){
        try {
            String q = "DELETE FROM unitat WHERE numero='"+id+"'";
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
