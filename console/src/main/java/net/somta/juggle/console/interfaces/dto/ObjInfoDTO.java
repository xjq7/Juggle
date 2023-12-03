package net.somta.juggle.console.interfaces.dto;

import net.somta.juggle.console.domain.obj.vo.PropertyVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;

import java.util.List;

/**
 * @author Gavin
 */
public class ObjInfoDTO {

    private Long id;

    private String objCode;

    private String objName;

    private String objDesc;

    private List<PropertyVO> props;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjCode() {
        return objCode;
    }

    public void setObjCode(String objCode) {
        this.objCode = objCode;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjDesc() {
        return objDesc;
    }

    public void setObjDesc(String objDesc) {
        this.objDesc = objDesc;
    }

    public List<PropertyVO> getProps() {
        return props;
    }

    public void setProps(List<PropertyVO> props) {
        this.props = props;
    }
}
