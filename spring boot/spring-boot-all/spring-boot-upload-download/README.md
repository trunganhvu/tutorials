# Setups
## Path save files
```properties
file.upload-dir=./uploads
```

# Danh sách curl
Call api qua postman với body là form-data (file/files: attach files) 
## Upload 1 file
```shell
curl --location 'http://localhost:8080/uploadFile' \
--form 'file=@"/C:/Users/Admin/Pictures/Screenshot 2024-09-03 211714.png"'
```

Output
```shell
{
    "fileName": "Screenshot 2024-09-03 211714.png",
    "fileDownloadUri": "http://localhost:8080/downloadFile/Screenshot%202024-09-03%20211714.png",
    "fileType": "image/png",
    "size": 590685
}
```

## Upload nhiều file
```shell
curl --location 'http://localhost:8080/uploadMultipleFiles' \
--form 'files=@"/C:/Users/Admin/Pictures/Screenshot 2024-09-03 211714.png"' \
--form 'files=@"/C:/Users/Admin/Pictures/128849-1694765872469.png"'
```

Output
```shell
[
    {
        "fileName": "Screenshot 2024-09-03 211714.png",
        "fileDownloadUri": "http://localhost:8080/downloadFile/Screenshot%202024-09-03%20211714.png",
        "fileType": "image/png",
        "size": 590685
    }
]
```
## Download file
```shell
curl --location 'http://localhost:8080/downloadFile/128849-1694765872469.png'
```
