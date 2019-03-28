Google PUB/SUB en Weblogic 12c
============================

Demo : publicar mensajes en pub/sub desde una aplicacion web corriendo sobre weblogic 

https://cloud.google.com/pubsub/docs/quickstart-client-libraries#pubsub-client-libraries-java


## Setup

* Crear Topico 
`gcloud pubsub topics create my-topic`

* Crear suscriptor del topico
`gcloud pubsub subscriptions create my-sub --topic my-topic`

* Crear Cuenta de Servicio con los roles apropiados
`pub/sub publisher`

* Instalar App en weblogic y probar en la siguiente dirección  http://localhost:7001/pub-sub-weblogic/Test

* Obtener mensajes publicados en cloud shell
`gcloud pubsub subscriptions pull --auto-ack my-sub`


## Consideraciones 

* Setear la variable de ambiente ´GOOGLE_APPLICATION_CREDENTIALS´y en su valor, colocar la ubicacion de la cuenta de servicio para autenticar contra la api de pub/sub
* La cuenta de servicio debe tener los roles para poder publicar un mensaje en pub/sub 
* Setear PROJECT_ID y nombre del topico (Versiones futuras las dejo en un prop).


## Issues 

* Error SSL en autenticación contra google 

`<27-03-2019 23:07:10,708 Hora de verano de Chile> <Warning> <Security> <BEA-090504> <Certificate chain received from oauth2.googleapis.com - x.x.192.95 failed hostname verification check. Certificate contained *.googleapis.com but check expected oauth2.googleapis.com> 
`

`Caused by: com.google.api.gax.rpc.DeadlineExceededException: io.grpc.StatusRuntimeException: DEADLINE_EXCEEDED: deadline exceeded after 9962175952ns
	at com.google.api.gax.rpc.ApiExceptionFactory.createException(ApiExceptionFactory.java:51)
	at com.google.api.gax.grpc.GrpcApiExceptionFactory.create(GrpcApiExceptionFactory.java:72)
`


https://serverfault.com/questions/503751/certificate-verification-error-when-sending-a-service-request-from-weblogic

** reiniciar el server luego de hacer los cambios

