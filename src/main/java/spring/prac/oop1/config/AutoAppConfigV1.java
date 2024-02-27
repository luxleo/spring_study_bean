package spring.prac.oop1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.Filter;

@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
basePackages = "spring.prac.oop1.member")
@Configuration
public class AutoAppConfigV1 {

}
