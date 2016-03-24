package tools;

/**
 * Classe qui représente des coordonnées à 2 dimensions
 *
 * @author ecrvnr
 *
 */
public class Coord {

    private int x;
    private int y;

    /**
     * constructeur de classe
     *
     * @param x Abscisse
     * @param y Ordonnée
     */
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter pour l'abscisse
     *
     * @return abscisse
     */
    public int getX() {
        return x;
    }

    /**
     * getter pour l'ordonnée
     *
     * @return ordonnée
     */
    public int getY() {
        return y;
    }

    /**
     * setter pour l'abscisse
     *
     * @param x abscisse
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * setter pour l'ordonnée
     *
     * @param y ordonnée
     */
    public void setY(int y) {
        this.y = y;
    }
}
