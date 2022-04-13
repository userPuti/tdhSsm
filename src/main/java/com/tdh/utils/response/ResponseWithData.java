package com.tdh.utils.response;


/**
 * The type Response with data.
 *
 * @author yef
 * @date 2018 /8/30
 */
public class ResponseWithData extends  ResponseVO{

    private int code;

    private String msg;

    private Object data;


	/**
	 * Instantiates a new Response with data.
	 *
	 * @param code the code
	 * @param msg  the msg
	 * @param data the data
	 */
	public ResponseWithData(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
