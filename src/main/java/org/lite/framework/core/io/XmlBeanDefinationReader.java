package org.lite.framework.core.io;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lite.framework.beans.BeanDefination;
import org.lite.framework.beans.GenericBeanDefination;
import org.lite.framework.beans.factory.support.BeanDefinationRegistry;
import org.lite.framework.exception.BeanDefinationException;
import org.lite.framework.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @ClassName: XmlBeanDefinationReader
 * @description: xml文件reader
 * @author: leijing
 * @Date: 2019/5/30 上午2:10
 * @Version: 1.0
 */
public class XmlBeanDefinationReader {
    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";
    private BeanDefinationRegistry registry;

    public XmlBeanDefinationReader(BeanDefinationRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefination(String configFile) {
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
                this.registry.registryBeanDefination(id, bd);
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
}
