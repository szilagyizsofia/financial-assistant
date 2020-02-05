# financial-assistant

## A dolgozat által megvalósítandó szoftver követelmény leírása

A dolgozat célja egy webes alkalmazás megvalósítása Java backend-del a Spring keretrendszer használatával. Az alkalmazás alapfunkciója a felhasználók személyes pénzügyeinek nyilvántartása.

Az alkalmazás lehetőséget nyújt a felhasználó bevételeinek, kiadásainak és megtakarításainak feljegyzésére és lekérdezésére, valamint kiadásait a felhasználó több szempont szerint osztályozhatja, például tervezett vagy impulzív volt a kiadás, valamint milyen kategóriába sorolható a vásárolt termék vagy szolgáltatás. A kézzel felvitt adatokon kívül rendszeres kiadások megadására is lenne lehetőség. Rendszeres kiadás lehet például a lakbér, rendszeres bevétel pedig a havi fizetés.

A felhasználók, valamint bevételeik és kiadásaik adatbázisban kerülnek tárolásra.

Megvalósítandó, hogy az alkalmazást csak regisztrált felhasználók használhassák, és jogosulatlanok ne érhessék el más felhasználók adatait.

A tárolt adatok alapján a felhasználók vásárlási szokásairól statisztika készül, mely megmutatja például, mennyi pénzt költött a felhasználó adott idő leforgása alatt, és ez arányaiban hogyan oszlott el a kategóriák között.

Az alkalmazás felhasználói különböző jogosultságokkal rendelkeznek, bizonyos funkciókhoz, például meghatározott statisztikákhoz, vagy rendszeres kiadások és bevételek megadásához nem fér hozzá minden felhasználó.

![Use case diagram](https://github.com/szilagyizsofia/financial-assistant/blob/master/use_case.jpg)

GIVEN | WHEN | THEN
--- | --- | ---
Megfelelő jogosultságokkal rendelkező, bejelentkezett felhasználó vagyok | Kiválasztom a kiadás/bevétel hozzáadása menüpontot | Megjelenik a kiadás/bevétel adatbeviteli form
Kitöltöttem a kiadás/bevétel adatbeviteli form kötelező mezőit megfelelő formátumú adatokkal | Rákattintok a mentésre | A bevitt adatok az adatbázisba íródnak
Megfelelő jogosultságokkal rendelkező, bejelentkezett felhasználó vagyok | Kiválasztom a kiadás/bevétel törlése menüpontot | Listázásra kerülnek az eddigi kiadásaim/bevételeim
Kiválasztottam a kiadás/bevétel törlése menüpontot | Kiválasztom a listából a törölni kívánt kiadást/bevételt | A kiválasztott kiadás/bevétel törlődik az adatbázisból
Megfelelő jogosultságokkal rendelkező, bejelentkezett felhasználó vagyok | Kiválasztom a kiadás/bevétel módosítása menüpontot | Listázásra kerülnek az eddigi kiadásaim/bevételeim
Kiválasztottam a kiadás/bevétel módosítása menüpontot | Kiválasztom a listából a módosítani kívánt kiadást/bevételt | Megjelenik a kiválasztott kiadás/bevétel adatbeviteli formja az aktuális adatokkal kitöltve
Átírtam a kiadás/bevétel adatbeviteli form módosítani kívánt mezőit úgy, hogy kötelező mezőt nem hagytam üresen, és a megadott adatok megfelelő formátumúak | Rákattintok a mentésre | A kiválasztott kiadás/bevétel adatai a megadottaknak megfelelően frissülnek az adatbázisban
Megfelelő jogosultságokkal rendelkező, bejelentkezett felhasználó vagyok | Kiválasztom a kiadások/bevételek listázása menüpontot | Listázásra kerülnek az eddigi kiadásaim/bevételeim
Megfelelő jogosultságokkal rendelkező, bejelentkezett felhasználó vagyok | Kiválasztom a statisztikák megtekintése menüpontot | Listázásra kerülnek a statisztikák
Kiválasztottam a megtekinteni kívánt statisztikát | Rendelkezem a megtekintéshez szükséges jogosultsággal  | Megjelenik a statisztika
Kiválasztottam a megtekinteni kívánt statisztikát | Nem rendelkezem a megtekintéshez szükséges jogosultsággal | Nem jelenik meg a statisztika
Megfelelő jogosultságokkal rendelkező, bejelentkezett felhasználó vagyok | Kiválasztom a felhasználói profil szerkesztése menüpontot | Megjelenik egy form 
Megadtam egy rendszeres kiadást/bevételt | Rákattintok a mentésre | A profil adatai a megadottaknek megfelelően frissülnek az adatbázisban
Bejelentkezett admin vagyok | Kiválasztom a felhasználók kezelése menüpontot | A végrehajtható műveletek listázásra kerülnek
Kiválasztottam a felhasználó jogosultságainak módosítása opciót | Kiválasztom a felhasználót | Megjelenik egy form
Megadom az új jogosultságokat | A mentésre kattintok | Az adatbázisban az adott felhasználó adatai a megadottaknak megfelelően frissülnek
Kiválasztottam a felhasználó törlése menüpontot | Kiválasztom a felhasználót | A felhasználó törlődik az adatbázisból
