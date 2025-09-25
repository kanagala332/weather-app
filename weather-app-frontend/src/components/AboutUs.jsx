import "../styles/AboutUs.css"
const AboutUs = () => {
  const creators = [
    {
      name: "Abdul Rehman",
      role: "Full-Stack Developer",
      desc: "Specializes in React and backend integration with Spring Boot. Passionate about building scalable web apps.",
    },
    {
      name: "Harsha Vardhan",
      role: "Frontend Developer",
      desc: "Focuses on creating beautiful UI with React, CSS animations, and interactive components.",
    },
    {
      name: "Mahesh",
      role: "Backend Developer",
      desc: "Expert in Spring Boot and REST APIs, ensures data accuracy, security, and smooth system performance.",
    },
  ];

  return (
    <div className="about-container">
      <h2 className="about-title">About Us</h2>
      <p className="about-desc">
        This Weather App was built with React and Spring Boot. 🌍  
        It provides real-time weather updates, user-specific alerts, and personalized dashboards.  
        Our mission is to help people stay informed about weather conditions and stay safe in extreme situations.
      </p>

      <h3 className="creators-title">👩‍💻 Meet the Creators</h3>
      <div className="creators-grid">
        {creators.map((creator, index) => (
          <div key={index} className="creator-card">
            <h4 className="creator-name">{creator.name}</h4>
            <p className="creator-role">{creator.role}</p>
            <p className="creator-desc">{creator.desc}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default AboutUs;
