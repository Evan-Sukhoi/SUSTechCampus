package com.sustech.campus.database.annotation;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME) //组合注解必须要加这个
@JacksonAnnotationsInside //Jackson中需要在自定义组合注解中加上该注解
@JsonFormat(pattern = "HH:mm:ss")
public @interface TimeField {}
