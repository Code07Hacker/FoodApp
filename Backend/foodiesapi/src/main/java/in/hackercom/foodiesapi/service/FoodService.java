package in.hackercom.foodiesapi.service;

import in.hackercom.foodiesapi.io.FoodRequest;
import in.hackercom.foodiesapi.io.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FoodService {

    String uploadFile(MultipartFile file);

    FoodResponse addFood(FoodRequest request , MultipartFile file);
}
