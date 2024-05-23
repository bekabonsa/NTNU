print("Oppgave 10")

# Importere numpy_financial for finansielle beregninger
import numpy_financial as npf

kontantstrom_A = [-2050, 750, 750, 750, 900] # A
kontantstrom_B = [-2150, 650, 650, 650, 650, 1000] # B
kontantstrom_C = [-2200, 600, 600, 700, 700, 1000] # C
kontantstrom_D = [-2400, 650, 650, 650, 650, 650, 750] # D

A_gjentas_hvert_år = 4
B_gjentas_hvert_år = 5
C_gjentas_hvert_år = 5
D_gjentas_hvert_år = 6

rente = 0.06

# finner hvilken som har høyest npv
npv_a = npf.npv(rente, kontantstrom_A)
npv_b = npf.npv(rente, kontantstrom_B)
npv_c = npf.npv(rente, kontantstrom_C)
npv_d = npf.npv(rente, kontantstrom_D)


def annu(npv, n_e):
    return npv * (rente / (1 - (1 + rente)**-n_e))

annu_a = annu(npv_a, len(kontantstrom_A) - 1)
annu_b = annu(npv_b, len(kontantstrom_B) - 1)
annu_c = annu(npv_c, len(kontantstrom_C) - 1)
annu_d = annu(npv_d, len(kontantstrom_D) - 1)


mulighet_a = annu_a* ((rente)/(1-(1+rente)**-A_gjentas_hvert_år))
mulighet_b = annu_b* ((rente)/(1-(1+rente)**-B_gjentas_hvert_år))
mulighet_c = annu_c* ((rente)/(1-(1+rente)**-C_gjentas_hvert_år))
mulighet_d = annu_d* ((rente)/(1-(1+rente)**-D_gjentas_hvert_år))

print(mulighet_a, mulighet_b, mulighet_c, mulighet_d)

print(max(mulighet_a, mulighet_b, mulighet_c, mulighet_d))