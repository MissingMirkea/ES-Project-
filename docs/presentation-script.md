# FoodWise — script de prezentare (5 minute)

## 1. Problema și direcția

„Pentru persoane care vor să cumpere alimente mai ieftin și să reducă risipa, FoodWise compară ofertele magazinelor, generează un coș săptămânal pentru `CUT` sau `BULK` și permite rezervarea pachetelor care urmează să fie retrase.”

## 2. Demo UI

Pornesc aplicația cu Maven și deschid homepage-ul. Arăt trei fluxuri: sales hunting, food rescue și coșul săptămânal.

La coș aleg obiectivul și bugetul. `HomeController` apelează `WeeklyBasketStrategy`, găsește cea mai ieftină ofertă pentru fiecare `Product`, construiește `BasketLine` și afișează totalul, macro-urile, cantitățile, magazinul și subtotalul.

## 3. Clase și interfețe

`Product`, `Store`, `PriceOffer`, `FoodRescueOffer` și `Reservation` sunt clasele de domeniu. `BasketStrategy` permite strategii diferite pentru `CUT` și `BULK`, iar `PriceComparisonService` definește alegerea celei mai ieftine oferte. Diagrama este în `docs/class-diagram.md`.

## 4. Fișier

La calcularea coșului, `FileDataService.writePreferences()` scrie obiectivul și bugetul în `data/preferences.json`. La homepage, `readPreferences()` citește fișierul înapoi și gestionează `FileNotFoundException`, `IOException` și `NumberFormatException`.

## 5. Baza de date și JDBC

H2 persistă entitățile prin JPA/Hibernate. Pentru a demonstra JDBC explicit, `/api/database/demo` folosește `JdbcTemplate` și execută `COUNT(*)` plus un query pentru numele produselor. Inserarea și actualizarea se văd prin `/api/products`.

## 6. Validare, excepții și roluri

Inputul este validat cu Bean Validation și verificări în servicii. `InvalidBasketException` și `OfferNotFoundException` sunt excepții custom. `ADMIN` poate administra produse, `SHOP_MANAGER` poate publica oferte, iar `CLIENT` poate citi oferte și rezerva pachete.

## 7. Alegerea finală

Pentru secțiunea 10–13 aleg REST: `ProductController` oferă CRUD complet, iar interfața web Thymeleaf rămâne demo-ul vizual. Nu revendic simultan JavaFX, REST și Thymeleaf, deoarece regulamentul marchează o singură alegere.

## 8. Threads și sockets

Pentru punctele opționale, `/api/demo/threads-sockets` pornește o sarcină pe `ExecutorService`, deschide un `ServerSocket` local, conectează un client `Socket`, trimite mesajul și returnează răspunsul serverului. Astfel pot arăta concret atât thread-ul, cât și comunicarea client-server.
