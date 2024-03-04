docker exec -i routerdb mongosh --host localhost --port 12001 <<EOF
sh.startBalancer();
sh.addShard("cfgsrd1/srd1:11001");
sh.addShard("cfgsrd2/srd2:11002");
sh.enableSharding("parking");
sh.enableSharding("parking");
use parking;
db.payment.insertOne({"_id": ObjectId("65e344195b90a94dfb61d23a"),"paymentType": "PIX","amount": 60.0,"time": 120,"version": 0});
db.orderParking.insertOne({"_id": ObjectId("65e344195b90a94dfb61d22a"),"vehiclePlate": "GAL2A69","parkingTime": 120,"dateStart": ISODate('2024-03-02T15:22:01.629Z'),"dateFinal": ISODate('2024-03-02T17:22:01.629Z'),  "payment": [{"_id": ObjectId("65e344195b90a94dfb61d23a"),"paymentType": "PIX","amount": "60.0","time": 120,"version": 0}],  "parkingmeter": {"code": "f679af614f1810e505df08eac609c16c","street": "RUA LEBLON","number": 3,"neighborhood": "JARDIM SÃO VICENTE","zipcode": "06826-270","city": "EMBU","uf": "SP"},"version": 0});
db.orderParking.createIndex({"vehiclePlate":"text"});
db.orderParking.createIndex({"parkingmeter.street":"text"});
EOF