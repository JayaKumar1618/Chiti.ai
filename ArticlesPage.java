package chiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArticlesPage extends JFrame {

    public ArticlesPage() {
        // Set title, size, and fullscreen
        setTitle("Chiti.ai - Educational Articles");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen mode
        setUndecorated(true); // Optionally remove window borders for a clean fullscreen effect
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with background color
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255)); // White background for main content

        // Header Panel with App name and background color
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(0, 123, 255)); // Vibrant blue

        JLabel appNameLabel = new JLabel("Chiti.ai", JLabel.CENTER);
        appNameLabel.setFont(new Font("Helvetica", Font.BOLD, 48));
        appNameLabel.setForeground(Color.WHITE);

        // Short description for the page
        JPanel keywordPanel = new JPanel();
        keywordPanel.setOpaque(false);
        JLabel keywordLabel = new JLabel("<html><font color='white'>Explore Educational Articles | Stay Informed</font></html>");
        keywordLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        keywordPanel.add(keywordLabel);

        headerPanel.add(appNameLabel, BorderLayout.NORTH);
        headerPanel.add(keywordPanel, BorderLayout.SOUTH);

        // Content Area for Articles
        JPanel articlesPanel = new JPanel();
        articlesPanel.setLayout(new GridLayout(0, 1, 15, 15)); // Grid layout with spacing
        articlesPanel.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(articlesPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Adding sample articles with card-like design
        String[] articleTitles = {
            "The Future of Online Education: Trends to Watch in 2024",
            "Why STEM Education is the Key to a Successful Career",
            "5 Tips for Balancing Online Learning with a Full-Time Job",
            "How Technology is Revolutionizing the Education System",
            "The Importance of Mental Health in Education",
            "How to Choose the Right College for Your Future",
            "The Role of Artificial Intelligence in Education",
            "The Impact of Globalization on Education Systems",
            "Why Lifelong Learning is Critical in the 21st Century",
            "Top 10 Scholarships You Should Apply for in 2024"
        };

        // Add each article as a card
        for (String title : articleTitles) {
            JPanel cardPanel = new JPanel();
            cardPanel.setLayout(new BorderLayout());
            cardPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1)); // Light gray border
            cardPanel.setBackground(new Color(255, 255, 255)); // White background for card
            cardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            cardPanel.setPreferredSize(new Dimension(300, 150));

            // Article title label
            JLabel articleTitle = new JLabel(title, JLabel.CENTER);
            articleTitle.setFont(new Font("Arial", Font.BOLD, 16));
            articleTitle.setForeground(new Color(0, 123, 255)); // Blue color for title
            articleTitle.setOpaque(true);
            articleTitle.setBackground(new Color(245, 245, 245)); // Light gray background for title
            articleTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Action for the card (on click)
            articleTitle.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    displayArticle(title);
                }

                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    cardPanel.setBackground(new Color(240, 240, 240)); // Hover effect
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    cardPanel.setBackground(Color.WHITE); // Reset background
                }
            });

            // Add title to card panel
            cardPanel.add(articleTitle, BorderLayout.CENTER);
            articlesPanel.add(cardPanel);
        }

        // Close button at the top-right corner
        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Arial", Font.BOLD, 12)); // Smaller font
        closeButton.setBackground(new Color(220, 53, 69)); // Red color for close button
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setPreferredSize(new Dimension(40, 40)); // Smaller button size
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add action listener for close button to open CareerGuidancePage
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCareerGuidancePage();
            }
        });

        // Footer with subtle branding and gradient background
        JPanel footerPanel = new JPanel();
        footerPanel.setOpaque(true);
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(0, 123, 255)); // Blue footer background

        JLabel footerLabel = new JLabel("Chiti.ai - Empowering Your Career with Knowledge");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        footerLabel.setForeground(new Color(255, 255, 255)); // White text
        footerPanel.add(footerLabel);

        // Add header, articles, footer, and close button to the main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER); // Scrollable content area
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        mainPanel.add(closeButton, BorderLayout.EAST);

        add(mainPanel);
    }

    // Display the article content when a title is clicked
    private void displayArticle(String title) {
        // Example articles for display
        String articleContent = getArticleContent(title);

        // Display the article content in a new window
        JFrame articleWindow = new JFrame(title);
        articleWindow.setSize(700, 500);
        articleWindow.setLocationRelativeTo(null);

        JTextArea articleArea = new JTextArea(articleContent);
        articleArea.setFont(new Font("Arial", Font.PLAIN, 16));
        articleArea.setEditable(false);
        articleArea.setLineWrap(true);
        articleArea.setWrapStyleWord(true);

        // Scrollable text area
        JScrollPane scrollPane = new JScrollPane(articleArea);
        articleWindow.add(scrollPane);

        articleWindow.setVisible(true);
    }

    // Simulate fetching article content based on title
    private String getArticleContent(String title) {
        switch (title) {
            case "The Future of Online Education: Trends to Watch in 2024":
                return "Online education is transforming in 2024 with new technologies and methodologies, such as AI-powered tutoring, virtual classrooms, and personalized learning experiences...";
            case "Why STEM Education is the Key to a Successful Career":
                return "STEM (Science, Technology, Engineering, and Mathematics) education provides students with critical thinking, problem-solving, and technical skills that are in high demand in today's job market...";
            case "5 Tips for Balancing Online Learning with a Full-Time Job":
                return "Balancing online learning and a full-time job requires time management, discipline, and utilizing the flexibility of online courses. Here are five practical tips to help...";
            case "How Technology is Revolutionizing the Education System":
                return "Technology is reshaping education through online platforms, digital resources, and interactive learning tools. From virtual labs to mobile apps, here's how tech is revolutionizing education...";
            case "The Importance of Mental Health in Education":
                return "Mental health plays a crucial role in the success of students. A focus on emotional well-being ensures that students can manage stress, stay motivated, and excel academically...";
            case "How to Choose the Right College for Your Future":
                return "Choosing the right college involves evaluating factors such as location, tuition costs, courses offered, campus culture, and future career opportunities. Here’s a guide to making the right decision...";
            case "The Role of Artificial Intelligence in Education":
                return "AI is transforming education by providing personalized learning experiences, automating administrative tasks, and enhancing student engagement through smart tutoring systems...";
            case "The Impact of Globalization on Education Systems":
                return "Globalization has led to the integration of diverse educational practices and exchange programs across countries, influencing curricula, teaching methods, and student mobility...";
            case "Why Lifelong Learning is Critical in the 21st Century":
                return "In the rapidly changing world of work, lifelong learning is essential for career advancement and personal growth. Here’s why continual learning is vital in today's economy...";
            case "Top 10 Scholarships You Should Apply for in 2024":
                return "Here are ten scholarships that every student should consider applying for in 2024. These scholarships cover various fields of study, and can make your educational dreams more affordable...";
            default:
                return "Article content is not available for this title.";
        }
    }

    // Open CareerGuidancePage when Close button is clicked
    private void openCareerGuidancePage() {
        // Close the current window
        dispose();

        // Open CareerGuidancePage
        new CareerGuidancePage().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ArticlesPage().setVisible(true);
        });
    }
}
