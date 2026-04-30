package jp.co.beattech.workflow.java.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import jp.co.beattech.workflow.java.app.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    // insert と update は JpaRepository の save() で代用可能なのでメソッドは不要

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.delFlg = :delFlg WHERE u.employeeNumber = :employeeNumber")
    void delete(@Param("employeeNumber") Integer employeeNumber, @Param("delFlg") int delFlg);

    User findByEmployeeNumber(Integer employeeNumber);
}