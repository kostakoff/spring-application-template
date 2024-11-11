   package org.kostakoff.application;

   import io.micrometer.core.instrument.MeterRegistry;
   import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;

   @Configuration
   public class MonitoringConfiguration {

       @Bean
       public MeterRegistryCustomizer<MeterRegistry> customizer() {
           return registry -> registry.config().commonTags("application", "your-app-name");
       }
   }