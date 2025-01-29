package chiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseCategoryPage extends JFrame {

    private JPanel contentPanel;
    private JPanel categoryPanel;
    private JPanel courseListPanel;
    private CardLayout cardLayout;

    public CourseCategoryPage() {
        setTitle("Course Categories");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);  // Remove default title bar
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with gradient background
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(255, 105, 180);
                Color color2 = new Color(30, 144, 255);
                GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Title Panel with Custom Title Bar
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);

        // Close and Minimize buttons
        JPanel titleBar = new JPanel();
        titleBar.setOpaque(false);
        titleBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton minimizeButton = new JButton("-");
        minimizeButton.setFont(new Font("Arial", Font.BOLD, 18));
        minimizeButton.setBackground(new Color(70, 130, 180));
        minimizeButton.setForeground(Color.WHITE);
        minimizeButton.setBorder(null);
        minimizeButton.setFocusPainted(false);
        minimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(JFrame.ICONIFIED); // Minimize window
            }
        });

        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Arial", Font.BOLD, 18));
        closeButton.setBackground(new Color(255, 69, 0));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorder(null);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the application
            }
        });

        titleBar.add(minimizeButton);
        titleBar.add(closeButton);
        titlePanel.add(titleBar, BorderLayout.EAST);

        JLabel appTitle = new JLabel("Choose Your Course Category");
        appTitle.setFont(new Font("Arial", Font.BOLD, 48));
        appTitle.setForeground(Color.WHITE);
        titlePanel.add(appTitle, BorderLayout.CENTER);

        // Category Panel
        categoryPanel = new JPanel();
        categoryPanel.setOpaque(false);
        categoryPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        addCategoryButton("B.Tech", gbc, 0, 0, new Color(255, 165, 0));
        addCategoryButton("Arts", gbc, 1, 0, new Color(255, 20, 147));
        addCategoryButton("BBA", gbc, 0, 1, new Color(0, 255, 255));
        addCategoryButton("B.Com", gbc, 1, 1, new Color(0, 255, 127));
        addCategoryButton("B.Sc", gbc, 0, 2, new Color(255, 99, 71));
        addCategoryButton("M.Sc", gbc, 1, 2, new Color(255, 69, 0));

        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 24));
        nextButton.setBackground(new Color(255, 69, 0));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setPreferredSize(new Dimension(150, 50));
        nextButton.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCareerGuidancePage();
            }
        });

        categoryPanel.add(nextButton, gbc);

        // Course List Panel
        courseListPanel = new JPanel();
        courseListPanel.setLayout(new BoxLayout(courseListPanel, BoxLayout.Y_AXIS));
        courseListPanel.setOpaque(false);
        JScrollPane courseScrollPane = new JScrollPane(courseListPanel);
        courseScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseScrollPane.setPreferredSize(new Dimension(800, 400));

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setOpaque(false);
        contentPanel.add(courseScrollPane, "Courses");

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(categoryPanel, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void addCategoryButton(String category, GridBagConstraints gbc, int x, int y, Color buttonColor) {
        JButton button = new JButton(category);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBackground(buttonColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(300, 80));
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCourses(category);
            }
        });
        gbc.gridx = x;
        gbc.gridy = y;
        categoryPanel.add(button, gbc);
    }

    private void showCourses(String category) {
        courseListPanel.removeAll();
        String[] courses = getCoursesForCategory(category);
        if (courses != null) {
            for (String course : courses) {
                JPanel courseCard = new JPanel();
                courseCard.setLayout(new BorderLayout());
                courseCard.setOpaque(false);
                JLabel courseLabel = new JLabel(course);
                courseLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                courseLabel.setForeground(Color.BLACK);
                courseLabel.setHorizontalAlignment(JLabel.CENTER);
                courseCard.add(courseLabel, BorderLayout.CENTER);
                courseCard.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2));
                courseCard.setBackground(new Color(255, 255, 255, 180));
                courseListPanel.add(courseCard);
            }
        }
        courseListPanel.revalidate();
        courseListPanel.repaint();
    }

    private String[] getCoursesForCategory(String category) {
        switch (category) {
            case "B.Tech":
                return new String[] {
                        "B.Tech in Computer Science",
                        "B.Tech in Electrical Engineering",
                        "B.Tech in Mechanical Engineering",
                        "B.Tech in Civil Engineering",
                        "B.Tech in Chemical Engineering",
                        "B.Tech in Information Technology",
                        "B.Tech in Electronics and Communication",
                        "B.Tech in Aerospace Engineering",
                        "B.Tech in Robotics",
                        "B.Tech in Biotechnology"
                };
            case "Arts":
                return new String[] {
                        "BA in English Literature",
                        "BA in History",
                        "BA in Political Science",
                        "BA in Psychology",
                        "BA in Sociology",
                        "BA in Philosophy",
                        "BA in Journalism",
                        "BA in Fine Arts",
                        "BA in Economics",
                        "BA in Linguistics"
                };
            case "BBA":
                return new String[] {
                        "BBA in Marketing",
                        "BBA in Human Resource Management",
                        "BBA in Finance",
                        "BBA in International Business",
                        "BBA in Operations Management",
                        "BBA in Entrepreneurship",
                        "BBA in Retail Management",
                        "BBA in Tourism",
                        "BBA in Event Management",
                        "BBA in Hospitality Management"
                };
            case "B.Com":
                return new String[] {
                        "B.Com in General",
                        "B.Com in Accounting",
                        "B.Com in Finance",
                        "B.Com in Taxation",
                        "B.Com in Banking and Insurance",
                        "B.Com in E-Commerce",
                        "B.Com in Corporate Accounting",
                        "B.Com in Investment Management",
                        "B.Com in Business Analytics",
                        "B.Com in International Business"
                };
            case "B.Sc":
                return new String[] {
                        "B.Sc in Physics",
                        "B.Sc in Chemistry",
                        "B.Sc in Biology",
                        "B.Sc in Mathematics",
                        "B.Sc in Environmental Science",
                        "B.Sc in Computer Science",
                        "B.Sc in Geology",
                        "B.Sc in Zoology",
                        "B.Sc in Botany",
                        "B.Sc in Microbiology"
                };
            case "M.Sc":
                return new String[] {
                        "M.Sc in Physics",
                        "M.Sc in Chemistry",
                        "M.Sc in Biology",
                        "M.Sc in Mathematics",
                        "M.Sc in Environmental Science",
                        "M.Sc in Computer Science",
                        "M.Sc in Data Science",
                        "M.Sc in Biotechnology",
                        "M.Sc in Genetics",
                        "M.Sc in Biochemistry"
                };
            default:
                return null;
        }
    }

    private void showCareerGuidancePage() {
        // New page with Career Guidance options
        JFrame guidanceFrame = new JFrame("Career Guidance");
        guidanceFrame.setSize(800, 600);
        guidanceFrame.setLocationRelativeTo(null);
        guidanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel guidancePanel = new JPanel();
        guidancePanel.setLayout(new GridLayout(3, 2, 20, 20));
        guidancePanel.setBackground(new Color(255, 255, 255));

        JButton scholarshipButton = createGuidanceButton("Scholarships");
        JButton articlesButton = createGuidanceButton("Articles");
        JButton notesButton = createGuidanceButton("Take Notes");
        JButton careerButton = createGuidanceButton("Career Opportunities");
        JButton resumeButton = createGuidanceButton("Build Resume");
        JButton skillButton = createGuidanceButton("Skill Development");

        guidancePanel.add(scholarshipButton);
        guidancePanel.add(articlesButton);
        guidancePanel.add(notesButton);
        guidancePanel.add(careerButton);
        guidancePanel.add(resumeButton);
        guidancePanel.add(skillButton);

        guidanceFrame.add(guidancePanel);
        guidanceFrame.setVisible(true);
    }

    private JButton createGuidanceButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(250, 70));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseCategoryPage().setVisible(true));
    }
}
