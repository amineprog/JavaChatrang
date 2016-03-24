package joueur;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import tools.Coord;
import tools.Couleur;
import jeu.Piece;
import jeu.Plateau;
import jeu.Regles;

/**
 * Classe qui reprÃƒÂ©sente un joueur AI
 *
 * @author ecrvnr
 *
 */
public class JoueurAI extends Joueur {

    public JoueurAI(String s, Couleur c) {
        super(s, c);
    }

    public void faireUnMouvement(Plateau pl) {
        //IA Random
        Piece[][] plateau = pl.getPlateau();
        ArrayList<Piece> pieceDispo = new ArrayList<Piece>();
        ArrayList<ArrayList<Coord>> toutLesCoupsPossible = new ArrayList<ArrayList<Coord>>();
        int i;
        int j;
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if (plateau[i][j] != null && plateau[i][j].getCouleur().equals(couleur)) {
                    pieceDispo.add(plateau[i][j]);
                    toutLesCoupsPossible.add(Regles.mouvementsPossible(pl, plateau[i][j]));
                }
            }
        }
        Random rand = new Random();
        boolean mouvOk = false;
        int numPiece = 0;
        int numCoup = 0;
        while (!mouvOk) {
            numPiece = rand.nextInt(pieceDispo.size());
            if (toutLesCoupsPossible.get(numPiece).size() > 0) {
                numCoup = rand.nextInt(toutLesCoupsPossible.get(numPiece).size());
                mouvOk = true;
            }
            /*	numCoup=rand.nextInt(toutLesCoupsPossible.get(numPiece).size());
    		if(numCoup>0){
    			mouvOk=true;
    		}*/
        }
        Piece choisi = pieceDispo.get(numPiece);
        Coord destination = toutLesCoupsPossible.get(numPiece).get(numCoup);
        pl.deplacerPiece(choisi.getCoord(), destination);
        System.out.println("L'ia a bouge de " + choisi.getX() + " " + choisi.getY() + " vers " + destination.getX() + " " + destination.getY());
    }

} // fin de classe
