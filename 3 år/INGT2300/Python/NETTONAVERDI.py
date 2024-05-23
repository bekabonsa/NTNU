

# Definerte verdier
C = 205  # Årlig kontantstrøm
r = 0.10  # Kalkulasjonsrente
investering = 1000  # Investeringens beløp

n = 2
NV = -investering
print("year 1: ", NV) 
while True:
    NV += (C/(1+r)**n)
    print("year ", n,":",NV)
    if NV > 0:
        break
    n += 1


