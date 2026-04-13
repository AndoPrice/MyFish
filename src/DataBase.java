import java.sql.*;

/**
 * Clase encargada de la gestión de la base de datos MySQL.
 *
 * Proporciona métodos para:
 * - Conectar con la base de datos
 * - Ejecutar consultas
 * - Insertar, actualizar y eliminar datos
 * - Obtener información de usuarios, capturas y especies
 */
public class DataBase {
    /** Conexión a la base de datos */
    Connection c;

    /** Objeto para ejecutar consultas SQL */
    Statement query;

    /** Credenciales de acceso */
    String user, password, databaseName;

    /** Indica si está conectado */
    boolean connectat = false;

    /**
     * Constructor de la base de datos.
     * @param user usuario
     * @param password contraseña
     * @param databaseName nombre de la base de datos
     */
    public DataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    /**
     * Establece conexión con la base de datos MySQL.
     */
    public void connect(){
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:8889/"+databaseName, user, password);
            query = c.createStatement();
            System.out.println("Conectado a la BBDD! :) ");
            connectat = true;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Devuelve el número de filas de una tabla.
     * @param nomTaula nombre de la tabla
     * @return número de filas
     */
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


    /**
     * Obtiene el número de resultados de una query personalizada.
     * @param q consulta SQL
     * @return número de resultados
     */
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

    /**
     * Imprime un array unidimensional.
     */
    public void printArray1D(String[] info){
        System.out.println();
        for(int i = 0; i<info.length; i++){
            System.out.printf("%d:", i);
            System.out.printf("%s. \t", info[i]);
            System.out.println();
        }
    }

    /**
     * Imprime un array bidimensional.
     */
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


    /**
     * Obtiene el ID de un usuario a partir de su contraseña.
     *
     * @param contrasena contraseña del usuario
     * @return ID del usuario o null si no se encuentra
     */
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

    /**
     * Obtiene los IDs de todos los usuarios.
     *
     * - Ordenados alfabéticamente
     * - Devuelve un array con todos los IDs
     *
     * @return array de IDs de usuarios o null en caso de error
     */
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

    /**
     * Obtiene información de todos los usuarios.
     *
     * Devuelve un array bidimensional donde:
     * [i][0] → ID del usuario
     * [i][1] → contraseña
     *
     * @return matriz con información de usuarios o null si ocurre un error
     */
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

    /**
     * Obtiene información de todas las especies.
     *
     * Funcionamiento:
     * - Realiza un LEFT JOIN entre:
     *   Especie e Imagen
     * - Solo obtiene imágenes asociadas a especies (no capturas)
     * - Ordena por nombre común
     *
     * Devuelve un array donde cada fila contiene:
     * [0] nombre común
     * [1] nombre científico
     * [2] descripción
     * [3] ubicación
     * [4] información adicional
     * [5] comportamiento
     * [6] talla mínima
     * [7] nombre de la imagen
     *
     * @return matriz con información de especies
     */
    public String[][] getInfoTodasEspecies(){
        String q = "SELECT e.nombreComun, e.nombreCientifico, e.descripcion, e.ubicacion, e.masInfo, e.comportamiento, e.tallaMin, i.nombre " +
                "FROM Especie e " +
                "LEFT JOIN Imagen i ON e.numero = i.Especie_numero AND i.Captura_numero IS NULL " +
                "ORDER BY e.nombreComun ASC";
        System.out.println(q);
        try{
            int numFilas = getNumFilesTaula("Especie");
            String[][] info = new String[numFilas][8];
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
                info[f][7] = rs.getString("nombre");

                f++;
            }
            return info;

        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    /**
     * Obtiene todas las capturas de un usuario.
     *
     * Funcionamiento:
     * 1. Ejecuta una query para contar el número de capturas del usuario
     * 2. Crea un array bidimensional con ese tamaño
     * 3. Ejecuta una segunda query con JOINs entre:
     *    - Captura
     *    - Usuario
     *    - Especie
     *    - Imagen (opcional)
     * 4. Recorre el ResultSet y rellena el array con:
     *    [0] nombre de la especie
     *    [1] peso
     *    [2] tamaño
     *    [3] ubicación
     *    [4] fecha
     *    [5] señuelo
     *    [6] notas
     *    [7] id de captura
     *    [8] nombre de la imagen
     *
     * @param usuario ID del usuario
     * @return matriz con la información de todas las capturas
     */
    public String[][] getCapturasUsuario(String usuario){
        String qf = "SELECT COUNT(*) AS n " +
                "FROM Captura c, Usuario u, Especie e " +
                "WHERE c.Usuario_id=u.id AND u.id='"+usuario+"' AND c.Especie_numero=e.numero "+
                "ORDER BY c.fecha ASC";
        System.out.println(qf);

        int nf = getNumFilesMatchQuery(qf);
        String[][] info = new String[nf][9];


        String q = "SELECT c.numero, c.fecha, e.nombreComun, c.peso, c.tamano, c.ubicacion, c.senuelo, c.notas, i.nombre AS nombreImagen \n" +
                "FROM Captura c \n" +
                "JOIN Usuario u ON c.Usuario_id=u.id \n" +
                "JOIN Especie e ON c.Especie_numero=e.numero \n" +
                "LEFT JOIN Imagen i ON c.numero=i.Captura_numero \n" +
                "WHERE u.id='"+usuario+"' \n" +
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
                info[f][8] = rs.getString("nombreImagen");


                f++;

            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return info;

    }

    /**
     * Verifica si el login es correcto.
     * @param nom usuario
     * @param password contraseña
     * @return true si es válido
     */
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

    /**
     * Obtiene el ID (numero) de una especie a partir de su nombre común.
     *
     * Realiza una consulta SQL sobre la tabla Especie filtrando por nombreComun.
     *
     * @param nombre nombre común de la especie
     * @return ID de la especie en formato String o null si no se encuentra
     */
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

    /**
     * Inserta una nueva captura en la base de datos.
     *
     * Construye una query SQL con todos los datos de la captura.
     */
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

    /**
     * Actualiza una captura existente.
     */
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

    /**
     * Elimina una captura y su imagen asociada.
     * @param id id de la captura
     */
    public void deleteCaptura(int id) {
        try {
            String qImg = "DELETE FROM Imagen WHERE Captura_numero=" + id;
            System.out.println(qImg);
            query.execute(qImg);

            String q = "DELETE FROM Captura WHERE numero=" + id;
            System.out.println(q);
            query.execute(q);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Inserta una imagen asociada a la última captura.
     */
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

    /**
     * Obtiene el nombre de la imagen asociada a una captura.
     */
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

    /**
     * Inserta o actualiza la imagen de una captura.
     *
     * Si ya existe → UPDATE
     * Si no existe → INSERT
     */
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


    /**
     * Obtiene el ID máximo de capturas.
     */
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

    /**
     * Inserta un nuevo usuario.
     * @return true si se inserta correctamente
     */
    public boolean insertUsuario(String id, String contrasena){
        String q = "INSERT INTO Usuario (id, contrasena) VALUES ('"+id+"', '"+contrasena+"')";
        System.out.println(q);
        try{
            query.execute(q);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }


}
