package com.andx.micro.management.client;

import com.andx.micro.api.core.dto.PageResponse;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.management.dto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-7.
 */
@FeignClient(value = "permission")
public interface PermissionClient {

    //role
    @RequestMapping(method = RequestMethod.GET, value = "/service/org/{id}/roles", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<RoleDto>> queryOrgRoles(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.POST, value = "/service/role", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<RoleDto> newRole(Request<RoleDto> request);

    @RequestMapping(method = RequestMethod.PUT, value = "/service/role/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<RoleDto> editRole(@PathVariable("id") Long id, Request<RoleDto> request);

    @RequestMapping(method = RequestMethod.DELETE, value = "/service/role/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<RoleDto> deleteRole(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/role/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<RoleDto> queryRole(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    //service
    @RequestMapping(method = RequestMethod.GET, value = "/service/services", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    Response<List<ServiceDto>> queryServices(String code, String method, String isAudit, Integer page, Integer size);
    PageResponse<List<ServiceDto>> queryServices(@RequestParam Map<String, String> prams, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/service/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<ServiceDto> queryService(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/services/noPermission", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<ServiceDto>> queryServicesNoPermission(@RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.PUT, value = "/service/service/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response editService(@PathVariable("id") Long id, Request<ServiceDto> request);

    @RequestMapping(method = RequestMethod.GET, value = "/service/permissions/services", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<PermissionServiceDto>> queryPermissionsServices(@RequestParam Map<String, String> prams);

    @RequestMapping(method = RequestMethod.GET, value = "/service/role/{id}/permissions/services", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<RoleDto>> queryRolePermissionsServices(@RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);


    //menu
    @RequestMapping(method = RequestMethod.GET, value = "/service/tree-menus", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<MenuDto> queryTreeMenus(@RequestParam("id") String id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/list-menus", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<MenuDto>> queryListMenus(@RequestParam("id") String id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/list-menus/noPermission", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<MenuDto>> queryListMenusNoPermission(@RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/list-menus/ExistPermission", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<MenuDto>> queryListMenusExistPermission(@RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.POST, value = "/service/menu", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<MenuDto> newMenu(Request<MenuDto> request);

    @RequestMapping(method = RequestMethod.PUT, value = "/service/menu/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<MenuDto> editMenu(@PathVariable("id") Long id, Request<MenuDto> request);

    @RequestMapping(method = RequestMethod.DELETE, value = "/service/menu/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<MenuDto>> deleteMenu(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/permissions/menus", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<PermissionMenuDto>> queryPermissionsMenus(@RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/role/{id}/list-menus", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<MenuDto>> queryRoleListMenus(@PathVariable("id") Long roleId, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);


    //permission
    @RequestMapping(method = RequestMethod.POST, value = "/service/permission", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<MenuDto> newPermission(Request<List<NewPermissionDto>> request);

    @RequestMapping(method = RequestMethod.DELETE, value = "/service/permission/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response deletePermission(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/permissions", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<RoleDto>> queryPermissions(@RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/owner/{ownerId}/resource/{serviceCode}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<RoleDto>> queryOwnerResource(@PathVariable("ownerId") String ownerId, @PathVariable("serviceCode") String serviceCode, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.POST, value = "/service/service/init", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response initSevice(Request<TmpDto> request);

    @RequestMapping(method = RequestMethod.GET, value = "/service/role/{id}/permissions", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<PermissionDto>> queryRolePermissions(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/channel/{id}/permissions", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<PermissionDto>> queryChannelPermissions(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    //channel
    @RequestMapping(method = RequestMethod.POST, value = "/service/channel", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<MenuDto> newChannel(Request<ChannelDto> request);

    @RequestMapping(method = RequestMethod.PUT, value = "/service/channel/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response editChannel(@PathVariable("id") Long id, Request<ChannelDto> request);

    @RequestMapping(method = RequestMethod.PUT, value = "/service/channel/{id}/permissions", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response editChannelPermissions(@PathVariable("id") Long id, Request<List<Long>> request);

    @RequestMapping(method = RequestMethod.DELETE, value = "/service/channel/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response deleteChannel(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/channels", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<List<ChannelDto>> queryChannels(@RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

    @RequestMapping(method = RequestMethod.GET, value = "/service/channel/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<ChannelDto> queryChannel(@PathVariable("id") Long id, @RequestParam("channelId") String channelId, @RequestParam("requestId") String requestId);

}
