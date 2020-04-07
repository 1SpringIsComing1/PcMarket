package my.market.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserResponseModel {
    private String firstName;
    private String lastName;
    private String email;
    private String userId;

}
