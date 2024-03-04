package br.com.fiap.orderparkingbr.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderParkingUpdateRequest {

    @Schema(name = "parkingTime", type = "Integer", description = "Tempo de estacionamento em minutos", requiredMode = Schema.RequiredMode.AUTO ,example = "30")
    @NotNull
    private Integer parkingTime;

    @Schema(name = "payment", type = "String", description = "Tipo de pagamento", requiredMode = Schema.RequiredMode.AUTO ,example = "CREDIT, DEBIT ou PIX")
    @NotBlank
    private String payment;


}
