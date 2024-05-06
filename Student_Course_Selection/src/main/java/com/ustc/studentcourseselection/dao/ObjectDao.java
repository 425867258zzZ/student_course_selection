package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.BaseObject;


import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

/**
 * ÊµÏÖDao²ã¸¸Àà
 *
 * @author ÃÏè÷êÏ
 */
public interface ObjectDao {

    public boolean add(BaseObject baseObject);

    public boolean del(String number);

    public boolean update(BaseObject object);

    public BaseObject query(String number);

    public Vector<Vector> queryAll();


}
