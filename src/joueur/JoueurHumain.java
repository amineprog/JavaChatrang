package joueur;

import java.util.ArrayList;
import java.util.Scanner;
import javachatrang.JavaChatrang;

import jeu.Plateau;
import jeu.Regles;
import tools.Coord;
import tools.Couleur;

/**
 * Classe qui reprÃƒÂ©sente un joueur humain
 *
 * @author ecrvnr
 *
 */
public class JoueurHumain extends Joueur {

    public JoueurHumain(String s, Couleur c) {
        super(s, c);
    }

    public boolean aMoi(Plateau pl, Coord pos) {
        if (Regles.caseValide(pl, pos) && Regles.caseAllie(pl, pos, this.couleur)) {
            return true;
        } else {
            return false;
        }
    }

    public void faireUnMouvement(Plateau pl) {
        Scanner sc = new Scanner(System.in);
        boolean mouvOk = false;
        boolean pieceOk = false;
        int x = -1, y = -1, xA = -1, yA = -1;
        Coord depart = null, arrivee = null;
        while (!mouvOk || !pieceOk) {
            System.out.println("Coordonnee de la piece a deplacer puis coordonnee de la destination (exemple : 6 0 5 0 ) :  ");
            //System.out.println("(ligne espace colonne x2, exemple : 6 0 5 0 )");
            x = sc.nextInt();
            y = sc.nextInt();
            depart = new Coord(x, y);
            if (aMoi(pl, depart)) {
                pieceOk = true;
            } else {
                System.out.println("La case choisi ne contient pas de piece a vous");
                pieceOk = false;
            }
            if (pieceOk) {
                xA = sc.nextInt();
                yA = sc.nextInt();
                arrivee = new Coord(xA, yA);
                ArrayList<Coord> mouvPossible = Regles.mouvementsPossible(pl, pl.getPiece(depart));
                for (int i = 0; i < mouvPossible.size(); i++) {
                    //System.out.println(mouvPossible.get(i).getX()+" "+mouvPossible.get(i).getY());
                    if (mouvPossible.get(i).getX() == arrivee.getX() && mouvPossible.get(i).getY() == arrivee.getY()) {
                        mouvOk = true;
                    }
                }
                if (mouvOk) {
                    pl.deplacerPiece(depart, arrivee);
                } else {
                    System.out.println("Mouvement invalide ou entraînant l'echec de votre roi, recommencez");
                    JavaChatrang.lblMsg.setText("Mouvement invalide ou entraînant l'echec de votre roi, recommencez");
                }
            }
        }

    }

    public Boolean faireUnMouvementUI(Plateau pl, int x1, int y1, int x2, int y2) {
        boolean mouvOk = false;
        boolean pieceOk = false;
        int x, y, xA, yA;
        Coord depart = null, arrivee = null;
        x = x1;
        y = y1;
        depart = new Coord(x, y);
        if (aMoi(pl, depart)) {
            pieceOk = true;
        } else {
            System.out.println("La case choisi ne contient pas de piece a vous");
            JavaChatrang.lblMsg.setText("");
            pieceOk = false;
        }
        if (pieceOk) {
            xA = x2;
            yA = y2;
            arrivee = new Coord(xA, yA);
            ArrayList<Coord> mouvPossible = Regles.mouvementsPossible(pl, pl.getPiece(depart));
            for (int i = 0; i < mouvPossible.size(); i++) {
                //System.out.println(mouvPossible.get(i).getX()+" "+mouvPossible.get(i).getY());
                if (mouvPossible.get(i).getX() == arrivee.getX() && mouvPossible.get(i).getY() == arrivee.getY()) {
                    mouvOk = true;
                }
            }
            if (mouvOk) {
                pl.deplacerPiece(depart, arrivee);
            } else {
                System.out.println("Mouvement invalide ou entraînant l'echec de votre roi, recommencez");
                JavaChatrang.lblMsg.setText("Mouvement invalide ou entraînant l'echec de votre roi, recommencez");
            }
        }
        return mouvOk;
    }

}// fin de classe
