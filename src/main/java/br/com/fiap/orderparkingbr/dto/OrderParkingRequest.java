package br.com.fiap.orderparkingbr.dto;

import br.com.fiap.orderparkingbr.validations.VehiclePlateValidation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderParkingRequest {

    @Schema(name = "vehiclePlate", type = "String", description = "Placa do veículo", requiredMode = Schema.RequiredMode.AUTO ,example = "FOR-12A3")
    @VehiclePlateValidation
    @NotBlank
    private String vehiclePlate;

    @Schema(name = "parkingTime", type = "Integer", description = "Tempo de estacionamento em minutos", requiredMode = Schema.RequiredMode.AUTO ,example = "30")
    @NotNull
    private Integer parkingTime;

    @Schema(name = "payment", type = "String", description = "Tipo de pagamento", requiredMode = Schema.RequiredMode.AUTO ,example = "CREDIT, DEBIT ou PIX")
    @NotBlank
    private String payment;

    @Schema(name = "parkingmeterCode", type = "String", description = "Código do parquímetro", requiredMode = Schema.RequiredMode.AUTO ,example = "f679af614f1810e505df08eac609c16c")
    @NotBlank
    private String parkingmeterCode;

}
