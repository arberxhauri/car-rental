import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faCalendar,
    faCheck,
    faTimes, faCarOn, faCarRear, faPlateWheat, faPalette, faHourglass1, faBandage, faMoneyBill,
} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import React, { useState, useEffect } from "react";

export default function AddReservation() {
    let navigate = useNavigate();
    const storedToken = 'Basic ' + btoa(localStorage.getItem('authToken'));

    const [reservationDto, setReservationDto] = useState({
        costumerId: "",
        carId: "",
        dateOfBooking: "",
        departureDate: "",
        returnDate: "",
        // amount: 1,
    });

    const onInputChange = (event) => {
        const { name, value } = event.target;
        setReservationDto({ ...reservationDto, [name]: value });
    };

    const { costumerId ,carId , dateOfBooking, departureDate, returnDate } =
        reservationDto;

    const onSubmit = async (event) => {
        event.preventDefault();

        try {
            await axios.post("http://localhost:8080/api/reservations/save", reservationDto, {
                headers: {
                    'Authorization': storedToken,
                    'Content-Type': 'application/json',
                },
            });
            navigate("/reservations");
        } catch (error) {
            console.error("Error adding reservation:", error);

        }
    };

    const [cars, setCars] = useState([]); // Define categories state
    useEffect(() => {
        // Fetch categories when the component mounts
        axios
            .get("http://localhost:8080/api/cars/list",{
                headers: {
                    'Authorization': storedToken,
                    'Content-Type': 'application/json',
                },
            })
            .then((response) => {
                setCars(response.data);
            })
            .catch((error) => {
                console.error("Error fetching categories:", error);
            });
    }, []);

    const [costumer, setCostumer] = useState([]);
    useEffect(() => {
        // Fetch categories when the component mounts
        axios
            .get("http://localhost:8080/api/costumer/list",{
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
    }, []);


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
                        Reservation Form
                    </h2>
                    <form onSubmit={(event) => onSubmit(event)}>
                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Customer" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faCarOn}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <select
                                className="form-control with-shadow"
                                name="costumerId" // Update name attribute to match the state key
                                value={costumerId}
                                onChange={(event) => onInputChange(event)}
                            >
                                <option value="">Select a customer...</option>
                                {costumer.map((singleCustomer) => (
                                    <option key={singleCustomer.id} value={singleCustomer.id}>
                                        {singleCustomer.firstName}{" "}{singleCustomer.lastName}
                                    </option>
                                ))}
                            </select>
                        </div>

                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Car" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faCarRear}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <select
                                className="form-control with-shadow"
                                name="carId"
                                value={carId}
                                onChange={(event) => onInputChange(event)}
                            >
                                <option value="">Select a car...</option>
                                {cars.map((car) => (
                                    <option key={car.id} value={car.id}>
                                        {car.brand}{" "}{car.model}
                                    </option>
                                ))}
                            </select>
                        </div>

                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Date of Booking" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faPlateWheat}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <input
                                type="date"
                                className="form-control with-shadow"
                                placeholder="Enter the date of Booking"
                                name="dateOfBooking"
                                value={dateOfBooking}
                                onChange={(event) => onInputChange(event)}
                            />
                        </div>

                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Date of Departure" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faCalendar}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <input
                                type="date"
                                className="form-control with-shadow"
                                placeholder="Enter the date of departure"
                                name="departureDate"
                                value={departureDate}
                                onChange={(event) => onInputChange(event)}
                            />
                        </div>

                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Return Date" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faPalette}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <input
                                type="date"
                                className="form-control with-shadow"
                                placeholder="Enter the return date"
                                name="returnDate"
                                value={returnDate}
                                onChange={(event) => onInputChange(event)}
                            />
                        </div>
                        <div className="mb-3 d-flex align-items-center">
                        </div>
                        <button  className="btn btn-primary mx-2" style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }}>
                            <FontAwesomeIcon icon={faCheck} />
                        </button>

                        <Link className="btn btn-primary mx-2" style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }} to="/reservations">
                            <FontAwesomeIcon icon={faTimes} />
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    );
}
