package com.hengmall.treehousegateway.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("用户系统", "/api-user/v2/api-docs", "2.0"));
        resources.add(swaggerResource("好友系统", "/api-friend/v2/api-docs", "2.0"));
        resources.add(swaggerResource("物品装备系统", "/api-goods/v2/api-docs", "2.0"));
        resources.add(swaggerResource("树屋宅友系统", "/api-treeHouse/v2/api-docs", "2.0"));
        resources.add(swaggerResource("钓鱼系统", "/api-fishing/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
