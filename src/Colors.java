import processing.core.PApplet;
/**
 * Gestiona los colores utilizados en la aplicación.
 *
 * Permite:
 * - Cargar colores
 * - Acceder a ellas
 * - Mostrarlas en pantalla
 */
public class Colors {

    /** Array de colores */
    int[] colors;

    /**
     * Constructor que inicializa los colores.
     * @param p5 contexto Processing
     */
    public Colors(PApplet p5){
        this.setColors(p5);
    }

    /**
     * Carga los colores.
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

    /** getters de los diferentes colores */
    int getNumColors(){
        return this.colors.length;
    }

    public int getAzure(){
        return  this.colors[0];
    }

    int getLightAzure(){
        return  this.colors[1];
    }

    int getAzureSelected(){
        return  this.colors[2];
    }

    int getThirdColor(){
        return  this.colors[2];
    }

    int getColorAt(int i){
        return this.colors[i];
    }

    /**
     * Muestra todas los colores en pantalla.
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
