package com.chris.demo.core.auto.mapper;

import com.chris.demo.core.auto.Xf04RecordDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * todo
 *
 * @ClassName Xf04RecordDao
 * @Author jlhe
 * @Date 2018/9/7
 * @Version 1.0
 */
@Slf4j
@Component
public class Xf04RecordDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

   public void batchInsert(List<Xf04RecordDO> list) {
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        Xf04RecordDOMapper mapper = session.getMapper(Xf04RecordDOMapper.class);

        try {
            for (int i = 0; i < list.size();  i++) {

                Xf04RecordDO recordDO =  list.get(i);
                mapper.insert(recordDO);
                if ((i+1) % 100 == 0 || i ==( list.size() - 1)) {
                    session.commit();
                    session.clearCache();
                }
            }
        } catch (Exception e) {
            log.error("异常了" , e);
            session.rollback();
        } finally {
            session.close();
        }

    }
}
