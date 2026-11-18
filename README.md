# FoodWise

FoodWise este o aplicație Java pentru persoane care vor să cumpere alimente mai ieftin, să-și planifice coșul săptămânal și să salveze produse care altfel ar fi risipite.

## Rulare

Necesită Java 21 și Maven:

```powershell
mvn test
mvn spring-boot:run
```

Deschide `http://localhost:8080`. Dacă portul este ocupat:

```powershell
mvn spring-boot:run "-Dspring-boot.run.arguments=--server.port=18080"
```

## Demo rapid pentru prezentare

1. Deschide pagina principală și arată comparația de prețuri.
2. Mergi la **Construiește coșul tău**, alege `CUT` sau `BULK` și un buget.
3. Apasă **Calculează coșul**: aplicația afișează suma, macro-urile, produsele, cantitățile și magazinul cu oferta cea mai ieftină.
4. Arată `GET /api/database/demo` pentru citirea directă prin JDBC din H2.
5. Arată `GET /api/access/ADMIN`, `GET /api/access/CLIENT` și `GET /api/access/SHOP_MANAGER` pentru roluri.
7. Arată `GET /api/demo/threads-sockets?message=Salut` pentru threads și sockets.
8. Arată diagrama din [docs/class-diagram.md](docs/class-diagram.md) și discursul din [docs/presentation-script.md](docs/presentation-script.md).

## Cerințele proiectului UVT

### Mid-term

- **Clase și relații:** clasele de domeniu sunt în `ro.foodwise.model`: `Product`, `Store`, `PriceOffer`, `FoodRescueOffer`, `Reservation`, `AppUser` și `Goal`.
- **Interfețe:** `BasketStrategy` și `PriceComparisonService` în `ro.foodwise.service`.
- **Colecții:** `List` pentru produse/oferte, `Set` pentru filtrele magazinelor și `Map` pentru preferințele salvate.
- **Fișiere:** `FileDataService` scrie și citește `data/preferences.json`; homepage-ul citește preferințele salvate la pornirea paginii.
- **Configurație:** `application.properties` configurează H2, portul și Thymeleaf; `--server.port` poate porni aplicația pe alt port.
- **Excepții standard:** `IOException`, `FileNotFoundException`, `NumberFormatException` și `IllegalArgumentException` sunt tratate în fluxurile de fișiere/input.
- **Excepții custom:** `InvalidBasketException` și `OfferNotFoundException`.
- **Validare:** `@Valid`, `@NotBlank`, `@Email`, `@Positive`, validare manuală a bugetului, porțiilor și prețurilor.

### Final

- **Teste:** `DomainConstructorTest` testează constructorii principali ai domeniului; `WeeklyBasketStrategyTest` conține teste de comportament pentru `CUT` și `BULK`.
- **Threads și sockets:** `ThreadAndSocketDemoService` folosește `ExecutorService`, `ServerSocket` și `Socket`; demo la `/api/demo/threads-sockets`.
- **Bază de date:** H2 persistentă, mapată prin JPA/Hibernate; schema este creată automat.
- **JDBC:** `DatabaseDemoService` folosește `JdbcTemplate` pentru `COUNT` și citirea produselor; demonstrație la `/api/database/demo`.
- **Insert/update:** `ProductController` și `OfferController` creează/modifică date; `RescueController` inserează rezervări.
- **Roluri:** `AppUser.Role` definește `ADMIN`, `CLIENT`, `SHOP_MANAGER`; `RoleAccessService` aplică acces diferit pentru produse și oferte.
- **REST CRUD ales pentru punctaj:** `ProductController` are GET, POST, PUT și DELETE la `/api/products`. Conform regulii UVT, proiectul revendică varianta REST, nu JavaFX și Thymeleaf simultan.

## Endpoint-uri pentru demonstrație

| Endpoint | Ce demonstrează |
|---|---|
| `GET /api/products` | citire REST |
| `POST /api/products?role=ADMIN` | creare + validare + rol |
| `PUT /api/products/{id}?role=ADMIN` | update |
| `DELETE /api/products/{id}?role=ADMIN` | delete |
| `GET /api/offers/hunt?query=pui&store=Lidl` | căutare/comparare oferte |
| `GET /api/database/demo` | JDBC + baza de date |
| `GET /api/access/ADMIN` | rol și permisiuni |
| `GET /api/rescue/offers` | citire food rescue |
| `POST /api/rescue/offers/{id}/reserve` | validare + tranzacție + insert |

Prețurile actuale din seed sunt demonstrative. Integrarea cu cataloagele retailerilor este o etapă separată și trebuie făcută doar prin surse și condiții de utilizare permise.
