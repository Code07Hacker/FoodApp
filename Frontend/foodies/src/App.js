import React from 'react'
import Menubar from './components/Menubar/Menubar';
import {Route, Routes} from 'react-router-dom'
import Home from './pages/Home/Home';
import ContantUs from './pages/ContactUs/ContantUs';
import Explore from './pages/Explore/Explore';

const App = () => {
  return (
    <div>
      <Menubar/>
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/contactus' element={<ContantUs/>}/>
        <Route path='/explore' element={<Explore/>}/>

      </Routes>
    </div>
  )
}

export default App;