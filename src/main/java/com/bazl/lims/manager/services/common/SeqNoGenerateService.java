package com.bazl.lims.manager.services.common;

/**
 * Created by Administrator on 2017/1/7.
 */
public interface SeqNoGenerateService {

    public int deleteCode(String code);

    /**
     * 根据条件查询下一个
     * @param code
     * @return
     */
    public String getNextBoardNoVal(String code);

    /**
     * 根据条件查询扩增编号
     * @param code
     * @return
     */
    public String getNextKZBoardNoVal(String code);

    /**
     * 根据条件查询电泳编号
     * @param code
     * @return
     */
    public String getNextSYBoardNoVal(String code);

    /**
     * 根据条件查询下一个
     * @param code
     * @return
     */
    public String getNextTestNoVal(String code);

    /**
     * 根据条件查询下一个
     * @param code
     * @return
     */
    public String getNextSampleBoardNoVal(String code);

    /**
     * 根据code还原currentVal的值
     * @param code
     * @return
     */
    public boolean returnCurrentValByCode(String code);
}
