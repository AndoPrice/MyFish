import Graphics.Colors;
import Graphics.Fonts;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class MyFish extends PApplet {

    GUI gui;
    public PImage logo;
    PShape add, list, stat, info, logoI;

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
        add  = loadShape("data/Icons/plus-solid.svg");
        list = loadShape("data/Icons/list-ul-solid.svg");
        stat = loadShape("data/Icons/chart-bar-regular.svg");
        info = loadShape("data/Icons/info-solid.svg");
        logoI = loadShape("data/Icons/MyFish-Logo (150 x 150 px) (1).svg");

        float scaleFactor = 0.1f;
        add.scale(scaleFactor);
        list.scale(scaleFactor);
        stat.scale(scaleFactor);
        info.scale(scaleFactor);

        setShapeColor(add, color(20, 93, 160));
        setShapeColor(list, color(20, 93, 160));
        setShapeColor(stat, color(20, 93, 160));
        setShapeColor(info, color(20, 93, 160));







        fontsApp = new Fonts(this);
        noStroke();
        textAlign(CENTER); textSize(18);
        gui = new GUI(this, logo, add, list, stat, info, logoI);                   // Constructor de la GUI
    }

    public void draw(){
        background(255);

        switch(gui.pantallaActual){
            case INICIO:   gui.dibujaPantallaInicio(this);
                break;

            case REGISTRAR_CAPTURA:     gui.dibujaPantallaRegistrarCaptura(this);
                break;

        }
        updateCursor(this);

    }
    public void keyPressed(){
        if(key=='0'){
            gui.pantallaActual = GUI.PANTALLA.INICIO;
        }
        else if(key=='1'){
            gui.pantallaActual = GUI.PANTALLA.REGISTRAR_CAPTURA;
        }

        gui.t1.keyPressed(key, keyCode);
    }

    public void mousePressed(){
        if ( gui.b1.mouseOverButton( this )) {
            println(" B1 has been pressed!!! ");
            gui.pantallaActual = GUI.PANTALLA.REGISTRAR_CAPTURA;
        }
        if ( gui.b2.mouseOverButton( this )){
            println( " B2 has been pressed!!! " );
        }
        if ( gui.b3.mouseOverButton( this )) {
            println( " B3 has been pressed!!! " );
        }
        if ( gui.b4.mouseOverButton( this )) {
            println( " B4 has been pressed!!! " );
        }
        if (gui.ib5.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIO;
        }

        GUI.t1.isPressed(this);


    }

    public void updateCursor(PApplet p5){
        if ( gui.b1.updateHandCursor(p5)||gui.b2.updateHandCursor(p5)||gui.b3.updateHandCursor(p5)||gui.b4.updateHandCursor(p5)){
            cursor(HAND);
        }
        if(gui.t1.selected == true){
            cursor(TEXT);

        }
        else {
            cursor(ARROW);
        }
    }

    void setShapeColor(PShape shape, int c) {
        shape.disableStyle();
        shape.setFill(c);
        shape.setStroke(false);

        for (int i = 0; i < shape.getChildCount(); i++) {
            PShape child = shape.getChild(i);
            if (child != null) {
                child.disableStyle();
                child.setFill(c);
                child.setStroke(false);
            }
        }
    }



}
