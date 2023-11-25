import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faUser,
  faUserTag,
  faCalendar,
  faUserAlt,
  faEnvelope,
  faLock,
  faCheck,
  faTags,
  faTimes,
  faEdit,
} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";
import React, { useState, useEffect } from "react";

export default function EditCustomer() {
  let navigate = useNavigate();
  const storedToken = 'Basic ' + btoa(localStorage.getItem('authToken'));

  const {customerId} = useParams()

  const [customerDto, setCustomerDto] = useState({
    firstName: "",
    lastName: "",
    age: 0,
    username: "",
    email: "",
    address: "",
    phoneNumber: 0,
  });

  const onInputChange = (event) => {
    const { name, value } = event.target;
    setCustomerDto({ ...customerDto, [name]: value });
  };

  const { firstName, lastName, age, username, email, address, phoneNumber } =
    customerDto;


    useEffect(()=>{
      loadCustomer();
    }, []);

  const onSubmit = async (event) => {
    event.preventDefault();
    await axios.put(`http://localhost:8080/api/costumer/${customerId}`, customerDto,{
      headers: {
        'Authorization': storedToken,
        'Content-Type': 'application/json',
      }
    });
    navigate("/customer");
  };

  const loadCustomer = async ()=>{
    console.log(customerId);
    const result = await axios.get(`http://localhost:8080/api/costumer/${customerId}`,{
      headers: {
        'Authorization': storedToken,
        'Content-Type': 'application/json',
      }
    });
    setCustomerDto(result.data)
  }
  
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2
            className="text-center mb-4"
            style={{
              color: "#9400D3",
              fontSize: "2.5rem",
              fontFamily: "Arial, sans-serif",
            }}
          >
            <FontAwesomeIcon icon={faEdit} className="me-2" />
            Edit
          </h2>
          <form onSubmit={(event) => onSubmit(event)}>
            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="First Name" className="form-label with-shadow">
                <FontAwesomeIcon
                  icon={faUser}
                  className="mr-2"
                  style={{ color: "#9400D3" }}
                />
              </label>
              <input
                type="text"
                className="form-control with-shadow custom-input"
                placeholder="John"
                name="firstName"
                value={firstName}
                onChange={(event) => onInputChange(event)}
              />
            </div>

            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="Last Name" className="form-label with-shadow">
                <FontAwesomeIcon
                  icon={faUserTag}
                  className="mr-2"
                  style={{ color: "#9400D3" }}
                />
              </label>
              <input
                type="text"
                className="form-control with-shadow"
                placeholder="Smith"
                name="lastName"
                value={lastName}
                onChange={(event) => onInputChange(event)}
              />
            </div>

            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="Age" className="form-label with-shadow">
                <FontAwesomeIcon
                  icon={faCalendar}
                  className="mr-2"
                  style={{ color: "#9400D3" }}
                />
              </label>
              <input
                type="number"
                className="form-control with-shadow"
                placeholder="Enter your age"
                name="age"
                value={age}
                onChange={(event) => onInputChange(event)}
              />
            </div>

            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="Username" className="form-label with-shadow">
                <FontAwesomeIcon
                  icon={faUserAlt}
                  className="mr-2"
                  style={{ color: "#9400D3" }}
                />
              </label>
              <input
                type="text"
                className="form-control with-shadow"
                placeholder="Nickname or username"
                name="username"
                value={username}
                onChange={(event) => onInputChange(event)}
              />
            </div>

            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="Email" className="form-label with-shadow">
                <FontAwesomeIcon
                  icon={faEnvelope}
                  className="mr-2"
                  style={{ color: "#9400D3" }}
                />
              </label>
              <input
                type="text"
                className="form-control with-shadow"
                placeholder="someone@email.com"
                name="email"
                value={email}
                onChange={(event) => onInputChange(event)}
              />
            </div>
            {/*<div className="mb-3 d-flex align-items-center">*/}
            {/*  <label htmlFor="Password" className="form-label with-shadow">*/}
            {/*    <FontAwesomeIcon*/}
            {/*      icon={faLock}*/}
            {/*      className="mr-2"*/}
            {/*      style={{ color: "#9400D3" }}*/}
            {/*    />*/}
            {/*  </label>*/}
            {/*  <input*/}
            {/*    type="password"*/}
            {/*    className="form-control with-shadow"*/}
            {/*    placeholder="Enter your password"*/}
            {/*    name="password"*/}
            {/*    value={password}*/}
            {/*    onChange={(event) => onInputChange(event)}*/}
            {/*  />*/}
            {/*</div>*/}
            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="Address" className="form-label with-shadow">
                <FontAwesomeIcon
                    icon={faLock}
                    className="mr-2"
                    style={{ color: "#9400D3" }}
                />
              </label>
              <input
                  type="text"
                  className="form-control with-shadow"
                  placeholder="Enter your address"
                  name="address"
                  value={address}
                  onChange={(event) => onInputChange(event)}
              />
            </div>
            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="Phone Number" className="form-label with-shadow">
                <FontAwesomeIcon
                    icon={faLock}
                    className="mr-2"
                    style={{ color: "#9400D3" }}
                />
              </label>
              <input
                  type="text"
                  className="form-control with-shadow"
                  placeholder="Enter your phone_number"
                  name="phoneNumber"
                  value={phoneNumber}
                  onChange={(event) => onInputChange(event)}
              />
            </div>
            <button  className="btn btn-primary mx-2"style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }}>
              <FontAwesomeIcon icon={faCheck} />
            </button>

            <Link className="btn btn-primary mx-2"style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }} to="/customer">
              <FontAwesomeIcon icon={faTimes} />
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
