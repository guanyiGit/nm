package atshunhengli.com.entity.app;

import java.util.List;

public class GroupExpress {

    private Operator operator = Operator.AND;

    private List<GroupElement> groupElements;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public List<GroupElement> getGroupElements() {
        return groupElements;
    }

    public void setGroupElements(List<GroupElement> groupElements) {
        this.groupElements = groupElements;
    }

}
