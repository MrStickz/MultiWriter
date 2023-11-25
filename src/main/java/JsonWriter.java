import com.google.gson.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JsonWriter {

    private final String path;
    private JsonObject jsonData;

    public JsonWriter(String path) {
        this.path = path;
        this.jsonData = parseJsonFile(path);
    }

    public void write(String input, String value) {
        String[] arrayNames = input.split("\\.");
        int lastIndex = arrayNames.length - 1;

        Map<String, Object> data = parseJsonFile(); // Load existing data

        Map<String, Object> currentLevel = data;

        for (int i = 0; i < lastIndex; i++) {
            String arrayName = arrayNames[i];

            if (currentLevel.containsKey(arrayName) && currentLevel.get(arrayName) instanceof Map) {
                currentLevel = (Map<String, Object>) currentLevel.get(arrayName);
            } else {
                // If the array doesn't exist, create a new one
                Map<String, Object> nextLevel = new HashMap<>();
                currentLevel.put(arrayName, nextLevel);
                currentLevel = nextLevel;
            }
        }

        currentLevel.put(arrayNames[lastIndex], value);

        saveJsonFile(data); // Save the updated data
    }
    public void write(String input, int value) {
        String[] arrayNames = input.split("\\.");
        int lastIndex = arrayNames.length - 1;

        Map<String, Object> data = parseJsonFile(); // Load existing data

        Map<String, Object> currentLevel = data;

        for (int i = 0; i < lastIndex; i++) {
            String arrayName = arrayNames[i];

            if (currentLevel.containsKey(arrayName) && currentLevel.get(arrayName) instanceof Map) {
                currentLevel = (Map<String, Object>) currentLevel.get(arrayName);
            } else {
                // If the array doesn't exist, create a new one
                Map<String, Object> nextLevel = new HashMap<>();
                currentLevel.put(arrayName, nextLevel);
                currentLevel = nextLevel;
            }
        }

        currentLevel.put(arrayNames[lastIndex], value);

        saveJsonFile(data); // Save the updated data
    }
    public void write(String input, boolean value) {
        String[] arrayNames = input.split("\\.");
        int lastIndex = arrayNames.length - 1;

        Map<String, Object> data = parseJsonFile(); // Load existing data

        Map<String, Object> currentLevel = data;

        for (int i = 0; i < lastIndex; i++) {
            String arrayName = arrayNames[i];

            if (currentLevel.containsKey(arrayName) && currentLevel.get(arrayName) instanceof Map) {
                currentLevel = (Map<String, Object>) currentLevel.get(arrayName);
            } else {
                // If the array doesn't exist, create a new one
                Map<String, Object> nextLevel = new HashMap<>();
                currentLevel.put(arrayName, nextLevel);
                currentLevel = nextLevel;
            }
        }

        currentLevel.put(arrayNames[lastIndex], value);

        saveJsonFile(data); // Save the updated data
    }

    public boolean check(String input) {
        String[] arrayNames = input.split("\\.");
        JsonObject currentLevel = parseJsonFile(path);

        for (String arrayName : arrayNames) {
            if (currentLevel.has(arrayName) && currentLevel.get(arrayName).isJsonObject()) {
                currentLevel = currentLevel.getAsJsonObject(arrayName);
            } else {
                return false; // Key not found
            }
        }

        return true; // Value found
    }

    public Object get(String input) {
        String[] arrayNames = input.split("\\.");
        JsonElement currentLevel = parseJsonFile(path);

        for (String arrayName : arrayNames) {
            if (currentLevel.isJsonObject() && currentLevel.getAsJsonObject().has(arrayName)) {
                currentLevel = currentLevel.getAsJsonObject().get(arrayName);
            } else {
                return null; // Key not found
            }
        }

        if (currentLevel.isJsonPrimitive()) {
            return currentLevel.getAsJsonPrimitive().getAsString(); // Return as string or the appropriate type
        } else if (currentLevel.isJsonArray()) {
            return currentLevel.getAsJsonArray(); // Return as an array or the appropriate type
        } else if (currentLevel.isJsonObject()) {
            return currentLevel.getAsJsonObject(); // Return as an object or the appropriate type
        } else {
            return null; // Handle other types as needed
        }
    }


    public void set(String input, String value) {
        String[] arrayNames = input.split("\\.");
        JsonObject currentLevel = jsonData;

        // Navigate to the second-to-last level, assuming the last part is the key to set
        for (int i = 0; i < arrayNames.length - 1; i++) {
            String arrayName = arrayNames[i];
            if (currentLevel.has(arrayName) && currentLevel.get(arrayName).isJsonObject()) {
                currentLevel = currentLevel.getAsJsonObject(arrayName);
            } else {
                // Key not found, so we cannot set the value
                return;
            }
        }

        String lastKey = arrayNames[arrayNames.length - 1];
        currentLevel.addProperty(lastKey, value);

        saveJsonFile();
    }
    public void set(String input, int value) {
        String[] arrayNames = input.split("\\.");
        JsonObject currentLevel = jsonData;

        // Navigate to the second-to-last level, assuming the last part is the key to set
        for (int i = 0; i < arrayNames.length - 1; i++) {
            String arrayName = arrayNames[i];
            if (currentLevel.has(arrayName) && currentLevel.get(arrayName).isJsonObject()) {
                currentLevel = currentLevel.getAsJsonObject(arrayName);
            } else {
                // Key not found, so we cannot set the value
                return;
            }
        }

        String lastKey = arrayNames[arrayNames.length - 1];
        currentLevel.addProperty(lastKey, value);

        saveJsonFile();
    }
    public void set(String input, boolean value) {
        String[] arrayNames = input.split("\\.");
        JsonObject currentLevel = jsonData;

        // Navigate to the second-to-last level, assuming the last part is the key to set
        for (int i = 0; i < arrayNames.length - 1; i++) {
            String arrayName = arrayNames[i];
            if (currentLevel.has(arrayName) && currentLevel.get(arrayName).isJsonObject()) {
                currentLevel = currentLevel.getAsJsonObject(arrayName);
            } else {
                // Key not found, so we cannot set the value
                return;
            }
        }

        String lastKey = arrayNames[arrayNames.length - 1];
        currentLevel.addProperty(lastKey, value);

        saveJsonFile();
    }

    public boolean checkArray(JsonArray jsonArray, String value) {
        for (JsonElement element : jsonArray) {
            if (element.isJsonPrimitive() && element.getAsString().equals(value)) {
                return true;
            }
        }
        return false;
    }

    private void saveJsonFile() {
        try {
            Files.writeString(Paths.get(path), jsonData.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JsonObject parseJsonFile(String path) {
        try {
            String jsonContent = Files.readString(Paths.get(path));
            return JsonParser.parseString(jsonContent).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
            return new JsonObject();
        }
    }

    private static String toJson(Map<String, Object> data) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(data);
    }

    private Map<String, Object> parseJsonFile() {
        try {
            String jsonContent = Files.readString(Paths.get(path));
            return new Gson().fromJson(jsonContent, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private void saveJsonFile(Map<String, Object> data) {
        String jsonData = new GsonBuilder().setPrettyPrinting().create().toJson(data);
        try {
            Files.writeString(Paths.get(path), jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}