package entity;

import java.io.Serializable;

/**
 * 添加结果类
 */
public class Result implements Serializable {
    private boolean success;
    private String message;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Result executeSuccess(String message){
        return new Result(true,message);
    }

    public static Result executeFail(String mesage){
        return new Result(false,mesage);
    }
}
