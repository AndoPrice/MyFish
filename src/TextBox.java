import processing.core.PApplet;
import processing.core.PFont;

import static processing.core.PConstants.CORNER;
/**
 * Componente gráfico para mostrar texto en pantalla de forma no editable.
 * A diferencia de TextField, este componente solo muestra información.
 */
public class TextBox {
    /** Posición y dimensiones del cuadro de texto. */
    int x, y, h, w;

    /** Fuentes utilizadas para el texto y el título. */
    PFont bebasNeue;
    PFont font;

    /** Colores del componente. */
    int bgColor, fgColor, selectedColor, borderColor;

    /** Grosor del borde. */
    int borderWeight = 1;

    /** Texto que se muestra dentro del cuadro. */
    public String text = "";

    /** Tamaño del texto. */
    int textSize = 10;

    /** Nombre o etiqueta del TextBox. */
    String name;

    /**
     * Constructor de la clase TextBox.
     *
     * @param p5 instancia de Processing
     * @param name nombre o etiqueta del cuadro
     * @param x posición horizontal
     * @param y posición vertical
     * @param w anchura
     * @param h altura
     * @param font fuente para el texto
     */
    public TextBox(PApplet p5, String name, int x, int y, int w, int h, PFont font) {
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.bgColor = p5.color(20, 93, 160);
        this.fgColor = p5.color(20, 93, 160);
        this.selectedColor = p5.color(171, 193, 213);
        this.borderColor = p5.color(20, 93, 160);
        this.borderWeight = 1;
        this.bebasNeue = p5.createFont("data/Fonts/BebasNeue-Regular.ttf", 26);
        this.name = name;
        this.font = font;
    }

    /**
     * Establece el texto que se mostrará en el cuadro.
     *
     * @param t texto a mostrar
     */
    public void setText(String t){
        this.text= t;
    }


    /**
     * Dibuja el cuadro de texto en pantalla.
     *
     * Funcionamiento:
     * - Aplica estilos gráficos.
     * - Dibuja el fondo y el borde del cuadro.
     * - Renderiza el texto dentro del área definida.
     * - Muestra el nombre o etiqueta encima del cuadro.
     *
     * @param p5 instancia de Processing
     */
    public void display(PApplet p5) {
        p5.pushStyle();

        p5.fill(255);

        p5.strokeWeight(borderWeight);
        p5.stroke(borderColor);
        p5.rectMode(CORNER);


        p5.fill(fgColor);
        p5.textSize(textSize); p5.textAlign(p5.LEFT, p5.TOP);p5.textFont(font);
        p5.text(text, x + 5, y,  w, h);
        p5.popStyle();

        p5.fill(fgColor); p5.textFont(bebasNeue); p5.textAlign(p5.LEFT, p5.BOTTOM);
        p5.text(name, this.x, this.y-10);
    }
}
