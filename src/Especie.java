import processing.core.PImage;
/**
 * Representa una especie de pez.
 *
 * Contiene información descriptiva y visual:
 * - Nombre común y científico
 * - Descripción
 * - Ubicación
 * - Comportamiento
 * - Talla mínima
 * - Imagen
 */
public class Especie {

    String nombreComun;
    String nombreCientifico;
    String descripcion;
    String ubicacion;
    String masInfo;
    String comportamiento;
    String tallaMin;
    String nombreImagen;

    /** Imagen cargada */
    PImage foto;

    /**
     * Constructor de la especie.
     */
    public Especie(String nombreComun, String scientificName, String descripcion, String ubicacion, String masInfo, String comportamiento, String tallaMin, String nombreImagen) {
        this.nombreComun = nombreComun;
        this.nombreCientifico = scientificName;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.masInfo = masInfo;
        this.comportamiento = comportamiento;
        this.tallaMin = tallaMin;
        this.nombreImagen = nombreImagen;
    }

    /** Getters y setters de todos los atributos */

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getMasInfo() {
        return masInfo;
    }

    public void setMasInfo(String masInfo) {
        this.masInfo = masInfo;
    }

    public String getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
    }

    public String getTallaMin() {
        return tallaMin;
    }

    public void setTallaMin(String tallaMin) {
        this.tallaMin = tallaMin;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public PImage getFoto() {
        return foto;
    }

    public void setFoto(PImage foto) {
        this.foto = foto;
    }
}
