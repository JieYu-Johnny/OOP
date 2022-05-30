package mapper;

import pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectByName(String name);
    int insertUser(@Param("name")String name,@Param("password")String password);
}
