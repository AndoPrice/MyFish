package Graphics;

import processing.core.*;

public class Counter {
    // Valor del comptador
    int value = 255;
    int minValue = 0, maxValue = 255;
    int stepValue = 10;

    // Propietats d'un counter:
    float x, y, w, h;  // Posició i dimensions

    // Colors de farciment i contorn
    int fillColor, strokeColor;

    // Icones del botó
    PImage iconaMes, iconaMenys;

    PFont bebasNeue;

    String name;

    // Mètode Constructor
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

    // Getters

    public int getValue(){
        return  this.value;
    }

    // Setters

    public void resetValue(){
        this.value = this.minValue;
    }

    public void setInitialValue(int n){
        this.value = n;
    }

    public void setValue(int n){
        this.value = n;
    }

    public void setStepValue(int n){
        this.stepValue = n;
    }

    public void setValues(int minValue, int maxValue){
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    // Dibuixa el botó
    public void display(PApplet p5){

        p5.pushStyle();
        p5.fill(fillColor);                            // Color
        p5.stroke(strokeColor); p5.strokeWeight(3);      //Color i gruixa del contorn
        p5.rect(this.x, this.y, this.w + 2*this.h, this.h, 10);   // Rectangle del botó

        p5.fill(255); p5.textSize(32); p5.textAlign(p5.LEFT); p5.textFont(bebasNeue);
        p5.text(value, this.x + 20, this.y + this.h/2 + 10);

        p5.fill(fillColor);
        p5.text(name, this.x, this.y+this.h/2-40);


        // Icona del botó
        p5.fill(255); p5.stroke(0);
        p5.rect(this.x + this.w, this.y, this.h, this.h, 10);
        p5.imageMode(PConstants.CORNER);
        p5.image(iconaMes,   this.x + this.w, this.y, this.h, this.h);
        p5.rect(this.x + this.w + this.h, this.y, this.h, this.h, 10);
        p5.image(iconaMenys, this.x + this.w + this.h, this.y, this.h, this.h);
        p5.popStyle();
    }

    public boolean mouseOverButtons(PApplet p5){
        return mouseOverButtonMes(p5) || mouseOverButtonMenys(p5);
    }

    // Indica si el cursor està sobre el botó Més
    public boolean mouseOverButtonMes(PApplet p5){
        return p5.mouseX >= this.x + this.w && p5.mouseX <= this.x + this.w + this.h &&
                p5.mouseY >= this.y && p5.mouseY <= this.y + this.h;
    }

    // Indica si el cursor està sobre el botó Menys
    public boolean mouseOverButtonMenys(PApplet p5){
        return p5.mouseX >= this.x + this.w + this.h && p5.mouseX <= this.x + this.w + 2*this.h &&
                p5.mouseY >= this.y && p5.mouseY <= this.y + this.h;
    }

    public void increment(){
        this.value += stepValue;
        if(this.value>this.maxValue){
            this.value = this.maxValue;
        }
    }

    public void decrement(){
        this.value -= stepValue;
        if(this.value<this.minValue){
            this.value = this.minValue;
        }
    }

    public void update(PApplet p5){
        if(mouseOverButtonMes(p5)){
            increment();
        }
        else if(mouseOverButtonMenys(p5)){
            decrement();
        }
    }
}
