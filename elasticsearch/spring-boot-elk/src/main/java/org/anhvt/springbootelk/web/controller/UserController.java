package org.anhvt.springbootelk.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.anhvt.springbootelk.service.UserResourceConverter;
import org.anhvt.springbootelk.service.UserService;
import org.anhvt.springbootelk.web.request.UserResourceRequest;
import org.anhvt.springbootelk.web.response.ResourceIdResponse;
import org.anhvt.springbootelk.web.response.SuccessResponse;
import org.anhvt.springbootelk.web.response.UserResourceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import java.util.stream.Collectors;

/**
 * @author trunganhvu
 */
@Tag(name = "/users", description = "API manages 'users' allowing to perform basic CRUD operations")
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserResourceConverter converter;

    @Operation(
            summary = "Retrieve 'request' by Id"
    )
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getUser(@Parameter(name = "Unique 'request' identifier") @PathVariable final Long userId) {
        LOGGER.debug("Trying to retrieve User by ID: " + userId);
        return toJson(this.converter.convert(this.userService.getUser(userId)));
    }

    @Operation(
            summary = "Retrieve all 'users'"
    )
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getAllUsers() {
        LOGGER.debug("Trying to retrieve all users");
        return toJson(
                this.userService.getAllUsers().stream()
                        .map(user -> this.converter.convert(user)).collect(Collectors.toSet()));
    }

    @Operation(
            summary = "Create new 'request'"
    )
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String createUser(@RequestBody UserResourceRequest request) {
        LOGGER.debug("Trying to create a user: " + request.toString());
        Long userId = this.userService.createUser(this.converter.convert(request));
        return toJson(new ResourceIdResponse(userId));
    }

    @Operation(
            summary = "Update existing 'request'"
    )
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public String updateUser(@Parameter(name = "Unique 'request' identifier") @PathVariable final Long userId,
                             @RequestBody UserResourceRequest request) {
        LOGGER.debug("Trying to update a user: " + request.toString());
        this.userService.updateUser(this.converter.convert(request, userId));
        return toJson(new SuccessResponse());
    }

    @Operation(
            summary = "Delete existing 'request'"
    )
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String deleteUser(@Parameter(name = "Unique 'request' identifier") @PathVariable final Long userId) {
        LOGGER.debug("Trying to delete a user: " + userId);
        this.userService.deleteUser(userId);
        return toJson(new SuccessResponse());
    }

    private static String toJson(Object object) {
        return new Gson().toJson(object);
    }

//    /**
//     * Sets request service.
//     *
//     * @param userService the request service
//     */
//    void setUserService(final UserService userService) {
//        this.userService = userService;
//    }
//
//    /**
//     * Sets converter.
//     *
//     * @param converter the converter
//     */
//    void setConverter(final UserResourceConverter converter) {
//        this.converter = converter;
//    }
}
