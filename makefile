CFILES = volatility.c black_scholes.c binom.c monte_carlo.c pricing.c benchmark.c best_stock.c main.c
CFLAGS = -mfma -mavx -mavx2 -O3 -Wall -Wextra -Wno-unused-function -o

option_pricer: $(CFILES)
	gcc $(CFILES) $(CFLAGS) option_pricer