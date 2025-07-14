import React from 'react';
import {
    Routes,
    Route,
} from "react-router-dom";

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.bundle.js'
import 'bootstrap-icons/font/bootstrap-icons.css'

import Sidebar from './components/Sidebar/Sidebar';
import Menubar from './components/Menubar/Menubar';
import AddFood from './pages/AddFood/AddFood';
import ListFood from './pages/ListFood/ListFood';
import Order from './pages/Orders/Order';



const App = () => {
  return (
    <div className="d-flex" id="wrapper">
            <Sidebar/>
            <div id="page-content-wrapper">
                <Menubar/>
                <div className="container-fluid">
                    <Routes>
                        <Route path="/add" element={<AddFood/>}/>
                        <Route path="/list" element={<ListFood/>}/>
                        <Route path="/orders" element={<Order/>}/>
                        <Route path="/" element={<ListFood/>}/>
                    </Routes>
                </div>
            </div>
        </div>
  )
}

export default App