package com.ustc.studentcourseselection.model;

import java.util.Objects;

/**
 * 专业类
 */
class Major {
    /**
     * The constant CS.
     */
    public static final String CS = "计科";
    /**
     * The constant MATH.
     */
    public static final String MATH = "数学";
    /**
     * The constant PSY.
     */
    public static final String PSY = "物理";
    /**
     * The constant CHEM.
     */
    public static final String CHEM = "化学";
    /**
     * The constant BIO.
     */
    public static final String BIO = "生物";
}

/**
 * 基本类
 *
 * @author 孟梓晗
 */
public class BaseObject {

    /**
     * The Id.
     */
    public int id;

    /**
     * The create time.
     */
    public String createTime;

    /**
     * The Update time.
     */
    public String updateTime;

    /**
     * Instantiates a new Base object.
     */
    public BaseObject() {
    }

    /**
     * Instantiates a new Base object.
     *
     * @param id         the id
     * @param createTime the creation time
     * @param updateTime the update time
     */
    public BaseObject(int id, String createTime, String updateTime) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time.
     *
     * @param createTime the create time
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets update time.
     *
     * @return the update time
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets update time.
     *
     * @param updateTime the update time
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BaseObject{" +
                "id=" + id +
                ", create_time=" + createTime +
                ", update_time=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseObject object = (BaseObject) o;
        return id == object.id && createTime == object.createTime && updateTime == object.updateTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime);
    }
}