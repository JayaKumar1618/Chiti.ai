package chiti;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class WelcomePage extends JFrame {

    public WelcomePage() {
        // Set full-screen size and remove window controls
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true); // Remove window borders
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        // Create a panel to hold all components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240)); // Light Gray background

        // App name label with larger font and primary color
        JLabel appNameLabel = new JLabel("Chiti.ai", JLabel.CENTER);
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 100));
        appNameLabel.setForeground(new Color(0, 123, 255));  // Primary Blue color
        panel.add(appNameLabel, BorderLayout.NORTH);

        // Brief description of the app
        JLabel descriptionLabel = new JLabel("<html><div style='text-align:center;'>"
                + "<br>This is a career guidance app designed to help students "
                + "explore different career options, courses, exams, scholarships, and more."
                + "<br><br>Find your career path and make informed decisions.</div></html>", JLabel.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        descriptionLabel.setForeground(new Color(51, 51, 51));  // Dark Gray text color
        panel.add(descriptionLabel, BorderLayout.CENTER);

        // Create a login button with secondary color
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 30));
        loginButton.setBackground(new Color(255, 127, 80));  // Secondary Orange color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setPreferredSize(new Dimension(200, 50));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Play music
                playSound("pk.wav");

                // Open the LoginPage when the login button is clicked
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                dispose(); // Close the current WelcomePage
            }
        });

        // Add the login button to the bottom of the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Transparent background
        buttonPanel.add(loginButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the panel to the JFrame
        add(panel);
    }

    // Method to play sound from a file
    private void playSound(String soundFile) {
        try {
            URL soundURL = getClass().getResource("/resources/" + soundFile);
            if (soundURL != null) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } else {
                System.out.println("Sound file not found: " + soundFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method to run the WelcomePage
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomePage().setVisible(true);
            }
        });
    }
}
