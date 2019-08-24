package cn.lyhxh.util;

/**
 * 经纬度实体
 *
 * @author leiyang
 * @since 2019/8/23 16:52
 */
public class LatLng {

    public double longitude;
    double latitude;

    public LatLng() {
    }

    public LatLng(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
