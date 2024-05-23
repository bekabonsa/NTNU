from sympy import *

E = symbols('E')

gjeldsgrad_naa = 1.7
D_naa = gjeldsgrad_naa * E

gjeldsandel_naa = D_naa / (D_naa + E)

# Ny gjeld etter økning på 60%
D_ny = D_naa * 1.2

gjeldsandel_ny = D_ny / (D_ny + E)

okning_i_gjeldsandel = gjeldsandel_ny - gjeldsandel_naa

okning_i_gjeldsandel_vereinf = okning_i_gjeldsandel.simplify()

print(okning_i_gjeldsandel_vereinf.evalf())