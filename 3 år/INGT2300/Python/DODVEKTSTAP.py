import sympy as sp

# Definerer symbolene
Q = sp.symbols('Q')

# Gitt fra oppgaven
P_Q = 100 - 0.1*Q  # Prisfunksjonen
MC_Q = 75      # Marginalkostnaden

# Finner totalinntekt (TR) som P*Q og deretter marginalinntekt (MR) ved å derivere TR med hensyn på Q
TR = P_Q * Q
MR = sp.diff(TR, Q)

# MR(Q*) = MC(Q*) 
Q_M = sp.solve(sp.Eq(MR, MC_Q), Q)


Q_SF = sp.solve(sp.Eq(P_Q,MC_Q))

f = P_Q-MC_Q

dodvektstapet = sp.integrate(f, (Q, Q_M, Q_SF))

print(Q_M)
print(Q_SF)
print(dodvektstapet)