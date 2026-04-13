import processing.core.PApplet;
import processing.core.PFont;


/**
 * Gestiona las tipografías utilizadas en la aplicación.
 *
 * Permite:
 * - Cargar fuentes
 * - Acceder a ellas
 * - Mostrarlas en pantalla
 */
public class Fonts {
    /** Array de fuentes */
    PFont[] fonts;

    /**
     * Constructor que inicializa las fuentes.
     * @param p5 contexto Processing
     */
    public Fonts(PApplet p5){
        this.setFonts(p5);
    }

    /**
     * Carga las fuentes desde archivos.
     */
    public void setFonts(PApplet p5){
        this.fonts = new PFont[3];
        this.fonts[0] = p5.createFont("data/Fonts/BebasNeue-Regular.ttf", 26);
        this.fonts[1] = p5.createFont("data/Fonts/LEMONMILK-Regular.otf", 22);
        this.fonts[2] = p5.createFont("data/Fonts/Coolvetica Rg.otf", 20);
    }

    /** @return número de fuentes */
    public int getNumFonts(){
        return this.fonts.length;
    }

    /** @return primera fuente */
    public PFont getFirstFont(){
        return  this.fonts[0];
    }

    /** @return segunda fuente */
    public PFont getSecondFont(){
        return  this.fonts[1];
    }

    /** @return tercera fuente */
    public PFont getThirdFont(){
        return  this.fonts[2];
    }

    /**
     * Obtiene una fuente por índice.
     */
    public PFont getFontAt(int i){
        return this.fonts[i];
    }

    /**
     * Muestra todas las fuentes en pantalla.
     */
    public void displayFonts(PApplet p5, float x, float y, float h){
        p5.pushStyle();
        for(int i=0; i<getNumFonts(); i++){
            p5.fill(0); p5.stroke(0); p5.strokeWeight(3);
            p5.textFont(getFontAt(i));
            p5.text("Tipografia "+i, x, y + i*h);
        }
        p5.popStyle();
    }
}
