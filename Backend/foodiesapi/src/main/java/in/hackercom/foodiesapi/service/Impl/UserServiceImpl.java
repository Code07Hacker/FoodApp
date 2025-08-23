package in.hackercom.foodiesapi.service.Impl;

import in.hackercom.foodiesapi.Entity.UserEntity;
import in.hackercom.foodiesapi.Mapper.UserEntityMapper;
import in.hackercom.foodiesapi.Repository.UserRepository;
import in.hackercom.foodiesapi.io.UserRequest;
import in.hackercom.foodiesapi.io.UserResponse;
import in.hackercom.foodiesapi.service.AuthenticationFacade;
import in.hackercom.foodiesapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserEntityMapper userEntityMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationFacade authenticationFacade;
    @Override
    public UserResponse registerUser(UserRequest request) {
        UserEntity userEntity = userEntityMapper.getUserEntity(request);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity = userRepository.save(userEntity);
        return userEntityMapper.convertToUserResponse(userEntity);
    }

    @Override
    public String findByUserId() {
        String LoggedInUserEmail = authenticationFacade.getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(LoggedInUserEmail).orElseThrow(()->new UsernameNotFoundException("Not Found"));
        return user.getId();
    }
}
