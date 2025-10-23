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

    IconButton ib1, ib2, ib3, ib4, ib5;

    static TextField t1;
    TextField t2;

    public enum PANTALLA {INICIO, REGISTRAR_CAPTURA, VER_CAPTURA, DETALLES, ESTADISTICAS, INFO, PEZ};


    public PANTALLA pantallaActual;

    // Constructor de la GUI
    public GUI(PApplet p5, PImage logo, PShape add, PShape list, PShape stat, PShape info, PShape logoI){
        colors = new Colors(p5);

        b1 = new Button(p5, "REGISTRAR CAPTURA", p5.width/2-100, 400, 200, 80);
        b1.setColors(colors);
        b2 = new Button(p5, "VER REGISTRO", p5.width/2-100, 500, 200, 80);
        b2.setColors(colors);
        b3 = new Button(p5, "ESTADÍSTICAS", p5.width/2-100, 600, 200, 80);
        b3.setColors(colors);
        b4 = new Button(p5, "INFORMACIÓN DE PECES", p5.width/2-100, 700, 200, 80);
        b4.setColors(colors);

        ib1 = new IconButton(p5, "REGISTRAR CAPTURA", (p5.width*2/5)-150, 100, 200, 125, add);
        ib1.setColors(colors);
        ib2 = new IconButton(p5, "VER REGISTRO", (p5.width*3/5)-150, 100, 200, 125, list);
        ib2.setColors(colors);
        ib3 = new IconButton(p5, "ESTADÍSTICAS", (p5.width*4/5)-150, 100, 200, 125, stat);
        ib3.setColors(colors);
        ib4 = new IconButton(p5, "INFORMACIÓN DE PECES", (p5.width*5/5)-150, 100, 200, 125, info);
        ib4.setColors(colors);
        ib5 = new IconButton(p5, "", (p5.width/5)-150, 100, 200, 125, logoI);

        t1 = new TextField(p5, p5.width/2+200, 300, 200, 50);

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
        ib1.display(p5);
        ib2.display(p5);
        ib3.display(p5);
        ib4.display(p5);
        ib5.displayL(p5);

    }

    public void dibujaTextFieldInicio(PApplet p5){
        t1.display(p5);
    }



    public void dibujaPantallaInicio(PApplet p5){

        p5.background(255);
        dibujaLogo(p5);
        dibujaBotonesInicio(p5);

    }

    public void dibujaPantallaRegistrarCaptura(PApplet p5){
        p5.background(255);
        dibujaTextFieldInicio(p5);
        dibujaBotonesTopBar(p5);

    }



    public void dibujaLogo(PApplet p5){
        p5.imageMode(p5.CENTER);
        p5.image(logo, p5.width/2, 250);
    }



}
