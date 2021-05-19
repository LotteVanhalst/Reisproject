package be.kdg.reisproject.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lotte Vanhalst
 * @version 1.0 2/04/2019 20:31
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CanRun {
    String value() default "dummy";
}
