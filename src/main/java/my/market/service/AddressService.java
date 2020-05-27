package my.market.service;

import my.market.shared.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAddressesByUserId();
}
