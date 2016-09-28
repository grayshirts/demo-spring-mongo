#!/usr/bin/env bash

# List products with -> curl http://localhost:5000/orders/products/
curl -X POST http://localhost:5000/orders/products -d '{"sku":"IPH7","description":"iPhone 7","instock":10}' -H Content-Type:application/json
curl -X POST http://localhost:5000/orders/products -d '{"sku":"S7","description":"Samsung S7","instock":5}' -H Content-Type:application/json
curl -X POST http://localhost:5000/orders/products -d '{"sku":"CR456","description":"Cargador Univ 456","instock":0}' -H Content-Type:application/json
curl -X POST http://localhost:5000/orders/products -d '{"sku":"GREXT","description":"Garantía Extendida"}' -H Content-Type:application/json

# List orders with -> curl http://localhost:5000/orders/
curl -X POST http://localhost:5000/orders/ -d '{"item":"IPH7","price":18200.43,"quantity":1}' -H Content-Type:application/json
curl -X POST http://localhost:5000/orders/ -d '{"item":"IPH7","price":36000.00,"quantity":2}' -H Content-Type:application/json
curl -X POST http://localhost:5000/orders/ -d '{"item":"S7","price":13000,"quantity":1}' -H Content-Type:application/json
curl -X POST http://localhost:5000/orders/ -d '{"item":"S7","price":120000,"quantity":10}' -H Content-Type:application/json
curl -X POST http://localhost:5000/orders/ -d '{"item":"S7","price":120000,"quantity":10}' -H Content-Type:application/json
curl -X POST http://localhost:5000/orders/ -d '{"item":"GREXT","price":999,"quantity":1}' -H Content-Type:application/json


## Left Join with MongoDB 3.2 -> curl http://localhost:5000/orders/report

#db.orders.aggregate([
#  {
#    $lookup: {
#      from: "products",
#      localField: "item",
#      foreignField: "_id",
#      as: "products"
#    }
#  }
#  ,{
#    $out: "orders_report"
#  }
#]);
