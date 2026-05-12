import "./Accordian.css";

function Accordian() {
  return (
    <div className="accordion" id="accordionExample">
      <div className="accordion-item">
        <h2 className="accordion-header">
          <button
            className="accordion-button"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#collapseTwo"
            aria-expanded="true"
            aria-controls="collapseTwo"
          >
            Models Competing for You
          </button>
        </h2>

        <div
          id="collapseTwo"
          className="accordion-collapse collapse show"
          data-bs-parent="#accordionExample"
        >
          <div className="accordion-body">
            <strong>No single model deserves your trust.</strong> RoseRank runs
            Black-Scholes, Binomial, Monte Carlo, and volatility frameworks side
            by side—then exposes where they agree, where they diverge, and where
            opportunity hides.
            <br />
            <br />
            Greeks update in real time. Volatility rankings surface distortion.
            Scenario views reveal how positions behave before capital is
            deployed.
            <br />
            <br />
            Markets speak in probabilities. We reel them up to the surface.
          </div>
        </div>
      </div>
      <div className="accordion-item">
        <h2 className="accordion-header">
          <button
            className="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#collapseOne"
            aria-expanded="false"
            aria-controls="collapseOne"
          >
            Custom-Built Engine
          </button>
        </h2>

        <div
          id="collapseOne"
          className="accordion-collapse collapse"
          data-bs-parent="#accordionExample"
        >
          <div className="accordion-body">
            <strong>
              RoseRank runs on its own open-source options engine.
            </strong>{" "}
            Built with <code>C SIMD</code> acceleration and GPU compute, it
            processes options statistics at ultra-fast speeds.
            <br />
            <br />
            Fair-value estimates for underlying assets are enhanced using{" "}
            <code>XGBoost</code> machine learning models to rank opportunities
            more intelligently.
            <br />
            <br />
            <a
              href="https://github.com/Ra4ster/RoseRank"
              target="_blank"
              rel="noopener noreferrer"
            >
              View our source code
            </a>
          </div>
        </div>
      </div>
      <div className="accordion-item">
        <h2 className="accordion-header">
          <button
            className="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#collapseThree"
            aria-expanded="false"
            aria-controls="collapseThree"
          >
            Exceptional Skill Training
          </button>
        </h2>

        <div
          id="collapseThree"
          className="accordion-collapse collapse"
          data-bs-parent="#accordionExample"
        >
          <div className="accordion-body">
            <strong>Prior experience is helpful, but not required.</strong>{" "}
            RoseRank includes explanations for key metrics and tools so users
            can understand what they are seeing and how it may be used.
            <br />
            <br />
            The platform is designed for both financial and non-financial
            backgrounds.
            <br />
            <br />
            Learn Options provides structured lessons on options mechanics,
            common metrics, and how to use RoseRank effectively. Community
            forums allow users to discuss ideas, solve problems, and share
            research.
          </div>
        </div>
      </div>
    </div>
  );
}

export default Accordian;
