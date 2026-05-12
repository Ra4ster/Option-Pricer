import "./Footer.css";
import icon from "../../assets/icons/icon.svg";

function Footer() {
  return (
    <footer className="rr-footer">
      <div className="container">
        <div className="rr-footer-top">
          <div className="rr-footer-brand">
            <div className="rr-footer-logo">
              <img src={icon} alt="RoseRank" width="42" />
              <h3>RoseRank</h3>
            </div>

            <p>
              Options-first analytics for traders who prefer probability over
              guesswork.
            </p>
          </div>

          <div className="rr-footer-meta">
            <span>Built independently.</span>
            <span>Open-source components.</span>
            <span>Active development.</span>
          </div>
        </div>

        <div className="rr-footer-links">
          <a href="#">Home</a>
          <a href="#features">Features</a>
          <a href="#pricing">Pricing</a>
          <a href="#faq">FAQ</a>
          <a href="https://github.com/ra4ster/RoseRank">GitHub</a>
          <a href="#">Contact</a>
        </div>

        <div className="rr-footer-bottom">
          <span>© 2026 RoseRank</span>
          <span>Not financial advice.</span>
          <span>All rights reserved.</span>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
