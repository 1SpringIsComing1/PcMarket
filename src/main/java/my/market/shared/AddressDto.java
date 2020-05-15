package my.market.shared;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class AddressDto implements Serializable {
    private static final long serialVersionUID = -3985387399566260765L;
    private String addressId;
    private String city;
    private String postalCode;
    private String streetName;
    private String country;
    private String type;
    private UserDto userDetails;

}
