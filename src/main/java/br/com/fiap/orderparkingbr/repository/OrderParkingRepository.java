package br.com.fiap.orderparkingbr.repository;

import br.com.fiap.orderparkingbr.model.OrderParking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderParkingRepository extends MongoRepository<OrderParking, String> {
}
