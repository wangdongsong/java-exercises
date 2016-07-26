package com.wds.thread_design_pattern.immutableObject;

/**
 * 彩信中心的相关数据，例如设备编号、URL、支持的最大附件尺寸，被建模为一个不可变对象
 *
 * 彩信中心信息
 *
 * 模式角色：ImmutableObject.ImmutableObject
 * Created by wangdongsong on 2016/7/21.
 */
public class MMSCInfo {

    /**
     * 设备编号
     */
    private final String deviceID;

    /**
     * 彩信中心URL
     */
    private final String url;

    /**
     * 该彩信中心允许的最大附件大小
     */
    private final int maxAttachementSizeInBytes;

    public MMSCInfo(String deviceID, String url, int maxAttachementSizeInBytes) {
        this.deviceID = deviceID;
        this.url = url;
        this.maxAttachementSizeInBytes = maxAttachementSizeInBytes;
    }

    public MMSCInfo(MMSCInfo prototype) {
        this.deviceID = prototype.deviceID;
        this.url = prototype.url;
        this.maxAttachementSizeInBytes = prototype.maxAttachementSizeInBytes;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getUrl() {
        return url;
    }

    public int getMaxAttachementSizeInBytes() {
        return maxAttachementSizeInBytes;
    }
}
