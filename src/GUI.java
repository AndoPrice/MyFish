import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PShape;

public class GUI {

    Colors colors;
    PImage logo;
    PFont bebasNeue;

    Fonts fonts;

    Button iniciar, b1, b2, b3, b4, nextPage, previousPage, bCal, registrar, nextPage2, previousPage2, volver;

    IconButton ib1, ib2, ib3, ib4, homeB;

    DayButton db1;

    Card longCard, heavyCard, mostCommonCard, avgWeightCard, avgLengthCard;

    String [] species = {"Barracuda", "Llampuga", "Palometón", "Bacoreta"};

    TextList tl1;
    TextBox  tb1, tb2, tb3, tb4, tb5;

    TextField usuario, contrasena, t1, t2, t3, t4;

    Counter peso, tamano;

    PagedTable registro, infoPeces;
    String[] registroHeaders = {"FECHA", "ESPECIE", "PESO (Kg)", "TAMAÑO (cm)"};
    float tableW = 1000, tableH = 500;
    float[] colWidths = {25, 25, 25, 25};
    Especie[] especies = {
            new Especie(
                    "Llampuga",
                    "Coryphaena hippurus",
                    "Cuerpo muy alargado, destaca por su gran cabeza y frente redondeada. Color azul y amarillento, aletas pectorales negras. Puede alcanzar alrededor de 100 cm de longitud.",
                    "Frecuenta la superficie marina entre 5 y 10 metros de profundidad. Aunque a veces se acerca a la costa, es más común encontrarla en alta mar.",
                    "Su alimentación es muy variada: peces, zooplancton, crustáceos y calamares.",
                    "Forma bancos y es una especie migratoria.",
                    "N/C"
            ),
            new Especie(
                    "Lubina",
                    "Dicentrarchus labrax",
                    "Cuerpo alargado con morro pronunciado y elevado. Color gris plateado. En edad adulta mide entre 60 y 90 cm. Cola ligeramente dividida.",
                    "Cerca de la costa, sobre bancos de arena, zonas portuarias o rocosas, desde la superficie hasta los 30 metros de profundidad.",
                    "Depredador de crustáceos, gusanos marinos y peces pequeños. Se reproduce en invierno.",
                    "Nada constantemente sin detenerse en el fondo.",
                    "25 cm"
            ),
            new Especie(
                    "Bacoreta",
                    "Euthynnus alletteratus",
                    "Cuerpo alargado y sin escamas en forma de proyectil, muy similar al atún rojo. Dorso azulado que tiende al plateado, aleta caudal en forma de media luna y manchas oscuras en el vientre. Puede superar el metro de longitud.",
                    "Especie pelágica que se concentra en aguas cálidas de la costa. Durante la caza suele asociarse con gaviotas u otras aves marinas.",
                    "Depredador de pequeños peces como anchoas o sardinas. Ocasionalmente se alimenta de crustáceos o calamares.",
                    "Forma cardúmenes en aguas costeras y habita desde la superficie hasta los 150 metros de profundidad.",
                    "N/C"
            ),
            new Especie(
                    "Espetón",
                    "Sphyraena sphyraena",
                    "Cuerpo muy alargado y fino en forma de aguja, con gran boca puntiaguda. Color gris azulado con bandas verticales oscuras en el lomo. Puede alcanzar hasta 1 metro de longitud.",
                    "Habita el litoral entre 5 y 100 metros de profundidad, sobre fondos arenosos o formaciones rocosas sumergidas.",
                    "Depredador que se alimenta de peces, pulpos y camarones.",
                    "Suele formar bancos y nadar cerca de la superficie para cazar, utilizando una visión aérea para sorprender a sus presas.",
                    "N/C"
            ),
            new Especie(
                    "Pez Limón, Verderol, Serviola",
                    "Seriola Dumerili",
                    "Cuerpo ovalado y alargado, de color más pálido en el vientre y verdoso-amarillento en la mayor parte del cuerpo o gris oseáceo y a veces el reflejo de la luz hace que produzca reflejos brillantes, puede llegar a medir hasta 2 metros.",
                    "Dependiendo de la estación varían su localización, mientras que en invierno bajan a las profundidades de hasta 70m, es en verano donde se acerca a costas rocosas y se hace más visible debido a que sube a la superficie.",
                    "Es un cazador activo de pequeños  peces, crustáceos o cefalópodos.",
                    "Forman bancos y suelen ser muy curiosos, nadan a media profundidad y de forma constante, no muy apegados a las rocas.",
                    "35 cm en época de veda (islas baleares)."
            ),
            new Especie(
                    "Palometón",
                    "Lichia Amia",
                    "Cuerpo alargado y ciertamente comprimido, tiene una característica línea lateral serpenteante, tiene color verdoso o gris, su aleta caudal está bifurcada, pueden llegar a medir hasta 2 metros de longitud, aunque su tallaje normal ronda los 50cm – 1 metro.",
                    "Vive por la costa y en hasta 50m de profundidad y sobre fondos arenosos, en verano frecuentan la costa a pocos centímetros de la superficie.",
                    "Se alimentan pequeños  peces y crustáceos.",
                    "Son muy rápidos y tienen una gran vista.",
                    "60cm"
            )
    };
    Catch[] capturas = {
            new Catch(searchSpecies(especies, "Espetón"), 2, 60, "Sa Coma","20/10/25", "Popper", "Nada"),
            new Catch(searchSpecies(especies, "Espetón"), 1, 45, "Cala Millor","28/10/25", "Minnow", "Lucha dura"),
            new Catch(searchSpecies(especies, "Espetón"), 2, 60, "Sa Coma","2/11/25", "Paseante", "Nada"),
            new Catch(searchSpecies(especies, "Bacoreta"), 0.5f, 34, "Alcúdia","20/11/25", "Popper", ""),
            new Catch(searchSpecies(especies, "Bacoreta"), 0.8f, 40, "Porto Cristo","23/11/25", "Spotter", "Nada"),
            new Catch(searchSpecies(especies, "Llampuga"), 3, 55, "Sa Coma","26/11/25", "Bombeta", ""),
            new Catch(searchSpecies(especies, "Llampuga"), 4, 62, "Sa Coma","3/11/25", "Paseante", "PR"),
            new Catch(searchSpecies(especies, "Lubina"), 0.7f, 32, "Alcúdia","4/12/25", "Paseante", ""),



    };
    Card c1;
    CalendariPlus cp1;
    String dataCalendari = "";

    public enum PANTALLA {INICIAR, INICIO, REGISTRAR_CAPTURA, VER_REGISTRO, VER_CAPTURA, DETALLES, ESTADISTICAS, INFO, ESPECIE};


    public PANTALLA pantallaActual;

    // Constructor de la GUI
    public GUI(PApplet p5, PImage logo, PShape add, PShape list, PShape stat, PShape info, PImage home, PImage mes, PImage menys){
        colors = new Colors(p5);

        this.bebasNeue = p5.createFont("data/Fonts/BebasNeue-Regular.ttf", 26);
        fonts = new Fonts(p5);

        iniciar = new Button(p5, "INICIAR", p5.width/2-100, 700, 200, 80);
        iniciar.setBlues(colors);
        usuario = new TextField(p5, "Usuario", p5.width/2-150, 450, 300, 50, fonts.getFontAt(2));
        contrasena = new TextField(p5, "Contrasena", p5.width/2-150, 575, 300, 50, fonts.getFontAt(2));



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
        tl1 = new TextList(p5, "Especie:",especies, p5.width/2+50, 300, 500, 50);

        tb1 = new TextBox(p5, "Descripción:", p5.width/2+50, 300, 500, 100, fonts.getFontAt(2));
        tb2 = new TextBox(p5, "Ubicación:",p5.width/2+50, 450, 500, 80, fonts.getFontAt(2));
        tb3 = new TextBox(p5, "Más Información:",p5.width/2+50, 580, 500, 50, fonts.getFontAt(2));
        tb4 = new TextBox(p5, "Comportamiento:",p5.width/2+50, 680, 500, 50, fonts.getFontAt(2));
        tb5 = new TextBox(p5, "Talla mínima:",p5.width/2+50, 780, 500, 50, fonts.getFontAt(2));

        volver = new Button(p5, "VOLVER", p5.width/2+450, 800, 100, 50);
        volver.setGreys(colors);


        peso = new Counter(p5, "Peso (kg): ", mes, menys,p5.width/2+50, 400, 100, 50);
        peso.setInitialValue(0);
        peso.setValues(0,10000);
        peso.setStepValue(0.5f);
        tamano = new Counter(p5, "Tamaño (cm): ", mes, menys,p5.width/2+50+300, 400, 100, 50);
        tamano.setInitialValue(0);
        tamano.setValues(0,10000);
        tamano.setStepValue(0.5f);

        registro = new PagedTable(PagedTable.TableMode.GRID, 6, 4);
        registro.setHeaders(registroHeaders);
        registro.setGridData(catchesToTableData(this.capturas));
        registro.setColumnWidths(colWidths);

        infoPeces = new PagedTable(PagedTable.TableMode.LIST,4, 0);
        infoPeces.setData(especies);

        previousPage2 = new Button(p5, "<", p5.width/2-40,775, 30, 30 );
        previousPage2.setBlues(colors);
        nextPage2 = new Button(p5, ">", p5.width/2+10,775, 30, 30 );
        nextPage2.setBlues(colors);



        previousPage = new Button(p5, "<", p5.width/2-40,775, 30, 30 );
        previousPage.setBlues(colors);
        nextPage = new Button(p5, ">", p5.width/2+10,775, 30, 30 );
        nextPage.setBlues(colors);




        cp1 = new CalendariPlus(p5,p5.width/2, 500,350,275);
        bCal = new Button(p5, "  /  /  ", p5.width/2+350, 500, 200, 50);
        bCal.setDateButtonColors(p5, colors);




        pantallaActual = PANTALLA.INICIAR;
        this.logo = logo;

        Catch longest = CatchStats.longestCatch(capturas);
        longCard = new Card(
                Card.CardType.CATCH,
                "Captura más larga",
                longest.ubicacion,
                longest.fecha,
                longest.especie.nombreComun,
                "Longitud: " + longest.tamano + " cm"
        );
        Catch heavy = CatchStats.heaviestCatch(capturas);
        heavyCard = new Card(
                Card.CardType.CATCH,
                "Captura más pesada",
                heavy.ubicacion,
                heavy.fecha,
                heavy.especie.nombreComun,
                "Peso: " + heavy.peso + " kg"
        );
        Object[] result = CatchStats.commonSpecies(capturas);
        Especie especie = (Especie) result[0];
        int times = (int) result[1];
        mostCommonCard = new Card(
                Card.CardType.SPECIES,
                "Especie más común",
                "",
                "",
                especie.nombreComun,
                "Número de capturas: "+times
        );
        float avgW = CatchStats.averageWeight(capturas);
        avgWeightCard = new Card(
                Card.CardType.AVERAGE,
                "Peso promedio",
                "",
                "",
                "",
                 p5.nf(avgW, 1, 2) + " kg"
        );

        float avgL = CatchStats.averageLength(capturas);
        avgLengthCard = new Card(
                Card.CardType.AVERAGE,
                "Tamaño promedio",
                "",
                "",
                "",
                p5.nf(avgL, 1, 1) + " cm"
        );

        float gap = 30;
        float marginX = 40;

        float bigCardW = (p5.width - marginX*2 - gap) / 2;
        float bigCardH = p5.height*0.28f;

        float mediumCardW = bigCardW;
        float smallCardW = (bigCardW - gap) / 2;
        float bottomCardH = p5.height * 0.22f;

        float topY = 275;

        heavyCard.setDimensions(marginX, topY, bigCardW, bigCardH,20);
        longCard.setDimensions(marginX + bigCardW + gap, topY, bigCardW, bigCardH, 20);

        float bottomY = topY + bigCardH + gap+20;

        mostCommonCard.setDimensions(marginX, bottomY, mediumCardW, bottomCardH, 20);
        avgWeightCard.setDimensions(marginX + mediumCardW + gap, bottomY, smallCardW, bottomCardH, 20);
        avgLengthCard.setDimensions(marginX + mediumCardW + gap + smallCardW + gap, bottomY, smallCardW, bottomCardH, 20);

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
        ib4.display(p5, pantallaActual==PANTALLA.INFO||pantallaActual==PANTALLA.ESPECIE);
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

    public void dibujaPantallaIniciar(PApplet p5){
        dibujaLogo(p5);
        usuario.display(p5);
        contrasena.display(p5);
        iniciar.display(p5);
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
    public void dibujaPantallaEstadisticas(PApplet p5){
        p5.background(255);
        dibujaBotonesTopBar(p5);
        p5.fill(colors.getAzure()); p5.textFont(bebasNeue); p5.textSize(50); p5.textAlign(p5.CENTER);
        p5.text("ESTADÍSTICAS", p5.width/2, 225);
        heavyCard.display(p5, false);
        longCard.display(p5, false);

        mostCommonCard.display(p5, false);
        avgWeightCard.display(p5, false);
        avgLengthCard.display(p5, false);

    }

    public void dibujaPantallaInformacion(PApplet p5){
        p5.background(255);
        dibujaBotonesTopBar(p5);
        p5.fill(colors.getAzure()); p5.textFont(bebasNeue); p5.textSize(50); p5.textAlign(p5.CENTER);
        p5.text("INFORMACIÓN DE PECES", p5.width/2, 225);
        float contentW = p5.width * 0.75f;
        float contentX = (p5.width - contentW) / 2;
        infoPeces.displayList(p5, contentX, 300, contentW, p5.height-450);
        nextPage2.display(p5);
        previousPage2.display(p5);
    }

    public void dibujaPantallaEspecie(PApplet p5, Especie e) {
        p5.background(255);
        dibujaBotonesTopBar(p5);
        p5.fill(colors.getAzure());
        p5.textFont(bebasNeue);
        p5.textSize(50);
        p5.textAlign(p5.CENTER);
        p5.text(e.nombreComun +" ("+e.nombreCientifico +")", p5.width / 2, 225);
        tb1.setText(e.descripcion);
        tb2.setText(e.ubicacion);
        tb3.setText(e.masInfo);
        tb4.setText(e.comportamiento);
        tb5.setText(e.tallaMin);
        tb1.display(p5);
        tb2.display(p5);
        tb3.display(p5);
        tb4.display(p5);
        tb5.display(p5);
        volver.display(p5);

    }


    public void dibujaLogo(PApplet p5){
        p5.imageMode(p5.CENTER);
        p5.image(logo, p5.width/2, 250);
    }

    public Especie searchSpecies(Especie[] especies, String name){
        for (int i=0; i<especies.length; i++){
            if(especies[i].nombreComun ==name){
                return especies[i];
            }

        }
        return null;
    }

    public String[][] catchesToTableData(Catch[] capturas) {
        String[][] data = new String[capturas.length][4];

        for (int i = 0; i < capturas.length; i++) {
            Catch c = capturas[i];

            data[i][0] = c.fecha;
            data[i][1] = c.especie.nombreComun;
            data[i][2] = String.valueOf(c.peso);
            data[i][3] = String.valueOf(c.tamano);
        }

        return data;
    }



}
