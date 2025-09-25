import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/Alerts.css"; // import CSS

const Alerts = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  const [alerts, setAlerts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchAlerts = async () => {
      try {
        const res = await axios.get(`http://localhost:8082/api/alerts/${user.id}`);
        setAlerts(res.data);
      } catch {
        setAlerts([]);
      }
    };
    fetchAlerts();
  }, [user.id]);

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/login");
  };

  const handleDashboard = () => {
    navigate("/dashboard");
  };

  return (
    <div className="alerts-container">
      <h2>Alerts</h2>

      <div className="top-buttons">
        <button onClick={handleDashboard} className="btn dashboard-btn">
          Dashboard
        </button>
        <button onClick={handleLogout} className="btn logout-btn">
          Logout
        </button>
      </div>

      {alerts.length === 0 ? (
        <p className="no-alerts">✅ No active alerts.</p>
      ) : (
        <ul className="alerts-list">
          {alerts.map((a) => (
            <li key={a.id} className="alert-card">
              <p className="title">
                {a.city} - {a.severity}
              </p>
              <p className="message">{a.message}</p>
              <p className="time">
                Issued at: {new Date(a.issuedAt).toLocaleString()}
              </p>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Alerts;
