package com.gabrieldeoliveira.cursospring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.gabrieldeoliveira.cursospring.domain.Client;
import com.gabrieldeoliveira.cursospring.dto.ClientDTO;
import com.gabrieldeoliveira.cursospring.repositories.ClientRepository;
import com.gabrieldeoliveira.cursospring.resources.exceptions.FieldMessage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UpdateClientValidator implements ConstraintValidator<UpdateClient, ClientDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> errors = new ArrayList<>();

        @SuppressWarnings("unchecked")
        Map<String, String> mapAttributes = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Client aux = clientRepository.findByEmail(objDto.getEmail());
        Integer id = Integer.parseInt(mapAttributes.get("id"));
        if (aux != null && aux.getId() != id) {
            errors.add(new FieldMessage("email", "email already registered by other client"));
        }

        for (FieldMessage x : errors) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(x.getMessage())
                .addPropertyNode(x.getField())
                .addConstraintViolation();
        }
        return errors.isEmpty();
    }
}
