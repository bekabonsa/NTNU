from scipy.optimize import minimize_scalar
# Definerer den variable kostnadsfunksjonen med den nye formelen
def total_kostnad(Q):
    VC = 5 * Q + 0.5 * Q ** 1.5
    FC = 1000 if Q <= 200 else 1200  # 1000 for det første skiftet, +200 for det andre
    return VC + FC

def gjennomsnittlig_kostnad(Q):
    if Q == 0:  # Unngå divisjon med null
        return float('inf')
    return total_kostnad(Q) / Q

# Finn minste gjennomsnittlige kostnad
resultat = minimize_scalar(gjennomsnittlig_kostnad, bounds=(1, 400), method='bounded')

print(resultat.fun)
