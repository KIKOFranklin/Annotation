package Demo01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author KIKOFranklin
 * @create 2021/3/30 0030 22:02
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SQLstring {
    //名称
    String name() default "";
    //长度
    int length() default 0;
    //限制
    Constraints constraints() default @Constraints;
}
