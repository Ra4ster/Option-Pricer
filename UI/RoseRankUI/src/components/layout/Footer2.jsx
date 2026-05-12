import React from "react";

function Footer2({ open, delay }) {
  return (
    <footer className="fixed-bottom text-secondary border-top border-secondary border-opacity-25 py-3 px-4 d-flex justify-content-between align-items-center">
      <div className="d-flex align-items-center gap-2">
        <small style={{ fontSize: "0.8rem", letterSpacing: "1px" }}>
          Market Status:
        </small>
        <span
          className={`badge ${open ? "bg-success" : "bg-danger"} rounded-circle p-1`}
        >
          <span className="visually-hidden">Status Indicator</span>
        </span>
        <small className={open ? "text-success" : "text-danger"}>
          {open ? "OPEN" : "CLOSED"}
        </small>
      </div>

      <small className="d-none d-md-inline">
        Quotes delayed by {delay || "NaN"} minutes.
      </small>
      <div className="d-flex gap-3">
        <small>© 2026 RoseRank ❤️</small>
      </div>
    </footer>
  );
}

export default Footer2;
