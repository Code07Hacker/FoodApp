package in.hackercom.foodiesapi.service.Impl;

import in.hackercom.foodiesapi.Entity.CartEntity;
import in.hackercom.foodiesapi.Mapper.CartEntityMapper;
import in.hackercom.foodiesapi.Repository.CartRepository;
import in.hackercom.foodiesapi.io.CartRequest;
import in.hackercom.foodiesapi.io.CartResponse;
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

    private final CartEntityMapper cartEntityMapper;

    @Override
    public CartResponse addToCart(CartRequest request) {
        String loggedInUserId = userService.findByUserId();
        Optional<CartEntity> cartEntityOptional = cartRepository.findByUserId(loggedInUserId);
        CartEntity cart =  cartEntityOptional.orElseGet(()-> new CartEntity(loggedInUserId,new HashMap<>()));
        Map<String ,Integer> cartItems = cart.getItems();
        cartItems.put(request.getFoodId(), cartItems.getOrDefault(request.getFoodId(),0)+1);
        cart.setItems(cartItems);
        cart = cartRepository.save(cart);
        return cartEntityMapper.convertToCartResponse(cart);
    }

    @Override
    public CartResponse getCart() {
        String loggedInUserId = userService.findByUserId();
        CartEntity entity = cartRepository.findByUserId(loggedInUserId)
                .orElse(new CartEntity(null , loggedInUserId , new HashMap<>()));
        return cartEntityMapper.convertToCartResponse(entity);
    }

    @Override
    public void clearCart() {
        String loggedInUserId = userService.findByUserId();
        cartRepository.deleteByUserId(loggedInUserId);
    }

    @Override
    public CartResponse removeFromCart(CartRequest cartRequest) {
        String loggedInUserId = userService.findByUserId();
        CartEntity cart = cartRepository.findByUserId(loggedInUserId)
                .orElseThrow(()->new RuntimeException("Cart id not Found"));
        Map<String,Integer> cartItems = cart.getItems();
        if(cartItems.containsKey(cartRequest.getFoodId())){
            int currentQty = cartItems.get(cartRequest.getFoodId());
            if(currentQty > 0){
                cartItems.put(cartRequest.getFoodId(), currentQty - 1);
            }else{
                cartItems.remove(cartRequest.getFoodId());
            }
            cart = cartRepository.save(cart);
        }
        return cartEntityMapper.convertToCartResponse(cart);
    }

}
