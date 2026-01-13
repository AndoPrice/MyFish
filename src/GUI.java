import Graphics.*;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PShape;

public class GUI {

    Colors colors;
    PImage logo;
    PFont bebasNeue;

    Fonts fonts;

    Button b1, b2, b3, b4, nextPage, previousPage, bCal, registrar;

    IconButton ib1, ib2, ib3, ib4, homeB;

    DayButton db1;

    String [] species = {"Barracuda", "Llampuga", "Palometón", "Bacoreta"};

    TextList tl1;

    static TextField t1, t2, t3, t4;

    Counter peso, tamano;

    PagedTable registro;
    String[] registroHeaders = {"FECHA", "ESPECIE", "PESO (Kg)", "TAMAÑO (cm)"};
    float tableW = 1000, tableH = 500;
    float[] colWidths = {25, 25, 25, 25};
    String[][] capturas = {
            {"20/10/25", "Barracuda", "2", "60"},
            {"19/10/25", "Bacoreta", "1", "40"},
            {"17/10/25", "Llampuga", "3", "65"},
            {"16/10/25", "Palometón", "4", "50"},
            {"5/10/25", "Lubina", "2", "45"},
            {"1/10/25", "Dorada", "1", "34"},
            {"1/10/25", "Mero", "5", "70"},

    };
    Card c1;
    CalendariPlus cp1;
    String dataCalendari = "";

    public enum PANTALLA {INICIO, REGISTRAR_CAPTURA, VER_REGISTRO, VER_CAPTURA, DETALLES, ESTADISTICAS, INFO, PEZ};


    public PANTALLA pantallaActual;

    // Constructor de la GUI
    public GUI(PApplet p5, PImage logo, PShape add, PShape list, PShape stat, PShape info, PImage home, PImage mes, PImage menys){
        colors = new Colors(p5);

        this.bebasNeue = p5.createFont("data/Fonts/BebasNeue-Regular.ttf", 26);
        fonts = new Fonts(p5);

        b1 = new Button(p5, "REGISTRAR CAPTURA", p5.width/2-100, 400, 200, 80);
        b1.setBlues(colors);
        b2 = new Button(p5, "VER REGISTRO", p5.width/2-100, 500, 200, 80);
        b2.setBlues(colors);
        b3 = new Button(p5, "ESTADÍSTICAS", p5.width/2-100, 600, 200, 80);
        b3.setBlues(colors);
        b4 = new Button(p5, "INFORMACIÓN DE PECES", p5.width/2-100, 700, 200, 80);
        b4.setBlues(colors);

        registrar = new Button(p5, "REGISTRAR", p5.width/2+450, 800, 100, 50);
        registrar.setBlues(colors);



        ib1 = new IconButton(p5, "REGISTRAR CAPTURA", (p5.width*2/5)-150, 100, 200, 125, add);
        ib1.setColors(colors);
        ib2 = new IconButton(p5, "VER REGISTRO", (p5.width*3/5)-150, 100, 200, 125, list);
        ib2.setColors(colors);
        ib3 = new IconButton(p5, "ESTADÍSTICAS", (p5.width*4/5)-150, 100, 200, 125, stat);
        ib3.setColors(colors);
        ib4 = new IconButton(p5, "INFORMACIÓN DE PECES", (p5.width*5/5)-150, 100, 200, 125, info);
        ib4.setColors(colors);
        this.homeB = new IconButton(p5, "", (p5.width/5)-150, 100, 200, 125, home);

        t1 = new TextField(p5, "Notas adicionales:", p5.width/2+50, 700, 500, 90, fonts.getFontAt(2));
        t2 = new TextField(p5, "Ubicación:",p5.width/2+50, 500, 250, 50, fonts.getFirstFont());
        t3 = new TextField(p5, "Fecha:",p5.width/2+350, 500, 200, 50, fonts.getFirstFont());
        t4 = new TextField(p5, "Señuelo:",p5.width/2+50, 600, 500, 50, fonts.getFirstFont());
        tl1 = new TextList(p5, "Especie:",species, p5.width/2+50, 300, 500, 50);


        peso = new Counter(p5, "Peso (kg): ", mes, menys,p5.width/2+50, 400, 100, 50);
        peso.setInitialValue(0);
        peso.setValues(0,10000);
        peso.setStepValue(0.5f);
        tamano = new Counter(p5, "Tamaño (cm): ", mes, menys,p5.width/2+50+300, 400, 100, 50);
        tamano.setInitialValue(0);
        tamano.setValues(0,10000);
        tamano.setStepValue(0.5f);

        registro = new PagedTable(6, 4);
        registro.setHeaders(registroHeaders);
        registro.setData(this.capturas);
        registro.setColumnWidths(colWidths);

        previousPage = new Button(p5, "<", p5.width/2-40,775, 30, 30 );
        previousPage.setBlues(colors);
        nextPage = new Button(p5, ">", p5.width/2+10,775, 30, 30 );
        nextPage.setBlues(colors);


        c1 = new Card(p5, "Pez más pesado", 200, 200, 300, 500);

        cp1 = new CalendariPlus(p5,p5.width/2, 500,350,275);
        bCal = new Button(p5, "  /  /  ", p5.width/2+350, 500, 200, 50);
        bCal.setDateButtonColors(p5, colors);




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


        ib1.display(p5, pantallaActual==PANTALLA.REGISTRAR_CAPTURA);
        ib2.display(p5, pantallaActual==PANTALLA.VER_REGISTRO);
        ib3.display(p5, pantallaActual==PANTALLA.ESTADISTICAS);
        ib4.display(p5, pantallaActual==PANTALLA.INFO);
        homeB.display(p5, pantallaActual==PANTALLA.INICIO);




    }

    public void dibujaTextFieldRegistrar(PApplet p5){
        t1.display(p5);
        t2.display(p5);


        // Rectangle
        p5.fill(255); p5.rect(p5.width/2+350, 500, 200, 50, 5);

        // Text amb data seleccionada
        p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(24);
        //p5.text(dataCalendari, p5.width/2+350, 500);

        p5.pushStyle();
        p5.fill(colors.getAzure());
        p5.textSize(24); p5.textAlign(p5.LEFT, p5.BOTTOM);p5.textFont(fonts.getFirstFont());
        p5.text("Fecha: ", p5.width/2+355, 500-10);
        p5.popStyle();

        bCal.display(p5);
        t4.display(p5);
        cp1.display(p5);
    }



    public void dibujaPantallaInicio(PApplet p5){

        p5.background(255);
        dibujaLogo(p5);
        dibujaBotonesInicio(p5);

    }

    public void dibujaPantallaRegistrarCaptura(PApplet p5){
        p5.background(255);

        dibujaBotonesTopBar(p5);
        peso.display(p5);
        tamano.display(p5);
        tl1.display(p5);
        p5.fill(colors.getAzure()); p5.textFont(bebasNeue); p5.textSize(50); p5.textAlign(p5.CENTER);
        p5.text("REGISTRAR CAPTURA", p5.width/2, 225);
        registrar.display(p5);
        dibujaTextFieldRegistrar(p5);


    }

    public void dibujaPantallaVerRegistro(PApplet p5){
        p5.background(255);
        dibujaBotonesTopBar(p5);
        registro.display(p5, 220, 250, tableW, tableH);
        nextPage.display(p5);
        previousPage.display(p5);
        p5.fill(colors.getAzure()); p5.textFont(bebasNeue); p5.textSize(50); p5.textAlign(p5.CENTER);
        p5.text("MIS CAPTURAS", p5.width/2, 225);

    }



    public void dibujaLogo(PApplet p5){
        p5.imageMode(p5.CENTER);
        p5.image(logo, p5.width/2, 250);
    }



}
