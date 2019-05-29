package org.lite.framework.beans.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lite.framework.beans.BeanDefination;
import org.lite.framework.beans.GenericBeanDefination;
import org.lite.framework.beans.factory.BeanFactory;
import org.lite.framework.exception.BeanCreationException;
import org.lite.framework.exception.BeanDefinationException;
import org.lite.framework.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: DefaultBeanFactory
 * @description: 默认的bean工厂实现
 * @author: leijing
 * @Date: 2019/5/29 下午11:27
 * @Version: 1.0
 */
public class DefaultBeanFactory implements BeanFactory {
    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";
    Map<String , BeanDefination> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactory(String configFile) {
        loadBeanDefination(configFile);
    }

    private void loadBeanDefination(String configFile) {
        InputStream is = null;
        try {
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            is = cl.getResourceAsStream(configFile);

            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);

            Element root = doc.getRootElement(); //<beans>
            Iterator<Element> iter = root.elementIterator();
            while (iter.hasNext()) {
                Element ele = (Element) iter.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefination bd = new GenericBeanDefination(id, beanClassName);
                this.beanDefinitionMap.put(id, bd);
            }
        } catch (DocumentException e) {
            throw new BeanDefinationException("parse "+configFile+" error!"+e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public BeanDefination getBeanDefination(String id) {
        return this.beanDefinitionMap.get(id);
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
