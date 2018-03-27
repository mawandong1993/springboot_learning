package vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mawandong
 * @date 2018/3/28 0:47
 */
public class BaseVO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Date creatDate;
    private String creatTime;
    private String createBy;
    private Date updateDate;
    private String updateTime;
    private String updateBy;
    private String desc;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
