package chiti;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChitiApp {

    public static void main(String[] args) {
        // Play sound when the application starts
        playSound("C:\\Users\\Admin\\Documents\\NetBeansProjects\\Chiti\\src\\chiti\\pk.wav");
        
        // Launch the WelcomePage (first screen of the app)
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.setVisible(true);
    }

    // Method to get the database connection
    public static Connection getConnection() {
        try {
            // Define the JDBC URL, username, and password for MySQL
            String url = "jdbc:mysql://localhost:3306/chiti";  // Make sure the database name is correct
            String username = "root";  // Use your MySQL username
            String password = "root";  // Use your MySQL password

            // Return the connection object
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // Print any SQL exceptions for debugging purposes
            e.printStackTrace();
        }
        return null; // If connection fails, return null
    }

    // Method to play sound
    public static void playSound(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
