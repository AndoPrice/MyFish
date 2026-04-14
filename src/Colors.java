import processing.core.PApplet;
/**
 * Gestiona la paleta de colores utilizada en la aplicación.
 *
 * Esta clase permite:
 * - Inicializar una serie de colores predefinidos.
 * - Acceder a cada color mediante métodos getter.
 * - Mostrar visualmente la paleta de colores en pantalla.
 *
 * Los colores están pensados para mantener una coherencia estética en toda la interfaz.
 */
public class Colors {

    /** Array que almacena los colores de la aplicación */
    int[] colors;

    /**
     * Constructor que inicializa la paleta de colores.
     *
     * @param p5 contexto de Processing necesario para crear colores
     */
    public Colors(PApplet p5){
        this.setColors(p5);
    }

    /**
     * Inicializa el array de colores con valores predefinidos.
     *
     * Los colores incluyen diferentes tonos de azul, grises y rojos,
     * utilizados para botones, fondos, estados seleccionados y alertas.
     *
     * @param p5 contexto de Processing
     */
    void setColors(PApplet p5){
        this.colors = new int[7];
        this.colors[0] = p5.color(20, 93, 160);
        this.colors[1] = p5.color(40, 112, 180);
        this.colors[2] = p5.color(171, 193, 213);
        this.colors[3] = p5.color(148, 148, 148);
        this.colors[4] = p5.color(181, 181, 181);
        this.colors[5] = p5.color(204, 0, 0);
        this.colors[6] = p5.color(255, 102, 102);
    }

    /**
     * Devuelve el número total de colores definidos.
     *
     * @return número de colores en la paleta
     */
    int getNumColors(){
        return this.colors.length;
    }

    /**
     * Devuelve el color azul principal.
     *
     * @return color en formato int
     */
    public int getAzure(){
        return  this.colors[0];
    }

    /**
     * Devuelve un tono de azul más claro.
     *
     * @return color en formato int
     */
    int getLightAzure(){
        return  this.colors[1];
    }

    /**
     * Devuelve el color utilizado para elementos seleccionados.
     *
     * @return color en formato int
     */
    int getAzureSelected(){
        return  this.colors[2];
    }

    /**
     * Devuelve el tercer color de la paleta (alias de azure seleccionado).
     *
     * @return color en formato int
     */
    int getThirdColor(){
        return  this.colors[2];
    }

    /**
     * Devuelve un color concreto del array según su índice.
     *
     * @param i índice del color
     * @return color correspondiente
     */
    int getColorAt(int i){
        return this.colors[i];
    }

    /**
     * Muestra visualmente la paleta de colores en pantalla.
     *
     * Funcionamiento:
     * - Dibuja un título ("Colors:")
     * - Divide el ancho disponible entre todos los colores
     * - Dibuja un rectángulo para cada color del array
     *
     * @param p5 contexto Processing
     * @param x posición X inicial
     * @param y posición Y inicial
     * @param w ancho total disponible para mostrar la paleta
     */
    public void displayColors(PApplet p5, float x, float y, float w){
        p5.pushStyle();
        //Llegenda
        p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(36);
        p5.text("Colors:", x, y-10);

        // Paleta de colors
        float wc = w / getNumColors();
        for(int i=0; i<getNumColors(); i++){
            p5.fill(getColorAt(i)); p5.stroke(0); p5.strokeWeight(3);
            p5.rect(x + i*wc, y, wc, wc);
        }
        p5.popStyle();
    }

}
