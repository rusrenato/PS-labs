package ro.utcluj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.security.remoting.httpinvoker.AuthenticationSimpleHttpInvokerRequestExecutor;
import ro.utcluj.api.CarServiceInterface;
import ro.utcluj.api.UserServiceInterface;
import ro.utcluj.api.WashServiceInterface;

@Configuration
public class ClientConfig {

    @Bean
    public AuthenticationSimpleHttpInvokerRequestExecutor httpInvokerRequestExecutor() { // spring-security-remoting dependency (check latest version here https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-dependency-versions.html)
        return new AuthenticationSimpleHttpInvokerRequestExecutor();
    }

  /*  @Bean
    public HttpInvokerProxyFactoryBean studentInvoker(AuthenticationSimpleHttpInvokerRequestExecutor requestExecutor) {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setHttpInvokerRequestExecutor(requestExecutor);
        invoker.setServiceUrl("http://localhost:8080/student");
        invoker.setServiceInterface(StudentService.class);
        return invoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean anotherInvoker(AuthenticationSimpleHttpInvokerRequestExecutor requestExecutor) {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setHttpInvokerRequestExecutor(requestExecutor);
        invoker.setServiceUrl("http://localhost:8080/another");
        invoker.setServiceInterface(AnotherService.class);
        return invoker;
    }*/

    @Bean
    public HttpInvokerProxyFactoryBean userInvoker(AuthenticationSimpleHttpInvokerRequestExecutor requestExecutor) {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setHttpInvokerRequestExecutor(requestExecutor);
        invoker.setServiceUrl("http://localhost:8080/user");
        invoker.setServiceInterface(UserServiceInterface.class);
        return invoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean carInvoker(AuthenticationSimpleHttpInvokerRequestExecutor requestExecutor) {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setHttpInvokerRequestExecutor(requestExecutor);
        invoker.setServiceUrl("http://localhost:8080/car");
        invoker.setServiceInterface(CarServiceInterface.class);
        return invoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean washInvoker(AuthenticationSimpleHttpInvokerRequestExecutor requestExecutor) {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setHttpInvokerRequestExecutor(requestExecutor);
        invoker.setServiceUrl("http://localhost:8080/wash");
        invoker.setServiceInterface(WashServiceInterface.class);
        return invoker;
    }
}
