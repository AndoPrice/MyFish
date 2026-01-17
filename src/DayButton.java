import processing.core.PApplet;

public class DayButton {
    float x, y, w, h;

    // Data representativa
    int dia, mes, any;

    // Estats del botó
    boolean selected, enabled;

    // Constructor
    public DayButton(float x, float y, float w, float h, int d, int m, int a){
        this.x = x; this.y=y; this.w = w; this.h = h;
        this.dia = d; this.mes = m; this.any = a;
        this.selected = false;
        this.enabled = true;
    }

    // Setters

    public void setEnabled(boolean b){
        this.enabled = b;
    }

    public void setSelected(boolean b){
        this.selected = b;
    }


    // Dibuixa el botó
    public void display(PApplet p5){
        p5.pushStyle();
        if(enabled){
            p5.fill(255);
        }
        else{
            p5.fill(100);
        }
        p5.stroke(0); p5.strokeWeight(1);
        p5.rect(x, y, w, h, 5);
        if(selected){
            p5.fill(200, 100, 100); p5.noStroke();
            p5.ellipse(x + w/2, y+h/2, 40, 40);
        }
        p5.fill(0); p5.textSize(24); p5.textAlign(p5.CENTER);
        p5.text(dia, x + w/2, y + h/2 + 10);
        p5.popStyle();
    }

    // Ratolí sobre el botó
    public boolean mouseOver(PApplet p5){
        return p5.mouseX>=this.x && p5.mouseX<=this.x+this.w &&
                p5.mouseY>=this.y && p5.mouseY<=this.y+this.h;
    }
}
