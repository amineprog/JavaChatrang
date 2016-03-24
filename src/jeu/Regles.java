package jeu;

import javachatrang.JavaChatrang;
import java.util.ArrayList;

import tools.Coord;
import tools.Couleur;

/**
 * Classe non instantiÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e, elle sert ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â 
 * gÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©rer les rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨gles du jeu
 *
 * @author ecrvnr
 *
 */
public class Regles {
    
    public static ArrayList<Coord> mouvementsPossible(Plateau pl, Piece pi) {
        ArrayList<Coord> coupsAutorises = mouvementsValides(pl, pi);
        coupsAutorises = retireLesEchec(coupsAutorises, pl, pi.getCouleur(), pi.getCoord());
        return coupsAutorises;
    }

    /**
     * cette mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode se contente d'appeler une des
     * mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thodes qui retournera la liste des coups jouables par
     * un piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce selon son type
     *
     * @param pl le plateau sur lequel on se place
     * @param pi la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce dont on veut
     * connaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®tre les mouvements autorisÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©s
     * @return tableau contenant les coordonnÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es des cases ou
     * la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce a le droit d'aller
     */
    public static ArrayList<Coord> mouvementsValides(Plateau pl, Piece pi) {        
        switch (pi.getType()) {
            case ROI:
                return mouvementsValidesRoi(pl, pi);
            
            case CONSEILLER:
                return mouvementsValidesConseiller(pl, pi);
            
            case CAVALIER:
                return mouvementsValidesCavalier(pl, pi);
            
            case ELEPHANT:
                return mouvementsValidesElephant(pl, pi);
            
            case CHAR:
                return mouvementsValidesChar(pl, pi);
            
            case SOLDAT:
                return mouvementsValidesSoldat(pl, pi);
            
            default:
                return new ArrayList<Coord>();
        }
    }

    /**
     * liste des coups jouables par un Roi
     *
     * @param pl le plateau sur lequel on se place
     * @param pi la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce dont on veut
     * connaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®tre les mouvements autorisÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©s
     * @return tableau contenant les coordonnÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es des cases ou
     * la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce a le droit d'aller
     */
    public static ArrayList<Coord> mouvementsValidesRoi(Plateau pl, Piece pi) {
        ArrayList<Coord> coupsAutorises = new ArrayList<Coord>();
        
        Coord haut = new Coord(pi.getX(), pi.getY() + 1);
        if (caseValide(pl, haut) && !caseAllie(pl, haut, pi.getCouleur())) {
            coupsAutorises.add(haut);
        }
        Coord bas = new Coord(pi.getX(), pi.getY() - 1);
        if (caseValide(pl, bas) && !caseAllie(pl, bas, pi.getCouleur())) {
            coupsAutorises.add(bas);
        }
        Coord gauche = new Coord(pi.getX() - 1, pi.getY());
        if (caseValide(pl, gauche) && !caseAllie(pl, gauche, pi.getCouleur())) {
            coupsAutorises.add(gauche);
        }
        Coord droite = new Coord(pi.getX() + 1, pi.getY());
        if (caseValide(pl, droite) && !caseAllie(pl, droite, pi.getCouleur())) {
            coupsAutorises.add(droite);
        }
        
        Coord hautGauche = new Coord(pi.getX() - 1, pi.getY() + 1);
        if (caseValide(pl, hautGauche) && !caseAllie(pl, hautGauche, pi.getCouleur())) {
            coupsAutorises.add(hautGauche);
        }
        Coord hautDroite = new Coord(pi.getX() + 1, pi.getY() + 1);
        if (caseValide(pl, hautDroite) && !caseAllie(pl, hautDroite, pi.getCouleur())) {
            coupsAutorises.add(hautDroite);
        }
        Coord basGauche = new Coord(pi.getX() - 1, pi.getY() - 1);
        if (caseValide(pl, basGauche) && !caseAllie(pl, basGauche, pi.getCouleur())) {
            coupsAutorises.add(basGauche);
        }
        Coord basDroite = new Coord(pi.getX() + 1, pi.getY() - 1);
        if (caseValide(pl, basDroite) && !caseAllie(pl, basDroite, pi.getCouleur())) {
            coupsAutorises.add(basDroite);
        }
        return coupsAutorises;
    }

    /**
     * liste des coups jouables par un Conseiller
     *
     * @param pl le plateau sur lequel on se place
     * @param pi la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce dont on veut
     * connaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®tre les mouvements autorisÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©s
     * @return tableau contenant les coordonnÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es des cases ou
     * la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce a le droit d'aller
     */
    public static ArrayList<Coord> mouvementsValidesConseiller(Plateau pl, Piece pi) {
        ArrayList<Coord> coupsAutorises = new ArrayList<Coord>();
        
        Coord hautGauche = new Coord(pi.getX() - 1, pi.getY() + 1);
        if (caseValide(pl, hautGauche) && !caseAllie(pl, hautGauche, pi.getCouleur())) {
            coupsAutorises.add(hautGauche);
        }
        Coord hautDroite = new Coord(pi.getX() + 1, pi.getY() + 1);
        if (caseValide(pl, hautDroite) && !caseAllie(pl, hautDroite, pi.getCouleur())) {
            coupsAutorises.add(hautDroite);
        }
        Coord basGauche = new Coord(pi.getX() - 1, pi.getY() - 1);
        if (caseValide(pl, basGauche) && !caseAllie(pl, basGauche, pi.getCouleur())) {
            coupsAutorises.add(basGauche);
        }
        Coord basDroite = new Coord(pi.getX() + 1, pi.getY() - 1);
        if (caseValide(pl, basDroite) && !caseAllie(pl, basDroite, pi.getCouleur())) {
            coupsAutorises.add(basDroite);
        }
        return coupsAutorises;
    }

    /**
     * liste des coups jouables par un Cavalier
     *
     * @param pl le plateau sur lequel on se place
     * @param pi la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce dont on veut
     * connaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®tre les mouvements autorisÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©s
     * @return tableau contenant les coordonnÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es des cases ou
     * la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce a le droit d'aller
     */
    public static ArrayList<Coord> mouvementsValidesCavalier(Plateau pl, Piece pi) {
        ArrayList<Coord> coupsAutorises = new ArrayList<Coord>();
        
        Coord hautHautGauche = new Coord(pi.getX() - 1, pi.getY() + 2);
        if (caseValide(pl, hautHautGauche) && !caseAllie(pl, hautHautGauche, pi.getCouleur())) {
            coupsAutorises.add(hautHautGauche);
        }
        Coord hautGaucheGauche = new Coord(pi.getX() - 2, pi.getY() + 1);
        if (caseValide(pl, hautGaucheGauche) && !caseAllie(pl, hautGaucheGauche, pi.getCouleur())) {
            coupsAutorises.add(hautGaucheGauche);
        }
        Coord hautHautDroite = new Coord(pi.getX() + 1, pi.getY() + 2);
        if (caseValide(pl, hautHautDroite) && !caseAllie(pl, hautHautDroite, pi.getCouleur())) {
            coupsAutorises.add(hautHautDroite);
        }
        Coord hautDroiteDroite = new Coord(pi.getX() + 2, pi.getY() + 1);
        if (caseValide(pl, hautDroiteDroite) && !caseAllie(pl, hautDroiteDroite, pi.getCouleur())) {
            coupsAutorises.add(hautDroiteDroite);
        }
        
        Coord basBasGauche = new Coord(pi.getX() - 1, pi.getY() - 2);
        if (caseValide(pl, basBasGauche) && !caseAllie(pl, basBasGauche, pi.getCouleur())) {
            coupsAutorises.add(basBasGauche);
        }
        Coord basGaucheGauche = new Coord(pi.getX() - 2, pi.getY() - 1);
        if (caseValide(pl, basGaucheGauche) && !caseAllie(pl, basGaucheGauche, pi.getCouleur())) {
            coupsAutorises.add(basGaucheGauche);
        }
        Coord basBasDroite = new Coord(pi.getX() + 1, pi.getY() - 2);
        if (caseValide(pl, basBasDroite) && !caseAllie(pl, basBasDroite, pi.getCouleur())) {
            coupsAutorises.add(basBasDroite);
        }
        Coord basDroiteDroite = new Coord(pi.getX() + 2, pi.getY() - 1);
        if (caseValide(pl, basDroiteDroite) && !caseAllie(pl, basDroiteDroite, pi.getCouleur())) {
            coupsAutorises.add(basDroiteDroite);
        }
        return coupsAutorises;
    }

    /**
     * liste des coups jouables par un Elephant
     *
     * @param pl le plateau sur lequel on se place
     * @param pi la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce dont on veut
     * connaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®tre les mouvements autorisÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©s
     * @return tableau contenant les coordonnÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es des cases ou
     * la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce a le droit d'aller
     */
    public static ArrayList<Coord> mouvementsValidesElephant(Plateau pl, Piece pi) {
        ArrayList<Coord> coupsAutorises = new ArrayList<Coord>();
        
        Coord hautGauche2 = new Coord(pi.getX() - 2, pi.getY() + 2);
        if (caseValide(pl, hautGauche2) && !caseAllie(pl, hautGauche2, pi.getCouleur())) {
            coupsAutorises.add(hautGauche2);
        }
        Coord hautDroite2 = new Coord(pi.getX() + 2, pi.getY() + 2);
        if (caseValide(pl, hautDroite2) && !caseAllie(pl, hautDroite2, pi.getCouleur())) {
            coupsAutorises.add(hautDroite2);
        }
        Coord basGauche2 = new Coord(pi.getX() - 2, pi.getY() - 2);
        if (caseValide(pl, basGauche2) && !caseAllie(pl, basGauche2, pi.getCouleur())) {
            coupsAutorises.add(basGauche2);
        }
        Coord basDroite2 = new Coord(pi.getX() + 2, pi.getY() - 2);
        if (caseValide(pl, basDroite2) && !caseAllie(pl, basDroite2, pi.getCouleur())) {
            coupsAutorises.add(basDroite2);
        }
        return coupsAutorises;
    }

    /**
     * liste des coups jouables par un Char
     *
     * @param pl le plateau sur lequel on se place
     * @param pi la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce dont on veut
     * connaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®tre les mouvements autorisÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©s
     * @return tableau contenant les coordonnÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es des cases ou
     * la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce a le droit d'aller
     */
    public static ArrayList<Coord> mouvementsValidesChar(Plateau pl, Piece pi) {
        ArrayList<Coord> coupsAutorises = new ArrayList<Coord>();
        
        Coord haut = new Coord(pi.getX(), pi.getY() + 1);
        while (caseValide(pl, haut) && !caseOccupe(pl, haut)) {
            coupsAutorises.add(haut);
            haut = new Coord(haut.getX(), haut.getY() + 1); // on remplace la
            // coord par une
            // autre en
            // augmentant de 1
        }
        if (caseValide(pl, haut) && caseEnnemi(pl, haut, pi.getCouleur())) {
            coupsAutorises.add(haut);
        }
        
        Coord bas = new Coord(pi.getX(), pi.getY() - 1);
        while (caseValide(pl, bas) && !caseOccupe(pl, bas)) {
            coupsAutorises.add(bas);
            bas = new Coord(bas.getX(), bas.getY() - 1); // on remplace la coord
            // par une autre en
            // augmentant de 1
        }
        if (caseValide(pl, bas) && caseEnnemi(pl, bas, pi.getCouleur())) {
            coupsAutorises.add(bas);
        }
        
        Coord gauche = new Coord(pi.getX() - 1, pi.getY());
        while (caseValide(pl, gauche) && !caseOccupe(pl, gauche)) {
            coupsAutorises.add(gauche);
            gauche = new Coord(gauche.getX() - 1, gauche.getY()); // on remplace la
            // coord par une
            // autre en
            // augmentant de
            // 1
        }
        if (caseValide(pl, gauche) && caseEnnemi(pl, gauche, pi.getCouleur())) {
            coupsAutorises.add(gauche);
        }
        
        Coord droite = new Coord(pi.getX() + 1, pi.getY());
        while (caseValide(pl, droite) && !caseOccupe(pl, droite)) {
            coupsAutorises.add(droite);
            droite = new Coord(droite.getX() + 1, droite.getY()); // on remplace la
            // coord par une
            // autre en
            // augmentant de
            // 1
        }
        if (caseValide(pl, droite) && caseEnnemi(pl, droite, pi.getCouleur())) {
            coupsAutorises.add(droite);
        }
        
        return coupsAutorises;
    }

    /**
     * liste des coups jouables par un Soldat
     *
     * @param pl le plateau sur lequel on se place
     * @param pi la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce dont on veut
     * connaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®tre les mouvements autorisÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©s
     * @return tableau contenant les coordonnÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es des cases ou
     * la piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ce a le droit d'aller
     */
    public static ArrayList<Coord> mouvementsValidesSoldat(Plateau pl, Piece pi) {
        ArrayList<Coord> coupsAutorises = new ArrayList<Coord>();
        if (pi.getCouleur() == Couleur.BLANC) { // Les blanc sont en bas du
            // plateau
            //System.out.println("pion blanc");
            Coord avant = new Coord(pi.getX() - 1, pi.getY());
            if (caseValide(pl, avant) && !caseOccupe(pl, avant)) {
                coupsAutorises.add(avant);
            }
            Coord diagonaleGauche = new Coord(pi.getX() - 1, pi.getY() - 1);
            if (caseValide(pl, diagonaleGauche) && caseEnnemi(pl, diagonaleGauche, pi.getCouleur())) {
                coupsAutorises.add(diagonaleGauche);
            }
            Coord diagonaleDroite = new Coord(pi.getX() - 1, pi.getY() + 1);
            if (caseValide(pl, diagonaleDroite) && caseEnnemi(pl, diagonaleDroite, pi.getCouleur())) {
                coupsAutorises.add(diagonaleDroite);
            }
        } else { // les noirs sont en haut du plateau
            //System.out.println("pion noir ");
            Coord avant = new Coord(pi.getX() + 1, pi.getY());
            if (caseValide(pl, avant) && !caseOccupe(pl, avant)) {
                coupsAutorises.add(avant);
            }
            Coord diagonaleGauche = new Coord(pi.getX() + 1, pi.getY() - 1);
            if (caseValide(pl, diagonaleGauche) && caseEnnemi(pl, diagonaleGauche, pi.getCouleur())) {
                coupsAutorises.add(diagonaleGauche);
            }
            Coord diagonaleDroite = new Coord(pi.getX() + 1, pi.getY() + 1);
            if (caseValide(pl, diagonaleDroite) && caseEnnemi(pl, diagonaleDroite, pi.getCouleur())) {
                coupsAutorises.add(diagonaleDroite);
            }
        }
        return coupsAutorises;
    }

    /**
     * indique si une case est sur le plateau
     *
     * @param pl le plateau sur lequel on se place
     * @param pos coordonnÃƒÆ’Ã‚Â©e de la case
     * @return vrai si la case est dans le plateau (x et y entre -1 et 8
     * exclus), faux sinon
     */
    public static boolean caseValide(Plateau pl, Coord pos) {
        if (pos.getX() > -1 && pos.getY() > -1 && pos.getX() < 8 && pos.getY() < 8) {
            return true;
        }
        return false;
    }

    /**
     * indique si une case est occupe par une piece ennemis
     *
     * @param pl le plateau sur lequel on se place
     * @param pos coordonnÃƒÆ’Ã‚Â©e de la case
     * @param couleur la couleur qui est considÃƒÆ’Ã‚Â©rÃƒÆ’Ã‚Â© comme
     * alliÃƒÆ’Ã‚Â©
     * @return vrai si la case contient une piece de la couleur adverse, faux
     * sinon ou si vide
     */
    private static boolean caseEnnemi(Plateau pl, Coord pos, Couleur couleur) {
        //	if(caseValide(pl,pos)){
        Piece[][] plateau = pl.getPlateau();
        if (plateau[pos.getX()][pos.getY()] != null && plateau[pos.getX()][pos.getY()].getCouleur() != couleur) {
            return true;
        } else {
            return false;
        }
        //}
        //else{return false;}
    }

    /**
     * indique si une case est occupe par une piece allie
     *
     * @param pl le plateau sur lequel on se place
     * @param pos coordonnÃƒÆ’Ã‚Â©e de la case
     * @param couleur la couleur qui est considÃƒÆ’Ã‚Â©rÃƒÆ’Ã‚Â© comme
     * alliÃƒÆ’Ã‚Â©
     * @return vrai si la case contient une piece de la couleur allie, faux
     * sinon ou si vide
     */
    public static boolean caseAllie(Plateau pl, Coord pos, Couleur couleur) {
        //if(caseValide(pl,pos)){
        Piece[][] plateau = pl.getPlateau();
        if (plateau[pos.getX()][pos.getY()] != null && plateau[pos.getX()][pos.getY()].getCouleur() == couleur) {
            return true;
        } else {
            return false;
        }
        //	}
        //else{return false;}
    }

    /**
     * indique si une case est occupe par une piece
     *
     * @param pl le plateau sur lequel on se place
     * @param pos coordonnÃƒÆ’Ã‚Â©e de la case
     * @return vrai si la case est occupe par une piece, faux sinon
     */
    private static boolean caseOccupe(Plateau pl, Coord pos) {
        //if(caseValide(pl,pos)){
        Piece[][] plateau = pl.getPlateau();
        if (plateau[pos.getX()][pos.getY()] != null) {
            return true;
        } else {
            return false;
        }
        //	}
        //	else {return false;}
    }

    /**
     * indique si la piece roi est en echec
     *
     * @param pl le plateau sur lequel on se place
     * @param roi la piece de type roi
     * @return vrai si le roi est en echec, faux sinon
     */
    public static boolean estEnEchec(Piece roi, Plateau pl) {
        boolean enEchec = false;
        int i = 0;
        Couleur couleurRoi = roi.getCouleur();
        Piece[][] plateau = pl.getPlateau();
        while (i < 8 && !enEchec) {
            int j = 0;
            while (j < 8 && !enEchec) { // les 2 boucle permettent de parcourir tout le tableau
                Coord cible = new Coord(i, j);
                if (caseEnnemi(pl, cible, couleurRoi)) { // si on arrive sur une case ennemi on va calculer ses coup possible
                    ArrayList<Coord> coupsPossible = mouvementsValides(pl, plateau[i][j]);
                    for (int x = 0; x < coupsPossible.size(); x++) { // puis pour chaque coup on vÃƒÂ©rifie s'il atteint le roi
                        if (coupsPossible.get(x).getX() == roi.getX() && coupsPossible.get(x).getY() == roi.getY()) {
                            enEchec = true;
                        }
                    }
                }
                j++;
            }
            i++;
        }
        return enEchec;
    }

    /**
     * filtre un ensemble de coup possible en retirant les coup mettant en échec
     * le joueur actif
     *
     * @param coupPossible ArrayList<Coord> de coup théoriquement possible pour
     * une piece
     * @param pl le plateau sur lequel on se place
     * @param coul Couleur du joueur actif (permet de trouver son roi)
     * @return vrai si le roi est en echec et mat, faux sinon
     */
    private static ArrayList<Coord> retireLesEchec(ArrayList<Coord> coupPossible, Plateau pl, Couleur coul, Coord PieceQuiBouge) {
        ArrayList<Coord> coupVraimentPossible = new ArrayList<Coord>();
        for (int i = 0; i < coupPossible.size(); i++) {
            Plateau copie = pl.cloner();
            copie.deplacerPiece(PieceQuiBouge, coupPossible.get(i));
            Piece roi = copie.trouverLeRoi(coul);
            if (!estEnEchec(roi, copie)) {
                coupVraimentPossible.add(coupPossible.get(i));
            }
        }
        return coupVraimentPossible;
    }

    /**
     * indique si la piece roi est en echec et mat, s'utilise au dÃƒÂ©but du
     * tour d'un joueur qui est en ÃƒÂ©chec
     *
     * @param pl le plateau sur lequel on se place
     * @param roi la piece de type roi
     * @return vrai si le roi est en echec et mat, faux sinon
     */
    public static boolean estEnEchecEtMat(Piece roi, Plateau pl) {
        boolean enEchecEtMat = true;
        int i = 0;
        if (roi == null) {
            System.out.println("Roi non trouve");
            JavaChatrang.lblMsg.setText("Roi non trouve");
        }
        Couleur couleurRoi = roi.getCouleur();
        Piece[][] plateau = pl.getPlateau();
        while (i < 8 && enEchecEtMat) {
            int j = 0;
            while (j < 8 && enEchecEtMat) { // les 2 boucle permettent de parcourir tout le tableau
                Coord cible = new Coord(i, j);
                if (caseAllie(pl, cible, couleurRoi)) { //si on arrive sur une case allie on va calculer ses coup possible
                    ArrayList<Coord> coupsPossible = mouvementsValides(pl, plateau[i][j]);
                    for (int x = 0; x < coupsPossible.size(); x++) { // puis pour chaque coup on vÃƒÂ©rifie si le roi est toujours en ÃƒÂ©chec
                        Plateau copie = pl.cloner(); // implÃƒÂ©menter cloner() dans plateau // On clone le plateau
                        copie.deplacerPiece(cible, coupsPossible.get(x)); // puis on joue le coup dessus la copie
                        Piece copieRoi = copie.trouverLeRoi(couleurRoi); // on retrouve le roi sur la copie
                        if (!estEnEchec(copieRoi, copie)) { // on teste si le roi n'est pas en echec dans cette nouvelle configuration
                            enEchecEtMat = false; // si oui il n'y a pas echec et mat
                        }
                    }
                }
                j++;
            }
            i++;
        }
        
        return enEchecEtMat;
    }
}
