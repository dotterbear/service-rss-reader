# DEV

##### Start DynamoDB in local

```
java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb -port 33000
```

##### Start DynamoDB GUI in local

https://github.com/aaronshaf/dynamodb-admin

```
export DYNAMO_ENDPOINT=http://localhost:33000
dynamodb-admin
```

For windows
```
SET DYNAMO_ENDPOINT=http://localhost:33000
dynamodb-admin
```