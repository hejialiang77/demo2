package com.chris.demo.core.auto.mapper;

import com.chris.demo.core.auto.SalarySummary;
import com.chris.demo.core.auto.SalarySummaryExample;
import org.apache.ibatis.annotations.Param;

public interface SalarySummaryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SALARY_SUMMARY
     *
     * @mbg.generated Tue Jan 22 11:47:27 CST 2019
     */
    int insert(SalarySummary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SALARY_SUMMARY
     *
     * @mbg.generated Tue Jan 22 11:47:27 CST 2019
     */
    int insertSelective(SalarySummary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SALARY_SUMMARY
     *
     * @mbg.generated Tue Jan 22 11:47:27 CST 2019
     */
    SalarySummary selectByPrimaryKey(String summaryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SALARY_SUMMARY
     *
     * @mbg.generated Tue Jan 22 11:47:27 CST 2019
     */
    int updateByExampleSelective(@Param("record") SalarySummary record, @Param("example") SalarySummaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SALARY_SUMMARY
     *
     * @mbg.generated Tue Jan 22 11:47:27 CST 2019
     */
    int updateByExample(@Param("record") SalarySummary record, @Param("example") SalarySummaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SALARY_SUMMARY
     *
     * @mbg.generated Tue Jan 22 11:47:27 CST 2019
     */
    int updateByPrimaryKeySelective(SalarySummary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SALARY_SUMMARY
     *
     * @mbg.generated Tue Jan 22 11:47:27 CST 2019
     */
    int updateByPrimaryKey(SalarySummary record);
}