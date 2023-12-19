package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userId){
        //todo#4-1 회원조회
        log.debug("getUser - 실행");
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        return null;
    }

    @Override
    public void saveUser(User user) throws UserAlreadyExistsException {
        //todo#4-2 회원등록

        if(userRepository.countByUserId(user.getUserId()) == 0){
            userRepository.save(user);
            log.info("회원을 저장했습니다.");
        }else {
            throw new UserAlreadyExistsException("이미 존재하는 회원입니다.");

        }

    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        if(userRepository.countByUserId(user.getUserId()) > 0){
            userRepository.update(user);
        }
    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        if(userRepository.countByUserId(userId) > 0){
            userRepository.deleteByUserId(userId);
        }
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회

        Optional<User> userOptional = userRepository.findByUserIdAndUserPassword(userId, userPassword);
        if(userOptional.isPresent() ) {
           User user = userOptional.get();
           userRepository.updateLatestLoginAtByUserId(user.getUserId(), LocalDateTime.now());
           return user;
        } else {
            throw new UserNotFoundException("로그인에 실패했습니다!");
        }
    }

    @Override
    public User doSignup(String userId, String userPassword, String userName, String userBirth, User.Auth userAuth, int userPoint, LocalDateTime userCreatedAt, LocalDateTime userLatest_login) throws UserAlreadyExistsException {
        User newUser = new User(userId, userPassword, userName, userBirth, User.Auth.ROLE_USER, userPoint, userCreatedAt, userLatest_login);
        return newUser;
    }

    @Override
    public boolean isAuthenticated(HttpSession session) {
        if(session != null){
            Object userIdObj = session.getAttribute("userId");
            return userIdObj != null;
        }
        return false;
    }


//    @Override
//    public User doSignup(String userId, String userPassword, String userName, String userBirth, User.Auth userAuth, int userPoint, LocalDateTime userCreatedAt, LocalDateTime userLatest_login) throws UserAlreadyExistsException{
//        User newUser = new User(userId, userPassword, userName, userBirth, User.Auth.ROLE_USER, userPoint, userCreatedAt, userLatest_login);
//        saveUser(newUser);
//        return newUser;
//    }

}
