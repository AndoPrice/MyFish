import processing.core.PApplet;
/**
 * Clase que representa una tabla paginada capaz de mostrar datos en dos modos:
 * GRID (tabla con filas y columnas) y LIST (lista de elementos).
 *
 * Permite visualizar grandes cantidades de datos dividiéndolos en páginas,
 * facilitando la navegación mediante botones de siguiente y anterior.
 *
 * Además, gestiona la interacción del usuario con botones dentro de la tabla,
 * como botones de edición o visualización de detalles.
 */
public class PagedTable {
    /**
     * Enumeración que define los modos de visualización de la tabla.
     * GRID: formato tabla con columnas.
     * LIST: formato lista con elementos visuales.
     */
    enum TableMode {GRID, LIST}

    /** Modo actual de visualización */
    TableMode mode = TableMode.GRID;

    /** Cabeceras de la tabla (solo en modo GRID) */
    String[] tableHeaders;

    /** Datos genéricos (usados en modo LIST) */
    Object[] data;

    /** Datos organizados en matriz (usados en modo GRID) */
    String[][] gridData;

    /** Porcentaje de ancho de cada columna */
    float[] columnWidths;

    /** Número de columnas */
    int numCols;

    /** Número de filas visibles por página */
    int numRows;

    /** Página actual */
    int numPage;

    /** Número total de páginas */
    int numTotalPages;

    /** Botones asociados a cada fila en modo LIST */
    private Button[] rowButtons;

    /** Botones de edición en modo GRID */
    private Button[] editButtons;


    /**
     * Constructor de la tabla paginada.
     *
     * @param mode modo inicial de visualización (GRID o LIST)
     * @param nr número de filas por página
     * @param nc número de columnas (solo relevante en GRID)
     */
    public PagedTable(TableMode mode, int nr, int nc){
        this.mode = mode;
        this.numRows = nr;
        this.numCols = nc;
        this.numPage = 0;
    }

    /**
     * Establece el modo de visualización.
     * @param mode nuevo modo (GRID o LIST)
     */
    public void setMode(TableMode mode){
        this.mode=mode;
    }

    /**
     * Define las cabeceras de la tabla.
     * @param h array de Strings con los nombres de las columnas
     */
    public void setHeaders(String[] h){
        this.tableHeaders = h;
    }

    /**
     * Establece los datos para el modo LIST y calcula el número de páginas.
     *
     * @param data array de objetos a mostrar
     */
    public void setData(Object[] data){
        this.data = data;

        if(data.length % numRows == 0){
            numTotalPages = (data.length / numRows) - 1;
        } else {
            numTotalPages = data.length / numRows;
        }
    }
    /**
     * Establece los datos para el modo GRID y calcula el número de páginas.
     *
     * @param d matriz de Strings con los datos
     */
    public void setGridData(String[][] d){
        this.gridData = d;

        if(d.length % (numRows - 1) == 0){
            numTotalPages = (d.length / (numRows - 1)) - 1;
        } else {
            numTotalPages = (d.length / (numRows - 1));
        }
    }


    /**
     * Define el ancho relativo de cada columna.
     * @param w array de porcentajes
     */
    public void setColumnWidths(float[] w){
        this.columnWidths = w;
    }

    /** Avanza a la siguiente página si es posible */
    public void nextPage(){
        if(this.numPage<this.numTotalPages){
            this.numPage++;
        }
    }

    /** Retrocede a la página anterior si es posible */
    public void prevPage(){
        if(this.numPage>0){
            this.numPage--;
        }
    }

    /**
     * Dibuja la tabla en pantalla según el modo actual.
     *
     * @param p5 instancia de PApplet
     * @param x posición X
     * @param y posición Y
     * @param w ancho
     * @param h alto
     */
    public void display(PApplet p5, float x, float y, float w, float h){
        if(mode == TableMode.GRID){
            displayGrid(p5, x, y, w, h);
        }
        else if(mode == TableMode.LIST){
            displayList(p5, x, y, w, h);
        }
    }

    /**
     * Dibuja la tabla en modo GRID.
     *
     * Funcionamiento:
     * - Dibuja el contenedor de la tabla y las cabeceras.
     * - Calcula la altura de cada fila.
     * - Dibuja líneas horizontales y verticales.
     * - Muestra las cabeceras en la primera fila.
     * - Para cada fila de datos:
     *   - Calcula el índice real en función de la página.
     *   - Muestra el contenido de cada celda.
     *   - En una columna específica (c==4) crea un botón "VER".
     * - Muestra el número de página actual.
     */
    private void displayGrid(PApplet p5, float x, float y, float w, float h){
        p5.pushStyle();

        p5.fill(250, 200); p5.stroke(0); p5.strokeWeight(1);
        p5.rect(x, y, w, h, 6);

        float rowHeight = h / numRows;
        p5.fill(171, 193, 213); p5.stroke(0); p5.strokeWeight(1);
        p5.rect(x, y, w, rowHeight, 6);

        p5.stroke(0);

        p5.pushStyle();

        p5.stroke(100);

        for(int r = 1; r <numRows; r++){
            if(r==1){ p5.strokeWeight(1); }
            else {    p5.strokeWeight(1); }
            p5.line(x, y + r*rowHeight, x + w, y + r*rowHeight);
        }

        p5.popStyle();

        float xCol = x;
        for(int c = 0; c<numCols; c++){
            xCol += w*columnWidths[c]/100.0;
            p5.line(xCol, y, xCol, y + h);
        }

        p5.fill(20, 93, 160); p5.textSize(24); p5.textAlign(p5.CENTER, p5.CENTER);
        for(int r = 0; r < numRows; r++){
            xCol = x;
            for(int c = 0; c< numCols; c++){
                float colW = w * columnWidths[c] / 100.0f;
                float textX = xCol + colW / 2;
                float textY = y + r * rowHeight + rowHeight / 2;
                if(r==0){
                    p5.text(tableHeaders[c], textX, textY);
                }
                else{
                    int k = (numRows-1)*numPage + (r-1);
                    if(k<gridData.length){
                        if (c == 4) {
                            float buttonW = 100;
                            float buttonH = rowHeight - 16;

                            float bx = xCol + (colW - buttonW) / 2;
                            float by = y + r * rowHeight + (rowHeight - buttonH) / 2;

                            Button editBtn = new Button(p5, "VER", bx, by, buttonW, buttonH);
                            Colors colors = new Colors(p5);
                            editBtn.setBlues(colors);
                            editBtn.display(p5);

                            if (editButtons == null || editButtons.length != numRows - 1) {
                                editButtons = new Button[numRows - 1];
                            }
                            editButtons[r - 1] = editBtn;;
                        } else {
                            p5.text(gridData[k][c], textX, textY);
                        }
                    }
                }
                xCol += w*columnWidths[c]/100.0;
            }
        }

        p5.fill(20, 93, 160);
        p5.text("Pag: "+(this.numPage+1)+" / "+(this.numTotalPages+1), x+w/2, y + h + 90);

        p5.popStyle();
    }

    /**
     * Dibuja la tabla en modo LIST.
     *
     * Funcionamiento:
     * - Calcula cuántas filas son visibles en la página actual.
     * - Recorre los datos visibles.
     * - Para cada elemento llama a drawListRow().
     * - Muestra la paginación.
     */
    public void displayList(PApplet p5, float x, float y, float w, float h){
        p5.pushStyle();

        float rowHeight = h / numRows;

        int visibleRows = Math.min(numRows, data.length - numPage * numRows);
        rowButtons = new Button[visibleRows];

        for(int r = 0; r < numRows; r++){

            int k = numPage * numRows + r;
            if(k >= data.length) break;

            float ry = y + r * rowHeight;

            drawListRow(p5, x, ry, w, rowHeight, data[k], r);
        }

        p5.fill(20, 93, 160);
        p5.textAlign(p5.LEFT);
        p5.textSize(18);
        p5.text(
                "Pag: " + (numPage + 1) + " / " + (numTotalPages + 1),
                x,
                y + h + 40
        );


        p5.popStyle();
    }

    /**
     * Dibuja una fila individual en modo LIST.
     *
     * Funcionamiento:
     * - Dibuja un rectángulo contenedor.
     * - Muestra una imagen si existe, o un placeholder.
     * - Muestra el nombre común y científico.
     * - Crea un botón "VER DETALLES".
     * - Guarda el botón para detectar clics posteriormente.
     *
     * @param p5 instancia de PApplet
     * @param x posición X
     * @param y posición Y
     * @param w ancho
     * @param h alto
     * @param obj objeto a mostrar (cast a Especie)
     * @param index índice de la fila
     */
    public void drawListRow(PApplet p5, float x, float y, float w, float h, Object obj, int index){
        Colors c = new Colors(p5);
        float pad = 20;

        p5.stroke(180);
        p5.fill(255);
        p5.rect(x, y, w, h-6, 12);

        Especie s = (Especie) obj;

        float imgSize = h - pad*2;
        if(s.getFoto()!=null){
            p5.imageMode(p5.CORNER);
            p5.image(s.getFoto(), x + pad, y + pad, imgSize, imgSize);
        } else {
            p5.fill(220);
            p5.rect(x + pad, y + pad, imgSize, imgSize, 8);
        }

        p5.fill(30, 90, 150);
        p5.textAlign(p5.LEFT, p5.CENTER);
        p5.textSize(36);


        p5.text(
                s.nombreComun + " (" + s.nombreCientifico + ")",
                x + imgSize + pad*2+50,
                y + h/2
        );

        float bw = 150;
        float bh = 50;
        float bx = x + w - bw - pad;
        float by = y + h/2 - bh/2;

        p5.stroke(20, 93, 160);
        p5.noFill();
        Button b = new Button(p5, "VER DETALLES", bx, by, bw, bh);
        b.setBlues(c);
        b.display(p5);

        rowButtons[index] = b;


    }

    /**
     * Gestiona el clic en modo LIST.
     *
     * @param p5 instancia de PApplet
     * @return la especie seleccionada o null si no hay clic
     */
    public Especie handleClick(PApplet p5) {
        if (rowButtons == null) return null;

        for (int r = 0; r < rowButtons.length; r++) {
            Button b = rowButtons[r];
            if (b != null && b.mouseOverButton(p5)) {
                int dataIndex = numPage * numRows + r;
                return (Especie) data[dataIndex];
            }
        }
        return null;
    }

    /**
     * Gestiona el clic en botones de edición en modo GRID.
     *
     * @param p5 instancia de PApplet
     * @return índice del elemento seleccionado o -1 si no hay clic
     */
    public int handleEditGridClick(PApplet p5) {
        if (editButtons == null || mode != TableMode.GRID) return -1;
        for (int r = 0; r < editButtons.length; r++) {
            Button b = editButtons[r];
            if (b != null && b.mouseOverButton(p5)) {
                return (numRows - 1) * numPage + r;
            }
        }
        return -1;
    }


    /**
     * Detecta si el ratón está sobre botones en modo GRID.
     *
     * @param p5 instancia de PApplet
     * @return true si está sobre algún botón
     */
    public boolean mouseOverGridButtons(PApplet p5) {
        if (mode != TableMode.GRID) return false;
        if (editButtons != null) {
            for (Button b : editButtons) {
                if (b != null && b.mouseOverButton(p5)) return true;
            }
        }
        return false;
    }

    /**
     * Detecta si el ratón está sobre botones en modo LIST.
     *
     * @param p5 instancia de PApplet
     * @return true si está sobre algún botón
     */
    public boolean mouseOverListButtons(PApplet p5) {
        if (mode != TableMode.LIST) return false;
        if (rowButtons != null) {
            for (Button b : rowButtons) {
                if (b != null && b.mouseOverButton(p5)) return true;
            }
        }
        return false;
    }

}
