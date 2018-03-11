# SpringCloudForDotNet

> 这是一个使用 SpringCloud 异构 .net core 的项目，可使用 docker 部署，实现了服务发现，api网关，配置中心，断路器

## 项目介绍

```
admin-service
eureka-service
eureka-client
gateway-service
config-service
simple-service
zipkin-service
sidecar-service
```

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



断路器实现：
    断路器模式源于 Martin Fowler 的 Circuit Breaker 一文。类似一种开关装置，用于在电路上保护线路过载，
    在分布式架构中，断路器模式的作用也是类似的，当某个服务单元发生故障(类似用电器发生短路)之后，通过断路器的故障监控(类似熔断保险丝)，向调用发返回一个
    错误响应，而不是长时间等待。这样就不会使得线程因调用故障服务被长时间占用不释放，避免了故障在分布式系统中的蔓延。
Spring Cloud Hystrix 实现了断路器，线程隔离等一系服务保护功能。它也是基于 Netflix 的开源框架 Hystrix 实现的，该框架的目标在于通过控制那些访问远程
系统，服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力。Hystrix 具备服务降级，服务熔断，线程和信号隔离，请求缓存，请求合并以及服务监控等强大功能。
Netflix Hystrix 官方提供的流程图：
    


## License
[MIT](https://github.com/PanJiaChen/vueAdmin-template/blob/master/LICENSE) license.

Copyright (c) 2018-present GongTao


