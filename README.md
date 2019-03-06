1) products folder contains product Spring boot microservice source code.

	Endpoints of Product App :
	
	1) Get All Product   -> GET http://localhost:8089/Product/products
	2) Get Product Brand Wise -> GET http://localhost:8089/Product/brands
	
	Swagger URL : http://localhost:8089/Product/swagger-ui.html
	
2) Transformer folder contains transform Json microservice source code.

	Endpoints of Transformer App :

	1) PUT  http://localhost:8088/Transformer/alpha
		
		Request Body : 
						{
							"fruit":"apple",
							"animal":"zebra",
							"city-list":["sunnyvale","sanjose"]
						}
	2) POST http://localhost:8088/Transformer/flatten
	
		Request Body :
		
					{
						"fruit":"apple",
						"animal":"zebra",
						"city-list":["sunnyvale","sanjose"]
					}
	3) GET  http://localhost:8088/Transformer/status
	
	Swagger URL : http://localhost:8088/Transformer/swagger-ui.html
	
3) ProductAPI.postman_collection.json contains A postman collection to test all implemented API Endpoints of Product MicroService

4) TransformerAPI.postman_collection.json contains A postman collection to test all implemented API Endpoints of Transformer MicroService	