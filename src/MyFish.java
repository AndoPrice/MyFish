import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

import java.io.File;

public class MyFish extends PApplet {

    GUI gui;
    public PImage logo, home, mes, menys;
    PShape add, list, stat, info;

    PImage uploadImage;
    Button uploadB;
    String titol = "";
    Especie clicked;


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

        uploadB = new Button(this, "IMAGEN", 150, 800, 100, 50);
        uploadB.setBlues(gui.colors);


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

            case ESTADISTICAS:     gui.dibujaPantallaEstadisticas(this);
                break;

            case INFO:     gui.dibujaPantallaInformacion(this);
                break;

            case ESPECIE:     gui.dibujaPantallaEspecie(this, clicked);
                break;


        }
        updateCursor(this);
        if(gui.pantallaActual== GUI.PANTALLA.REGISTRAR_CAPTURA){
            if(uploadImage!=null){
                imageMode(CORNER);
                image(uploadImage, 150, 275, 520, 520);
                textSize(34); textAlign(RIGHT);
                fill(0);
            }
            else{
                fill(200);
                rect(150, 275, 520, 520);
                textSize(34); textAlign(RIGHT);
            }

            // Dibuixa el botó
            uploadB.display(this);
        }

    }

    public void fileSelected(File selection) {
        if (selection == null) {
            println("No s'ha seleccionat cap fitxer.");
        } else {

            // Obtenim la ruta del fitxer seleccionat
            String rutaImatge = selection.getAbsolutePath();

            uploadImage = loadImage(rutaImatge);  // Actualitzam imatge
            titol = selection.getName();  // Actualitzam títol
        }
    }

    public void keyPressed(){

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

        if(gui.pantallaActual==GUI.PANTALLA.INICIO) {
            if (gui.b1.mouseOverButton(this)) {
                println(" B1 has been pressed!!! ");
                gui.pantallaActual = GUI.PANTALLA.REGISTRAR_CAPTURA;
            }
            else if (gui.b2.mouseOverButton(this)) {
                println(" B2 has been pressed!!! ");
                gui.pantallaActual = GUI.PANTALLA.VER_REGISTRO;
            }
            else if (gui.b3.mouseOverButton(this)) {
                println(" B3 has been pressed!!! ");
                gui.pantallaActual = GUI.PANTALLA.ESTADISTICAS;
            }
            else if (gui.b4.mouseOverButton(this)) {
                println(" B4 has been pressed!!! ");
                gui.pantallaActual = GUI.PANTALLA.INFO;
            }
        }
        if (gui.homeB.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIO;
        }

        if (gui.ib1.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.REGISTRAR_CAPTURA;
        }

        if (gui.ib2.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.VER_REGISTRO;
        }
        if (gui.ib3.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.ESTADISTICAS;
        }
        if (gui.ib4.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INFO;
        }

        if(gui.pantallaActual==GUI.PANTALLA.REGISTRAR_CAPTURA) {

            gui.t1.isPressed(this);
            gui.t2.isPressed(this);
            gui.t3.isPressed(this);
            gui.t4.isPressed(this);

            gui.tl1.getTextField().isPressed(this);
            gui.tl1.buttonPressed(this);

            if (gui.tamano.mouseOverButtonMes(this)) {
                gui.tamano.increment();
            }
            if (gui.tamano.mouseOverButtonMenys(this)) {
                gui.tamano.decrement();
            }

            if (gui.peso.mouseOverButtonMes(this)) {
                gui.peso.increment();
            }
            if (gui.peso.mouseOverButtonMenys(this)) {
                gui.peso.decrement();
            }

            if(gui.registrar.mouseOverButton(this)){
                gui.pantallaActual=GUI.PANTALLA.VER_REGISTRO;
            }
            // Comprovar si clicam sobre botons del Calendari
            gui.cp1.checkButtons(this);

            // Si pitja el botó, canvia la visibilitat del calendari.
            if(gui.bCal.mouseOverButton(this)&&gui.bCal.isEnabled()){
                gui.cp1.toggleVisibility();
            }
            // Si pitjam el botó de Next, canviarà al seguent mes
            if(gui.cp1.bNext.mouseOverButton(this)){
                gui.cp1.nextMonth();
            }
            // Si pitjam el botó de Prev, canviarà al mes anterior
            if(gui.cp1.bPrev.mouseOverButton(this)){
                gui.cp1.prevMonth();
            }
            // Si pitjam el botó de OK, confirmarà la data seleccionada i amagarà el calendari
            if(gui.cp1.bOK.mouseOverButton(this) && gui.cp1.isDateSelected()){
                gui.dataCalendari = gui.cp1.getSelectedDate();
                gui.cp1.setVisible(false);
                gui.bCal.setTextBoto(gui.dataCalendari);
            }
            if(uploadB.mouseOverButton(this)){
                // Obrim el dialeg
                selectInput("Selecciona una imatge ...", "fileSelected");
            }
        }

        if(gui.pantallaActual== GUI.PANTALLA.VER_REGISTRO){
            if(gui.nextPage.mouseOverButton(this)){
                gui.registro.nextPage();
            }
            if(gui.previousPage.mouseOverButton(this)) {
                gui.registro.prevPage();
            }
        }


        if(gui.pantallaActual== GUI.PANTALLA.INFO){
            if(gui.nextPage2.mouseOverButton(this)){
                gui.infoPeces.nextPage();
                System.out.println("next page");
            }
            if(gui.previousPage2.mouseOverButton(this)) {
                gui.infoPeces.prevPage();
            }
            clicked = gui.infoPeces.handleClick(this);
            if (clicked != null) {
                gui.pantallaActual = GUI.PANTALLA.ESPECIE;
            }

        }
        if(gui.pantallaActual == GUI.PANTALLA.ESPECIE){
            if(gui.volver.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.INFO;
            }
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
