package GUI;

import java.awt.*;
import javax.swing.*;

public class UserSearchGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    final private Font mainFont = new Font("Free Serif", Font.BOLD, 18);
    JTextField tfSearch;
    JTextArea taUserInfo;
    JButton btnSearch;


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


    }

   

    public static void main(String[] args) {
        UserSearchGUI userSearchGUI = new UserSearchGUI();
        userSearchGUI.initialize();
    }
}
