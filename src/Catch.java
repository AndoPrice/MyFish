public class Catch{
    Especie especie;
    float peso;
    float tamano;
    String ubicacion;
    String fecha;
    String senuelo;
    String notas;

    public Catch(Especie e, float peso, float tamano, String ubicacion, String fecha, String senuelo, String notas) {
        this.especie = e;
        this.peso = peso;
        this.tamano = tamano;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.senuelo = senuelo;
        this.notas = notas;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getTamano() {
        return tamano;
    }

    public void setTamano(float tamano) {
        this.tamano = tamano;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSenuelo() {
        return senuelo;
    }

    public void setSenuelo(String senuelo) {
        this.senuelo = senuelo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
