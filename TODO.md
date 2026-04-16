#include "pricing.h"

float benchmark_models(OptionInfo option, float american)
{
printf("Option: %s\n", option.is_put ? "Put" : "Call");
printf("Style: %s\n", american ? "American" : "European");
printf("Inputs:\n");
printf("S=%f K=%f T=%f r=%f sigma=%f\n\n",
option.strike_price, option.current_price, option.expiry_years, option.rf_rate, option.volatility);

    PricingResult pr_s[3];
    pr_s[0] = price(option, "BlackScholes", american);
    pr_s[1] = price(option, "Binomial", american);
    pr_s[2] = price(option, "MonteCarlo", american);

    print_results(3, pr_s, american);
    return (pr_s[0].price + pr_s[1].price + pr_s[2].price) / 3.0f;

}

void benchmark_model(OptionInfo option, float american, char \*model)
{
if (strcmp(model, "Binomial") == 0)
{
PricingResult results[7];
results[0] = price(option, "BlackScholes", american);
results[1] = price_advanced(option, model, american, 10, 0);
results[2] = price_advanced(option, model, american, 50, 0);
results[3] = price_advanced(option, model, american, 100, 0);
results[4] = price_advanced(option, model, american, 500, 0);
results[5] = price_advanced(option, model, american, 1000, 0);
results[6] = price_advanced(option, model, american, 5000, 0);
}
else if (strcmp(model, "MonteCarlo") == 0)
{
PricingResult results[5];
results[0] = price(option, "BlackScholes", american);

        results[1] = price_advanced(option, model, american, 0, 1e3);
        results[2] = price_advanced(option, model, american, 0, 1e4);
        results[3] = price_advanced(option, model, american, 0, 1e5);
        results[4] = price_advanced(option, model, american, 0, 1e6);
    }
    else
    {
        PricingResult result[1] = {price(option, "BlackScholes", american)};
        print_results(1, result, american);
    }

}

Next step: make the benchmarks print this for binomial.

Steps Price Error vs BS

---

10 ...
100 ...
1000 ...
5000 ...

And this for Monte Carlo:

## Sims Price Error Time

1e3 ...
1e4 ...
1e5 ...
1e6 ...

---

#ifndef PRICING_H
#define PRICING_H

#include <string.h>
#include <stdio.h>
#include "black_scholes.h"
#include "binom.h"
#include "testing.h"

typedef struct PricingResult
{
const char \*model;
float price;
double runtime;
int steps;
int simulations;
} PricingResult;

// current_price
// strike_price
// expiry_years
// rf_rate
// volatility
// is_put
typedef struct OptionInfo
{
float current_price;
float strike_price;
float expiry_years;
float rf_rate;
float volatility;

    int is_put;

} OptionInfo;

PricingResult price(OptionInfo option, char *model, int is_american);
PricingResult price_advanced(OptionInfo option, char *model, int is_american, int n_steps, int n_sims);

static inline void print_results(int count, PricingResult results[], int american)
{
printf("\nModel Results\n");
printf("-----------------------------------------------------------------\n");
printf("%-20s %-12s %-12s %-10s\n", "Model", "Applicable", "Price", "Time");
printf("-----------------------------------------------------------------\n");

    for (int i = 0; i < count; i++)
    {
        PricingResult pr = results[i];

        char label[32];
        char applicable[12];
        char price_str[16];

        if (strcmp(pr.model, "Binomial") == 0)
        {
            snprintf(label, sizeof(label), "%s(%d)", pr.model, pr.steps);
        }
        else if (strcmp(pr.model, "MonteCarlo") == 0)
        {
            snprintf(label, sizeof(label), "%s(%.0e)", pr.model, (double)pr.simulations);
        }
        else
        {
            snprintf(label, sizeof(label), "%s", pr.model);
        }

        if (american)
        {
            if (strcmp(pr.model, "Binomial") == 0)
            {
                snprintf(applicable, sizeof(applicable), "Yes");
                snprintf(price_str, sizeof(price_str), "%.2f", pr.price);
            }
            else if (strcmp(pr.model, "MonteCarlo") == 0)
            {
                snprintf(applicable, sizeof(applicable), "No*");
                snprintf(price_str, sizeof(price_str), "N/A");
            }
            else
            {
                snprintf(applicable, sizeof(applicable), "No");
                snprintf(price_str, sizeof(price_str), "N/A");
            }
        }
        else
        {
            snprintf(applicable, sizeof(applicable), "Yes");
            snprintf(price_str, sizeof(price_str), "%.2f", pr.price);
        }

        // ----- Unified print -----
        printf("%-20s %-12s %-12s %-10.4f\n",
               label,
               applicable,
               price_str,
               pr.runtime);
    }

    printf("-----------------------------------------------------------------\n");

    if (american)
    {
        printf("* basic implementation prices European exercise only\n");
    }

}

#endif // PRICING_H
