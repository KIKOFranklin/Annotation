package Demo01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author KIKOFranklin
 * @create 2021/3/30 0030 22:04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Constraints {
    //是否是主键
    boolean isPrimaryKey() default false;
    //是否是不能为空
    boolean isAllowNull() default false;
    //是否唯一
    boolean isUnique() default false;
}
