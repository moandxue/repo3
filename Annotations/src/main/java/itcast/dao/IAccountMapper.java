package itcast.dao;

import itcast.domain.Account;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IAccountMapper {

    /**
     * 根据用户id查询账户信息
     */
    @Select(value = {"select * from account where uid = #{id}"})
    public List<Account> findAccount();
}
