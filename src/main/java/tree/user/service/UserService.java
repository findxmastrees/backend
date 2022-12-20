package tree.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tree.config.AuthDto;
import tree.user.dto.KakaoTokenRequestDto;
import tree.user.dto.KakaoTokenResponseDto;
import tree.user.mapper.UserMapper;

import java.sql.SQLException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserMapper userMapper;
    public int createUserInfo(AuthDto authDto) throws SQLException, Exception{
        return userMapper.createUserInfo(authDto);
    }
    public AuthDto getUserInfo(String userId) throws SQLException,Exception {
        return userMapper.getUserInfo(userId);
    }
}
