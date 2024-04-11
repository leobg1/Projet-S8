package projetS8;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class UserSearchGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    JTextField tfSearch;
    JTextArea taUserInfo;
    JButton btnSearch;

    // Database credentials
    static final String DB_URL = "jdbc:mysql://localhost/your_database_name";
    static final String USER = "your_username";
    static final String PASS = "your_password";

    public void initialize() {

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setOpaque(false);

        JLabel lbSearch = new JLabel("Search User:");
        lbSearch.setFont(mainFont);
        tfSearch = new JTextField(20);
        tfSearch.setFont(mainFont);
        btnSearch = new JButton("Search");
        btnSearch.setFont(mainFont);

        searchPanel.add(lbSearch);
        searchPanel.add(tfSearch);
        searchPanel.add(btnSearch);

        // User Info Panel
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BorderLayout());
        userInfoPanel.setOpaque(false);

        taUserInfo = new JTextArea(10, 30);
        taUserInfo.setFont(mainFont);
        taUserInfo.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taUserInfo);

        userInfoPanel.add(scrollPane);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(128, 128, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(userInfoPanel, BorderLayout.CENTER);
        

        add(mainPanel);

        setTitle("User Search");
        setSize(500, 500);
        setMinimumSize(new Dimension(400, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

        // Add action listener to search button
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = tfSearch.getText();
                if (!searchQuery.isEmpty()) {
                    displayUserInfo(searchQuery);
                } else {
                    JOptionPane.showMessageDialog(UserSearchGUI.this, "Please enter a search query.");
                }
            }
        });
    }

    // Method to fetch user info and display
    private void displayUserInfo(String searchQuery) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query to retrieve user information
            String sql = "SELECT * FROM users WHERE username=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, searchQuery);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String userInfo = "User: " + rs.getString("username") + "\n";

                // Fetch user's last connections
                userInfo += "Last Connections:\n";
                List<String> lastConnections = getLastConnections(rs.getInt("user_id"), conn);
                for (String connection : lastConnections) {
                    userInfo += connection + "\n";
                }

                taUserInfo.setText(userInfo);
            } else {
                taUserInfo.setText("User not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving user information.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    // Method to fetch user's last connections
    private List<String> getLastConnections(int userId, Connection conn) throws SQLException {
        List<String> lastConnections = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Execute SQL query to retrieve last connections
        String sql = "SELECT * FROM connections WHERE user_id=? ORDER BY connection_time DESC LIMIT 5";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);
        rs = stmt.executeQuery();

        while (rs.next()) {
            String connectionInfo = rs.getString("connection_time");
            lastConnections.add(connectionInfo);
        }

        return lastConnections;
    }

    public static void main(String[] args) {
        UserSearchGUI userSearchGUI = new UserSearchGUI();
        userSearchGUI.initialize();
    }
}
