package com.sustech.campus.web.annotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;


/**
 * 将多个常见的注解组合到一个单一的注解中，以简化控制器的定义和配置。包括 @RestController、@RequestMapping 和 @Validated 注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping
@Validated
public @interface MappingController {
    @AliasFor(annotation = RequestMapping.class, attribute="path")
    String[] value() default {};
}
