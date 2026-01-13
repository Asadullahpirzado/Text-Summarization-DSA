import java.awt.*;
// This class manages the styles (fonts and colors) used throughout the application.
public class StyleManager {

    // This method returns the font style for the header section of the GUI.
    // It sets the font to "Times New Roman", bold, and size 38.
    public static Font getHeaderFont() {
        return new Font("Times New Roman", Font.BOLD, 38);  // Return header font style
    }

    // This method returns the font style for sections like input and summary.
    // It sets the font to "Times New Roman", bold, and size 19.
    public static Font getSectionFont() {
        return new Font("Times New Roman", Font.BOLD, 19);  // Return section font style
    }

    // This method returns the color used for the header background.
    // It returns a custom color using RGB values (34, 23, 83).
    public static Color getHeaderColor() {
        return new Color(34, 23, 83);  // Return header background color
    }

    // This method returns the color used for the overall background of the GUI.
    // It returns a predefined BLue color.
    public static Color getBackgroundColor() {
        return Color.BLUE;  // Return blue background color
    }

    // This method returns the color used for the text throughout the GUI.
    // It returns a predefined white color.
    public static Color getTextColor() {
        return Color.WHITE;  // Return white text color
    }

    // This method returns the color used for the buttons in the GUI.
    // It returns a custom green color using RGB values (0, 153, 86).
    public static Color getButtonColor() {
        return new Color(198, 163, 38);  // Return button color (green)
    }
}

