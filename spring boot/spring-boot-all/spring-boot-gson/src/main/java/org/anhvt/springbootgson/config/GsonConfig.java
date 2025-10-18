package org.anhvt.springbootgson.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.anhvt.springbootgson.adapter.UtcDateAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.Date;


@Configuration
public class GsonConfig {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new UtcDateAdapter())
                .registerTypeAdapterFactory(new RequestTimeZoneAwareAdapterFactory())
                .setPrettyPrinting()
                .create();
    }

    @Bean
    @Primary
    public HttpMessageConverter<Object> gsonHttpMessageConverter(Gson gson) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        return converter;
    }
}