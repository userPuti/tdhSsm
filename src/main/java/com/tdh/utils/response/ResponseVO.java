package com.tdh.utils.response;

/**
 * The type Response vo.
 *
 * @author yef
 * @date 2018 /8/30
 */
public class ResponseVO {

    private int code;

    private String msg;

    /**
     * des:数据
     */
    private Object data;
    /**
     * des:附加说明
     */
    private String attMsg;

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public int getCode() {
        return code;
    }

	/**
	 * Sets code.
	 *
	 * @param code the code
	 */
	public void setCode(int code) {
        this.code = code;
    }

	/**
	 * Gets msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
        return msg;
    }

	/**
	 * Sets msg.
	 *
	 * @param msg the msg
	 */
	public void setMsg(String msg) {
        this.msg = msg;
    }

	/**
	 * Gets data.
	 *
	 * @return the data
	 */
	public Object getData() {
        return data;
    }

	/**
	 * Sets data.
	 *
	 * @param data the data to set
	 */
	public void setData(Object data) {
        this.data = data;
    }

	/**
	 * Gets att msg.
	 *
	 * @return the attMsg
	 */
	public String getAttMsg() {
        return attMsg;
    }

	/**
	 * Sets att msg.
	 *
	 * @param attMsg the attMsg to set
	 */
	public void setAttMsg(String attMsg) {
        this.attMsg = attMsg;
    }

	@Override
	public String toString() {
		return "ResponseVO{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", data=" + data +
				", attMsg='" + attMsg + '\'' +
				'}';
	}
}
