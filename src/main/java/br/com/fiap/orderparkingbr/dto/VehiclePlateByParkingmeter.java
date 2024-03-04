package br.com.fiap.orderparkingbr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePlateByParkingmeter {

    private String vehiclePlate;
    private LocalDateTime dateFinal;
    private Integer parkingTime;

}
