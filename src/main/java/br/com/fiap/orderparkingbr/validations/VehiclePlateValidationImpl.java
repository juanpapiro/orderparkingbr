package br.com.fiap.orderparkingbr.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VehiclePlateValidationImpl implements ConstraintValidator<VehiclePlateValidation, String> {

    private static final String regex = "\\D{3}\\d{1}[0-9a-zA-Z]\\d{2}";

    /**
     * Valida os formatos de placa na regra antiga LLLNNNN e no novo formato mercosul LLLNLNN
     * https://www.gov.br/transportes/pt-br/assuntos/transito/conteudo-contran/resolucoes/resolucao9692022anexos.pdf
     * @param value object to validate
     * @param context context in which the constraint is evaluated
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(regex);
    }
}
