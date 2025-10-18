package org.anhvt.springbootgson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringBootGsonApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootGsonApplication.class, args);
        System.out.println("========== GSON BASIC DEMO ==========");
        runBasicDemo();

        System.out.println("\n========== GSON ADVANCED DEMO ==========");
        runAdvancedDemo();
    }

    /**
     * Demonstrates basic Gson features:
     * - Serialize object to JSON.
     * - Deserialize JSON back to object.
     * - Pretty print JSON output.
     * - Parse JSON into JsonObject to access fields dynamically.
     * - Deserialize JSON array into a generic List<User>.
     */
    public static void runBasicDemo() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd")
                .create();

        // 1. Serialize object to JSON
        UserTest user = new UserTest(1L, "John Doe", "john@example.com", "secret123", new Date());
        String json = gson.toJson(user);
        System.out.println("1. Object to JSON:\n" + json);

        // 2. Deserialize JSON back to object
        UserTest parsed = gson.fromJson(json, UserTest.class);
        System.out.println("2. JSON to Object: " + parsed.getName() + " / " + parsed.getBirthDate());

        // 3. Pretty print JSON output
        System.out.println("3. Pretty JSON:\n" + gson.toJson(user));

        // 4. Parse JSON into JsonObject to access fields dynamically
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
        System.out.println("4. Email field value: " + obj.get("email").getAsString());

        // 5. Deserialize JSON array into a generic List<User>
        String listJson = "[{\"full_name\":\"A\"}, {\"full_name\":\"B\"}]";
        List<UserTest> users = gson.fromJson(listJson, new TypeToken<List<UserTest>>(){}.getType());
        System.out.println("5. List size: " + users.size());
    }

    /**
     * Demonstrates advanced Gson features:
     * - Excluding fields without @Expose annotation.
     * - Including null values in serialization.
     * - Applying custom field naming policies.
     * - Serializing Map objects.
     * - Creating custom deserializer logic.
     */
    public static void runAdvancedDemo() {
        Gson gsonExpose = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        UserTest u = new UserTest(2L, "Alice", "alice@company.com", "pwd", new Date());

        // 1. Serialize only fields annotated with @Expose
        System.out.println("1. Only @Expose fields:\n" + gsonExpose.toJson(u));

        // 2. Include null fields in serialization
        Gson gsonNulls = new GsonBuilder()
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setPrettyPrinting()
                .create();

        UserTest nullUser = new UserTest(null, null, "abc@x.com", null, null);
        System.out.println("2. Include null fields:\n" + gsonNulls.toJson(nullUser));

        // 3. Apply field naming policy
        Gson gsonNaming = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setDateFormat("dd/MM/yyyy")
                .setPrettyPrinting()
                .create();
        System.out.println("3. Field naming policy:\n" + gsonNaming.toJson(u));

        // 4. Serialize a Map to JSON
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "OK");
        System.out.println("4. Map JSON:\n" + gsonExpose.toJson(map));

        // 5. Custom deserializer for User class
        Gson gsonCustom = new GsonBuilder()
                .registerTypeAdapter(UserTest.class, (JsonDeserializer<UserTest>) (json, type, ctx) -> {
                    JsonObject obj = json.getAsJsonObject();
                    String name = obj.has("full_name") ? obj.get("full_name").getAsString() : "Unknown";
                    return new UserTest(999L, name, "auto@gen.com", "none", new Date());
                })
                .create();

        UserTest custom = gsonCustom.fromJson("{\"full_name\":\"Custom User\"}", UserTest.class);
        System.out.println("5. Custom deserializer result: " + custom.getName());
    }

}
