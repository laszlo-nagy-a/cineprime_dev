# DATABASE STRUCTURE FOR CINEPRIME APP

## TABLES AND COLUMNS

### genre - műfaj
---
- **id**  - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- name - szöveg (VARCHAR(255) NOT NULL) - *műfaj neve*
- deleted_at - dátum (DATETIME) - *logikai törlés dátuma *

### director - rendező
---
- **id** - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- person_data - személy (PERSON NOT NULL) - *személy adatok*
- name - szöveg (VARCHAR(255) NOT NULL) - *rendező neve*

### writer - író
---
- **id** - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- person_data - személy (PERSON NOT NULL) - *személy adatok*
- name - szöveg (VARCHAR(255) NOT NULL) - *író neve*

### star - színész
---
- **id** - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- person_data - személy (PERSON NOT NULL) - *személy adatok*
- name - szöveg (VARCHAR(255) NOT NULL) - *író neve*

### movie - film
---
- **id** - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- title - szöveg (VARCHAR NOT NULL) - *film címe*
- release_date - dátum (DATETIME) - *megjelenés dátuma*
- pg  - egész szám (BIT) - *korhatár besorolás*
- playtime_min - egész szám (SMALLINT UNSIGNED) - *játékidő percben*
- last_modified_at - dátum  (DATETIME) - *utolsó módosítás dátuma*

### person_data - személy adatok
---
- first_name - szöveg (VARCHAR NOT NULL) - *kereszt név*
- last_name - szöveg (VARCHAR) - *vezeték név*
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
