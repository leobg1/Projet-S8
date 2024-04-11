package projetS8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PageAccueil extends JFrame {
    
	private static final long serialVersionUID = 1L; // Ajout du serialVersionUID
	 
    public PageAccueil() {
        setTitle("Page d'Accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Création des boutons
        JButton btnGestionComptes = new JButton("Gérer liste de comptes et MDP");
        JButton btnVisualiserHistorique = new JButton("Visualiser historique de connexion");
        JButton btnAfficherNombreClubs = new JButton("Afficher le nombre de clubs d'une fédération dans une commune");
        
        // Création du champ de texte
        JTextField textFieldCommune = new JTextField(20);
        
        // Création du panneau pour organiser les éléments
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Ajout de la marge
        
        // Ajout des éléments au panneau
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnGestionComptes, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(btnVisualiserHistorique, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Afficher le nombre de clubs d'une fédération dans une commune :"), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(textFieldCommune, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(btnAfficherNombreClubs, gbc);
        
        // Ajout du panneau à la fenêtre
        add(panel);
        
        // Redimensionner la fenêtre pour s'adapter au contenu
        pack();
        
        // Afficher la fenêtre au centre de l'écran
        setLocationRelativeTo(null);
        
        // Rendre la fenêtre visible
        setVisible(true);
        
        // Action lorsque le bouton "Afficher le nombre de clubs" est cliqué
        btnAfficherNombreClubs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String commune = textFieldCommune.getText();
                // Ici, vous pouvez mettre le code pour afficher le nombre de clubs dans la commune spécifiée
                // par exemple, une méthode pour effectuer une recherche dans une base de données ou un autre traitement
                JOptionPane.showMessageDialog(PageAccueil.this, "Vous avez entré : " + commune);
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PageAccueil();
            }
        });
    }
}
