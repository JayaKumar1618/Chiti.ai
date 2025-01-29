package chiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CareerOpportunitiesPage extends JFrame {

    // Constructor to set up the Career Opportunities Page
    public CareerOpportunitiesPage() {
        // Set the window size
        setSize(900, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        // Set layout and color
        setLayout(new BorderLayout());

        // Creating a gradient background for the entire frame
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

        // Title Label for Career Opportunities
        JLabel titleLabel = new JLabel("Career Opportunities", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));  // Smaller font size
        titleLabel.setForeground(new Color(255, 255, 255));  // White text
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0, 191, 255));  // Dodger blue background for the title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));  // Reduced spacing around title
        gradientPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a panel for career opportunity buttons with rounded corners and shadow effect
        JPanel careerPanel = new JPanel();
        careerPanel.setLayout(new BoxLayout(careerPanel, BoxLayout.Y_AXIS));  // Vertical layout
        careerPanel.setBackground(new Color(255, 255, 255));  // White background

        // List of career opportunities and their URLs (Example data, modify as needed)
        String[] careerOpportunities = {
            "Software Engineer", "Data Scientist", "Product Manager",
            "Graphic Designer", "Web Developer", "Marketing Specialist",
            "HR Specialist", "Business Analyst", "Project Manager",
            "AI Researcher", "UX Designer", "Cybersecurity Expert",
            "DevOps Engineer", "Full Stack Developer", "Cloud Architect",
            "Blockchain Developer", "Financial Analyst", "Data Analyst",
            "IT Support Specialist", "Machine Learning Engineer", "Game Developer",
            "Content Writer", "SEO Specialist", "Mobile App Developer"
        };

        String[] urls = {
            "https://www.linkedin.com/jobs/software-engineer-jobs", "https://www.linkedin.com/jobs/data-scientist-jobs",
            "https://www.linkedin.com/jobs/product-manager-jobs", "https://www.linkedin.com/jobs/graphic-designer-jobs",
            "https://www.linkedin.com/jobs/web-developer-jobs", "https://www.linkedin.com/jobs/marketing-specialist-jobs",
            "https://www.linkedin.com/jobs/hr-specialist-jobs", "https://www.linkedin.com/jobs/business-analyst-jobs",
            "https://www.linkedin.com/jobs/project-manager-jobs", "https://www.linkedin.com/jobs/ai-researcher-jobs",
            "https://www.linkedin.com/jobs/ux-designer-jobs", "https://www.linkedin.com/jobs/cybersecurity-expert-jobs",
            "https://www.linkedin.com/jobs/devops-engineer-jobs", "https://www.linkedin.com/jobs/full-stack-developer-jobs",
            "https://www.linkedin.com/jobs/cloud-architect-jobs", "https://www.linkedin.com/jobs/blockchain-developer-jobs",
            "https://www.linkedin.com/jobs/financial-analyst-jobs", "https://www.linkedin.com/jobs/data-analyst-jobs",
            "https://www.linkedin.com/jobs/it-support-specialist-jobs", "https://www.linkedin.com/jobs/machine-learning-engineer-jobs",
            "https://www.linkedin.com/jobs/game-developer-jobs", "https://www.linkedin.com/jobs/content-writer-jobs",
            "https://www.linkedin.com/jobs/seo-specialist-jobs", "https://www.linkedin.com/jobs/mobile-app-developer-jobs"
        };

        // Adding buttons for each career opportunity with colorful box and hover effect
        for (int i = 0; i < careerOpportunities.length; i++) {
            JPanel careerBox = new JPanel();
            careerBox.setLayout(new BorderLayout());
            careerBox.setBackground(new Color(240, 248, 255));  // Light blue background for each career

            JButton careerButton = new JButton(careerOpportunities[i]);
            careerButton.setFont(new Font("Arial", Font.PLAIN, 20));  // Smaller font size
            careerButton.setForeground(new Color(0, 0, 0));  // Black text
            careerButton.setBackground(new Color(30, 144, 255));  // Dodger blue background for button
            careerButton.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));  // Reduced padding
            careerButton.setFocusable(false);

            // Adding hover effect to buttons
            careerButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    careerButton.setBackground(new Color(0, 191, 255)); // Darker blue on hover
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    careerButton.setBackground(new Color(30, 144, 255)); // Default blue
                }
            });

            // Action listener for each career opportunity button
            final String url = urls[i];
            careerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(java.net.URI.create(url));  // Open URL in browser
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            careerBox.add(careerButton, BorderLayout.CENTER);  // Add the button to the panel
            careerPanel.add(careerBox);  // Add the colorful box to the main panel
        }

        // Scroll Pane to allow scrolling if the list is long
        JScrollPane scrollPane = new JScrollPane(careerPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Reduced margin
        gradientPanel.add(scrollPane, BorderLayout.CENTER);

        // Close Button with shadow and rounded corners
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
                new CareerOpportunitiesPage();  // Create and display the Career Opportunities Page
            }
        });
    }
}
