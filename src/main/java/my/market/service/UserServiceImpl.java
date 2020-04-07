package my.market.service;

import lombok.RequiredArgsConstructor;
import my.market.model.response.ErrorMessage;
import my.market.repository.UserEntity;
import my.market.repository.UserRepository;
import my.market.shared.UserDto;
import my.market.shared.Util;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

import static my.market.model.response.ErrorMessage.RECORD_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final Util util;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new EntityExistsException(RECORD_ALREADY_EXISTS.getErrorMassage());
        }

        userDto.setUserId(util.generateUserId());
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        userRepository.save(userEntity);
        return userDto;
    }

    @Override
    public UserDto findById(String id) {
        UserEntity userById = userRepository.findByUserId(id).orElseThrow(() -> new UsernameNotFoundException(id));
        return modelMapper.map(userById, UserDto.class);
    }

    @Override
    public UserDto findByEmail(String email) {
        UserEntity userByEmail = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return modelMapper.map(userByEmail, UserDto.class);
    }

    @Override
    public void updateUser(String id, UserDto user) {
        UserEntity userEntity = userRepository.findByUserId(id).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.NO_RECORD_FOUND.getErrorMassage()));

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userRepository.save(userEntity);

    }

    @Override
    public void deleteUser(String id) {
        UserEntity userEntity = userRepository.findByUserId(id).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.NO_RECORD_FOUND.getErrorMassage()));
        
        userRepository.delete(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userByEmail = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        return new User(userByEmail.getEmail(),
                userByEmail.getEncryptedPassword(),
                true, true, true, true, new ArrayList<>());
    }


}
