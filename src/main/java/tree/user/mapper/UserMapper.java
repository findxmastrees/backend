package tree.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import tree.config.AuthDto;
import tree.user.dto.KakaoTokenResponseDto;

import java.sql.SQLException;

@Mapper
public interface UserMapper {

    int createUserInfo(AuthDto authDto) throws SQLException, Exception;
    AuthDto getUserInfo(String userId) throws SQLException, Exception;

}
