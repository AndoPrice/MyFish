import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

import java.io.File;

public class MyFish extends PApplet {

    public static DataBase db;

    GUI gui;
    public PImage background, logo, home, mes, menys;
    PShape add, list, stat, info;

    PImage uploadImage;
    Button uploadB;
    String titol = "";
    Especie clicked;


    Fonts fontsApp;

    boolean loginOK=true;

    public static void main(String[] args) {
        PApplet.main("MyFish");
    }

    public void settings(){
        fullScreen();
        smooth(10);
    }

    public void setup(){

        db = new DataBase("admin", "12345", "myfish");
        db.connect();


        logo = loadImage("data/MyFish-Logo (1).png");
        home = loadImage("data/Icons/MyFish-Logo Home.png");
        mes = loadImage("data/Icons/mes.png");
        menys = loadImage("data/Icons/menys.png");
        add  = loadShape("data/Icons/plus-solid.svg");
        list = loadShape("data/Icons/list-ul-solid.svg");
        stat = loadShape("data/Icons/chart-bar-regular.svg");
        info = loadShape("data/Icons/info-solid.svg");
        background = loadImage("data/MyFishBackground lowquality.jpg");

        float scaleFactor = 0.1f;
        add.scale(scaleFactor);
        list.scale(scaleFactor);
        stat.scale(scaleFactor);
        info.scale(scaleFactor);

        fontsApp = new Fonts(this);
        noStroke();
        textAlign(CENTER); textSize(18);
        gui = new GUI(this, db, logo, add, list, stat, info, home, mes, menys, background);//passar a array

        setShapeColor(add, gui.colors.getAzure());
        setShapeColor(list, gui.colors.getAzure());
        setShapeColor(stat, gui.colors.getAzure());
        setShapeColor(info, gui.colors.getAzure());
        //setShapeColor(logoI, gui.colors.getAzure());

        uploadB = new Button(this, "IMAGEN", 150, 800, 100, 50);
        uploadB.setBlues(gui.colors);


    }

    public void draw(){
        background(255);

        switch(gui.pantallaActual){

            case INICIAR:   gui.dibujaPantallaIniciar(this);
                            if(loginOK==false){
                                this.textAlign(CENTER);
                                this.fill(210, 43, 43);
                                this.textFont(fontsApp.getFirstFont());
                                this.text("Usuario o contraseña incorrectos", this.width/2, 850);
                            }
                break;

            case INICIO:   gui.dibujaPantallaInicio(this);
                break;

            case REGISTRAR_CAPTURA:     gui.dibujaPantallaRegistrarCaptura(this);
                break;

            case VER_REGISTRO:     gui.dibujaPantallaVerRegistro(this);
                break;

            case VER_CAPTURA:     gui.dibujaPantallaVerCaptura(this);
                break;

            case ESTADISTICAS:     gui.dibujaPantallaEstadisticas(this);
                break;

            case INFO:     gui.dibujaPantallaInformacion(this);
                break;

            case ESPECIE:     gui.dibujaPantallaEspecie(this, clicked);
                break;


        }
        updateCursor(this);
        if(gui.pantallaActual== GUI.PANTALLA.REGISTRAR_CAPTURA || gui.pantallaActual == GUI.PANTALLA.VER_CAPTURA){
            if(uploadImage!=null){
                imageMode(CORNER);
                image(uploadImage, 150, 275, 520, 520);
                textSize(34); textAlign(RIGHT);
                fill(0);
            }
            else{
                fill(200);
                rect(150, 275, 520, 520);
                textSize(34); textAlign(RIGHT);
                if (gui.pantallaActual == GUI.PANTALLA.VER_CAPTURA) {
                    fill(gui.colors.getAzure());
                    textSize(24);
                    textAlign(CENTER, CENTER);
                    text("SIN IMAGEN", 150 + 260, 275 + 260);
                }
            }

            if (gui.pantallaActual == GUI.PANTALLA.REGISTRAR_CAPTURA || (gui.pantallaActual == GUI.PANTALLA.VER_CAPTURA && gui.enModoEdicion)) {
                uploadB.display(this);
            }
        }

    }

    public void fileSelected(File selection) {
        if (selection == null) {
            println("No s'ha seleccionat cap fitxer.");
        } else {

            // Obtenim la ruta del fitxer seleccionat
            String rutaImatge = selection.getAbsolutePath();

            uploadImage = loadImage(rutaImatge);  // Actualitzam imatge
            titol = rutaImatge;  // Actualitzam títol
        }
    }

    public void keyPressed(){

        gui.usuario.keyPressed(key);
        gui.contrasena.keyPressed(key);

        gui.tNotas.keyPressed(key);
        gui.tUbicacion.keyPressed(key);
        gui.tFecha.keyPressed(key);
        gui.tSenuelo.keyPressed(key);

        if(gui.tlEspecie.getTextField().mouseOverTextField(this)){
            gui.tlEspecie.getTextField().keyPressed(key, keyCode);
            gui.tlEspecie.update(this);
        }
    }

    public void keyTyped(){
        gui.usuario.keyTyped(key);
        gui.contrasena.keyTyped(key);

        gui.tNotas.keyTyped(key);
        gui.tUbicacion.keyTyped(key);
        gui.tFecha.keyTyped(key);
        gui.tSenuelo.keyTyped(key);


    }

    public void mousePressedPantallaINICIAR(){
        gui.usuario.isPressed(this);
        gui.contrasena.isPressed(this);
        if(gui.iniciar.mouseOverButton(this)){
            String id = gui.usuario.getText();
            String contrasena = gui.contrasena.getText();
            if(db.loginCorrecte(id, contrasena)){
                loginOK = true;
                gui.pantallaActual = GUI.PANTALLA.INICIO;
                gui.updateCaptuarasUsuario(this);
                println("iniciar");
            }
            else{
                loginOK = false;
            }
        }
        else if(gui.registrarUsuario.mouseOverButton(this)){
            String id = gui.usuario.getText();
            String contrasena = gui.contrasena.getText();
            if(!id.isEmpty() && !contrasena.isEmpty()){
                if(db.insertUsuario(id, contrasena)){
                    loginOK = true;
                    gui.pantallaActual = GUI.PANTALLA.INICIO;
                    gui.updateCaptuarasUsuario(this);
                } else {
                    loginOK = false;
                }
            }
        }
    }

    public void resetFormularioCaptura() {
        gui.tNotas.text = "";
        gui.tUbicacion.text = "";
        gui.tSenuelo.text = "";
        gui.tFecha.text = "";
        gui.tlEspecie.selectedValue = "";
        gui.tlEspecie.getTextField().text = "";
        gui.peso.value = 0;
        gui.tamano.value = 0;
        gui.peso.setInitialValue(0);
        gui.tamano.setInitialValue(0);
        gui.dataCalendari = "";
        gui.bCal.setTextBoto("  /  /  ");
        uploadImage = null;
        titol = "";
    }

    public void mousePressed(){
        if(gui.pantallaActual==GUI.PANTALLA.INICIAR){
            mousePressedPantallaINICIAR();
        }

        else if(gui.pantallaActual==GUI.PANTALLA.INICIO) {
            if (gui.b1.mouseOverButton(this)) {
                println(" B1 has been pressed!!! ");
                resetFormularioCaptura();
                gui.pantallaActual = GUI.PANTALLA.REGISTRAR_CAPTURA;
            }
            else if (gui.b2.mouseOverButton(this)) {
                println(" B2 has been pressed!!! ");
                gui.pantallaActual = GUI.PANTALLA.VER_REGISTRO;
            }
            else if (gui.b3.mouseOverButton(this)) {
                println(" B3 has been pressed!!! ");
                gui.pantallaActual = GUI.PANTALLA.ESTADISTICAS;
            }
            else if (gui.b4.mouseOverButton(this)) {
                println(" B4 has been pressed!!! ");
                gui.pantallaActual = GUI.PANTALLA.INFO;
            }
            else if (gui.cerrarSesion.mouseOverButton(this)) {
                gui.usuario.setText("");
                gui.contrasena.setText("");
                gui.pantallaActual = GUI.PANTALLA.INICIAR;
            }
        }


        else if(gui.pantallaActual==GUI.PANTALLA.REGISTRAR_CAPTURA) {

            gui.tNotas.isPressed(this);
            gui.tUbicacion.isPressed(this);
            gui.tFecha.isPressed(this);
            gui.tSenuelo.isPressed(this);



            gui.tlEspecie.getTextField().isPressed(this);
            gui.tlEspecie.buttonPressed(this);

            if (gui.tamano.mouseOverButtonMes(this)) {
                gui.tamano.increment();
            }
            else if (gui.tamano.mouseOverButtonMenys(this)) {
                gui.tamano.decrement();
            }

            else if (gui.peso.mouseOverButtonMes(this)) {
                gui.peso.increment();
            }
            else if (gui.peso.mouseOverButtonMenys(this)) {
                gui.peso.decrement();
            }
            if(uploadB.mouseOverButton(this)){
                // Obrim el dialeg
                selectInput("Selecciona una imatge ...", "fileSelected");
            }

            else if(gui.registrar.mouseOverButton(this)){
                db.insertCaptura(gui.peso.value, gui.tamano.value, gui.tUbicacion.getText(), gui.cp1.selectedDay, gui.cp1.selectedMonth, gui.cp1.selectedYear, gui.tSenuelo.getText(),gui.tNotas.getText(), gui.tlEspecie.selectedValue, gui.usuario.getText());
                if(uploadImage!=null && !titol.isEmpty()) {
                    db.insertImagen(titol);
                }
                gui.updateCaptuarasUsuario(this);
                resetFormularioCaptura();


                gui.pantallaActual=GUI.PANTALLA.VER_REGISTRO;
            }
            // Comprovar si clicam sobre botons del Calendari
            gui.cp1.checkButtons(this);

            // Si pitja el botó, canvia la visibilitat del calendari.
            if(gui.bCal.mouseOverButton(this)&&gui.bCal.isEnabled()){
                gui.cp1.toggleVisibility();
            }
            // Si pitjam el botó de Next, canviarà al seguent mes
            if(gui.cp1.bNext.mouseOverButton(this)){
                gui.cp1.nextMonth();
            }
            // Si pitjam el botó de Prev, canviarà al mes anterior
            if(gui.cp1.bPrev.mouseOverButton(this)){
                gui.cp1.prevMonth();
            }
            // Si pitjam el botó de OK, confirmarà la data seleccionada i amagarà el calendari
            if(gui.cp1.bOK.mouseOverButton(this) && gui.cp1.isDateSelected()){
                gui.dataCalendari = gui.cp1.getSelectedDate();
                gui.cp1.setVisible(false);
                gui.bCal.setTextBoto(gui.dataCalendari);
            }

        }

        else if(gui.pantallaActual== GUI.PANTALLA.VER_REGISTRO){
            if(gui.registro != null) {
                if (gui.nextPage.mouseOverButton(this)) {
                    gui.registro.nextPage();
                }
                if (gui.previousPage.mouseOverButton(this)) {
                    gui.registro.prevPage();
                }
                int editIndex = gui.registro.handleEditGridClick(this);
                if (editIndex != -1 && gui.capturas != null && editIndex < gui.capturas.length) {
                    gui.cargarCaptura(gui.capturas[editIndex]);
                    String imgName = db.getImagenPorCapturaId(gui.capturas[editIndex].id);
                    if (imgName != null && !imgName.isEmpty()) {
                        File file = new File(imgName);
                        if (file.exists()) {
                            uploadImage = loadImage(imgName);
                            titol = imgName;
                        } else if (new File(dataPath(imgName)).exists()) {
                            uploadImage = loadImage(imgName);
                            titol = imgName;
                        } else if (new File("/Users/andoprice/Documents/MyFishImages/Capturas/" + imgName).exists()) {
                            System.out.println("Image found!");
                            uploadImage = loadImage("/Users/andoprice/Documents/MyFishImages/Capturas/" + imgName);
                            titol = imgName;
                        } else {
                            // File not found explicitly, avoid attempting to load it
                            uploadImage = null;
                            titol = "";
                        }
                    } else {
                        uploadImage = null;
                        titol = "";
                    }
                    gui.pantallaActual = GUI.PANTALLA.VER_CAPTURA;
                    return;
                }
            }

        }
        else if(gui.pantallaActual== GUI.PANTALLA.VER_CAPTURA){
            if (gui.volverVerCapturaBtn.mouseOverButton(this)) {
                gui.pantallaActual = GUI.PANTALLA.VER_REGISTRO;
            }
            else if (gui.deleteCapturaBtn.mouseOverButton(this)) {
                db.deleteCaptura(gui.capturaActiva.id);
                gui.updateCaptuarasUsuario(this);
                gui.pantallaActual = GUI.PANTALLA.VER_REGISTRO;
            }
            else if (gui.editCapturaBtn.mouseOverButton(this)) {
                if (gui.enModoEdicion) {
                    db.updateCaptura(gui.capturaActiva.id, gui.peso.value, gui.tamano.value, gui.tUbicacion.getText(), gui.cp1.selectedDay, gui.cp1.selectedMonth, gui.cp1.selectedYear, gui.tSenuelo.getText(), gui.tNotas.getText(), gui.tlEspecie.selectedValue);
                    if (titol != null && !titol.isEmpty()) {
                        db.updateImagenCaptura(gui.capturaActiva.id, titol);
                    }
                    gui.updateCaptuarasUsuario(this);
                    gui.enModoEdicion = false;
                    for(Catch c : gui.capturas) {
                        if(c.id == gui.capturaActiva.id) {
                            gui.cargarCaptura(c);
                            break;
                        }
                    }
                } else {
                    gui.enModoEdicion = true;
                }
            }

            if (gui.enModoEdicion) {
                gui.tNotas.isPressed(this);
                gui.tUbicacion.isPressed(this);
                gui.tFecha.isPressed(this);
                gui.tSenuelo.isPressed(this);

                gui.tlEspecie.getTextField().isPressed(this);
                gui.tlEspecie.buttonPressed(this);

                if (gui.tamano.mouseOverButtonMes(this)) {
                    gui.tamano.increment();
                } else if (gui.tamano.mouseOverButtonMenys(this)) {
                    gui.tamano.decrement();
                } else if (gui.peso.mouseOverButtonMes(this)) {
                    gui.peso.increment();
                } else if (gui.peso.mouseOverButtonMenys(this)) {
                    gui.peso.decrement();
                }

                gui.cp1.checkButtons(this);
                if (gui.bCal.mouseOverButton(this) && gui.bCal.isEnabled()) {
                    gui.cp1.toggleVisibility();
                }
                if (gui.cp1.bNext.mouseOverButton(this)) {
                    gui.cp1.nextMonth();
                }
                if (gui.cp1.bPrev.mouseOverButton(this)) {
                    gui.cp1.prevMonth();
                }
                if (gui.cp1.bOK.mouseOverButton(this) && gui.cp1.isDateSelected()) {
                    gui.dataCalendari = gui.cp1.getSelectedDate();
                    gui.cp1.setVisible(false);
                    gui.bCal.setTextBoto(gui.dataCalendari);
                }

                if (uploadB.mouseOverButton(this)) {
                    selectInput("Selecciona una imatge ...", "fileSelected");
                }

//                int editIndex = gui.registro.handleEditGridClick(this);
//                if (editIndex != -1 && editIndex < gui.capturas.length) {
//                    gui.cargarCaptura(gui.capturas[editIndex]);
//                    gui.pantallaActual = GUI.PANTALLA.VER_CAPTURA;
//                }
            }

        }


        else if(gui.pantallaActual== GUI.PANTALLA.INFO){
            if(gui.nextPage2.mouseOverButton(this)){
                gui.infoPeces.nextPage();
                System.out.println("next page");
            }
            if(gui.previousPage2.mouseOverButton(this)) {
                gui.infoPeces.prevPage();
            }
            clicked = gui.infoPeces.handleClick(this);
            if (clicked != null) {
                gui.pantallaActual = GUI.PANTALLA.ESPECIE;
            }

        }
        else if(gui.pantallaActual == GUI.PANTALLA.ESPECIE){
            if(gui.volver.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.INFO;
            }
        }


        if(gui.pantallaActual==GUI.PANTALLA.REGISTRAR_CAPTURA||gui.pantallaActual==GUI.PANTALLA.VER_REGISTRO||
                gui.pantallaActual==GUI.PANTALLA.ESTADISTICAS||gui.pantallaActual==GUI.PANTALLA.INFO||gui.pantallaActual==GUI.PANTALLA.ESPECIE||gui.pantallaActual==GUI.PANTALLA.VER_CAPTURA){
            if (gui.homeB.mouseOverButton(this)) {
                gui.pantallaActual = GUI.PANTALLA.INICIO;
            }

            else if (gui.ib1.mouseOverButton(this)) {
                resetFormularioCaptura();
                gui.pantallaActual = GUI.PANTALLA.REGISTRAR_CAPTURA;
            }

            else if (gui.ib2.mouseOverButton(this)) {
                gui.pantallaActual = GUI.PANTALLA.VER_REGISTRO;
            }
            else if (gui.ib3.mouseOverButton(this)) {
                gui.pantallaActual = GUI.PANTALLA.ESTADISTICAS;
            }
            else if (gui.ib4.mouseOverButton(this)) {
                gui.pantallaActual = GUI.PANTALLA.INFO;
            }

            gui.tlEspecie.getTextField().isPressed(this);
            gui.tlEspecie.buttonPressed(this);
        }



    }

    public void updateCursor(PApplet p5){
        boolean overButton = false;
        boolean overText = false;

        switch(gui.pantallaActual){
            case INICIAR:
                if (gui.iniciar.mouseOverButton(p5) || gui.registrarUsuario.mouseOverButton(p5)) overButton = true;
                if (gui.usuario.mouseOverTextField(p5) || gui.contrasena.mouseOverTextField(p5)) overText = true;
                break;
            case INICIO:
                if (gui.b1.mouseOverButton(p5) || gui.b2.mouseOverButton(p5) || gui.b3.mouseOverButton(p5) || gui.b4.mouseOverButton(p5) || gui.cerrarSesion.mouseOverButton(p5)) overButton = true;
                break;
            case REGISTRAR_CAPTURA:
                if (gui.registrar.mouseOverButton(p5) || uploadB.mouseOverButton(p5)) overButton = true;
                if (gui.bCal.mouseOverButton(p5) || gui.peso.mouseOverButtonMes(p5) || gui.peso.mouseOverButtonMenys(p5) || gui.tamano.mouseOverButtonMes(p5) || gui.tamano.mouseOverButtonMenys(p5)) overButton = true;
                if (gui.tlEspecie.getTextField().mouseOverTextField(p5)) overText = true;
                if (gui.tNotas.mouseOverTextField(p5) || gui.tUbicacion.mouseOverTextField(p5) || gui.tFecha.mouseOverTextField(p5) || gui.tSenuelo.mouseOverTextField(p5)) overText = true;
                if (gui.cp1.visible) {
                    if (gui.cp1.bNext.mouseOverButton(p5) || gui.cp1.bPrev.mouseOverButton(p5) || gui.cp1.bOK.mouseOverButton(p5)) overButton = true;
                    for (int i=0; i < gui.cp1.buttons.length; i++) {
                        if (gui.cp1.buttons[i] != null && gui.cp1.buttons[i].enabled && gui.cp1.buttons[i].mouseOver(p5)) overButton = true;
                    }
                }
                break;
            case VER_REGISTRO:
                if (gui.nextPage.mouseOverButton(p5) || gui.previousPage.mouseOverButton(p5)) overButton = true;
                if (gui.registro != null && gui.registro.mouseOverGridButtons(p5)) overButton = true;
                break;
            case VER_CAPTURA:
                if (gui.editCapturaBtn.mouseOverButton(p5) || gui.deleteCapturaBtn.mouseOverButton(p5) || gui.volverVerCapturaBtn.mouseOverButton(p5)) overButton = true;
                if (gui.enModoEdicion) {
                    if (gui.bCal.mouseOverButton(p5) || gui.peso.mouseOverButtonMes(p5) || gui.peso.mouseOverButtonMenys(p5) || gui.tamano.mouseOverButtonMes(p5) || gui.tamano.mouseOverButtonMenys(p5) || uploadB.mouseOverButton(p5)) overButton = true;
                    if (gui.tlEspecie.getTextField().mouseOverTextField(p5)) overText = true;
                    if (gui.tNotas.mouseOverTextField(p5) || gui.tUbicacion.mouseOverTextField(p5) || gui.tFecha.mouseOverTextField(p5) || gui.tSenuelo.mouseOverTextField(p5)) overText = true;
                    if (gui.cp1.visible) {
                        if (gui.cp1.bNext.mouseOverButton(p5) || gui.cp1.bPrev.mouseOverButton(p5) || gui.cp1.bOK.mouseOverButton(p5)) overButton = true;
                        for (int i=0; i < gui.cp1.buttons.length; i++) {
                            if (gui.cp1.buttons[i] != null && gui.cp1.buttons[i].enabled && gui.cp1.buttons[i].mouseOver(p5)) overButton = true;
                        }
                    }
                }
                break;
            case INFO:
                if (gui.nextPage2.mouseOverButton(p5) || gui.previousPage2.mouseOverButton(p5)) overButton = true;
                if (gui.infoPeces != null && gui.infoPeces.mouseOverListButtons(p5)) overButton = true;
                break;
            case ESPECIE:
                if (gui.volver.mouseOverButton(p5)) overButton = true;
                break;
            case ESTADISTICAS:
                break;
        }

        if (gui.pantallaActual != GUI.PANTALLA.INICIO && gui.pantallaActual != GUI.PANTALLA.INICIAR) {
            if (gui.homeB.mouseOverButton(p5) || gui.ib1.mouseOverButton(p5) || gui.ib2.mouseOverButton(p5) || gui.ib3.mouseOverButton(p5) || gui.ib4.mouseOverButton(p5)) {
                overButton = true;
            }
        }

        if (overButton) cursor(HAND);
        else if (overText) cursor(TEXT);
        else cursor(ARROW);
    }

    void setShapeColor(PShape shape, int c) {
        shape.disableStyle();
        shape.setFill(c);
        shape.setStroke(false);

        for (int i = 0; i < shape.getChildCount(); i++) {
            PShape child = shape.getChild(i);
            if (child != null) {
                child.disableStyle();
                child.setFill(c);
                child.setStroke(false);
            }
        }
    }



}
