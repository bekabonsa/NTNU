from sympy import *

# Definerer symboler
Q = symbols('Q', real=True, positive=True)

# Definerer etterspørselsfunksjonen og marginalkostnaden direkte
P_Q = 100 - 0.1 * Q
MC = 25

# Totalinntekt TR(Q) er P(Q) * Q
TR_Q = P_Q * Q

# Marginalinntekt MR(Q) er derivert av TR(Q) med hensyn på Q
MR_Q = diff(TR_Q, Q)

# Finn mengden Q der MR(Q) = MC for et selskap
Q_duopol_each = solve(MR_Q - MC, Q)[0]

# Mengden som ville blitt produsert i perfekt konkurranse er der P(Q) = MC
Q_perfect_competition = solve(P_Q - MC, Q)[0]

# Dødvektstapet er området under etterspørselskurven fra Q_duopol_each til Q_perfect_competition
deadweight_loss = integrate(P_Q - MC, (Q, Q_duopol_each, Q_perfect_competition))

# Evaluerer resultatene
(Q_duopol_each.evalf(), Q_perfect_competition.evalf(), deadweight_loss.evalf())

print(deadweight_loss.evalf())