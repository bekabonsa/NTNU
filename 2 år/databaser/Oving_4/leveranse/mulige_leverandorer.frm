TYPE=VIEW
query=select `leveranse`.`prisinfo`.`levnr` AS `lev_nr`,`leveranse`.`ordredetalj`.`delnr` AS `del_nr`,`leveranse`.`prisinfo`.`pris` AS `pris`,(`leveranse`.`prisinfo`.`pris` * `leveranse`.`ordredetalj`.`kvantum`) AS `total_pris` from `leveranse`.`prisinfo` join `leveranse`.`ordredetalj` where ((`leveranse`.`prisinfo`.`delnr` = `leveranse`.`ordredetalj`.`delnr`) and (`leveranse`.`ordredetalj`.`ordrenr` = 18))
md5=6089073ec69250ee97b7a8af431e87cf
updatable=1
algorithm=0
definer_user=root
definer_host=localhost
suid=2
with_check_option=0
timestamp=2022-09-24 08:59:32
create-version=1
source=SELECT levnr, ordredetalj.delnr, pris, pris*kvantum\nFROM prisinfo, ordredetalj\nWHERE prisinfo.delnr = ordredetalj.delnr AND ordredetalj.ordrenr = 18
client_cs_name=utf8mb4
connection_cl_name=utf8mb4_unicode_ci
view_body_utf8=select `leveranse`.`prisinfo`.`levnr` AS `lev_nr`,`leveranse`.`ordredetalj`.`delnr` AS `del_nr`,`leveranse`.`prisinfo`.`pris` AS `pris`,(`leveranse`.`prisinfo`.`pris` * `leveranse`.`ordredetalj`.`kvantum`) AS `total_pris` from `leveranse`.`prisinfo` join `leveranse`.`ordredetalj` where ((`leveranse`.`prisinfo`.`delnr` = `leveranse`.`ordredetalj`.`delnr`) and (`leveranse`.`ordredetalj`.`ordrenr` = 18))
