import { useEffect, useRef } from "react";
import { createChart } from "lightweight-charts";
import "./MarketCard.css";

function MarketCard({ name, priceData }) {
  const chartRef = useRef(null);

  const firstPrice = priceData[0]?.value ?? 0;
  const latestPrice = priceData[priceData.length - 1]?.value ?? 0;

  const change = latestPrice - firstPrice;
  const percentChange = firstPrice ? (change / firstPrice) * 100 : 0;

  const isPositive = change >= 0;

  useEffect(() => {
    if (!chartRef.current || priceData.length === 0) return;

    chartRef.current.innerHTML = "";

    const chart = createChart(chartRef.current, {
      width: 220,
      height: 100,

      layout: {
        background: { color: "transparent" },
        attributionLogo: false,
        textColor: "#8b949e",
      },

      grid: {
        vertLines: { visible: false },
        horzLines: { visible: false },
      },

      rightPriceScale: {
        visible: false,
        borderVisible: false,
      },

      leftPriceScale: {
        visible: false,
        borderVisible: false,
      },

      timeScale: {
        visible: false,
        borderVisible: false,
      },

      crosshair: {
        vertLine: {
          visible: true,
          labelVisible: false,
        },
        horzLine: {
          visible: false,
          labelVisible: false,
        },
      },

      handleScroll: false,
      handleScale: false,
    });

    const areaSeries = chart.addAreaSeries({
      lineColor: isPositive ? "#22c55e" : "#ef4444",
      topColor: isPositive
        ? "rgba(34, 197, 94, 0.28)"
        : "rgba(239, 68, 68, 0.28)",
      bottomColor: "rgba(0, 0, 0, 0)",
      lineWidth: 2,
      priceLineVisible: false,
      lastValueVisible: false,
    });

    areaSeries.setData(priceData);
    chart.timeScale().fitContent();

    return () => chart.remove();
  }, [priceData, isPositive]);

  return (
    <div className="marketCard">
      <div className="marketInfo m-2">
        <h5>{name}</h5>

        <h3>{latestPrice.toFixed(2)}</h3>

        <p className={isPositive ? "marketPositive" : "marketNegative"}>
          {isPositive ? "+" : ""}
          {change.toFixed(2)} ({isPositive ? "+" : ""}
          {percentChange.toFixed(2)}%)
        </p>
      </div>

      <div ref={chartRef} className="marketChart" />
    </div>
  );
}

export default MarketCard;
