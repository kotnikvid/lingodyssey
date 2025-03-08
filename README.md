# Lingodyssey

Lingodyssey je mikrostoritvena aplikacija za učenje jezikov, ki omogoča uporabnikom interaktivno učenje s pomočjo flash kartic in gamifikacije. Cilj aplikacije je izboljšati učenje jezikov skozi igrifikacijo, prilagojene vaje in sledenje napredku.

## Arhitektura

Aplikacija je sestavljena iz treh mikrostoritev in enega frontend vmesnika:

### Mikroservisi

1. **User Service** – Upravljanje uporabnikov in avtentikacija.
   - Registracija in prijava uporabnikov.
   - Upravljanje uporabniških profilov.
   - Avtentikacija z JWT.
   - Upravljanje dovoljenj in vlog uporabnikov.

2. **Flashcard Service** – Upravljanje učnih kartic in vsebine v več različnih jezikih.
   - Dodajanje, urejanje in brisanje kartic.
   - Organizacija kartic v pakete in kategorije.
   - Prikaz kartic glede na stopnjo znanja uporabnika.
   - Podpora za učenje v različnih jezikih.

3. **Gamification Service** – Sistem točk in nagrad za motivacijo uporabnikov.
   - Sledenje uporabniškemu napredku.
   - Podeljevanje točk za uspešno opravljene vaje.
   - Upravljanje dosežkov in nagrad.
   - Lestvica najboljših uporabnikov.

### Frontend

- **React aplikacija** – Uporabniški vmesnik, ki omogoča interakcijo z mikrostoritvami.
  - Prijava in registracija.
  - Prikaz učnih kartic.
  - Sledenje napredku in pregled dosežkov.
  - Interaktivne igre za utrjevanje znanja.

## Struktura projekta

```
Lingodyssey/
│── services/
│   ├── user/
│   ├── flashcard/
│   ├── gamification/
│── frontend/ (React aplikacija)
│── docs/ (dokumentacija, diagrami)
│── README.md
│── docker-compose.yml
```

