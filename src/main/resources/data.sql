
INSERT INTO resource as res (id,name,district) values (1,'гусь','санкт-петербург');
INSERT INTO resource as res (id,name,district) values (2,'кабан','санкт-петербург');
INSERT INTO resource as res (id,name,district) values (3,'кролик','санкт-петербург');

INSERT INTO resource as res (id,name,district) values (4,'лось','москва');
INSERT INTO resource as res (id,name,district) values (5,'кабан','москва');
INSERT INTO resource as res (id,name,district) values (6,'кролик','москва');

INSERT INTO base_resource AS b (id,resource_id,quota,start_date,end_date) values (1,1,11,'2024-01-01','2024-02-01');
INSERT INTO base_resource AS b (id,resource_id,quota,start_date,end_date) values (2,2,22,'2024-02-01','2024-03-01');
INSERT INTO base_resource AS b (id,resource_id,quota,start_date,end_date) values (3,3,33,'2023-03-01','2024-04-01');
INSERT INTO base_resource AS b (id,resource_id,quota,start_date,end_date) values (4,4,44,'2023-04-01','2024-03-01');
INSERT INTO base_resource AS b (id,resource_id,quota,start_date,end_date) values (5,5,55,'2023-05-01','2024-03-01');
INSERT INTO base_resource AS b (id,resource_id,quota,start_date,end_date) values (6,6,66,'2023-06-01','2024-03-01');


