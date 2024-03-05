package br.com.fiap.orderparkingbr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorApi {

	@Schema(name = "message", type = "String", description = "Mensagem de erro")
	private String message;
	
	@Schema(name = "errorsValidation", type = "Lista", format="List<ErrorValidation>", 
			description = "Lista com parâmetros e respectivos erros de request")
	private List<ErrorValidation> errorsValidation;
	
	
	public ErrorApi(String message) {
		this.message = message;
	}
	
	public void setError(ErrorValidation error) {
		if (errorsValidation == null) {
			this.errorsValidation = new ArrayList<>(); 
		}
		errorsValidation.add(error);
	}
	
}
