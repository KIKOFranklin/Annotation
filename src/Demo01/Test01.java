package Demo01;

/**
 * @author KIKOFranklin
 * @create 2021/3/30 0030 22:10
 */
@DBtable(name="SU_DELIVERY_SERVICE")
public class Test01 {

    @SQLstring(name = "NAME",length = 10, constraints = @Constraints(isPrimaryKey = true) )
    private String name;

    @SQLInteger(name = "AGE", constraints = @Constraints(isAllowNull = true) )
    private Integer age;

    @SQLstring(name = "ORG", length = 10, constraints = @Constraints(isUnique = true))
    private String org;

}
