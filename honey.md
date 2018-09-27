# API 명세서

## User

### 1. POST /users/login
요청 : username, password
응답 : token

### 2. POST /users/join
요청 : username, password
응답 : boolean

### 3. GET /users/info
요청 : token(header)
응답 : username

### 4. GET /users/mark
요청 : token(header)
응답 : marked combinations (cid, image, name, combination, type, marked)

### 5. GET /users/combinations
요청 : token(header)
응답 : my combinations (cid, image, name, combination, type, marked)

### 6. PUT /users/name
요청 : token(header), username
응답 : boolean

## Combination

### 1. GET /combinations/{type}
요청 : token(header), type(param)
응답 : combinations(cid, image, name, combination, type, marked)

### 2. GET /combinations/{name} (검색기능)
요청 : token(header), name(param)
응답 : combinations(cid, image, name, combination, type, marked)

### 2. GET /combinations/{cid} (상세보기)
요청 : token(header), name(param)
응답 : combination(cid, image, name, combination, type)

### 3. POST /combinations
요청 : token(header), image, name, combination, type
응답 : boolean