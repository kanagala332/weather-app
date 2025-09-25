import { Link } from "react-router-dom";
import "../styles/Home.css"; // import CSS

const Home = () => {
  return (
    <div className="home-container">
      <h1>🌤 Weather App</h1>
      <p>Welcome! Please choose an option below:</p>
      <div className="button-group">
        <Link to="/login" className="login-btn">
          Login
        </Link>
        <Link to="/register" className="register-btn">
          Register
        </Link>
        <Link to="/about" className="about-btn">
          About Us
        </Link>
      </div>
    </div>
  );
};

export default Home;
