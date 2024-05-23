# Bedriftens balansetall
immaterielle_eiendeler = 822
innskutt_egenkapital = 532
inventar_og_maskiner = 502
kontanter = 542
kortsiktig_gjeld = 540
kortsiktige_fordringer = 163
langsiktig_gjeld = 1000
opptjent_egenkapital_tidligere_perioder = 829
tomter_bygninger = 686
varelager = 509
arets_resultat = 323

# Beregning av likviditetsgrad 1 og 2 før utbetaling av utbytte
omlopsmidler = kontanter + kortsiktige_fordringer + varelager + 0.5
likviditetsgrad_1 = omlopsmidler / kortsiktig_gjeld
likviditetsgrad_2 = (omlopsmidler - varelager) / kortsiktig_gjeld

print(f"Likviditetsgrad 1 før utbytte: {likviditetsgrad_1:.2f}")
print(f"Likviditetsgrad 2 før utbytte: {likviditetsgrad_2:.2f}")

# Ønsket likviditetsgrad 1 og 2 etter utbetaling av utbytte
onsket_likviditetsgrad_1 = 2.0
onsket_likviditetsgrad_2 = 1.0

# Beregning av maksimalt utbytte for å opprettholde ønsket likviditetsgrad 1
utbytte_likviditetsgrad_1 = omlopsmidler - (onsket_likviditetsgrad_1 * kortsiktig_gjeld)

# Beregning av maksimalt utbytte for å opprettholde ønsket likviditetsgrad 2
utbytte_likviditetsgrad_2 = (omlopsmidler - varelager) - (onsket_likviditetsgrad_2 * kortsiktig_gjeld)

# Velg det laveste utbyttet for å oppfylle begge kravene
maksimalt_utbytte = min(utbytte_likviditetsgrad_1, utbytte_likviditetsgrad_2)

print(f"Maksimalt utbytte for å opprettholde likviditetsgrad 1 på {onsket_likviditetsgrad_1:.1f}: {utbytte_likviditetsgrad_1:.0f}")
print(f"Maksimalt utbytte for å opprettholde likviditetsgrad 2 på {onsket_likviditetsgrad_2:.1f}: {utbytte_likviditetsgrad_2:.0f}")
print(f"Bedriften kan gi inntil {maksimalt_utbytte:.0f} i utbytte til eierne.")