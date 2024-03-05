package br.com.fiap.orderparkingbr.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Payment {

    @Id
    private String code;
    private String paymentType;
    private BigDecimal amount;
    private Integer time;
    @Version
    private Long version;

}
