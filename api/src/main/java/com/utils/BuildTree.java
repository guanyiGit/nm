package com.utils;

import com.entities.Tree;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class BuildTree {

	public static <T> Tree<T> build(List<Tree<T>> nodes) {

		if (nodes == null) {
			return null;
		}
		List<Tree<T>> topNodes = new ArrayList<Tree<T>>();

		for (Tree<T> children : nodes) {

			String pid = children.getParentId();
			if (pid == null || "0".equals(pid)) {
				topNodes.add(children);

				continue;
			}

			for (Tree<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
					continue;
				}
			}

		}

		Tree<T> root = new Tree<T>();
		if (topNodes.size() == 1) {
			root = topNodes.get(0);
		} else {
			root.setId("-1");
			root.setParentId("");
			root.setHasParent(false);
			root.setChildren(true);
			root.setChecked(true);
			root.setChildren(topNodes);
			root.setText("顶级节点");
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			root.setState(state);
		}

		return root;
	}



	/**
	 *
	 * 过滤出所有的顶级菜单
	 */
	public static <T> List<Tree<T>> topMenu(List<Tree<T>> nodes, String idParam) {
        List<Tree<T>> topMenu = nodes.stream().filter(tree -> {return idParam.equals(tree.getParentId());}).collect(Collectors.toList());
        return topMenu;
	}



    /**
     *
     * 根据父id查找所有的子
     */
    public static <T>  Tree<T> getAllChild(List<Tree<T>> nodes, String id){
        Tree<T> parentTree = nodes.stream().filter(tree -> { return id.equals(tree.getId()); }).collect(Collectors.toList()).get(0);
        List<Tree<T>> childs = nodes.stream().filter(tree -> { return tree.getParentId().equals(id); }).collect(Collectors.toList());
        if(childs==null||"".equals(childs)){
            return parentTree;
        }
        childs.stream().forEach(child ->{
            child.setHasParent(true);
            parentTree.setChildren(true);
            parentTree.getChildren().add(child);;
            getAllChild(nodes,child.getId());
       });
        return parentTree;
    }


    public static <T> List<Tree<T>> buildList(List<Tree<T>> nodes, String idParam) {
		if (nodes == null) {
			return null;
		}
		List<Tree<T>> topNodes = new ArrayList<Tree<T>>();

		for (Tree<T> children : nodes) {

			String pid = children.getParentId();
			if (pid == null || idParam.equals(pid)) {
				topNodes.add(children);

				continue;
			}

			for (Tree<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);

					continue;
				}
			}

		}
		return topNodes;
	}


	//将list转为treeList
	public static  <T> List<Tree<T>>   changeListToTreeList(List<T> list){
		List<Tree<T>> trees = null;
		try {
			trees = new ArrayList<Tree<T>>();
			for (T  tre : list) {
				Class<?> clas = tre.getClass();
				Tree<T> tree = new Tree<T>();
				Field[] fields = clas.getDeclaredFields();
				Arrays.stream(fields).forEach(field -> {
					field.setAccessible(true);
					String name = field.getName();
					try {
						if(name.equals("deptId")||name.equals("id")){
							Object id = field.get(tre);
							tree.setId(id.toString());

						}else  if(name.equals("pid")||name.equals("parentId")){
							Object pid = field.get(tre);
							tree.setParentId(pid.toString());
						}else  if(name.equals("name")){
							Object names = field.get(tre);
							tree.setText(names.toString());
						}
					}catch (Exception e){

					}
				});
				trees.add(tree);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return trees;
	}

}