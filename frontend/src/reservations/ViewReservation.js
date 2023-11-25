import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import { faHome,faInfoCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';


export default function ViewReservation() {
    const [reservation, setReservation] = useState({
        costumerId: "",
        carId: "",
        dateOfBooking: "",
        departureDate: "",
        returnDate: ""
    });
    const { id } = useParams();
    const storedToken = 'Basic ' + btoa(localStorage.getItem('authToken'));
    useEffect(() => {
        loadReservation();
    }, []);

    const loadReservation = async () => {
        console.log('test');
        const result = await axios.get(`http://localhost:8080/api/reservations/${id}`,{
            headers: {
                'Authorization': storedToken,
                'Content-Type': 'application/json',
            }
        });
        setReservation(result.data);
    };
    const [cars, setCars] = useState([]); // Define categories state
    useEffect(() => {
        // Fetch categories when the component mounts
        axios
            .get(`http://localhost:8080/api/cars/${reservation.carId}`,{
                headers: {
                    'Authorization': storedToken,
                    'Content-Type': 'application/json',
                },
            })
            .then((response) => {
                console.log(response.data);
                setCars(response.data);
            })
            .catch((error) => {
                console.error("Error fetching categories:", error);
            });
    }, [reservation]);

    const [costumer, setCostumer] = useState([]);
    useEffect(() => {
        // Fetch categories when the component mounts
        axios
            .get(`http://localhost:8080/api/costumer/${reservation.costumerId}`,{
                headers: {
                    'Authorization': storedToken,
                    'Content-Type': 'application/json',
                },
            })
            .then((response) => {
                console.log(response.data)
                setCostumer(response.data);
            })
            .catch((error) => {
                console.error("Error fetching customers:", error);
            });
    }, [reservation]);

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
                            Details of reservation with id: {id}
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item">
                                    <b>Costumer:</b> {costumer?.firstName } {costumer?.lastName }
                                </li>
                                <li className="list-group-item">
                                    <b>car:</b> {cars?.model} {cars?.brand}
                                </li>
                                <li className="list-group-item">
                                    <b>Date of Booking:</b> {reservation.dateOfBooking}
                                </li>
                                <li className="list-group-item">
                                    <b>Departure Date:</b> {reservation.departureDate}
                                </li>
                                <li className="list-group-item">
                                    <b>Return Date</b> {reservation.returnDate}
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
                        to={"/reservations"}
                    >
                        <FontAwesomeIcon icon={faHome} className="me-2" />
                        Back to Home
                    </Link>
                </div>
            </div>
        </div>
    );
}
