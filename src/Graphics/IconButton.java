package Graphics;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PShape;

public class IconButton {
    PFont bebasNeue;
    float x, y, w, h;  // Posició (x, y) i dimensions (w, h)
    int fillColor, strokeColor; // Colors del boto (fill / stroke).
    int fillColorOver, fillColorDisabled;  // Colors del boto (actiu / inactiu).
    String textBoto;  // Text
    boolean enabled;// Estat del botó (actiu / inactiu).
    PShape icon;

    // Constructor
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

    // Setters

    public void setColors(Colors c){
        this.fillColor = c.getAzure();
        this.fillColorOver = c.getLightAzure();
    }


    public void setEnabled(boolean b){
        this.enabled = b;
    }

    public void setTextBoto(String t){ this.textBoto = t; }



    // Getters
    public boolean isEnabled(){
        return  this.enabled;
    }

    // Dibuixa el botó
    public void display(PApplet p5){
        p5.pushStyle();
        if(!enabled){
            p5.fill(fillColorDisabled);  // Color desabilitat
        }
        else if(mouseOverButton(p5)){
            p5.fill(fillColorOver);      // Color quan ratolí a sobre
        }
        else{
            p5.fill(fillColor);          // Color actiu però ratolí fora
        }
        p5.stroke(255); p5.strokeWeight(0);         //Color i gruixa del contorn
        p5.rect(this.x, this.y, this.w, this.h, 10);// Rectangle del botó

        // Text (color, alineació i mida)
        p5.fill(255); p5.textAlign(p5.CENTER); p5.textSize(20); p5.textFont(bebasNeue);
        p5.text(textBoto, this.x + this.w/2, this.y + this.h/2 + 10);
        p5.popStyle();
    }

    // Indica si el cursor està sobre el botó
    public boolean mouseOverButton(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }

    // Indica si cal posar el cursor a HAND
    public boolean updateHandCursor(PApplet p5){
        return mouseOverButton(p5) && enabled;
    }
}
