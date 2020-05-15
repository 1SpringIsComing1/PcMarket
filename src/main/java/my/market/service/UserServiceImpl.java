package my.market.service;

import lombok.RequiredArgsConstructor;
import my.market.model.response.ErrorMessage;
import my.market.repository.entity.UserEntity;
import my.market.repository.UserRepository;
import my.market.shared.AddressDto;
import my.market.shared.UserDto;
import my.market.shared.Util;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
        for (int i = 0; i < userDto.getAddresses().size(); i++) {
            AddressDto addressDto = userDto.getAddresses().get(i);
            addressDto.setUserDetails(userDto);
            addressDto.setAddressId(util.generateAddressId());
            userDto.getAddresses().set(i, addressDto);

        }

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
    public List<UserDto> getUsers(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<UserEntity> userPage = userRepository.findAll(pageable);
        List<UserEntity> userEntities = userPage.getContent();
        return mapListUserEntityToListUserDto(userEntities);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userByEmail = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        return new User(userByEmail.getEmail(),
                userByEmail.getEncryptedPassword(),
                true, true, true, true, new ArrayList<>());
    }
    private List<UserDto> mapListUserEntityToListUserDto(List<UserEntity> content) {
        List<UserDto> usersDto = new ArrayList<>();
        for (UserEntity userEntity : content) {
            modelMapper.map(userEntity, UserDto.class);
        }
        return usersDto;
    }

}
