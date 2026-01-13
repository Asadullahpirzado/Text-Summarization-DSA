// This class is used to process text and count the number of words in a given string.
public class WordProcessor {

    // This method counts the number of words in a given text.
    public int countWords(String text) {
        // Check if the input text is null or empty
        if (text == null || text.isEmpty()) {
            return 0; // If no text, return 0 words
        }

        // Remove leading and trailing spaces, then split the text by spaces
        // The split uses the regular expression "\\s+" which matches any whitespace character (spaces, tabs, etc.)
        String[] words = text.trim().split("\\s+");

        // Return the number of words by checking the length of the words array
        return words.length;
    }
}
