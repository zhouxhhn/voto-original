package bjl.core.enums;

/**
 * Created by pengyi on 2016/3/9.
 */
public enum FlowType {

    ALL("全部", 0, Boolean.TRUE),
    IN_FLOW("流入", 1, Boolean.FALSE),
    OUT_FLOW("流出", 2, Boolean.FALSE);

    FlowType(String name, int value, Boolean onlyQuery) {
        this.name = name;
        this.value = value;
        this.onlyQuery = onlyQuery;
    }

    private String name;

    private int value;

    private Boolean onlyQuery;                  // 仅用于页面查询和业务逻辑无关

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public Boolean isOnlyQuery() {
        return onlyQuery;
    }

}
