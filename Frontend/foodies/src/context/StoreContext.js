import {createContext, useEffect, useState} from 'react';
import axios from 'axios';
import { fetchFoodList } from '../service/FoodService';

export const StoreContext = createContext(null);


export const StoreContextProvider = (props) => {

    const [foodList,setFoodList] = useState([]);
    const [quantities , setQuantities] = useState({});
    const [token , setToken] = useState("")


    const increaseQty = async (foodId) => {
        setQuantities((prev)=>({...prev,[foodId]:(prev[foodId] || 0)+1}))
        await axios.post(
            'http://localhost:8080/api/cart',
            {foodId},
            {headers:{"Authorization":`Bearer ${token}`}}
        );
    }

    const decreaseQty = async (foodId) => {
        setQuantities((prev) => ({...prev,[foodId]:prev[foodId]>0?prev[foodId] - 1:0}));
        await axios.post(
            'http://localhost:8080/api/cart/remove',
            {foodId},
            {headers:{"Authorization":`Bearer ${token}`}}
        );
    }

    useEffect(()=>{
        async function loadData(params) {
          const data = await fetchFoodList();
          setFoodList(data);
          if(localStorage.getItem("token")){
            setToken(localStorage.getItem("token"));
            await loadCartData(localStorage.getItem("token"))
          }
        }
        loadData();
    },[])

    const removeFromCart = (foodId) => {
        setQuantities((prevQuantities)=> {
           const updated =  {...prevQuantities};
           delete updated[foodId];
           return updated;
        })
    }


    const loadCartData =async (token) => {
        const response = await axios.get(
            'http://localhost:8080/api/cart',
            {headers:{"Authorization":`Bearer ${token}`}}
        );
        setQuantities(response.data.items);
    }


    const contextValue = {
        foodList,
        increaseQty,
        decreaseQty,
        quantities,
        setQuantities,
        removeFromCart,
        token,
        setToken,
        loadCartData
    };

    return (
        <StoreContext.Provider value={contextValue}>
            {props.children}
        </StoreContext.Provider>
    )
}


