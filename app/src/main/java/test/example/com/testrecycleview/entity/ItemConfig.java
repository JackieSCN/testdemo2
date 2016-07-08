package test.example.com.testrecycleview.entity;

/**
 * Created by jackie.sun on 2015/12/1.
 */
public class ItemConfig {
    private int resId;
    private Class<?> cls;

    public ItemConfig(int resId, Class<?> cls) {
        this.resId = resId;
        this.cls = cls;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }
}
