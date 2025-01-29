package chiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EducationDetailsPage extends JFrame {

    private JTextField fullNameField, contactNumberField, highSchoolGradeField, highSchoolYearField;
    private JComboBox<String> highestQualificationComboBox, coursePreferenceComboBox, countryComboBox;
    private JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    private JTextArea commentsTextArea;
    private ButtonGroup genderGroup;

    public EducationDetailsPage() {
        // Set full-screen size and remove window controls
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with gradient background
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 123, 255);
                Color color2 = new Color(255, 127, 80);
                GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        JLabel appTitle = new JLabel("Chiti.ai - Educational Details");
        appTitle.setFont(new Font("Arial", Font.BOLD, 40));
        appTitle.setForeground(Color.WHITE);
        titlePanel.add(appTitle);

        // Form Panel with GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add Fields to the Form
        fullNameField = new JTextField(20);
        addField(formPanel, gbc, "Full Name:", fullNameField);

        contactNumberField = new JTextField(20);
        addField(formPanel, gbc, "Contact Number:", contactNumberField);

        // Gender Selection
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        otherRadioButton = new JRadioButton("Other");
        maleRadioButton.setOpaque(false);
        femaleRadioButton.setOpaque(false);
        otherRadioButton.setOpaque(false);
        maleRadioButton.setForeground(Color.WHITE);
        femaleRadioButton.setForeground(Color.WHITE);
        otherRadioButton.setForeground(Color.WHITE);
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setOpaque(false);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.add(otherRadioButton);
        addField(formPanel, gbc, "Gender:", genderPanel);

        // Highest Qualification
        String[] qualifications = {"High School", "Diploma", "Bachelor's", "Master's"};
        highestQualificationComboBox = new JComboBox<>(qualifications);
        addField(formPanel, gbc, "Highest Qualification:", highestQualificationComboBox);

        // High School Grade
        highSchoolGradeField = new JTextField(20);
        addField(formPanel, gbc, "Grade/Percentage (High School):", highSchoolGradeField);

        // High School Year
        highSchoolYearField = new JTextField(20);
        addField(formPanel, gbc, "Year of Passing High School:", highSchoolYearField);

        // Course Preference
        String[] courses = {"B.Tech", "B.Sc", "BA", "BBA", "B.Com"};
        coursePreferenceComboBox = new JComboBox<>(courses);
        addField(formPanel, gbc, "Preferred Course:", coursePreferenceComboBox);

        // Preferred Country
        String[] countries = {"India", "USA", "UK", "Canada", "Australia"};
        countryComboBox = new JComboBox<>(countries);
        addField(formPanel, gbc, "Preferred Country:", countryComboBox);

        // Additional Comments
        commentsTextArea = new JTextArea(5, 20);
        commentsTextArea.setLineWrap(true);
        commentsTextArea.setWrapStyleWord(true);
        JScrollPane commentsScrollPane = new JScrollPane(commentsTextArea);
        addField(formPanel, gbc, "Additional Comments:", commentsScrollPane);

        // Save Button
        JButton saveButton = new JButton("Save Details");
        saveButton.setFont(new Font("Arial", Font.BOLD, 25));
        saveButton.setBackground(new Color(0, 123, 255));
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEducationDetails();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(saveButton);

        // Assemble Panels
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent component) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(label, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(component, gbc);
    }

    private void saveEducationDetails() {
        // Check if all fields are filled
        if (fullNameField.getText().isEmpty() || contactNumberField.getText().isEmpty() ||
            highSchoolGradeField.getText().isEmpty() || highSchoolYearField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        // Validate phone number (exactly 10 digits)
        String contactNumber = contactNumberField.getText();
        if (contactNumber.length() != 10 || !contactNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid 10-digit phone number.");
            return;
        }

        String gender = maleRadioButton.isSelected() ? "Male" : femaleRadioButton.isSelected() ? "Female" : "Other";
        String highestQualification = (String) highestQualificationComboBox.getSelectedItem();
        String highSchoolGrade = highSchoolGradeField.getText();
        String highSchoolYear = highSchoolYearField.getText();
        String coursePreference = (String) coursePreferenceComboBox.getSelectedItem();
        String countryPreference = (String) countryComboBox.getSelectedItem();
        String comments = commentsTextArea.getText();

        boolean success = DatabaseUtil.saveEducationDetails(fullNameField.getText(), contactNumber, gender, highestQualification,
                highSchoolGrade, highSchoolYear, coursePreference, countryPreference, comments);

        if (success) {
            JOptionPane.showMessageDialog(this, "Details saved successfully!");
            new CareerGuidancePage().setVisible(true);
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save details.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EducationDetailsPage().setVisible(true);
            }
        });
    }
}
