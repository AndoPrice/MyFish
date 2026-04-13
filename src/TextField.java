import processing.core.PApplet;
import processing.core.PFont;

import static processing.core.PConstants.BACKSPACE;
import static processing.core.PConstants.CORNER;

/**
 * Componente de entrada de texto editable.
 * Permite introducir texto mediante teclado y gestionar selección.
 */
public class TextField {
    /** Posición y dimensiones. */
    int x, y, h, w;

    /** Fuentes utilizadas. */
    PFont bebasNeue;
    PFont font;

    /** Colores del componente. */
    int bgColor, fgColor, selectedColor, borderColor;

    /** Grosor del borde. */
    int borderWeight = 1;

    /** Texto introducido. */
    public String text = "";

    /** Tamaño del texto. */
    int textSize = 24;

    /** Nombre o etiqueta del campo. */
    String name;

    /** Indica si está seleccionado. */
    public boolean selected = false;

    /**
     * Constructor del campo de texto.
     *
     * @param p5 instancia de Processing
     * @param name nombre del campo
     * @param x posición horizontal
     * @param y posición vertical
     * @param w anchura
     * @param h altura
     * @param font fuente de texto
     */
    public TextField(PApplet p5, String name, int x, int y, int w, int h, PFont font) {
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.bgColor = p5.color(255);
        this.fgColor = p5.color(20, 93, 160);
        this.selectedColor = p5.color(171, 193, 213);
        this.borderColor = p5.color(20, 93, 160);
        this.borderWeight = 1;
        this.bebasNeue = p5.createFont("data/Fonts/BebasNeue-Regular.ttf", 26);
        this.name = name;
        this.font = font;
    }


    /**
     * Dibuja el campo de texto y su contenido.
     *
     * @param p5 instancia de Processing
     */
    public void display(PApplet p5) {
        p5.pushStyle();

        p5.fill(bgColor);


        p5.strokeWeight(borderWeight);
        p5.stroke(borderColor);
        p5.rectMode(CORNER);
        p5.rect(x, y, w, h, 5);

        p5.fill(fgColor);
        p5.textSize(textSize); p5.textAlign(p5.LEFT, p5.TOP);p5.textFont(font);
        p5.text(text, x + 5, y+textSize/2, w, h);
        p5.popStyle();

        p5.fill(fgColor); p5.textFont(bebasNeue); p5.textAlign(p5.LEFT, p5.BOTTOM);
        p5.text(name, this.x, this.y-10);
    }


    /**
     * Gestiona la entrada de teclado.
     *
     * @param key carácter pulsado
     * @param keyCode código de tecla
     */
    public void keyPressed(char key, int keyCode) {
        if (selected) {
            if (keyCode == (int)BACKSPACE) {
                removeText();
            } else if (keyCode == 32) {
                addText(' ');
            } else {

                boolean isKeyCapitalLetter = (key >= 'A' && key <= 'Z');
                boolean isKeySmallLetter = (key >= 'a' && key <= 'z');
                boolean isKeyNumber = (key >= '0' && key <= '9');

                if (isKeyCapitalLetter || isKeySmallLetter || isKeyNumber) {
                    addText(key);
                }
            }
        }
    }

    /**
     * Gestiona teclas especiales.
     *
     * @param keyCode código de tecla
     */
    public void keyPressed(int keyCode) {
        if (!selected) return;

        if (keyCode == BACKSPACE) {
            removeText();
        }
    }

    /**
     * Añade caracteres al texto.
     *
     * @param key carácter introducido
     */
    public void keyTyped(char key) {
        if (!selected) return;

        if (key == '\n' || key == '\r' || key == '\b') return;

        addText(key);
    }


    /**
     * Añade un carácter al texto si no supera el ancho.
     *
     * @param c carácter
     */
    public void addText(char c) {
        if (this.text.length() + 1 < w) {
            this.text += c;
        }
    }


    /**
     * Elimina el último carácter del texto.
     */
    public void removeText() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }


    /**
     * Borra todo el texto.
     */
    public void removeAllText(){
        this.text = "";
    }


    /**
     * Obtiene el texto actual.
     *
     * @return texto
     */
    public String getText(){
        return this.text;
    }


    /**
     * Establece el texto.
     *
     * @param t nuevo texto
     */
    public void setText(String t){
        this.text= t;
    }

    /**
     * Comprueba si el ratón está sobre el campo.
     *
     * @param p5 instancia de Processing
     * @return true si está sobre el campo
     */
    public boolean mouseOverTextField(PApplet p5) {
        return (p5.mouseX >= this.x && p5.mouseX <= this.x + this.w && p5.mouseY >= this.y && p5.mouseY <= this.y + this.h);
    }

    /**
     * Gestiona la selección mediante clic del ratón.
     *
     * @param p5 instancia de Processing
     */
    public void isPressed(PApplet p5) {
        if (mouseOverTextField(p5)) {
            selected = true;
            bgColor = p5.color(240);
            System.out.println(selected);
        } else {
            selected = false;
            bgColor = p5.color(255);
        }
    }
}
