package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PageAccueilGUI extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private final Font mainFont = new Font("Free Serif", Font.BOLD, 18);

    public PageAccueilGUI() {
        setTitle("Page d'Accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Création des boutons avec leurs textes correspondants
        JButton btnGestionComptes = new JButton("Gérer liste de comptes et MDP");
        JButton btnVisualiserHistorique = new JButton("Visualiser historique de connexion");
        JButton btnAfficherNombreClubs = new JButton("Afficher le nombre de clubs d'une fédération dans une commune");
        
        // Appliquer le style visuel avec la police de caractères principale
        btnGestionComptes.setFont(mainFont);
        btnVisualiserHistorique.setFont(mainFont);
        btnAfficherNombreClubs.setFont(mainFont);
        
        // Création du champ de texte pour saisir le nom de la commune
        JTextField textFieldCommune = new JTextField(20);
        textFieldCommune.setFont(mainFont);
        
        // Création du JPanel avec GridBagLayout comme gestionnaire de disposition
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Ajout de la marge
        
        // Ajout des éléments au panneau avec des contraintes de grille spécifiées
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnGestionComptes, gbc);
        
        // Augmentation de la marge inférieure pour le bouton "Visualiser historique"
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets.bottom = 50; // Ajout d'une marge inférieure supplémentaire de 20 pixels
        panel.add(btnVisualiserHistorique, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Afficher le nombre de clubs d'une fédération dans une commune, saisir le nom de commune ci-dessous :"), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(textFieldCommune, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(btnAfficherNombreClubs, gbc);
        
        // Appliquer le style visuel avec la couleur de fond du panneau
        panel.setBackground(new Color(128, 128, 255));
        
        // Ajout du panneau à la fenêtre principale
        add(panel);
        
        // Ajustement de la taille de la fenêtre en fonction de son contenu et centrage sur l'écran
        pack();
        setLocationRelativeTo(null);
        
        // Rendre la fenêtre visible
        setVisible(true);
        
        // Ajout d'un écouteur d'événements au bouton "Afficher le nombre de clubs"
        btnAfficherNombreClubs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupération du texte saisi dans le champ de texte pour le nom de la commune
                String commune = textFieldCommune.getText();
                // Affichage d'un message avec le nom de la commune saisie
                JOptionPane.showMessageDialog(PageAccueilGUI.this, "Vous avez entré : " + commune);
            }
        });
    }
    
    // Méthode principale pour exécuter l'application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PageAccueilGUI(); // Création d'une instance de PageAccueil
            }
        });
    }
}
