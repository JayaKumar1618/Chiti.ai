package chiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public HomePage() {
        setTitle("Welcome to Chiti.ai");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Title Panel
        JLabel title = new JLabel("Chiti.ai Login");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(title, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (DatabaseUtil.isUserAuthenticated(email, password)) {
                    JOptionPane.showMessageDialog(HomePage.this, "Login successful!");
                    new EducationDetailsPage().setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(HomePage.this, "Invalid credentials, please try again.");
                }
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 20));
        registerButton.setBackground(new Color(255, 127, 80));
        registerButton.setForeground(Color.WHITE);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (DatabaseUtil.registerUser(email, password)) {
                    JOptionPane.showMessageDialog(HomePage.this, "Registration successful! You can now log in.");
                } else {
                    JOptionPane.showMessageDialog(HomePage.this, "Registration failed, please try again.");
                }
            }
        });

        buttonsPanel.add(loginButton);
        buttonsPanel.add(registerButton);

        // Add Panels to Main Panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }
}
