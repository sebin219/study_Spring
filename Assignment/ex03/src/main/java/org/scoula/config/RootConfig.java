package org.scoula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "org.scoula.controller",           // HomeController
        "org.scoula.ex03.controller"       // SampleController
})
public class RootConfig {
}
