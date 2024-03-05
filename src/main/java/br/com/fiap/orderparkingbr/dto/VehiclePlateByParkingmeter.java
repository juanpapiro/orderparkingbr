package br.com.fiap.orderparkingbr.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehiclePlateByParkingmeter {

    private String vehiclePlate;
    private LocalDateTime dateFinal;
    private Integer parkingTime;

}
