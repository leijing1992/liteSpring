package org.lite.framework.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.lite.framework.beans.BeanDefination;
import org.lite.framework.beans.factory.BeanFactory;
import org.lite.framework.beans.factory.support.DefaultBeanFactory;
import org.lite.framework.core.io.XmlBeanDefinationReader;
import org.lite.framework.exception.BeanDefinationException;

/**
 * @ClassName: BeanFactoryTest
 * @description: BeanFactory test case
 * @author: leijing
 * @Date: 2019/5/29 下午11:10
 * @Version: 1.0
 */
public class BeanFactoryTest {

    @Test
    public void beanFactoryTest(){
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();
        XmlBeanDefinationReader reader = new XmlBeanDefinationReader(beanFactory);
        reader.loadBeanDefination("beans-v1.xml");
        BeanDefination beanDefination = beanFactory.getBeanDefination("catService");
        Assert.assertEquals("org.lite.framework.service.v1.CatService",beanDefination.getBeanClassName());
        Object bean = beanFactory.getBean("catService");
        Assert.assertNotNull(bean);
    }

    @Test
    public void xmlParseErrorTest(){
        try {
            DefaultBeanFactory beanFactory = new DefaultBeanFactory();
            XmlBeanDefinationReader reader = new XmlBeanDefinationReader(beanFactory);
            reader.loadBeanDefination("beans-not-exist.xml");
        }catch (BeanDefinationException e){
            return;
        }
        Assert.fail("expected BeanDefinationException");
    }
}
