package com.bazl.lims.common;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class Constants {

    //中心用户
    public static final String LOGIN_TYPE_CENTER = "center";

    public static final String FLAG_TRUE = "1";
    public static final String FLAG_FALSE= "0";

    //状态
    //未开始
    public static final String STATE_0 = "0";
    //已开始
    public static final String STATE_1 = "1";
    //已完成
    public static final String STATE_2 = "2";

    public static final String OPERATE_TYPE_ADD = "1";
    public static final String OPERATE_TYPE_EDIT = "2";
    public static final String OPERATE_TYPE_DEL = "3";

    //是否拼板
    public static final String IS_SPELL_YES = "YES";
    public static final String IS_SPELL_NO = "NO";

    //是否已经创建实验,0：未创建，1已创建
    public static final String IS_CREATE_0 = "0";
    public static final String IS_CREATE_1 = "1";

    //实验阶段
    //提取
    public static final String STAGE_TQ = "tq";
    //扩增
    public static final String STAGE_KZ = "kz";
    //电泳
    public static final String STAGE_SY = "sy";
    //其他
    public static final String STAGE_QT = "qt";

    //菜单
    public static final String MENU_TYPE = "menu";
    //字典
    public static final String DICR_TYPE = "dict";
    //24孔
    public static final String HOLE_NUM_24 = "24";
    //96孔
    public static final String HOLE_NUM_96 = "96";

    /*  DICT */
    //默认洗脱体积
    public static final String DICT_TPYE_ELUTION_DEFAULT = "ELUTION_DEFAULT";
    //检材属性
    public static final String DICT_TPYE_SAMPLE_PROPERTY = "SAMPLE_PROPERTY";
    //设备类型
    public static final String DICT_TPYE_DEVICE_TYPE = "DEVICE_TYPE";
    //确证方法
    public static final String DICT_TPYE_CONFIRMATORY_METHOD = "CONFIRMATORY_METHOD";
    //预实验方法
    public static final String DICT_TPYE_PRE_EXPERIMENTAL_METHOD = "PRE_EXPERIMENTAL_METHOD";
    //检材转移方法
    public static final String DICT_TPYE_SAMPLE_TRANSFER_METHOD = "SAMPLE_TRANSFER_METHOD";
    //程序名称方法
    public static final String DICT_TPYE_PROGRAM_NAME = "PROGRAM_NAME";

    public static final String[] POSTION_ARR = new String[]{
            "A01","A02","A03","A04","A05","A06","B01","B02","B03","B04","B05","B06",
            "C01","C02","C03","C04","C05","C06","D01","D02","D03","D04","D05","D06",
            "A01","A02","A03","A04","A05","A06","B01","B02","B03","B04","B05","B06",
            "C01","C02","C03","C04","C05","C06","D01","D02","D03","D04","D05","D06",
            "A01","A02","A03","A04","A05","A06","B01","B02","B03","B04","B05","B06",
            "C01","C02","C03","C04","C05","C06","D01","D02","D03","D04","D05","D06",
            "A01","A02","A03","A04","A05","A06","B01","B02","B03","B04","B05","B06",
            "C01","C02","C03","C04","C05","C06","D01","D02","D03","D04","D05","D06"
    };

    public static final Integer[] POSTION_HORIZONTAL_NUMBER = new Integer[]{
            1,2,9,10,17,18,3,4,11,12,19,20,
            5,6,13,14,21,22,7,8,15,16,23,24,
            25,26,33,34,41,42,27,28,35,36,43,44,
            29,30,37,38,45,46,31,32,39,40,47,48,
            49,50,57,58,65,66,51,52,59,60,67,68,
            53,54,61,62,69,70,55,56,63,64,71,72,
            73,74,81,82,89,90,75,76,83,84,91,92,
            77,78,85,86,93,94,79,80,87,88,95,96
    };

    public static final String[] POSTION_ARR_ALL = new String[]{
            "A01","A02","A03","B01","B02","B03","C01","C02","C03","D01","D02","D03",
            "E01","E02","E03","F01","F02","F03","G01","G02","G03","H01","H02","H03",
            "A04","A05","A06","B04","B05","B06","C04","C05","C06","D04","D05","D06",
            "E04","E05","E06","F04","F05","F06","G04","G05","G06","H04","H05","H06",
            "A07","A08","A09","B07","B08","B09","C07","C08","C09","D07","D08","D09",
            "E07","E08","E09","F07","F08","F09","G07","G08","G09","H07","H08","H09",
            "A10","A11","A12","B10","B11","B12","C10","C11","C12","D10","D11","D12",
            "E10","E11","E12","F10","F11","F12","G10","G11","G12","H10","H11","H12"
    };

    public static final Integer[] POSTION_HORIZONTAL_NUMBER_ALL = new Integer[]{
            1,9,17,2,10,18,3,11,19,4,12,20,
            5,13,21,6,14,22,7,15,23,8,16,24,
            25,33,41,26,34,42,27,35,43,28,36,44,
            29,37,45,30,38,46,31,39,47,32,40,48,
            49,57,65,50,58,66,51,59,67,52,60,68,
            53,61,69,54,62,70,55,63,71,56,64,72,
            73,81,89,74,82,90,75,83,91,76,84,92,
            77,85,93,78,86,94,79,87,95,80,88,96
    };

    public static final String[] SYTABLE_POSTION_ARR = new String[]{
            "A01","A02","A03","A04","A05","A06","A07","A08","A09","A10","A11","A12",
            "B01","B02","B03","B04","B05","B06","B07","B08","B09","B10","B11","B12",
            "C01","C02","C03","C04","C05","C06","C07","C08","C09","C10","C11","C12",
            "D01","D02","D03","D04","D05","D06","D07","D08","D09","D10","D11","D12",
            "E01","E02","E03","E04","E05","E06","E07","E08","E09","E10","E11","E12",
            "F01","F02","F03","F04","F05","F06","F07","F08","F09","F10","F11","F12",
            "G01","G02","G03","G04","G05","G06","G07","G08","G09","G10","G11","G12",
            "H01","H02","H03","H04","H05","H06","H07","H08","H09","H10","H11","H12"
    };

    public static final String[] SYTABLE_POSTION_HORIZONTAL_ARR = new String[]{
            "A01","E01","A02","E02","A03","E03","B01","F01","B02","F02","B03","F03",
            "C01","G01","C02","G02","C03","G03","D01","H01","D02","H02","D03","H03",
            "A04","E04","A05","E05","A06","E06","B04","F04","B05","F05","B06","F06",
            "C04","G04","C05","G05","C06","G06","D04","H04","D05","H05","D06","H06",
            "A07","E07","A08","E08","A09","E09","B07","F07","B08","F08","B09","F09",
            "C07","G07","C08","G08","C09","G09","D07","H07","D08","H08","D09","H09",
            "A10","E10","A11","E11","A12","E12","B10","F10","B11","F11","B12","F12",
            "C10","G10","C11","G11","C12","G12","D10","H10","D11","H11","D12","H12"
    };

    public static final Integer[] SYTABLE_POSTION_HORIZONTAL_NUMBER = new Integer[]{
            1,5,9,13,17,21,2,6,10,14,18,22,
            3,7,11,15,19,23,4,8,12,16,20,24,
            25,29,33,37,41,45,26,30,34,38,42,46,
            27,31,35,39,43,47,28,32,36,40,44,48,
            49,53,57,61,65,69,50,54,58,62,66,70,
            51,55,59,63,67,71,52,56,60,64,68,72,
            73,77,81,85,89,93,74,78,82,86,90,94,
            75,79,83,87,91,95,76,80,84,88,92,96
    };

    public static final String[] SYTABLE_POSTION_ARR_VER = new String[]{
            "A01","B01","C01","D01","E01","F01","G01","H01",
            "A02","B02","C02","D02","E02","F02","G02","H02",
            "A03","B03","C03","D03","E03","F03","G03","H03",
            "A04","B04","C04","D04","E04","F04","G04","H04",
            "A05","B05","C05","D05","E05","F05","G05","H05",
            "A06","B06","C06","D06","E06","F06","G06","H06",
            "A07","B07","C07","D07","E07","F07","G07","H07",
            "A08","B08","C08","D08","E08","F08","G08","H08",
            "A09","B09","C09","D09","E09","F09","G09","H09",
            "A10","B10","C10","D10","E10","F10","G10","H10",
            "A11","B11","C11","D11","E11","F11","G11","H11",
            "A12","B12","C12","D12","E12","F12","G12","H12"
    };

    public static final Integer[] SYTABLE_POSTION_ARR_VER_NUM = new Integer[]{
            1,2,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,
            33,34,35,36,37,38,39,40,
            41,42,43,44,45,46,47,48,
            49,50,51,52,53,54,55,56,
            57,58,59,60,61,62,63,64,
            65,66,67,68,69,70,71,72,
            73,74,75,76,77,78,79,80,
            81,82,83,84,85,86,87,88,
            89,90,91,92,93,94,95,96
    };

    public static final String[] PCRTABLE_POSTION_ARR_VER = new String[]{
            "A01","A04","A07","A10",
            "B01","B04","B07","B10",
            "C01","C04","C07","C10",
            "D01","D04","D07","D10",
            "E01","E04","E07","E10",
            "F01","F04","F07","F10",
            "G01","G04","G07","G10",
            "H01","H04","H07","H10",
            "A02","A05","A08","A11",
            "B02","B05","B08","B11",
            "C02","C05","C08","C11",
            "D02","D05","D08","D11",
            "E02","E05","E08","E11",
            "F02","F05","F08","F11",
            "G02","G05","G08","G11",
            "H02","H05","H08","H11",
            "A03","A06","A09","A12",
            "B03","B06","B09","B12",
            "C03","C06","C09","C12",
            "D03","D06","D09","D12",
            "E03","E06","E09","E12",
            "F03","F06","F09","F12",
            "G03","G06","G09","G12",
            "H03","H06","H09","H12"
    };

    public final static String[] HORIZONTAL_SYTABLE_POSTION_ARR = {
            "01",
            "02",
            "03",
            "04",
            "05",
            "06",
            "07",
            "08",
            "09",
            "10",
            "11",
            "12"
    };


    //样本表序号
    public final static String SAMPLE_PLATE_SORT_1 = "1";

    public final static String DICT_01 = "01";

    //表
    public final static String SAMPLE_TABLE = "SAMPLE_TABLE";
    public final static String SAMPLE_EXTRACT = "SAMPLE_EXTRACT";
    public final static String SAMPLE_PCR = "SAMPLE_PCR";
    public final static String SAMPLE_SY = "SAMPLE_SY";

    //背景色
    public final static String COLOR_LIGHT_CORNFLOWER_BLUE = "LIGHT_CORNFLOWER_BLUE";
    public final static String COLOR_LIGHT_BLUE = "LIGHT_BLUE";

    //微量、常量分界
    public final static int TRACE_CONSTANT_LIMIT = 50;

}
