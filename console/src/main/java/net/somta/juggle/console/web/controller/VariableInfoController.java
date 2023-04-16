package net.somta.juggle.console.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.model.VariableInfo;
import net.somta.juggle.console.model.param.VariableParam;
import net.somta.juggle.console.service.IVariableInfoService;
import net.somta.juggle.core.model.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "变量接口")
@RestController
@RequestMapping("/v1/variable")
public class VariableInfoController {

    @Autowired
    private IVariableInfoService variableService;

    /**
     * 新增变量
     * @param variableParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "新增变量")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addVariable(@RequestBody VariableParam variableParam){
        if(variableParam == null){
            //todo 统一异常处理
        }
        VariableInfo variableInfo = new VariableInfo();
        variableInfo.setEnvKey(variableParam.getEnvKey());
        variableInfo.setEnvName(variableParam.getEnvName());
        variableInfo.setDataType(JsonSerializeHelper.serialize(variableParam.getDataType()));
        variableInfo.setFlowDefinitionId(variableParam.getFlowDefinitionId());
        Boolean result = variableService.addVariable(variableInfo);
        return ResponseDataResult.setResponseResult(result);
    }

    /**
     * 删除变量
     * @param flowDefinitionId 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "删除变量")
    @DeleteMapping("/delete/{flowDefinitionId}/{envId}")
    public ResponseDataResult<Boolean> deleteVariable(@PathVariable Long flowDefinitionId, @PathVariable Long envId){
        // todo 条件判断
        Boolean result = variableService.deleteVariable(flowDefinitionId, envId);
        return ResponseDataResult.setResponseResult(result);
    }

    /**
     * 修改
     * @param variableParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "修改变量")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateVariable(@RequestBody VariableParam variableParam){
        if(variableParam == null){
            //todo 统一异常处理
        }
        VariableInfo variableInfo = new VariableInfo();
        variableInfo.setId(variableParam.getId());
        variableInfo.setEnvKey(variableParam.getEnvKey());
        variableInfo.setEnvName(variableParam.getEnvName());
        variableInfo.setDataType(JsonSerializeHelper.serialize(variableParam.getDataType()));
        variableInfo.setFlowDefinitionId(variableParam.getFlowDefinitionId());
        Boolean result = variableService.updateVariable(variableInfo);
        return ResponseDataResult.setResponseResult(result);
    }


    /**
     * 修改
     * @return Boolean
     */
    @Operation(summary = "获取流程内的变量列表")
    @GetMapping("/list/{flowDefinitionId}")
    public ResponseDataResult<List<Variable>> getVariableInfoList(Long flowDefinitionId){
        List<Variable> list = variableService.getFlowVariableList(flowDefinitionId);
        return ResponseDataResult.setResponseResult(list);
    }


}
