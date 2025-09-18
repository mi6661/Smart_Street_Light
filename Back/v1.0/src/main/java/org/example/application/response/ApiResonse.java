package org.example.application.response;



public class ApiResonse<T>{
    private int status;
    private String message;
    private T data;

    //成功
    public static <T> ApiResonse<T> success(T data){
        ApiResonse<T> response = new ApiResonse<T>();
        response.setStatus(200);
        response.setMessage("success");
        response.setData(data);
        return response;
    }
    //失败
    public static <T> ApiResonse<T> fail(String message){
        ApiResonse<T> response = new ApiResonse<T>();
        response.setStatus(500);
        response.setMessage(message);
        return response;
    }


    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
