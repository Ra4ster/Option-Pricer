import "./FAQ.css";
function FAQ() {
  return (
    <div className="list-group">
      <div className="list-group-item">
        <h5 className="mb-2">What is RoseRank?</h5>
        <p className="mb-0">
          RoseRank is an options-first analytics platform for exploring stock
          option positions, pricing risk, and comparing opportunities.
        </p>
      </div>

      <div className="list-group-item">
        <h5 className="mb-2">
          How is RoseRank different from TradingView or OptionStrat?
        </h5>
        <p className="mb-0">
          RoseRank is built specifically for options. Instead of treating
          options as a secondary feature, RoseRank focuses on pricing,
          probabilities, volatility, and surfacing stronger setups quickly.
        </p>
      </div>

      <div className="list-group-item">
        <h5 className="mb-2">Is RoseRank beginner friendly?</h5>
        <p className="mb-0">
          Yes. RoseRank includes explanations throughout the platform, community
          support, and a dedicated learning section covering core options
          concepts and metrics.
        </p>
      </div>

      <div className="list-group-item">
        <h5 className="mb-2">How does RoseRank identify opportunities?</h5>
        <p className="mb-0">
          RoseRank combines fast pricing models, volatility analysis, and
          machine learning systems to compare positions at scale and highlight
          promising opportunities efficiently.
        </p>
      </div>

      <div className="list-group-item">
        <h5 className="mb-2">Is RoseRank free?</h5>
        <p className="mb-0">
          Yes. RoseRank offers a free tier with option pricing tools, learning
          material, and limited scans, with additional tiers for advanced users.
          <br />
          Because the best things in life are free.
        </p>
      </div>
    </div>
  );
}

export default FAQ;
