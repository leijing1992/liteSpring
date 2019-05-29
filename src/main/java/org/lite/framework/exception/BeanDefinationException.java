package org.lite.framework.exception;


/**
 * @ClassName: BeanDefinationException
 * @description: TODO
 * @author: leijing
 * @Date: 2019/5/30 上午1:00
 * @Version: 1.0
 */
public class BeanDefinationException extends BeanException{
    public BeanDefinationException(String msg) {
        super(msg);
    }

    public BeanDefinationException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
