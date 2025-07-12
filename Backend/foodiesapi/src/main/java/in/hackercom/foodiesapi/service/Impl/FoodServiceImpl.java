package in.hackercom.foodiesapi.service.Impl;

import in.hackercom.foodiesapi.Entity.FoodEntity;
import in.hackercom.foodiesapi.Mapper.FoodEntityMapper;
import in.hackercom.foodiesapi.Repository.FoodRepository;
import in.hackercom.foodiesapi.io.FoodRequest;
import in.hackercom.foodiesapi.io.FoodResponse;
import in.hackercom.foodiesapi.service.FoodService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final S3Client s3Client;
    private final FoodRepository foodRepository;
    private final FoodEntityMapper foodEntityMapper;


    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) {
        String fileNameExtension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".")+1);
        String key = UUID.randomUUID().toString()+"."+fileNameExtension;
        try{
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName).key(key)
                    .acl("public-read")
                    .contentType(file.getContentType())
                    .build();
            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            if(response.sdkHttpResponse().isSuccessful()){
                return "https://"+bucketName+".s3.amazonaws.com/"+key;
            }else{
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"File Upload failed");
            }
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An Error Occured while uploading the file");
        }
    }

    @Override
    public FoodResponse addFood(FoodRequest request, MultipartFile file) {
        FoodEntity foodEntity = foodEntityMapper.convertToFoodEntity(request);
        String imageUrl = uploadFile(file);
        foodEntity.setImageUrl(imageUrl);
        foodEntity=foodRepository.save(foodEntity);
        return foodEntityMapper.convertToFoodResponse(foodEntity);
    }

    @Override
    public List<FoodResponse> readFoods() {
       List<FoodEntity> databaseEntries =  foodRepository.findAll();
       return databaseEntries.stream().map(foodEntityMapper::convertToFoodResponse)
               .toList();
    }
}
