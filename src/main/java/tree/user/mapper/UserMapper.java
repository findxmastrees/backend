package tree.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import tree.user.dto.KakaoLoginResponseDto;

@Mapper
public interface UserMapper {

    KakaoLoginResponseDto getKaokaoLoginInfo();
}
