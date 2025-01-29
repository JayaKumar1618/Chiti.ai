package chiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ScholarshipInformationPage extends JFrame {

    // Constructor to set up the Scholarship Information Page
    public ScholarshipInformationPage() {
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
                Color color1 = new Color(0, 191, 255); // Dodger blue for gradient
                Color color2 = new Color(255, 105, 180); // Hot Pink for gradient
                GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(new BorderLayout());
        setContentPane(gradientPanel); // Set gradientPanel as the main content pane

        // Title Label for Scholarship Information (Changed text to "Scholarship Information")
        JLabel titleLabel = new JLabel("Scholarship Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));  // Smaller font size
        titleLabel.setForeground(new Color(255, 255, 255));  // White text
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0, 191, 255));  // Dodger blue background for the title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));  // Reduced spacing around title
        gradientPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a panel for scholarship buttons with rounded corners and shadow effect
        JPanel scholarshipPanel = new JPanel();
        scholarshipPanel.setLayout(new BoxLayout(scholarshipPanel, BoxLayout.Y_AXIS));  // Vertical layout
        scholarshipPanel.setBackground(new Color(255, 255, 255));  // White background

        // List of scholarships and their URLs
        String[] scholarships = {
            "KVPY Fellowship", "Merit Cum Means Scholarships for Professional and Technical Courses", 
            "Ramanujan Fellowship", "PM Scholarship Scheme for CAPF and Assam Rifles", 
            "Legrand Empowering Scholarship Program", "MIT-WPU BBA Scholarship", 
            "NSP Scholarships for Top-Class Education", "Swami Vivekananda Merit Cum Means Scholarship", 
            "Amity University Scholarships", "HDFC Bank Parivartan ECSS Program",
            "Tata Trusts Scholarships", "Google India Scholarships", "Fellowship for Students with Disabilities"
        };

        String[] urls = {
            "https://www.kvpy.iisc.ernet.in/", "https://scholarships.gov.in/", "https://www.serb.gov.in/", 
            "https://www.civildefence.nic.in/", "https://www.legrand.co.in/scholarship", "https://www.mitwpu.edu.in/",
            "https://www.nsp.gov.in/", "https://wbmdfc.org.in/svmcm/", "https://www.amity.edu/", 
            "https://www.hdfcbank.com/personal/loans/education-loans",
            "https://www.tatatrusts.org/", "https://www.google.com/edu/scholarships", "https://www.ncpedp.org/"
        };

        // Adding buttons for each scholarship with colorful box and hover effect
        for (int i = 0; i < scholarships.length; i++) {
            // Create a panel for each scholarship button with background color
            JPanel scholarshipBox = new JPanel();
            scholarshipBox.setLayout(new BorderLayout());
            scholarshipBox.setBackground(new Color(240, 248, 255));  // Light blue background for each scholarship

            JButton scholarshipButton = new JButton(scholarships[i]);
            scholarshipButton.setFont(new Font("Arial", Font.PLAIN, 20));  // Smaller font size
            scholarshipButton.setForeground(new Color(0, 0, 0));  // Black text
            scholarshipButton.setBackground(new Color(30, 144, 255));  // Dodger blue background for button
            scholarshipButton.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));  // Reduced padding
            scholarshipButton.setFocusable(false);

            // Adding hover effect to buttons
            scholarshipButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    scholarshipButton.setBackground(new Color(0, 191, 255)); // Darker blue on hover
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    scholarshipButton.setBackground(new Color(30, 144, 255)); // Default blue
                }
            });

            // Action listener for each scholarship button
            final String url = urls[i];
            scholarshipButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(java.net.URI.create(url));  // Open URL in browser
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            scholarshipBox.add(scholarshipButton, BorderLayout.CENTER);  // Add the button to the panel
            scholarshipPanel.add(scholarshipBox);  // Add the colorful box to the main panel
        }

        // Scroll Pane to allow scrolling if the list is long
        JScrollPane scrollPane = new JScrollPane(scholarshipPanel);
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
                new ScholarshipInformationPage();  // Create and display the Scholarship Information Page
            }
        });
    }
}
