package com.andx.micro.user.execute;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;

/**
 * Created by andongxu on 17-4-24.
 */
@Component
public class DeleteExecuteSample implements ExecuteTemplate<Null, SampleService, Response> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());
    @Autowired
    @Qualifier("permissionValidator")
    private Validator<PermissionValidatorDto, Boolean> permissionValidator;
    @Autowired
    @Qualifier("permissionValidatorProcessor")
    protected ValidatorProcessor<PermissionValidatorDto, Boolean> permissionValidatorProcessor;

    @Override
//    @Transactional
    public Response execute(Null aNull, SampleService processor, Object... args) throws ServiceException {
        long begin = System.currentTimeMillis();
        log.info("begin request method [DELETE], uri [" + String.valueOf(args[1]) + "]");
        try {
            SampleService sampleService = null; //(SampleService) processor;
            try {
                sampleService = (SampleService) AopTargetUtils.getTarget(processor);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage(), e);
            }
            Service service = sampleService.getClass().getAnnotation(Service.class);

            //验证是否有调用服务的权限
            long validateBegin = System.currentTimeMillis();
            log.info(String.format("begin execute validate module, service code [%s], service name [%s], service uri [%s]", service.code(), service.name(), service.path()));
            PermissionValidatorDto permissionValidatorDto = new PermissionValidatorDto();
            HttpServletRequest httpServletRequest = (HttpServletRequest) args[0];
            permissionValidatorDto.setOwnerId((String) httpServletRequest.getSession().getAttribute("userId"));
            permissionValidatorDto.setServiceCode(service.code());
            Boolean hasPermission = permissionValidator.validate(permissionValidatorDto, permissionValidatorProcessor);
            long validateEnd = System.currentTimeMillis();
            log.info(String.format("end execute validate module, service code [%s], service name [%s], service uri [%s], time mills [%d]ms, response [%s]", service.code(), service.name(), service.path(), validateEnd - validateBegin, hasPermission));
            if (hasPermission == false) {
                throw new ServiceException("no permission");
            }
            //调用目标服务
            long serviceBegin = System.currentTimeMillis();
            log.info(String.format("begin execute service code [%s], name [%s], uri [%s]", service.code(), service.name(), service.path()));
            Response response = (Response) processor.process(null, args);
            long serviceEnd = System.currentTimeMillis();
            log.info(String.format("end execute service code [%s], name [%s], uri [%s] time mills [%d]ms, response [%s]", service.code(), service.name(), service.path(), (serviceEnd - serviceBegin), response.toString()));
            return response;
        } catch (ValidatorException e) {
            log.error(String.format("validate exception %s", e.getMessage()), e);
            throw new ServiceException(e.getMessage(), e);
        } finally {
            long end = System.currentTimeMillis();
            log.info("end request method [DELETE], uri [" + args[1] + "], time mills:" + (end - begin) + " ms");
        }
    }
}
