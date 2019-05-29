package org.lite.framework.exception;

/**
 * @ClassName: BeanCreationException
 * @description: TODO
 * @author: leijing
 * @Date: 2019/5/30 上午1:00
 * @Version: 1.0
 */
public class BeanCreationException extends BeanException{
    public BeanCreationException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public BeanCreationException(String msg) {
        super(msg);
    }
}
