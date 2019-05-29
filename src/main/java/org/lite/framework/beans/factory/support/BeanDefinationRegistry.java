package org.lite.framework.beans.factory.support;

import org.lite.framework.beans.BeanDefination;

/**
 * @ClassName: BeanDefinationRegistry
 * @description: bean定义注册类
 * @author: leijing
 * @Date: 2019/5/30 上午2:14
 * @Version: 1.0
 */
public interface BeanDefinationRegistry {
    BeanDefination getBeanDefination(String id);
    void registryBeanDefination(String id , BeanDefination beanDefination);
}
