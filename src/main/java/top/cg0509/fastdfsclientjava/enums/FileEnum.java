package top.cg0509.fastdfsclientjava.enums;

/**
 * @author 陈赓
 * @version 2018/11/1/001
 */
public enum FileEnum {

    SUCCESS(0,"操作成功"),
    ERROR(-1,"操作失败,出现异常");


    private int state;

    private String stateInfo;

    private FileEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static FileEnum stateOf(int index){
        for (FileEnum state: values()){
            if (state.getState() == index){
                return state;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
