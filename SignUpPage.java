package chiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public SignUpPage() {
        // Set full-screen size and remove window controls
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true); // Remove window borders
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel with a gradient background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Gradient background
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 123, 255); // Primary Blue color
                Color color2 = new Color(255, 127, 80); // Secondary Orange color
                GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240)); // Light Gray background

        // Create a header label with app name and description
        JLabel appTitle = new JLabel("Chiti.ai - Career Guidance App");
        appTitle.setFont(new Font("Arial", Font.BOLD, 40));
        appTitle.setForeground(Color.WHITE);
        appTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add a subtitle
        JLabel description = new JLabel("Create your account to get personalized guidance");
        description.setFont(new Font("Arial", Font.ITALIC, 18));
        description.setForeground(Color.WHITE);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create labels, fields, and buttons
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setForeground(Color.WHITE);

        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        emailField.setBackground(new Color(255, 255, 255));
        emailField.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true));
        emailField.setCaretColor(Color.BLUE);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setForeground(Color.WHITE);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true));

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        confirmPasswordLabel.setForeground(Color.WHITE);

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 20));
        confirmPasswordField.setBackground(new Color(255, 255, 255));
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true));

        // Create a sign-up button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 25));
        signUpButton.setBackground(new Color(0, 123, 255));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.setBorder(BorderFactory.createCompoundBorder(
                signUpButton.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

                // Validate empty fields
                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check email format
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid email address containing '@'.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate passwords match
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Register the new user
                boolean isRegistered = DatabaseUtil.registerUser(email, password);

                if (isRegistered) {
                    JOptionPane.showMessageDialog(null, "Sign Up Successful!");
                    new LoginPage().setVisible(true); // Redirect to Login page
                    dispose(); // Close current SignUpPage
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Unable to register user.");
                }
            }
        });

        // Create a login redirect button
        JButton loginButton = new JButton("Already have an account? Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        loginButton.setBackground(new Color(255, 127, 80));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setPreferredSize(new Dimension(350,60));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage().setVisible(true); // Open Login page
                dispose(); // Close current SignUpPage
            }
        });

        // Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(appTitle, gbc);

        gbc.gridy = 1;
        panel.add(description, gbc);

        gbc.gridy = 2;
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        panel.add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(signUpButton, gbc);

        gbc.gridy = 6;
        panel.add(loginButton, gbc);

        add(panel);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new SignUpPage().setVisible(true));
    }
}
