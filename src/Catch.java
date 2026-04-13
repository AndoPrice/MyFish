/**
 * Representa una captura de pesca realizada por un usuario.
 *
 * Contiene información sobre:
 * - Especie
 * - Peso y tamaño
 * - Ubicación
 * - Fecha
 * - Señuelo utilizado
 * - Notas
 * - Imagen asociada
 */
public class Catch{
    /** Identificador de la captura */
    public int id;

    /** Especie capturada */
    Especie especie;

    /** Peso del pez */
    float peso;

    /** Tamaño del pez */
    float tamano;

    /** Lugar de captura */
    String ubicacion;

    /** Fecha de captura */
    String fecha;

    /** Señuelo utilizado */
    String senuelo;

    /** Notas adicionales */
    String notas;

    /** Nombre de la imagen */
    String nombreImagen;

    /**
     * Constructor de la captura.
     */
    public Catch(int id, Especie e, float peso, float tamano, String ubicacion, String fecha, String senuelo, String notas, String nombreImagen) {
        this.id = id;
        this.especie = e;
        this.peso = peso;
        this.tamano = tamano;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.senuelo = senuelo;
        this.notas = notas;
        this.nombreImagen = nombreImagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    /** @return peso */
    public float getPeso() {
        return peso;
    }

    /** @param peso nuevo peso */
    public void setPeso(float peso) {
        this.peso = peso;
    }

    /** @return tamaño */
    public float getTamano() {
        return tamano;
    }

    /** @param tamano nuevo tamaño */
    public void setTamano(float tamano) {
        this.tamano = tamano;
    }

    /** @return ubicación */
    public String getUbicacion() {
        return ubicacion;
    }

    /** @param ubicacion nueva ubicación */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /** @return fecha */
    public String getFecha() {
        return fecha;
    }

    /** @param fecha nueva fecha */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /** @return señuelo */
    public String getSenuelo() {
        return senuelo;
    }

    /** @param senuelo nuevo señuelo */
    public void setSenuelo(String senuelo) {
        this.senuelo = senuelo;
    }

    /** @return notas */
    public String getNotas() {
        return notas;
    }

    /** @param notas nuevas notas */
    public void setNotas(String notas) {
        this.notas = notas;
    }
}
