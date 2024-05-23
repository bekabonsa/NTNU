print("Oppgave 9")

import numpy as np
import numpy_financial as npf

# Definerte verdier
I0 = -4000  # Investering nå
I1 = -2000  # Investering om ett år
k4 = 1000   # Forventet kontantstrøm i år 4
vekstrate = 0.05  # Årlig vekstrate for kontantstrøm
r = 0.08    # Kalkulasjonsrente
varighet = 8  # Varighet av konstante kontantstrømmer etter år 6, til og med år 14

# Beregne kontantstrømmer for år 5 og 6
k5 = k4 * (1 + vekstrate)
k6 = k5 * (1 + vekstrate)

# Kontantstrømmer fra år 0 til 14
cash_flows = np.zeros(15)  # Opprette en array fylt med 0 for 15 år
cash_flows[0] = I0
cash_flows[1] = I1
cash_flows[4] = k4
cash_flows[5] = k5
cash_flows[6] = k6
# Kontantstrømmer for år 7 til 14 (år 6 til 13 i indekser) er konstante og lik k6
cash_flows[7:15] = k6

# Beregne nettonåverdien
npv = npf.npv(r, cash_flows)
print(round(npv, 0))