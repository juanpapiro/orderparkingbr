package br.com.fiap.orderparkingbr.controller;

import br.com.fiap.orderparkingbr.controller.swagger.OrderParkingControllerOpenApi;
import br.com.fiap.orderparkingbr.dto.PageOrderParking;
import br.com.fiap.orderparkingbr.dto.VehiclePlateByParkingmeter;
import br.com.fiap.orderparkingbr.dto.OrderParkingRequest;
import br.com.fiap.orderparkingbr.dto.OrderParkingUpdateRequest;
import br.com.fiap.orderparkingbr.model.OrderParking;
import br.com.fiap.orderparkingbr.service.OrderParkingService;
import br.com.fiap.orderparkingbr.service.ParkingmeterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class OrderParkingController implements OrderParkingControllerOpenApi {

    @Autowired
    private OrderParkingService orderParkingService;

    @Autowired
    private ParkingmeterService parkingmeterService;


    @GetMapping("/order")
    public ResponseEntity<PageOrderParking> findCustomers(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return orderParkingService.findOrders(pageable);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderParking> createOrderParking(
            @Valid @RequestBody OrderParkingRequest request) {
        return orderParkingService.createOrder(request);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<OrderParking> updateAddTime(
            @PathVariable(name = "id") String id,
            @RequestBody OrderParkingUpdateRequest request) {
        return orderParkingService.updateAddTime(id, request);
    }

    @GetMapping("/order/{parkingmeterId}")
    public ResponseEntity<List<VehiclePlateByParkingmeter>> findOrdersByDateFinalAndParkingmeterId(
            @PathVariable(name = "parkingmeterId") String id,
            @RequestParam(name = "dateFinal") LocalDateTime dateFinal){
        return orderParkingService.findOrdersByDateFinalAndParkingmeterId(dateFinal, id);
    }

    @GetMapping("/order/active/{vehiclePlate}")
    public ResponseEntity<List<VehiclePlateByParkingmeter>> findOrdersByCarPlate(
            @PathVariable(name = "vehiclePlate") String vehiclePlate,
            @RequestParam(name = "dateFinal") LocalDateTime dateFinal) {
        return orderParkingService.findOrdersByVehiclePlate(dateFinal, vehiclePlate);
    }

    @GetMapping("/order/active/parkingmeter/{street}")
    public ResponseEntity<List<VehiclePlateByParkingmeter>> findOrdersByStreet(
            @PathVariable(name = "street") String street,
            @RequestParam(name = "dateFinal") LocalDateTime dateFinal) {
        return orderParkingService.findOrdersByStreet(dateFinal, street);
    }

}
