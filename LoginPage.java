package chiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPage() {
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
        JLabel description = new JLabel("Helping you shape your future with the right guidance");
        description.setFont(new Font("Arial", Font.ITALIC, 18));
        description.setForeground(Color.WHITE);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create labels, fields, and buttons with the color palette
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setForeground(Color.WHITE);  // White text for better visibility
        
        // Create text field with rounded corners and outer box with shadow effect
        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        emailField.setBackground(new Color(255, 255, 255)); // White background
        emailField.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true)); // Blue border
        emailField.setOpaque(true);
        emailField.setCaretColor(Color.BLUE);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setForeground(Color.WHITE);  // White text for better visibility

        // Create password field with rounded corners and outer box with shadow effect
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordField.setBackground(new Color(255, 255, 255)); // White background
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true)); // Blue border

        // Create a login button with rounded corners and a blue color
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 25));
        loginButton.setBackground(new Color(0, 123, 255));  // Primary Blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createCompoundBorder(
                loginButton.getBorder(), 
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Extra padding inside the button
        ));

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // Check if email or password fields are empty
                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both email and password.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if email contains '@' symbol
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid email address containing '@'.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Proceed with login functionality if validation passes
                boolean authenticated = DatabaseUtil.isUserAuthenticated(email, password);

                if (authenticated) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");

                    // Redirect to EducationDetailsPage
                    EducationDetailsPage educationPage = new EducationDetailsPage();
                    educationPage.setVisible(true);
                    dispose(); // Close current LoginPage
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials!");
                }
            }
        });

        // Create a sign-up button with a secondary color
        JButton signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Arial", Font.PLAIN, 20));
        signupButton.setBackground(new Color(255, 127, 80)); // Secondary Orange color
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);
        signupButton.setBorderPainted(false);
        signupButton.setPreferredSize(new Dimension(200, 50));
        signupButton.setBorder(BorderFactory.createCompoundBorder(
                signupButton.getBorder(), 
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Extra padding inside the button
        ));
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open SignUp page
                SignUpPage signUpPage = new SignUpPage();
                signUpPage.setVisible(true);
                dispose(); // Close current LoginPage
            }
        });

        // Create GridBagConstraints to position components
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
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        gbc.gridy = 5;
        panel.add(signupButton, gbc);

        // Add the panel to the JFrame
        add(panel);
    }

    public static void main(String[] args) {
        // Set Look and Feel to System Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Run the login page
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }
}
