package Graphics;

import processing.core.*;

public class IconButton {
    PFont bebasNeue;
    float x, y, w, h;
    int fillColor, strokeColor;
    int fillColorOver, fillColorDisabled;
    String textBoto;
    boolean enabled;
    public PShape icon;
    public PShape logo;


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



    public void setColors(Colors c){
        this.fillColor = c.getAzure();
        this.fillColorOver = c.getAzureSelected();
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
        p5.stroke(50); p5.strokeWeight(2); p5.fill(255);
        p5.rectMode(PConstants.CENTER);
        p5.rect(this.x, this.y, this.w, this.h, 10);
        p5.popStyle();

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


        p5.shape(icon, x-(icon.width/2*0.1f), y-(icon.height/2*0.1f)-10, icon.width, icon.height);

        p5.textAlign(p5.CENTER); p5.textSize(20); p5.textFont(bebasNeue);
        p5.text(textBoto, this.x, this.y+50);
        p5.popStyle();




    }

    public void displayL(PApplet p5){

        p5.pushStyle();
        p5.stroke(50); p5.strokeWeight(2); p5.fill(255);
        p5.rectMode(PConstants.CENTER);
        p5.rect(this.x, this.y, this.w, this.h, 10);
        p5.popStyle();

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

        p5.shape(icon, x-(icon.width/2), y-(icon.height/2)+5, icon.width, icon.height);


        p5.textAlign(p5.CENTER); p5.textSize(20); p5.textFont(bebasNeue);
        p5.text(textBoto, this.x, this.y+100);
        p5.popStyle();




    }

    public void dibujaIcons (PApplet p5){
        p5.shape(icon, x, y);

    }

    public boolean mouseOverButton(PApplet p5){
        return (p5.mouseX >= this.x-(w/2)) && (p5.mouseX <= this.x-(w/2) + this.w) &&
                (p5.mouseY >= this.y-(y/2)) && (p5.mouseY <= this.y-(y/2) + this.h);
    }


    public boolean updateHandCursor(PApplet p5){
        return mouseOverButton(p5) && enabled;
    }
}
