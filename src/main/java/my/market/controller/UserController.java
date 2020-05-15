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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @ResponseStatus(CREATED)
    @PostMapping(produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    },
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public UserResponseModel createUser(@RequestBody CreateUserRequestModel userDetails) {
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto user = userService.createUser(userDto);
        return modelMapper.map(user, UserResponseModel.class);
    }

    @ResponseStatus(OK)
    @GetMapping(path = "/{id}")
    public UserResponseModel getUser(@PathVariable String id) {
        UserDto userById = userService.findById(id);

        return modelMapper.map(userById, UserResponseModel.class);
    }

    @ResponseStatus(OK)
    @GetMapping(
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public List<UserResponseModel> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<UserDto> users = userService.getUsers(page, limit);
        return mapListUserDtoToListUserRequestModel(users);
    }

    @ResponseStatus(CREATED)
    @PutMapping(
            path = "/{id}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            },
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public void updateUser(@RequestBody CreateUserRequestModel userDetails, @PathVariable String id) {
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        userService.updateUser(id, userDto);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(
            path = "/{id}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            },
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    private List<UserResponseModel> mapListUserDtoToListUserRequestModel(List<UserDto> users) {
        List<UserResponseModel> userResponseModelList = new ArrayList<>();
        for (UserDto user : users) {
            userResponseModelList.add(modelMapper.map(user, UserResponseModel.class));
        }
        return userResponseModelList;
    }
}
