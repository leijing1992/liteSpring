package org.lite.framework.beans.factory.support;

import org.lite.framework.beans.BeanDefination;
import org.lite.framework.beans.factory.BeanFactory;
import org.lite.framework.exception.BeanCreationException;
import org.lite.framework.utils.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: DefaultBeanFactory
 * @description: 默认的bean工厂实现
 * @author: leijing
 * @Date: 2019/5/29 下午11:27
 * @Version: 1.0
 */
public class DefaultBeanFactory implements BeanFactory , BeanDefinationRegistry{

    Map<String , BeanDefination> beanDefinitionMap = new ConcurrentHashMap<>();


    @Override
    public BeanDefination getBeanDefination(String id) {
        return this.beanDefinitionMap.get(id);
    }

    @Override
    public void registryBeanDefination(String id, BeanDefination beanDefination) {
        this.beanDefinitionMap.put(id , beanDefination);
    }

    @Override
    public Object getBean(String id) {
        BeanDefination bd = this.getBeanDefination(id);
        if (bd == null) {
            return null;
        }
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (ClassNotFoundException e) {
            throw new BeanCreationException(beanClassName+" not found!");
        } catch (InstantiationException e) {
            throw new BeanCreationException(beanClassName+" instantiation fail!!"+e.getMessage());
        } catch (IllegalAccessException e) {
            throw new BeanCreationException(beanClassName+" illegalAccess exception!!"+e.getMessage());
        }
    }
}
