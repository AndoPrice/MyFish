import Graphics.Button;
import Graphics.Colors;
import Graphics.IconButton;
import Graphics.TextField;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

import static Graphics.Layout.*;

public class GUI {

    Colors colors;
    PImage logo;

    Button b1, b2, b3, b4, b5, b6;

    IconButton ib1, ib2, ib3, ib4;

    TextField t1, t2;

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIO, REGISTRAR_CAPTURA, VER_CAPTURA, DETALLES, ESTADISTICAS, INFO, PEZ};

    // Pantalla Actual
    public PANTALLA pantallaActual;

    // Constructor de la GUI
    public GUI(PApplet p5, PImage logo, PShape add, PShape list, PShape stat, PShape info){
        colors = new Colors(p5);

        b1 = new Button(p5, "REGISTRAR CAPTURA", p5.width/2-100, 400, 200, 80);
        b1.setColors(colors);
        b2 = new Button(p5, "VER REGISTRO", p5.width/2-100, 500, 200, 80);
        b2.setColors(colors);
        b3 = new Button(p5, "ESTADÍSTICAS", p5.width/2-100, 600, 200, 80);
        b3.setColors(colors);
        b4 = new Button(p5, "INFORMACIÓN DE PECES", p5.width/2-100, 700, 200, 80);
        b4.setColors(colors);

        ib1 = new IconButton(p5, "REGISTRAR CAPTURA", p5.width*2/5, 50, 100, 80, add);
        ib2 = new IconButton(p5, "VER REGISTRO", p5.width*3/5, 50, 100, 80, list);
        ib3 = new IconButton(p5, "ESTADÍSTICAS", p5.width*4/5, 50, 100, 80, stat);
        ib4 = new IconButton(p5, "INFORMACIÓN DE PECES", p5.width*5/5, 50, 100, 80, info);

        t1 = new TextField(p5, p5.width/2+200, 400, 200, 80);

        pantallaActual = PANTALLA.INICIO;
        this.logo = logo;
    }

    public void dibujaBotonesInicio(PApplet p5){
        b1.display(p5);
        b2.display(p5);
        b3.display(p5);
        b4.display(p5);
    }

    public void dibujaBotonesTopBar(PApplet p5){

    }

    public void dibujaTextFieldInicio(PApplet p5){
        t1.display(p5);
    }


    // PANTALLES DE LA GUI

    public void dibujaPantallaInicio(PApplet p5){

        p5.background(255);
        dibujaLogo(p5);
        dibujaBotonesInicio(p5);
        dibujaTextFieldInicio(p5);

    }

    public void dibujaPantallaRegistrarCaptura(PApplet p5){

    }

    /*public void dibuixaPantallaAbout(PApplet p5){
        p5.background(55);
        dibuixaLogo(p5);
        dibuixaSideBar(p5);
        dibuixaBanner(p5);
        dibuixaColumna1(p5);
    }

    public void dibuixaPantallaDetalls(PApplet p5){
        p5.background(55);
        dibuixaLogo(p5);
        dibuixaSideBar(p5);
        dibuixaBanner(p5);
        dibuixaColumnes12(p5);
    }*/


    // ZONES DE LA GUI

    public void dibujaLogo(PApplet p5){
        p5.imageMode(p5.CENTER);
        p5.image(logo, p5.width/2, 250);
    }

    public void dibujaTopBar(PApplet p5){
        p5.rect(marginH, 2*marginV + logoHeight, sidebarWidth, sidebarHeight);
        p5.fill(0);
        p5.text("SIDEBAR", marginH + sidebarWidth/2, marginV + logoHeight + sidebarHeight/2);
    }


}
