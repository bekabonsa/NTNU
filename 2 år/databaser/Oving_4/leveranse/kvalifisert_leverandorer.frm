TYPE=VIEW
query=select `mulige_leverandorer`.`lev_nr` AS `lev_nr`,sum(`mulige_leverandorer`.`total_pris`) AS `total_pris` from `leveranse`.`mulige_leverandorer` group by `mulige_leverandorer`.`lev_nr` having (count(0) = (select count(0) from `leveranse`.`ordredetalj` where (`leveranse`.`ordredetalj`.`ordrenr` = 18)))
md5=26f6cb9ee39310d8240556b366a33e1a
updatable=0
algorithm=0
definer_user=root
definer_host=localhost
suid=2
with_check_option=0
timestamp=2022-09-24 09:11:47
create-version=1
source=SELECT lev_nr,SUM(total_pris)\nFROM mulige_leverandorer GROUP BY lev_nr\nHAVING COUNT(*)= (SELECT COUNT(*) FROM ordredetalj WHERE ordrenr=18)
client_cs_name=utf8mb4
connection_cl_name=utf8mb4_unicode_ci
view_body_utf8=select `mulige_leverandorer`.`lev_nr` AS `lev_nr`,sum(`mulige_leverandorer`.`total_pris`) AS `total_pris` from `leveranse`.`mulige_leverandorer` group by `mulige_leverandorer`.`lev_nr` having (count(0) = (select count(0) from `leveranse`.`ordredetalj` where (`leveranse`.`ordredetalj`.`ordrenr` = 18)))
