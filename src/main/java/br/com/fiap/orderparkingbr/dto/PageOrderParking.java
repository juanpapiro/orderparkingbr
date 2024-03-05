package br.com.fiap.orderparkingbr.dto;

import br.com.fiap.orderparkingbr.model.OrderParking;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageOrderParking {

    private List<OrderParking> content;
    private long totalElements;
    private int totalPages;
    private int size;
    private int number;

}
