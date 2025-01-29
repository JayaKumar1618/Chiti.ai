package chiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CareerGuidancePage extends JFrame {

    public CareerGuidancePage() {
        setTitle("Chiti.ai Career Guidance");
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximize window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel (with background gradient)
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(34, 193, 195); // Aqua
                Color color2 = new Color(253, 187, 45); // Yellow
                GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Header Section with app name and keywords
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel appNameLabel = new JLabel("Chiti.ai", JLabel.CENTER);
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 48));
        appNameLabel.setForeground(Color.WHITE);

        JPanel keywordPanel = new JPanel();
        keywordPanel.setOpaque(false);
        JLabel keywordLabel = new JLabel("<html><font color='white'>Career Growth | Opportunities | Guidance</font></html>");
        keywordLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        keywordPanel.add(keywordLabel);

        headerPanel.add(appNameLabel, BorderLayout.NORTH);
        headerPanel.add(keywordPanel, BorderLayout.SOUTH);

        // Centered Navigation Panel with colorful buttons for different options
        JPanel navPanel = new JPanel(new GridBagLayout());
        navPanel.setOpaque(false);

        // Set up GridBagConstraints to center the buttons and make the layout more attractive
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);  // Spacing between buttons

        // Button for Courses Available
        JButton btnCourses = new JButton("Courses Available");
        customizeButton(btnCourses, new Color(70, 130, 180));  // Steel Blue
        btnCourses.addActionListener(e -> handleButtonClick("Courses"));
        navPanel.add(btnCourses, gbc);

        gbc.gridy++;

        // Button for Scholarships
        JButton btnScholarships = new JButton("Scholarships");
        customizeButton(btnScholarships, new Color(255, 99, 71));  // Tomato
        btnScholarships.addActionListener(e -> handleButtonClick("Scholarships"));
        navPanel.add(btnScholarships, gbc);

        gbc.gridy++;

        // Button for Articles
        JButton btnArticles = new JButton("Articles");
        customizeButton(btnArticles, new Color(123, 104, 238));  // Purple
        btnArticles.addActionListener(e -> handleButtonClick("Articles"));
        navPanel.add(btnArticles, gbc);

        gbc.gridy++;

        // Button for Take Notes
        JButton btnTakeNotes = new JButton("Take Notes");
        customizeButton(btnTakeNotes, new Color(34, 193, 195));  // Aqua
        btnTakeNotes.addActionListener(e -> handleButtonClick("Take Notes"));
        navPanel.add(btnTakeNotes, gbc);

        gbc.gridy++;

        // Button for Career Opportunities
        JButton btnCareerOpportunities = new JButton("Career Opportunities");
        customizeButton(btnCareerOpportunities, new Color(255, 165, 0));  // Orange
        btnCareerOpportunities.addActionListener(e -> handleButtonClick("Career Opportunities"));
        navPanel.add(btnCareerOpportunities, gbc);

        gbc.gridy++;

        // Button for Colleges Information
        JButton btnCollegesInfo = new JButton("Colleges Information");
        customizeButton(btnCollegesInfo, new Color(138, 43, 226));  // BlueViolet
        btnCollegesInfo.addActionListener(e -> handleButtonClick("Colleges Information"));
        navPanel.add(btnCollegesInfo, gbc);

        // Add the header and nav panel to the main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(navPanel, BorderLayout.CENTER);

        // Footer Panel for additional info or branding
        JPanel footerPanel = new JPanel();
        footerPanel.setOpaque(false);
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel footerLabel = new JLabel("Empowering Your Career with Chiti.ai!");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Customize button to make them visually attractive
    private void customizeButton(JButton button, Color color) {
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(250, 60));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Handle button click action
    private void handleButtonClick(String option) {
        switch (option) {
            case "Courses":
                new CourseCategoryPage().setVisible(true);  // Open CourseCategoryPage
                break;
            case "Scholarships":
                new ScholarshipInformationPage().setVisible(true);  // Open ScholarshipInformationPage
                break;
            case "Articles":
                new ArticlesPage().setVisible(true);  // Open ArticlesPage
                break;
            case "Take Notes":
                new NoteTakingPage().setVisible(true);  // Open NoteTakingPage
                break;
            case "Career Opportunities":
                new CareerOpportunitiesPage().setVisible(true);  // Open CareerOpportunitiesPage
                break;
            case "Colleges Information":
                new CollegeInformationPage().setVisible(true);  // Open CollegeInformationPage
                break;
            default:
                JOptionPane.showMessageDialog(this, "Option not available.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CareerGuidancePage().setVisible(true));
    }
}
