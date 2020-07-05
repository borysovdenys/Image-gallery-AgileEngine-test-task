# AgileEngine test task
## Borysov Denys

1. Create postgres db with name image-gallery(username: postgres, password: root), 
or change it in file application.yaml

2. Run app (data is updated every 5 minutes)

3. Try functionality 

- GET /images - JSON all images
- GET /images?page=2 - JSON page images
- GET /images/${id} - JSON image full info
- GET /search/${searchTerm} - that will return all the photos with 
any of the meta fields (author, camera, tags, etc) matching the search term.
