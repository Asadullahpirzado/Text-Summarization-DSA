import javax.swing.*; // Swing library for GUI components.
import java.awt.*; // AWT library for layout and graphics.
import java.awt.event.ActionEvent; // For handling button events.

public class TextSummarizerApp {

    // Fields to manage text summarization and word processing
    private final SummaryGenerator summaryGenerator = new SummaryGenerator(); // Generates summaries for input text.
    private final WordProcessor wordProcessor = new WordProcessor(); // Counts words in the input and summary text.
    private boolean isDarkMode = true;  // Default theme is dark mode.

    public void createAndShowGUI() {
        // **Frame (Main Window)**:
        JFrame frame = new JFrame("Text Summarizer Created By AOR"); // Application title.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application when the frame is closed.
        frame.setSize(1000, 700); // Set the size of the window.
        frame.setLayout(new BorderLayout()); // Use a BorderLayout to organize the components.

        // **Title Panel**:
        JPanel titlePanel = UIComponents.createPanel(
            new BorderLayout(), 
            isDarkMode ? Color.DARK_GRAY : Color.LIGHT_GRAY // Background color based on the theme.
        );
        JLabel titleLabel = new JLabel("Text Summarizer"); // Application title label.
        titleLabel.setFont(StyleManager.getHeaderFont()); // Set a custom font for the title.
        titleLabel.setForeground(isDarkMode ? Color.WHITE : Color.BLACK); // Title text color based on the theme.
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the label.
        titlePanel.add(titleLabel, BorderLayout.CENTER); // Add the title label to the panel.

        // **Input and Summary Text Areas**:
        JTextArea inputTextArea = UIComponents.createTextArea(); // Text area for user input.
        JTextArea summaryTextArea = UIComponents.createTextArea(); // Text area for displaying the summary.
        summaryTextArea.setEditable(false); // Summary text area is read-only.

        // Labels to display word counts.
        JLabel inputWordCountLabel = new JLabel("Word Count: 0"); 
        JLabel summaryWordCountLabel = new JLabel("Word Count: 0");

        // Create input and summary sections with their respective labels.
        JPanel inputPanel = UIComponents.createTextSection("                                       Input Passage", inputTextArea, inputWordCountLabel);
        JPanel summaryPanel = UIComponents.createTextSection("                                     Generated Summary", summaryTextArea, summaryWordCountLabel);

        // **Bottom Panel (Buttons)**:
        JPanel buttonPanel = UIComponents.createPanel(
            new FlowLayout(), 
            isDarkMode ? Color.DARK_GRAY : Color.LIGHT_GRAY // Background color for the button panel.
        );
        JButton summarizeButton = UIComponents.createButton("Summarize"); // Button to generate summary.
        JButton clearButton = UIComponents.createButton("Clear"); // Button to clear input and summary areas.
        JButton themeToggleButton = UIComponents.createButton("Toggle Theme"); // Button to toggle between dark and light themes.

        // Add buttons to the panel.
        buttonPanel.add(summarizeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(themeToggleButton);

        // **Progress Bar**:
        JProgressBar progressBar = new JProgressBar(); // Progress bar for visual feedback.
        progressBar.setStringPainted(true); // Display progress percentage on the bar.
        progressBar.setVisible(false); // Initially hidden.
        buttonPanel.add(progressBar); // Add the progress bar to the button panel.

        // **Action Listeners**:
        
        // Listener for the "Summarize" button.
        summarizeButton.addActionListener((ActionEvent e) -> {
            String inputText = inputTextArea.getText().trim(); // Get the trimmed input text.
            if (inputText.isEmpty()) { // Show an error if no input is provided.
                JOptionPane.showMessageDialog(frame, "Please enter text to summarize.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Show and initialize the progress bar.
            progressBar.setVisible(true);
            progressBar.setIndeterminate(false);
            progressBar.setValue(0);

            // **Background Task for Summary Generation**:
            SwingWorker<String, Void> worker = new SwingWorker<>() {
                @Override
                protected String doInBackground() throws Exception {
                    // Simulate progress steps (e.g., 10 steps for feedback).
                    for (int i = 0; i <= 100; i += 10) {
                        Thread.sleep(100); // Simulate work delay.
                        progressBar.setValue(i); // Update progress bar.
                    }
                    return summaryGenerator.generateSummary(inputText); // Generate the summary.
                }

                @Override
                protected void done() {
                    try {
                        String summary = get(); // Retrieve the generated summary.
                        summaryTextArea.setText(summary); // Display the summary in the text area.

                        // Update word count labels.
                        inputWordCountLabel.setText("                                                    Word Count: " + wordProcessor.countWords(inputText));
                        summaryWordCountLabel.setText("                                                  Word Count: " + wordProcessor.countWords(summary));

                        progressBar.setVisible(false); // Hide progress bar after completion.
                    } catch (Exception ex) {
                        ex.printStackTrace(); // Print stack trace for debugging.
                        JOptionPane.showMessageDialog(frame, "An error occurred while generating the summary.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            };
            worker.execute(); // Start the background task.
        });

        // Listener for the "Clear" button to reset input and output areas.
        clearButton.addActionListener(e -> {
            inputTextArea.setText(""); // Clear the input area.
            summaryTextArea.setText(""); // Clear the summary area.
            inputWordCountLabel.setText("                             Word Count: 0");
            summaryWordCountLabel.setText("                           Word Count: 0");
            progressBar.setValue(0); // Reset progress bar value.
            progressBar.setVisible(false); // Hide progress bar.
        });

        // Listener for the "Toggle Theme" button to switch between themes.
        themeToggleButton.addActionListener(e -> {
            isDarkMode = !isDarkMode; // Toggle the dark mode flag.
            updateTheme(frame, inputTextArea, summaryTextArea, inputWordCountLabel, summaryWordCountLabel, titlePanel, buttonPanel); // Apply the theme change.
        });

        // **Main Layout**:
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // Two columns for input and summary sections.
        mainPanel.add(inputPanel); // Add the input section.
        mainPanel.add(summaryPanel); // Add the summary section.

        // Add all components to the frame.
        frame.add(titlePanel, BorderLayout.NORTH); // Title at the top.
        frame.add(mainPanel, BorderLayout.CENTER); // Input and summary sections in the center.
        frame.add(buttonPanel, BorderLayout.SOUTH); // Buttons at the bottom.

        updateTheme(frame, inputTextArea, summaryTextArea, inputWordCountLabel, summaryWordCountLabel, titlePanel, buttonPanel); // Apply theme to all components.
        frame.setVisible(true); // Show the GUI window.
    }

    // **Theme Update Method**:
    private void updateTheme(JFrame frame, JTextArea inputTextArea, JTextArea summaryTextArea, JLabel inputWordCountLabel, JLabel summaryWordCountLabel, JPanel titlePanel, JPanel buttonPanel) {
        Color backgroundColor = isDarkMode ? Color.BLACK : Color.WHITE; // Background color.
        Color textColor = isDarkMode ? Color.WHITE : Color.BLACK; // Text color.
        Color panelColor = isDarkMode ? Color.BLACK : Color.LIGHT_GRAY; // Panel background.
        Color buttonColor = isDarkMode ? new Color(0, 153, 76) : new Color(0, 102, 204); // Button color.

        frame.getContentPane().setBackground(backgroundColor); // Update background of the main window.
        inputTextArea.setBackground(backgroundColor); // Input area background.
        inputTextArea.setForeground(textColor); // Input area text color.
        summaryTextArea.setBackground(backgroundColor); // Summary area background.
        summaryTextArea.setForeground(textColor); // Summary area text color.
        inputWordCountLabel.setForeground(textColor); // Input word count label text color.
        summaryWordCountLabel.setForeground(textColor); // Summary word count label text color.

        titlePanel.setBackground(panelColor); // Title panel background.
        buttonPanel.setBackground(panelColor); // Button panel background.

        // Update button colors.
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JButton) {
                comp.setBackground(buttonColor);
                comp.setForeground(Color.WHITE); // Button text color.
            }
        }
    }
}
