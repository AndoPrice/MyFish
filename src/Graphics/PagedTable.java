package Graphics;

import processing.core.PApplet;

public class PagedTable {
    String[] tableHeaders;   // Títols de les columnes
    String[][] tableData;    // Dades de la taula
    float[] columnWidths;    // Amplades de les columnes

    int numCols, numRows;  // Número de files i columnes

    int numPage;
    int numTotalPages;

    // Constructor
    public PagedTable(int nr, int nc){
        this.numRows = nr;
        this.numCols = nc;
        this.numPage = 0;
    }

    // Setters

    public void setHeaders(String[] h){
        this.tableHeaders = h;
    }

    public void setData(String[][] d){
        this.tableData = d;
        if(d.length % (this.numRows-1)==0){
            this.numTotalPages = (d.length / (this.numRows-1)) -1;
        }
        else {
            this.numTotalPages = (d.length / (this.numRows-1)) ;
        }
    }

    public void setValueAt(String value, int nr, int nc){
        this.tableData[nr][nc] = value;
    }

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

    // Dibuixa taula
    public void display(PApplet p5, float x, float y, float w, float h){

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
                    if(k<tableData.length){
                        p5.text(tableData[k][c], xCol + 125, y + (r+1)*rowHeight - 10);
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
}
