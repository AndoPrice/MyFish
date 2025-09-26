import Graphics.Colors;
import processing.core.PApplet;

public class MyFish extends PApplet {

    Colors appColors;

    public static void main(String[] args) {
        PApplet.main("MyFish");
    }

    public void settings(){
        fullScreen();
    }

    public void setup(){

        appColors = new Colors(this);

    }

    public void draw(){

        background(255);
        appColors.displayColors(this, 100,100,width-200);

    }

}
