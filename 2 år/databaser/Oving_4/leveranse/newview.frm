TYPE=VIEW
query=select `leveranse`.`levinfov2`.`levnr` AS `levnr`,`leveranse`.`levinfov2`.`navn` AS `navn`,`leveranse`.`levinfov2`.`adresse` AS `adresse`,`leveranse`.`levinfov2`.`levby` AS `levby`,`leveranse`.`by_og_fylke`.`fylke` AS `fylke`,`leveranse`.`levinfov2`.`postnr` AS `postnr` from `leveranse`.`levinfov2` join `leveranse`.`by_og_fylke` where (`leveranse`.`levinfov2`.`levby` = `leveranse`.`by_og_fylke`.`city`)
md5=53c0ed3fa4333d974474231e61047c94
updatable=1
algorithm=0
definer_user=root
definer_host=localhost
suid=2
with_check_option=0
timestamp=2022-09-22 11:12:35
create-version=1
source=SELECT levnr,navn,adresse,levby,fylke,postnr\nFROM levinfov2 , by_og_fylke\nWHERE levby = city
client_cs_name=utf8mb4
connection_cl_name=utf8mb4_unicode_ci
view_body_utf8=select `leveranse`.`levinfov2`.`levnr` AS `levnr`,`leveranse`.`levinfov2`.`navn` AS `navn`,`leveranse`.`levinfov2`.`adresse` AS `adresse`,`leveranse`.`levinfov2`.`levby` AS `levby`,`leveranse`.`by_og_fylke`.`fylke` AS `fylke`,`leveranse`.`levinfov2`.`postnr` AS `postnr` from `leveranse`.`levinfov2` join `leveranse`.`by_og_fylke` where (`leveranse`.`levinfov2`.`levby` = `leveranse`.`by_og_fylke`.`city`)
