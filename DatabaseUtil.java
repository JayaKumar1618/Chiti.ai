package chiti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/chiti";  // Database URL
    private static final String USER = "root";  // Database username
    private static final String PASSWORD = "root";  // Database password

    // Method to save education details
    public static boolean saveEducationDetails(String fullName, String contactNumber, String gender, String highestQualification,
                                               String highSchoolGrade, String highSchoolYear, String coursePreference,
                                               String countryPreference, String comments) {
        String query = "INSERT INTO education_details (full_name, contact_number, gender, highest_qualification, " +
                       "high_school_grade, high_school_year, course_preference, country_preference, comments) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, fullName);
            stmt.setString(2, contactNumber);
            stmt.setString(3, gender);
            stmt.setString(4, highestQualification);
            stmt.setString(5, highSchoolGrade);
            stmt.setString(6, highSchoolYear);
            stmt.setString(7, coursePreference);
            stmt.setString(8, countryPreference);
            stmt.setString(9, comments);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the details were successfully saved

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to check if a user is authenticated
    public static boolean isUserAuthenticated(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if a matching user is found

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to register a new user
    public static boolean registerUser(String email, String password) {
        String query = "INSERT INTO users (email, password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the user was successfully registered

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to save note content
    public static boolean saveNoteContent(String content) {
        String query = "INSERT INTO notes (content) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, content);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the note was successfully saved

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
