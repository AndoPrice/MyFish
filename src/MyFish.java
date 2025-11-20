import Graphics.Colors;
import Graphics.Fonts;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class MyFish extends PApplet {

    GUI gui;
    public PImage logo, home, mes, menys;
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
        home = loadImage("data/Icons/MyFish-Logo Home.png");
        mes = loadImage("data/Icons/mes.png");
        menys = loadImage("data/Icons/menys.png");
        add  = loadShape("data/Icons/plus-solid.svg");
        list = loadShape("data/Icons/list-ul-solid.svg");
        stat = loadShape("data/Icons/chart-bar-regular.svg");
        info = loadShape("data/Icons/info-solid.svg");

        float scaleFactor = 0.1f;
        add.scale(scaleFactor);
        list.scale(scaleFactor);
        stat.scale(scaleFactor);
        info.scale(scaleFactor);

        fontsApp = new Fonts(this);
        noStroke();
        textAlign(CENTER); textSize(18);
        gui = new GUI(this, logo, add, list, stat, info, home, mes, menys);//passar a array

        setShapeColor(add, gui.colors.getAzure());
        setShapeColor(list, gui.colors.getAzure());
        setShapeColor(stat, gui.colors.getAzure());
        setShapeColor(info, gui.colors.getAzure());
        //setShapeColor(logoI, gui.colors.getAzure());





    }

    public void draw(){
        background(255);

        switch(gui.pantallaActual){
            case INICIO:   gui.dibujaPantallaInicio(this);
                break;

            case REGISTRAR_CAPTURA:     gui.dibujaPantallaRegistrarCaptura(this);
                break;

            case VER_REGISTRO:     gui.dibujaPantallaVerRegistro(this);
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
        gui.t2.keyPressed(key, keyCode);
        gui.t3.keyPressed(key, keyCode);
        gui.t4.keyPressed(key, keyCode);

        if(gui.tl1.getTextField().mouseOverTextField(this)){
            gui.tl1.getTextField().keyPressed(key, (int)keyCode);
            gui.tl1.update(this);
        }
    }

    public void mousePressed(){
        if ( gui.b1.mouseOverButton( this )) {
            println(" B1 has been pressed!!! ");
            gui.pantallaActual = GUI.PANTALLA.REGISTRAR_CAPTURA;
        }
        if ( gui.b2.mouseOverButton( this )){
            println( " B2 has been pressed!!! " );
            gui.pantallaActual = GUI.PANTALLA.VER_REGISTRO;
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

        if (gui.ib1.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.REGISTRAR_CAPTURA;
        }

        if (gui.ib2.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.VER_REGISTRO;
        }

        gui.t1.isPressed(this);
        gui.t2.isPressed(this);
        gui.t3.isPressed(this);
        gui.t4.isPressed(this);

        gui.tl1.getTextField().isPressed(this);
        gui.tl1.buttonPressed(this);

        if(gui.tamano.mouseOverButtonMes(this)){
            gui.tamano.increment();
        }
        if(gui.tamano.mouseOverButtonMenys(this)){
            gui.tamano.decrement();
        }
        if(gui.peso.mouseOverButtonMes(this)){
            gui.peso.increment();
        }
        if(gui.peso.mouseOverButtonMenys(this)){
            gui.peso.decrement();
        }

        if(gui.nextPage.mouseOverButton(this)){
            gui.registro.nextPage();
        }
        if(gui.previousPage.mouseOverButton(this)){
            gui.registro.prevPage();
        }


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
