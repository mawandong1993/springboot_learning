package com.note.cloud_note.vo;

import vo.BaseVO;

/**
 * @author mawandong
 * @date 2018/3/29 22:41
 */
public class NoteBookVO extends BaseVO {

    private Integer cn_notebook_id;
    private Integer cn_user_id;
    private String cn_notebook_type_id;
    private String cn_notebook_name;

    public Integer getCn_notebook_id() {
        return cn_notebook_id;
    }

    public void setCn_notebook_id(Integer cn_notebook_id) {
        this.cn_notebook_id = cn_notebook_id;
    }

    public Integer getCn_user_id() {
        return cn_user_id;
    }

    public void setCn_user_id(Integer cn_user_id) {
        this.cn_user_id = cn_user_id;
    }

    public String getCn_notebook_type_id() {
        return cn_notebook_type_id;
    }

    public void setCn_notebook_type_id(String cn_notebook_type_id) {
        this.cn_notebook_type_id = cn_notebook_type_id;
    }

    public String getCn_notebook_name() {
        return cn_notebook_name;
    }

    public void setCn_notebook_name(String cn_notebook_name) {
        this.cn_notebook_name = cn_notebook_name;
    }
}
