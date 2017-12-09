package com.example.ehab.japroject.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by ehab on 12/1/17.
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RunOn {
    SchedularType value() default SchedularType.IO;
}
