import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Clase que implementa una lista de texto con autocompletado.
 * Permite buscar elementos (Especie) mediante un campo de texto y
 * muestra coincidencias en forma de botones seleccionables.
 */

public class TextList {
    /** Posición y dimensiones del componente. */
    float x, y, w, h;

    /** Lista de especies disponibles para búsqueda. */
    Especie[] especies;

    /** Campo de texto para introducir la búsqueda. */
    TextField textField;

    /** Identificador seleccionado. */
    String selectedId;

    /** Valor seleccionado (nombre de la especie). */
    String selectedValue;

    /** Indica si el componente está activo. */
    boolean enabled;

    /** Número de coincidencias encontradas. */
    int numMatchs = 0;

    /** Lista de botones generados dinámicamente con las coincidencias. */
    ArrayList<Button> buttons;

    /** Gestión de colores. */
    Colors colors;

    /** Gestión de fuentes. */
    Fonts fonts;

    /**
     * Constructor de la clase.
     *
     * @param p5 instancia de Processing
     * @param name nombre del campo de texto
     * @param especies lista de especies disponibles
     * @param x posición horizontal
     * @param y posición vertical
     * @param w anchura
     * @param h altura
     */
    public TextList(PApplet p5, String name, Especie[] especies, float x, float y, float w, float h) {

        colors = new Colors(p5);
        fonts = new Fonts(p5);

        this.especies = especies;
        this.selectedId = "";
        this.selectedValue = "";
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;

        this.textField = new TextField(p5, name, (int)x, (int)y, (int)w, (int)h, fonts.getFirstFont());
        this.buttons = new ArrayList<Button>();
    }

    /**
     * Dibuja el campo de texto y los botones de coincidencias.
     *
     * @param p5 instancia de Processing
     */

    public void display(PApplet p5) {
        p5.pushStyle();
        textField.display(p5);

        for(Button b : buttons){
            b.display(p5);
        }
        p5.popStyle();
    }

    /**
     * Obtiene el valor seleccionado.
     *
     * @return texto seleccionado
     */

    public String getSelectedValue(){
        return this.selectedValue;
    }

    /**
     * Obtiene el campo de texto asociado.
     *
     * @return objeto TextField
     */

    public TextField getTextField(){
        return  this.textField;
    }

    /**
     * Actualiza la lista de coincidencias según el texto introducido.
     *
     * Funcionamiento detallado:
     * - Obtiene el texto actual del TextField.
     * - Reinicia el número de coincidencias y la lista de botones.
     * - Si el texto no está vacío:
     *   - Recorre todas las especies.
     *   - Comprueba si el nombre común empieza por el texto introducido (sin distinguir mayúsculas).
     *   - Por cada coincidencia:
     *       - Crea un botón con el nombre de la especie.
     *       - Lo posiciona dinámicamente debajo del campo de texto.
     *       - Lo añade a la lista.
     *   - Limita el número máximo de resultados a 5.
     *
     * @param p5 instancia de Processing
     */
    public void update(PApplet p5) {

        String searchFor = this.textField.text;
        System.out.println("SEARCH FOR: "+searchFor);

        this.numMatchs = 0;
        this.buttons = new ArrayList<Button>();

        if (searchFor.length() > 0) {
            for (int i=0; i<especies.length; i++) {
                if (especies[i].nombreComun.toLowerCase().startsWith(searchFor.toLowerCase())) {
                    float espai = 10;
                    Button b = new Button(p5, especies[i].nombreComun, x, y + h + 10 + (h + espai)*numMatchs, w, h);
                    b.setBlues(colors);
                    buttons.add(b);
                    this.numMatchs++;
                    if (this.numMatchs==5) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Comprueba si el ratón está sobre algún botón.
     *
     * @param p5 instancia de Processing
     * @return true si el ratón está sobre algún botón
     */
    public boolean mouseOverButtons(PApplet p5){
        for(Button b : buttons){
            if(b.mouseOverButton(p5)){
                return true;
            }
        }
        return false;
    }

    /**
     * Gestiona la pulsación de botones.
     *
     * Funcionamiento:
     * - Recorre todos los botones.
     * - Si el ratón está sobre uno:
     *   - Copia su texto al TextField.
     *   - Actualiza el valor seleccionado.
     * - Si se ha seleccionado alguno, limpia la lista de botones.
     *
     * @param p5 instancia de Processing
     */
    public void buttonPressed(PApplet p5){
        boolean pressed = false;
        for(Button b : buttons){
            if(b.mouseOverButton(p5)){
                textField.text = b.textBoto;
                this.selectedValue = b.textBoto;
                pressed = true;
            }
        }
        if(pressed){
            buttons.clear();
        }
    }

}
