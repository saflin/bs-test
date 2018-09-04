package com.bs.assignment.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Configuration
public class RestConfig {

    static final DateTimeFormatter FORMATTER = ofPattern("yyyy-MM-dd HH:mm:ss");

    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerialiser());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimDeserialiser());
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    private class LocalDateTimeSerialiser extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            gen.writeString(value.format(FORMATTER));
        }
    }

    private class LocalDateTimDeserialiser extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            return LocalDateTime.parse(p.getValueAsString(), FORMATTER);
        }
    }
}
