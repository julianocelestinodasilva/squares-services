package vittahealth.hiring.challenge;


public class Data {

    private long count;
    private Object data;
    private String errorMsg;

    public Data(long count, Object data) {
        this.count = count;
        this.data = data;
    }

    public Data(Object data) {
        this.data = data;
    }

    public Data(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
