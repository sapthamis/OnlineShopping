insert into roles (id,name)values(1,'ROLE_USER'),(2,'ROLE_ADMIN');
insert into users(id,email,name,password,username)values(1,'sap@gmail.com','sapthami','$2a$10$wI7sxtEYYh8zir2GNscbtuG3i2N4Zv8PUw6n4moUgw0Y3MwZOvxkm','sap'),(2,'prince@gmail.com','prince','$2a$10$8zEfNyHIwFsbpagIu3sXK.YSNrRIBrrm/qo7wmwa6d39lTnYMzh8u','pri');
insert into user_roles(user_id,role_id)values(1,1),(2,2);