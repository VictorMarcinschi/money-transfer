package transfer.user.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
public class EndUserDetails {

    @JsonProperty
    private final String email;

    @JsonProperty
    private final String phone;

    public boolean isFullDetails() {
        return Optional.ofNullable(getPhone()).isPresent()
                && Optional.ofNullable(getEmail()).isPresent();
    }
}
