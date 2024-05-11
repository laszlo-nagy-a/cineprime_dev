# DATABASE STRUCTURE FOR CINEPRIME APP

## TABLES AND COLUMNS

### genre - műfaj
---
- **id**  - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- name - szöveg (VARCHAR(500) NOT NULL) - *műfaj neve*

### director - rendező
---
- **id** - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- person_data - személy (PERSON NOT NULL) - *személy adatok*

### writer - író
---
- **id** - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- person_data - személy (PERSON NOT NULL) - *személy adatok*

### star - színész
---
- **id** - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- person_data - személy (PERSON NOT NULL) - *személy adatok*

### movie - film
---
- **id** - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- title - szöveg (VARCHAR(500) NOT NULL) - *film címe*
- release_date - dátum (DATETIME) - *megjelenés dátuma*
- pg  - egész szám (BIT) - *korhatár besorolás*
- playtime_min - egész szám (SMALLINT UNSIGNED) - *játékidő percben*
- last_modified_at - időbélyeg  (TIMESTAMP) - *utolsó módosítás dátuma*

### person_data - személy adatok
---
- first_name - szöveg (VARCHAR(255) NOT NULL) - *kereszt név*
- last_name - szöveg (VARCHAR(255) NOT NULL) - *vezeték név*
- age - szám (SMALLINT) - *életkor*
- birthdate - dátum(DATETIME) - *születés dátuma*

### writer_movie - kapcsolótábla
--- 
- movie_id - egész szám (BIGINT FOREIGN KEY) - *film azonosítója*
- writer_id - egész szám (BIGINT FOREIGN KEY) - *író azonosítója*

### director_movie - kapcsolótábla
---
- movie_id - egész szám (BIGINT FOREIGN KEY) - *film azonosítója*
- director_id - egész szám (BIGINT FOREIGN KEY) - *rendező azonosítója*

### star_movie - kapcsolótábla
---
- movie_id - egész szám (BIGINT FOREIGN KEY) - *film azonosítója*
- star_id - egész szám (BIGINT FOREIGN KEY) - *színész azonosítója*

### genre_movie - kapcsolótábla
---
- movie_id - egész szám (BIGINT FOREIGN KEY) - *film azonosítója*
- genre_id - egész szám (BIGINT FOREIGN KEY) - *műfaj azonosítója*
