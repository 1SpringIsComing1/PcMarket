package my.market.controller;

import lombok.RequiredArgsConstructor;
import my.market.model.request.CreateUserRequestModel;
import my.market.model.response.UserResponseModel;
import my.market.service.UserService;
import my.market.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;


    @PostMapping(produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    },
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<CreateUserRequestModel> createUser(@RequestBody CreateUserRequestModel userDetails) {

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public UserResponseModel getUser(@PathVariable String id) {
        UserDto userById = userService.findById(id);

        return modelMapper.map(userById, UserResponseModel.class);
    }

//    @PutMapping(path = "/{id}")
//    public
}
