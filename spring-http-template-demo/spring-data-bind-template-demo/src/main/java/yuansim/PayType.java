package yuansim;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/1/3
 */
public enum PayType {

    ali_pay(1,"ali_pay"),
    wx_pay(2,"wx_pay"),
    union_pay(3,"union_pay");


    private int code;
    private String name;
    PayType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }
}
