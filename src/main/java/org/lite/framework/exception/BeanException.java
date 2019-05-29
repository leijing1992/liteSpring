package org.lite.framework.exception;

/**
 * @ClassName: BeanParseException
 * @description: TODO
 * @author: leijing
 * @Date: 2019/5/30 上午12:56
 * @Version: 1.0
 */
public class BeanException extends RuntimeException{
    public BeanException(String msg){
        super(msg);
    }
    public BeanException(String msg , Throwable throwable){
        super(msg , throwable);
    }
}
