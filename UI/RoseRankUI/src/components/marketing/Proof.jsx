import { useState } from "react";
import JackRose from "../../assets/images/profile2025.jpg";
import "./Proof.css";

function Proof() {
  const [copied, setCopied] = useState(false);

  const handleCopy = async () => {
    await navigator.clipboard.writeText("jackrose2335@gmail.com");
    setCopied(true);

    setTimeout(() => setCopied(false), 1800);
  };

  return (
    <div className="proof-grid">
      <div className="card proof-card text-center">
        <div className="card-header">Open Source</div>

        <div className="card-body">
          <h5 className="card-title">Code is actively, publicly maintained</h5>

          <p className="card-text">
            You can see updates as they launch, and use the engine via MIT
            license and publicly exposed APIs.
          </p>

          <a
            href="https://github.com/ra4ster/RoseRank"
            target="_blank"
            rel="noopener noreferrer"
            className="btn btn-primary"
          >
            Go to Source
          </a>
        </div>
      </div>

      <div className="card founder-card text-center">
        <div className="card-body">
          <img
            src={JackRose}
            className="founder-avatar"
            alt="Jack Rose @ 2025"
          />

          <h5 className="card-title mt-3 mb-1">Jack Rose</h5>

          <p className="text-muted small mb-3">Founder, RoseRank</p>

          <p className="card-text">
            Building transparent, high-performance options analytics for
            independent traders.
          </p>

          <div className="email-row mt-3">
            <span className="email-text">jackrose2335@gmail.com</span>

            <button
              className="copy-icon-btn"
              onClick={handleCopy}
              aria-label="Copy email"
              title="Copy email"
            >
              <i
                className={`bi ${copied ? "bi-check-lg" : "bi-clipboard"}`}
              ></i>
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Proof;
