package com.cy.store.service.ex;

/**
 * @author XQR
 * @date 2022/10/9 2022/10/9
 * @dsecription 新密码确认是否准确
 */
public class NewPasswordNotMatchException extends ServiceException{
    public NewPasswordNotMatchException() {
        super();
    }

    public NewPasswordNotMatchException(String message) {
        super(message);
    }

    public NewPasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewPasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    protected NewPasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
