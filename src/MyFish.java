import Graphics.Colors;
import Graphics.Fonts;
import processing.core.PApplet;

public class MyFish extends PApplet {

    GUI gui;

    Colors appColors;
    Fonts fontsApp;

    public static void main(String[] args) {
        PApplet.main("MyFish");
    }

    public void settings(){
        //fullScreen();
        size(1366, 768);
        smooth(10);
    }

    public void setup(){

        fontsApp = new Fonts(this);
        noStroke();                         // Sense bordes
        textAlign(CENTER); textSize(18);   // Alineació i mida del text
        gui = new GUI(this);                   // Constructor de la GUI
    }

    public void draw(){
        background(255);

        switch(gui.pantallaActual){
            case INICIAL:   gui.dibuixaPantallaInicial(this);
                break;

            case ABOUT:     gui.dibuixaPantallaAbout(this);
                break;

            case DETALLS:   gui.dibuixaPantallaDetalls(this);
                break;
        }
        updateCursor(this);

    }
    public void keyPressed(){
        if(key=='0'){
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
        }
        else if(key=='1'){
            gui.pantallaActual = GUI.PANTALLA.DETALLS;
        }
        else if(key=='2'){
            gui.pantallaActual = GUI.PANTALLA.ABOUT;
        }
    }

    public void mousePressed(){
        if ( gui.b1.mouseOverButton( this ) ) {
            println( " B1 has been pressed!!! " );
        }

    }

    public void updateCursor(PApplet p5){
        if ( gui.b1.updateHandCursor(p5)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }
    }



}
