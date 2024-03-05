package br.com.fiap.orderparkingbr.dto;

import lombok.*;
import org.springframework.data.mongodb.core.index.TextIndexed;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParkingmeterDto {

    private String code;
    @TextIndexed
    private String street;
    private Integer number;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String uf;

}
