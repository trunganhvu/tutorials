# API /products
```shell
curl --location 'localhost:8080/products' --header 'Accept-Language: vi' --header 'lang: vn'
```

Output
```json
{
    "recordCount": 200,
    "response": [
        {
            "id": 1,
            "name": "Product1",
            "quantity": 52,
            "price": 24231
        },
//        ....
        {
            "id": 200,
            "name": "Product200",
            "quantity": 82,
            "price": 24725
        }
    ]
}
```

# API sort name field /products/name
```shell
curl --location 'localhost:8080/products/name' --header 'Accept-Language: vi' --header 'lang: vn'
```

Output
```json
{
  "recordCount": 200,
  "response": [
    {
      "id": 1,
      "name": "Product1",
      "quantity": 52,
      "price": 24231
    },
    {
      "id": 10,
      "name": "Product10",
      "quantity": 28,
      "price": 37884
    },
    {
      "id": 100,
      "name": "Product100",
      "quantity": 22,
      "price": 49736
    },
    {
      "id": 101,
      "name": "Product101",
      "quantity": 95,
      "price": 43521
    },
//    ...
    {
      "id": 98,
      "name": "Product98",
      "quantity": 54,
      "price": 35823
    },
    {
      "id": 99,
      "name": "Product99",
      "quantity": 95,
      "price": 35707
    }
  ]
}
```

# API paging products/pagination/offset/pageSize
```shell
curl --location 'localhost:8080/products/pagination/0/5' --header 'Accept-Language: vi' --header 'lang: vn'
```

Output
```json
{
  "recordCount": 5,
  "response": {
    "content": [
      {
        "id": 1,
        "name": "Product1",
        "quantity": 52,
        "price": 24231
      },
      {
        "id": 2,
        "name": "Product2",
        "quantity": 88,
        "price": 40322
      },
      {
        "id": 3,
        "name": "Product3",
        "quantity": 5,
        "price": 15685
      },
      {
        "id": 4,
        "name": "Product4",
        "quantity": 15,
        "price": 12907
      },
      {
        "id": 5,
        "name": "Product5",
        "quantity": 63,
        "price": 2368
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 5,
      "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
      },
      "offset": 0,
      "paged": true,
      "unpaged": false
    },
    "last": false,
    "totalElements": 200,
    "totalPages": 40,
    "size": 5,
    "number": 0,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "first": true,
    "numberOfElements": 5,
    "empty": false
  }
}
```

# API paging and sorting field products/paginationAndSort/offset/pageSize/field
```shell
curl --location 'localhost:8080/products/paginationAndSort/0/5/price' --header 'Accept-Language: vi' --header 'lang: vn'
```

Output
```json
{
  "recordCount": 5,
  "response": {
    "content": [
      {
        "id": 78,
        "name": "Product78",
        "quantity": 6,
        "price": 175
      },
      {
        "id": 170,
        "name": "Product170",
        "quantity": 35,
        "price": 232
      },
      {
        "id": 18,
        "name": "Product18",
        "quantity": 71,
        "price": 529
      },
      {
        "id": 16,
        "name": "Product16",
        "quantity": 47,
        "price": 595
      },
      {
        "id": 186,
        "name": "Product186",
        "quantity": 29,
        "price": 754
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 5,
      "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
      },
      "offset": 0,
      "paged": true,
      "unpaged": false
    },
    "last": false,
    "totalElements": 200,
    "totalPages": 40,
    "size": 5,
    "number": 0,
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "first": true,
    "numberOfElements": 5,
    "empty": false
  }
}
```