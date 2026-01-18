import processing.core.PApplet;

import static java.lang.Math.ceil;

public class PagedTable {
    enum TableMode {GRID, LIST}
    TableMode mode = TableMode.GRID;

    String[] tableHeaders;   // Títols de les columnes
    Object[] data;
    String[][] gridData;
    // Dades de la taula
    float[] columnWidths;    // Amplades de les columnes

    int numCols, numRows;  // Número de files i columnes

    int numPage;
    int numTotalPages;

    public int selectedIndex = -1;


    // Constructor
    public PagedTable(TableMode mode, int nr, int nc){
        this.mode = mode;
        this.numRows = nr;
        this.numCols = nc;
        this.numPage = 0;
    }

    // Setters

    public void setMode(TableMode mode){
        this.mode=mode;
    }

    public void setHeaders(String[] h){
        this.tableHeaders = h;
    }

    public void setData(Object[] data){
        this.data = data;

        if(data.length % numRows == 0){
            numTotalPages = (data.length / numRows) - 1;
        } else {
            numTotalPages = data.length / numRows;
        }
    }
    public void setGridData(String[][] d){
        this.gridData = d;

        if(d.length % (numRows - 1) == 0){
            numTotalPages = (d.length / (numRows - 1)) - 1;
        } else {
            numTotalPages = (d.length / (numRows - 1));
        }
    }

    /*public void setValueAt(String value, int nr, int nc){
        this.tableData[nr][nc] = value;
    }*/

    public void setColumnWidths(float[] w){
        this.columnWidths = w;
    }

    public void nextPage(){
        if(this.numPage<this.numTotalPages){
            this.numPage++;
        }
    }

    public void prevPage(){
        if(this.numPage>0){
            this.numPage--;
        }
    }

    public void display(PApplet p5, float x, float y, float w, float h){
        if(mode == TableMode.GRID){
            displayGrid(p5, x, y, w, h);
        }
        else if(mode == TableMode.LIST){
            displayList(p5, x, y, w, h);
        }
    }

    private void displayGrid(PApplet p5, float x, float y, float w, float h){
        p5.pushStyle();

        p5.fill(200, 50); p5.stroke(0); p5.strokeWeight(1);
        p5.rect(x, y, w, h);

        float rowHeight = h / numRows;
        p5.fill(171, 193, 213); p5.stroke(0); p5.strokeWeight(1);
        p5.rect(x, y, w, rowHeight);

        p5.stroke(0);

        p5.pushStyle();

        // Dibuixa files
        p5.stroke(100);

        for(int r = 1; r <numRows; r++){
            if(r==1){ p5.strokeWeight(1); }
            else {    p5.strokeWeight(1); }
            p5.line(x, y + r*rowHeight, x + w, y + r*rowHeight);
        }

        p5.popStyle();

        // Dibuixa Columnes
        float xCol = x;
        for(int c = 0; c<numCols; c++){
            xCol += w*columnWidths[c]/100.0;
            p5.line(xCol, y, xCol, y + h);
        }

        // Dibuixa textos
        p5.fill(20, 93, 160); p5.textSize(24); p5.textAlign(p5.CENTER);
        for(int r = 0; r < numRows; r++){
            xCol = x;
            for(int c = 0; c< numCols; c++){
                if(r==0){
                    p5.text(tableHeaders[c], xCol + 125, y + (r+1)*rowHeight - 10);
                }
                else{
                    int k = (numRows-1)*numPage + (r-1);
                    if(k<gridData.length){
                        p5.text(gridData[k][c], xCol + 125, y + (r+1)*rowHeight - 10);
                    }
                }
                xCol += w*columnWidths[c]/100.0;
            }
        }

        // Informació de la Pàgina
        p5.fill(20, 93, 160);
        p5.text("Pag: "+(this.numPage+1)+" / "+(this.numTotalPages+1), x, y + h + 50);

        p5.popStyle();
    }

    // Dibuixa taula
    public void displayList(PApplet p5, float x, float y, float w, float h){
        p5.pushStyle();

        float rowHeight = h / numRows;

        for(int r = 0; r < numRows; r++){

            int k = numPage * numRows + r;
            if(k >= data.length) break;

            float ry = y + r * rowHeight;

            drawListRow(p5, x, ry, w, rowHeight, data[k], k);
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

    public void drawListRow(
            PApplet p5,
            float x, float y,
            float w, float h,
            Object obj,
            int index
    ){
        Colors c = new Colors(p5);
        float pad = 20;

        // Fondo
        p5.stroke(180);
        p5.fill(255);
        p5.rect(x, y, w, h-6, 12);

        // Imagen
        float imgSize = h - pad*2;
        p5.fill(220);
        p5.rect(x + pad, y + pad, imgSize, imgSize, 8);

        // Texto
        p5.fill(30, 90, 150);
        p5.textAlign(p5.LEFT, p5.CENTER);
        p5.textSize(36);

        Especie s = (Especie) obj;

        p5.text(
                s.commonName + " (" + s.scientificName + ")",
                x + imgSize + pad*2+50,
                y + h/2
        );

        // Botón "VER DETALLES"
        float bw = 150;
        float bh = 50;
        float bx = x + w - bw - pad;
        float by = y + h/2 - bh/2;

        p5.stroke(20, 93, 160);
        p5.noFill();
        Button b = new Button(p5, "VER DETALLES", bx, by, bw, bh);
        b.setBlues(c);
        b.display(p5);


    }

}
