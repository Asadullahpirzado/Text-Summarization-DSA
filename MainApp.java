// Main class that starts the application and displays the GUI.
public class MainApp {
    // Main method to start the program
    public static void main(String[] args) {
        // This ensures the GUI is created and shown on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Create an instance of the TextSummarizerApp class
            TextSummarizerApp app = new TextSummarizerApp();
            // Call the createAndShowGUI method to display the GUI
            app.createAndShowGUI();
        });
    }
}
