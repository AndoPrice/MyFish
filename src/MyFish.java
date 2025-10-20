import Graphics.Colors;
import Graphics.Fonts;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class MyFish extends PApplet {

    GUI gui;
    public PImage logo;
    PShape add, list, stat, info;

    Fonts fontsApp;

    public static void main(String[] args) {
        PApplet.main("MyFish");
    }

    public void settings(){
        fullScreen();
        smooth(10);
    }

    public void setup(){

        logo = loadImage("data/MyFish-Logo (1).png");
        add = loadShape("data/Icons/plus-solid.svg");
        list = loadShape("data/Icons/list-ul-solid.svg");
        stat = loadShape("data/Icons/chart-bar-regular.svg");
        info = loadShape("data/Icons/info-solid.svg");

        add.setFill(color(20, 93, 160));
        list.setFill(color(20, 93, 160));
        stat.setFill(color(20, 93, 160));
        info.setFill(color(20, 93, 160));

        fontsApp = new Fonts(this);
        noStroke();                         // Sense bordes
        textAlign(CENTER); textSize(18);   // Alineació i mida del text
        gui = new GUI(this, logo);                   // Constructor de la GUI
    }

    public void draw(){
        background(255);

        switch(gui.pantallaActual){
            case INICIO:   gui.dibujaPantallaInicio(this);
                break;

            /*case ABOUT:     gui.dibuixaPantallaAbout(this);
                break;

            case DETALLS:   gui.dibuixaPantallaDetalls(this);
                break;*/
        }
        updateCursor(this);

    }
    public void keyPressed(){
        if(key=='0'){
            gui.pantallaActual = GUI.PANTALLA.INICIO;
        }
        /*else if(key=='1'){
            gui.pantallaActual = GUI.PANTALLA.DETALLS;
        }
        else if(key=='2'){
            gui.pantallaActual = GUI.PANTALLA.ABOUT;
        }*/
    }

    public void mousePressed(){//MILLORAR AIXÒ
        if ( gui.b1.mouseOverButton( this )||gui.b2.mouseOverButton( this )||gui.b3.mouseOverButton( this )||gui.b4.mouseOverButton( this )) {
            println( " B1 has been pressed!!! " );
        }


    }

    public void updateCursor(PApplet p5){
        if ( gui.b1.updateHandCursor(p5)||gui.b2.updateHandCursor(p5)||gui.b3.updateHandCursor(p5)||gui.b4.updateHandCursor(p5)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }
    }



}
