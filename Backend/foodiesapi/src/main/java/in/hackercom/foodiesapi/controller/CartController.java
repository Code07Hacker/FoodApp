package in.hackercom.foodiesapi.controller;

import in.hackercom.foodiesapi.io.CartRequest;
import in.hackercom.foodiesapi.io.CartResponse;
import in.hackercom.foodiesapi.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public CartResponse addToCart(@RequestBody CartRequest request){
        String foodId = request.getFoodId();
        if(foodId == null || foodId.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Food not found");
        }
        return cartService.addToCart(request);
    }


    @GetMapping
    public CartResponse getCart(){
        return cartService.getCart();
    }

}
