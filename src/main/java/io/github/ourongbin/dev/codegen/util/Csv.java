package io.github.ourongbin.dev.codegen.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Csv {
    public static void main(String[] args) {
        EasyExcel.read(new File("/Users/ronou/Documents/comments/goods_comments_zan.xlsx"), new NoModelDataListener())
                .sheet().doRead();
    }

    @Slf4j
    public static class NoModelDataListener extends AnalysisEventListener<Map<Integer, String>> {
        private static List<String> colNames = null;
        List<List<String>> rows = new ArrayList<>();


        @Override
        public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
            colNames = Lists.newArrayList(headMap.values());
            log.info("解析到表头:{}", colNames);
        }

        @Override
        public void invoke(Map<Integer, String> data, AnalysisContext context) {
            List<String> row = Lists.newArrayList(data.values());
            log.info("解析到数据:{}", row);
            rows.add(row);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            log.info("表头！{}", colNames);
            log.info("所有数据解析完成！{}", rows.size());

            List<List<String>> colValuesList = Lists.newArrayList();
            for (int m = 0; m < colNames.size(); m++) {
                colValuesList.add(Lists.newArrayList());
            }

            for (int i = 0; i < rows.size(); i++) {
                List<String> cells = rows.get(i);
                for (int j = 0; j < cells.size(); j++) {
                    String s = cells.get(j);
                    if (s == null) {
                        s = "\\N";
                    } else if (StringUtils.isBlank(s)) {
                        s = "\\B";
                    }
                    colValuesList.get(j).add(s);
                }
            }

            analyze(colNames, colValuesList);
        }

    }

    private static void analyze(List<String> colNames, List<List<String>> colValuesList) {
        Map<String, Map<String, Integer>> data = Maps.newHashMap();
        Map<String, Integer> data2 = Maps.newHashMap();
        for (int i = 0; i < colNames.size(); i++) {
            Map<String, Integer> counts = counts(colValuesList.get(i));
            data2.put(colNames.get(i), counts.size());
            data.put(colNames.get(i), sortCounts(counts));
        }

        for (String colName : colNames) {
            System.out.println(colName + " -- " + data2.get(colName) + " | " + data.get(colName));
        }
    }

    public static Map<String, Integer> counts(List<String> strings) {
        Map<String, Integer> counts = new HashMap<>();
        for (String s : strings) {
            if (counts.containsKey(s)) {
                Integer c = counts.get(s) + 1;
                counts.put(s, c);
            } else {
                counts.put(s, 1);
            }
        }
        return counts;
    }

    public static Map<String, Integer> sortCounts(Map<String, Integer> counts) {
        ValueComparator vc = new ValueComparator(counts);
        TreeMap<String, Integer> sortedByValueMap = new TreeMap<>(vc);
        sortedByValueMap.putAll(counts);

        Map<String, Integer> topN = Maps.newLinkedHashMap();
        int i = 0;
        for (Map.Entry<String, Integer> entry : sortedByValueMap.entrySet()) {
            topN.put(entry.getKey(), entry.getValue());
            i++;
            if (i == 3) {
                break;
            }
        }

        return topN;
    }

    static class ValueComparator implements Comparator<String> {
        private final Map<String, Integer> base;

        public ValueComparator(Map<String, Integer> base) {
            this.base = base;
        }

        @Override
        public int compare(String k1, String k2) {
            Integer val1 = base.get(k1);
            Integer val2 = base.get(k2);

            int num = val1.compareTo(val2);

            int result = num == 0 ? k1.compareTo(k2) : num;
            return -result;
        }
    }
}

