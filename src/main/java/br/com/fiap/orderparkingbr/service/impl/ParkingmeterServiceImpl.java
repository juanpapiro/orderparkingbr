package br.com.fiap.orderparkingbr.service.impl;

import br.com.fiap.orderparkingbr.dto.ParkingmeterDto;
import br.com.fiap.orderparkingbr.service.ParkingmeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class ParkingmeterServiceImpl implements ParkingmeterService {

    @Autowired
    @Qualifier("withssl")
    private RestTemplate restTemplate;

    @Value("${URL_PARKINGMETER}")
    private String urlParkingmeter;

    @Override
    public ParkingmeterDto findParkingmeter(String parkingmeterCode) {
        UriComponentsBuilder uri = UriComponentsBuilder
                .fromHttpUrl(urlParkingmeter)
                .pathSegment(parkingmeterCode);
        try {
            ResponseEntity<ParkingmeterDto> response = restTemplate.exchange(uri.toUriString(),
                    HttpMethod.GET, null, ParkingmeterDto.class);
            return Optional.ofNullable(response)
                    .map(ResponseEntity::getBody)
                    .orElseThrow(() -> new IllegalArgumentException("Parquímetro não encontrado"));
        } catch(HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Parquímetro não encontrado");
        }
    }
}
