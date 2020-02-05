# financial-assistant

## A dolgozat által megvalósítandó szoftver követelmény leírása

A dolgozat célja egy webes alkalmazás megvalósítása Java backend-del a Spring keretrendszer használatával. Az alkalmazás alapfunkciója a felhasználók személyes pénzügyeinek nyilvántartása.

Az alkalmazás lehetőséget nyújt a felhasználó bevételeinek, kiadásainak és megtakarításainak feljegyzésére és lekérdezésére, valamint kiadásait a felhasználó több szempont szerint osztályozhatja, például tervezett vagy impulzív volt a kiadás, valamint milyen kategóriába sorolható a vásárolt termék vagy szolgáltatás. A kézzel felvitt adatokon kívül rendszeres kiadások megadására is lenne lehetőség. Rendszeres kiadás lehet például a lakbér, rendszeres bevétel pedig a havi fizetés.

A felhasználók, valamint bevételeik és kiadásaik adatbázisban kerülnek tárolásra.

Megvalósítandó, hogy az alkalmazást csak regisztrált felhasználók használhassák, és jogosulatlanok ne érhessék el más felhasználók adatait.

A tárolt adatok alapján a felhasználók vásárlási szokásairól statisztika készül, mely megmutatja például, mennyi pénzt költött a felhasználó adott idő leforgása alatt, és ez arányaiban hogyan oszlott el a kategóriák között.

Az alkalmazás felhasználói különböző jogosultságokkal rendelkeznek, bizonyos funkciókhoz, például meghatározott statisztikákhoz nem fér hozzá minden felhasználó.

![Use case diagram](https://github.com/szilagyizsofia/financial-assistant/blob/master/Untitled%20Diagram.jpg)
