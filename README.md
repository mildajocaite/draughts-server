# Mokymosi ir mokymo žaisti šaškėmis sistemos prototipo serverio programinis kodas

Šioje repozitorijoje yra saugomas mokymosi ir mokymo žaisti šaškėmis sistemos prototipo serverio kodas. Kitos susijusios repozitorijos:
- kliento programinis kodas „https://github.com/krismilda/draughts-client"
- „Arduino“ valdiklio programinis kodas „https://github.com/krismilda/iot-draughts-arduino“

Programos paleidimo intrukcijos:
- nusikopijuokite repozitoriją;
- įvykdykite Maven clean-install;
- paleiskite programą pasinaudodami kokia nors IDE arba komanda run cd draughts-web && mvn spring-boot:run. 

Šiame projekte yra naudojama „H2 in memory" duomenų bazė. Tam, kad būtų galima išbandyti sistemos teikiamą
funkcionalumą, duomenų bazė yra užpildoma pradiniais duomenimis, t.y. sukuriama keletas naudotojų, šaškių lentų, uždavinių ir partijų.

Norėdami prisijungti kaip treneris, naudokite šiuos prisijungimo duomenis:
- el. paštas: treneris@gmail.com
- slaptažodis: asd123

Norėdami prisijungti kaip mokinys, naudokite šiuos prisijungimo duomenis:
- el. paštas: mokinys@gmail.com
- slaptažodis: asd123
