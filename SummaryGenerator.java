import java.util.*; // Importing utility classes for data structures like Map, HashMap, ArrayList, and PriorityQueue.

public class SummaryGenerator {

    // This method generates a summary from the input text.
    public String generateSummary(String inputText) {
        // Step 1: Split input into sentences
        // Using a regex pattern to split text into sentences while avoiding splitting titles like "Mr.", "Mrs.", "Dr."
        String[] sentences = inputText.split("(?<!Mr|Mrs|Dr)\\.\\s+");

        // Step 2: Count word frequencies
        // Creating a map to store word frequencies (word as key, frequency as value).
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String sentence : sentences) { // Loop through each sentence.
            String[] words = sentence.split("\\s+"); // Split the sentence into words using whitespace.
            for (String word : words) { // Loop through each word in the sentence.
                word = word.toLowerCase().replaceAll("[^a-zA-Z]", ""); // Normalize word (convert to lowercase, remove non-alphabetic characters).
                if (!word.isEmpty()) { // Check if the word is not empty after normalization.
                    // Increment the frequency of the word, defaulting to 0 if the word is not already in the map.
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }
        }

        // Step 3: Rank sentences based on word importance
        // Creating a map to store sentence scores (sentence as key, score as value).
        Map<String, Integer> sentenceScores = new HashMap<>();
        for (String sentence : sentences) { // Loop through each sentence.
            int score = 0; // Initialize score for the sentence.
            String[] words = sentence.split("\\s+"); // Split the sentence into words.
            for (String word : words) { // Loop through each word in the sentence.
                word = word.toLowerCase().replaceAll("[^a-zA-Z]", ""); // Normalize word.
                if (wordFrequency.containsKey(word)) { // If the word exists in the word frequency map.
                    score += wordFrequency.get(word); // Add the frequency of the word to the sentence's score.
                }
            }
            sentenceScores.put(sentence, score); // Store the calculated score for the sentence in the map.
        }

        // Step 4: Select top-ranked sentences
        // Limit the summary to half of the total sentences.
        int summaryLength = sentences.length / 2;

        // Create a priority queue to rank sentences by their scores in descending order.
        PriorityQueue<Map.Entry<String, Integer>> rankedSentences = new PriorityQueue<>(
            Map.Entry.comparingByValue(Comparator.reverseOrder()) // Comparator to sort by score in reverse order.
        );
        rankedSentences.addAll(sentenceScores.entrySet()); // Add all sentence-score pairs to the priority queue.

        // List to store the selected top-ranked sentences.
        List<String> summarySentences = new ArrayList<>();
        for (int i = 0; i < summaryLength && !rankedSentences.isEmpty(); i++) {
            // Poll the top entry from the queue and add its key (the sentence) to the summary list.
            summarySentences.add(rankedSentences.poll().getKey());
        }

        // Step 5: Return the summary as a single text block
        // Combine the selected sentences into a single string, separated by periods.
        return String.join(". ", summarySentences) + ".";
    }
}
