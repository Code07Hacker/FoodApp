package in.hackercom.foodiesapi.controller;

import in.hackercom.foodiesapi.io.UserRequest;
import in.hackercom.foodiesapi.io.UserResponse;
import in.hackercom.foodiesapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    private UserResponse register(@RequestBody UserRequest user){
      return userService.registerUser(user);
    }

}
