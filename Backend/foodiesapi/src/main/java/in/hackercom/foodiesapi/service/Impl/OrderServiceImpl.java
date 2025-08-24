package in.hackercom.foodiesapi.service.Impl;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import in.hackercom.foodiesapi.Entity.OrderEntity;
import in.hackercom.foodiesapi.Repository.OrderRepository;
import in.hackercom.foodiesapi.io.OrderRequest;
import in.hackercom.foodiesapi.io.OrderResponse;
import in.hackercom.foodiesapi.service.AuthenticationFacade;
import in.hackercom.foodiesapi.service.OrderService;
import in.hackercom.foodiesapi.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final AuthenticationFacade authenticationFacade;

    @Value("${razorpay_key}")
    private String RAZORPAY_KEY;

    @Value("${razorpay_secret}")
    private String RAZORPAY_SECRET;

    @Override
    public OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException {
        OrderEntity newOrder = convertToEntity(request);
        newOrder = orderRepository.save(newOrder);

        RazorpayClient razorpayClient = new RazorpayClient(RAZORPAY_KEY,RAZORPAY_SECRET);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",newOrder.getAmount());
        orderRequest.put("currency","INR");
        orderRequest.put("payment_capture",1);

        Order razorpayOrder = razorpayClient.orders.create(orderRequest);
        newOrder.setRazorpayOrderId(razorpayOrder.get("id"));
        String loggedInUser = userService.findByUserId();
        newOrder.setUserId(loggedInUser);
        newOrder= orderRepository.save(newOrder);
        return convertToResponse(newOrder);
    }

    private OrderResponse convertToResponse(OrderEntity newOrder) {
        return OrderResponse.builder().id(newOrder.getId())
                .amount(newOrder.getAmount())
                .userAddress(newOrder.getUserAddress())
                .userId(newOrder.getUserId())
                .razorpayOrderId(newOrder.getRazorpayOrderId())
                .orderStatus(newOrder.getOrderStatus())
                .paymentStatus(newOrder.getPaymentStatus())
                .email(newOrder.getEmail())
                .phoneNumber(newOrder.getPhoneNumber())
                .build();
    }

    private OrderEntity convertToEntity(OrderRequest request) {
        return OrderEntity.builder()
                .userAddress(request.getUserAddress())
                .amount(request.getAmount())
                .orderItemList(request.getOrderedItems())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .orderStatus(request.getOrderStatus())
                .build();
    }
}
