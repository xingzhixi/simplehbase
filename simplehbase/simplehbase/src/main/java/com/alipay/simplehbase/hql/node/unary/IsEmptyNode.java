package com.alipay.simplehbase.hql.node.unary;

import java.util.Collection;
import java.util.Map;

import com.alipay.simplehbase.exception.SimpleHBaseException;
import com.alipay.simplehbase.hql.HQLNodeType;

public class IsEmptyNode extends UnaryNode {

    protected IsEmptyNode() {
        super(HQLNodeType.IsEmpty);
    }

    @Override
    protected boolean isConditionSatisfied(Map<String, Object> para) {
        if (para.containsKey(getProperty())) {
            Object value = para.get(getProperty());
            if (value == null) {
                return true;
            }
            if (value instanceof String) {
                return String.class.cast(value).isEmpty();
            }
            if (value instanceof Collection) {
                return Collection.class.cast(value).isEmpty();
            }
        }
        throw new SimpleHBaseException("IsEmptyNode invalid value.");
    }
}
