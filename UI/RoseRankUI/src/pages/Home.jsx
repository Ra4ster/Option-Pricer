import "./Home.css";
import React, { useEffect, useRef } from "react";
import { Link } from "react-router-dom";
import NavBar from "../components/layout/Nav.jsx";
import ParallaxCard from "../components/marketing/ParallaxCard.jsx";
import "../components/marketing/ParallaxCard.css";

import {
  SignedOut,
  SignedIn,
  UserButton,
  SignInButton,
} from "@clerk/clerk-react";
import viz from "../assets/images/viz.png";
import ranker from "../assets/images/ranker.png";
import wavesMoving from "../assets/videos/waves.mp4";

import Accordian from "../components/marketing/Accordian.jsx";
import Proof from "../components/marketing/Proof.jsx";
import Tiers from "../components/marketing/Tiers.jsx";
import FAQ from "../components/marketing/FAQ.jsx";
import Footer from "../components/layout/Footer.jsx";

const cardsData = [
  {
    image:
      "https://images.unsplash.com/photo-1558078854-48273da9bf3c?dpr=2&auto=compress,format&fit=crop&w=1199&h=798&q=80&cs=tinysrgb&crop=",
    title: "Predict Moves",
    content:
      "Visualize the exact range the market is pricing in for any expiration date with confidence levels and ML.",
  },
  {
    image:
      "https://images.unsplash.com/photo-1702382116645-e93f30e25626?dpr=2&auto=compress,format&fit=crop&w=1199&h=799&q=80&cs=tinysrgb&crop=",
    title: "Manage Risk",
    content:
      "Know your probability of profit, breakeven points, and max loss before you enter a trade.",
  },
  {
    image:
      "https://images.unsplash.com/photo-1584728888978-a028e807bf3f?dpr=2&auto=compress,format&fit=crop&w=1199&h=799&q=80&cs=tinysrgb&crop=",
    title: "View Volatility",
    content:
      "Never overpay for options again. Instantly spot when volatility changes to time your entries.",
  },
];

function Home() {
  const videoRef = useRef(null);

  useEffect(() => {
    if (videoRef.current) {
      videoRef.current.playbackRate = 0.55;
    }
  }, []);

  return (
    <>
      <NavBar />

      <video
        ref={videoRef}
        className="video-bg"
        autoPlay
        muted
        loop
        playsInline
      >
        <source src={wavesMoving} type="video/mp4" />
      </video>

      <main className="page-content">
        <section className="hero-section text-center">
          <p className="hero-eyebrow">Options Intelligence Platform</p>

          <h1 className="hero-title">RoseRank</h1>

          <p className="hero-motto">See Risk. Seize Edge.</p>

          <p className="hero-subtitle">
            Rank opportunities, visualize expected moves, and trade with
            probability on your side.
          </p>

          <div className="hero-cta">
            <SignedOut>
              <SignInButton mode="modal">
                <button className="button hero-btn hero-btn-primary">
                  Get Started
                </button>
              </SignInButton>
            </SignedOut>
            <SignedIn>
              <Link to="/today" className="button hero-btn hero-btn-primary">
                See Today
              </Link>
            </SignedIn>

            <a href="#pricing" className="hero-btn hero-btn-secondary">
              See Pricing
            </a>
          </div>
        </section>

        <section id="features" className="features-section">
          <div
            className="container"
            style={{
              padding: "40px 40px",
              display: "flex",
              flexWrap: "wrap",
              justifyContent: "center",
            }}
          >
            {cardsData.map((card, index) => (
              <ParallaxCard
                key={index}
                image={card.image}
                title={card.title}
                content={card.content}
              />
            ))}
          </div>
        </section>

        <div className="section-separator" />

        <section className="product-section container">
          <div className="section-header text-center">
            <p className="hero-eyebrow">Product Visuals</p>
            <h2>See the market before you trade it.</h2>
          </div>

          <div className="product-grid">
            <div className="product-card">
              <img src={viz} height="300" alt="RoseRank visualization graph" />

              <h3>Visualize Expected Moves</h3>

              <p>
                See market-implied ranges and confidence zones before entering a
                position.
              </p>
            </div>

            <div className="product-card">
              <img
                src={ranker}
                height="300"
                alt="RoseRank options ranking dashboard"
              />

              <h3>Rank Better Setups</h3>

              <p>
                Compare opportunities by probability, volatility, risk, and
                reward.
              </p>
            </div>
          </div>
        </section>

        <div className="section-separator" />

        <section id="how-it-works" className="empty-section container">
          <p className="hero-eyebrow">How It Works</p>
          <Accordian />
        </section>

        <div className="section-separator" />

        <section id="social-proof" className="empty-section container">
          <p className="hero-eyebrow">Social Proof</p>
          <Proof />
        </section>

        <div className="section-separator" />

        <section id="pricing" className="empty-section container">
          <p className="hero-eyebrow">Pricing</p>
          <Tiers />
        </section>

        <div className="section-separator" />

        <section id="faq" className="empty-section container">
          <p className="hero-eyebrow">FAQ</p>
          <FAQ />
        </section>

        <Footer />
      </main>
    </>
  );
}

export default Home;
