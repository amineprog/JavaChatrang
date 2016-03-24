package joueur;

import jeu.Plateau;
import tools.Couleur;

/**
 * Classe non instantiÃ©e qui reprÃ©sente un joueur en gÃ©nÃ©ral. Les classe
 * JoueurHumain et JoueurAI hÃ©riteront de celle-ci
 *
 * @author ecrvnr
 *
 */
public abstract class Joueur {

    protected String pseudo;
    protected Couleur couleur;

    public Joueur(String s, Couleur c) {
        this.pseudo = s;
        this.couleur = c;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public abstract void faireUnMouvement(Plateau pl);

    public Boolean faireUnMouvementUI(Plateau plateau, int x1, int y1, int x2, int y2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
