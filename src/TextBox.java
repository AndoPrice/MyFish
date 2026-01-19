import processing.core.PApplet;
import processing.core.PFont;

import static processing.core.PConstants.CORNER;

public class TextBox {
    int x, y, h, w;

    PFont bebasNeue;
    PFont font;


    int bgColor, fgColor, selectedColor, borderColor;
    int borderWeight = 1;


    public String text = "";
    int textSize = 10;
    String name;

    public boolean selected = false;


    public TextBox(PApplet p5, String name, int x, int y, int w, int h, PFont font) {
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.bgColor = p5.color(20, 93, 160);
        this.fgColor = p5.color(20, 93, 160);
        this.selectedColor = p5.color(171, 193, 213);
        this.borderColor = p5.color(20, 93, 160);
        this.borderWeight = 1;
        this.bebasNeue = p5.createFont("data/Fonts/BebasNeue-Regular.ttf", 26);
        System.out.println(p5.width);
        System.out.println(p5.height);
        this.name = name;
        this.font = font;
    }

    public void setText(String t){
        this.text= t;
    }


    public void display(PApplet p5) {
        p5.pushStyle();

        p5.fill(255);

        p5.strokeWeight(borderWeight);
        p5.stroke(borderColor);
        p5.rectMode(CORNER);
        //p5.rect(x, y, w, h, 5);

        p5.fill(fgColor);
        p5.textSize(textSize); p5.textAlign(p5.LEFT, p5.CENTER);p5.textFont(font);
        p5.text(text, x + 5, y + textSize);
        p5.popStyle();

        p5.fill(fgColor); p5.textFont(bebasNeue); p5.textAlign(p5.LEFT, p5.BOTTOM);
        p5.text(name, this.x, this.y-10);
    }
}
