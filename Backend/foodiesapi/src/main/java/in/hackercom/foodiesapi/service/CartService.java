package in.hackercom.foodiesapi.service;

import in.hackercom.foodiesapi.io.CartRequest;
import in.hackercom.foodiesapi.io.CartResponse;

public interface CartService {

    CartResponse addToCart(CartRequest request);

    CartResponse getCart();

}
