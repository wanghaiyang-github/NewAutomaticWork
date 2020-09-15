package com.bazl.lims.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.bazl.lims.common.Constants;
import com.bazl.lims.manager.model.po.*;
import com.bazl.lims.manager.model.vo.SampleInfoVo;
import com.bazl.lims.web.controller.BaseController;
import com.bazl.lims.web.datamodel.SampleInfoModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wanghaiyang
 * @date 2020/2/12.
 */
@Service
public class DownloadFileUtils extends BaseController {

    /**
     * 导入4孔样本表
     * @param request
     * @param file
     */
    public List<SampleInfoModel> importSampleTable(HttpServletRequest request, MultipartFile file) {
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<SampleInfoModel> sampleInfoModelList = null;
        String cellData = null;
        try {
            wb = readExcel(file);
            if(wb != null){
                //用来存放表中数据
                sampleInfoModelList = new ArrayList<>();
                //获取第一个sheet
                sheet = wb.getSheetAt(0);
                //获取最大行数
                int rownum = sheet.getPhysicalNumberOfRows();

                SampleInfoModel sampleInfoModel = new SampleInfoModel();

                row = sheet.getRow(2);
                SampleTable sampleTable = new SampleTable();

                //板号
                cellData = (String) getCellFormatValue(row.getCell(2));
                sampleTable.setBoardNo(cellData);

                //创建者
                cellData = (String) getCellFormatValue(row.getCell(4));
                sampleTable.setCreatePerson(cellData);

                //创建者
                cellData = (String) getCellFormatValue(row.getCell(6));
                if (StringUtils.isBlank(cellData)) {
                    sampleTable.setCreateDatetime(new Date());
                }else {
                    sampleTable.setCreateDatetime(DateUtils.stringToDate(cellData, DateUtils.MIN));
                }
                sampleInfoModel.setSampleTable(sampleTable);

                //获取最大列数
                int colnum = row.getPhysicalNumberOfCells();
                List<SampleInfo> sampleInfoList = new ArrayList<>();
                int count = 0;
                for (int i = 4; i < 8; i++) {
                    row = sheet.getRow(i);
                    if(row !=null){
                        for (int j= 2;j < 8; j++){
                            cellData = (String) getCellFormatValue(row.getCell(j));
                            if (StringUtils.isNotBlank(cellData)) {
                                SampleInfo sampleInfo = new SampleInfo();
                                sampleInfo.setSampleLocationSort(count+1);
                                sampleInfo.setSamplePlateLocation(Constants.SYTABLE_POSTION_ARR_VER[count]);
                                sampleInfo.setSampleNo(cellData);

                                sampleInfoList.add(sampleInfo);
                            }
                            count++;
                        }
                    }else{
                        break;
                    }
                }
                sampleInfoModel.setSampleInfoList(sampleInfoList);
                sampleInfoModelList.add(sampleInfoModel);
            }
            return sampleInfoModelList;
        } catch (Exception ex) {
            logger.error("导入Excel错误！", ex);
            return null;
        }
    }

    /**
     * 导出24孔样本表
     * @param request
     * @param response
     * @param sampleInfoVoList
     * @param sampleTable
     */
    public void exportSampleTable(HttpServletRequest request, HttpServletResponse response,
                                  List<SampleInfoVo> sampleInfoVoList, SampleTable sampleTable) {

        String templateFilePath = request.getServletContext().getRealPath("templates/sampleTable.xls");
        HSSFWorkbook workbook = null;

        try {
            FileInputStream fis = new FileInputStream(templateFilePath);
            workbook = new HSSFWorkbook(new POIFSFileSystem(fis));
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;

            int startRow = 2;
            int idx = 0;
            if (sampleTable != null) {
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(2);
                if (cell == null) {
                    cell = row.createCell(2);
                }
                cell.setCellValue(sampleTable.getBoardNo());

                cell = row.getCell(4);
                if (cell == null) {
                    cell = row.createCell(4);
                }
                cell.setCellValue(sampleTable.getCreatePerson());

                cell = row.getCell(6);
                if (cell == null) {
                    cell = row.createCell(6);
                }
                cell.setCellValue(DateUtils.dateToString(sampleTable.getCreateDatetime(), DateUtils.MIN));
            }

            List<SampleInfoModel> sampleInfoModelList = getSampleInfoGroup(sampleInfoVoList, 6);
            createRowCell(sampleInfoModelList, row, cell, sheet, 4, 0, workbook, Constants.SAMPLE_TABLE);

            String fileName = "导出24孔样本表_" + sampleTable.getBoardNo() + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".xls";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename="  + URLEncoder.encode(fileName, "utf-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception ex) {
            logger.error("导出Excel错误！", ex);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    /**
     * 导出csv文件
     * @param request
     * @param response
     * @param sampleInfoVoList
     * @param extractPlate
     */
    public void exportCSVFile (HttpServletRequest request, HttpServletResponse response,
                               List<SampleInfoVo> sampleInfoVoList, ExtractPlate extractPlate) {
        List<SampleInfoModel> sampleInfoModelList = getSampleInfoGroup(sampleInfoVoList, 12);
        String fileName = "导出CSV文件-" + extractPlate.getBoardNo() + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".csv";

        StringBuffer buffer = new StringBuffer();
        BufferedOutputStream bos = null;
        try {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO-8859-1"));

            bos = new BufferedOutputStream(response.getOutputStream());

            // 写入文件内容
            buffer.append("Contents of Purification plate(Sequential ID = 5),,,,,,,,,,,," + "\n");
            buffer.append(",,,,,,,,,,,," + "\n");
            buffer.append("Volume,,,,,,,,,,,," + "\n");
            buffer.append("SampleID,,,,,,,,,,,," + "\n");
            buffer.append(",,,,,,,,,,,," + "\n");
            buffer.append(",1,2,3,4,5,6,7,8,9,10,11,12" + "\n");

            if (ListUtils.isNotNullAndEmptyList(sampleInfoModelList)) {
                for (int i = 0; i < sampleInfoModelList.size(); i++) {
                    SampleInfoModel sampleInfoModel = sampleInfoModelList.get(i);
                    if (i == 0) {
                        sampleInfoModel.setParameter("A,270,270,270,270,270,270,270,270,270,270,0,0");
                    }else if (i == 1) {
                        sampleInfoModel.setParameter("B,270,270,270,270,270,270,270,270,270,270,0,0");
                    }else if (i == 2) {
                        sampleInfoModel.setParameter("C,270,270,270,270,270,270,270,270,270,270,0,0");
                    }else if (i == 3) {
                        sampleInfoModel.setParameter("D,270,270,270,270,270,270,270,270,270,270,0,0");
                    }else if (i == 4) {
                        sampleInfoModel.setParameter("E,270,270,270,270,270,270,270,270,270,270,0,0");
                    }else if (i == 5) {
                        sampleInfoModel.setParameter("F,270,270,270,270,270,270,270,270,270,270,0,0");
                    }else if (i == 6) {
                        sampleInfoModel.setParameter("G,270,270,270,270,270,270,270,270,270,270,0,0");
                    }else if (i == 7) {
                        sampleInfoModel.setParameter("H,270,270,270,270,270,270,270,270,270,270,0,0");
                    }
                }
            }

            for (SampleInfoModel sampleInfoModel : sampleInfoModelList) {
                buffer.append(sampleInfoModel.getParameter() + "\n");
                if (ListUtils.isNullOrEmptyList(sampleInfoModel.getSampleInfoVoList())) {
                    buffer.append(",,,,,,,,,,,," + "\n");
                }else {
                    String str = ",";
                    for (SampleInfoVo sampleInfoVo : sampleInfoModel.getSampleInfoVoList()) {
                        str += "\t"+ sampleInfoVo.getEntity().getSampleNo() + ",";
                    }
                    if (StringUtils.isNotBlank(str)) {
                        str = str.substring(0, str.length() - 1);
                    }
                    buffer.append(str + "\n");
                }
            }
            bos.write(buffer.toString().getBytes("UTF-8"));
            bos.flush();
        } catch (Exception e) {
            logger.error("生成CSV错误！", e);
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    /**
     * 导出上样文件
     * @param request
     * @param response
     * @param sampleInfoVoList
     * @param extractPlate
     */
    public void exportSampleFile(HttpServletRequest request, HttpServletResponse response,
                                         List<SampleInfoVo> sampleInfoVoList, ExtractPlate extractPlate) {

        StringBuffer buffer = new StringBuffer();
        BufferedOutputStream bos = null;
        try {
            String fileName = "导出上样文件_" + extractPlate.getBoardNo() + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".txt";

            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO-8859-1"));

            bos = new BufferedOutputStream(response.getOutputStream());

            buffer.append("3500 Plate Layout File Version 1.0" + "\n" + "\n");
            buffer.append("Plate Name\tApplication Type\tCapillary Length (cm)\tPolymer\tNumber of Wells\tOwner Name\tBarcode Number\tComments" + "\n");
            buffer.append((StringUtils.isBlank(extractPlate.getBoardNo()) ? "" : extractPlate.getBoardNo()) + "\t" +"HID" + "\t" + "36" + "\t" + "POP4" + "\t" + "96" + "\n" + "\n");
            buffer.append("Well\tSample Name\tAssay\tFile Name Convention\tResults Group\tSample Type\t");
            buffer.append("User Defined Field 1\tUser Defined Field 2\tUser Defined Field 3\tUser Defined Field 4\tUser Defined Field 5\tComments" + "\n");

            if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
                for (String positionStr : Constants.SYTABLE_POSTION_ARR_VER) {
                    for (SampleInfoVo sampleInfoVo : sampleInfoVoList) {
                        if (positionStr.equals(sampleInfoVo.getEntity().getExtractPlateLocation())) {
                            buffer.append(sampleInfoVo.getEntity().getExtractPlateLocation()
                                    + "\t" + sampleInfoVo.getEntity().getSampleNo()
                                    + "\t" + "HID" + "\t" + "HID" + "\t"
                                    + "HID" + "\t" + "Sample" + "\t" + "" + "\t" + "" + "\t"
                                    + "" + "\n");
                            break;
                        }
                    }
                }
            }

            bos.write(buffer.toString().getBytes("UTF-8"));
            bos.flush();
        } catch (Exception ex) {
            logger.error("生成上样表错误！", ex);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception ex) {
                }
            }
        }
    }
    /**
     * 导出提取样本表
     * @param request
     * @param response
     * @param sampleInfoVoList
     * @param extractPlate
     */
    public void exportExtractSampleTable(HttpServletRequest request, HttpServletResponse response,
                                         List<SampleInfoVo> sampleInfoVoList, ExtractPlate extractPlate) {

        String templateFilePath = request.getServletContext().getRealPath("templates/extractSampleTable.xls");
        HSSFWorkbook workbook = null;

        try {
            FileInputStream fis = new FileInputStream(templateFilePath);
            workbook = new HSSFWorkbook(new POIFSFileSystem(fis));
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;

            int startRow = 2;
            int idx = 0;
            if (extractPlate != null) {
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(3);
                if (cell == null) {
                    cell = row.createCell(3);
                }
                cell.setCellValue(extractPlate.getBoardNo());

                cell = row.getCell(9);
                if (cell == null) {
                    cell = row.createCell(9);
                }
                cell.setCellValue(extractPlate.getCreatePerson());

                idx++;
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(3);
                if (cell == null) {
                    cell = row.createCell(3);
                }

                cell.setCellValue(extractPlate.getExtractMethod());
                cell = row.getCell(9);
                if (cell == null) {
                    cell = row.createCell(9);
                }
                cell.setCellValue(DateUtils.dateToString(extractPlate.getCreateDatetime(), DateUtils.MIN));
            }
            List<SampleInfoModel> sampleInfoModelList = getSampleInfoGroup(sampleInfoVoList, 12);
            createRowCell(sampleInfoModelList, row, cell, sheet, 5, 0, workbook, Constants.SAMPLE_EXTRACT);

            String fileName = "导出DNA提取样本表_" + extractPlate.getBoardNo() + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".xls";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename="  + URLEncoder.encode(fileName, "utf-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception ex) {
            logger.error("导出Excel错误！", ex);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    /**
     * 导出提取样本记录表
     * @param request
     * @param response
     * @param sampleInfoVoList
     * @param extractPlate
     */
    public void exportExtractSampleRecord(HttpServletRequest request, HttpServletResponse response,
                                         List<SampleInfoVo> sampleInfoVoList, ExtractPlate extractPlate) {

        String templateFilePath = request.getServletContext().getRealPath("templates/extractSampleRecord.xls");
        HSSFWorkbook workbook = null;

        try {
            FileInputStream fis = new FileInputStream(templateFilePath);
            workbook = new HSSFWorkbook(new POIFSFileSystem(fis));
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            HSSFCellStyle cellStyle = createCellStyle(workbook);

            int startRow = 2;
            int idx = 0;
            if (extractPlate != null) {
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(2);
                if (cell == null) {
                    cell = row.createCell(2);
                }
                cell.setCellValue(extractPlate.getBoardNo());

                cell = row.getCell(6);
                if (cell == null) {
                    cell = row.createCell(6);
                }
                if (StringUtils.isNotBlank(extractPlate.getOperationPerson())) {
                    cell.setCellValue(extractPlate.getOperationPerson());
                }else {
                    cell.setCellValue(extractPlate.getCreatePerson());
                }

                idx++;
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(2);
                if (cell == null) {
                    cell = row.createCell(2);
                }
                cell.setCellValue(extractPlate.getExtractMethod());

                cell = row.getCell(6);
                if (cell == null) {
                    cell = row.createCell(6);
                }
                cell.setCellValue(DateUtils.dateToString(extractPlate.getCreateDatetime(), DateUtils.MIN));
            }
            startRow = 5;
            idx = 0;
           for (SampleInfoVo sampleInfoVo : sampleInfoVoList) {
               row = sheet.getRow(startRow + idx);
               if (row == null) {
                   row = sheet.createRow(startRow + idx);
               }

               cell = row.getCell(1);
               if (cell == null) {
                   cell = row.createCell(1);
               }
               cell.setCellStyle(cellStyle);
               cell.setCellValue(sampleInfoVo.getEntity().getSampleNo());

               cell = row.getCell(2);
               if (cell == null) {
                   cell = row.createCell(2);
               }
               cell.setCellStyle(cellStyle);
               cell.setCellValue(sampleInfoVo.getEntity().getExtractPlateLocation());

               cell = row.getCell(3);
               if (cell == null) {
                   cell = row.createCell(3);
               }
               cell.setCellStyle(cellStyle);
               cell.setCellValue(sampleInfoVo.getPreExperimentalMethodName());

               cell = row.getCell(4);
               if (cell == null) {
                   cell = row.createCell(4);
               }
               cell.setCellStyle(cellStyle);
               cell.setCellValue(sampleInfoVo.getConfirmatoryMethodName());

               cell = row.getCell(5);
               if (cell == null) {
                   cell = row.createCell(5);
               }
               cell.setCellStyle(cellStyle);
               cell.setCellValue(sampleInfoVo.getSampleTransferMethodName());

               cell = row.getCell(6);
               if (cell == null) {
                   cell = row.createCell(6);
               }
               cell.setCellStyle(cellStyle);
               cell.setCellValue(sampleInfoVo.getElutionName());

               cell = row.getCell(7);
               if (cell == null) {
                   cell = row.createCell(7);
               }
               cell.setCellStyle(cellStyle);
               cell.setCellValue(sampleInfoVo.getSamplePropertyName());

               idx++;
           }

            String fileName = "导出样本提取记录表_" + extractPlate.getBoardNo() + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".xls";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename="  + URLEncoder.encode(fileName, "utf-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception ex) {
            logger.error("导出Excel错误！", ex);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    /**
     * 导出PCR扩增样本表
     * @param request
     * @param response
     * @param sampleInfoVoList
     * @param pcrPlate
     */
    public void exportPcrSampleRecord(HttpServletRequest request, HttpServletResponse response,
                                         List<SampleInfoVo> sampleInfoVoList, PcrPlate pcrPlate) {

        String templateFilePath = request.getServletContext().getRealPath("templates/pcrSampleRecord.xls");
        HSSFWorkbook workbook = null;

        try {
            FileInputStream fis = new FileInputStream(templateFilePath);
            workbook = new HSSFWorkbook(new POIFSFileSystem(fis));
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;

            int startRow = 2;
            int idx = 0;
            if (pcrPlate != null) {
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(3);
                if (cell == null) {
                    cell = row.createCell(3);
                }
                cell.setCellValue(pcrPlate.getBoardNo());

                cell = row.getCell(9);
                if (cell == null) {
                    cell = row.createCell(9);
                }
                cell.setCellValue(pcrPlate.getCreatePerson());

                idx++;
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(3);
                if (cell == null) {
                    cell = row.createCell(3);
                }

                cell.setCellValue(pcrPlate.getTestSystem());
                cell = row.getCell(9);
                if (cell == null) {
                    cell = row.createCell(9);
                }
                cell.setCellValue(pcrPlate.getPcrSystemTrace());

                idx++;
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(3);
                if (cell == null) {
                    cell = row.createCell(3);
                }

                cell.setCellValue(pcrPlate.getPcrRunNum());
                cell = row.getCell(9);
                if (cell == null) {
                    cell = row.createCell(9);
                }
                cell.setCellValue(pcrPlate.getPcrSystemConstant());

                idx++;
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(3);
                if (cell == null) {
                    cell = row.createCell(3);
                }

                cell.setCellValue(pcrPlate.getPcrInstrumentNum());
                cell = row.getCell(9);
                if (cell == null) {
                    cell = row.createCell(9);
                }
                cell.setCellValue(pcrPlate.getReagentBatch());
            }
            List<SampleInfoModel> sampleInfoModelList = getSampleInfoGroup(sampleInfoVoList, 12);
            createRowCell(sampleInfoModelList, row, cell, sheet, 8, 0, workbook, Constants.SAMPLE_PCR);

            String fileName = "导出PCR扩增样本表_" + pcrPlate.getBoardNo() + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".xls";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename="  + URLEncoder.encode(fileName, "utf-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception ex) {
            logger.error("导出Excel错误！", ex);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    /**
     * 导出DNA检验记录扩增作业单
     * @param request
     * @param response
     * @param resultList
     * @param pcrPlate
     */
    public void exportWorkFile(HttpServletRequest request, HttpServletResponse response,
                               List<SampleInfoModel> resultList, PcrPlate pcrPlate) {
        Map<String, Object> params = new HashMap<>();

        params.put("pcrPlate", pcrPlate);
        params.put("resultList", resultList);
        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            ServletContext servletContext = request.getSession().getServletContext();
            cfg.setServletContextForTemplateLoading(servletContext, "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("exportWorkFile.ftl", "UTF-8");

            String filename = "（导出DNA检验记录扩增作业单）DNA检测记录扩增作业单_"+ pcrPlate.getBoardNo() + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1"));//文件头，导出的文件名
            response.setContentType("application/x-msdownload");//类型

            tmp.process(params, response.getWriter());
        } catch (Exception ex) {
            logger.error("", ex);
        }
    }

    /**
     * 导出STR电泳样本表
     * @param request
     * @param response
     * @param sampleInfoVoList
     * @param syPlate
     */
    public void exportSySampleRecord(HttpServletRequest request, HttpServletResponse response,
                                         List<SampleInfoVo> sampleInfoVoList,SyPlate syPlate) {

        String templateFilePath = request.getServletContext().getRealPath("templates/sySampleRecord.xls");
        HSSFWorkbook workbook = null;

        try {
            FileInputStream fis = new FileInputStream(templateFilePath);
            workbook = new HSSFWorkbook(new POIFSFileSystem(fis));
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;

            int startRow = 2;
            int idx = 0;
            if (syPlate != null) {
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(3);
                if (cell == null) {
                    cell = row.createCell(3);
                }
                cell.setCellValue(syPlate.getBoardNo());

                cell = row.getCell(9);
                if (cell == null) {
                    cell = row.createCell(9);
                }
                cell.setCellValue(syPlate.getCreatePerson());

                idx++;
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(3);
                if (cell == null) {
                    cell = row.createCell(3);
                }

//                cell.setCellValue();
                cell = row.getCell(9);
                if (cell == null) {
                    cell = row.createCell(9);
                }
                cell.setCellValue(syPlate.getFirstInstrumentNum());

                idx++;
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(3);
                if (cell == null) {
                    cell = row.createCell(3);
                }

                cell.setCellValue(syPlate.getMolecularWeightMarker());
                cell = row.getCell(9);
                if (cell == null) {
                    cell = row.createCell(9);
                }
                cell.setCellValue(syPlate.getMixingRatio());

                idx++;
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(3);
                if (cell == null) {
                    cell = row.createCell(3);
                }

                cell.setCellValue(syPlate.getDenaturationCondition());
                cell = row.getCell(9);
                if (cell == null) {
                    cell = row.createCell(9);
                }
                cell.setCellValue(syPlate.getSySystem());

                idx++;
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }

                cell = row.getCell(9);
                if (cell == null) {
                    cell = row.createCell(9);
                }
                cell.setCellValue(syPlate.getEnvironmentTemperature());
            }
            List<SampleInfoModel> sampleInfoModelList = getSampleInfoGroup(sampleInfoVoList, 12);
            createRowCell(sampleInfoModelList, row, cell, sheet, 9, 0, workbook, Constants.SAMPLE_SY);

            String fileName = "导出STR电泳样本表_" + syPlate.getBoardNo() + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".xls";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename="  + URLEncoder.encode(fileName, "utf-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception ex) {
            logger.error("导出Excel错误！", ex);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    /**
     * 导出检材信息
     * @param request
     * @param response
     * @param sampleInfoVoList
     */
    public void exportSampleInfoRecord(HttpServletRequest request, HttpServletResponse response,
                                          List<SampleInfoVo> sampleInfoVoList) {

        String templateFilePath = request.getServletContext().getRealPath("templates/sampleInfoRecord.xls");
        HSSFWorkbook workbook = null;

        try {
            FileInputStream fis = new FileInputStream(templateFilePath);
            workbook = new HSSFWorkbook(new POIFSFileSystem(fis));
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            HSSFCellStyle cellStyle = createCellStyle(workbook);

            int startRow = 2;
            int idx = 0;
            String boardNo = "";
            if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
                SampleInfo sampleInfo = sampleInfoVoList.get(0).getEntity();
                if (sampleInfo != null) {
                    boardNo = sampleInfo.getBoardNo();

                    row = sheet.getRow(startRow + idx);
                    if (row == null) {
                        row = sheet.createRow(startRow + idx);
                    }

                    cell = row.getCell(2);
                    if (cell == null) {
                        cell = row.createCell(2);
                    }
                    cell.setCellValue(sampleInfo.getCreatePerson());
                }

                startRow = 4;
                idx = 0;
                for (SampleInfoVo sampleInfoVo : sampleInfoVoList) {
                    row = sheet.getRow(startRow + idx);
                    if (row == null) {
                        row = sheet.createRow(startRow + idx);
                    }

                    cell = row.getCell(1);
                    if (cell == null) {
                        cell = row.createCell(1);
                    }
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(sampleInfoVo.getEntity().getBoardNo());

                    cell = row.getCell(2);
                    if (cell == null) {
                        cell = row.createCell(2);
                    }
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(sampleInfoVo.getEntity().getSampleNo());

                    cell = row.getCell(3);
                    if (cell == null) {
                        cell = row.createCell(3);
                    }
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(sampleInfoVo.getEntity().getExtractPlateLocation());

                    cell = row.getCell(4);
                    if (cell == null) {
                        cell = row.createCell(4);
                    }
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(sampleInfoVo.getPreExperimentalMethodName());

                    cell = row.getCell(5);
                    if (cell == null) {
                        cell = row.createCell(5);
                    }
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(sampleInfoVo.getConfirmatoryMethodName());

                    cell = row.getCell(6);
                    if (cell == null) {
                        cell = row.createCell(6);
                    }
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(sampleInfoVo.getSampleTransferMethodName());

                    cell = row.getCell(7);
                    if (cell == null) {
                        cell = row.createCell(7);
                    }
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(sampleInfoVo.getElutionName());

                    cell = row.getCell(8);
                    if (cell == null) {
                        cell = row.createCell(8);
                    }
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(sampleInfoVo.getSamplePropertyName());

                    idx++;
                }
            }

            String fileName = "导出检材信息_" + boardNo + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".xls";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename="  + URLEncoder.encode(fileName, "utf-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception ex) {
            logger.error("导出Excel错误！", ex);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    private void createRowCell(List<SampleInfoModel> sampleInfoModelList, HSSFRow row, HSSFCell cell,
                               HSSFSheet sheet, int startRow, int idx, HSSFWorkbook workbook, String table) {
        if (ListUtils.isNotNullAndEmptyList(sampleInfoModelList)) {
            HSSFCellStyle cellStyle = null;
            for (SampleInfoModel sampleInfoModel : sampleInfoModelList) {
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }
                for (int i = 0; i < sampleInfoModel.getSampleInfoVoList().size(); i++) {
                    int count = i + 2;
                    cell = row.getCell(count);
                    if (cell == null) {
                        cell = row.createCell(count);
                    }
                    SampleInfoVo sample = sampleInfoModel.getSampleInfoVoList().get(i);
                    if (sample != null) {
                        if (Constants.SAMPLE_TABLE.equals(table) || Constants.SAMPLE_EXTRACT.equals(table)
                                || Constants.SAMPLE_PCR.equals(table) || Constants.SAMPLE_SY.equals(table)) {
                            if (StringUtils.isNotBlank((sample.getElutionName()))) {
                                int elution = Integer.parseInt(sample.getElutionName());
                                //大于50是常量，小于50是微量
                                if (elution > Constants.TRACE_CONSTANT_LIMIT) {
                                    cellStyle = getCellStyle(workbook, Constants.COLOR_LIGHT_BLUE);
                                }else {
                                    cellStyle = getCellStyle(workbook, Constants.COLOR_LIGHT_CORNFLOWER_BLUE);
                                }
                                cell.setCellStyle(cellStyle);
                            }
                        }
                        cell.setCellValue(sample.getEntity().getSampleNo());
                    }
                }

                idx++;
            }
        }
    }

    public HSSFCellStyle createCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);

        return cellStyle;
    }

    /**
     * 设置背景色
     * @param workbook
     * @return
     */
    public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook, String color){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        if (color.equals(Constants.COLOR_LIGHT_CORNFLOWER_BLUE)) {
            style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
        }else if (color.equals(Constants.COLOR_LIGHT_BLUE)) {
            style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.index);
        }
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成另一个字体
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);

        return style;
    }

    public static List<SampleInfoModel> getSampleInfoGroup(List<SampleInfoVo> sampleInfoVoList, int count) {

        List<SampleInfoModel> resultList = null;
        if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
            resultList = new ArrayList<>();
            List<SampleInfoVo> strList = new ArrayList<>();

            for (int i = 0; i < sampleInfoVoList.size(); i++) {
                strList.add(sampleInfoVoList.get(i));

                if ((i + 1) % count == 0) {
                    SampleInfoModel sampleInfoModel = new SampleInfoModel();
                    sampleInfoModel.setSampleInfoVoList(strList);
                    resultList.add(sampleInfoModel);
                    strList = new ArrayList<>();
                }
            }

            if (ListUtils.isNotNullAndEmptyList(strList)) {
                SampleInfoModel sampleInfoModel = new SampleInfoModel();
                sampleInfoModel.setSampleInfoVoList(strList);
                resultList.add(sampleInfoModel);
            }
        }

        return resultList;
    }

    //读取excel
    public static Workbook readExcel(MultipartFile file){
        String filePath = file.getOriginalFilename();
        Workbook wb = null;
        if(filePath == null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = file.getInputStream();
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case NUMERIC:{
                    cellValue = DataFormat.convertDoubleToString(cell.getNumericCellValue());
                    break;
                }
                case FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

}
