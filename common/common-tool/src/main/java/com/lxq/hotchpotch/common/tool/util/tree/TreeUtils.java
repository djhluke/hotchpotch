package com.lxq.hotchpotch.common.tool.util.tree;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lxq.hotchpotch.common.tool.util.tree.inter.ChildrenTreeInter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeUtils {

    public static <T, D> List<D> getChildrenTree(List<T> list, ChildrenTreeInter<T, D> childrenTreeInter) {
        Map<String, D> childrenTreeMap = Maps.newLinkedHashMap();
        Map<String, String> parentIdMap = Maps.newHashMap();
        for (T t : list) {
            String id = childrenTreeInter.getId(t);
            String parentId = childrenTreeInter.getParentId(t);
            D childrenTree = childrenTreeInter.mapper(t);
            childrenTreeMap.put(id, childrenTree);
            parentIdMap.put(id, parentId);
        }
        List<D> rootChildrenTreeList = Lists.newArrayList();
        Map<String, List<D>> parentChildrenTreeMap = Maps.newHashMap();
        for (Map.Entry<String, D> entry : childrenTreeMap.entrySet()) {
            String id = entry.getKey();
            D childrenTree = entry.getValue();
            String parentId = parentIdMap.get(id);
            if (childrenTreeInter.getRootId().equals(childrenTreeInter.includeRootSelf() ? id : parentId)) {
                rootChildrenTreeList.add(childrenTree);
            } else {
                D parentTree = childrenTreeMap.get(parentId);
                if (parentTree != null) {
                    List<D> childrenTreeList = parentChildrenTreeMap.get(parentId);
                    if (childrenTreeList == null) {
                        childrenTreeList = new ArrayList<>();
                        parentChildrenTreeMap.put(parentId, childrenTreeList);
                        childrenTreeInter.setChildren(parentTree, childrenTreeList);
                    }
                    childrenTreeList.add(childrenTree);
                }
            }
        }
        return rootChildrenTreeList;
    }

}
