import processing.core.*;

/**
 * Representa un botón con icono en la interfaz.
 *
 * Permite:
 * - Mostrar iconos (imagen o vector)
 * - Detectar interacción del ratón
 * - Cambiar estado visual (hover, disabled)
 */
public class IconButton {
    PFont bebasNeue;
    float x, y, w, h;
    int fillColor, strokeColor;
    int fillColorOver, fillColorDisabled;
    String textBoto;
    boolean enabled;
    /** Icono vectorial */
    public PShape icon;

    /** Icono en imagen */
    PImage home;

    /**
     * Constructor con icono vectorial.
     */
    public IconButton(PApplet p5, String text, float x, float y, float w, float h, PShape icon){
        this.textBoto = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;
        this.fillColorDisabled = p5.color(150);
        this.strokeColor = p5.color(0);
        this.bebasNeue = p5.createFont("data/Fonts/BebasNeue-Regular.ttf", 26);
        this.icon = icon;

    }

    /**
     * Constructor con imagen.
     */
    public IconButton(PApplet p5, String text, float x, float y, float w, float h, PImage icon){
        this.textBoto = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;
        this.fillColorDisabled = p5.color(150);
        this.strokeColor = p5.color(0);
        this.bebasNeue = p5.createFont("data/Fonts/BebasNeue-Regular.ttf", 26);
        this.home = icon;

    }



    /**
     * Define colores del botón.
     */
    public void setColors(Colors c){
        this.fillColor = c.getAzure();
        this.fillColorOver = c.getAzureSelected();
    }

    /**
     * Activa o desactiva el botón.
     */
    public void setEnabled(boolean b){
        this.enabled = b;
    }

    /**
     * Cambia el texto del botón.
     */
    public void setTextBoto(String t){ this.textBoto = t; }


    /** @return si está activo */
    public boolean isEnabled(){
        return  this.enabled;
    }

    /**
     * Dibuja el botón en pantalla.
     *
     * Cambia su apariencia según:
     * - Si está activo
     * - Si el ratón está encima
     */
    public void display(PApplet p5, boolean b){

        p5.pushStyle();
        p5.stroke(255); p5.strokeWeight(0); p5.fill(255);
        p5.rectMode(PConstants.CENTER);
        p5.rect(this.x, this.y, this.w, this.h, 10);
        p5.popStyle();

        p5.pushStyle();



        if(icon !=null) {
            if(!enabled){
                p5.fill(fillColorDisabled);
            }
            else if(mouseOverButton(p5) ||b){
                p5.fill(fillColorOver);
            }
            else{
                p5.fill(fillColor);
            }
            p5.shape(icon, x - (icon.width / 2 * 0.1f), y - (icon.height / 2 * 0.1f) - 10, icon.width, icon.height);
        }
        if(home !=null){
            if(!enabled){
                p5.fill(fillColorDisabled);
            }
            else if(mouseOverButton(p5)){
                selected(p5);
            }
            else{
                p5.fill(fillColor);
            }
            p5.imageMode(p5.CENTER);
            p5.image(home, x, y, home.width, home.height);

        }

        p5.textAlign(p5.CENTER); p5.textSize(20); p5.textFont(bebasNeue);
        p5.text(textBoto, this.x, this.y+50);
        p5.popStyle();

    }

    /**
     * Aplica estilo cuando está seleccionado.
     */
    public void selected(PApplet p5){
        if (home != null){
            p5.tint(171, 193, 213, 100);
        }
        else if (icon != null){
            p5.fill(fillColorOver);
        }
    }


    /**
     * Dibuja solo el icono.
     */
    public void dibujaIcons (PApplet p5){
        p5.shape(icon, x, y);

    }

    /**
     * Detecta si el ratón está sobre el botón.
     */
    public boolean mouseOverButton(PApplet p5){
        return (p5.mouseX >= this.x-(w/2)) && (p5.mouseX <= this.x-(w/2) + this.w) &&
                (p5.mouseY >= this.y-(y/2)) && (p5.mouseY <= this.y-(y/2) + this.h);
    }

    /**
     * Indica si debe mostrarse el cursor de mano.
     */
    public boolean updateHandCursor(PApplet p5){
        return mouseOverButton(p5) && enabled;
    }
}
