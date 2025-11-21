package Graphics;

import processing.core.PApplet;

public class CalendariPlus extends Calendari {
    public Button bNext, bPrev, bOK;

    // Visibilitat del calendari
    boolean visible = false;

    // Constructor
    public CalendariPlus(PApplet p5, int x, int y, int w, int h) {

        super(x, y, w, h);

        bNext = new Button(p5, "Seguent", x+ w/3, y -70, 100, 50);
        bPrev = new Button(p5, "Anterior", x+w/3+100, y - 70, 100, 50);
        bOK   = new Button(p5, "OK", x+w/3+200, y - 70, 50, 50);
    }


    // Setters

    public void toggleVisibility(){
        this.visible = !this.visible;
    }

    public void setVisible(boolean b){
        this.visible = b;
    }

    // Dibuixa el Calendari
    public void display(PApplet p5) {
        if (visible) {
            p5.pushStyle();

            p5.fill(255); p5.noStroke();
            p5.rect(x, y-80, w, h);

            super.display(p5);

            if (dateSelected) {
                String dateText = this.selectedDay+"/"+this.selectedMonth+"/"+this.selectedYear;
                p5.fill(0);
                p5.textSize(24);
                p5.textAlign(p5.RIGHT);
                p5.text(dateText, x+w, y - 30);
            }

            // Dibuixa els botons
            bNext.display(p5);
            bPrev.display(p5);
            bOK.display(p5);
            p5.popStyle();
        }

    }
}
