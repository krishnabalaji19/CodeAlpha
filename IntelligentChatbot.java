import java.util.*;
import java.io.*;

public class IntelligentChatbot {

    private static final Map<String, String> RESPONSES = new HashMap<>();
    static {
        RESPONSES.put("greeting", "Hello! How can I assist you today?");
        RESPONSES.put("farewell", "Goodbye! Have a great day!");
        RESPONSES.put("help", "I can help you with general questions. Just ask!");
        RESPONSES.put("weather", "I'm sorry, I cannot provide weather information at the moment.");
        RESPONSES.put("smalltalk", "I'm just a chatbot, but I'm here to chat with you!");
        RESPONSES.put("joke", "Why did the scarecrow win an award? Because he was outstanding in his field!");
        RESPONSES.put("default", "I'm not sure I understand. Can you rephrase that?");
    }
    private static final String LOG_FILE = "chatbot_log.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chatbot: Hello! How can I assist you today? (Type 'exit' to quit)");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase();

            if (userInput.equals("exit")) {
                System.out.println("Chatbot: " + RESPONSES.get("farewell"));
                logInteraction("User  exited the chat.");
                break;
            }

            String processedInput = preprocessInput(userInput);
            String intent = detectIntent(processedInput);
            String response = generateResponse(intent);
            System.out.println("Chatbot: " + response);
            logInteraction(":User  " + userInput + " | Chatbot: " + response);
        }

        scanner.close();
    }

    private static String preprocessInput(String input) {
        return input.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase().trim();
    }

    private static String detectIntent(String input) {
        if (input.matches(".*\\b(hello|hi|hey)\\b.*")) {
            return "greeting";
        } else if (input.matches(".*\\b(bye|goodbye|see you)\\b.*")) {
            return "farewell";
        } else if (input.matches(".*\\b(help|support)\\b.*")) {
            return "help";
        } else if (input.matches(".*\\b(weather)\\b.*")) {
            return "weather";
        } else if (input.matches(".*\\b(joke|funny)\\b.*")) {
            return "joke";
        } else if (input.matches(".*\\b(how are you|what's up|how's it going)\\b.*")) {
            return "smalltalk";
        } else {
            return "default";
        }
    }

    private static String generateResponse(String intent) {
        return RESPONSES.getOrDefault(intent, RESPONSES.get("default"));
    }

    private static void logInteraction(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error logging interaction: " + e.getMessage());
        }
    }
}
//Sample Output:
//Chatbot: Hello! How can I assist you today? (Type 'exit' to quit)
//You: hi
//Chatbot: Hello! How can I assist you today?
//You: Can you help me?
//Chatbot: I can help you with general questions. Just ask!
//You: What's the weather like?
//Chatbot: I'm sorry, I cannot provide weather information at the moment.
//You: Tell me a joke
//Chatbot: Why did the scarecrow win an award? Because he was outstanding in his field!
//You: how are you?
//Chatbot: I'm just a chatbot, but I'm here to chat with you!
//You: exit
//Chatbot: Goodbye! Have a great day!
