package my.market.service.impl;

import lombok.RequiredArgsConstructor;
import my.market.model.response.AddressesResponseModel;
import my.market.repository.AddressRepository;
import my.market.repository.UserRepository;
import my.market.repository.entity.AddressEntity;
import my.market.repository.entity.UserEntity;
import my.market.service.AddressService;
import my.market.shared.AddressDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AddressDto> getAddressesByUserId(String id) {
        UserEntity userEntity = userRepository.findByUserId(id).orElseThrow(EntityNotFoundException::new);

        List<AddressEntity> addressEntities = addressRepository.findAllByUserDetails(userEntity);


        return mapListAddressEntityToListAddressDto(addressEntities);
    }

    private List<AddressDto> mapListAddressEntityToListAddressDto(List<AddressEntity> addressEntities) {
        Type listType = new TypeToken<List<AddressDto>>() {}.getType();

        return modelMapper.map(addressEntities, listType);
    }
}
