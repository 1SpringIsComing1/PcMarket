package my.market.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UserResponseModel {
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
    private List<AddressesResponseModel> addresses;

}
