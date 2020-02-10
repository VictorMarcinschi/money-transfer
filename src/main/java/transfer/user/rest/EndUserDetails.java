package transfer.user.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EndUserDetails {

    @JsonProperty
    private final String email;

    @JsonProperty
    private final String phone;

    @JsonIgnore
    public boolean isFullDetails() {
        return Optional.ofNullable(getPhone()).isPresent()
                && Optional.ofNullable(getEmail()).isPresent();
    }
}
