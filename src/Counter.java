import processing.core.*;
/**
 * Componente contador interactivo que permite incrementar o decrementar
 * un valor dentro de un rango definido mediante botones.
 */
public class Counter {

    /** Valor actual. */
    float value = 255;

    /** Valores mínimo y máximo permitidos. */
    float minValue = 0, maxValue = 255;

    /** Incremento/decremento aplicado. */
    float stepValue = 10;

    /** Posición y dimensiones. */
    float x, y, w, h;

    /** Colores del componente. */
    int fillColor, strokeColor;

    /** Iconos de incremento y decremento. */
    PImage iconaMes, iconaMenys;

    /** Fuente utilizada. */
    PFont bebasNeue;

    /** Nombre del contador. */
    String name;

    /**
     * Constructor del contador.
     *
     * @param p5 instancia de Processing
     * @param name nombre del contador
     * @param iconaMes icono de incremento
     * @param iconaMenys icono de decremento
     * @param x posición horizontal
     * @param y posición vertical
     * @param w anchura
     * @param h altura
     */
    public Counter(PApplet p5, String name, PImage iconaMes, PImage iconaMenys, float x, float y, float w, float h){
        this.iconaMes = iconaMes;
        this.iconaMenys = iconaMenys;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        fillColor = p5.color(20, 93, 160);
        strokeColor = p5.color(0);
        this.bebasNeue = p5.createFont("data/Fonts/BebasNeue-Regular.ttf", 26);
        this.name = name;
    }



    /**
     * Obtiene el valor actual.
     *
     * @return valor
     */
    public float getValue(){
        return  this.value;
    }


    /**
     * Reinicia el valor al mínimo.
     */
    public void resetValue(){
        this.value = this.minValue;
    }

    /**
     * Establece el valor inicial.
     *
     * @param n valor inicial
     */
    public void setInitialValue(float n){
        this.value = n;
    }

    /**
     * Establece el valor.
     *
     * @param n nuevo valor
     */
    public void setValue(float n){
        this.value = n;
    }

    /**
     * Establece el incremento.
     *
     * @param n valor del paso
     */
    public void setStepValue(float n){
        this.stepValue = n;
    }

    /**
     * Define los límites del contador.
     *
     * @param minValue mínimo
     * @param maxValue máximo
     */
    public void setValues(int minValue, int maxValue){
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Dibuja el contador y sus botones.
     *
     * @param p5 instancia de Processing
     */
    public void display(PApplet p5){

        p5.pushStyle();
        p5.fill(fillColor);                            // Color
        p5.stroke(strokeColor); p5.strokeWeight(3);      //Color i gruixa del contorn
        p5.rect(this.x, this.y, this.w + 2*this.h, this.h, 10);   // Rectangle del botó

        p5.fill(255); p5.textSize(32); p5.textAlign(p5.LEFT); p5.textFont(bebasNeue);
        p5.text(value, this.x + 20, this.y + this.h/2 + 10);

        p5.fill(fillColor);
        p5.text(name, this.x, this.y+this.h/2-40);



        p5.fill(255); p5.stroke(0);
        p5.rect(this.x + this.w, this.y, this.h, this.h, 10);
        p5.imageMode(PConstants.CORNER);
        p5.image(iconaMes,   this.x + this.w, this.y, this.h, this.h);
        p5.rect(this.x + this.w + this.h, this.y, this.h, this.h, 10);
        p5.image(iconaMenys, this.x + this.w + this.h, this.y, this.h, this.h);
        p5.popStyle();
    }

    /**
     * Comprueba si el ratón está sobre alguno de los botones.
     *
     * @param p5 instancia de Processing
     * @return true si está sobre un botón
     */
    public boolean mouseOverButtons(PApplet p5){
        return mouseOverButtonMes(p5) || mouseOverButtonMenys(p5);
    }

    /**
     * Comprueba si el ratón está sobre el botón de incremento.
     *
     * @param p5 instancia de Processing
     * @return true si está sobre "+"
     */

    public boolean mouseOverButtonMes(PApplet p5){
        return p5.mouseX >= this.x + this.w && p5.mouseX <= this.x + this.w + this.h &&
                p5.mouseY >= this.y && p5.mouseY <= this.y + this.h;
    }

    /**
     * Comprueba si el ratón está sobre el botón de decremento.
     *
     * @param p5 instancia de Processing
     * @return true si está sobre "-"
     */
    public boolean mouseOverButtonMenys(PApplet p5){
        return p5.mouseX >= this.x + this.w + this.h && p5.mouseX <= this.x + this.w + 2*this.h &&
                p5.mouseY >= this.y && p5.mouseY <= this.y + this.h;
    }

    /**
     * Incrementa el valor respetando el máximo.
     */
    public void increment(){
        this.value += stepValue;
        if(this.value>this.maxValue){
            this.value = this.maxValue;
        }
    }

    /**
     * Decrementa el valor respetando el mínimo.
     */
    public void decrement(){
        this.value -= stepValue;
        if(this.value<this.minValue){
            this.value = this.minValue;
        }
    }

    /**
     * Actualiza el valor según la interacción del ratón.
     *
     * Funcionamiento:
     * - Si el ratón está sobre el botón de incremento, aumenta el valor.
     * - Si está sobre el de decremento, lo reduce.
     *
     * @param p5 instancia de Processing
     */
    public void update(PApplet p5){
        if(mouseOverButtonMes(p5)){
            increment();
        }
        else if(mouseOverButtonMenys(p5)){
            decrement();
        }
    }
}
