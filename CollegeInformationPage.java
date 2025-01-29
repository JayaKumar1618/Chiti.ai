package chiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CollegeInformationPage extends JFrame {

    // Constructor to set up the College Information Page
    public CollegeInformationPage() {
        // Set the window size (smaller size than full screen)
        setSize(900, 700);  // Set custom size
        setResizable(false);  // Make window non-resizable
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        // Set layout and color
        setLayout(new BorderLayout());

        // Creating a gradient background for the entire frame with a new gradient color
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(255, 105, 180); // Hot Pink for gradient
                Color color2 = new Color(255, 165, 0); // Orange for gradient
                GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(new BorderLayout());
        setContentPane(gradientPanel); // Set gradientPanel as the main content pane

        // Title Label for College Information (Changed text to "College Information")
        JLabel titleLabel = new JLabel("College Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));  // Smaller font size
        titleLabel.setForeground(new Color(255, 255, 255));  // White text
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(255, 105, 180));  // Hot pink background for the title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));  // Reduced spacing around title
        gradientPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a panel for college buttons with rounded corners and shadow effect
        JPanel collegePanel = new JPanel();
        collegePanel.setLayout(new BoxLayout(collegePanel, BoxLayout.Y_AXIS));  // Vertical layout
        collegePanel.setBackground(new Color(255, 255, 255));  // White background

        // List of colleges and their URLs
        String[] colleges = {
            "IIT Madras", "NIT Trichy", "IIITDM Kancheepuram", "VIT University Vellore",
            "Anna University, Chennai", "Amrita School of Engineering", "SRM Institute of Science and Technology",
            "Sastra University, Thanjavur", "B.S. Abdur Rahman Crescent Institute", 
            "Coimbatore Institute of Technology", "PSG College of Technology", "Rajalakshmi Engineering College", 
            "K.L. University", "Dr. M.G.R. Educational Institute", "Amity University, Chennai"
        };

        String[] urls = {
            "https://www.iitm.ac.in/", "https://www.nitt.edu/", "https://www.iiitdm.ac.in/", "https://www.vit.ac.in/",
            "https://www.annauniv.edu/", "https://www.amrita.edu/school/engineering", "https://www.srmist.edu.in/",
            "https://www.sastra.edu/", "https://www.bsauniv.ac.in/", "https://www.cit.edu.in/", "https://www.psgtech.edu/",
            "https://www.rajalarakshmi.edu.in/", "https://www.kluniversity.in/", "https://www.drmgrdu.ac.in/", 
            "https://www.amity.edu/chennai/"
        };

        // Adding buttons for each college with colorful box and hover effect
        for (int i = 0; i < colleges.length; i++) {
            // Create a panel for each college button with background color
            JPanel collegeBox = new JPanel();
            collegeBox.setLayout(new BorderLayout());
            collegeBox.setBackground(new Color(240, 248, 255));  // Light blue background for each college

            JButton collegeButton = new JButton(colleges[i]);
            collegeButton.setFont(new Font("Arial", Font.PLAIN, 20));  // Smaller font size
            collegeButton.setForeground(new Color(0, 0, 0));  // Black text
            collegeButton.setBackground(new Color(30, 144, 255));  // Dodger blue background for button
            collegeButton.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));  // Reduced padding
            collegeButton.setFocusable(false);

            // Adding hover effect to buttons
            collegeButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    collegeButton.setBackground(new Color(0, 191, 255)); // Darker blue on hover
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    collegeButton.setBackground(new Color(30, 144, 255)); // Default blue
                }
            });

            // Action listener for each college button
            final String url = urls[i];
            collegeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(java.net.URI.create(url));  // Open URL in browser
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            collegeBox.add(collegeButton, BorderLayout.CENTER);  // Add the button to the panel
            collegePanel.add(collegeBox);  // Add the colorful box to the main panel
        }

        // Scroll Pane to allow scrolling if the list is long
        JScrollPane scrollPane = new JScrollPane(collegePanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Reduced margin
        gradientPanel.add(scrollPane, BorderLayout.CENTER);

        // Close Button with shadow and rounded corners (Reduced size)
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));  // Smaller font size
        closeButton.setForeground(new Color(255, 255, 255));
        closeButton.setBackground(new Color(220, 53, 69));  // Red color for the button
        closeButton.setBorder(BorderFactory.createLineBorder(new Color(220, 53, 69), 2, true));  // Red border
        closeButton.setFocusable(false);
        closeButton.setPreferredSize(new Dimension(100, 40)); // Smaller size

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Exit the application when close button is clicked
            }
        });

        // Adding the close button to the top right with drop shadow effect
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setOpaque(false);
        closeButton.setBounds(getWidth() - 120, 10, 100, 40); // Top right corner, adjusted for visibility
        topPanel.add(closeButton);
        gradientPanel.add(topPanel, BorderLayout.NORTH);

        // Add a footer to enhance the look
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 165, 0));  // Orange footer to match gradient theme
        JLabel footerLabel = new JLabel("Powered by Chiti.ai", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));  // Smaller font size for footer
        footerLabel.setForeground(new Color(255, 255, 255));  // White text
        footerPanel.add(footerLabel);
        gradientPanel.add(footerPanel, BorderLayout.SOUTH);

        // Make the window visible
        setVisible(true);
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CollegeInformationPage();  // Create and display the College Information Page
            }
        });
    }
}
