import processing.core.PApplet;
import processing.core.PFont;

/**
 * Clase que representa un botón interactivo.
 * Permite mostrar texto, detectar interacción del ratón y cambiar estilos.
 */
public class Button {

    /** Fuente utilizada para el texto. */
    PFont bebasNeue;

    /** Posición y dimensiones del botón. */
    float x, y, w, h;

    /** Colores del botón. */
    int fillColor, strokeColor, textColor;

    /** Color al pasar el ratón por encima. */
    int fillColorOver;

    /** Color cuando está deshabilitado. */
    int fillColorDisabled;

    /** Grosor del borde. */
    int strokeWeight;

    /** Texto del botón. */
    String textBoto;

    /** Indica si el botón está habilitado. */
    boolean enabled;

    /**
     * Constructor del botón.
     *
     * @param p5 instancia de Processing
     * @param text texto del botón
     * @param x posición horizontal
     * @param y posición vertical
     * @param w anchura
     * @param h altura
     */
    public Button(PApplet p5, String text, float x, float y, float w, float h){
        this.textBoto = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;
        this.fillColorDisabled = p5.color(150);
        this.strokeColor = p5.color(0);
        this.bebasNeue = p5.createFont("data/Fonts/BebasNeue-Regular.ttf", 26);
        this.textColor = p5.color(255);
        this.strokeWeight = 2;
    }



    /**
     * Aplica colores azules al botón.
     *
     * @param c objeto de colores
     */
    public void setBlues(Colors c){
        this.fillColor = c.getAzure();
        this.fillColorOver = c.getAzureSelected();
    }

    /**
     * Aplica colores grises al botón.
     *
     * @param c objeto de colores
     */
    public void setGreys(Colors c){
        this.fillColor = c.getColorAt(3);
        this.fillColorOver = c.getColorAt(4);
    }

    /**
     * Aplica colores rojos al botón.
     *
     * @param c objeto de colores
     */
    public void setReds(Colors c){
        this.fillColor = c.getColorAt(5);
        this.fillColorOver = c.getColorAt(6);
    }

    /**
     * Configura colores específicos para botones de calendario.
     *
     * @param p5 instancia de Processing
     * @param c objeto de colores
     */
    public void setDateButtonColors(PApplet p5, Colors c){
        this.fillColor = p5.color(255);
        this.fillColorOver = p5.color(230);
        this.textColor= c.getAzure();
        this.strokeColor = c.getAzure();
        this.strokeWeight = 1;
    }

    /**
     * Activa o desactiva el botón.
     *
     * @param b estado
     */
    public void setEnabled(boolean b){
        this.enabled = b;
    }

    /**
     * Establece el texto del botón.
     *
     * @param t nuevo texto
     */
    public void setTextBoto(String t){ this.textBoto = t; }

    /**
     * Indica si el botón está habilitado.
     *
     * @return true si está activo
     */
    public boolean isEnabled(){
        return  this.enabled;
    }

    /**
     * Dibuja el botón en pantalla.
     *
     * Funcionamiento:
     * - Cambia el color según el estado (normal, hover, deshabilitado).
     * - Dibuja el rectángulo.
     * - Renderiza el texto centrado.
     *
     * @param p5 instancia de Processing
     */
    public void display(PApplet p5){
        p5.pushStyle();
        if(!enabled){
            p5.fill(fillColorDisabled);
        }
        else if(mouseOverButton(p5)){
            p5.fill(fillColorOver);
        }
        else{
            p5.fill(fillColor);
        }
        p5.stroke(strokeColor); p5.strokeWeight(strokeWeight);
        p5.rect(this.x, this.y, this.w, this.h, 10);


        p5.fill(textColor); p5.textAlign(p5.CENTER); p5.textSize(20); p5.textFont(bebasNeue);
        p5.text(textBoto, this.x + this.w/2, this.y + this.h/2 + 10);
        p5.popStyle();
    }


    /**
     * Comprueba si el ratón está sobre el botón.
     *
     * @param p5 instancia de Processing
     * @return true si el ratón está dentro del botón
     */
    public boolean mouseOverButton(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }

}
