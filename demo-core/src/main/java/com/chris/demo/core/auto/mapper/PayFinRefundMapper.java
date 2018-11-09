package com.chris.demo.core.auto.mapper;

import com.chris.demo.core.auto.PayFinRefund;
import com.chris.demo.core.auto.PayFinRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayFinRefundMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_PAY_FIN_REFUND
     *
     * @mbg.generated Wed Jul 18 14:21:44 CST 2018
     */
    long countByExample(PayFinRefundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_PAY_FIN_REFUND
     *
     * @mbg.generated Wed Jul 18 14:21:44 CST 2018
     */
    int deleteByPrimaryKey(Long serialNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_PAY_FIN_REFUND
     *
     * @mbg.generated Wed Jul 18 14:21:44 CST 2018
     */
    int insert(PayFinRefund record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_PAY_FIN_REFUND
     *
     * @mbg.generated Wed Jul 18 14:21:44 CST 2018
     */
    int insertSelective(PayFinRefund record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_PAY_FIN_REFUND
     *
     * @mbg.generated Wed Jul 18 14:21:44 CST 2018
     */
    List<PayFinRefund> selectByExample(PayFinRefundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_PAY_FIN_REFUND
     *
     * @mbg.generated Wed Jul 18 14:21:44 CST 2018
     */
    PayFinRefund selectByPrimaryKey(Long serialNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_PAY_FIN_REFUND
     *
     * @mbg.generated Wed Jul 18 14:21:44 CST 2018
     */
    int updateByExampleSelective(@Param("record") PayFinRefund record, @Param("example") PayFinRefundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_PAY_FIN_REFUND
     *
     * @mbg.generated Wed Jul 18 14:21:44 CST 2018
     */
    int updateByExample(@Param("record") PayFinRefund record, @Param("example") PayFinRefundExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_PAY_FIN_REFUND
     *
     * @mbg.generated Wed Jul 18 14:21:44 CST 2018
     */
    int updateByPrimaryKeySelective(PayFinRefund record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_PAY_FIN_REFUND
     *
     * @mbg.generated Wed Jul 18 14:21:44 CST 2018
     */
    int updateByPrimaryKey(PayFinRefund record);
}