package tree.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tree.user.dto.KakaoLoginRequestDto;
import tree.user.dto.KakaoLoginResponseDto;
import tree.user.mapper.UserMapper;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    UserMapper userMapper;
    public KakaoLoginResponseDto getKaokaoLoginInfo(KakaoLoginRequestDto kakaoLoginRequestDto){
        return userMapper.getKaokaoLoginInfo();
    }
}
