package my.market.shared;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
public class UserDto  implements Serializable {
    private static final long serialVersionUID = -6978012027126168308L;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private  Boolean emailVerificationStatus;
    private List<AddressDto> addresses;

}
