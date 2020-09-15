package com.bazl.lims.manager.dao.impl;

import com.bazl.lims.manager.dao.SeqNoGenerateDao;
import com.bazl.lims.manager.model.po.DnaNoSeq;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/1/7.
 */
@Repository
public class SeqNoGenerateDaoImpl extends BaseDaoImpl<DnaNoSeq, String> implements SeqNoGenerateDao {


    @Override
    public String namespace() {
        return DnaNoSeq.class.getName();
    }

    public int selectNextVal(String code) {
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectNextVal", code);
    }

    public int deleteCode(String code) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteCode", code);
    }

    public int returnCurrentValByCode(String code) {
        return this.getSqlSessionTemplate().update(this.namespace() + ".returnCurrentValByCode", code);
    }
}
