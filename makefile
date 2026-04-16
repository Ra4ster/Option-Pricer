cfiles = volatility.c black_scholes.c binom.c monte_carlo.c pricing.c benchmark.c main.c

option_pricer: $(cfiles)
	gcc $(cfiles) -mfma -mavx -mavx2 -O3 -o option_pricer