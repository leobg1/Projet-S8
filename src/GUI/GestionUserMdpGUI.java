package GUI;

import java.awt.*;
import javax.swing.*;

public class GestionUserMdpGUI extends JFrame {
    
    private static final long serialVersionUID = 1L;
    final private Font mainFont = new Font("Free Serif", Font.BOLD, 18);
    JTextField tfSearchUser;
    JButton btnSearch;
    JLabel userDetailsNameLabel; // Label to display user name
    JLabel userDetailsLastNameLabel; // Label to display user last name
    JLabel userDetailsPasswordLabel; // Label to display user password
    JButton btnDeleteUser; // Button to delete user
    
    public void initialize() {
         
        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setOpaque(false);
        
        JLabel lbSearch = new JLabel("Search User:");
        lbSearch.setFont(mainFont);
        tfSearchUser = new JTextField(20);
        tfSearchUser.setFont(mainFont);
        btnSearch = new JButton("Search");
        btnSearch.setFont(mainFont);
        
        // Panel to display user details and delete button
        JPanel userDetailsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Utilisation d'un FlowLayout
        userDetailsPanel.setOpaque(false);
        
        userDetailsNameLabel = new JLabel("First Name:"); // Initialize the label for first name
        userDetailsNameLabel.setFont(mainFont);
        userDetailsLastNameLabel = new JLabel("Last Name:"); // Initialize the label for last name
        userDetailsLastNameLabel.setFont(mainFont);
        userDetailsPasswordLabel = new JLabel("Password:"); // Initialize the label for password
        userDetailsPasswordLabel.setFont(mainFont);
        
        btnDeleteUser = new JButton("Delete User");
        btnDeleteUser.setFont(mainFont);
        btnDeleteUser.setPreferredSize(new Dimension(150, 30)); // Set button size
        
        userDetailsPanel.add(userDetailsNameLabel);
        userDetailsPanel.add(userDetailsLastNameLabel);
        userDetailsPanel.add(userDetailsPasswordLabel);
        userDetailsPanel.add(btnDeleteUser);
        
        searchPanel.add(lbSearch);
        searchPanel.add(tfSearchUser);
        searchPanel.add(btnSearch);
        
        // Container to hold searchPanel and userDetailsPanel side by side
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(searchPanel, BorderLayout.WEST);
        mainPanel.add(userDetailsPanel, BorderLayout.CENTER);
        
        // Add the mainPanel to the frame
        add(mainPanel);
        
        // Set frame properties
        setTitle("Gestion User MDP GUI");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
     
    public static void main(String[] args) {
        GestionUserMdpGUI gestionUserMdpGUI = new GestionUserMdpGUI();
        gestionUserMdpGUI.initialize();
    }
}
