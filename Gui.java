package chiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Gui extends JFrame {
    private JPanel mainPanel;

    public Gui() {
        setTitle("Chiti.ai - Career Development App");

        // Set full screen size
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JPanel(new CardLayout());
        mainPanel.setBackground(new Color(63, 81, 181)); // Deep blue background

        JPanel homePanel = createHomePanel();
        JPanel coursesPanel = createCoursesPanel();
        JPanel examsPanel = createExamsPanel();
        JPanel scholarshipsPanel = createScholarshipsPanel();

        mainPanel.add(homePanel, "Home");
        mainPanel.add(coursesPanel, "Courses");
        mainPanel.add(examsPanel, "Exams");
        mainPanel.add(scholarshipsPanel, "Scholarships");

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(63, 81, 181)); // Deep blue background

        // Title
        JLabel title = new JLabel("Welcome to Chiti.ai", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(new Color(255, 87, 34)); // Bright orange

        // Buttons for navigation
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(63, 81, 181));

        JButton coursesButton = new JButton("Courses");
        coursesButton.setFont(new Font("Arial", Font.BOLD, 18));
        coursesButton.addActionListener(e -> switchPanel("Courses"));

        JButton examsButton = new JButton("Exams");
        examsButton.setFont(new Font("Arial", Font.BOLD, 18));
        examsButton.addActionListener(e -> switchPanel("Exams"));

        JButton scholarshipsButton = new JButton("Scholarships");
        scholarshipsButton.setFont(new Font("Arial", Font.BOLD, 18));
        scholarshipsButton.addActionListener(e -> switchPanel("Scholarships"));

        buttonPanel.add(coursesButton);
        buttonPanel.add(examsButton);
        buttonPanel.add(scholarshipsButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCoursesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(63, 81, 181));

        JPanel coursesListPanel = new JPanel();
        coursesListPanel.setLayout(new BoxLayout(coursesListPanel, BoxLayout.Y_AXIS));
        coursesListPanel.setBackground(new Color(255, 255, 255, 180)); // Transparent white background

        try (Connection conn = ChitiApp.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM courses")) {

            while (rs.next()) {
                String courseName = rs.getString("course_name");
                String category = rs.getString("category");
                String description = rs.getString("description");

                JPanel coursePanel = new JPanel();
                coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
                coursePanel.setBackground(new Color(255, 255, 255));
                coursePanel.add(new JLabel("Course: " + courseName));
                coursePanel.add(new JLabel("Category: " + category));
                coursePanel.add(new JTextArea("Description: " + description));
                coursePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                coursesListPanel.add(coursePanel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> switchPanel("Home"));

        panel.add(new JScrollPane(coursesListPanel), BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createExamsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(63, 81, 181));

        JPanel examsListPanel = new JPanel();
        examsListPanel.setLayout(new BoxLayout(examsListPanel, BoxLayout.Y_AXIS));
        examsListPanel.setBackground(new Color(255, 255, 255, 180));

        try (Connection conn = ChitiApp.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM exams")) {

            while (rs.next()) {
                String examName = rs.getString("exam_name");
                String category = rs.getString("category");
                String description = rs.getString("description");

                JPanel examPanel = new JPanel();
                examPanel.setLayout(new BoxLayout(examPanel, BoxLayout.Y_AXIS));
                examPanel.setBackground(new Color(255, 255, 255));
                examPanel.add(new JLabel("Exam: " + examName));
                examPanel.add(new JLabel("Category: " + category));
                examPanel.add(new JTextArea("Description: " + description));
                examPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                examsListPanel.add(examPanel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> switchPanel("Home"));

        panel.add(new JScrollPane(examsListPanel), BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createScholarshipsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(63, 81, 181));

        JPanel scholarshipsListPanel = new JPanel();
        scholarshipsListPanel.setLayout(new BoxLayout(scholarshipsListPanel, BoxLayout.Y_AXIS));
        scholarshipsListPanel.setBackground(new Color(255, 255, 255, 180));

        try (Connection conn = ChitiApp.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM scholarships")) {

            while (rs.next()) {
                String scholarshipName = rs.getString("scholarship_name");
                String eligibilityCriteria = rs.getString("eligibility_criteria");
                double amount = rs.getDouble("scholarship_amount");

                JPanel scholarshipPanel = new JPanel();
                scholarshipPanel.setLayout(new BoxLayout(scholarshipPanel, BoxLayout.Y_AXIS));
                scholarshipPanel.setBackground(new Color(255, 255, 255));
                scholarshipPanel.add(new JLabel("Scholarship: " + scholarshipName));
                scholarshipPanel.add(new JLabel("Eligibility: " + eligibilityCriteria));
                scholarshipPanel.add(new JLabel("Amount: ₹" + amount));
                scholarshipPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                scholarshipsListPanel.add(scholarshipPanel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> switchPanel("Home"));

        panel.add(new JScrollPane(scholarshipsListPanel), BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private void switchPanel(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Gui::new);
    }
}
