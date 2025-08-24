package in.hackercom.foodiesapi.service;

import com.razorpay.RazorpayException;
import in.hackercom.foodiesapi.io.OrderRequest;
import in.hackercom.foodiesapi.io.OrderResponse;

public interface OrderService {
    OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException;
}
