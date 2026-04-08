package com.app.ecom.service.;

import com.app.ecom.dto.OrderResponse;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.model.*;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final UserRepository userRepository;
    public Optional<OrderResponse> createOrder(String userId) {

        //Validate for cart items
        List<CartItem> cartItems = cartService.getCart(userId);
        if( cartItems.isEmpty()){
         return Optional.empty();
        }

        //Validate for user
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if(userOptional.isEmpty()){
            return Optional.empty();
        }
        User user = userOptional.get();

        //Calculate total price
        BigDecimal totalPrice = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        //Create order
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        List<OrderItem> orderItems = cartItems.stream()
                .map(item-> new OrderItem(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        order

                ))
                .toList();
        order.setItems(orderItems);
        //Clear the cart

    }
}
