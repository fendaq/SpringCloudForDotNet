# SpringCloudForDotNet

> 这是一个使用 SpringCloud 异构 .net core 的项目，可使用 docker 部署，实现了服务发现，api网关，配置中心，断路器

## Build Setup

``` bash

# Clone project
git clone ssh://git@gitlab.knoviagroup.cn:1022/root/SpringCloudForDotNet.git

```
## 相关实现
服务发现使用由 Spring Cloud 封装的 Netflix Eureka 实现服务注册中心，针对 .net core 项目需要注意三个地方：

1.再 Nuget 包的配置文件 KPM.csproj 的 <ItemGroup> 节点下添加客户端服务发现依赖：
```
<ItemGroup>
    ...
    <PackageReference Include="Pivotal.Discovery.Client" Version="1.1.0" />
    ...
</ItemGroup>
```
2.在 appsettings.json 文件中添加：

```
"spring": {
    "application": {
      "name": "kpmservice"    #声明服务注册名称
    }
  },
  "eureka": {
    "client": {
      "serviceUrl": "http://discovery:8761/eureka/",    #生命服务注册地址
      "shouldFetchRegistry": false,               
      "shouldRegisterWithEureka": true
    },
    "instance": {
      "port": 8090    #服务的端口号
    }
  }

```
3.在启动类 Startup.cs 文件中的 ConfigureServices() 方法和 Configure() 方法中分别添加两句代码来获取配置信息构建客户端服务并将其注册到 eureka 服务注册中心上：
```
// 运行时调用此方法，使用此方法将服务添加到容器
public void ConfigureServices(IServiceCollection services){
  ...
  services.AddDiscoveryClient(Configuration); // 添加服务注册支持，并传入基本服务信息
  ...
}

//// 运行时调用此方法，使用此方法配置 HTTP 请求
public void Configure(IApplicationBuilder app, IHostingEnvironment env){
  ...
  app.UseDiscoveryClient(); // 将服务注册到eureka server上
  ...
}
```
以上配置完成后，启动 eureka-server 后，再启动 kpm 项目可以看到 kpm 已经注册到 eureka 服务注册中心上。

配置中心由 Spring Cloud Config 实现分布式配置管理：
1.再 Nuget 包的配置文件 KPM.csproj 的 <ItemGroup> 节点下添加客户端服务发现依赖：
```
<ItemGroup>
    ...
    <PackageReference Include="Steeltoe.Extensions.Configuration.ConfigServerCore" Version= "2.0.0"/>
    ...
</ItemGroup>
```
2.在 appsettings.json 文件中添加：

```
{
    "spring": {
        "application": {
          "name": "kpmservice"    #声明服务注册名称
        }
      },
      "cloud": {
        "config": {
          "uri": "http://localhost:8888"    #声明服务配置中心地址
        }
      }
    ...
}
```



 
 


微服务中网关尤为重要，相关服务不暴露请求地址，由网关统一调度，它包含路由，授权，压力测试等一系列功能，这里使用 Spring Cloud 提供的 Api 网关组件 Zuul 实现：



## License
[MIT](https://github.com/PanJiaChen/vueAdmin-template/blob/master/LICENSE) license.

Copyright (c) 2018-present GongTao


