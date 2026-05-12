import icon from "../../assets/icons/icon.svg";
import "./Nav.css";
import { Link } from "react-router-dom";
import {
  SignedOut,
  SignedIn,
  UserButton,
  SignInButton,
  SignOutButton,
} from "@clerk/clerk-react";

function NavBar() {
  return (
    <nav className="navbar navbar-expand-lg custom-nav fixed-top">
      <div className="container-fluid">
        <a className="navbar-brand" href="#">
          <img src={icon} alt="RoseRank" width="36" />
          RoseRank
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            <li className="nav-item">
              <a className="nav-link active" aria-current="page" href="#">
                Home
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#features">
                Features
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#pricing">
                Pricing
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#contact">
                Contact
              </a>
            </li>
          </ul>
          <SignedOut>
            <SignInButton mode="modal">
              <button className="btn btn-outline-login ms-auto">
                Login / Signup
              </button>
            </SignInButton>
          </SignedOut>

          <SignedIn>
            <SignOutButton mode="modal">
              <button className="btn btn-outline-login ms-auto">
                Sign Out
              </button>
            </SignOutButton>
          </SignedIn>
        </div>
      </div>
    </nav>
  );
}

export default NavBar;
