package my.market.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AddressRequestModel {
    @NotNull(message = "City name cannot be null")
    @Size(min = 2, message = "City name must not be less than 2 characters")
    private String city;
    @NotNull(message = "Country cannot be null")
    @Size(min = 2, message = "Country must not be less than 2 characters")
    private String country;
    @NotNull(message = "streetName cannot be null")
    @Size(min = 2, message = "streetName must not be less than 2 characters")
    private String streetName;
    @NotNull(message = "postalCode cannot be null")
    @Size(min = 2, message = "postalCode must not be less than 2 characters")
    private String postalCode;
    @NotNull(message = "type cannot be null")
    @Size(min = 2, message = "type must not be less than 2 characters")
    private String type;
}
