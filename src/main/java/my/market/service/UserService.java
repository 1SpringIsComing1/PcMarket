package my.market.service;

import my.market.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto findById(String id);

    UserDto findByEmail(String email);

    void updateUser(String id,UserDto user);

    void deleteUser(String id);
}
