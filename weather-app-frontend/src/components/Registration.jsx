import { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import "../styles/Registration.css"; // import CSS

const Registration = () => {
  const [form, setForm] = useState({
    username: "",
    email: "",
    password: "",
    preferredLocation: "",
    units: "metric",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      await axios.post("http://localhost:8082/api/users", form);
      alert("✅ Registration successful!");
      navigate("/login");
    } catch (err) {
      if (err.response && err.response.status === 409) {
        alert("❌ Email already registered. Please use another email.");
      } else {
        console.error("Registration error:", err.response?.data || err.message);
        alert("❌ Registration failed! Please try again.");
      }
    }
  };

  return (
    <div className="registration-container">
      <h2>Register</h2>

      <input
        name="username"
        placeholder="Username"
        value={form.username}
        onChange={handleChange}
        required
      />

      <input
        name="email"
        type="email"
        placeholder="Email"
        value={form.email}
        onChange={handleChange}
        required
      />

      <input
        name="password"
        type="password"
        placeholder="Password"
        value={form.password}
        onChange={handleChange}
        required
      />

      <input
        name="preferredLocation"
        placeholder="City"
        value={form.preferredLocation}
        onChange={handleChange}
      />

      <select name="units" onChange={handleChange} value={form.units}>
        <option value="metric">Metric (°C)</option>
        <option value="imperial">Imperial (°F)</option>
      </select>

      <button onClick={handleSubmit} className="btn">
        Register
      </button>

      <p className="login-link">
        Already have an account? <Link to="/login">Login</Link>
      </p>

      <button onClick={() => navigate("/")} className="btn home-btn">
        ⬅ Go Back Home
      </button>
    </div>
  );
};

export default Registration;
