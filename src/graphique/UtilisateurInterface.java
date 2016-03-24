package graphique;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import javachatrang.JavaChatrang;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jeu.Piece;
import jeu.Plateau;
import jeu.Regles;
import tools.Coord;
import tools.Couleur;

/**
 *
 * @author AMINE IT
 */
public class UtilisateurInterface extends JPanel implements MouseListener, MouseMotionListener {
    
    static int mouseX, mouseY, newMouseX, newMouseY;
    static int squareSize = 64;
    private Piece[][] plateau;
    private Plateau p;
    
    public UtilisateurInterface(Plateau p) {
        this.p = p;
        this.plateau = p.getPlateau();
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
                if (this.plateau[i][j] == null) {
                    
                } else {
                    switch (this.plateau[i][j].getType()) {
                        case ROI:
                            x = 4;
                            y = (this.plateau[i][j].getCouleur() == Couleur.NOIR) ? 1 : 0; // ShÃ¢h
                            break;
                        
                        case CONSEILLER:
                            x = 1;
                            y = (this.plateau[i][j].getCouleur() == Couleur.NOIR) ? 1 : 0; // Vizir
                            break;
                        
                        case CAVALIER:
                            x = 4;
                            y = (this.plateau[i][j].getCouleur() == Couleur.NOIR) ? 1 : 0; // Faras
                            break;
                        
                        case ELEPHANT:
                            x = 0;
                            y = (this.plateau[i][j].getCouleur() == Couleur.NOIR) ? 1 : 0;// Elephant
                            break;
                        
                        case CHAR:
                            x = 2;
                            y = (this.plateau[i][j].getCouleur() == Couleur.NOIR) ? 1 : 0; // Roukh
                            break;
                        
                        case SOLDAT:
                            x = 3;
                            y = (this.plateau[i][j].getCouleur() == Couleur.NOIR) ? 1 : 0; // Baidaq
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
        boolean pieceOk;
        boolean mouvOk = false;
        System.out.println(" " + (mouseX / squareSize) + " " + (mouseY / squareSize) + " " + (newMouseX / squareSize) + " " + (mouseY / squareSize));
        if (e.getX() < 8 * squareSize && e.getY() < 8 * squareSize) {
            //si sur le plateau
            Coord depart = new Coord(mouseY / squareSize, mouseX / squareSize);
            newMouseX = e.getX();
            newMouseY = e.getY();
            Coord arrivee = new Coord(newMouseY / squareSize, newMouseX / squareSize);
            //System.out.println(" " + (mouseX / squareSize) + " " + (mouseY / squareSize) + " " + (newMouseX / squareSize) + " " + (mouseY / squareSize));
            if (aMoi(p, depart)) {
                pieceOk = true;
            } else {
                //javax.swing.JOptionPane.showMessageDialog(null, "La case choisi ne contient pas de piece a vous", "Chtrang", javax.swing.JOptionPane.ERROR_MESSAGE);
                pieceOk = false;
                //JavaChatrang.lblMsg.setText("La case choisi ne contient pas de piece a vous");
            }
            if (pieceOk) {
                ArrayList<Coord> mouvPossible = Regles.mouvementsPossible(p, p.getPiece(depart));
                for (int i = 0; i < mouvPossible.size(); i++) {
                    //System.out.println(mouvPossible.get(i).getX()+" "+mouvPossible.get(i).getY());
                    if (mouvPossible.get(i).getX() == arrivee.getX() && mouvPossible.get(i).getY() == arrivee.getY()) {
                        mouvOk = true;
                    }
                }
                if (mouvOk) {
                    p.deplacerPiece(depart, arrivee);
                } else {
                    //javax.swing.JOptionPane.showMessageDialog(null, "Mouvement invalide ou entraînant l'echec de votre roi, recommencez", "Chtrang", javax.swing.JOptionPane.ERROR_MESSAGE);
                    JavaChatrang.lblMsg.setText("Mouvement invalide ou entraînant l'echec de votre roi, recommencez");
                }
            }
            repaint();
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
