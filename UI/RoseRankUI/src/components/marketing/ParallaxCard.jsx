import React, { useEffect, useRef, useState } from "react";

function ParallaxCard({ image, title, content }) {
  const cardRef = useRef(null);
  const leaveTimeoutRef = useRef(null);

  const [size, setSize] = useState({ width: 0, height: 0 });
  const [mouse, setMouse] = useState({ x: 0, y: 0 });

  useEffect(() => {
    if (!cardRef.current) return;

    setSize({
      width: cardRef.current.offsetWidth,
      height: cardRef.current.offsetHeight,
    });
  }, []);

  const mousePX = size.width ? mouse.x / size.width : 0;
  const mousePY = size.height ? mouse.y / size.height : 0;

  const cardStyle = {
    transform: `rotateY(${mousePX * 30}deg) rotateX(${mousePY * -30}deg)`,
  };

  const cardBgStyle = {
    transform: `translateX(${mousePX * -40}px) translateY(${mousePY * -40}px)`,
    backgroundImage: `url(${image})`,
  };

  const handleMouseMove = (e) => {
    if (!cardRef.current) return;

    const rect = cardRef.current.getBoundingClientRect();

    setMouse({
      x: e.clientX - rect.left - size.width / 2,
      y: e.clientY - rect.top - size.height / 2,
    });
  };

  const handleMouseEnter = () => {
    clearTimeout(leaveTimeoutRef.current);
  };

  const handleMouseLeave = () => {
    leaveTimeoutRef.current = setTimeout(() => {
      setMouse({ x: 0, y: 0 });
    }, 1000);
  };

  return (
    <div
      className="card-wrap"
      ref={cardRef}
      onMouseMove={handleMouseMove}
      onMouseEnter={handleMouseEnter}
      onMouseLeave={handleMouseLeave}
    >
      <div className="card" style={cardStyle}>
        <div className="card-bg" style={cardBgStyle} />

        <div className="card-info">
          <h1>{title}</h1>
          <p>{content}</p>
        </div>
      </div>
    </div>
  );
}

export default ParallaxCard;
