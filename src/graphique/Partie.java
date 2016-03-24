package graphique;

import graphique.UtilisateurInterface;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import javachatrang.JavaChatrang;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jeu.Plateau;
import jeu.Regles;
import joueur.Joueur;
import joueur.JoueurAI;
import joueur.JoueurHumain;
import tools.Coord;
import tools.Couleur;

/**
 * Classe qui gÃƒÆ’Ã‚Â¨re une partie
 *
 * @author ecrvnr
 *
 */
public class Partie extends JPanel implements MouseListener, MouseMotionListener {

    private Plateau plateau;
    private Joueur[] joueurs;
    private Joueur joueurActif;
    static int mouseX, mouseY, newMouseX, newMouseY;
    static int squareSize = 64;

    /**
     * Constructeur par dÃƒÆ’Ã‚Â©faut qui assigne des pseudos aux deux joueurs
     */
    public Partie() {
        plateau = new Plateau();
        joueurs = new Joueur[2];
        joueurs[0] = new JoueurHumain("Joueur 1", Couleur.BLANC);
        joueurs[1] = new JoueurAI("Joueur 2", Couleur.NOIR);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        for (int i = 0; i < 64; i += 2) {
            g.setColor(new Color(255, 200, 100));
            g.fillRect((i % 8 + (i / 8) % 2) * squareSize, (i / 8) * squareSize, squareSize, squareSize);
            g.setColor(new Color(150, 50, 30));
            g.fillRect(((i + 1) % 8 - ((i + 1) / 8) % 2) * squareSize, ((i + 1) / 8) * squareSize, squareSize, squareSize);
        }
        Image chessPiecesImage;
        chessPiecesImage = new ImageIcon("ChessPieces.png").getImage();
        for (int i = 0; i <= 7; i++) {
            int x = -1, y = -1;
            for (int j = 0; j <= 7; j++) {
                if (this.plateau.getPlateau()[i][j] == null) {

                } else {
                    switch (this.plateau.getPlateau()[i][j].getType()) {
                        case ROI:
                            x = 0;
                            y = (this.plateau.getPlateau()[i][j].getCouleur() == Couleur.NOIR) ? 1 : 0; // ShÃ¢h
                            break;

                        case CONSEILLER:
                            x = 1;
                            y = (this.plateau.getPlateau()[i][j].getCouleur() == Couleur.NOIR) ? 1 : 0; // Vizir
                            break;

                        case CAVALIER:
                            x = 4;
                            y = (this.plateau.getPlateau()[i][j].getCouleur() == Couleur.NOIR) ? 1 : 0; // Faras
                            break;

                        case ELEPHANT:
                            x = 3;
                            y = (this.plateau.getPlateau()[i][j].getCouleur() == Couleur.NOIR) ? 1 : 0;// Elephant
                            break;

                        case CHAR:
                            x = 2;
                            y = (this.plateau.getPlateau()[i][j].getCouleur() == Couleur.NOIR) ? 1 : 0; // Roukh
                            break;

                        case SOLDAT:
                            x = 5;
                            y = (this.plateau.getPlateau()[i][j].getCouleur() == Couleur.NOIR) ? 1 : 0; // Baidaq
                            break;
                    }
                    if (x != -1 && y != -1) {
                        // System.out.println(" i => " + i + " , j => " + j); // hna
                        g.drawImage(chessPiecesImage, j * squareSize, i * squareSize, (j + 1) * squareSize, (i + 1) * squareSize, x * 64, y * 64, (x + 1) * 64, (y + 1) * 64, this);
                    }
                }
            }

            if (i != 7) {

            }
        }
    }

    /**
     * Constructeur qui permet de choisir les pseudos des joueurs
     *
     * @param j1 pseudo du joueur 1
     * @param j2 pseudo du joueur 2
     */
    public Partie(String j1, String j2) {
        plateau = new Plateau();
        joueurs = new Joueur[2];
        joueurs[0] = new JoueurHumain(j1, Couleur.BLANC);
        joueurs[1] = new JoueurAI(j2, Couleur.NOIR);
        joueurActif = joueurs[0]; // le joueur 
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Getter pour le plateau
     *
     * @return Plateau
     */
    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * Deplace une piÃƒÆ’Ã‚Â¨ce sur le plateau
     *
     * @param depart coordonnÃƒÆ’Ã‚Â©es de la piece ÃƒÆ’Ã‚Â  deplacer
     * @param arrivee coordonnÃƒÆ’Ã‚Â©es d'arrivÃƒÆ’Ã‚Â©e de la piÃƒÆ’Ã‚Â¨ce
     */
    public void deplacerPiece(Coord depart, Coord arrivee) {
        plateau.deplacerPiece(depart, arrivee);
    }

    /**
     * MÃƒÆ’Ã‚Â©thode toString pour la partie. Retourne Plateau.toString() + la
     * saisie utilisateur si le joueur en cours est humain
     */
    public String toString() {
        return plateau.toString();
    }

    public void jouer(int x1, int y1, int x2, int y2) {
        joueurActif = joueurs[0]; // le joueur 
        while (!Regles.estEnEchecEtMat(plateau.trouverLeRoi(joueurActif.getCouleur()), plateau)) {
            System.out.print("C'est le tour du joueur ");
            if (joueurActif.getCouleur() == Couleur.BLANC) {
                System.out.println("blanc");
            } else {
                System.out.println("noir");
            }
            joueurActif.faireUnMouvementUI(plateau, x1, y1, x2, y2);
            repaint();
            tourSuivant();
            repaint();
            System.out.println(toString());
        }
        if (joueurActif.getCouleur() == Couleur.BLANC) {
            System.out.println("Le joueur blanc a perdu");
        } else {
            System.out.println("Le joueur noir a perdu");
        }
    }

    public void tourSuivant() {
        if (joueurActif == joueurs[0]) {
            joueurActif = joueurs[1];
        } else {
            joueurActif = joueurs[0];
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() < 8 * squareSize && e.getY() < 8 * squareSize) {
            //si sur le plateau
            mouseX = e.getX();
            mouseY = e.getY();
            //repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JavaChatrang.lblMsg.setText("");
        if ((!Regles.estEnEchecEtMat(plateau.trouverLeRoi(joueurActif.getCouleur()), plateau))) {
            //System.out.println(" " + (mouseX / squareSize) + " " + (mouseY / squareSize) + " " + (newMouseX / squareSize) + " " + (mouseY / squareSize));
            if (e.getX() < 8 * squareSize && e.getY() < 8 * squareSize) {
                //si sur le plateau
                Coord depart = new Coord(mouseY / squareSize, mouseX / squareSize);
                newMouseX = e.getX();
                newMouseY = e.getY();
                Coord arrivee = new Coord(newMouseY / squareSize, newMouseX / squareSize);
                if (joueurs[0].faireUnMouvementUI(plateau, depart.getX(), depart.getY(), arrivee.getX(), arrivee.getY())) {
                    repaint();
                    joueurs[1].faireUnMouvement(plateau);
                }
                repaint();
            }
        } else if (joueurActif.getCouleur() == Couleur.BLANC) {
            System.out.println("Le joueur blanc a perdu");
        } else {
            System.out.println("Le joueur noir a perdu");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public boolean aMoi(Plateau pl, Coord pos) {
        if (Regles.caseValide(pl, pos) && Regles.caseAllie(pl, pos, Couleur.BLANC)) {
            return true;
        } else {
            return false;
        }
    }
}
