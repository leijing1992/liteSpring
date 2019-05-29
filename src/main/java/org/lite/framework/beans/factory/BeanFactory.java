package org.lite.framework.beans.factory;

import org.lite.framework.beans.BeanDefination;

/**
 * @ClassName: BeanFactory
 * @description: TODO
 * @author: leijing
 * @Date: 2019/5/29 下午11:20
 * @Version: 1.0
 */
public interface BeanFactory {
    BeanDefination getBeanDefination(String id);
    Object getBean(String id);

}
