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
    faEdit, faCarOn, faCarRear, faPlateWheat, faPalette, faHourglass1, faBandage, faMoneyBill,
} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";
import React, { useState, useEffect } from "react";

export default function Editcar() {
    let navigate = useNavigate();
    const storedToken = 'Basic ' + btoa(localStorage.getItem('authToken'));

    const {carId} = useParams()

    const [carDto, setCarDto] = useState({
        brand: "",
        model: "",
        licensePlate: "",
        year: "",
        color: "",
        mileage: "",
        status: "",
        amount: "",
        categoryId: 0
    });

    const onInputChange = (event) => {
        const { name, value } = event.target;
        setCarDto({ ...carDto, [name]: value });
    };

    const {  brand,
        model,
        licensePlate,
        year,
        color,
        mileage,
        status,
        amount, categoryId } =
        carDto;


    useEffect(()=>{
        loadCustomer();
    }, []);

    const onSubmit = async (event) => {
        event.preventDefault();
        await axios.put(`http://localhost:8080/api/cars/update/${carId}`, carDto,{
            headers: {
                'Authorization': storedToken,
                'Content-Type': 'application/json',
            }
        });
        navigate("/cars");
    };

    const loadCustomer = async ()=>{
        const result = await axios.get(`http://localhost:8080/api/cars/${carId}`,
            {
                headers: {
                    'Authorization': storedToken,
                    'Content-Type': 'application/json',
                }
            })
        setCarDto(result.data)
    }

    const [category, setCategory] = useState([]);
    useEffect(() => {
        // Fetch categories when the component mounts
        axios
            .get("http://localhost:8080/api/categories/findAll",{
                headers: {
                    'Authorization': storedToken,
                    'Content-Type': 'application/json',
                },
            })
            .then((response) => {
                console.log(response.data)
                setCategory(response.data);
            })
            .catch((error) => {
                console.error("Error fetching categories:", error);
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
                        <FontAwesomeIcon icon={faEdit} className="me-2" />
                        Edit
                    </h2>
                    <form onSubmit={(event) => onSubmit(event)}>
                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Brand" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faCarOn}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <input
                                type="text"
                                className="form-control with-shadow custom-input"
                                placeholder="Toyota"
                                name="brand"
                                value={brand}
                                onChange={(event) => onInputChange(event)}
                            />
                        </div>

                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Model" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faCarRear}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <input
                                type="text"
                                className="form-control with-shadow"
                                placeholder="Supra"
                                name="model"
                                value={model}
                                onChange={(event) => onInputChange(event)}
                            />
                        </div>

                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="License Plate" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faPlateWheat}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <input
                                type="string"
                                className="form-control with-shadow"
                                placeholder="Enter your plate BAXXXX"
                                name="licensePlate"
                                value={licensePlate}
                                onChange={(event) => onInputChange(event)}
                            />
                        </div>

                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Year" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faCalendar}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <input
                                type="int"
                                className="form-control with-shadow"
                                placeholder="Production year 1997"
                                name="year"
                                value={year}
                                onChange={(event) => onInputChange(event)}
                            />
                        </div>

                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Color" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faPalette}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <input
                                type="text"
                                className="form-control with-shadow"
                                placeholder="Color"
                                name="color"
                                value={color}
                                onChange={(event) => onInputChange(event)}
                            />
                        </div>
                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Mileage" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faHourglass1}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <input
                                type="int"
                                className="form-control with-shadow"
                                placeholder="Mileage"
                                name="mileage"
                                value={mileage}
                                onChange={(event) => onInputChange(event)}
                            />
                        </div>
                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Status" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faBandage}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <input
                                type="text"
                                className="form-control with-shadow"
                                placeholder="Status"
                                name="status"
                                value={status}
                                onChange={(event) => onInputChange(event)}
                            />
                        </div>
                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Amount" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faMoneyBill}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <input
                                type="int"
                                className="form-control with-shadow"
                                placeholder="Enter your amount"
                                name="amount"
                                value={amount}
                                onChange={(event) => onInputChange(event)}
                            />
                        </div>
                        <div className="mb-3 d-flex align-items-center">
                            <label htmlFor="Cars" className="form-label with-shadow">
                                <FontAwesomeIcon
                                    icon={faCarOn}
                                    className="mr-2"
                                    style={{ color: "#9400D3" }}
                                />
                            </label>
                            <select
                                className="form-control with-shadow"
                                name="categoryId" // Update name attribute to match the state key
                                value={categoryId}
                                onChange={(event) => onInputChange(event)}
                            >
                                <option value="">Select a category...</option>
                                {category.map((singleCategory) => (
                                    <option key={singleCategory.id} value={singleCategory.id}>
                                        {singleCategory.type}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <button  className="btn btn-primary mx-2"style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }}>
                            <FontAwesomeIcon icon={faCheck} />
                        </button>

                        <Link className="btn btn-primary mx-2"style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }} to="/cars">
                            <FontAwesomeIcon icon={faTimes} />
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    );
}
