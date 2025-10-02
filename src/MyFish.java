import Graphics.Colors;
import Graphics.Fonts;
import processing.core.PApplet;

public class MyFish extends PApplet {

    Colors appColors;
    Fonts fontsApp;

    public static void main(String[] args) {
        PApplet.main("MyFish");
    }

    public void settings(){
        size(800, 800);
        smooth(10);
    }

    public void setup(){

        fontsApp = new Fonts(this);
    }

    public void draw(){
        background(255);

        textFont(fontsApp.getFirstFont());
        text("Titol de l'App", 50, 200);

        fill(50);
        textFont(fontsApp.getSecondFont());
        text("Subtitol de l'App", 50, 250);

        fill(55, 0, 0);
        textFont(fontsApp.getThirdFont());
        text("Paràgraf de l'App", 50, 300);


        fontsApp.displayFonts(this, 100, 400, 50);


    }


}
