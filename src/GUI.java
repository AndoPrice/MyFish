import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PShape;

/**
 * Clase principal encargada de gestionar toda la interfaz gráfica de la aplicación.
 * Controla la navegación entre pantallas, la interacción del usuario y la visualización
 * de datos como capturas, especies y estadísticas.
 */
public class GUI {

    /** Gestión de colores y fuentes. */
    Colors colors;
    Fonts fonts;

    /** Recursos gráficos. */
    PImage logo, background;

    /** Fuente principal. */
    PFont bebasNeue;

    /** Base de datos de la aplicación. */
    DataBase dataBase;

    /** Botones principales de la interfaz. */
    Button iniciar, registrarUsuario, cerrarSesion, b1, b2, b3, b4;
    Button nextPage, previousPage, bCal, registrar, nextPage2, previousPage2, volver;

    /** Botones de acciones sobre capturas. */
    Button editCapturaBtn, deleteCapturaBtn, volverVerCapturaBtn;

    /** Botones con iconos. */
    IconButton ib1, ib2, ib3, ib4, homeB;

    /** Estado de edición de captura. */
    public boolean enModoEdicion = false;

    /** Captura actualmente seleccionada. */
    public Catch capturaActiva = null;

    /** Tarjetas de estadísticas. */
    Card longCard, heavyCard, mostCommonCard, avgWeightCard, avgLengthCard;

    /** Componentes de entrada y visualización. */
    TextList tlEspecie;
    TextBox tb1, tb2, tb3, tb4, tb5;
    TextField usuario, contrasena, tNotas, tUbicacion, tFecha, tSenuelo;

    /** Contadores de peso y tamaño. */
    Counter peso, tamano;

    /** Tablas de datos. */
    PagedTable registro, infoPeces;

    String[] registroHeaders = {"FECHA", "ESPECIE", "PESO (Kg)", "TAMAÑO (cm)", "ACCIONES"};
    float tableW = 1000, tableH = 500;
    float[] colWidths = {22, 22, 22, 22, 12};
    /** Datos de especies y capturas. */
    Especie[] especies;
    Catch[] capturas;

    /** Calendario. */
    CalendariPlus cp1;
    String dataCalendari = "";

    /** Información de capturas en formato tabla. */
    String[][] infoCapturas;

    /**
     * Enumeración de pantallas disponibles en la aplicación.
     */
    public enum PANTALLA {
        INICIAR,
        INICIO,
        REGISTRAR_CAPTURA,
        VER_REGISTRO,
        VER_CAPTURA,
        ESTADISTICAS,
        INFO,
        ESPECIE
    }

    /** Pantalla actual mostrada. */
    public PANTALLA pantallaActual;

    /**
     * Constructor principal de la GUI.
     *
     * Inicializa todos los componentes gráficos, carga datos desde la base de datos
     * y configura las pantallas iniciales.
     *
     * @param p5 instancia de Processing
     * @param db base de datos
     * @param logo imagen del logo
     * @param add icono añadir
     * @param list icono lista
     * @param stat icono estadísticas
     * @param info icono información
     * @param home icono home
     * @param mes icono sumar
     * @param menys icono restar
     * @param background imagen de fondo
     */
    public GUI(PApplet p5, DataBase db, PImage logo, PShape add, PShape list, PShape stat, PShape info, PImage home, PImage mes, PImage menys, PImage background){
        colors = new Colors(p5);

        this.background = background;

        this. dataBase = db;

        this.bebasNeue = p5.createFont("data/Fonts/BebasNeue-Regular.ttf", 26);
        fonts = new Fonts(p5);

        iniciar = new Button(p5, "LOGIN", p5.width/2-210, 700, 200, 80);
        iniciar.setBlues(colors);
        registrarUsuario = new Button(p5, "REGISTRAR", p5.width/2+10, 700, 200, 80);
        registrarUsuario.setBlues(colors);
        usuario = new TextField(p5, "Usuario", p5.width/2-150, 450, 300, 50, fonts.getFontAt(2));
        contrasena = new TextField(p5, "Contraseña", p5.width/2-150, 575, 300, 50, fonts.getFontAt(2));

        String[][] infoEspecies = dataBase.getInfoTodasEspecies();
        especies = new Especie[infoEspecies.length];
        for(int e=0; e<especies.length; e++){
            especies[e] = new Especie (infoEspecies[e][0],infoEspecies[e][1], infoEspecies[e][2], infoEspecies[e][3], infoEspecies[e][4], infoEspecies[e][5], infoEspecies[e][6], infoEspecies[e][7]);
            if (especies[e].nombreImagen != null) {
                String fullPath = especies[e].nombreImagen.startsWith("/") ? especies[e].nombreImagen : "/Users/andoprice/Documents/MyFishImages/Especies/" + especies[e].nombreImagen;
                especies[e].foto = p5.loadImage(fullPath);
            }
        }

        infoCapturas = dataBase.getCapturasUsuario("lian");
        if(infoCapturas.length>0) {
            capturas = new Catch[infoCapturas.length];
            for (int c = 0; c < capturas.length; c++) {
                capturas[c] = new Catch(Integer.parseInt(infoCapturas[c][7]), searchSpecies(especies, infoCapturas[c][0]), Float.parseFloat(infoCapturas[c][1]), Float.parseFloat(infoCapturas[c][2]), infoCapturas[c][3], infoCapturas[c][4], infoCapturas[c][5], infoCapturas[c][6], infoCapturas[c][8]);
            }
        }



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

        editCapturaBtn = new Button(p5, "EDITAR", p5.width/2+330, 800, 100, 50);
        editCapturaBtn.setBlues(colors);

        deleteCapturaBtn = new Button(p5, "ELIMINAR", p5.width/2+450, 800, 100, 50);
        deleteCapturaBtn.setReds(colors);

        volverVerCapturaBtn = new Button(p5, "VOLVER", p5.width/2+210, 800, 100, 50);
        volverVerCapturaBtn.setGreys(colors);





        ib1 = new IconButton(p5, "REGISTRAR CAPTURA", (p5.width*2/5)-150, 100, 200, 125, add);
        ib1.setColors(colors);
        ib2 = new IconButton(p5, "VER REGISTRO", (p5.width*3/5)-150, 100, 200, 125, list);
        ib2.setColors(colors);
        ib3 = new IconButton(p5, "ESTADÍSTICAS", (p5.width*4/5)-150, 100, 200, 125, stat);
        ib3.setColors(colors);
        ib4 = new IconButton(p5, "INFORMACIÓN DE PECES", (p5.width*5/5)-150, 100, 200, 125, info);
        ib4.setColors(colors);
        this.homeB = new IconButton(p5, "", (p5.width/5)-150, 100, 200, 125, home);

        tNotas = new TextField(p5, "Notas adicionales:", p5.width/2+50, 700, 500, 90, fonts.getFontAt(2));
        tUbicacion = new TextField(p5, "Ubicación:",p5.width/2+50, 500, 250, 50, fonts.getFirstFont());
        tFecha = new TextField(p5, "Fecha:",p5.width/2+350, 500, 200, 50, fonts.getFirstFont());
        tSenuelo = new TextField(p5, "Señuelo:",p5.width/2+50, 600, 500, 50, fonts.getFirstFont());
        tlEspecie = new TextList(p5, "Especie:",especies, p5.width/2+50, 300, 500, 50);

        tb1 = new TextBox(p5, "Descripción:", p5.width/2+50, 300, 500, 100, fonts.getFontAt(2));
        tb2 = new TextBox(p5, "Ubicación:",p5.width/2+50, 450, 500, 80, fonts.getFontAt(2));
        tb3 = new TextBox(p5, "Más Información:",p5.width/2+50, 580, 500, 50, fonts.getFontAt(2));
        tb4 = new TextBox(p5, "Comportamiento:",p5.width/2+50, 680, 500, 50, fonts.getFontAt(2));
        tb5 = new TextBox(p5, "Talla mínima:",p5.width/2+50, 780, 500, 50, fonts.getFontAt(2));

        volver = new Button(p5, "VOLVER", p5.width/2+475, 805, 100, 50);
        volver.setGreys(colors);

        cerrarSesion = new Button(p5, "CERRAR SESION", p5.width - 250, 40, 200, 50);
        cerrarSesion.setGreys(colors);


        peso = new Counter(p5, "Peso (kg): ", mes, menys,p5.width/2+50, 400, 100, 50);
        peso.setInitialValue(0);
        peso.setValues(0,10000);
        peso.setStepValue(0.5f);
        tamano = new Counter(p5, "Tamaño (cm): ", mes, menys,p5.width/2+50+300, 400, 100, 50);
        tamano.setInitialValue(0);
        tamano.setValues(0,10000);
        tamano.setStepValue(0.5f);

        if(this.capturas!=null) {
            registro = new PagedTable(PagedTable.TableMode.GRID, 6, 5);
            registro.setHeaders(registroHeaders);
            registro.setGridData(catchesToTableData(this.capturas));
            registro.setColumnWidths(colWidths);

            Catch longest = CatchStats.longestCatch(capturas);
            longCard = new Card(
                    Card.CardType.CATCH,
                    "Captura más larga",
                    longest.ubicacion,
                    longest.fecha,
                    longest.especie.nombreComun,
                    "Longitud: " + longest.tamano + " cm"
            );
            if (longest.getNombreImagen() != null) {
                String fullPath = longest.getNombreImagen().startsWith("/") ? longest.getNombreImagen() : "/Users/andoprice/Documents/MyFishImages/Capturas/" + longest.getNombreImagen();
                longCard.setImage(p5.loadImage(fullPath));
            }
            Catch heavy = CatchStats.heaviestCatch(capturas);
            heavyCard = new Card(
                    Card.CardType.CATCH,
                    "Captura más pesada",
                    heavy.ubicacion,
                    heavy.fecha,
                    heavy.especie.nombreComun,
                    "Peso: " + heavy.peso + " kg"
            );
            if (heavy.getNombreImagen() != null) {
                String fullPath = heavy.getNombreImagen().startsWith("/") ? heavy.getNombreImagen() : "/Users/andoprice/Documents/MyFishImages/Capturas/" + heavy.getNombreImagen();
                heavyCard.setImage(p5.loadImage(fullPath));
            }

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
            if (especie.foto != null) {
                mostCommonCard.setImage(especie.foto);
            }
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


    }

    /**
     * Actualiza las capturas del usuario desde la base de datos
     * y recalcula las estadísticas.
     *
     * @param p5 instancia de Processing
     */
    public void updateCaptuarasUsuario (PApplet p5){
        registro = null;
        infoCapturas = dataBase.getCapturasUsuario(usuario.getText());
        if(infoCapturas.length>0) {
            capturas = new Catch[infoCapturas.length];
            for (int c = 0; c < capturas.length; c++) {
                capturas[c] = new Catch(Integer.parseInt(infoCapturas[c][7]), searchSpecies(especies, infoCapturas[c][0]), Float.parseFloat(infoCapturas[c][1]), Float.parseFloat(infoCapturas[c][2]), infoCapturas[c][3], infoCapturas[c][4], infoCapturas[c][5], infoCapturas[c][6], infoCapturas[c][8]);
            }
            registro = new PagedTable(PagedTable.TableMode.GRID, 6, 5);
            registro.setHeaders(registroHeaders);
            registro.setGridData(catchesToTableData(this.capturas));
            registro.setColumnWidths(colWidths);

            Catch longest = CatchStats.longestCatch(capturas);
            longCard = new Card(
                    Card.CardType.CATCH,
                    "Captura más larga",
                    longest.ubicacion,
                    longest.fecha,
                    longest.especie.nombreComun,
                    "Longitud: " + longest.tamano + " cm"
            );
            if (longest.getNombreImagen() != null) {
                String fullPath = longest.getNombreImagen().startsWith("/") ? longest.getNombreImagen() : "/Users/andoprice/Documents/MyFishImages/Capturas/" + longest.getNombreImagen();
                longCard.setImage(p5.loadImage(fullPath));
            }
            Catch heavy = CatchStats.heaviestCatch(capturas);
            heavyCard = new Card(
                    Card.CardType.CATCH,
                    "Captura más pesada",
                    heavy.ubicacion,
                    heavy.fecha,
                    heavy.especie.nombreComun,
                    "Peso: " + heavy.peso + " kg"
            );
            if (heavy.getNombreImagen() != null) {
                String fullPath = heavy.getNombreImagen().startsWith("/") ? heavy.getNombreImagen() : "/Users/andoprice/Documents/MyFishImages/Capturas/" + heavy.getNombreImagen();
                heavyCard.setImage(p5.loadImage(fullPath));
            }
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
            if (especie.foto != null) {
                mostCommonCard.setImage(especie.foto);
            }
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


    }

    /**
     * Dibuja los botones principales de la pantalla de inicio.
     *
     * @param p5 instancia de Processing
     */
    public void dibujaBotonesInicio(PApplet p5){
        b1.display(p5);
        b2.display(p5);
        b3.display(p5);
        b4.display(p5);
    }

    /**
     * Dibuja la barra superior de navegación.
     *
     * @param p5 instancia de Processing
     */
    public void dibujaBotonesTopBar(PApplet p5){

        ib1.display(p5, pantallaActual==PANTALLA.REGISTRAR_CAPTURA);
        ib2.display(p5, pantallaActual==PANTALLA.VER_REGISTRO);
        ib3.display(p5, pantallaActual==PANTALLA.ESTADISTICAS);
        ib4.display(p5, pantallaActual==PANTALLA.INFO||pantallaActual==PANTALLA.ESPECIE);
        homeB.display(p5, pantallaActual==PANTALLA.INICIO);

    }

    /**
     * Dibuja los campos de entrada para registrar una captura.
     *
     * @param p5 instancia de Processing
     */
    public void dibujaTextFieldRegistrar(PApplet p5){
        tNotas.display(p5);
        tUbicacion.display(p5);

        p5.fill(255); p5.rect(p5.width/2+350, 500, 200, 50, 5);

        p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(24);

        p5.pushStyle();
        p5.fill(colors.getAzure());
        p5.textSize(24); p5.textAlign(p5.LEFT, p5.BOTTOM);p5.textFont(fonts.getFirstFont());
        p5.text("Fecha: ", p5.width/2+355, 500-10);
        p5.popStyle();

        bCal.display(p5);
        tSenuelo.display(p5);
        cp1.display(p5);
    }

    /**
     * Dibuja la pantalla de inicio de sesión.
     *
     * @param p5 instancia de Processing
     */
    public void dibujaPantallaIniciar(PApplet p5){
        p5.imageMode(p5.CORNER);
        p5.image(background, 0, 0);
        dibujaLogo(p5);
        usuario.display(p5);
        contrasena.display(p5);
        iniciar.display(p5);
        registrarUsuario.display(p5);

    }



    /**
     * Dibuja la pantalla principal tras iniciar sesión.
     *
     * @param p5 instancia de Processing
     */
    public void dibujaPantallaInicio(PApplet p5){

        p5.background(255);
        p5.imageMode(p5.CORNER);
        p5.image(background, 0, 0);

        dibujaLogo(p5);
        dibujaBotonesInicio(p5);
        dibujaInfoUsuario(p5);

    }

    /**
     * Muestra información del usuario activo.
     *
     * @param p5 instancia de Processing
     */
    public void dibujaInfoUsuario(PApplet p5){
        p5.pushStyle();
        p5.fill(colors.getAzure());
        p5.textAlign(p5.RIGHT);
        p5.textSize(24);
        p5.textFont(fonts.getFirstFont());
        p5.text("Usuario: " + usuario.getText(), p5.width - 270, 75);
        cerrarSesion.display(p5);
        p5.popStyle();
    }

    /**
     * Dibuja la pantalla de registro de capturas.
     *
     * @param p5 instancia de Processing
     */
    public void dibujaPantallaRegistrarCaptura(PApplet p5){
        p5.background(255);
        p5.imageMode(p5.CORNER);
        p5.image(background, 0, 0);

        p5.fill(255, 200);
        p5.rect(p5.width/2+25, 225, 550, 580, 12);

        dibujaBotonesTopBar(p5);
        peso.display(p5);
        tamano.display(p5);
        tlEspecie.display(p5);
        p5.fill(colors.getAzure()); p5.textFont(bebasNeue); p5.textSize(50); p5.textAlign(p5.CENTER);
        p5.text("REGISTRAR CAPTURA", p5.width/2, 225);
        registrar.display(p5);
        dibujaTextFieldRegistrar(p5);

    }

    /**
     * Dibuja la pantalla de listado de capturas.
     *
     * @param p5 instancia de Processing
     */
    public void dibujaPantallaVerRegistro(PApplet p5){
        p5.background(255);
        p5.imageMode(p5.CORNER);
        p5.image(background, 0, 0);
        dibujaBotonesTopBar(p5);
        if(registro!=null) {
            registro.display(p5, 220, 250, tableW, tableH);
            nextPage.display(p5);
            previousPage.display(p5);
        }
        else {
            p5.fill(colors.getAzure()); p5.textFont(bebasNeue); p5.textSize(30); p5.textAlign(p5.CENTER);
            p5.text("SIN CAPTURAS", p5.width/2, 400);
        }
        p5.fill(colors.getAzure()); p5.textFont(bebasNeue); p5.textSize(50); p5.textAlign(p5.CENTER);
        p5.text("MIS CAPTURAS", p5.width/2, 225);

    }

    /**
     * Dibuja la pantalla de visualización/edición de una captura.
     *
     * @param p5 instancia de Processing
     */
    public void dibujaPantallaVerCaptura(PApplet p5){
        p5.background(255);
        p5.imageMode(p5.CORNER);
        p5.image(background, 0, 0);

        p5.fill(255, 200);
        p5.rect(p5.width/2+25, 225, 550, 580, 12);

        dibujaBotonesTopBar(p5);
        peso.display(p5);
        tamano.display(p5);
        tlEspecie.display(p5);
        p5.fill(colors.getAzure()); p5.textFont(bebasNeue); p5.textSize(50); p5.textAlign(p5.CENTER);
        p5.text(enModoEdicion ? "EDITAR CAPTURA" : "VER CAPTURA", p5.width/2, 225);

        volverVerCapturaBtn.display(p5);
        editCapturaBtn.setTextBoto(enModoEdicion ? "GUARDAR" : "EDITAR");
        editCapturaBtn.display(p5);
        deleteCapturaBtn.display(p5);
        dibujaTextFieldRegistrar(p5);


    }

    /**
     * Dibuja la pantalla de estadísticas.
     *
     * @param p5 instancia de Processing
     */

    public void dibujaPantallaEstadisticas(PApplet p5){

        p5.background(255);
        p5.imageMode(p5.CORNER);
        p5.image(background, 0, 0);
        dibujaBotonesTopBar(p5);
        p5.fill(colors.getAzure()); p5.textFont(bebasNeue); p5.textSize(50); p5.textAlign(p5.CENTER);
        p5.text("ESTADÍSTICAS", p5.width/2, 225);
        if(registro!=null) {
            heavyCard.display(p5, false);
            longCard.display(p5, false);
            mostCommonCard.display(p5, false);
            avgWeightCard.display(p5, false);
            avgLengthCard.display(p5, false);
        }
        else{
            p5.fill(colors.getAzure()); p5.textFont(bebasNeue); p5.textSize(30); p5.textAlign(p5.CENTER);
            p5.text("SIN CAPTURAS", p5.width/2, 400);
        }

    }

    /**
     * Dibuja la pantalla de información de especies.
     *
     * @param p5 instancia de Processing
     */
    public void dibujaPantallaInformacion(PApplet p5){
        p5.background(255);
        p5.imageMode(p5.CORNER);
        p5.image(background, 0, 0);
        dibujaBotonesTopBar(p5);
        p5.fill(colors.getAzure()); p5.textFont(bebasNeue); p5.textSize(50); p5.textAlign(p5.CENTER);
        p5.text("INFORMACIÓN DE PECES", p5.width/2, 225);
        float contentW = p5.width * 0.75f;
        float contentX = (p5.width - contentW) / 2;
        infoPeces.displayList(p5, contentX, 300, contentW, p5.height-450);
        nextPage2.display(p5);
        previousPage2.display(p5);
    }

    /**
     * Dibuja la pantalla detallada de una especie.
     *
     * @param p5 instancia de Processing
     * @param e especie a mostrar
     */
    public void dibujaPantallaEspecie(PApplet p5, Especie e) {
        p5.background(255);
        p5.imageMode(p5.CORNER);
        p5.image(background, 0, 0);
        dibujaBotonesTopBar(p5);
        p5.rectMode(p5.CORNER);

        if (e.foto != null) {
            p5.imageMode(p5.CORNER);
            p5.image(e.foto, 40, 225, p5.width/2 - 60, 580);
        } else {
            p5.fill(220);
            p5.rect(40, 225, p5.width/2 - 60, 580, 12);
            p5.fill(100); p5.textAlign(p5.CENTER, p5.CENTER); p5.textSize(20);
            p5.text("SIN FOTO", 40 + (p5.width/2 - 60)/2.0f, 225 + 580/2.0f);
        }

        p5.fill(255, 200);
        p5.rect(p5.width/2+25, 225, 550, 580, 12);
        p5.fill(colors.getAzure());
        p5.textFont(bebasNeue);
        p5.textSize(50);
        p5.textAlign(p5.CENTER);
        p5.text(e.nombreComun +" ("+e.nombreCientifico +")", p5.width / 2, 225-20);
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


    /**
     * Dibuja el logo en pantalla.
     *
     * @param p5 instancia de Processing
     */
    public void dibujaLogo(PApplet p5){
        p5.imageMode(p5.CENTER);
        p5.image(logo, p5.width/2, 250);
    }

    /**
     * Carga una captura en el modo de visualización/edición.
     *
     * Funcionamiento:
     * - Copia los datos de la captura a los componentes gráficos.
     * - Actualiza fecha, especie, notas, etc.
     *
     * @param c captura a cargar
     */
    public void cargarCaptura(Catch c) {
        capturaActiva = c;
        enModoEdicion = false;

        peso.value = c.peso;
        tamano.value = c.tamano;

        tUbicacion.text = c.ubicacion != null ? c.ubicacion : "";
        tNotas.text = c.notas != null ? c.notas : "";
        tSenuelo.text = c.senuelo != null ? c.senuelo : "";

        if (c.fecha != null && c.fecha.contains("-")) {
            String[] parts = c.fecha.split("-");
            if (parts.length == 3) {
                int any = Integer.parseInt(parts[0]);
                int mes = Integer.parseInt(parts[1]);
                int dia = Integer.parseInt(parts[2]);
                cp1.dia = dia;
                cp1.mes = mes;
                cp1.any = any;
                cp1.selectedDay = dia;
                cp1.selectedMonth = mes;
                cp1.selectedYear = any;
                cp1.dateSelected = true;
                dataCalendari = dia + "/" + mes + "/" + any;
                bCal.setTextBoto(dataCalendari);
            }
        }

        tlEspecie.selectedValue = c.especie.nombreComun;
        tlEspecie.getTextField().text = c.especie.nombreComun;
    }

    /**
     * Busca una especie por nombre.
     *
     * @param especies lista de especies
     * @param name nombre a buscar
     * @return especie encontrada o null
     */
    public Especie searchSpecies(Especie[] especies, String name){
        for (int i=0; i<especies.length; i++){
            if(especies[i].nombreComun.equals(name)){
                return especies[i];
            }

        }
        return null;
    }

    /**
     * Convierte las capturas en datos para tabla.
     *
     * @param capturas array de capturas
     * @return matriz de Strings con los datos
     */
    public String[][] catchesToTableData(Catch[] capturas) {
        String[][] data = new String[capturas.length][5];

        for (int i = 0; i < capturas.length; i++) {
            Catch c = capturas[i];

            data[i][0] = c.fecha;
            data[i][1] = c.especie.nombreComun;
            data[i][2] = String.valueOf(c.peso);
            data[i][3] = String.valueOf(c.tamano);
            data[i][4] = "";
        }

        return data;
    }
}
