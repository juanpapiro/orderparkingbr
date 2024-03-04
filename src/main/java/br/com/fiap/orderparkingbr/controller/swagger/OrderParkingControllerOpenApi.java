package br.com.fiap.orderparkingbr.controller.swagger;

import br.com.fiap.orderparkingbr.dto.*;
import br.com.fiap.orderparkingbr.model.OrderParking;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

//@Tag(name = "Reservas de estacionamento")
public interface OrderParkingControllerOpenApi {

    @Operation(summary = "Consulta de reservas de estacionamento", description = "Liste as reservas de estacionamento")
    @ApiResponse(
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = PageOrderParking.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<PageOrderParking> findCustomers(Pageable pageable);



    @Operation(summary = "Cria reserva de estacionamento",
            description = "Reserve estacionamento")
    @ApiResponse(
            responseCode = "201",
            content = {@Content(schema = @Schema(implementation = OrderParking.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
    @ApiResponse(responseCode = "400",
            content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<OrderParking> createOrderParking(OrderParkingRequest request);



    @Operation(summary = "Inclui mais tempo em uma reserva de estacionamento",
            description = "Inclua tempo a uma reserva de estacionamento")
    @ApiResponse(
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = OrderParking.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<OrderParking> updateAddTime(String id, OrderParkingUpdateRequest request);



    @Operation(summary = "Busca de reservas de estacionamento por data de expiração e código do parquímetro",
            description = "Busque reservas de estacionamento por data de expiração e código do parquímetro")
    @ApiResponse(
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = List.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<List<VehiclePlateByParkingmeter>> findOrdersByDateFinalAndParkingmeterId(
            String id, LocalDateTime dateFinal);



    @Operation(summary = "Busca de reservas de estacionamento por data de expiração e placa do veículo",
            description = "Busque reservas de estacionamento por data de expiração e placa do veículo")
    @ApiResponse(
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = List.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<List<VehiclePlateByParkingmeter>> findOrdersByCarPlate(
            String vehiclePlate,LocalDateTime dateFinal);


    @Operation(summary = "Busca de reservas de estacionamento por data de expiração e rua",
            description = "Busque reservas de estacionamento por data de expiração e rua")
    @ApiResponse(
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = List.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<List<VehiclePlateByParkingmeter>> findOrdersByStreet(
            String street, LocalDateTime dateFinal);


}
