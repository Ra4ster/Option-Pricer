#include "volatility.h"
#include "benchmark.h"

const float rf_rate = 0.004f;
const float expiry_days = 365.0f; // !15
const float strike = 680.0f;
const float current = 680.0f; // !699.57f;
const float expiry_years = expiry_days / 365.0f;

int main(int argc, char *argv[])
{
    int american = (argc > 1 && strcmp(argv[1], "american") == 0);
    float sigma = 0.20f;

    OptionInfo option = {current, strike, expiry_years, rf_rate, sigma, 0};
    benchmark_models(option, american);

    Greeks g = get_greeks(
        current, strike, expiry_years, rf_rate, sigma, 0);
    print_greeks(g);

    return 0;
}