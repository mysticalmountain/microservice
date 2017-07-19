package com.andx.micro.core.service.execute;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.execute.ExecuteTemplate;
import com.andx.micro.api.core.module.service.ComplexService;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.validator.Validator;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.core.util.Constant;
import com.andx.micro.core.validator.PermissionValidatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

//import com.andx.micro.api.log.Log;
//import com.andx.micro.core.log.log4j.Log4jLogFactory;

/**
 * Created by andongxu on 17-4-24.
 */
@Component
public class GetExecuteComplex implements ExecuteTemplate<Map<String, String>, ComplexService, Response> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Autowired
    @Qualifier("permissionValidator")
    private Validator<PermissionValidatorDto, Boolean> permissionValidator;
    @Autowired
    @Qualifier("permissionValidatorProcessor")
    private ValidatorProcessor<PermissionValidatorDto, Boolean> permissionValidatorProcessor;

    @Override
    public Response execute(Map<String, String> stringMap, ComplexService processor, Object... args) throws ServiceException {
        long begin = System.currentTimeMillis();
        String requestId = stringMap.get(Constant.KEY_REQUEST_ID);
        String executeId = String.valueOf(begin);
        if (requestId == null) {
            requestId = UUID.randomUUID().toString();
            stringMap.put(Constant.KEY_REQUEST_ID, requestId);
        }
        log.info(String.format("[%s -> %s] begin request method [GET], uri [%s] ms", requestId, executeId, String.valueOf(args[1])));
        try {
//            if (stringMap.get("channelId") == null) {
//                throw new  ServiceException("未知的渠道");
//            }
            ComplexService sampleService = (ComplexService) processor;
            Service service = sampleService.getClass().getAnnotation(Service.class);
            PermissionValidatorDto permissionValidatorDto = new PermissionValidatorDto();
            permissionValidatorDto.setOwnerId(stringMap.get("channelId"));
            permissionValidatorDto.setServiceCode(service.code());
            permissionValidatorDto.setRequestId(requestId);
            permissionValidatorDto.setExecuteId(executeId);
            Boolean hasPermission = permissionValidator.validate(permissionValidatorDto, permissionValidatorProcessor, args);
            if (hasPermission == false) {
                throw new ServiceException(String.format("渠道[%s]无权访问服务[%s]", permissionValidatorDto.getOwnerId(), permissionValidatorDto.getServiceCode()));
            }
            //调用目标服务
            Object [] target = new Object[args.length + 1];
            System.arraycopy(args, 0, target, 0, args.length);
            target[args.length] = executeId;
            Response response = (Response) processor.process(stringMap, target);
            return response;
        } catch (ValidatorException e) {
            log.error(String.format("[%s -> %s] validate exception %s", requestId, executeId, e.getMessage()), e);
            throw new ServiceException(e.getMessage(), e);
        } catch (Exception e) {
            log.error(String.format("[%s -> %s] validate exception %s", requestId, executeId, e.getMessage()), e);
            throw new ServiceException(e.getMessage(), e);
        } finally {
            long end = System.currentTimeMillis();
            log.info(String.format("[%s -> %s] end request method [GET], uri [%s] time mills: [%s] ms", requestId, executeId, args[1], (end - begin)));
        }
    }
}
