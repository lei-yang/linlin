package cn.lyhxh.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ran Han on 2017/12/19.
 */
public class XSS {

    private Integer id;

    private String name;

    private Date time;

    private Double money;

    private BigDecimal price;

    private List<String> listNames;

    private Map<String, Object> mapNames;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getListNames() {
        return listNames;
    }

    public void setListNames(List<String> listNames) {
        this.listNames = listNames;
    }

    public Map<String, Object> getMapNames() {
        return mapNames;
    }

    public void setMapNames(Map<String, Object> mapNames) {
        this.mapNames = mapNames;
    }
}
