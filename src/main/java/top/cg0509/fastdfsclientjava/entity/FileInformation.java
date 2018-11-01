package top.cg0509.fastdfsclientjava.entity;

import com.github.tobato.fastdfs.domain.GroupState;
import lombok.Data;

import javax.persistence.*;
import javax.swing.plaf.PanelUI;
import java.io.Serializable;
import java.util.List;

/**
 * @author 陈赓
 * @version 2018/11/1/001
 */
@Data
@Table(name = "tb_file")
@Entity
public class FileInformation implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column( name = "project_name")
    private String projectName;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "`type`")
    private String type;

    @Column(name = "path")
    private String path;

    @Transient
    private List<GroupState> groupStates;

    public FileInformation(){}
    public FileInformation(String projectName,String fileName, String type, String path){
        this.projectName = projectName;
        this.fileName = fileName;
        this.type = type;
        this.path = path;
    }
}
