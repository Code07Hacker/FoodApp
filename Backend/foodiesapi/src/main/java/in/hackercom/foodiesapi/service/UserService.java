package in.hackercom.foodiesapi.service;

import in.hackercom.foodiesapi.io.UserRequest;
import in.hackercom.foodiesapi.io.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest request);

}
