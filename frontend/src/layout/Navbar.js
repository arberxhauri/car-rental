import React, { useState, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faCarSide, faCar, faPerson, faFileText} from "@fortawesome/free-solid-svg-icons";
import { Link, useLocation } from "react-router-dom";
import '../index.css';
export default function Navbar() {
  const [showCarsMenu, setShowCarsMenu] = useState(false);
  const [showCustomersMenu, setShowCustomersMenu] = useState(false);
  const [showReservationsMenu, setReservationsMenu] = useState(false);


  const location = useLocation();

  useEffect(() => {
    setShowCarsMenu(location.pathname.includes("/cars"));
    setShowCustomersMenu(location.pathname.includes("/customer"));
    setReservationsMenu(location.pathname.includes("/reservations"));
  }, [location.pathname]);


  const toggleCategoryMenu = () => {
    setShowCategoryMenu(!showCategoryMenu);
    setShowCarsMenu(false);
    setShowCustomersMenu(false);
    setReservationsMenu(false);
  };

  const toggleCarsMenu = () => {
    setShowCarsMenu(!showCarsMenu);
    setShowCustomersMenu(false);
    setReservationsMenu(false);
    setShowCategoryMenu(false);
  };

  const toggleCustomersMenu = () => {
    setShowCustomersMenu(!showCustomersMenu);
    setShowCarsMenu(false);
    setReservationsMenu(false);
    setShowCategoryMenu(false);

  };

  const toggleReservationsMenu = () => {
    setReservationsMenu(!showReservationsMenu);
    setShowCarsMenu(false);
    setShowCustomersMenu(false);
    setShowCategoryMenu(false);

  };

  return (
      <nav className="navbar navbar-expand-lg navbar-dark" style={{ backgroundColor: "#9400D3" }}>
        <div className="container-fluid">
          <Link className="navbar-brand" to="/" style={{ fontFamily: "Arial, sans-serif", fontSize: "1.5rem" }}>
            <FontAwesomeIcon icon={faCar} className="me-2" />
            Car Rent
          </Link>

          <div className={`nav-item ${showCategoryMenu ? "active" : ""}`}>
              <button className="nav-link">
                <Link to="/category" className="nav-link">
                  <FontAwesomeIcon icon={faPerson} className="me-2" />
                  Category
                </Link>
              </button>
            </div>
          

          <div className="container-fluid d-flex">
            <div className={`nav-item me-3 ${showCarsMenu ? "active" : ""}`}>
              <button className="nav-link">
                <Link to="/cars" className="nav-link">
                  <FontAwesomeIcon icon={faCarSide} className="me-2"/>
                  Cars
                </Link>
              </button>
            </div>

            <div className={`nav-item ${showCustomersMenu ? "active" : ""}`}>
              <button className="nav-link">
                <Link to="/customer" className="nav-link">
                  <FontAwesomeIcon icon={faPerson} className="me-2" />
                  Customers
                </Link>
              </button>
            </div>

            <div className={`nav-item ${showReservationsMenu ? "active" : ""}`} style={{marginLeft: "10px"}}>
              <button className="nav-link">
                <Link to="/reservations" className="nav-link">
                  <FontAwesomeIcon icon={faFileText} className="me-2" />
                  Reservations
                </Link>
              </button>
            </div>
          </div>

          <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
        </div>
      </nav>
  );
}
