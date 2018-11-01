package top.cg0509.fastdfsclientjava.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.cg0509.fastdfsclientjava.entity.FileInformation;

import java.util.List;

/**
 * @author 陈赓
 * @version 2018/11/1/001
 */
@Mapper
public interface FileMapper {

    @Insert({"INSERT INTO tb_file(project_name,file_name,`type`,path) VALUES(#{projectName},#{fileName},#{type},#{path})"})
    int inserFile(FileInformation file);

    @Delete({"DELETE FROM tb_file WHERE  `path`=#{path}"})
    int deleteFileById(String path);

    @Select({"SELECT id,project_name,file_name,`type`,path FROM tb_file WHERE id = #{id}"})
    FileInformation getFileById(int id);

    @Select({"SELECT id,project_name,file_name,`type`,path FROM tb_file"})
    List<FileInformation> listFile();

}
