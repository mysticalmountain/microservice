package com.andx.micro.core.service.execute;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.execute.ExecuteTemplate;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.core.module.validator.Validator;
import com.andx.micro.api.core.module.validator.ValidatorException;
import com.andx.micro.api.core.module.validator.ValidatorProcessor;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.core.util.AopTargetUtils;
import com.andx.micro.core.validator.PermissionValidatorDto;
import com.andx.micro.core.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * Created by andongxu on 17-4-24.
 */
@Component
public class DeleteExecuteSample implements ExecuteTemplate<Map<String, String>, SampleService, Response> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());
    @Autowired
    @Qualifier("permissionValidator")
    private Validator<PermissionValidatorDto, Boolean> permissionValidator;
    @Autowired
    @Qualifier("permissionValidatorProcessor")
    protected ValidatorProcessor<PermissionValidatorDto, Boolean> permissionValidatorProcessor;

    @Override
    public Response execute(Map<String, String> prams, SampleService processor, Object... args) throws ServiceException {
        long begin = System.currentTimeMillis();
        String requestId = prams.get(Constant.KEY_REQUEST_ID);
        String executeId = String.valueOf(begin);
        prams.put(Constant.KEY_EXECUTE_ID, executeId);
        if (requestId == null) {
            requestId = UUID.randomUUID().toString();
            prams.put(Constant.KEY_REQUEST_ID, requestId);
        }
        log.info(String.format("[%s -> %s] begin request method [GET], uri [%s] ms", requestId, executeId, String.valueOf(args[1])));
        try {
//            if (prams.get("channelId") == null) {
//                throw new ServiceException("未知的渠道");
//            }
            SampleService sampleService = (SampleService) AopTargetUtils.getTarget(processor);
            Service service = sampleService.getClass().getAnnotation(Service.class);
            //验证是否有调用服务的权限
            PermissionValidatorDto permissionValidatorDto = new PermissionValidatorDto();
            permissionValidatorDto.setOwnerId(prams.get("channelId"));
            permissionValidatorDto.setServiceCode(service.code());
            Boolean hasPermission = permissionValidator.validate(permissionValidatorDto, permissionValidatorProcessor, args);
            if (hasPermission == false) {
                throw new ServiceException(String.format("渠道[%s]无权访问服务[%s]", permissionValidatorDto.getOwnerId(), permissionValidatorDto.getServiceCode()));
            }
            //调用目标服务
            Object[] target = new Object[args.length + 1];
            System.arraycopy(args, 0, target, 0, args.length);
            target[args.length] = executeId;
            Response response = (Response) processor.process(null, target);
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
