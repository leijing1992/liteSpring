package org.lite.framework.beans.factory;

/**
 * @ClassName: BeanFactory
 * @description: bean工厂类
 * @author: leijing
 * @Date: 2019/5/29 下午11:20
 * @Version: 1.0
 */
public interface BeanFactory {
    Object getBean(String id);

}
