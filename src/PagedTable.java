import processing.core.PApplet;

public class PagedTable {
    enum TableMode {GRID, LIST}
    TableMode mode = TableMode.GRID;

    String[] tableHeaders;
    Object[] data;
    String[][] gridData;
    float[] columnWidths;

    int numCols, numRows;

    int numPage;
    int numTotalPages;

    private Button[] rowButtons;
    private Button[] editButtons;


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


    public boolean mouseOverGridButtons(PApplet p5) {
        if (mode != TableMode.GRID) return false;
        if (editButtons != null) {
            for (Button b : editButtons) {
                if (b != null && b.mouseOverButton(p5)) return true;
            }
        }
        return false;
    }

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
