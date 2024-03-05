docker exec -i routerdb mongosh --host localhost --port 27017 <<EOF
sh.startBalancer();
sh.addShard("cfgsrd1/srd1:27017");
sh.addShard("cfgsrd2/srd2:27017");
sh.enableSharding("parking");
sh.enableSharding("parking");
use parking;
db.payment.insertOne({"_id": ObjectId("65e344195b90a94dfb61d23a"),"paymentType": "PIX","amount": 60.0,"time": 120,"version": 0});
db.orderParking.insertOne({"_id": ObjectId("65e344195b90a94dfb61d22a"),"vehiclePlate": "GAL2A69","parkingTime": 120,"dateStart": ISODate('2024-03-02T15:22:01.629Z'),"dateFinal": ISODate('2024-03-02T17:22:01.629Z'),  "payment": [{"_id": ObjectId("65e344195b90a94dfb61d23a"),"paymentType": "PIX","amount": "60.0","time": 120,"version": 0}],  "parkingmeter": {"code": "ed8adb42606647a3df44d65e552dfbc6","street": "RUA CARDEAL ARCOVERDE","number": 700,"neighborhood": "PINHEIROS","zipcode": "05407-001","city": "SÃO PAULO","uf": "SP"},"version": 0});
db.orderParking.createIndex({"vehiclePlate":"text", "parkingmeter.street":"text"});
EOF