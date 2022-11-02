create table order_line_item (id bigint not null, item_name varchar(255), item_quantity bigint not null, order_id bigint, primary key (id));
create table sales_order (id bigint not null, order_date timestamp, order_desc varchar(255), total_price double not null, primary key (id));
