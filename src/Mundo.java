import Stdlib.*;
import static java.awt.event.KeyEvent.*;

public class Mundo {
    private final int filas = 35;
    private final int cols = 110;
    private Cell[][] cuadricula = new Cell[filas][cols];
    private boolean gameOn;

    public Mundo(boolean setFirstGen){
        this.gameOn = setFirstGen;
        for(int i = 0; i<filas; i++){
            for(int j = 0; j<cols; j++)
                cuadricula[i][j] = new Cell(i, j);
        }
        if(gameOn)
            RandomFirstGen();
    }

    public int getFilas(){
        return filas;
    }

    public int getCols(){
        return cols;
    }

    private void setCell(int i, int j, boolean alive){
        cuadricula[i][j] = new Cell(i, j, alive);
    }

    private int getVecinosVivos(int fila, int col){
        int vecinosVivos = 0;
        for(int i = fila-1; i<=fila+1; i++){
            for(int j = col-1; j<=col+1; j++){
                if(i>=0 && i<filas && j>=0 && j<cols && !(i+""+j).equals(fila+""+col) )
                    vecinosVivos+=cuadricula[i][j].isAlive()?1:0;
            }
        }
        return vecinosVivos;
    }

    private void nextGeneracion(){
        Cell[][] nextGen = new Cell[filas][cols];
        for(int i = 0; i<filas; i++){
            for(int j = 0; j<cols; j++){
                nextGen[i][j] = new Cell(i, j, isGoingToBeAlive(i, j));
            }
        }
        for (int i = 0; i < filas; i++) {
            System.arraycopy(nextGen[i], 0, cuadricula[i], 0, cols);
        }
       // System.out.println(toString());
    }

    private void firstGeneracion(){
        while(!gameOn){
            int mousex = (int)StdDraw.mouseX()<140?(int)StdDraw.mouseX():139;
            int mousey = (int)StdDraw.mouseY()<35?(int)StdDraw.mouseY():34;
            if(StdDraw.isMousePressed()){
                cuadricula[mousey][mousex].setAlive(true);
                cuadricula[mousey][mousex].pintar();
                StdDraw.show();

            }
            else if(StdDraw.isKeyPressed(VK_SPACE)) {
                gameOn = true;
            }
            // System.out.println(toString());
        }

    }

    private void RandomFirstGen(){
        for(int i = 0; i<filas; i++){
            for(int j=0; j< cols;j++){
                setCell(i, j, Math.random()*100<30);
            }
        }
    }

    private boolean isGoingToBeAlive(int fila, int col){
        int vecinos = getVecinosVivos(fila, col);
        if(cuadricula[fila][col].isAlive()){
            return vecinos == 2 || vecinos == 3;
        }
        else{
            return vecinos == 3;
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for (Cell[] cells : cuadricula) {
            for (int i = 0; i < cols; i++) {
                str.append(cells[i]);
            }
            str.append("\n");
        }
        return str.toString();
    }

    private void initGraficos(){
        StdDraw.setCanvasSize(1500, 700);
        StdDraw.setXscale(0, cols);
        StdDraw.setYscale(0, filas);
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.filledRectangle(((double)cols)/2, ((double)filas)/2, ((double)cols)/2, ((double)filas)/2);
        pintar();
        StdDraw.show();

    }

    private void pintar(){
        for(Cell[] cells : cuadricula){
            for(int i = 0; i<cols; i++)
                cells[i].pintar();
        }
        StdDraw.show();
    }



    private void start() throws InterruptedException {
        while(gameOn) {
            nextGeneracion();
            pintar();
            Thread.sleep(1000);
            //System.out.println(toString());
        }
    }

    public void init() throws InterruptedException {
        initGraficos();
        firstGeneracion();
        start();
    }


}
