package com.chris.demo.core.auto.mapper;

import com.chris.demo.core.auto.bankAllotSerialDO;
import com.chris.demo.core.auto.bankAllotSerialDOExample;
import com.chris.demo.core.auto.bankAllotSerialDOKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface bankAllotSerialDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    long countByExample(bankAllotSerialDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    int deleteByPrimaryKey(bankAllotSerialDOKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    int insert(bankAllotSerialDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    int insertSelective(bankAllotSerialDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    List<bankAllotSerialDO> selectByExampleWithBLOBs(bankAllotSerialDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    List<bankAllotSerialDO> selectByExample(bankAllotSerialDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    bankAllotSerialDO selectByPrimaryKey(bankAllotSerialDOKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    int updateByExampleSelective(@Param("record") bankAllotSerialDO record, @Param("example") bankAllotSerialDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") bankAllotSerialDO record, @Param("example") bankAllotSerialDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    int updateByExample(@Param("record") bankAllotSerialDO record, @Param("example") bankAllotSerialDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    int updateByPrimaryKeySelective(bankAllotSerialDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(bankAllotSerialDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_BANK_ALLOT_SERIAL
     *
     * @mbg.generated Tue Aug 21 16:27:54 CST 2018
     */
    int updateByPrimaryKey(bankAllotSerialDO record);
}