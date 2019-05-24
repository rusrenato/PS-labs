package ro.utcluj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import ro.utcluj.api.CarServiceInterface;
import ro.utcluj.api.UserServiceInterface;
import ro.utcluj.api.WashServiceInterface;

@Configuration
public class ServerConfig {

   /* @Bean(name = "/student")
    HttpInvokerServiceExporter studentService(StudentService studentService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(studentService);
        exporter.setServiceInterface(StudentService.class);
        return exporter;
    }

    @Bean(name = "/another")
    HttpInvokerServiceExporter anotherService(AnotherService anotherService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(anotherService);
        exporter.setServiceInterface(AnotherService.class);
        return exporter;
    }
*/
    @Bean(name = "/user")
    HttpInvokerServiceExporter userService(UserServiceInterface userService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(userService);
        exporter.setServiceInterface(UserServiceInterface.class);
        return exporter;
    }

    @Bean(name = "/car")
    HttpInvokerServiceExporter carService(CarServiceInterface carService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(carService);
        exporter.setServiceInterface(CarServiceInterface.class);
        return exporter;
    }

    @Bean(name = "/wash")
    HttpInvokerServiceExporter washService(WashServiceInterface washService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(washService);
        exporter.setServiceInterface(WashServiceInterface.class);
        return exporter;
    }


}
