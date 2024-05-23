produkter = ['A', 'B', 'C', 'D']
priser = [100, 110, 80, 90]
salg = [50000, 40000, 45000, 55000]
dm = [16, 17, 12, 14]
dl = [15, 18, 12, 15]
provisjon = [10, 11, 8, 9]

MOF = 25/100
TOF = 155/100
TOV = 45/100
SAOF = 15/100

# sikkerhetsmargin = dagens omsetning i stk - nullpunkt i stk
# nullpunkt i stykk = faste kostnader / dekningsbidrag per stk
# dekningsbidrag per stk = salgspris - variable kostnader per stk

dekningsbidrag = lambda salgspris, variable_kost: salgspris - variable_kost
nullpunkt = lambda faste_kost, dekningsbidrag: faste_kost / dekningsbidrag
sikkerhetsmargin = lambda omsetning, nullpunkt: omsetning - nullpunkt

# regne ut variable kostnader
variable_kostnader = []
for i in range(4):
    mat = dm[i] * (1)
    prod = dl[i] * (1+TOV)
    variable_kost = mat + prod + provisjon[i]
    variable_kostnader.append(variable_kost)

# regne ut faste kostnader
faste_kostnader = []
for i in range(4):
    mat = dm[i] * (MOF)
    prod = dl[i] * (TOF)
    tk = dm[i] + dl[i] + mat + dl[i]*TOV + prod
    faste_kost = mat + prod + tk * SAOF
    faste_kostnader.append(faste_kost*salg[i])

# regne ut dekningsbidrag
dekningsbidrag_stk = []
for i in range(4):
    dekningsbidrag_stk.append(dekningsbidrag(priser[i], variable_kostnader[i]))

# regne ut nullpunkt
nullpunkt_stk = []
for i in range(4):
    nullpunkt_stk.append(nullpunkt(faste_kostnader[i], dekningsbidrag_stk[i]))

# regne ut sikkerhetsmargin 
sikkerhetsmargin_stk = []
for i in range(4):
    sikkerhetsmargin_stk.append(sikkerhetsmargin(salg[i], nullpunkt_stk[i]))

max_sikkerhet = max(sikkerhetsmargin_stk)
max_index = sikkerhetsmargin_stk.index(max_sikkerhet)
print("4) Produkt", produkter[max_index], "har størst sikkerhetsmargin med", round(max_sikkerhet), "stk")