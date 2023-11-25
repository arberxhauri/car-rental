import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import {BrowserRouter as Router, Routes, Route, useNavigate} from 'react-router-dom';
import AddCustomer from './Customer/AddCustomer';
import EditCustomer from './Customer/EditCustomer';
import ViewCustomer from './Customer/ViewCustomer';

import Footer from './layout/Footer';
import Cars from "./cars/Index";
import EditCar from "./cars/EditCar"
import ViewCar from "./cars/ViewCar"
import AddCar from "./cars/AddCar"
import Login from "./pages/login"
import React from "react";
import Reservation from "./reservations/Index";
import AddReservation from "./reservations/AddReservation";
import ViewReservation from "./reservations/ViewReservation";
import EditReservation from "./reservations/EditReservation";


function App() {

    const authToken = localStorage.getItem('authToken');
    const navigate = useNavigate();
    if (!authToken) {
      navigate('login')
    }
    return (
        <div className="App">
            {/*<Router>*/}
            <Navbar/>
            <div className="content">
                <Routes>
                    <Route exact path="login" element={<Login/>}/>

                    {/*CUSTOMER*/}
                    <Route exact path="customer/" element={<Home/>}/>
                    <Route exact path="customer/save" element={<AddCustomer/>}/>
                    <Route exact path="customer/view/:id" element={<ViewCustomer/>}/>
                    <Route exact path="customer/:customerId" element={<EditCustomer/>}/>
                    {/*CARS*/}
                    <Route exact path="/cars" element={<Cars/>}/>
                    <Route exact path="cars/save" element={<AddCar/>}/>
                    <Route exact path="cars/view/:id" element={<ViewCar/>}/>
                    <Route exact path="cars/:carId" element={<EditCar/>}/>
                    {/*    RESERVATIONS */}
                    <Route exact path="/reservations" element={<Reservation/>}/>
                    <Route exact path="reservations/save" element={<AddReservation/>}/>
                    <Route exact path="reservations/view/:id" element={<ViewReservation/>}/>
                    <Route exact path="reservations/:reservationId" element={<EditReservation/>}/>
                </Routes>
            </div>
            <Footer className="footer"/>
            {/*</Router>*/}
        </div>
    );
}


export default App;
