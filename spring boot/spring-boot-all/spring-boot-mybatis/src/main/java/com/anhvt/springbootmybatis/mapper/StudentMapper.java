package com.anhvt.springbootmybatis.mapper;

import com.anhvt.springbootmybatis.model.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/24/2024
 */
@Component
@Mapper
public interface StudentMapper {
    @Insert("insert into STUDENT(sno,sname,ssex) values(#{sno},#{name},#{sex})")
    int add(Student student);

    @Update("update STUDENT set sname=#{name},ssex=#{sex} where sno=#{sno}")
    int update(Student student);

    @Delete("delete from STUDENT where sno=#{sno}")
    int deleteBysno(String sno);

    @Select("select * from STUDENT where sno=#{sno}")
    @Results(id = "student",value= {
            @Result(property = "sno", column = "sno", javaType = String.class),
            @Result(property = "name", column = "sname", javaType = String.class),
            @Result(property = "sex", column = "ssex", javaType = String.class)
    })
    Student queryStudentBySno(String sno);

    @Select("select * from STUDENT")
    List<Map<String, Object>> queryStudentList();
}
