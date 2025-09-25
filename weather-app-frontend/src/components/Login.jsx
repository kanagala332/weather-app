import { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import "../styles/Login.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const res = await axios.post("http://localhost:8082/api/users/login", {
        email,
        password,
      });

      // ✅ Success: store user and redirect
      localStorage.setItem("user", JSON.stringify(res.data));
      alert("✅ Login successful!");
      navigate("/dashboard");
    } catch (err) {
      if (err.response && err.response.status === 401) {
        // ✅ Show backend message
        alert(`❌ ${err.response.data.error}`);
      } else {
        console.error("Login error:", err);
        alert("⚠️ Server error, please try again later!");
      }
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <input
        type="email"
        placeholder="Email"
        className="input"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        className="input"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleLogin} className="btn">
        Login
      </button>
      <p className="register-link">
        Create a new account <Link to="/register"> Register</Link>
      </p>
    </div>
  );
};

export default Login;
