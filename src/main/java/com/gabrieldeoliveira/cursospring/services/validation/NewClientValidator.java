package com.gabrieldeoliveira.cursospring.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gabrieldeoliveira.cursospring.domain.Client;
import com.gabrieldeoliveira.cursospring.domain.enums.ClientType;
import com.gabrieldeoliveira.cursospring.dto.NewClientDTO;
import com.gabrieldeoliveira.cursospring.repositories.ClientRepository;
import com.gabrieldeoliveira.cursospring.resources.exceptions.FieldMessage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NewClientValidator implements ConstraintValidator<NewClient, NewClientDTO> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public boolean isValid(NewClientDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> errors = new ArrayList<>();

        ClientType clientType = ClientType.fromCode(objDto.getTypeCode());
        String cpfOrCnpj = objDto.getCpfOrCnpj();
        if (clientType.equals(ClientType.FISIC_PERSON)) {
            if (cpfIsNotValid(cpfOrCnpj)) {
                errors.add(new FieldMessage("cpfOrCnpj", "invalid cpf"));
            }
        } else if (clientType.equals(ClientType.JURIDIC_PERSON)) {
            if (cnpjIsNotValid(cpfOrCnpj)) {
                errors.add(new FieldMessage("cpfOrCnpj", "invalid cnpj"));
            }
        }

        Client aux = clientRepository.findByEmail(objDto.getEmail());
        if (aux != null) {
            errors.add(new FieldMessage("email", "email already registered"));
        } 

        for (FieldMessage x : errors) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(x.getMessage())
                    .addPropertyNode(x.getField())
                    .addConstraintViolation();
        }
        return errors.isEmpty();
    }

    private boolean cnpjIsNotValid(String cnpj) {
        return !Validation.CNPJ.isValid(cnpj);
    }

    private boolean cpfIsNotValid(String cpf) {
        return !Validation.CPF.isValid(cpf);
    }
}
