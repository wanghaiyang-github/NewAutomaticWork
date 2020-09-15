package com.bazl.lims.manager.dao;

import com.bazl.lims.manager.model.po.DnaNoSeq;

/**
 * Created by Administrator on 2017/1/7.
 */
public interface SeqNoGenerateDao extends BaseDao<DnaNoSeq, String> {

    public int selectNextVal(String code);

    public int deleteCode(String code);

    /**
     * 根据code还原currentVal的值
     * @param code
     * @return
     */
    public int returnCurrentValByCode(String code);
}
