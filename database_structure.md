# DATABASE STRUCTURE FOR CINEPRIME APP

## TABLES AND COLUMNS

### genre - műfaj
---
- **id**  - egész szám (SMALLINT UNSIGNED PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- name - szöveg (VARCHAR(255) NOT NULL) - *műfaj neve*
- created_at - dátum (DATETIME NOT NULL) - *létrehozás dátuma*
- last_modified_at - dátum  (DATETIME) - *utolsó módosítás dátuma*
- deleted_at - dátum (DATETIME) - *logikai törlés dátuma *

### director - rendező
---
- **id** - egész szám (SMALLINT UNSIGNED PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- name - szöveg (VARCHAR(255) NOT NULL) - *rendező neve*
- created_at - dátum (DATETIME NOT NULL) - *létrehozás dátuma*
- last_modified_at - dátum  (DATETIME) - *utolsó módosítás dátuma*
- deleted_at - dátum (DATETIME) - *logikai törlés dátuma *

### writer - író
---
- **id** - egész szám (SMALLINT UNSIGNED PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- name - szöveg (VARCHAR(255) NOT NULL) - *író neve*
- created_at - dátum (DATETIME NOT NULL) - *létrehozás dátuma*
- last_modified_at - dátum  (DATETIME) - *utolsó módosítás dátuma*
- deleted_at - dátum (DATETIME) - *logikai törlés dátuma *

### star - színész
---
- **id** - egész szám (SMALLINT UNSIGNED PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- name - szöveg (VARCHAR(255) NOT NULL) - *író neve*
- created_at - dátum (DATETIME NOT NULL) - *létrehozás dátuma*
- last_modified_at - dátum  (DATETIME) - *utolsó módosítás dátuma*
- deleted_at - dátum (DATETIME) - *logikai törlés dátuma *

### movie - film
---
- **id** - egész szám (SMALLINT UNSIGNED PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- title - szöveg (VARCHAR(255) NOT NULL) - *film címe*
- release_date - dátum (DATETIME) - *megjelenés dátuma*
- pg  - egész szám (BIT) - *korhatár besorolás*
- playtime_min - egész szám (SMALLINT UNSIGNED) - *játékidő percben*
- created_at - dátum (DATETIME NOT NULL) - *létrehozás dátuma*
- last_modified_at - dátum  (DATETIME) - *utolsó módosítás dátuma*
- deleted_at - dátum (DATETIME) - *logikai törlés dátuma*

### writer_movie - kapcsolótábla
--- 
movie_id - egész szám (SMALLINT UNSIGNED FOREIGN KEY) - *film azonosítója*

writer_id - egész szám (SMALLINT UNSIGNED FOREIGN KEY) - *író azonosítója*

### director_movie - kapcsolótábla
---
movie_id - egész szám (SMALLINT UNSIGNED FOREIGN KEY) - *film azonosítója*

director_id - egész szám (SMALLINT UNSIGNED FOREIGN KEY) - *rendező azonosítója*

### star_movie - kapcsolótábla
---
movie_id - egész szám (SMALLINT UNSIGNED FOREIGN KEY) - *film azonosítója*

star_id - egész szám (SMALLINT UNSIGNED FOREIGN KEY) - *színész azonosítója*

### genre_movie - kapcsolótábla
---
movie_id - egész szám (SMALLINT UNSIGNED FOREIGN KEY) - *film azonosítója*

genre_id - egész szám (SMALLINT UNSIGNED FOREIGN KEY) - *műfaj azonosítója*
