package com.andx.micro.user.client;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.user.config.ServiceDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import static com.amazonaws.util.AWSRequestMetrics.Field.StatusCode;

/**
 * Created by andongxu on 17-5-27.
 */
@Component
public class PermissionClientFallbak implements PermissionClient {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Override
    public Response getPermissions() {
        didNotGetResponse();
        return null;
    }

    @Override
    public Response getServices_NoPermission(@PathVariable("serviceCode") String serviceCode) {
        didNotGetResponse();
        return null;
    }

    @Override
    public Response initSevice(Request<ServiceDto> request) {
        log.error("service '{}' has become unreachable");
        return null;
    }

    private void didNotGetResponse() {
        log.error("service '{}' has become unreachable");
    }
}
