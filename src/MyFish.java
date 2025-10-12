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
        size(1920, 1080);
        smooth(10);
    }

    public void setup(){

        fontsApp = new Fonts(this);
        noStroke();                         // Sense bordes
        textAlign(CENTER); textSize(18);   // Alineació i mida del text
        gui = new GUI();                   // Constructor de la GUI
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


}
