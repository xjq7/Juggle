package net.somta.juggle.console.interfaces.controller.example;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 订单的示例接口，为系统内置流程提供接口示例，这里的接口，请求内容类型为：application/json
 * @author Gavin
 */
@Tag(name = "示例-商品的示例接口")
@RestController
@RequestMapping("/example/goods")
public class GoodsExampleController {

    private final static Logger logger = LoggerFactory.getLogger(UserExampleController.class);

    @Operation(summary = "发布商品")
    @GetMapping("/releaseGoods")
    public Goods releaseGoods(@RequestBody GoodsParam goodsParam){
        logger.info("接收到商品名称为:{},商品库存为:{}",goodsParam.getGoodsName(),goodsParam.getGoodsInventory());
        Goods goods = new Goods();
        goods.setGoodsId(999);
        goods.setGoodsName(goodsParam.getGoodsName());
        goods.setGoodsInventory(goodsParam.getGoodsInventory());
        return goods;
    }

    @Operation(summary = "获取商品信息")
    @GetMapping("/getGoodsInfo")
    public Goods getGoodsInfo(@RequestBody GoodsParam goodsParam){
        logger.info("接收到商品名称为:{},商品库存为:{}",goodsParam.getGoodsName(),goodsParam.getGoodsInventory());
        Goods goods = new Goods();
        goods.setGoodsId(999);
        goods.setGoodsName(goodsParam.getGoodsName());
        goods.setGoodsPrice(189.59);
        goods.setGoodsInventory(goodsParam.getGoodsInventory());
        return goods;
    }

    public static class Goods{
        private Integer goodsId;
        private String goodsName;
        private Double goodsPrice;
        private Integer goodsInventory;

        public Integer getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Integer goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public Double getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(Double goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public Integer getGoodsInventory() {
            return goodsInventory;
        }

        public void setGoodsInventory(Integer goodsInventory) {
            this.goodsInventory = goodsInventory;
        }
    }

    public static class GoodsParam{
        private String goodsName;
        private Integer goodsInventory;

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public Integer getGoodsInventory() {
            return goodsInventory;
        }

        public void setGoodsInventory(Integer goodsInventory) {
            this.goodsInventory = goodsInventory;
        }
    }

}
