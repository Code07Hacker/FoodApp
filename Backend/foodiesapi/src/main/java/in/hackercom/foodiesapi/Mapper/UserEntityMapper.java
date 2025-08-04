package in.hackercom.foodiesapi.Mapper;

import in.hackercom.foodiesapi.Entity.UserEntity;
import in.hackercom.foodiesapi.io.UserRequest;
import in.hackercom.foodiesapi.io.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    UserEntity getUserEntity(UserRequest request);

    UserResponse convertToUserResponse(UserEntity userEntity);
}
