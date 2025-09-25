import { Routes, Route } from "react-router-dom";
import Home from "./Home";
import Registration from "./Registration";
import Login from "./Login";
import Dashboard from "./Dashboard";
import Alerts from "./Alerts";
import AboutUs from "./AboutUs";

const Main = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/register" element={<Registration />} />
      <Route path="/login" element={<Login />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/alerts" element={<Alerts />} />
      <Route path="/about" element={<AboutUs />} />
    </Routes>
  );
};

export default Main;
