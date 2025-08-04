package in.hackercom.foodiesapi.service.Impl;

import in.hackercom.foodiesapi.Entity.UserEntity;
import in.hackercom.foodiesapi.Mapper.UserEntityMapper;
import in.hackercom.foodiesapi.Repository.UserRepository;
import in.hackercom.foodiesapi.io.UserRequest;
import in.hackercom.foodiesapi.io.UserResponse;
import in.hackercom.foodiesapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserEntityMapper userEntityMapper;

    @Override
    public UserResponse registerUser(UserRequest request) {
        UserEntity userEntity = userEntityMapper.getUserEntity(request);
        userEntity = userRepository.save(userEntity);
        return userEntityMapper.convertToUserResponse(userEntity);
    }
}
