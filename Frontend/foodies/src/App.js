import React from 'react'
import Menubar from './components/Menubar/Menubar';
import {Route, Routes} from 'react-router-dom'
import Home from './pages/Home/Home';
import ContantUs from './pages/ContactUs/ContantUs';
import Explore from './pages/Explore/Explore';
import FoodDetails from './pages/FoodDetails/FoodDetails';
import Cart from './pages/Cart/Cart';
import PlaceOrder from './pages/PlaceOrder/PlaceOrder';

const App = () => {
  return (
    <div>
      <Menubar/>
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/contactus' element={<ContantUs/>}/>
        <Route path='/explore' element={<Explore/>}/>
        <Route path='/food/:id' element={<FoodDetails/>}/>
        <Route path='/cart' element={<Cart/>}/>
        <Route path='/order' element={<PlaceOrder/>}/>
      </Routes>
    </div>
  )
}

export default App;