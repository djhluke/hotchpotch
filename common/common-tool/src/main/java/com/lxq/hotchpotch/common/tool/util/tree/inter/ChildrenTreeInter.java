package com.lxq.hotchpotch.common.tool.util.tree.inter;

import java.util.List;

/**
 * @author luxinqiang
 */
public interface ChildrenTreeInter<T, D> {

    default boolean includeRootSelf() {
        return false;
    }

    default String getRootId() {
        return "0";
    }

    String getId(T t);

    String getParentId(T t);

    D mapper(T t);

    void setChildren(D d, List<D> children);

}
