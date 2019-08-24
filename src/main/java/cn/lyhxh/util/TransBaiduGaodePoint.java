package cn.lyhxh.util;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

/**
 * 百度坐标与高德坐标转换
 *
 * @author leiyang
 * @since 2019/8/23 16:49
 */
public class TransBaiduGaodePoint {

    private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    /**
     * 对double类型数据保留小数点后多少位
     * 高德地图转码返回的就是 小数点后6位，为了统一封装一下
     *
     * @param digit 位数
     * @param in    输入
     * @return 保留小数位后的数
     */
    static double dataDigit(int digit, double in) {
        return new BigDecimal(in).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 将火星坐标转变成百度坐标
     *
     * @param lngLat_gd 火星坐标（高德、腾讯地图坐标等）
     * @return 百度坐标
     */

    public static LatLng gaode_to_baidu(LatLng lngLat_gd) {
        double x = lngLat_gd.longitude;
        double y = lngLat_gd.latitude;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        return new LatLng(dataDigit(6, z * Math.sin(theta) + 0.006), dataDigit(6, z * Math.cos(theta) + 0.0065));

    }

    /**
     * 将百度坐标转变成火星坐标
     *
     * @param lngLat_bd 百度坐标（百度地图坐标）
     * @return 火星坐标(高德、腾讯地图等)
     */
    public static LatLng baidu_to_gaode(LatLng lngLat_bd) {
        double x = lngLat_bd.longitude - 0.0065, y = lngLat_bd.latitude - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        return new LatLng(dataDigit(6, z * Math.sin(theta)), dataDigit(6, z * Math.cos(theta)));
    }

    public static void main(String[] args) {

//        LatLng latLng = new LatLng(120.07399275367976, 30.293543061386019);
        //
        // longitude=120.07399275367976, latitude=30.293543061386019
        // longitude=120.073959, latitude=30.294114
        LatLng latLng = new LatLng(120.073959, 30.294114);
        System.out.println(JSON.toJSONString(latLng));

        // latitude":"30.294114","longitude":"120.073959"
        // "120.067397117746,30.287873680499"
        LatLng r = baidu_to_gaode(latLng);
        System.out.println(JSON.toJSONString(r));

    }
}
