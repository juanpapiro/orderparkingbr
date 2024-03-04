package br.com.fiap.orderparkingbr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
