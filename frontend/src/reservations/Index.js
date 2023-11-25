import React, { useEffect, useState } from "react";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faCarOn, faCarSide, faListUl, faPeopleGroup} from "@fortawesome/free-solid-svg-icons";
import {
    faEye,
    faEdit,
    faTrash,
    faPlus,
    faStar,
} from "@fortawesome/free-solid-svg-icons";
import { Link, useParams } from "react-router-dom";

export default function Reservation() {
    const [reservation, setReservation] = useState([]);
    const { carrId } = useParams();
    const storedToken = 'Basic ' + btoa(localStorage.getItem('authToken'));

    useEffect(() => {
        loadReservation();
    }, []);

    const loadReservation = async () => {
        try {
            const result = await axios.get("http://localhost:8080/api/reservations", {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': storedToken,
                },
            });
            setReservation(result.data);
            console.log(result.data);
        } catch (error) {
            console.error("Error loading customer:", error);
        }
    };

    const deleteReservation = async (id) => {
        const result = window.confirm("Are you sure you want to delete this reservation?");
        if (result) {
            try {
                await axios.delete(`http://localhost:8080/api/reservations/delete/${id}`,{
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': storedToken,
                    },
                });
                loadReservation();
            } catch (error) {
                console.error("Error deleting reservation:", error);
            }
        }
    }


    return (
        <div className="container">
            <div className="d-flex align-items-center">
                <Link className="btn btn-outline me-3 custom-button"
                      style={{ fontWeight: "bold", color: "#9400D3", borderColor: "#9400D3", marginTop: "1rem" }}
                      to="/reservations/save">
                    <FontAwesomeIcon icon={faListUl} className="me-2" />
                    Add Reservation
                </Link>
            </div>
            <div className="py-4">
                <div className="table-responsive">
                    <table className="table border shadow">
                        <thead className="table-header">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Costumer ID</th>
                            <th scope="col">Car ID</th>
                            <th scope="col">Date of Booking</th>
                            <th scope="col">Departure Date</th>
                            <th scope="col">Return Date</th>
                            <th scope="col">Amount</th>
                        </tr>
                        </thead>
                        <tbody>
                        {reservation.map((reservation, index) => (
                            <tr key={index} className={index % 2 === 0 ? "even-row" : ""}>
                                <th scope="row">{index + 1}</th>
                                <td>{reservation.costumerId}</td>
                                <td>{reservation.carId}</td>
                                <td>{reservation.dateOfBooking}</td>
                                <td>{reservation.departureDate}</td>
                                <td>{reservation.returnDate}</td>
                                <td>{reservation.amount}</td>
                                <td>
                                    <Link className="btn btn-primary mx-2" to={`/reservations/view/${reservation.reservation_id}`} style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }}>
                                        <FontAwesomeIcon icon={faEye} /> View
                                    </Link>
                                    <Link className="btn btn-outline-primary mx-2" style={{ color: "#9400D3", borderColor: "#9400D3" }} to={`/reservations/${reservation.reservation_id}`}>
                                        <FontAwesomeIcon icon={faEdit} /> Edit
                                    </Link>
                                    <button
                                        className="btn btn-primary mx-2"
                                        style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }}
                                        onClick={() => deleteReservation(reservation.reservation_id)}
                                    >
                                        <FontAwesomeIcon icon={faTrash} />
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}
