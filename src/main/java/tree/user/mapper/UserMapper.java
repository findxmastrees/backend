package tree.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import tree.user.dto.KakaoTokenResponseDto;

@Mapper
public interface UserMapper {

    KakaoTokenResponseDto getKaokaoLoginInfo();
}
