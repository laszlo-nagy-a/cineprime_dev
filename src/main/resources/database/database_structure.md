# DATABASE STRUCTURE FOR CINEPRIME APP
<!--- TODO: updatelni kell a diagram.pdf-et 
    movie -> description mező
--->
## TABLES AND COLUMNS

### genre - műfaj
---
- **id**  - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- name - szöveg (VARCHAR(500) NOT NULL) - *műfaj neve*
- public_id - szöveg (VARCHAR(255) NOT NULL) - *publikus azonosító*
- deleted_at - dátum (DATE DEFAULT NULL) - *logikai törlés dátuma*

### director - rendező
---
- **id** - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- public_id - szöveg (VARCHAR(255) NOT NULL) - *publikus azonosító*
- first_name - szöveg (VARCHAR(255) NOT NULL) - *keresztnév*
- last_name - szöveg (VARCHAR(255) NOT NULL) - *vezetéknév*
- age - egész szám (SAMMLINT) - *kor*
- birth_date - dátum (DATE) - *születési dátum*
- deleted_at - dátum (DATE DEFAULT NULL) - *logikai törlés dátuma*


### writer - író
---
- **id** - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- public_id - szöveg (VARCHAR(255) NOT NULL) - *publikus azonosító*
- first_name - szöveg (VARCHAR(255) NOT NULL) - *keresztnév*
- last_name - szöveg (VARCHAR(255) NOT NULL) - *vezetéknév*
- age - egész szám (SAMMLINT) - *kor*
- birth_date - dátum (DATE) - *születési dátum*
- deleted_at - dátum (DATE DEFAULT NULL) - *logikai törlés dátuma*

### star - színész
---
- **id** - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- public_id - szöveg (VARCHAR(255) NOT NULL) - *publikus azonosító*
- first_name - szöveg (VARCHAR(255) NOT NULL) - *keresztnév*
- last_name - szöveg (VARCHAR(255) NOT NULL) - *vezetéknév*
- age - egész szám (SAMMLINT) - *kor*
- birth_date - dátum (DATE) - *születési dátum*
- deleted_at - dátum (DATE DEFAULT NULL) - *logikai törlés dátuma*

### movie - film
---
- **id** - egész szám (BIGINT PRIMARY KEY AUTO INCREMENT) - *azonosító, elsődleges kulcs*
- public_id - szöveg (VARCHAR(255) NOT NULL) - *publikus azonosító*
- title - szöveg (VARCHAR(500) NOT NULL) - *film címe*
- description - szöveg (VARCHAR(1024) - *film összefolgalója*
- release_date - dátum (DATETIME) - *megjelenés dátuma*
- pg  - egész szám (TINYINT) - *korhatár besorolás*
- playtime_min - egész szám (SMALLINT UNSIGNED) - *játékidő percben*
- last_modified_at - időbélyeg  (TIMESTAMP) - *utolsó módosítás dátuma*
- deleted_at - dátum (DATE DEFAULT NULL) - *logikai törlés dátuma*

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
