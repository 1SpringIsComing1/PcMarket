package my.market.model.response;

import lombok.Data;

@Data
public class AddressesResponseModel {
    private String addressId;
    private String city;
    private String postalCode;
    private String streetName;
    private String country;
    private String type;
}
