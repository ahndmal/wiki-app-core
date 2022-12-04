# wiki-app-core

Primitive example of Confluence-like wiki system based on:

- Spring Boot
- Reactive Streams
- RSocket / WebSockets

Features:
- content
- reacting to events
- REST
- gRPC
- messaging
- emails
- groovy/python console

![Screenshot from 2022-05-08 17-56-10](https://user-images.githubusercontent.com/36703491/167308313-ae570b2d-64d6-4e9d-90f3-d49cad50e0e5.png)

![wiki1](https://user-images.githubusercontent.com/36703491/167308633-05977042-254f-40eb-8c71-1b6a9097c300.png)

### gRPC
Add Reflection service:
```
+import io.grpc.protobuf.services.ProtoReflectionService;
 import io.grpc.Server;
 import io.grpc.ServerBuilder;
+import io.grpc.protobuf.services.ProtoReflectionService;
 import io.grpc.stub.StreamObserver;
 import java.io.IOException;
 import java.util.logging.Logger;
@@ -50,6 +51,7 @@ public class HelloWorldServer {
     int port = 50051;
     server = ServerBuilder.forPort(port)
         .addService(new GreeterImpl())
+        .addService(ProtoReflectionService.newInstance())
         .build()
         .start();
     logger.info("Server started, listening on " + port);
```

Requests:
```bash
grpcurl -plaintext localhost:6565 list
grpcurl -d '{"id": 242}' -plaintext localhost:6565 com.andmal.PageGService.getPage
```

### DB
```sql
create table pages(id serial PRIMARY KEY, title varchar, body varchar, space_key varchar);

create table blogs(id serial primary key, title varchar, body varchar, space_key varchar);

create table attachments(id serial, title varchar, file_type varchar, page_id int, primary key (id));

create table spaces(id serial primary key, title varchar, category varchar, space_key varchar);

create table users(id serial primary key, name varchar, full_name varchar, user_role varchar);

create table comments(id serial primary key, body varchar, title varchar, parent_id int, created_at timestamp, last_edited timestamp, user_id int);

-- select 
select * from pages p;
select * from spaces s ;
select * from "comments" c ;

--delete from "comments" ;

-- INSERT
insert into pages(id, title, body, space_key) values(1, 'page 1', 'asd sd d asd as ', 'DEV');
insert into pages(id, title, body, space_key) values(2, 'page 2', 'asasd sda d sd d asd as ', 'DEV');

insert into spaces (id, title, "space_key", category) values (1, 'DEV', 'DEV', 'global');
insert into spaces (id, title, "space_key", category) values (2, 'DOCS', 'DOCS', 'global');
insert into spaces (id, title, "space_key", category) values (3, 'TEST', 'TEST', 'tesms');


insert into "comments" (id, "body", parent_id, created_at, last_edited, user_id) 
values (1, 'sdasdadsad', 1, now(), now(), 1);
```
