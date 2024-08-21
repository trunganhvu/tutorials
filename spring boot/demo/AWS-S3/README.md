# Thư viện
> aws-java-sdk-s3
> https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-s3

# Thiết định IAM key trong properties
```shell
accessKey={IAM_ACCESS_KEY}
secretKey={IAM_SECRET_KEY}
region={REGION}
cloud.aws.region.static={REGION}
```
# List api
### S3 bucket
* **GET DETAIL**: GET s3bucket/buckets/{bucketName}
* **CREATED**: POST s3bucket/buckets/{bucketName}
* **DELETE**: DELETE s3bucket/buckets/{bucketName}

### S3 object
* **CREATED**: POST s3bucket/buckets/{bucketName}/objects
* **GET DETAIL**: GET s3bucket/buckets/{bucketName}/objects/{filename}
* **DOWNLOAD**: GET s3bucket/buckets/{bucketName}/objects/{filename}/download
* **DELETE**: DELETE s3bucket/buckets/{bucketName}/objects/{filename}
