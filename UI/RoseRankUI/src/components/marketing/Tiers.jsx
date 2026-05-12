import "./Tiers.css";
function Tier() {
  const tiers = [
    {
      name: "Free",
      price: "$0",
      subtitle: "Start exploring RoseRank.",
      features: [
        "Basic options tools",
        "Limited daily scans",
        "Intro Learn Options content",
        "Read-only community access",
      ],
    },
    {
      name: "Learn",
      price: "$12/mo",
      subtitle: "Understand what the metrics mean.",
      features: [
        'Full "Learn Options" library',
        "Metric explanations",
        "Guided examples",
        "Forum access",
      ],
    },
    {
      name: "Pro",
      price: "$39/mo",
      subtitle: "Unlock the full analytics system.",
      features: [
        "Full options analytics",
        "Probability modeling",
        "Volatility rankings",
        "Ranked trade opportunities",
      ],
    },
  ];

  return (
    <section id="pricing" className="tier-section container">
      <div className="section-header text-center">
        <h2>Choose your plan.</h2>
      </div>

      <div className="tier-grid">
        {tiers.map((tier) => (
          <div className="tier-card" key={tier.name}>
            <button className="tier-name-btn" type="button">
              {tier.name}
            </button>

            <h3 className="tier-price">{tier.price}</h3>
            <p className="tier-subtitle">{tier.subtitle}</p>

            <ul className="tier-features">
              {tier.features.map((feature) => (
                <li key={feature}>
                  <i className="bi bi-check-circle-fill"></i>
                  <span>{feature}</span>
                </li>
              ))}
            </ul>

            <button className="tier-join-btn" type="button">
              Join
            </button>
          </div>
        ))}
      </div>
    </section>
  );
}

export default Tier;
