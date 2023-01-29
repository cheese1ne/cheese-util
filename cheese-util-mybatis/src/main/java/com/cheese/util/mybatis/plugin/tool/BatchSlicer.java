package com.cheese.util.mybatis.plugin.tool;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.cheese.util.mybatis.plugin.core.CustomBaseMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 集合切片处理，防止大批量数据插入报错
 * sqlSession.flushStatements();预插入生成一个id
 * (tip：如不使用事务进行预插入处理，flushStatements()可能无效)
 *
 * @author sobann
 */
public class BatchSlicer {
    private BatchSlicer() {
    }

    private static SqlSession sqlSession;

    static {
        sqlSession = ApplicationContextHelper.getBean(SqlSession.class);
    }

    private static <T> List<Collection<T>> slice(Collection<T> collection) {
        return BatchSlicer.slice(collection, 1000);
    }

    private static <T> List<Collection<T>> slice(Collection<T> collection, int batchSize) {
        if (Objects.nonNull(collection) && collection.size() > 0 && batchSize > 0) {
            List<Collection<T>> sliceList = new ArrayList<>();
            int count = 0;
            while (count < collection.size()) {
                sliceList.add(new ArrayList<>(collection).subList(count, Math.min((count + batchSize), collection.size())));
                count += batchSize;
            }
            return sliceList;
        } else {
            throw new MybatisPlusException("pls passing valid param");
        }
    }

    private static <T> void saveBatch(CustomBaseMapper<T> baseMapper, List<Collection<T>> sliceList, SqlSession sqlSession) {
        for (Collection<T> slice : sliceList) {
            Integer integer = baseMapper.insertBatchSomeColumn(slice);
            if (integer <= 0) {
                throw new MybatisPlusException("batch insert error,pls inspect mysql env or log file");
            }
            if (Objects.nonNull(sqlSession)){
                sqlSession.flushStatements();
            }else {
                BatchSlicer.sqlSession.flushStatements();
            }
        }
    }

    public static <T> void saveBatch(CustomBaseMapper<T> baseMapper, Collection<T> collection) {
        BatchSlicer.saveBatch(baseMapper, collection, null);
    }

    public static <T> void saveBatch(CustomBaseMapper<T> baseMapper, Collection<T> collection, SqlSession sqlSession) {
        BatchSlicer.saveBatch(baseMapper, collection, 1000, sqlSession);
    }

    public static <T> void saveBatch(CustomBaseMapper<T> baseMapper, Collection<T> collection, int batchSize, SqlSession sqlSession) {
        List<Collection<T>> sliceList = BatchSlicer.slice(collection, batchSize);
        BatchSlicer.saveBatch(baseMapper, sliceList, sqlSession);
    }
}
