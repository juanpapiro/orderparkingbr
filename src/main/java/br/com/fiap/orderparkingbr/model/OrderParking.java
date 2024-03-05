package br.com.fiap.orderparkingbr.model;

import br.com.fiap.orderparkingbr.dto.OrderParkingRequest;
import br.com.fiap.orderparkingbr.dto.ParkingmeterDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderParking {

    @Id
    private String code;
    @TextIndexed
    private String vehiclePlate;
    private Integer parkingTime;
    private LocalDateTime dateStart;
    private LocalDateTime dateFinal;
    private List<Payment> payment;
    private ParkingmeterDto parkingmeter;
    @Version
    private Long version;


    public OrderParking(OrderParkingRequest request,
                        Payment payment, ParkingmeterDto parkingmeterDto) {
        this.vehiclePlate = request.getVehiclePlate();
        this.parkingTime = request.getParkingTime();
        this.dateStart = LocalDateTime.now();
        this.dateFinal = dateStart.plusMinutes(request.getParkingTime());
        this.payment = Arrays.asList(payment);
        this.parkingmeter = parkingmeterDto;
    }


}
