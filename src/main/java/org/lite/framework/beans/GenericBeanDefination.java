package org.lite.framework.beans;

/**
 * @ClassName: GenericBeanDefination
 * @description: TODO
 * @author: leijing
 * @Date: 2019/5/29 下午11:37
 * @Version: 1.0
 */
public class GenericBeanDefination implements BeanDefination {
    private String id;
    private String beanClassName;
    public GenericBeanDefination(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        return beanClassName;
    }
}
