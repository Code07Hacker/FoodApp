import React, { useState } from 'react';
import {
    Routes,
    Route,
} from "react-router-dom";

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.bundle'
import 'bootstrap-icons/font/bootstrap-icons.css'

import Sidebar from './components/Sidebar/Sidebar';
import Menubar from './components/Menubar/Menubar';
import AddFood from './pages/AddFood/AddFood';
import ListFood from './pages/ListFood/ListFood';
import Order from './pages/Orders/Order';
import {ToastContainer} from 'react-toastify'



const App = () => {
    const [sideBarVisible , setSideBarVisible] = useState(true);

    const toggleSideBar = () => {
        setSideBarVisible(!sideBarVisible);
    }
    return (
        <div className="d-flex" id="wrapper">
                <Sidebar sideBarVisible={sideBarVisible}/>
                <div id="page-content-wrapper">
                    <Menubar toggleSideBar={toggleSideBar}/>
                    <ToastContainer/>
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