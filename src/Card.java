import processing.core.PApplet;
import processing.core.PImage;




public class Card {


    enum CardType {
        CATCH,      // más pesada, más larga
        SPECIES,    // especie más común
        AVERAGE     // peso medio, tamaño medio
    }

    CardType type;

    // Propietats
    PImage img;
    String title, place, date;
    String species;
    String description;

    // Dimensions
    float x, y, w, h, b;

    // Constructors

    public Card(){
    }

    public Card(CardType type, String title, String place, String date, String species, String description) {
        this.type = type;
        this.title = title;
        this.place = place;
        this.date = date;
        this.species = species;
        this.description = description;
    }

    public Card(String[] info){
        this.title = info[0];
        this.place = info[1];
        this.date = info[2];
        this.description = info[4];
    }

    //Setters

    public void setDimensions(float x, float y, float w, float h, float b){
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        this.b = b;
    }

    public void setImage(PImage img){
        this.img = img;
    }

    // Dibuixa la Card

    public void display(PApplet p5, boolean selectedCard){

        p5.pushStyle();

        p5.stroke(0);
        if(selectedCard){
            p5.fill(200, 100, 100);
        }
        else if(this.mouseOver(p5)){
            p5.fill(200);
        }
        else {
            p5.fill(220);
        }

        p5.stroke(60, 130, 200);
        p5.strokeWeight(2);
        p5.fill(245);
        p5.rect(x, y, w, h, b);


        p5.fill(20, 93, 160);
        p5.textFont(p5.createFont("data/Fonts/BebasNeue-Regular.ttf", 26));
        p5.textSize(32);
        p5.textAlign(p5.CENTER);
        p5.text(title, x + w/2, y + b*2);



        if (type == CardType.CATCH) {
            drawCatchCard(p5);
        }
        else if (type == CardType.SPECIES) {
            drawSpeciesCard(p5);
        }
        else if (type == CardType.AVERAGE) {
            drawAverageCard(p5);
        }
        p5.popStyle();
    }

    public boolean mouseOver(PApplet p5){
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }

    void drawCatchCard(PApplet p5) {

        float imgW = (w/3) - 2*b;
        float imgH = h - 3*b;

        if (img != null) {
            p5.image(img, x + b, y + 2*b, imgW, imgH);
        }

        float tx = x + w/2 + b;

        p5.textAlign(p5.LEFT);
        p5.textSize(28);


        p5.text("ESPECIE: " + species, tx, y + h/3);
        p5.text(description, tx, y + h/2);

        p5.textSize(26);
        p5.text(place + " · " + date, tx, y + h - b*2);
    }
    void drawSpeciesCard(PApplet p5) {

        float imgW = (w/3) - 2*b;
        float imgH = h - 3*b;

        if (img != null) {
            p5.image(img, x + b, y + 2*b, imgW, imgH);
        }

        p5.textAlign(p5.LEFT);
        p5.textSize(28);

        p5.text("ESPECIE: " + species, x + w/2 + b, y + h/2);
        p5.text(description, x + w/2 + b, y + h/2 + 40);
    }
    void drawAverageCard(PApplet p5) {

        p5.textAlign(p5.CENTER);
        p5.textSize(46);
        p5.text(description, x + w/2, y + h/2+20);
    }

}
