package com.andx.micro.core.validator;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * Created by andongxu on 17-1-6.
 */
@Component
public class DataValidatorProcessor implements ValidatorProcessor<Request, Response> {

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public Response process(final Request request, Object... args) throws ValidatorException {
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(request);
        List<Map> errors = new ArrayList<Map>();
        for (ConstraintViolation<Request> constraintViolation : constraintViolations) {
            Map<String, String> error = new HashMap<String, String>();
            error.put(String.valueOf(constraintViolation.getPropertyPath()), constraintViolation.getMessage());
            errors.add(error);
        }
        Response response = new Response();
        response.setServiceId(request.getServiceId());
        response.setRequestId(request.getRequestId());
        if (errors.size() > 0) {
            response.setSuccess(false);
            response.setErrorMessage(errors.toString());
        } else {
            response.setSuccess(true);
        }
        return response;
    }
}
