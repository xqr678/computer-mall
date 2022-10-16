package com.cy.store.service.ex;

/**
 * @author XQR
 * @date 2022/10/9 2022/10/9
 * @dsecription 用户在更新数据时产生未知的异常
 */
public class UpdateException extends ServiceException{
    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
