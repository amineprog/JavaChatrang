package jeu;

import java.util.ArrayList;
import tools.Coord;
import tools.Couleur;
import tools.TypeDePiece;

/**
 * Classe qui reprÃƒÆ’Ã‚Â©sente UN plateau de Chatrang et pourra ÃƒÆ’Ã‚Âªtre
 * instantiÃƒÆ’Ã‚Â©e plusieurs fois pour les prÃƒÆ’Ã‚Â©visions d'un joueur IA
 *
 * @author ecrvnr
 *
 */
public class Plateau implements Cloneable {

    private Piece[][] plateau = new Piece[8][8];
    private ArrayList<Piece> pieces = new ArrayList<Piece>(32);

    public Plateau() {
        this.initialiser();
    }

    /**
     * Affiche l'ÃƒÆ’Ã‚Â©tat actuel de la partie (plateau de 8x8 avec les
     * piÃƒÆ’Ã‚Â¨ces). Note les numÃƒÆ’Ã‚Â©ros et lettres des rangs et colonnes
     * Version texte uniquement
     */
    public String toString() {
        String s = "\n\n";
        s += "     0  1  2  3  4  5  6  7\n";
        s += "  ###########################\n";

        for (int i = 0; i <= 7; i++) {
            s += (i) + " #|";
            for (int j = 0; j <= 7; j++) {
                if (plateau[i][j] == null) {
                    s += "  ";
                    s += "|";

                } else {
                    s += plateau[i][j];
                    s += "|";
                }
            }

            s += "# " + (i) + "\n";
            if (i != 7) {
                s += "  #|-----------------------|#\n";
            }
        }

        s += "  ###########################\n";
        s += "     0  1  2  3  4  5  6  7\n";
        return s;
    }

    /**
     * Initialise le plateau de jeu en plaÃƒÆ’Ã‚Â§ant toutes les piÃƒÆ’Ã‚Â¨ces
     * sur leur position de dÃƒÆ’Ã‚Â©part
     */
    public void initialiser() {
        creerPieces();
        peupler();
    }

    /**
     * Remplit le tableau contenant toutes les piÃƒÆ’Ã‚Â¨ces
     */
    public void creerPieces() {

        // crÃƒÆ’Ã‚Â©ation des Soldats (Baidaq)
        for (int i = 0; i < 8; i++) {
            pieces.add(new Piece(Couleur.NOIR, TypeDePiece.SOLDAT, 1, i));
            pieces.add(new Piece(Couleur.BLANC, TypeDePiece.SOLDAT, 6, i));
        }

        // crÃƒÆ’Ã‚Â©ation des Cavaliers (Faras)
        pieces.add(new Piece(Couleur.NOIR, TypeDePiece.CAVALIER, 0, 1));
        pieces.add(new Piece(Couleur.NOIR, TypeDePiece.CAVALIER, 0, 6));
        pieces.add(new Piece(Couleur.BLANC, TypeDePiece.CAVALIER, 7, 1));
        pieces.add(new Piece(Couleur.BLANC, TypeDePiece.CAVALIER, 7, 6));

        // crÃƒÆ’Ã‚Â©ation des Chars (Roukh)
        pieces.add(new Piece(Couleur.NOIR, TypeDePiece.CHAR, 0, 0));
        pieces.add(new Piece(Couleur.NOIR, TypeDePiece.CHAR, 0, 7));
        pieces.add(new Piece(Couleur.BLANC, TypeDePiece.CHAR, 7, 0));
        pieces.add(new Piece(Couleur.BLANC, TypeDePiece.CHAR, 7, 7));

        // crÃƒÆ’Ã‚Â©ation des Elephants
        pieces.add(new Piece(Couleur.NOIR, TypeDePiece.ELEPHANT, 0, 2));
        pieces.add(new Piece(Couleur.NOIR, TypeDePiece.ELEPHANT, 0, 5));
        pieces.add(new Piece(Couleur.BLANC, TypeDePiece.ELEPHANT, 7, 2));
        pieces.add(new Piece(Couleur.BLANC, TypeDePiece.ELEPHANT, 7, 5));

        // crÃƒÆ’Ã‚Â©ation des Conseillers (Vizir)
        pieces.add(new Piece(Couleur.NOIR, TypeDePiece.CONSEILLER, 0, 3));
        pieces.add(new Piece(Couleur.BLANC, TypeDePiece.CONSEILLER, 7, 4));

        // crÃƒÆ’Ã‚Â©ation des Rois (ShÃƒÆ’Ã‚Â¢h)
        pieces.add(new Piece(Couleur.NOIR, TypeDePiece.ROI, 0, 4));
        pieces.add(new Piece(Couleur.BLANC, TypeDePiece.ROI, 7, 3));
    }

    /**
     * Place les piÃƒÆ’Ã‚Â¨ce sur le plateau
     */
    public void peupler() {

        // remplit le plateau de piÃƒÆ’Ã‚Â¨ces nulles (cases vides)
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                plateau[i][j] = null;
            }
        }

        // place les piÃƒÆ’Ã‚Â¨ces sur les bonnes cases
        int i = 0;
        do {
            this.plateau[pieces.get(i).getX()][pieces.get(i).getY()] = pieces.get(i);
            i++;
        } while (i < pieces.size());
    }

    /**
     * Deplace une piÃƒÆ’Ã‚Â¨ce vers une nouvelle position
     *
     * @param depart CoordonnÃƒÆ’Ã‚Â©es de la piÃƒÆ’Ã‚Â¨ce qu'on veut deplacer
     * @param arrivee position oÃƒÆ’Ã‚Â¹ on veut placer la piÃƒÆ’Ã‚Â¨ce
     */
    public void deplacerPiece(Coord depart, Coord arrivee) {
        //if(plateau[depart.getX()][depart.getY()]!=null){
        plateau[arrivee.getX()][arrivee.getY()] = new Piece(plateau[depart.getX()][depart.getY()]);
        plateau[arrivee.getX()][arrivee.getY()].setX(arrivee.getX());
        plateau[arrivee.getX()][arrivee.getY()].setY(arrivee.getY());
        plateau[depart.getX()][depart.getY()] = null;
        //} else {System.out.println("Déplacement d'une piéce inexistante");}

    }

    /**
     * retourne la piÃƒÆ’Ã‚Â¨ce ÃƒÆ’Ã‚Â  la coordonnÃƒÆ’Ã‚Â©e x,y
     *
     * @param c CoordonnÃƒÆ’Ã‚Â©es de la piÃƒÆ’Ã‚Â¨ce souhaitÃƒÆ’Ã‚Â©e
     * @return la piece
     */
    public Piece getPiece(Coord c) {
        return plateau[c.getX()][c.getY()];
    }

    /**
     * Retourne l'ensemble des pieces
     *
     * @return tableau qui reprÃƒÆ’Ã‚Â©sente le plateau de jeu
     */
    public Piece[][] getPlateau() {
        return plateau;
    }

    /**
     * Retourne une copie profonde du plateau
     *
     * @return copie profonde du plateau
     */
    public Plateau cloner() {
        int i, j;
        Plateau plateau_clone = new Plateau();
        plateau_clone.pieces.clear();					// on vide l'arraylist de piÃƒÂ¨ces
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                plateau_clone.plateau[i][j] = null;		// on vide plateau de toute les piece qui ont ete initialise
            }
        }

        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if (plateau[i][j] != null) {
                    Piece P = new Piece(plateau[i][j]);	// on recree chaque piece du plateau actuel
                    plateau_clone.plateau[i][j] = P; // on les ajoute au plateau clone
                }
            }
        }

        return plateau_clone;
    }

    /**
     * Retourne le roi de la couleur passe en parametre
     *
     * @return le roi de couleur c
     */
    public Piece trouverLeRoi(Couleur c) {
        int i;
        int j;
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if (plateau[i][j] != null) {
                    if (plateau[i][j].getType().equals(TypeDePiece.ROI)) {        // si c'est un ROI
                        if (plateau[i][j].getCouleur().equals(c)) {    		// si il correspond ÃƒÂ  la couleur passÃƒÂ© en param
                            return plateau[i][j];
                        }
                    }
                }
            }
        }
        return null;
    }
}
