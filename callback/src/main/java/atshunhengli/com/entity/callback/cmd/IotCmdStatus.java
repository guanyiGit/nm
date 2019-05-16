package atshunhengli.com.entity.callback.cmd;

public enum IotCmdStatus {
    DEFAULT, // 表示未下发
    EXPIRED, // 表示命令已经过期
    SUCCESSFUL, // 表示命令已经成功执行
    FAILED, // 表示命令执行失败
    TIMEOUT, // 表示命令下发执行超时
    CANCELED;// 表示命令已经被撤销执行

}
