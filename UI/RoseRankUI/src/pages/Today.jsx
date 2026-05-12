import TodayNavBar from "../components/layout/TodayNav";
import "./Today.css";
import MarketCard from "../components/today_stats/Marketcard";
import Footer2 from "../components/layout/Footer2";
function Today() {
  const formattedDate = new Date().toLocaleDateString("en-US", {
    month: "long",
    day: "2-digit",
    year: "numeric",
  });
  const sp500Data = [
    { time: "2026-05-01", value: 507.83 },
    { time: "2026-05-02", value: 509.12 },
    { time: "2026-05-03", value: 511.48 },
    { time: "2026-05-20", value: 541.32 },
  ];

  return (
    <>
      <TodayNavBar />
      <div className="todayHeader">
        <div className="topBar">
          <div>
            <h4 className="mb-1">Today</h4>
            <p className="text-secondary">
              Market overview and your edge for today.
            </p>
          </div>

          <div className="filterGroup">
            <button className="filterButton activeFilter">Overview</button>
            <button className="filterButton">Markets</button>
            <button className="filterButton">Watchlist</button>
            <button className="filterButton">News</button>
            <button className="filterButton">Calendar</button>
          </div>

          <div className="dateGroup">
            <i className="bi bi-calendar-check"></i>
            <p className="mb-0">{formattedDate}</p>
          </div>
        </div>

        <div className="marketSection">
          <MarketCard name="S&P 500" priceData={sp500Data} />
        </div>
      </div>
      <Footer2 open={false} delay="10" />
    </>
  );
}

export default Today;
