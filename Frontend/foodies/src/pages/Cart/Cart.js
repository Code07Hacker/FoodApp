import React, { useContext } from 'react';
import './Cart.css'
import { StoreContext } from '../../context/StoreContext';
import { Link, useNavigate } from 'react-router-dom';

const Cart = () => {
    const navigate = useNavigate();
    const {foodList,increaseQty,decreaseQty,quantities,removeFromCart} = useContext(StoreContext);

    const cartItems = foodList.filter(food => quantities[food.id]>0);

    const subtotal = cartItems.reduce((acc,food)=>acc+food.price*quantities[food.id],0);
    const shipping = subtotal === 0 ? 0.0 : 10;
    const tax = subtotal * 0.1;
    const total = subtotal + shipping + tax;

  return (
    <div class="container py-5">
        <h1 class="mb-5">Your Shopping Cart</h1>
        <div class="row">
            <div class="col-lg-8">
                {
                    cartItems.length === 0 ? (
                        <p>Your cart is empty.</p>
                    ) : (
                        <div class="card mb-4">
                            <div class="card-body">
                                {cartItems.map((food)=>{
                                    return <div key={food.id} class="row cart-item mb-3">
                                        <div class="col-md-3">
                                            <img src={food.imageUrl} alt={food.name} class="img-fluid rounded" width={100} height={100} />
                                        </div>
                                        <div class="col-md-5">
                                            <h5 class="card-title">{food.name}</h5>
                                            <p class="text-muted">Category: {food.category}</p>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="input-group">
                                                <button class="btn btn-outline-secondary btn-sm" type="button" onClick={()=>decreaseQty(food.id)}>-</button>
                                                <input style={{"max-width":"100px"}} type="text" class="form-control  form-control-sm text-center quantity-input" value={quantities[food.id]} readOnly/>
                                                <button class="btn btn-outline-secondary btn-sm" type="button" onClick={()=>increaseQty(food.id)}>+</button>
                                            </div>
                                        </div>
                                        <div class="col-md-2 text-end">
                                            <p class="fw-bold">&#8377;{(food.price * quantities[food.id]).toFixed(2)}</p>
                                            <button class="btn btn-sm btn-outline-danger" onClick={()=>removeFromCart(food.id)}>
                                                    <i class="bi bi-trash"></i>
                                                </button>
                                        </div>
                                    </div>
                                })}
                                <hr/>
                            </div>
                        </div>
                    )
                }
                <div class="text-start mb-4">
                    <Link to='/' class="btn btn-outline-primary">
                        <i class="bi bi-arrow-left me-2"></i>Continue Shopping
                    </Link>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="card cart-summary">
                    <div class="card-body">
                        <h5 class="card-title mb-4">Order Summary</h5>
                        <div class="d-flex justify-content-between mb-3">
                            <span>Subtotal</span>
                            <span>&#8377;{subtotal.toFixed(2)}</span>
                        </div>
                        <div class="d-flex justify-content-between mb-3">
                            <span>Shipping</span>
                            <span>&#8377;{subtotal === 0 ? 0.0 : shipping.toFixed(2)}</span>
                        </div>
                        <div class="d-flex justify-content-between mb-3">
                            <span>Tax</span>
                            <span>&#8377;{tax.toFixed(2)}</span>
                        </div>
                        <hr/>
                        <div class="d-flex justify-content-between mb-4">
                            <strong>Total</strong>
                            <strong>&#8377;{subtotal === 0 ? 0.0 : total.toFixed(2)}</strong>
                        </div>
                        <button class="btn btn-primary w-100" disabled={cartItems.length === 0} onClick={()=>navigate("/order")}>Proceed to Checkout</button>
                    </div>
                </div>
                <div class="card mt-4">
                    <div class="card-body">
                        <h5 class="card-title mb-3">Apply Promo Code</h5>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Enter promo code"/>
                            <button class="btn btn-outline-secondary" type="button">Apply</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default Cart