# Options Pricing Engine – Next Steps & Concepts

## 1. What “Applicable” Means

**Applicable = “Is this model theoretically valid for this contract?”**

It does NOT mean “can the code run,” but rather:
Does this model produce a _correct_ price under its assumptions?

### Example

```
Option: Call
Style: American
```

| Model         | Applicable | Reason                                             |
| ------------- | ---------- | -------------------------------------------------- |
| Black-Scholes | ❌ No      | Assumes European exercise only                     |
| Binomial      | ✅ Yes     | Supports early exercise                            |
| Monte Carlo   | ❌ No\*    | Current implementation only prices European payoff |

_Monte Carlo can support American options, but requires advanced methods (e.g., Longstaff–Schwartz), which you have not implemented._

---

## 2. European vs American Options

### Definitions

- **European**: Exercise only at expiry
- **American**: Exercise anytime before expiry

---

### Model Support

#### Black-Scholes

- Closed-form analytic solution
- Assumes no early exercise

Supports:

- ✔ European Call
- ✔ European Put
- ✘ American options

---

#### Binomial Tree

- Discrete-time lattice
- Supports early exercise via:

```
value = max(hold_value, exercise_value)
```

Supports:

- ✔ European
- ✔ American

---

#### Monte Carlo (your implementation)

- Simulates only terminal payoff:

```
payoff = max(S_T - K, 0)
```

Supports:

- ✔ European
- ✘ American

---

## 3. Robust Implied Volatility

### Current Method

You are using Newton-Raphson:

```
sigma -= diff / vega
```

This is good, but incomplete.

---

### Problems

1. Vega scaling ambiguity
2. No fallback if Newton fails
3. No bounds checking
4. Can produce negative or invalid sigma

---

### What “Robust” Means

#### A. Input Validation

For a call:

```
max(S - K e^{-rT}, 0) ≤ price ≤ S
```

Reject invalid inputs.

---

#### B. Bracketing

```
sigma_low  = 1e-4
sigma_high = 3.0
```

Ensure the root lies within bounds.

---

#### C. Hybrid Solver

1. Try Newton-Raphson
2. If failure:
   - switch to bisection

---

#### D. Convergence Criteria

- Absolute price error tolerance
- Max iterations

---

### Suggested Struct

```
typedef struct {
    float sigma;
    int converged;
    int iterations;
    const char *method;
} IVResult;
```

---

## 4. Validation Concepts

These are critical for credibility.

---

### (a) Call-Put Parity

For European options:

```
C - P = S - K e^{-rT}
```

Test:

```
lhs = call - put
rhs = S - K * exp(-rT)
```

---

### (b) Binomial → Black-Scholes Convergence

As steps increase:

```
steps → ∞
binomial → Black-Scholes
```

Test:

```
10, 100, 1000, 5000
```

---

### (c) Monte Carlo Convergence

As simulations increase:

```
N → ∞
MC price → true price
```

Error shrinks:

```
~ O(1 / sqrt(N))
```

---

### (d) Implied Vol Recovery

1. Choose σ
2. Compute price
3. Recover σ via solver

Check:

```
σ_recovered ≈ σ_original
```

---

### (e) Greek Sanity Checks

| Greek        | Expected Behavior |
| ------------ | ----------------- |
| Delta (call) | 0 → 1             |
| Delta (put)  | -1 → 0            |
| Gamma        | > 0               |
| Vega         | > 0               |
| Theta        | usually < 0       |
| Rho (call)   | > 0               |

---

## 5. Convergence Experiments

### Binomial Convergence

```
Steps    Price    Error vs BS
--------------------------------
10       ...
100      ...
1000     ...
5000     ...
```

---

### Monte Carlo Convergence

```
Sims     Price    Error    Time
--------------------------------
1e3      ...
1e4      ...
1e5      ...
1e6      ...
```

---

## 6. Recommended Next Steps

### 1. Improve Implied Volatility

- Remove magic constants
- Add bisection fallback
- Add bounds checking

---

### 2. Add Validation Tests

- Call-put parity
- Model convergence
- Implied vol recovery
- Greek sanity

---

### 3. Add Convergence Tables (CLI Output)

---

### 4. Then Add Visualization

---

## Key Insight

Right now:
You compute prices.

Next step:
You prove those prices are correct.

That is the difference between:

- a student project
- a production-quality engine
