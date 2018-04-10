package top.gongtao.dao;

import org.apache.ibatis.annotations.Param;
import top.gongtao.entity.SysAdminRule;
import top.gongtao.util.MyMapper;

import java.util.List;

public interface SysAdminRuleDao extends MyMapper<SysAdminRule> {

	List<SysAdminRule> selectInIds(@Param("ruleIds") String ruleIds, @Param("status") Integer status);

	List<SysAdminRule> selectByStatus(@Param("status") Integer status);
}