import icon from "../../assets/icons/icon.svg";
import "./Nav.css";
import { Link } from "react-router-dom";
import { SignedIn, UserButton, SignOutButton } from "@clerk/clerk-react";

function TodayNavBar() {
  return (
    <nav className="navbar navbar-expand-lg custom-nav fixed-top">
      <div className="container-fluid">
        {/* Brand */}
        <Link
          className="navbar-brand d-flex align-items-center gap-2"
          to="/today"
        >
          <img src={icon} alt="RoseRank" width="36" />
          RoseRank
        </Link>

        {/* Mobile Toggle */}
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#dashboardNavbar"
          aria-controls="dashboardNavbar"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        {/* Nav Content */}
        <div className="collapse navbar-collapse" id="dashboardNavbar">
          {/* Left Side Links - me-auto pushes everything else to the right on desktop */}
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link className="nav-link active" to="/home">
                Home
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link active" to="/today">
                Today
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/research">
                Research
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/portfolios">
                Portfolios
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/screener">
                Screener
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link active" to="/journal">
                Journal
              </Link>
            </li>
          </ul>
          <div
            className="flex-grow-1 d-flex justify-content-start justify-content-lg-end px-lg-3 my-2 my-lg-0"
            style={{ maxWidth: "100%" }}
          >
            <form
              className="d-flex w-100"
              style={{ maxWidth: "300px" }}
              role="search"
              data-bs-theme="dark"
            >
              <input
                className="form-control search-dark"
                type="search"
                placeholder="Search assets, tickers, or ideas..."
                aria-label="Search"
              />
            </form>
          </div>

          {/* Right Side Icons & Auth */}
          <div className="d-flex align-items-center gap-3 text-nowrap mt-2 mt-lg-0">
            <button className="unstyled position-relative bell-wrapper">
              <i className="bi bi-bell position-absolute bell-outline fs-4"></i>
              <i className="bi bi-bell-fill bell-fill fs-4"></i>
              <span className="notificationBadge">3</span>
            </button>

            <SignedIn>
              <div className="d-flex align-items-center gap-2">
                <UserButton afterSignOutUrl="/" />
                <SignOutButton mode="modal">
                  <button className="btn btn-outline-login">Sign Out</button>
                </SignOutButton>
              </div>
            </SignedIn>
          </div>
        </div>
      </div>
    </nav>
  );
}

export default TodayNavBar;
