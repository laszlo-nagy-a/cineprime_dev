# CinePrime program endpoint list
This is a short description for using the program's endpoint system.
You can try it with Curl or Postman.

**Contact:**<br>
E-mail: laszlo.n620@gmail.com <br>
Name: László Nagy <br>
Role: Software engineer<br>

---
## Director endpoint
| URL             | ACTION | DESCRIPTION                                                                         |
|-----------------|--------|-------------------------------------------------------------------------------------|
| /directors      | GET    | get all directors                                                                   |
| /directors/{id} | GET    | get one director                                                                    |
| /directors      | POST   | create a director                                                                   |
| /directors      | PUT    | update director, to update the entity you need to add the public id via the object  |
| /directors/{id} | DELETE | delete director - to delete the entity you need to add the public id via the object |

### URL params
*LIST*
- search - search keyword
- type - search in this field
- pagesize - list element per page
- pagenumber - page number, starts with 0

*GET OR DELETE ONE DIRECTOR*
- id - the identifier of the entity, you can get this id with key *public_id* if you get the collections
---
## Genre endpoint
| URL          | ACTION | DESCRIPTION                                                                      |
|--------------|--------|----------------------------------------------------------------------------------|
| /genres      | GET    | get all genres                                                                   |
| /genres/{id} | GET    | get one genre                                                                    |
| /genres      | POST   | create a genre                                                                   |
| /genres      | PUT    | update genre - to update the entity you need to add the public id via the object |
| /genres/{id} | DELETE | delete genre - to delete the entity you need to add the public id via the object |

### URL params
*LIST*
- search - search keyword
- type - search in this field
- pagesize - list element per page
- pagenumber - page number, starts with 0

*GET OR DELETE ONE GENRE*
- id - the identifier of the entity, you can get this id with key *public_id* if you get the collections
---
## Genre endpoint
| URL          | ACTION | DESCRIPTION                                                                      |
|--------------|--------|----------------------------------------------------------------------------------|
| /movies      | GET    | get all movies                                                                   |
| /movies/{id} | GET    | get one movie                                                                    |
| /movies      | POST   | create a movie                                                                   |
| /movies      | PUT    | update movie - to update the entity you need to add the public id via the object |
| /movies/{id} | DELETE | delete movie - to delete the entity you need to add the public id via the object |

### URL params
*LIST*
- search - search keyword
- type - search in this field
- pagesize - list element per page
- pagenumber - page number, starts with 0

*GET OR DELETE ONE MOVIE*
- id - the identifier of the entity, you can get this id with key *public_id* if you get the collections

---
## Star endpoint
| URL         | ACTION | DESCRIPTION                                                                     |
|-------------|--------|---------------------------------------------------------------------------------|
| /stars      | GET    | get all stars                                                                   |
| /stars/{id} | GET    | get one star                                                                    |
| /stars      | POST   | create a star                                                                   |
| /stars      | PUT    | update star - to update the entity you need to add the public id via the object |
| /stars/{id} | DELETE | delete star - to delete the entity you need to add the public id via the object |

### URL params
*LIST*
- search - search keyword
- type - search in this field
- pagesize - list element per page
- pagenumber - page number, starts with 0

*GET OR DELETE ONE STAR*
- id - the identifier of the entity, you can get this id with key *public_id* if you get the collections

---
## Writer endpoint
| URL           | ACTION | DESCRIPTION                                                                       |
|---------------|--------|-----------------------------------------------------------------------------------|
| /writers      | GET    | get all writers                                                                   |
| /writers/{id} | GET    | get one writer                                                                    |
| /writers      | POST   | create a writer                                                                   |
| /writers      | PUT    | update writer - to update the entity you need to add the public id via the object |
| /writers/{id} | DELETE | delete writer - to delete the entity you need to add the public id via the object |

### URL params
*LIST*
- search - search keyword
- type - search in this field
- pagesize - list element per page
- pagenumber - page number, starts with 0

*GET OR DELETE ONE WRITER*
- id - the identifier of the entity, you can get this id with key *public_id* if you get the collections

