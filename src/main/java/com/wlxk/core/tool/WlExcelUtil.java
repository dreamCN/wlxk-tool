package com.wlxk.core.tool;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * excel 工具类
 * <p>
 * 依赖 easyExcel fastjson lombok javax.servlet-api
 *
 * @author sxz
 * @date 2021/4/28 10:07
 */
@Slf4j
public class WlExcelUtil {

    /**
     * excel 读取
     * <p>
     * 采用阿里 easy excel 工具读取
     *
     * @param inputStream 输入流
     * @param beanType    类型
     * @return 结果
     */
    public static <T> List<T> simpleRead(InputStream inputStream, Class<T> beanType) {
        EasyExcelListener<T> listener = new EasyExcelListener<>();
        EasyExcel.read(inputStream, beanType, listener).sheet().doRead();
        return listener.getData();
    }

    /**
     * web导出
     *
     * @param response  response
     * @param fileName  文件名称
     * @param sheetName sheet名称
     * @param data      数据
     * @param beanType  类型
     * @param <T>       泛型
     */
    public static <T> void webExcelWrite(HttpServletResponse response, String fileName,
                                    String sheetName, List<T> data, Class<T> beanType) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileNameStr = null;
        try {
            fileNameStr = URLEncoder.encode(fileName, "UTF-8");
        } catch (Exception e) {
            log.error("", e);
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileNameStr + ".xlsx");
        try {
            EasyExcel.write(response.getOutputStream(), beanType).sheet(sheetName).doWrite(data);
        } catch (Exception e) {
            log.error("WlExcelUtil webExcel excel导出失败", e);
            throw e;
        }
    }
}
