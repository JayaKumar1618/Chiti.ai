package chiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteTakingPage extends JFrame {

    private JTextArea notesTextArea;

    public NoteTakingPage() {
        // Set window title and make it fullscreen
        setTitle("Chiti.ai - Take Notes");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen mode
        setUndecorated(true); // No borders for sleek look
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255)); // White background

        // Header Panel (Changed to show "Notes" title)
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(0, 123, 255)); // Blue header background
        headerPanel.setPreferredSize(new Dimension(getWidth(), 100)); // Adjust header height

        JLabel headerLabel = new JLabel("Notes", JLabel.CENTER); // Changed text to "Notes"
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 48));
        headerLabel.setForeground(Color.WHITE);

        // No description in the header now
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        // Text Area for Note Taking (Further reduced size)
        notesTextArea = new JTextArea();
        notesTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Reduced font size
        notesTextArea.setLineWrap(true);
        notesTextArea.setWrapStyleWord(true);
        notesTextArea.setBackground(new Color(240, 240, 240)); // Light gray background
        notesTextArea.setForeground(new Color(34, 34, 34)); // Dark text color for good contrast
        notesTextArea.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1)); // Light border
        notesTextArea.setPreferredSize(new Dimension(250, 80)); // Further reduced text area size

        // Scroll pane to make the text area scrollable
        JScrollPane scrollPane = new JScrollPane(notesTextArea);
        scrollPane.setPreferredSize(new Dimension(300, 120)); // Further reduced scrollable area
        scrollPane.setBackground(new Color(255, 255, 255));

        // Button Panel for all three buttons placed at the top-right corner
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // Align buttons to the right with some spacing
        buttonPanel.setBackground(new Color(255, 255, 255)); // White background

        // Save Notes Button
        JButton saveNotesButton = new JButton("Save Notes");
        saveNotesButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveNotesButton.setBackground(new Color(40, 167, 69)); // Green background
        saveNotesButton.setForeground(Color.WHITE);
        saveNotesButton.setPreferredSize(new Dimension(120, 40)); // Equal size to Reset and Close buttons
        saveNotesButton.setFocusPainted(false);
        saveNotesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setBackground(new Color(220, 53, 69)); // Red background
        resetButton.setForeground(Color.WHITE);
        resetButton.setPreferredSize(new Dimension(120, 40)); // Equal size to Save and Close buttons
        resetButton.setFocusPainted(false);
        resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Exit Button in the top-right corner
        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14)); // Font size for the button
        closeButton.setBackground(new Color(220, 53, 69)); // Red background for close button
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setPreferredSize(new Dimension(40, 40)); // Smaller button size
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Action for Close button
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCareerGuidancePage();
            }
        });

        // Action for Save Notes button
        saveNotesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String notes = notesTextArea.getText();
                if (!notes.trim().isEmpty()) {
                    if (DatabaseUtil.saveNoteContent(notes)) {
                        JOptionPane.showMessageDialog(NoteTakingPage.this, "Notes saved successfully!");
                    } else {
                        JOptionPane.showMessageDialog(NoteTakingPage.this, "Failed to save notes. Try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(NoteTakingPage.this, "Please enter some notes before saving.");
                }
            }
        });

        // Action for Reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notesTextArea.setText(""); // Clear the notes text area
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(saveNotesButton);  // Add Save button
        buttonPanel.add(resetButton);     // Add Reset button
        buttonPanel.add(closeButton);     // Add Close button

        // Footer with subtle branding and gradient background
        JPanel footerPanel = new JPanel();
        footerPanel.setOpaque(true);
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(0, 123, 255)); // Blue footer background

        JLabel footerLabel = new JLabel("Chiti.ai - Empowering Your Career with Knowledge");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        footerLabel.setForeground(new Color(255, 255, 255)); // White text
        footerPanel.add(footerLabel);

        // Adding all components to the main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER); // Content area with scrollable text
        mainPanel.add(buttonPanel, BorderLayout.NORTH); // Add the button panel to the top (right corner)
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Adding main panel to the frame
        add(mainPanel);
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
            new NoteTakingPage().setVisible(true);
        });
    }
}
