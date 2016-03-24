package javachatrang;

import graphique.UtilisateurInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import graphique.Partie;
import tools.Coord;

/**
 *
 * @author AMINE IT
 */
public class JavaChatrang {

    public static JLabel lblMsg;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Partie partie = new Partie("Humain", "IA_random");
        System.out.println(partie);
        JFrame f = new JFrame("Chtrang Rifi");
        f.setSize(new Dimension(512, 69 * 8));
        f.setLayout(new BorderLayout());
        f.setResizable(false);
        partie.setSize(new Dimension(64 * 8, 64 * 8));
        f.getContentPane().add(partie, BorderLayout.CENTER);
        lblMsg = new JLabel("");
        lblMsg.setForeground(Color.red);
        f.getContentPane().add(lblMsg, BorderLayout.SOUTH);
        Image icon = new ImageIcon("icon.png").getImage();
        f.setIconImage(icon);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // partie.jouer(ui,lblMsg);
    }

}
