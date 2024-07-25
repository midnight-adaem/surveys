package com.midnight.adaem.surveys.com.midnight.adaem.surveys.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.Id;
import lombok.extern.jackson.Jacksonized;

import java.io.IOException;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Jacksonized
@Builder
@JsonPropertyOrder({ "id", "fullName", "email", "isActive" })
public class Members {
    @Id
    @JsonProperty("Member Id")
    private long id;

    @NonNull
    @JsonProperty("Full name")
    private String fullName;

    @JsonProperty("E-mail address")
    private String email;

    @NonNull
    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    @JsonProperty("Is Active")
    private Boolean isActive;

}

class NumericBooleanDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return !"0".equals(parser.getText());
    }
}
