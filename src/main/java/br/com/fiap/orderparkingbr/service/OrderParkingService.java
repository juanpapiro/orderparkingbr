package br.com.fiap.orderparkingbr.service;

import br.com.fiap.orderparkingbr.dto.PageOrderParking;
import br.com.fiap.orderparkingbr.dto.VehiclePlateByParkingmeter;
import br.com.fiap.orderparkingbr.dto.OrderParkingRequest;
import br.com.fiap.orderparkingbr.dto.OrderParkingUpdateRequest;
import br.com.fiap.orderparkingbr.model.OrderParking;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderParkingService {

    ResponseEntity<OrderParking> createOrder(OrderParkingRequest request);
    ResponseEntity<OrderParking> updateAddTime(String id, OrderParkingUpdateRequest request);
    ResponseEntity<PageOrderParking> findOrders(Pageable pageable);
    ResponseEntity<List<VehiclePlateByParkingmeter>> findOrdersByDateFinalAndParkingmeterId(LocalDateTime dateFinal, String id);
    ResponseEntity<List<VehiclePlateByParkingmeter>> findOrdersByVehiclePlate(LocalDateTime dateFinal, String vehiclePlate);

    ResponseEntity<List<VehiclePlateByParkingmeter>> findOrdersByStreet(LocalDateTime dateFinal, String street);

}
