import Stdlib.StdDraw;

public class Cell {

    private boolean alive;
    private double centerX;
    private double centery;
    private double posx;
    private double posy;
    private final double len = 1;
    public double largo = posy +len;
    public double ancho = posx +len;

    public Cell(double fila, double col){
        this.centerX = col+len/2;
        this.centery = fila+len/2;
        this.alive = false;
        this.posx = col;
        this.posy = fila;
    }

    public Cell(double fila, double col, boolean alive){
        this.centerX = col+len/2;
        this.centery = fila+len/2;
        this.alive = alive;
        this.posx = col;
        this.posy = fila;
    }

    public Boolean isAlive(){
        return alive;
    }

    public double getCenterX(){
        return centerX;
    }

    public double getCentery(){
        return centery;
    }

    public double getLen() {
        return len;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public void setCentery(double centery) {
        this.centery = centery;
    }

    public boolean isHovered(double mousex, double mousey){
        return mousex>= posx && mousex<=ancho &&
                mousey>= posy && mousey<=largo;}

    public void pintar(){

        StdDraw.setPenColor(StdDraw.WHITE);
        if(isAlive()) {
            StdDraw.filledSquare(centerX, centery, len / 2);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.square(centerX, centery, len / 2);
        }
        else {
            borrar();
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.square(centerX, centery, len / 2);
        }
    }

    private void borrar(){
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledSquare(centerX, centery, len/2);
    }

    public String toString(){
        return alive?"o":"*";
    }
}
