import processing.core.PApplet;

/**
 * Clase que extiende {@link Calendari} añadiendo funcionalidad de interacción
 * mediante botones y control de visibilidad.
 * Permite navegar entre fechas (siguiente/anterior) y confirmar una selección.
 */

public class CalendariPlus extends Calendari {
    /** Botón para avanzar al siguiente periodo (mes/año). */
    public Button bNext;

    /** Botón para retroceder al periodo anterior. */
    public Button bPrev;

    /** Botón para confirmar la selección de fecha. */
    public Button bOK;

    /** Indica si el calendario es visible en pantalla. */
    boolean visible = false;

    /**
     * Constructor de la clase.
     * Inicializa el calendario y crea los botones asociados.
     *
     * @param p5 instancia principal de Processing
     * @param x posición horizontal del calendario
     * @param y posición vertical del calendario
     * @param w anchura del calendario
     * @param h altura del calendario
     */


    public CalendariPlus(PApplet p5, int x, int y, int w, int h) {

        super(x, y, w, h);

        bNext = new Button(p5, "Siguiente", x+ w/3, y -70, 100, 50);
        bPrev = new Button(p5, "Anterior", x+w/3+100, y - 70, 100, 50);
        bOK   = new Button(p5, "OK", x+w/3+200, y - 70, 50, 50);
    }

    /**
     * Cambia el estado de visibilidad del calendario.
     * Si está visible lo oculta, y viceversa.
     */

    public void toggleVisibility(){
        this.visible = !this.visible;
    }

    /**
     * Establece explícitamente la visibilidad del calendario.
     *
     * @param b true para mostrarlo, false para ocultarlo
     */

    public void setVisible(boolean b){
        this.visible = b;
    }

    /**
     * Dibuja el calendario en pantalla si está visible.
     *
     * Funcionamiento:
     * - Comprueba si el calendario está visible.
     * - Dibuja un fondo rectangular.
     * - Llama al método display de la clase padre para renderizar el calendario base.
     * - Si hay una fecha seleccionada, la muestra como texto.
     * - Dibuja los botones de navegación y confirmación.
     *
     * @param p5 instancia de Processing para renderizado
     */
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


            bNext.display(p5);
            bPrev.display(p5);
            bOK.display(p5);
            p5.popStyle();
        }

    }
}
