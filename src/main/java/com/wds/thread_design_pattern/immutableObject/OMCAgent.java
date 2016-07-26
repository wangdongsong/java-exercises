package com.wds.thread_design_pattern.immutableObject;

/**
 * 与运维中心（Operation and Maintenance Center）对接的类
 * 模式角色：ImmutableObject.Manipulator
 * Created by wangdongsong on 2016/7/22.
 */
public class OMCAgent extends Thread {

    @Override
    public void run() {
        boolean isTableModificationMsg = false;
        String updatedTableName = null;

        while (true) {
            /*
             * 从OMC连接的SOCKET中读取消息并进行解析
             * 解析到数据表更新消息后，重置MMSCRouter实例
             */
            if (isTableModificationMsg) {
                if ("MMSCInfo".equals(updatedTableName)) {
                    MMSCRouter.setInstance(new MMSCRouter());
                }
            }
        }
    }
}
