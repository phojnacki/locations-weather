# Capsilon demo

## Uruchomienie: 

```
runDocker.cmd
```
```
runApp1.cmd
```
```
runApp2.cmd
```


# test:

# POST http://127.0.0.1:9080/kafka/publish?cityName=Cracow&countryCode=PL
# POST http://127.0.0.1:9080/kafka/publish?cityName=Lisbona&countryCode=PT
POST http://127.0.0.1:9080/kafka/publish?cityName=Dover&countryCode=EN

-> zwraca requestId


GET http://127.0.0.1:9090/location/list
GET http://127.0.0.1:9090/location/{requestId}
//GET http://127.0.0.1:9090/location/880ec254-4d15-4900-8769-8d0dfc83935d
