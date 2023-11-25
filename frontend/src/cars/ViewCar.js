import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import { faHome,faInfoCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';


export default function ViewCar() {
    const [car, setCar] = useState({
        brand: "",
        model: "",
        licensePlate: "",
        year: "",
        color: "",
        mileage: "",
        status: "",
        amount: "",
        categoryDto: []
    });
    const { id } = useParams();
    const storedToken = 'Basic ' + btoa(localStorage.getItem('authToken'));
    useEffect(() => {
        loadCar();
    }, []);

    const loadCar = async () => {
        const result = await axios.get(`http://localhost:8080/api/cars/${id}`,{
            headers: {
                'Authorization': storedToken,
                'Content-Type': 'application/json',
            }
            });
        console.log(result.data);
        setCar(result.data);
    };

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
                        <FontAwesomeIcon icon={faInfoCircle} className="me-2" />
                        Info
                    </h2>

                    <div className="card">
                        <div className="card-header">
                            Details of car with id: {id}
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item">
                                    <b>Brand:</b> {car.brand}
                                </li>
                                <li className="list-group-item">
                                    <b>Model:</b> {car.model}
                                </li>
                                <li className="list-group-item">
                                    <b>License Plate:</b> {car.licensePlate}
                                </li>
                                <li className="list-group-item">
                                    <b>Year:</b> {car.year}
                                </li>
                                <li className="list-group-item">
                                    <b>Color:</b> {car.color}
                                </li>
                                <li className="list-group-item">
                                    <b>Mileage:</b> {car.mileage}
                                </li>
                                <li className="list-group-item">
                                    <b>Status:</b> {car.status}
                                </li>
                                <li className="list-group-item">
                                    <b>Amount:</b> {car.amount}
                                </li>
                                <li className="list-group-item">
                                    <b>Category:</b> {car.categoryDto.type}
                                </li>
                            </ul>
                        </div>
                    </div>
                    <Link
                        className="btn btn-primary mx-2"
                        style={{
                            backgroundColor: "#9400D3",
                            borderColor: "#9400D3",
                        }}
                        to={"/cars"}
                    >
                        <FontAwesomeIcon icon={faHome} className="me-2" />
                        Back to Home
                    </Link>
                </div>
            </div>
        </div>
    );
}
