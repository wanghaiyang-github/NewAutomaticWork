package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.common.Constants;
import com.bazl.lims.manager.dao.SeqNoGenerateDao;
import com.bazl.lims.manager.model.po.DnaNoSeq;
import com.bazl.lims.manager.services.common.SeqNoGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/7.
 */
@Service
public class SeqNoGenerateServiceImpl implements SeqNoGenerateService {

    @Autowired
    SeqNoGenerateDao seqNoGenerateDao;

    private String getNextVal(String code) {
        int nextVal = seqNoGenerateDao.selectNextVal(code);
        if (nextVal == 0) {
            DnaNoSeq dns = new DnaNoSeq();
            dns.setCode(code);
            seqNoGenerateDao.insert(dns);
            nextVal = seqNoGenerateDao.selectNextVal(code);
        }

        return nextVal + "";
    }

    private String FullCharString(String src, int len)
    {
        int srcLen = src.length();
        if(srcLen >= len)
        {
            return src;
        }

        int needlen = len - srcLen;
        for (int i = 0; i < needlen; i++ )
        {
            src = "0" + src;
        }

        return src;
    }

    @Override
    public int deleteCode (String code) {
        return seqNoGenerateDao.deleteCode(code);
    }

    @Override
    public String getNextBoardNoVal(String code) {
        return code + getNextVal(code);
    }

    @Override
    public String getNextKZBoardNoVal(String code) {
        return code + getNextVal(code);
    }
    @Override
    public String getNextSYBoardNoVal(String code) {
        return code + getNextVal(code);
    }

    @Override
    public String getNextTestNoVal(String code) {
        return code + "-" + getNextVal(code);
    }

    @Override
    public String getNextSampleBoardNoVal(String code) {
        return code + "-" + FullCharString(getNextVal(code), 2);
    }

    @Override
    public boolean returnCurrentValByCode(String code) {
        boolean flag = true;
        try {
            seqNoGenerateDao.returnCurrentValByCode(code);
        }catch (Exception e) {
            flag = false;
            e.getStackTrace();
        }

        return flag;
    }
}
