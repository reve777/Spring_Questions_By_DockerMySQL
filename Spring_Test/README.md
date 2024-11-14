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

---------------------------------------------------------------------------------------------  
SET NAMES 'utf8mb4';//改顯示中文  
SQL  
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
API POSTMAN
[11-12.postman_collection.json](https://github.com/user-attachments/files/17746530/11-12.postman_collection.json)
{
	"info": {
		"_postman_id": "4e15991b-bcb7-41f0-95f4-d03c5396b301",
		"name": "11/12",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "24782644"
	},
	"item": [
		{
			"name": "題目API",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\"req\":{\"Keys\":[\"10480016\"],\"From\":\"2023/03/10\",\"To\":\"2024/03/10\"}\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "https://www.cathaybk.com.tw/cathaybk/service/newwealth/fund/chartservice.asmx/GetFundNavChart",
					"protocol": "https",
					"host": [
						"www",
						"cathaybk",
						"com",
						"tw"
					],
					"path": [
						"cathaybk",
						"service",
						"newwealth",
						"fund",
						"chartservice.asmx",
						"GetFundNavChart"
					]
				}
			},
			"response": []
		},
		{
			"name": "Data_getById",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "localhost:8081/portfolio/api/forex/getProductData/10480016",
					"host": [
						"localhost"
					],
					"port": "8081",
					"path": [
						"portfolio",
						"api",
						"forex",
						"getProductData",
						"10480016"
					]
				}
			},
			"response": []
		},
		{
			"name": "Data_add",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"id\":100101,\r\n    \"name\":\"TEST\",\r\n    \"shortName\":\"TT\",\r\n    \"dataGrouping\": true ,\r\n    \"datas\":[\r\n        {\r\n            \"date\":\"2025-03-08\",\r\n            \"price\":99.99\r\n\r\n        },\r\n        {\r\n            \"date\":\"2025-04-08\",\r\n            \"price\":99.99\r\n\r\n        }\r\n    ]\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8081/portfolio/api/forex/addUpdate",
					"host": [
						"localhost"
					],
					"port": "8081",
					"path": [
						"portfolio",
						"api",
						"forex",
						"addUpdate"
					]
				}
			},
			"response": []
		},
		{
			"name": "Data_getAll",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "localhost:8081/portfolio/api/forex/getAllProductData",
					"host": [
						"localhost"
					],
					"port": "8081",
					"path": [
						"portfolio",
						"api",
						"forex",
						"getAllProductData"
					]
				}
			},
			"response": []
		},
		{
			"name": "Data_DELETE",
			"request": {
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "localhost:8081/portfolio/api/forex/deleteProductData/100101",
					"host": [
						"localhost"
					],
					"port": "8081",
					"path": [
						"portfolio",
						"api",
						"forex",
						"deleteProductData",
						"100101"
					]
				}
			},
			"response": []
		},
		{
			"name": "1.查詢某日價格。",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "localhost:8081/portfolio/api/forex/getProductPriceOnDate?productId=10480016&date=2026-03-10",
					"host": [
						"localhost"
					],
					"port": "8081",
					"path": [
						"portfolio",
						"api",
						"forex",
						"getProductPriceOnDate"
					],
					"query": [
						{
							"key": "productId",
							"value": "10480016"
						},
						{
							"key": "date",
							"value": "2026-03-10"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "2.修改某日價格。",
			"request": {
				"method": "PUT",
				"header": [],
				"url": {
					"raw": "localhost:8081/portfolio/api/forex/update?productId=10480016&dateString=2026-03-10&newPrice=99.15",
					"host": [
						"localhost"
					],
					"port": "8081",
					"path": [
						"portfolio",
						"api",
						"forex",
						"update"
					],
					"query": [
						{
							"key": "productId",
							"value": "10480016"
						},
						{
							"key": "dateString",
							"value": "2026-03-10"
						},
						{
							"key": "newPrice",
							"value": "99.15"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "3.新增價格至 DB",
			"request": {
				"method": "POST",
				"header": [],
				"url": {
					"raw": "localhost:8081/portfolio/api/forex/add?productId=10480016&dateString=2026-03-10&price=99.99",
					"host": [
						"localhost"
					],
					"port": "8081",
					"path": [
						"portfolio",
						"api",
						"forex",
						"add"
					],
					"query": [
						{
							"key": "productId",
							"value": "10480016"
						},
						{
							"key": "dateString",
							"value": "2026-03-10"
						},
						{
							"key": "price",
							"value": "99.99"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "4.可刪除某日價格",
			"request": {
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "localhost:8081/portfolio/api/forex/delete?productId=10480016&dateString=2026-03-10",
					"host": [
						"localhost"
					],
					"port": "8081",
					"path": [
						"portfolio",
						"api",
						"forex",
						"delete"
					],
					"query": [
						{
							"key": "productId",
							"value": "10480016"
						},
						{
							"key": "dateString",
							"value": "2026-03-10"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "5.計算漲跌",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "localhost:8081/portfolio/api/forex/calculate?productId=10480016&startDate=2023-03-16&endDate=2023-03-17",
					"host": [
						"localhost"
					],
					"port": "8081",
					"path": [
						"portfolio",
						"api",
						"forex",
						"calculate"
					],
					"query": [
						{
							"key": "productId",
							"value": "10480016"
						},
						{
							"key": "startDate",
							"value": "2023-03-16"
						},
						{
							"key": "endDate",
							"value": "2023-03-17"
						}
					]
				}
			},
			"response": []
		}
	]
}











