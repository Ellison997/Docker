package top.allviewit.lightlist.common;

public class RespModel {
    public RespModel(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public RespModel() {
    }

    public boolean success;
    public Object data;
    public String message;

    public boolean getSuccess() {
        return this.success;
    }

    public void isSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}