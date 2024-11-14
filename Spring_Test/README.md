DOCKER desktop 
1.創建mySQL Containers
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root -d -p 3307:3306 mysql:8
2.
docker exec -it mysql-container bash
3.
mysql -u root -p
Password:root
4.
//創建 資料庫
CREATE DATABASE test11_12;

5.起專案

SHOW TABLE;
/* 起專案會自動創建*/
SHOW CREATE TABLE product; //不需輸入指令
SHOW CREATE TABLE product_price; //不需輸入指令

------------------------------------
SET NAMES 'utf8mb4';//改顯示中文

1.查看product表
SELECT * FROM product;
2.查看product_price;表
SELECT * FROM product_price;
3.查看2表(Boolean  以0/1表示)
select *from product p join product_price p2 on p.id = p2.productid ;

SELECT 
    p.id,
    CASE p.data_grouping
        WHEN 0x01 THEN TRUE
        ELSE FALSE
    END AS data_grouping,  
    p.name,
    p.short_name,
    p2.price  
FROM product p
JOIN product_price p2 ON p.id = p2.productid;

---------------------------------------------------------------------------------------------

查看
SELECT * FROM product WHERE id = 105;
SELECT * FROM product_price WHERE id = 243;

//新增
INSERT INTO `product` (`id`, `data_grouping`, `name`, `short_name`)VALUES (105, 1, 'TEST1', 'T1');
INSERT INTO product_price(`id`, `date`, `price`, `productid`) VALUES (243, '2023-03-09', 99.5, 105);

//修改
UPDATE `product`SET `data_grouping` = 0,`name` = 'TEST2', `short_name` = 'T2'WHERE `id` = 105;
UPDATE product_price SET `date` = '2023-03-09', `price` = 88.5, `productid` = 105 WHERE `id` = 243;

//刪除
DELETE FROM `product` WHERE `id` = 105;
DELETE FROM product_price WHERE `id` = 243;

---------------------------------------------------------------------------------------------












