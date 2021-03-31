package Demo01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author KIKOFranklin
 * @create 2021/3/30 0030 22:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SQLInteger {

    //名称
    String name() default "";

    //限制
    Constraints constraints() default @Constraints;

}
