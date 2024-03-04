package br.com.fiap.orderparkingbr.service.impl;

import br.com.fiap.orderparkingbr.dto.*;
import br.com.fiap.orderparkingbr.enums.PaymentType;
import br.com.fiap.orderparkingbr.model.OrderParking;
import br.com.fiap.orderparkingbr.model.Payment;
import br.com.fiap.orderparkingbr.repository.OrderParkingRepository;
import br.com.fiap.orderparkingbr.service.OrderParkingService;
import br.com.fiap.orderparkingbr.service.ParkingmeterService;
import br.com.fiap.orderparkingbr.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderParkingServiceImpl implements OrderParkingService {

    private MongoTemplate mongoTemplate;

    public OrderParkingServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    private OrderParkingRepository orderParkingRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ParkingmeterService parkingmeterService;


    /**
     * Cria reserva de estacionamento em parquimetro
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<OrderParking> createOrder(OrderParkingRequest request) {
        ParkingmeterDto parkingmeterDto = parkingmeterService
                .findParkingmeter(request.getParkingmeterCode());
        Payment payment = paymentService.createPayment(request.getParkingTime(),
                PaymentType.findPaymentType(request.getPayment()));
        OrderParking orderParking = new OrderParking(request, payment, parkingmeterDto);
        orderParking = orderParkingRepository.save(orderParking);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderParking);
    }

    /**
     * Adiciona tempo a uma reserva de estacionamento
     * @param id
     * @param request
     * @return
     */
    @Transactional
    @Override
    public ResponseEntity<OrderParking> updateAddTime(String id, OrderParkingUpdateRequest request) {
        return orderParkingRepository.findById(id)
                .map(orderLocate -> {
                    Payment payment = paymentService.createPayment(request.getParkingTime(),
                            PaymentType.findPaymentType(request.getPayment()));
                    orderLocate.setParkingTime(orderLocate.getParkingTime() + request.getParkingTime());
                    orderLocate.setDateFinal(orderLocate.getDateStart().plusMinutes(orderLocate.getParkingTime()));
                    orderLocate.getPayment().add(payment);
                    return ResponseEntity.ok(orderParkingRepository.save(orderLocate));
                }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Busca todas as reservas de estacionamento
     * @return
     */
    @Override
    public ResponseEntity<PageOrderParking> findOrders(Pageable pageable) {
        Page<OrderParking> pageOrders = orderParkingRepository.findAll(pageable);
        return Optional.ofNullable(pageOrders).map(pg -> {
            PageOrderParking ordersResponse = new PageOrderParking(pg.getContent(),
                    pg.getTotalElements(), pg.getTotalPages(), pg.getSize(), pg.getNumber());
            return ResponseEntity.ok(ordersResponse);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Busca as reservas de um parquimetro por seu codigo e data de expiracao da reserva
     * acima da data atual retornando veiculos com estacionamento permitido
     * @param dateFinal
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<List<VehiclePlateByParkingmeter>> findOrdersByDateFinalAndParkingmeterId(
            LocalDateTime dateFinal, String id) {
        Criteria criteria = Criteria.where("dateFinal")
                .gte(dateFinal)
                .and("parkingmeter.code").is(id);
        Query query = new Query(criteria);

        TypedAggregation<OrderParking> aggregation = Aggregation.newAggregation(
                OrderParking.class,
                Aggregation.match(criteria),
                Aggregation.sort(Sort.by("dateFinal").ascending()),
                Aggregation.project("vehiclePlate")
                        .and("parkingTime").as("parkingTime")
                        .and("parkingmeter.code").as("parkingmeter.code")
//                        .and("parkingmeter._id").as("parkingmeter._id")
                        .and("dateFinal").as("dateFinal"));

        List<VehiclePlateByParkingmeter> result = mongoTemplate.aggregate(
                aggregation, VehiclePlateByParkingmeter.class).getMappedResults();
        return ResponseEntity.ok(result);
    }

    /**
     * Busca as reservas de estacionamento de um veiculo por sua placa
     * @param dateFinal
     * @param vehiclePlate
     * @return
     */
    @Override
    public ResponseEntity<List<VehiclePlateByParkingmeter>> findOrdersByVehiclePlate(
            LocalDateTime dateFinal, String vehiclePlate) {
        Criteria criteria = Criteria
                .where("vehiclePlate").is(vehiclePlate)
                .and("dateFinal").gte(dateFinal);
        TypedAggregation<OrderParking> aggregate = Aggregation.newAggregation(
                OrderParking.class,
                Aggregation.match(criteria),
                Aggregation.sort(Sort.by("dateFinal").descending()));
        List<VehiclePlateByParkingmeter> result = mongoTemplate.aggregate(
                aggregate, VehiclePlateByParkingmeter.class).getMappedResults();
        return result.isEmpty() ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<VehiclePlateByParkingmeter>> findOrdersByStreet(
            LocalDateTime dateFinal, String street) {
        Criteria criteria = Criteria
                .where("parkingmeter.street").regex(street, "i")
                .and("dateFinal").gte(dateFinal);
        TypedAggregation<OrderParking> aggregate = Aggregation.newAggregation(
                OrderParking.class,
                Aggregation.match(criteria),
                Aggregation.sort(Sort.by("dateFinal").descending()));
        List<VehiclePlateByParkingmeter> result = mongoTemplate.aggregate(
                aggregate, VehiclePlateByParkingmeter.class).getMappedResults();
        return Optional.ofNullable(result).filter(list -> !list.isEmpty())
                .map(list -> ResponseEntity.ok(list)).orElse(ResponseEntity.notFound().build());
    }


}
