package in.hackercom.foodiesapi.Mapper;

import in.hackercom.foodiesapi.Entity.FoodEntity;
import in.hackercom.foodiesapi.io.FoodRequest;
import in.hackercom.foodiesapi.io.FoodResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FoodEntityMapper {
    FoodEntityMapper INSTANCE = Mappers.getMapper(FoodEntityMapper.class);

    FoodEntity convertToFoodEntity(FoodRequest request);

    FoodResponse convertToFoodResponse(FoodEntity foodEntity);
}
