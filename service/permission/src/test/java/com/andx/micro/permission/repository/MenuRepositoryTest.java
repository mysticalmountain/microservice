package com.andx.micro.permission.repository;

import com.andx.micro.permission.BaseTest;
import com.andx.micro.permission.model.Menu;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.ResourceType;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andongxu on 17-6-17.
 */
public class MenuRepositoryTest extends BaseTest {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired ResourceRepository resourceRepository;

    @Test
    public void initMenu() {
        Menu n1 = new Menu();
        n1.setName("n1");
        Menu n11 = new Menu();
        n11.setName("n11");
        n11.setParent(n1);
        Menu n12 = new Menu();
        n12.setName("n12");
        n12.setParent(n1);
        Menu n13 = new Menu();
        n13.setName("n13");
        n13.setParent(n1);
        Menu n121 = new Menu();
        n121.setName("n121");
        n121.setParent(n12);
        Menu n122 = new Menu();
        n122.setName("n122");
        n122.setParent(n12);
        menuRepository.save(n1);
        menuRepository.save(n11);
        menuRepository.save(n12);
        menuRepository.save(n13);
        menuRepository.save(n121);
        menuRepository.save(n122);

    }

    @Test
    public void initMenu2() {
        Menu root = new Menu();
        root.setName("root");
        Menu system = new Menu();
        system.setName("系统管理");
        system.setOd(10);
        system.setParent(root);
        Menu user = new Menu();
        user.setName("用户管理");
        user.setAction("/views/user/list.html");
        user.setOd(10);
        user.setParent(system);
        Menu role = new Menu();
        role.setName("角色管理");
        role.setAction("/views/role/list.html");
        role.setOd(20);
        role.setParent(system);
        Menu permission = new Menu();
        permission.setName("权限管理");
        permission.setAction("/views/permission/list.html");
        permission.setOd(30);
        permission.setParent(system);
        Menu service = new Menu();
        service.setName("服务管理");
        service.setAction("/views/service/list.html");
        service.setOd(40);
        service.setParent(system);
        Menu menu = new Menu();
        menu.setName("菜单管理");
        menu.setAction("/views/menu/list.html");
        menu.setOd(50);
        menu.setParent(system);

        Resource r1 = new Resource();
        r1.setResourceType(ResourceType.MENU);
        resourceRepository.save(r1);
        root.setResource(r1);
        menuRepository.save(root);

        Resource r2 = new Resource();
        r2.setResourceType(ResourceType.MENU);
        resourceRepository.save(r2);
        system.setResource(r2);
        menuRepository.save(system);

        Resource r3 = new Resource();
        r3.setResourceType(ResourceType.MENU);
        resourceRepository.save(r3);
        user.setResource(r3);
        menuRepository.save(user);

        Resource r4 = new Resource();
        r4.setResourceType(ResourceType.MENU);
        resourceRepository.save(r4);
        role.setResource(r4);
        menuRepository.save(role);

        Resource r5 = new Resource();
        r5.setResourceType(ResourceType.MENU);
        resourceRepository.save(r5);
        permission.setResource(r5);
        menuRepository.save(permission);

        Resource r6 = new Resource();
        r6.setResourceType(ResourceType.MENU);
        resourceRepository.save(r6);
        service.setResource(r6);
        menuRepository.save(service);

        Resource r7 = new Resource();
        r7.setResourceType(ResourceType.MENU);
        resourceRepository.save(r7);
        menu.setResource(r7);
        menuRepository.save(menu);


    }

    @Test
    public void initMenuMenu() {
        Menu root = menuRepository.findByName("系统管理");
        Menu menu = new Menu();
        menu.setName("菜单管理");
        menu.setParent(root);
        menu.setAction("/views/menu/list.html");
        menu.setOd(50);
        menuRepository.save(menu);
    }

    @Test
    public void queryTree() {
        Menu menu = menuRepository.findOne(Long.valueOf(10000265));
        System.out.println(menu);

    }
}
