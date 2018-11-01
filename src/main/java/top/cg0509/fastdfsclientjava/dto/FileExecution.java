package top.cg0509.fastdfsclientjava.dto;

import top.cg0509.fastdfsclientjava.entity.FileInformation;
import top.cg0509.fastdfsclientjava.enums.FileEnum;

import java.util.List;

/**
 * @author 陈赓
 * @version 2018/11/1/001
 */
public class FileExecution {

    private int state;

    private String stateInfo;

    private FileInformation file;

    private List<FileInformation> files;

    public FileExecution(FileEnum fileEnum){
        this.state = fileEnum.getState();
        this.stateInfo = fileEnum.getStateInfo();
    }
    public FileExecution(FileEnum fileEnum,FileInformation file){
        this.state = fileEnum.getState();
        this.stateInfo = fileEnum.getStateInfo();
        this.file = file;
    }
    public FileExecution(FileEnum fileEnum,List<FileInformation> files){
        this.state = fileEnum.getState();
        this.stateInfo = fileEnum.getStateInfo();
        this.files = files;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public FileInformation getFile() {
        return file;
    }

    public List<FileInformation> getFiles() {
        return files;
    }
}
