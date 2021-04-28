package com.wlxk.core.tool;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sxz
 * @date 2021/4/28 10:07
 */
@Slf4j
public class EasyExcelListener<T> extends AnalysisEventListener<T> {

    private List<T> data = new ArrayList<>();

    @Override
    public void invoke(T item, AnalysisContext analysisContext) {
        log.debug("EasyExcel解析到一条数据:{}", JSON.toJSONString(item));
        data.add(item);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        /*if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }*/
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("EasyExcel所有数据解析完成！");
    }

    public List<T> getData() {
        return this.data;
    }
}
