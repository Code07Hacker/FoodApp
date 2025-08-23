package in.hackercom.foodiesapi.Mapper;

import in.hackercom.foodiesapi.Entity.CartEntity;
import in.hackercom.foodiesapi.io.CartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartEntityMapper {
    CartEntityMapper INSTANCE = Mappers.getMapper(CartEntityMapper.class);

    @Mapping(target = "id", source = "cartEntity.id")
    CartResponse convertToCartResponse(CartEntity cartEntity);

}
