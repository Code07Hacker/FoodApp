package in.hackercom.foodiesapi.service;

import in.hackercom.foodiesapi.Entity.FoodEntity;
import in.hackercom.foodiesapi.io.FoodRequest;
import in.hackercom.foodiesapi.io.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {

    String uploadFile(MultipartFile file);

    FoodResponse addFood(FoodRequest request , MultipartFile file);

    List<FoodResponse> readFoods();

    FoodResponse readFood(String id);

    boolean deleteFile(String filename);

    void deleteFood(String id);
}
