import processing.core.PApplet;
import processing.core.PFont;

public class Button {
    PFont bebasNeue;
    float x, y, w, h;  // Posició (x, y) i dimensions (w, h)
    int fillColor, strokeColor, textColor; // Colors del boto (fill / stroke).
    int fillColorOver, fillColorDisabled;// Colors del boto (actiu / inactiu).
    int strokeWeight;
    String textBoto;  // Text
    boolean enabled;  // Estat del botó (actiu / inactiu).


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



    public void setBlues(Colors c){
        this.fillColor = c.getAzure();
        this.fillColorOver = c.getAzureSelected();
    }

    public void setDateButtonColors(PApplet p5, Colors c){
        this.fillColor = p5.color(255);
        this.fillColorOver = p5.color(230);
        this.textColor= c.getAzure();
        this.strokeColor = c.getAzure();
        this.strokeWeight = 1;
    }





    public void setEnabled(boolean b){
        this.enabled = b;
    }

    public void setTextBoto(String t){ this.textBoto = t; }


    public boolean isEnabled(){
        return  this.enabled;
    }

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


    public boolean mouseOverButton(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }

    public boolean updateHandCursor(PApplet p5){
        return mouseOverButton(p5) && enabled;
    }
}
