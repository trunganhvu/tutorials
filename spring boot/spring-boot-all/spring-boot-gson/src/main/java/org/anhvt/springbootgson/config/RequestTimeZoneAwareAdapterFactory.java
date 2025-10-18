package org.anhvt.springbootgson.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.anhvt.springbootgson.context.RequestTimeZoneHolder;

import java.io.IOException;

public class RequestTimeZoneAwareAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);

        // Chỉ áp dụng cho class thuộc package của bạn (VD org.anhvt.springbootgson.dto)
        if (!type.getRawType().getName().startsWith("org.anhvt.springbootgson.dto")) {
            return delegate;
        }

        return new TypeAdapter<>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonElement element = JsonParser.parseReader(in);
                if (!element.isJsonObject()) {
                    // Không phải object -> trả về luôn
                    return delegate.fromJsonTree(element);
                }

                JsonObject obj = element.getAsJsonObject();
                if (obj.has("timeZone") && !obj.get("timeZone").isJsonNull()) {
                    String tz = obj.get("timeZone").getAsString();
                    System.out.println("[DEBUG] Read timezone: " + tz);
                    RequestTimeZoneHolder.set(tz);
                }

                try {
                    return delegate.fromJsonTree(obj);
                } finally {
                    RequestTimeZoneHolder.clear();
                }
            }
        };
    }
}
