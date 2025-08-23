package in.hackercom.foodiesapi.service.Impl;

import in.hackercom.foodiesapi.Entity.CartEntity;
import in.hackercom.foodiesapi.Repository.CartRepository;
import in.hackercom.foodiesapi.service.CartService;
import in.hackercom.foodiesapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final UserService userService;

    @Override
    public void addToCart(String foodId) {
        String loggedInUserId = userService.findByUserId();
        Optional<CartEntity> cartEntityOptional = cartRepository.findByUserId(loggedInUserId);
        CartEntity cart =  cartEntityOptional.orElseGet(()-> new CartEntity(loggedInUserId,new HashMap<>()));
        Map<String ,Integer> cartItems = cart.getItems();
        cartItems.put(foodId , cartItems.getOrDefault(foodId,0)+1);
        cart.setItems(cartItems);
        cart = cartRepository.save(cart);
    }
}
