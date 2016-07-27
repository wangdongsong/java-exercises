package com.wds.thread_design_pattern.guarded_suspension;

import jdk.nashorn.internal.ir.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Timer;
import java.util.function.Predicate;

/**
 * 负责连接告警服务器，并发送警告信息到警告服务器
 * Created by wangdongsong on 2016/7/27.
 */
public class AlarmAgent {
    private static final Logger LOGGER = LogManager.getLogger(AlarmAgent.class);

    /**
     * 用于记录AlarmAgent是否连接上告警服务器
     */
    private volatile boolean connectedToServer = false;

    /**
     * 模式角色：GuardedSuspension.Prdicate
     */
    private final Predicate agentConnected = new Predicate() {
        @Override
        public boolean test(Object o) {
            return connectedToServer;
        }
    };

    /**
     * 模式角色：GuardedSuspension.Blocker
     */
    private final Blocker blocker = new ConditionVarBlocker();

    /**
     * 心跳定时器
     */
    private final Timer heartbeatTimer = new Timer(true);

    /**
     * 发送警告信息
     * @param alarm 告警信息
     * @throws Exception
     */
    public void sendAlarm(final AlarmInfo alarm) throws Exception {
        //可能需要等待，直到AlarmAgent连接上告警服务器（或者连接中断后重新连接上服务器）
        //模式角色：GuardedSuspension.GuardedAction
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {
            @Override
            public Void call() throws Exception {
                doSendAlarm(alarm);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }

    /**
     * 通过网络连接将告警信息发送给告警服务器
     * @param alarm
     */
    private void doSendAlarm(AlarmInfo alarm) {
        //省略其它代码
        LOGGER.debug("send alarm" + alarm);
        //模拟发送告警至服务器的耗时
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
