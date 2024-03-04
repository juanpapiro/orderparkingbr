package br.com.fiap.orderparkingbr.service;

import br.com.fiap.orderparkingbr.dto.ParkingmeterDto;

public interface ParkingmeterService {

    ParkingmeterDto findParkingmeter(String parkingmeterCode);

}
