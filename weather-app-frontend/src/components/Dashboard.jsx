import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/Dashboard.css"; // import CSS

const Dashboard = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  const [city, setCity] = useState(user?.preferredLocation || "");
  const [weather, setWeather] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const fetchWeather = async (cityName) => {
    try {
      setLoading(true);
      const res = await axios.get(`http://localhost:8082/api/weather/live/${cityName}`);
      if (res.data) {
        setWeather(res.data);
        setError("");
      } else {
        setError("❌ No weather data found for this city.");
        setWeather(null);
      }
    } catch {
      setError("⚠️ City not found!");
      setWeather(null);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (city) {
      fetchWeather(city);
    }
  }, [city]);

  const handleSearch = () => {
    if (city.trim()) {
      fetchWeather(city);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/login");
  };

  const handleAlerts = () => {
    navigate("/alerts");
  };

  return (
    <div className="dashboard-container">
      <h2>Dashboard</h2>

      <div className="top-buttons">
        <button onClick={handleLogout} className="btn logout-btn">Logout</button>
        <button onClick={handleAlerts} className="btn alerts-btn">Alerts</button>
      </div>

      <div className="search-group">
        <input
          value={city}
          onChange={(e) => setCity(e.target.value)}
          placeholder="Enter city"
          className="input"
        />
        <button onClick={handleSearch} className="btn">Search</button>
      </div>

      {loading && <p className="loading">⏳ Fetching weather...</p>}

      {error && !loading && <p className="error">{error}</p>}

      {weather && !loading && (
        <div className="weather-card">
          <h3>{weather.city}</h3>
          <p>🌡 Temp: {weather.temperature}°C</p>
          <p>💧 Humidity: {weather.humidity}%</p>
          <p>🌬 Wind: {weather.windSpeed} km/h</p>
        </div>
      )}
    </div>
  );
};

export default Dashboard;
