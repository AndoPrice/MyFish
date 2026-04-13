import processing.core.PApplet;
/**
 * Representa un botón individual de día dentro de un calendario.
 * Permite seleccionar un día concreto y detectar interacción del ratón.
 */
public class DayButton {
    /** Posición y dimensiones del botón. */
    float x, y, w, h;

    /** Fecha representada. */
    int dia, mes, any;

    /** Indica si está seleccionado. */
    boolean selected;

    /** Indica si está habilitado. */
    boolean enabled;

    /**
     * Constructor del botón de día.
     *
     * @param x posición horizontal
     * @param y posición vertical
     * @param w anchura
     * @param h altura
     * @param d día
     * @param m mes
     * @param a año
     */
    public DayButton(float x, float y, float w, float h, int d, int m, int a){
        this.x = x; this.y=y; this.w = w; this.h = h;
        this.dia = d; this.mes = m; this.any = a;
        this.selected = false;
        this.enabled = true;
    }


    /**
     * Activa o desactiva el botón.
     *
     * @param b estado de habilitación
     */
    public void setEnabled(boolean b){
        this.enabled = b;
    }

    /**
     * Marca el botón como seleccionado o no.
     *
     * @param b estado de selección
     */
    public void setSelected(boolean b){
        this.selected = b;
    }


    /**
     * Dibuja el botón del día.
     *
     * @param p5 instancia de Processing
     */
    public void display(PApplet p5){
        p5.pushStyle();
        if(enabled){
            p5.fill(255);
        }
        else{
            p5.fill(100);
        }
        p5.stroke(0); p5.strokeWeight(1);
        p5.rect(x, y, w, h, 5);
        if(selected){
            p5.fill(200, 100, 100); p5.noStroke();
            p5.ellipse(x + w/2, y+h/2, 40, 40);
        }
        p5.fill(0); p5.textSize(24); p5.textAlign(p5.CENTER);
        p5.text(dia, x + w/2, y + h/2 + 10);
        p5.popStyle();
    }


    /**
     * Comprueba si el ratón está sobre el botón.
     *
     * @param p5 instancia de Processing
     * @return true si el ratón está dentro del botón
     */
    public boolean mouseOver(PApplet p5){
        return p5.mouseX>=this.x && p5.mouseX<=this.x+this.w &&
                p5.mouseY>=this.y && p5.mouseY<=this.y+this.h;
    }
}
