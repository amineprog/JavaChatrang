package jeu;

import tools.Coord;
import tools.Couleur;
import tools.TypeDePiece;

/**
 * Classe qui reprÃ©sente une piÃ¨ce de Chatrang
 *
 * @author ecrvnr
 *
 */
public class Piece {

    private Couleur couleur;
    private TypeDePiece type;
    private Coord coordonnees;

    /**
     * constructeur de la classe
     *
     * @param c couleur
     * @param t type de piÃ¨ce
     * @param x coordonnÃ©e x
     * @param y coordonnÃ©e y
     */
    public Piece(Couleur c, TypeDePiece t, int x, int y) {
        this.coordonnees = new Coord(x, y);
        this.couleur = c;
        this.type = t;
    }

    /**
     * Constructeur par dÃ©faut, crÃ©Ã© une piÃ¨ce en dehors du plateau
     * (utilisÃ© pour les cases vides du plateau)
     */
    public Piece() {
        this.coordonnees = new Coord(-1, -1);
    }

    /**
     * Constructeur par copie pour la classe Piece
     *
     * @param p Piece Ã  copier
     */
    public Piece(Piece p) {
        this.coordonnees = new Coord(p.getX(), p.getY());
        this.couleur = p.getCouleur();
        this.type = p.getType();
    }

    /**
     * retourne l'initiale de la piÃ¨ce utilisÃ©e pour l'affichage du plateau en
     * mode texte
     *
     * @return initiales de la piÃ¨ce pour l'affichage
     */
    public String toString() {
        String initiales = "";

        switch (couleur) { // initiale de la couleur de la piÃ¨ce
            case BLANC:
                initiales += 'B';
                break;

            case NOIR:
                initiales += 'N';
                break;
        }

        switch (type) { // initiale du type de piÃ¨ce
            case ROI:
                initiales += 'S'; // ShÃ¢h
                break;

            case CONSEILLER:
                initiales += 'V'; // Vizir
                break;

            case CAVALIER:
                initiales += 'F'; // Faras
                break;

            case ELEPHANT:
                initiales += 'E'; // Elephant
                break;

            case CHAR:
                initiales += 'R'; // Roukh
                break;

            case SOLDAT:
                initiales += 'B'; // Baidaq
                break;
        }

        return initiales;
    }

    /**
     * getter pour la couleur
     *
     * @return couleur de la piÃ¨ce (noir ou blanc)
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * getter pour le type
     *
     * @return type de la piÃ¨ce
     */
    public TypeDePiece getType() {
        return type;
    }

    /**
     * getter pour l'abcisse
     *
     * @return coordonnÃ©e x
     */
    public int getX() {
        return coordonnees.getX();
    }

    /**
     * getter pour l'ordonnÃ©e
     *
     * @return coordonnÃ©e y
     */
    public int getY() {
        return coordonnees.getY();
    }

    /**
     * getter pour la Coord
     *
     * @return coordonnÃ©e y
     */
    public Coord getCoord() {
        return coordonnees;
    }

    /**
     * setter pour l'abscisse
     *
     * @param n coordonnÃ©e x
     */
    public void setX(int n) {
        this.coordonnees.setX(n);
    }

    /**
     * setter pour l'ordonnÃ©e
     *
     * @param n coordonnÃ©e y
     */
    public void setY(int n) {
        this.coordonnees.setY(n);
    }
}
