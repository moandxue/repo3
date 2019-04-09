package itcast.dao;

import itcast.domain.Account;
import itcast.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IUserMapper {
    /**
     * 使用注解来代替映射配置文件的内容
     */
    @Select(value = {"select * from user"})
    @Results(id = "userMap", value = {
            /*@Result(id = true , property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property ="birthday",column = "birthday"),
            @Result(property ="sex",column = "sex"),
            @Result(property = "address",column = "address"),*/
            @Result(property = "accounts",column = "id",many = @Many(select = "itcast.dao.IAccountMapper.findAccount",fetchType = FetchType.LAZY))
    })
    public List<User> findUser();

    /**
     * 通过id查询用户
     */
    @Select(value = {"select * from user where id = #{id}"})
    @ResultMap(value = {"userMap"})
    public List<User> findUserById(Integer id);

}
