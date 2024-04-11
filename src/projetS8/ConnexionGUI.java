package projetS8;

// import des packages 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*; // import pour le GUI 

public class ConnexionGUI extends JFrame {

    private static final long serialVersionUID = 1L; // Ajout du serialVersionUID

    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    JTextField tfFirstName, tfLastName;
    JLabel lbWelcome;

    // Database credentials
    static final String DB_URL = "jdbc:mysql://localhost/your_database_name";
    static final String USER = "your_username"; //AJOUTER USERNAME  
    static final String PASS = "your_password"; //AJOUTER MDP

    public void initialize() {

        // FORM PANEL ...
        JLabel lbFirstName = new JLabel("First Name");
        lbFirstName.setFont(mainFont);

        tfFirstName = new JTextField();
        tfFirstName.setFont(mainFont);

        JLabel lbLastName = new JLabel("Last Name");
        lbLastName.setFont(mainFont);

        tfLastName = new JTextField();
        tfLastName.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 1, 5, 5));
        formPanel.setOpaque(false);
        formPanel.add(lbFirstName);
        formPanel.add(tfFirstName);
        formPanel.add(lbLastName);
        formPanel.add(tfLastName);

        // WELCOME LABEL ...
        lbWelcome = new JLabel();
        lbWelcome.setFont(mainFont);

        // Button Panel
        JButton btnOK = new JButton("OK");
        btnOK.setFont(mainFont);
        btnOK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = tfFirstName.getText();
                String lastName = tfLastName.getText();

                // authentif user
                boolean authenticated = authenticateUser(firstName, lastName);

                if (authenticated) {
                    lbWelcome.setText("Welcome, " + firstName + " " + lastName);
                } else {
                    lbWelcome.setText("Invalid username or password");
                }
            }

        });
        JButton btnClear = new JButton("Clear");
        btnClear.setFont(mainFont);
        btnClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tfFirstName.setText("");
                tfLastName.setText("");
                lbWelcome.setText("");
            }

        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 5, 5));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(btnOK);
        buttonsPanel.add(btnClear);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(128, 128, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(lbWelcome, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setTitle("Welcome");
        setSize(500, 500);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // methode identification avec DB 
    private boolean authenticateUser(String firstName, String lastName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean authenticated = false;

        try {
 
            Class.forName("com.mysql.jdbc.Driver");

            // Connexion à la DB 
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // requete SQL 
            String sql = "SELECT * FROM users WHERE first_name=? AND last_name=?"; //check req sql avec les bonnes tables 
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            rs = stmt.executeQuery();

            // verif de l'existence de user
            if (rs.next()) {
                authenticated = true;
            }
        } catch (SQLException se) {
            // exceptions 
            se.printStackTrace();
        } catch (Exception e) {
            // exceptions 
            e.printStackTrace();
        } finally {
            // fermeture
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return authenticated;
    }

    public static void main(String[] args) {
        ConnexionGUI myFrame = new ConnexionGUI();
        myFrame.initialize();
    }
}
