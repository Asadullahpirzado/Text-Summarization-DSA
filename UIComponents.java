import javax.swing.*;
import java.awt.*;
// This class provides utility methods to create common UI components like panels, text areas, buttons, etc.
public class UIComponents {

    // This method creates a JPanel with a given layout manager and background color.
    // It is used to create panels for different sections of the GUI.
    public static JPanel createPanel(LayoutManager layout, Color bgColor) {
        // Create a new JPanel with the specified layout
        JPanel panel = new JPanel(layout);
        // Set the background color of the panel
        panel.setBackground(bgColor);
        // Return the created panel
        return panel;
    }

    // This method creates a JTextArea with predefined settings like font, color, etc.
    public static JTextArea createTextArea() {
        // Create a JTextArea with 10 rows and 30 columns
        JTextArea textArea = new JTextArea(10, 30);
        // Set the background color of the text area using StyleManager class
        textArea.setBackground(StyleManager.getBackgroundColor());
        // Set the foreground (text) color using StyleManager class
        textArea.setForeground(StyleManager.getTextColor());
        // Set the font style for the text area using StyleManager class
        textArea.setFont(StyleManager.getSectionFont());
        // Enable word wrapping when text reaches the end of the line
        textArea.setLineWrap(true);
        // Ensure that wrapping occurs at word boundaries rather than cutting in the middle of words
        textArea.setWrapStyleWord(true);
        // Return the created text area
        return textArea;
    }

    // This method creates a JButton with the specified text and applies styling.
    public static JButton createButton(String text) {
        // Create a new button with the given text
        JButton button = new JButton(text);
        // Set the background color of the button using StyleManager class
        button.setBackground(StyleManager.getButtonColor());
        // Set the text color (foreground) of the button to white
        button.setForeground(Color.WHITE);
        // Set the font style for the button using StyleManager class
        button.setFont(StyleManager.getSectionFont());
        // Return the created button
        return button;
    }

    // This method creates a panel that contains a title, a text area, and a word count label.
    // It is used to create sections like the "Input Passage" and "Generated Summary" panels.
    public static JPanel createTextSection(String title, JTextArea textArea, JLabel wordCountLabel) {
        // Create a new panel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        // Set the background color of the panel using StyleManager class
        panel.setBackground(StyleManager.getBackgroundColor());

        // Create a label for the section title
        JLabel label = new JLabel(title);
        // Set the font style for the label using StyleManager class
        label.setFont(StyleManager.getSectionFont());
        // Set the text color (foreground) of the label using StyleManager class
        label.setForeground(StyleManager.getTextColor());

        // Create a JScrollPane to wrap the text area, allowing scrolling if the text is too long
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add the label to the top of the panel (NORTH)
        panel.add(label, BorderLayout.NORTH);
        // Add the scroll pane (containing the text area) to the center of the panel
        panel.add(scrollPane, BorderLayout.CENTER);
        // Add the word count label to the bottom of the panel (SOUTH)
        panel.add(wordCountLabel, BorderLayout.SOUTH);

        // Return the created text section panel
        return panel;
    }
}
