package com.wxmultilang;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Guany 20190522
 *
 * @author linchong
 * @create 2019-04-22 10:27
 */
@Component
@ConfigurationProperties(prefix = "zh_BO")
@PropertySource(value = "multi_lang.properties")
public class ZhBOLangProp {
/** Guany 20190522藏文  20190521*/
    /**
     * Guany 20190522
     * 语言
     */
    private String language;
    /**
     * Guany 20190522
     * 犬防疫
     */
    private String quanfangyi;
    /**
     * Guany 20190522
     * 到底了
     */
    private String daodile;
    /**
     * Guany 20190522
     * 未知
     */
    private String weizhi;
    /**
     * Guany 20190522
     * 亩
     */
    private String mu;
    /**
     * Guany 20190522
     * 下拉显示更多
     */
    private String xialagengduo;
    /**
     * Guany 20190522
     * 公斤
     */
    private String gongjin;
    /**
     * Guany 20190522
     * 年
     */
    private String year;
    /**
     * Guany 20190522
     * 月
     */
    private String month;
    /**
     * Guany 20190522
     * 日
     */
    private String day;
    /**
     * Guany 20190522
     * 岁
     */
    private String sui;
    /**
     * Guany 20190522
     * 请输入用户名
     */
    private String pl_username;
    /**
     * Guany 20190522
     * 请输入密码
     */
    private String pl_passwrod;
    /**
     * Guany 20190522
     * 提交
     */
    private String submit;
    /**
     * Guany 20190522
     * 保存
     */
    private String save;
    /**
     * Guany 20190522
     * 重置
     */
    private String reset;
    /**
     * Guany 20190522
     * 取消
     */
    private String cancel;
    /**
     * Guany 20190522
     * 犬只犬主
     */
    private String uanzhi_quanzhu;
    /**
     * Guany 20190522
     * 犬主管理
     */
    private String quanzhu_guanli;
    /**
     * Guany 20190522
     * 犬只管理
     */
    private String quanzhi_guanli;
    /**
     * Guany 20190522
     * 流浪犬管理
     */
    private String liulangquan_guanli;
    /**
     * Guany 20190522
     * 统计信息
     */
    private String tongjixinxi;
    /**
     * Guany 20190522
     * 周边犬只
     */
    private String quanzhi_zhoubian;
    /**
     * Guany 20190522
     * 防治管理
     */
    private String fangzhi_guanli;
    /**
     * Guany 20190522
     * 犬只防疫
     */
    private String quanzhi_fangyi;
    /**
     * Guany 20190522
     * 犬粪处理
     */
    private String quanfen_chuli;
    /**
     * Guany 20190522
     * 犬尸处理
     */
    private String quanshi_chuli;
    /**
     * Guany 20190522
     * 犬粪抗原检测
     */
    private String quanfenkangyuan_jiance;
    /**
     * Guany 20190522
     * 犬只解刨
     */
    private String quanhzi_jiepao;
    /**
     * Guany 20190522
     * 牛羊抗体检测
     */
    private String niuyangkangti_jiance;
    /**
     * Guany 20190522
     * 牛羊脏器处理
     */
    private String niuyangzangqi_chuli;
    /**
     * Guany 20190522
     * 统计信息
     */
    private String tongji_xinxi;
    /**
     * Guany 20190522
     * 物资管理
     */
    private String wuzi_guanli;
    /**
     * Guany 20190522
     * 防护物资
     */
    private String fanghuwuzi;
    /**
     * Guany 20190522
     * 犬只项圈
     */
    private String quanzhu_xianquan;
    /**
     * Guany 20190522
     * 宣传培训
     */
    private String xuanchuan_peixun;
    /**
     * Guany 20190522
     * 宣传活动
     */
    private String xuanchuan_huodong;
    /**
     * Guany 20190522
     * 培训活动
     */
    private String peixunhuodong;
    /**
     * Guany 20190522
     * 犬主信息
     */
    private String quanzhu_xinxi;
    /**
     * Guany 20190522
     * 犬主姓名
     */
    private String quanzhu_xingming;
    /**
     * Guany 20190522
     * 性别
     */
    private String xingbie;
    /**
     * Guany 20190522
     * 男
     */
    private String nan;
    /**
     * Guany 20190522
     * 女
     */
    private String nv;
    /**
     * Guany 20190522
     * 出生日期
     */
    private String chushengriqi;
    /**
     * Guany 20190522
     * 名族
     */
    private String mingzu;
    /**
     * Guany 20190522
     * 文化程度
     */
    private String wenhuachengdu;
    /**
     * Guany 20190522
     * 联系电话
     */
    private String lianxidianhua;
    /**
     * Guany 20190522
     * 证件类型
     */
    private String zhengjian_leix;
    /**
     * Guany 20190522
     * 证件号码
     */
    private String zhengjian_haoma;
    /**
     * Guany 20190522
     * 草场面积
     */
    private String caochangmianji;
    /**
     * Guany 20190522
     * 所属乡镇
     */
    private String suoshuxiangzhen;
    /**
     * Guany 20190522
     * 所属村委会
     */
    private String suoshucunweihui;
    /**
     * Guany 20190522
     * 门牌号
     */
    private String menpaihao;
    /**
     * Guany 20190522
     * 防疫员
     */
    private String fangyiyuan;
    /**
     * Guany 20190522
     * 录入时间
     */
    private String lvrushijian;
    /**
     * Guany 20190522
     * 照片
     */
    private String zhaopian;
    /**
     * Guany 20190522
     * 描述
     */
    private String miaoshu;
    /**
     * Guany 20190522
     * 犬只信息
     */
    private String quanzhi_xinxi;
    /**
     * Guany 20190522
     * 未绑定项圈
     */
    private String weibangdxiangquan;
    /**
     * Guany 20190522
     * 阳性率
     */
    private String yangxinglv;
    /**
     * Guany 20190522
     * 检测时间
     */
    private String jianceshijian;
    /**
     * Guany 20190522
     * 检测单位
     */
    private String jiancedanwei;
    /**
     * Guany 20190522
     * 检测数
     */
    private String jianceshu;
    /**
     * Guany 20190522
     * 阳性数
     */
    private String yangxingshu;
    /**
     * Guany 20190522
     * 无害化
     */
    private String wuhaihua;
    /**
     * Guany 20190522
     * 图片
     */
    private String tupian;
    /**
     * Guany 20190522
     * 视频
     */
    private String shipin;
    /**
     * Guany 20190522
     * 防疫时间
     */
    private String fangyishijan;
    /**
     * Guany 20190522
     * 犬名
     */
    private String quanming;
    /**
     * Guany 20190522
     * 犬主
     */
    private String quanzhu;
    /**
     * Guany 20190522
     * 溯源ID
     */
    private String suyuan_ID;
    /**
     * Guany 20190522
     * 项圈编号
     */
    private String xiangquanbianhao;
    /**
     * Guany 20190522
     * 犬主电话
     */
    private String quanzhudianhua;
    /**
     * Guany 20190522
     * 犬主身份证
     */
    private String quanzhushengfengzheng;
    /**
     * Guany 20190522
     * 所属乡镇
     */
    private String shusuoxiangzheng;
    /**
     * Guany 20190522
     * 详细地址
     */
    private String xiangxidizhi;
    /**
     * Guany 20190522
     * 防疫类型
     */
    private String fangyileixin;
    /**
     * Guany 20190522
     * 防疫时间
     */
    private String fangyishijian;
    /**
     * Guany 20190522
     * 防疫药品
     */
    private String fangyiyaopin;
    /**
     * Guany 20190522
     * 防疫周期
     */
    private String fangyizhouqi;
    /**
     * Guany 20190522
     * 防疫员电话
     */
    private String fangyiyuandianhua;
    /**
     * Guany 20190522
     * 合格数
     */
    private String hegeshu;
    /**
     * Guany 20190522
     * 合格率
     */
    private String hegelv;
    /**
     * Guany 20190522
     * 处理时间
     */
    private String chulishijian;
    /**
     * Guany 20190522
     * 死亡原因
     */
    private String siwangshijian;
    /**
     * Guany 20190522
     * 处理方法
     */
    private String chulifangfa;
    /**
     * Guany 20190522
     * 方法说明
     */
    private String fangfashuoming;
    /**
     * Guany 20190522
     * 处理人员
     */
    private String chulirenyuan;
    /**
     * Guany 20190522
     * 接收单位
     */
    private String jieshoudanwei;
    /**
     * Guany 20190522
     * 接收人
     */
    private String jieshouren;
    /**
     * Guany 20190522
     * 发放单位
     */
    private String fafangdanwei;
    /**
     * Guany 20190522
     * 发放
     */
    private String fafang;
    /**
     * Guany 20190522
     * 发放数量
     */
    private String fafangshuliang;
    /**
     * Guany 20190522
     * 发放时间
     */
    private String fafangshijian;
    /**
     * Guany 20190522
     * 入栏时间
     */
    private String rulanshijian;
    /**
     * Guany 20190522
     * 体重
     */
    private String tizhong;
    /**
     * Guany 20190522
     * 毛色
     */
    private String maose;
    /**
     * Guany 20190522
     * 犬龄
     */
    private String quanling;
    /**
     * Guany 20190522
     * 犬种
     */
    private String quanzhong;
    /**
     * Guany 20190522
     * 犬只存栏数量
     */
    private String quanzhicunlanshuliang;
    /**
     * Guany 20190522
     * 所属州
     */
    private String suoshuzhou;
    /**
     * Guany 20190522
     * 所属县
     */
    private String suoshuxian;
    /**
     * Guany 20190522
     * 所属乡
     */
    private String suoshuxiang;
    /**
     * Guany 20190522
     * 所属防疫员
     */
    private String suoshufangyiyuan;
    /**
     * Guany 20190522
     * 数量
     */
    private String shuliang;
    /**
     * Guany 20190522
     * 犬主数量
     */
    private String quanzhushuliang;
    /**
     * Guany 20190522
     * 流浪犬处理数量
     */
    private String liulangquanchulishuliang;
    /**
     * Guany 20190522
     * 防疫次数
     */
    private String fangyicishu;
    /**
     * Guany 20190522
     * 犬尸处理次数
     */
    private String shitichulicishu;
    /**
     * Guany 20190522
     * 犬粪处理次数
     */
    private String quanfenchulicishu;
    /**
     * Guany 20190522
     * 犬粪抗原检测次数
     */
    private String quanfenkangyuancishu;
    /**
     * Guany 20190522
     * 犬只解剖次数
     */
    private String quanzhijiepaocishu;
    /**
     * Guany 20190522
     * 牛羊抗体检测次数
     */
    private String niuyangjiancecishu;
    /**
     * Guany 20190522
     * 牛羊脏器处理次数
     */
    private String niuyangzangqichulicishu;
    /**
     * Guany 20190522
     * 次数
     */
    private String cishu;
    /**
     * Guany 20190522
     * 设备编号
     */
    private String shebeibianhao;
    /**
     * Guany 20190522
     * 身份证号码
     */
    private String shenfengzhenghaoma;
    /**
     * Guany 20190522
     * 物资类型
     */
    private String wuzileixing;
    /**
     * Guany 20190522
     * 物资名称
     */
    private String wuzimingcheng;
    /**
     * Guany 20190522
     * 发送时间
     */
    private String fasongshijian;
    /**
     * Guany 20190522
     * 居民身份证
     */
    private String qumingshengfengzheng;
    /**
     * Guany 20190522
     * 居民户口簿
     */
    private String juminghukoubo;
    /**
     * Guany 20190522
     * 粪便处理信息
     */
    private String fenbianchulixinxi;
    /**
     * Guany 20190522
     * 防疫信息
     */
    private String fangyixinxi;
    /**
     * Guany 20190522
     * 所属牧委会
     */
    private String suoshumoweihui;
    /**
     * Guany 20190522
     * 电子围栏
     */
    private String dianziweilan;
    /**
     * Guany 20190522
     * 活动时间
     */
    private String huodongshijian;
    /**
     * Guany 20190522
     * 举办单位
     */
    private String jibandanwei;
    /**
     * Guany 20190522
     * 内容
     */
    private String neirong;
    /**
     * Guany 20190522
     * 发放宣传品数量
     */
    private String fafangxuanchuanpinshuliang;
    /**
     * Guany 20190522
     * 主题
     */
    private String zhuti;
    /**
     * Guany 20190522
     * 地点
     */
    private String didian;
    /**
     * Guany 20190522
     * 时间
     */
    private String shijian;
    /**
     * Guany 20190522
     * 退出登录
     */
    private String tuichudenglu;
    /**
     * Guany 20190522
     * 密码设置
     */
    private String miamashezhi;
    /**
     * Guany 20190522
     * 个人信息
     */
    private String gerenxinxi;
    /**
     * Guany 20190522
     * 确认密码
     */
    private String querenmima;
    /**
     * Guany 20190522
     * 新密码
     */
    private String xinmima;
    /**
     * Guany 20190522
     * 旧密码
     */
    private String jiumima;
    /**
     * Guany 20190522
     * 犬只数
     */
    private String quanzhishu;
    /**
     * Guany 20190522
     * 已防疫
     */
    private String yifangyi;
    /**
     * Guany 20190522
     * 未防疫
     */
    private String weifangyi;
    /**
     * Guany 20190522
     * 处理方式
     */
    private String chulifangshi;
    /**
     * Guany 20190522
     * 公
     */
    private String gong_sex;
    /**
     * Guany 20190522
     * 母
     */
    private String mu_sex;
    /**
     * Guany 20190522
     * 处理人
     */
    private String chuliren;
    /**
     * Guany 20190522
     * 品种
     */
    private String pingzhong;
    /**
     * Guany 20190522
     * 流浪犬编号
     */
    private String liulangquanbianhao;
    /**
     * Guany 20190522
     * 参与人数
     */
    private String caiyurenshu;
    /**
     * Guany 20190522
     * 培训对象
     */
    private String peixunduixiang;
    /**
     * Guany 20190522
     * 用户名
     */
    private String yonghuming;
    /**
     * Guany 20190522
     * 姓名
     */
    private String xingming;
    /**
     * Guany 20190522
     * 所属区域
     */
    private String suoshuquyu;
    /**
     * Guany 20190522
     * 所属组织
     */
    private String suoshuzuzhi;
    /**
     * Guany 20190522
     * 病变脏器处理
     */
    private String binbianzangqichuli;
    /**
     * Guany 20190522
     * 病变脏器处理数
     */
    private String binbianzangqichulishu;
    /**
     * Guany 20190522
     * 感染数
     */
    private String ganranshu;
    /**
     * Guany 20190522
     * 调查数
     */
    private String diaochashu;
    /**
     * Guany 20190522
     * 项圈处理
     */
    private String xiangquanchuli;
    /**
     * Guany 20190522
     * 电话
     */
    private String dianhua;
    /**
     * Guany 20190522
     * 电话号码
     */
    private String dianhuahaoma;
    /**
     * Guany 20190522
     * 注销时间
     */
    private String zhuxiaoshijian;
    /**
     * Guany 20190522
     * 注销原因
     */
    private String zhuxiaoyuanyin;
    /**
     * Guany 20190522
     * 防疫员姓名
     */
    private String fangyiyuanxingming;
    /**
     * Guany 20190522
     * 确认绑定
     */
    private String querenbangdin;
    /**
     * Guany 20190522
     * 未定
     */
    private String weidin;
    /**
     * Guany 20190522
     * 请扫描二维码绑定项圈
     */
    private String scan_bind_device;
    /**
     * Guany 20190522
     * 暂不绑定
     */
    private String zanbubangdin;
    /**
     * Guany 20190522
     * 定位查询
     */
    private String dinweichaxun;
    /**
     * Guany 20190522
     * 轨迹查询
     */
    private String guijichaxun;
    /**
     * Guany 20190522
     * 日期
     */
    private String riqi;
    /**
     * Guany 20190522
     * 未读消息
     */
    private String weiduxiaoxi;
    /**
     * Guany 20190522
     * 已读消息
     */
    private String yiduxiaoxi;
    /**
     * Guany 20190522
     * 详情
     */
    private String xiangqing;
    /**
     * Guany 20190522
     * 标题
     */
    private String biaoti;
    /**
     * Guany 20190522
     * 更换为
     */
    private String genghuanwei;
    /**
     * Guany 20190522
     * 更换时间
     */
    private String genghuanshijian;
    /**
     * Guany 20190522
     * 更换原因
     */
    private String genghuanyuanyin;
    /**
     * Guany 20190522
     * 新设备编号
     */
    private String xinshebeibianhao;
    /**
     * Guany 20190522
     * 旧设备编号
     */
    private String jiushebeibianhao;
    /**
     * Guany 20190522
     * 原项圈处理
     */
    private String yuanxiangquanchuli;
    /**
     * Guany 20190522
     * 新项圈
     */
    private String xinxiangquan;
    /**
     * Guany 20190522
     * 新犬主
     */
    private String xinquanzhu;
    /**
     * Guany 20190522
     * 旧犬主
     */
    private String jiuquanzhu;
    /**
     * Guany 20190522
     * 语言设置
     */
    private String yuyanshezhi;
    /**
     * Guany 20190522
     * 开启
     */
    private String kaiqi;
    /**
     * Guany 20190522
     * 关闭
     */
    private String guanbi;
    /**
     * Guany 20190522
     * 项圈更换次数
     */
    private String xiangquangenghuancishu;
    /**
     * Guany 20190522
     * 粪便处理次数
     */
    private String fengbianchulicishu;
    /**
     * Guany 20190522
     * 犬尸处理数量
     */
    private String quanshichulishuliang;
    /**
     * Guany 20190522
     * 新增犬主数量
     */
    private String xinzengquanzhushuliang;
    /**
     * Guany 20190522
     * 注销犬只数量
     */
    private String zhuxiaoquanzhishuliang;
    /**
     * Guany 20190522
     * 新增犬只数量
     */
    private String xinzengquanzhishuliang;
    /**
     * Guany 20190522
     * 查询
     */
    private String chaxun;
    /**
     * Guany 20190522
     * 至
     */
    private String zhi;
    /**
     * Guany 20190522
     * 房产证
     */
    private String fangchanzhe;
    /**
     * Guany 20190522
     * 我的消息
     */
    private String wodexiaoxi;
    /**
     * Guany 20190522
     * 定位轨迹
     */
    private String dinweiguiji;
    /**
     * Guany 20190522
     * 犬只注销
     */
    private String quanzhizhuxiao;
    /**
     * Guany 20190522
     * 项圈更换
     */
    private String xiangquangenghuan;
    /**
     * Guany 20190522
     * 流浪犬处理
     */
    private String liulangquanchuli;
    /**
     * Guany 20190522
     * 粪便处理
     */
    private String fenbianchuli;
    /**
     * Guany 20190522
     * 确定
     */
    private String quedin;
    /**
     * Guany 20190522
     * 举办时间
     */
    private String jubanshijian;
    /**
     * Guany 20190522
     * 更多
     */
    private String gengduo;
    /**
     * Guany 20190522
     * 选择区域
     */
    private String xuanzhequyu;
    /**
     * Guany 20190522
     * 年龄
     */
    private String nianling;
    /**
     * Guany 20190522
     * 手机号
     */
    private String shoujihao;
    /**
     * Guany 20190522
     * 防疫登记
     */
    private String fangyidengji;
    /**
     * Guany 20190522
     * 新增犬主
     */
    private String xinzengquanzhu;
    /**
     * Guany 20190522
     * 项圈绑定
     */
    private String xiangquanbangdin;
    /**
     * Guany 20190522
     * 新增犬只
     */
    private String xinzengquanzxhi;
    /**
     * Guany 20190522
     * 查看详情
     */
    private String chakanxiangqing;
    /**
     * Guany 20190522
     * 犬主详情
     */
    private String quanzhuxiangqing;
    /**
     * Guany 20190522
     * 犬主信息录入
     */
    private String quanzhuxinxilvru;
    /**
     * Guany 20190522
     * 犬只信息录入
     */
    private String quanzhixinxilvru;
    /**
     * Guany 20190522
     * 绑定项圈
     */
    private String bangdinxiangquan;
    /**
     * Guany 20190522
     * 防疫信息录入
     */
    private String fangyixinxilvru;
    /**
     * Guany 20190522
     * 尸体无害化处理
     */
    private String shitiwuhaihuachuli;
    /**
     * Guany 20190522
     * 开始时间
     */
    private String kaishishijian;
    /**
     * Guany 20190522
     * 结束时间
     */
    private String jieshushijian;
    /**
     * Guany 20190522
     * 流浪犬处理录入
     */
    private String liulangquanchulilvru;
    /**
     * Guany 20190522
     * 登录
     */
    private String denglv;
    /**
     * Guany 20190522
     * 未能识别二维码
     */
    private String weinengshibieerweima;
    /**
     * Guany 20190522
     * 提示
     */
    private String tishi;
    /**
     * Guany 20190522
     * 该溯源Id不存在
     */
    private String gaisuyuanidbucunzai;
    /**
     * Guany 20190522
     * 该设备未绑定任何犬只
     */
    private String gaishebeiweibangdinquanzhi;
    /**
     * Guany 20190522
     * 添加成功
     */
    private String tianjiachenggong;
    /**
     * Guany 20190522
     * 犬只解剖
     */
    private String quanzhijiepao;
    /**
     * Guany 20190522
     * 新增犬只解剖
     */
    private String xinzengquanzhijiepao;
    /**
     * Guany 20190522
     * 新增犬粪抗原检测
     */
    private String xinzengquanfenkanyuanjiance;
    /**
     * Guany 20190522
     * 新增牛羊抗体检测
     */
    private String xinzengniuyangkanyuanjiance;
    /**
     * Guany 20190522
     * 项圈发放
     */
    private String xiangquanfafang;
    /**
     * Guany 20190522
     * 新增宣传活动
     */
    private String xinzengxuanchuanhuodong;
    /**
     * Guany 20190522
     * 设置
     */
    private String shezhi;
    /**
     * Guany 20190522
     * 新增培训活动
     */
    private String xinzengpeixunhuodong;
    /**
     * Guany 20190522
     * 新增牛羊脏器处理
     */
    private String xinzengniuyangzangqichuli;
    /**
     * Guany 20190522
     * 防疫统计
     */
    private String fangyitongji;
    /**
     * Guany 20190522
     * 新增
     */
    private String xinzeng;
    /**
     * Guany 20190522
     * 加载中
     */
    private String jiazaizhong;
    /**
     * Guany 20190522
     * 登录超时
     */
    private String denglvchaoshi;
    /**
     * Guany 20190522
     * 请重新登录
     */
    private String chongxindenglv;
    /**
     * Guany 20190522
     * 请输入有效的数字
     */
    private String qingshuruyouxiaoshuzi;
    /**
     * Guany 20190522
     * 请输入正确的数字
     */
    private String qingshuruzhegnqueshuzi;
    /**
     * Guany 20190522
     * 请正确输入数据参数
     */
    private String qingshurushujucanshu;
    /**
     * Guany 20190522
     * 我的位置
     */
    private String wodeweizhi;
    /**
     * Guany 20190522
     * 更改密码成功
     */
    private String genghuanmimachengg;
    /**
     * Guany 20190522
     * 请重新登录
     */
    private String qingchongxdenglv;
    /**
     * Guany 20190522
     * 两次输入密码不一致
     */
    private String liangcimimabuyizhi;
    /**
     * Guany 20190522
     * 请重新输入
     */
    private String qingchongxinshuru;
    /**
     * Guany 20190522
     * 感染数必须小于调查数
     */
    private String ganranshubixuxiaoyutiaochashu;
    /**
     * Guany 20190522
     * 修改成功
     */
    private String xiugaichenggong;
    /**
     * Guany 20190522
     * 修改失败
     */
    private String xiugaishibai;
    /**
     * Guany 20190522
     * 该设备不存在
     */
    private String gaishebeibucunzai;
    /**
     * Guany 20190522
     * 该设备已被绑定
     */
    private String gaishebeiyibeibangdin;
    /**
     * Guany 20190522
     * 请绑定项圈
     */
    private String qingbangdinxiangquan;
    /**
     * Guany 20190522
     * 绑定成功
     */
    private String bingdinchenggong;
    /**
     * Guany 20190522
     * 绑定失败
     */
    private String bingdinshibai;
    /**
     * Guany 20190522
     * 无犬只定位信息
     */
    private String wuquanzhidinweixinxi;
    /**
     * Guany 20190522
     * 当天无数据
     */
    private String wudangtianshuju;
    /**
     * Guany 20190522
     * 无对应犬只或者犬主
     */
    private String wuduiyinquanzhiquanzhu;
    /**
     * Guany 20190522
     * 添加失败
     */
    private String tianjiashibai;
    /**
     * Guany 20190522
     * 请稍后再试
     */
    private String qingshaohouzaishi;
    /**
     * Guany 20190522
     * 失败
     */
    private String shibai;
    /**
     * Guany 20190522
     * 更换成功
     */
    private String genghuanchenggong;
    /**
     * Guany 20190522
     * 证件号码已存在
     */
    private String zhengjianhaomayicunzai;
    /**
     * Guany 20190522
     * 电话号码已存在
     */
    private String dianhuahaomayicunzai;
    /**
     * Guany 20190522
     * 操作失败
     */
    private String caozuoshibai;
    /**
     * Guany 20190522
     * 用户电话或证书号码重复
     */
    private String yonghudianhuahaomachongfu;

    /**
     * Guany 20190522
     * 这是必填字段
     */
    private String zheshibitianziduan;
    /**
     * Guany 20190522
     * 请输入有效的电子邮件地址
     */
    private String qingshuruyouxiaodedianziyoujiandizhi;
    /**
     * Guany 20190522
     * 请输入11位的手机号码
     */
    private String qingshuru11weishoujihao;
    /**
     * Guany 20190522
     * 请输入有效的网址
     */
    private String qingshuruyouxiaodewangzhi;
    /**
     * Guany 20190522
     * 请输入有效的日期
     */
    private String qingshuruyouxiaoriqi;
    /**
     * Guany 20190522
     * 例如
     */
    private String liru;
    /**
     * Guany 20190522
     * 只能输入数字
     */
    private String zhinengshurushuzi;
    /**
     * Guany 20190522
     * 请输入18位的有效身份证
     */
    private String qingshuru18weiyouxiaozhenghao;
    /**
     * Guany 20190522
     * 输入值必须等于
     */
    private String shuruzhibixudengyu;
    /**
     * Guany 20190522
     * 输入值必须包含
     */
    private String shuruzhibixubaohan;
    /**
     * Guany 20190522
     * 输入最低长度为
     */
    private String shuruzuixiaochangduwei;
    /**
     * Guany 20190522
     * 输入最大长度为
     */
    private String shuruzuidachangduwei;
    /**
     * Guany 20190522
     * 个字符
     */
    private String gezifu;
    /**
     * Guany 20190522
     * 输入长度范围必须为
     */
    private String shuruchangdubixuwei;
    /**
     * Guany 20190522
     * 请输入不小于
     */
    private String qingshurubuxiaoyu;
    /**
     * Guany 20190522
     * 请输入不大于
     */
    private String qingshurubudayu;
    /**
     * Guany 20190522
     * 输入范围在
     */
    private String shurufanweizai;
    /**
     * Guany 20190522
     * 请填写检测数
     */
    private String qingtianxiejianceshu;
    /**
     * Guany 20190522
     * 检测数填写不正确
     */
    private String jianceshutianxiebuzhengque;
    /**
     * Guany 20190522
     * 请填写阳性数
     */
    private String qingtianxieyangxingshu;
    /**
     * Guany 20190522
     * 阳性数填写不正确
     */
    private String yangxingshutianxiebuzhengque;
    /**
     * Guany 20190522
     * 请填写阳性率
     */
    private String qingtianxieyangxinglv;
    /**
     * Guany 20190522
     * 阳性率填写不正确
     */
    private String yangxinglvtianxiebuzhengque;
    /**
     * Guany 20190522
     * 请填写无害化处理数
     */
    private String qingtianxiewuhaihuachulishu;
    /**
     * Guany 20190522
     * 无害化处理数填写不正确
     */
    private String wuhaihuachulishutianxiebuzhengque;
    /**
     * Guany 20190522
     * 请填写合格数
     */
    private String qingtianxiehegeshu;
    /**
     * Guany 20190522
     * 合格数填写不正确
     */
    private String hegeshutianxiebuzhengque;
    /**
     * Guany 20190522
     * 请填写溯源id
     */
    private String qingtianxieshuyuanID;
    /**
     * Guany 20190522
     * 请输入正确的数量
     */
    private String qingshuruzhengquedeshuliang;
    /**
     * Guany 20190522
     * 未防疫犬只
     */
    private String weifangyiquanzhi;
    /**
     * Guany 20190522
     * 已防疫犬只
     */
    private String yifangyiquanzhi;
    /**
     * Guany 20190522
     * 请填写地点
     */
    private String qingtianxiedidian;
    /**
     * Guany 20190522
     * 请填写主题
     */
    private String qingtianxiezhuti;
    /**
     * Guany 20190522
     * 请填写宣传品数量
     */
    private String qingtianxiexuanchuanppingshuliang;
    /**
     * Guany 20190522
     * 宣传品数量填写不正确
     */
    private String xuanchuanpingchuliangbuzhenfgque;
    /**
     * Guany 20190522
     * 请填写内容
     */
    private String qingtianxieneirong;
    /**
     * Guany 20190522
     * 旧密码不能为空
     */
    private String jiumimabunengweikong;
    /**
     * Guany 20190522
     * 新密码不能为空
     */
    private String xinmimabunengweikong;
    /**
     * Guany 20190522
     * 确认密码不能为空
     */
    private String querenmimabunengweikong;
    /**
     * Guany 20190522
     * 请填写处理方式
     */
    private String qingtianxiechulifangshi;
    /**
     * Guany 20190522
     * 请填写参与人数
     */
    private String qingtianxiecanyurenshu;
    /**
     * Guany 20190522
     * 参与人数填写不正确
     */
    private String canshurenshutianxiebuzhengque;
    /**
     * Guany 20190522
     * 请填写培训对象
     */
    private String qingtianxiepeixunduixiang;
    /**
     * Guany 20190522
     * 请填写调查数
     */
    private String qingtianxiedianchashu;
    /**
     * Guany 20190522
     * 调查数填写不正确
     */
    private String dianchashutianxiebuzhengque;
    /**
     * Guany 20190522
     * 请填写感染数
     */
    private String qingtianxieganranshu;
    /**
     * Guany 20190522
     * 感染数填写不正确
     */
    private String ganranshutianxiebuzhengque;
    /**
     * Guany 20190522
     * 请填写病变处理数
     */
    private String qingtianxiebingbianchulishu;
    /**
     * Guany 20190522
     * 病变处理数填写不正确
     */
    private String bingbianshutianxiebuzhengque;
    /**
     * Guany 20190522
     * 请填写犬名
     */
    private String qingshuruquanming;
    /**
     * Guany 20190522
     * 请填写处理方法
     */
    private String qingtianxiechulifangfa;
    /**
     * Guany 20190522
     * 请填写方法说明
     */
    private String qingtianxiefangfashuoming;
    /**
     * Guany 20190522
     * 请填写新项圈编号
     */
    private String qingtianxiexinxiangquanbianhao;
    /**
     * Guany 20190522
     * 请填写门牌号
     */
    private String tianxiexinxiangquanbianhao;
    /**
     * Guany 20190522
     * 请填写犬主姓名
     */
    private String qingtianxiequanzhuxinming;
    /**
     * Guany 20190522
     * 请填写电话号码
     */
    private String qingtianxiedianhuahao;
    /**
     * Guany 20190522
     * 请输入正确的电话号码
     */
    private String qingshuruzhengquededianhuahaoma;
    /**
     * Guany 20190522
     * 请填写证件号码
     */
    private String qingtianxiezhengjianhaoma;
    /**
     * Guany 20190522
     * 请输入正确的证件号码
     */
    private String qingshuruzhegnquedezhengjianhaoma;
    /**
     * Guany 20190522
     * 账号不能为空
     */
    private String zhanghaobunengweikong;
    /**
     * Guany 20190522
     * 密码不能为空
     */
    private String mimabunengweikong;
    /**
     * Guany 20190522
     * 至今
     */
    private String zhijin;
    /**
     * Guany 20190522
     * 请选择开始时间
     */
    private String qingxuanzekaishishijian;
    /**
     * Guany 20190522
     * 全部
     */
    private String quanbu;
    /**
     * Guany 20190522
     * 敬请期待
     */
    private String jingqingqidai;
    /**
     * Guany 20190522
     * 不限
     */
    private String buxian;
    /**
     * Guany 20190522
     * 该设备已丢失
     */
    private String gaishebeiyidiushi;
    /**
     * Guany 20190522
     * 该设备已损坏
     */
    private String gaishebeiyisunhuai;
    /**
     * Guany 20190522
     * 用户名或密码错误
     */
    private String yonghuminghuomimacuowu;
    /**
     * Guany 20190522
     * 阳性数不能大于检测数
     */
    private String yangxinshubunengdayujianceshu;
    /**
     * Guany 20190522
     * 合格数不能大于检测数
     */
    private String hegeshubunengdayujianceshu;
    /**
     * Guany 20190522
     * 新增防护物资
     */
    private String xinzengfanghuwuzi;
    /**
     * Guany 20190522
     * 请填写物资名称
     */
    private String qingtianxiewuzimingcheng;
    /**
     * Guany 20190522
     * 请填写发放数量
     */
    private String qingtianxiefafangshuliang;
    /**
     * Guany 20190522
     * 数量填写不正确
     */
    private String shuliangtianxiebuzhengque;
    /**
     * Guany 20190522
     * 输入的旧密码有误
     */
    private String shurudejiumimayouwu;
    /**
     * Guany 20190522
     * 确定要删除此图片吗
     */
    private String quedingyaoshanchucitupianma;
    /**
     * Guany 20190522
     * 最多上传三张
     */
    private String zuiduoshangchuansanzhang;
    /**
     * Guany 20190522
     * 汉族
     */
    private String hanzu;
    /**
     * Guany 20190522
     * 藏族
     */
    private String zangzu;
    /**
     * Guany 20190522
     * 小学
     */
    private String xiaoxue;
    /**
     * Guany 20190522
     * 初中
     */
    private String chuzhong;
    /**
     * Guany 20190522
     * 高中
     */
    private String gaozhong;
    /**
     * Guany 20190522
     * 中专
     */
    private String zhongzhuan;
    /**
     * Guany 20190522
     * 大专
     */
    private String dazhuan;
    /**
     * Guany 20190522
     * 本科及以上
     */
    private String benkejiyishang;
    /**
     * Guany 20190522
     * 正常
     */
    private String zhengchang;
    /**
     * Guany 20190522
     * 免疫超期
     */
    private String mianyichaoqi;
    /**
     * Guany 20190522
     * 暂未绑定项圈
     */
    private String zanweibangdinxiangquan;
    /**
     * Guany 20190522
     * 春防
     */
    private String chunfang;
    /**
     * Guany 20190522
     * 秋防
     */
    private String qiufang;
    /**
     * Guany 20190522
     * 月月投药
     */
    private String yueyuetouyao;
    /**
     * Guany 20190522
     * 下载中
     */
    private String xiazaizhong;
    /**
     * Guany 20190522
     * 更新提示
     */
    private String gengxintishi;
    /**
     * Guany 20190522
     * 新版本已下载完毕，请点击确定更新
     */
    private String dowloadAndupdate;
    /**
     * Guany 20190522
     * 新版本下载失败，请检查网络并重新更新
     */
    private String dowloadFailAndCheckd;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getQuanfangyi() {
        return quanfangyi;
    }

    public void setQuanfangyi(String quanfangyi) {
        this.quanfangyi = quanfangyi;
    }

    public String getDaodile() {
        return daodile;
    }

    public void setDaodile(String daodile) {
        this.daodile = daodile;
    }

    public String getWeizhi() {
        return weizhi;
    }

    public void setWeizhi(String weizhi) {
        this.weizhi = weizhi;
    }

    public String getMu() {
        return mu;
    }

    public void setMu(String mu) {
        this.mu = mu;
    }

    public String getXialagengduo() {
        return xialagengduo;
    }

    public void setXialagengduo(String xialagengduo) {
        this.xialagengduo = xialagengduo;
    }

    public String getGongjin() {
        return gongjin;
    }

    public void setGongjin(String gongjin) {
        this.gongjin = gongjin;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSui() {
        return sui;
    }

    public void setSui(String sui) {
        this.sui = sui;
    }

    public String getPl_username() {
        return pl_username;
    }

    public void setPl_username(String pl_username) {
        this.pl_username = pl_username;
    }

    public String getPl_passwrod() {
        return pl_passwrod;
    }

    public void setPl_passwrod(String pl_passwrod) {
        this.pl_passwrod = pl_passwrod;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getUanzhi_quanzhu() {
        return uanzhi_quanzhu;
    }

    public void setUanzhi_quanzhu(String uanzhi_quanzhu) {
        this.uanzhi_quanzhu = uanzhi_quanzhu;
    }

    public String getQuanzhu_guanli() {
        return quanzhu_guanli;
    }

    public void setQuanzhu_guanli(String quanzhu_guanli) {
        this.quanzhu_guanli = quanzhu_guanli;
    }

    public String getQuanzhi_guanli() {
        return quanzhi_guanli;
    }

    public void setQuanzhi_guanli(String quanzhi_guanli) {
        this.quanzhi_guanli = quanzhi_guanli;
    }

    public String getLiulangquan_guanli() {
        return liulangquan_guanli;
    }

    public void setLiulangquan_guanli(String liulangquan_guanli) {
        this.liulangquan_guanli = liulangquan_guanli;
    }

    public String getTongjixinxi() {
        return tongjixinxi;
    }

    public void setTongjixinxi(String tongjixinxi) {
        this.tongjixinxi = tongjixinxi;
    }

    public String getQuanzhi_zhoubian() {
        return quanzhi_zhoubian;
    }

    public void setQuanzhi_zhoubian(String quanzhi_zhoubian) {
        this.quanzhi_zhoubian = quanzhi_zhoubian;
    }

    public String getFangzhi_guanli() {
        return fangzhi_guanli;
    }

    public void setFangzhi_guanli(String fangzhi_guanli) {
        this.fangzhi_guanli = fangzhi_guanli;
    }

    public String getQuanzhi_fangyi() {
        return quanzhi_fangyi;
    }

    public void setQuanzhi_fangyi(String quanzhi_fangyi) {
        this.quanzhi_fangyi = quanzhi_fangyi;
    }

    public String getQuanfen_chuli() {
        return quanfen_chuli;
    }

    public void setQuanfen_chuli(String quanfen_chuli) {
        this.quanfen_chuli = quanfen_chuli;
    }

    public String getQuanshi_chuli() {
        return quanshi_chuli;
    }

    public void setQuanshi_chuli(String quanshi_chuli) {
        this.quanshi_chuli = quanshi_chuli;
    }

    public String getQuanfenkangyuan_jiance() {
        return quanfenkangyuan_jiance;
    }

    public void setQuanfenkangyuan_jiance(String quanfenkangyuan_jiance) {
        this.quanfenkangyuan_jiance = quanfenkangyuan_jiance;
    }

    public String getQuanhzi_jiepao() {
        return quanhzi_jiepao;
    }

    public void setQuanhzi_jiepao(String quanhzi_jiepao) {
        this.quanhzi_jiepao = quanhzi_jiepao;
    }

    public String getNiuyangkangti_jiance() {
        return niuyangkangti_jiance;
    }

    public void setNiuyangkangti_jiance(String niuyangkangti_jiance) {
        this.niuyangkangti_jiance = niuyangkangti_jiance;
    }

    public String getNiuyangzangqi_chuli() {
        return niuyangzangqi_chuli;
    }

    public void setNiuyangzangqi_chuli(String niuyangzangqi_chuli) {
        this.niuyangzangqi_chuli = niuyangzangqi_chuli;
    }

    public String getTongji_xinxi() {
        return tongji_xinxi;
    }

    public void setTongji_xinxi(String tongji_xinxi) {
        this.tongji_xinxi = tongji_xinxi;
    }

    public String getWuzi_guanli() {
        return wuzi_guanli;
    }

    public void setWuzi_guanli(String wuzi_guanli) {
        this.wuzi_guanli = wuzi_guanli;
    }

    public String getFanghuwuzi() {
        return fanghuwuzi;
    }

    public void setFanghuwuzi(String fanghuwuzi) {
        this.fanghuwuzi = fanghuwuzi;
    }

    public String getQuanzhu_xianquan() {
        return quanzhu_xianquan;
    }

    public void setQuanzhu_xianquan(String quanzhu_xianquan) {
        this.quanzhu_xianquan = quanzhu_xianquan;
    }

    public String getXuanchuan_peixun() {
        return xuanchuan_peixun;
    }

    public void setXuanchuan_peixun(String xuanchuan_peixun) {
        this.xuanchuan_peixun = xuanchuan_peixun;
    }

    public String getXuanchuan_huodong() {
        return xuanchuan_huodong;
    }

    public void setXuanchuan_huodong(String xuanchuan_huodong) {
        this.xuanchuan_huodong = xuanchuan_huodong;
    }

    public String getPeixunhuodong() {
        return peixunhuodong;
    }

    public void setPeixunhuodong(String peixunhuodong) {
        this.peixunhuodong = peixunhuodong;
    }

    public String getQuanzhu_xinxi() {
        return quanzhu_xinxi;
    }

    public void setQuanzhu_xinxi(String quanzhu_xinxi) {
        this.quanzhu_xinxi = quanzhu_xinxi;
    }

    public String getQuanzhu_xingming() {
        return quanzhu_xingming;
    }

    public void setQuanzhu_xingming(String quanzhu_xingming) {
        this.quanzhu_xingming = quanzhu_xingming;
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }

    public String getNan() {
        return nan;
    }

    public void setNan(String nan) {
        this.nan = nan;
    }

    public String getNv() {
        return nv;
    }

    public void setNv(String nv) {
        this.nv = nv;
    }

    public String getChushengriqi() {
        return chushengriqi;
    }

    public void setChushengriqi(String chushengriqi) {
        this.chushengriqi = chushengriqi;
    }

    public String getMingzu() {
        return mingzu;
    }

    public void setMingzu(String mingzu) {
        this.mingzu = mingzu;
    }

    public String getWenhuachengdu() {
        return wenhuachengdu;
    }

    public void setWenhuachengdu(String wenhuachengdu) {
        this.wenhuachengdu = wenhuachengdu;
    }

    public String getLianxidianhua() {
        return lianxidianhua;
    }

    public void setLianxidianhua(String lianxidianhua) {
        this.lianxidianhua = lianxidianhua;
    }

    public String getZhengjian_leix() {
        return zhengjian_leix;
    }

    public void setZhengjian_leix(String zhengjian_leix) {
        this.zhengjian_leix = zhengjian_leix;
    }

    public String getZhengjian_haoma() {
        return zhengjian_haoma;
    }

    public void setZhengjian_haoma(String zhengjian_haoma) {
        this.zhengjian_haoma = zhengjian_haoma;
    }

    public String getCaochangmianji() {
        return caochangmianji;
    }

    public void setCaochangmianji(String caochangmianji) {
        this.caochangmianji = caochangmianji;
    }

    public String getSuoshuxiangzhen() {
        return suoshuxiangzhen;
    }

    public void setSuoshuxiangzhen(String suoshuxiangzhen) {
        this.suoshuxiangzhen = suoshuxiangzhen;
    }

    public String getSuoshucunweihui() {
        return suoshucunweihui;
    }

    public void setSuoshucunweihui(String suoshucunweihui) {
        this.suoshucunweihui = suoshucunweihui;
    }

    public String getMenpaihao() {
        return menpaihao;
    }

    public void setMenpaihao(String menpaihao) {
        this.menpaihao = menpaihao;
    }

    public String getFangyiyuan() {
        return fangyiyuan;
    }

    public void setFangyiyuan(String fangyiyuan) {
        this.fangyiyuan = fangyiyuan;
    }

    public String getLvrushijian() {
        return lvrushijian;
    }

    public void setLvrushijian(String lvrushijian) {
        this.lvrushijian = lvrushijian;
    }

    public String getZhaopian() {
        return zhaopian;
    }

    public void setZhaopian(String zhaopian) {
        this.zhaopian = zhaopian;
    }

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public String getQuanzhi_xinxi() {
        return quanzhi_xinxi;
    }

    public void setQuanzhi_xinxi(String quanzhi_xinxi) {
        this.quanzhi_xinxi = quanzhi_xinxi;
    }

    public String getWeibangdxiangquan() {
        return weibangdxiangquan;
    }

    public void setWeibangdxiangquan(String weibangdxiangquan) {
        this.weibangdxiangquan = weibangdxiangquan;
    }

    public String getYangxinglv() {
        return yangxinglv;
    }

    public void setYangxinglv(String yangxinglv) {
        this.yangxinglv = yangxinglv;
    }

    public String getJianceshijian() {
        return jianceshijian;
    }

    public void setJianceshijian(String jianceshijian) {
        this.jianceshijian = jianceshijian;
    }

    public String getJiancedanwei() {
        return jiancedanwei;
    }

    public void setJiancedanwei(String jiancedanwei) {
        this.jiancedanwei = jiancedanwei;
    }

    public String getJianceshu() {
        return jianceshu;
    }

    public void setJianceshu(String jianceshu) {
        this.jianceshu = jianceshu;
    }

    public String getYangxingshu() {
        return yangxingshu;
    }

    public void setYangxingshu(String yangxingshu) {
        this.yangxingshu = yangxingshu;
    }

    public String getWuhaihua() {
        return wuhaihua;
    }

    public void setWuhaihua(String wuhaihua) {
        this.wuhaihua = wuhaihua;
    }

    public String getTupian() {
        return tupian;
    }

    public void setTupian(String tupian) {
        this.tupian = tupian;
    }

    public String getShipin() {
        return shipin;
    }

    public void setShipin(String shipin) {
        this.shipin = shipin;
    }

    public String getFangyishijan() {
        return fangyishijan;
    }

    public void setFangyishijan(String fangyishijan) {
        this.fangyishijan = fangyishijan;
    }

    public String getQuanming() {
        return quanming;
    }

    public void setQuanming(String quanming) {
        this.quanming = quanming;
    }

    public String getQuanzhu() {
        return quanzhu;
    }

    public void setQuanzhu(String quanzhu) {
        this.quanzhu = quanzhu;
    }

    public String getSuyuan_ID() {
        return suyuan_ID;
    }

    public void setSuyuan_ID(String suyuan_ID) {
        this.suyuan_ID = suyuan_ID;
    }

    public String getXiangquanbianhao() {
        return xiangquanbianhao;
    }

    public void setXiangquanbianhao(String xiangquanbianhao) {
        this.xiangquanbianhao = xiangquanbianhao;
    }

    public String getQuanzhudianhua() {
        return quanzhudianhua;
    }

    public void setQuanzhudianhua(String quanzhudianhua) {
        this.quanzhudianhua = quanzhudianhua;
    }

    public String getQuanzhushengfengzheng() {
        return quanzhushengfengzheng;
    }

    public void setQuanzhushengfengzheng(String quanzhushengfengzheng) {
        this.quanzhushengfengzheng = quanzhushengfengzheng;
    }

    public String getShusuoxiangzheng() {
        return shusuoxiangzheng;
    }

    public void setShusuoxiangzheng(String shusuoxiangzheng) {
        this.shusuoxiangzheng = shusuoxiangzheng;
    }

    public String getXiangxidizhi() {
        return xiangxidizhi;
    }

    public void setXiangxidizhi(String xiangxidizhi) {
        this.xiangxidizhi = xiangxidizhi;
    }

    public String getFangyileixin() {
        return fangyileixin;
    }

    public void setFangyileixin(String fangyileixin) {
        this.fangyileixin = fangyileixin;
    }

    public String getFangyishijian() {
        return fangyishijian;
    }

    public void setFangyishijian(String fangyishijian) {
        this.fangyishijian = fangyishijian;
    }

    public String getFangyiyaopin() {
        return fangyiyaopin;
    }

    public void setFangyiyaopin(String fangyiyaopin) {
        this.fangyiyaopin = fangyiyaopin;
    }

    public String getFangyizhouqi() {
        return fangyizhouqi;
    }

    public void setFangyizhouqi(String fangyizhouqi) {
        this.fangyizhouqi = fangyizhouqi;
    }

    public String getFangyiyuandianhua() {
        return fangyiyuandianhua;
    }

    public void setFangyiyuandianhua(String fangyiyuandianhua) {
        this.fangyiyuandianhua = fangyiyuandianhua;
    }

    public String getHegeshu() {
        return hegeshu;
    }

    public void setHegeshu(String hegeshu) {
        this.hegeshu = hegeshu;
    }

    public String getHegelv() {
        return hegelv;
    }

    public void setHegelv(String hegelv) {
        this.hegelv = hegelv;
    }

    public String getChulishijian() {
        return chulishijian;
    }

    public void setChulishijian(String chulishijian) {
        this.chulishijian = chulishijian;
    }

    public String getSiwangshijian() {
        return siwangshijian;
    }

    public void setSiwangshijian(String siwangshijian) {
        this.siwangshijian = siwangshijian;
    }

    public String getChulifangfa() {
        return chulifangfa;
    }

    public void setChulifangfa(String chulifangfa) {
        this.chulifangfa = chulifangfa;
    }

    public String getFangfashuoming() {
        return fangfashuoming;
    }

    public void setFangfashuoming(String fangfashuoming) {
        this.fangfashuoming = fangfashuoming;
    }

    public String getChulirenyuan() {
        return chulirenyuan;
    }

    public void setChulirenyuan(String chulirenyuan) {
        this.chulirenyuan = chulirenyuan;
    }

    public String getJieshoudanwei() {
        return jieshoudanwei;
    }

    public void setJieshoudanwei(String jieshoudanwei) {
        this.jieshoudanwei = jieshoudanwei;
    }

    public String getJieshouren() {
        return jieshouren;
    }

    public void setJieshouren(String jieshouren) {
        this.jieshouren = jieshouren;
    }

    public String getFafangdanwei() {
        return fafangdanwei;
    }

    public void setFafangdanwei(String fafangdanwei) {
        this.fafangdanwei = fafangdanwei;
    }

    public String getFafang() {
        return fafang;
    }

    public void setFafang(String fafang) {
        this.fafang = fafang;
    }

    public String getFafangshuliang() {
        return fafangshuliang;
    }

    public void setFafangshuliang(String fafangshuliang) {
        this.fafangshuliang = fafangshuliang;
    }

    public String getFafangshijian() {
        return fafangshijian;
    }

    public void setFafangshijian(String fafangshijian) {
        this.fafangshijian = fafangshijian;
    }

    public String getRulanshijian() {
        return rulanshijian;
    }

    public void setRulanshijian(String rulanshijian) {
        this.rulanshijian = rulanshijian;
    }

    public String getTizhong() {
        return tizhong;
    }

    public void setTizhong(String tizhong) {
        this.tizhong = tizhong;
    }

    public String getMaose() {
        return maose;
    }

    public void setMaose(String maose) {
        this.maose = maose;
    }

    public String getQuanling() {
        return quanling;
    }

    public void setQuanling(String quanling) {
        this.quanling = quanling;
    }

    public String getQuanzhong() {
        return quanzhong;
    }

    public void setQuanzhong(String quanzhong) {
        this.quanzhong = quanzhong;
    }

    public String getQuanzhicunlanshuliang() {
        return quanzhicunlanshuliang;
    }

    public void setQuanzhicunlanshuliang(String quanzhicunlanshuliang) {
        this.quanzhicunlanshuliang = quanzhicunlanshuliang;
    }

    public String getSuoshuzhou() {
        return suoshuzhou;
    }

    public void setSuoshuzhou(String suoshuzhou) {
        this.suoshuzhou = suoshuzhou;
    }

    public String getSuoshuxian() {
        return suoshuxian;
    }

    public void setSuoshuxian(String suoshuxian) {
        this.suoshuxian = suoshuxian;
    }

    public String getSuoshuxiang() {
        return suoshuxiang;
    }

    public void setSuoshuxiang(String suoshuxiang) {
        this.suoshuxiang = suoshuxiang;
    }

    public String getSuoshufangyiyuan() {
        return suoshufangyiyuan;
    }

    public void setSuoshufangyiyuan(String suoshufangyiyuan) {
        this.suoshufangyiyuan = suoshufangyiyuan;
    }

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }

    public String getQuanzhushuliang() {
        return quanzhushuliang;
    }

    public void setQuanzhushuliang(String quanzhushuliang) {
        this.quanzhushuliang = quanzhushuliang;
    }

    public String getLiulangquanchulishuliang() {
        return liulangquanchulishuliang;
    }

    public void setLiulangquanchulishuliang(String liulangquanchulishuliang) {
        this.liulangquanchulishuliang = liulangquanchulishuliang;
    }

    public String getFangyicishu() {
        return fangyicishu;
    }

    public void setFangyicishu(String fangyicishu) {
        this.fangyicishu = fangyicishu;
    }

    public String getShitichulicishu() {
        return shitichulicishu;
    }

    public void setShitichulicishu(String shitichulicishu) {
        this.shitichulicishu = shitichulicishu;
    }

    public String getQuanfenchulicishu() {
        return quanfenchulicishu;
    }

    public void setQuanfenchulicishu(String quanfenchulicishu) {
        this.quanfenchulicishu = quanfenchulicishu;
    }

    public String getQuanfenkangyuancishu() {
        return quanfenkangyuancishu;
    }

    public void setQuanfenkangyuancishu(String quanfenkangyuancishu) {
        this.quanfenkangyuancishu = quanfenkangyuancishu;
    }

    public String getQuanzhijiepaocishu() {
        return quanzhijiepaocishu;
    }

    public void setQuanzhijiepaocishu(String quanzhijiepaocishu) {
        this.quanzhijiepaocishu = quanzhijiepaocishu;
    }

    public String getNiuyangjiancecishu() {
        return niuyangjiancecishu;
    }

    public void setNiuyangjiancecishu(String niuyangjiancecishu) {
        this.niuyangjiancecishu = niuyangjiancecishu;
    }

    public String getNiuyangzangqichulicishu() {
        return niuyangzangqichulicishu;
    }

    public void setNiuyangzangqichulicishu(String niuyangzangqichulicishu) {
        this.niuyangzangqichulicishu = niuyangzangqichulicishu;
    }

    public String getCishu() {
        return cishu;
    }

    public void setCishu(String cishu) {
        this.cishu = cishu;
    }

    public String getShebeibianhao() {
        return shebeibianhao;
    }

    public void setShebeibianhao(String shebeibianhao) {
        this.shebeibianhao = shebeibianhao;
    }

    public String getShenfengzhenghaoma() {
        return shenfengzhenghaoma;
    }

    public void setShenfengzhenghaoma(String shenfengzhenghaoma) {
        this.shenfengzhenghaoma = shenfengzhenghaoma;
    }

    public String getWuzileixing() {
        return wuzileixing;
    }

    public void setWuzileixing(String wuzileixing) {
        this.wuzileixing = wuzileixing;
    }

    public String getWuzimingcheng() {
        return wuzimingcheng;
    }

    public void setWuzimingcheng(String wuzimingcheng) {
        this.wuzimingcheng = wuzimingcheng;
    }

    public String getFasongshijian() {
        return fasongshijian;
    }

    public void setFasongshijian(String fasongshijian) {
        this.fasongshijian = fasongshijian;
    }

    public String getQumingshengfengzheng() {
        return qumingshengfengzheng;
    }

    public void setQumingshengfengzheng(String qumingshengfengzheng) {
        this.qumingshengfengzheng = qumingshengfengzheng;
    }

    public String getJuminghukoubo() {
        return juminghukoubo;
    }

    public void setJuminghukoubo(String juminghukoubo) {
        this.juminghukoubo = juminghukoubo;
    }

    public String getFenbianchulixinxi() {
        return fenbianchulixinxi;
    }

    public void setFenbianchulixinxi(String fenbianchulixinxi) {
        this.fenbianchulixinxi = fenbianchulixinxi;
    }

    public String getFangyixinxi() {
        return fangyixinxi;
    }

    public void setFangyixinxi(String fangyixinxi) {
        this.fangyixinxi = fangyixinxi;
    }

    public String getSuoshumoweihui() {
        return suoshumoweihui;
    }

    public void setSuoshumoweihui(String suoshumoweihui) {
        this.suoshumoweihui = suoshumoweihui;
    }

    public String getDianziweilan() {
        return dianziweilan;
    }

    public void setDianziweilan(String dianziweilan) {
        this.dianziweilan = dianziweilan;
    }

    public String getHuodongshijian() {
        return huodongshijian;
    }

    public void setHuodongshijian(String huodongshijian) {
        this.huodongshijian = huodongshijian;
    }

    public String getJibandanwei() {
        return jibandanwei;
    }

    public void setJibandanwei(String jibandanwei) {
        this.jibandanwei = jibandanwei;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public String getFafangxuanchuanpinshuliang() {
        return fafangxuanchuanpinshuliang;
    }

    public void setFafangxuanchuanpinshuliang(String fafangxuanchuanpinshuliang) {
        this.fafangxuanchuanpinshuliang = fafangxuanchuanpinshuliang;
    }

    public String getZhuti() {
        return zhuti;
    }

    public void setZhuti(String zhuti) {
        this.zhuti = zhuti;
    }

    public String getDidian() {
        return didian;
    }

    public void setDidian(String didian) {
        this.didian = didian;
    }

    public String getShijian() {
        return shijian;
    }

    public void setShijian(String shijian) {
        this.shijian = shijian;
    }

    public String getTuichudenglu() {
        return tuichudenglu;
    }

    public void setTuichudenglu(String tuichudenglu) {
        this.tuichudenglu = tuichudenglu;
    }

    public String getMiamashezhi() {
        return miamashezhi;
    }

    public void setMiamashezhi(String miamashezhi) {
        this.miamashezhi = miamashezhi;
    }

    public String getGerenxinxi() {
        return gerenxinxi;
    }

    public void setGerenxinxi(String gerenxinxi) {
        this.gerenxinxi = gerenxinxi;
    }

    public String getQuerenmima() {
        return querenmima;
    }

    public void setQuerenmima(String querenmima) {
        this.querenmima = querenmima;
    }

    public String getXinmima() {
        return xinmima;
    }

    public void setXinmima(String xinmima) {
        this.xinmima = xinmima;
    }

    public String getJiumima() {
        return jiumima;
    }

    public void setJiumima(String jiumima) {
        this.jiumima = jiumima;
    }

    public String getQuanzhishu() {
        return quanzhishu;
    }

    public void setQuanzhishu(String quanzhishu) {
        this.quanzhishu = quanzhishu;
    }

    public String getYifangyi() {
        return yifangyi;
    }

    public void setYifangyi(String yifangyi) {
        this.yifangyi = yifangyi;
    }

    public String getWeifangyi() {
        return weifangyi;
    }

    public void setWeifangyi(String weifangyi) {
        this.weifangyi = weifangyi;
    }

    public String getChulifangshi() {
        return chulifangshi;
    }

    public void setChulifangshi(String chulifangshi) {
        this.chulifangshi = chulifangshi;
    }

    public String getGong_sex() {
        return gong_sex;
    }

    public void setGong_sex(String gong_sex) {
        this.gong_sex = gong_sex;
    }

    public String getMu_sex() {
        return mu_sex;
    }

    public void setMu_sex(String mu_sex) {
        this.mu_sex = mu_sex;
    }

    public String getChuliren() {
        return chuliren;
    }

    public void setChuliren(String chuliren) {
        this.chuliren = chuliren;
    }

    public String getPingzhong() {
        return pingzhong;
    }

    public void setPingzhong(String pingzhong) {
        this.pingzhong = pingzhong;
    }

    public String getLiulangquanbianhao() {
        return liulangquanbianhao;
    }

    public void setLiulangquanbianhao(String liulangquanbianhao) {
        this.liulangquanbianhao = liulangquanbianhao;
    }

    public String getCaiyurenshu() {
        return caiyurenshu;
    }

    public void setCaiyurenshu(String caiyurenshu) {
        this.caiyurenshu = caiyurenshu;
    }

    public String getPeixunduixiang() {
        return peixunduixiang;
    }

    public void setPeixunduixiang(String peixunduixiang) {
        this.peixunduixiang = peixunduixiang;
    }

    public String getYonghuming() {
        return yonghuming;
    }

    public void setYonghuming(String yonghuming) {
        this.yonghuming = yonghuming;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getSuoshuquyu() {
        return suoshuquyu;
    }

    public void setSuoshuquyu(String suoshuquyu) {
        this.suoshuquyu = suoshuquyu;
    }

    public String getSuoshuzuzhi() {
        return suoshuzuzhi;
    }

    public void setSuoshuzuzhi(String suoshuzuzhi) {
        this.suoshuzuzhi = suoshuzuzhi;
    }

    public String getBinbianzangqichuli() {
        return binbianzangqichuli;
    }

    public void setBinbianzangqichuli(String binbianzangqichuli) {
        this.binbianzangqichuli = binbianzangqichuli;
    }

    public String getBinbianzangqichulishu() {
        return binbianzangqichulishu;
    }

    public void setBinbianzangqichulishu(String binbianzangqichulishu) {
        this.binbianzangqichulishu = binbianzangqichulishu;
    }

    public String getGanranshu() {
        return ganranshu;
    }

    public void setGanranshu(String ganranshu) {
        this.ganranshu = ganranshu;
    }

    public String getDiaochashu() {
        return diaochashu;
    }

    public void setDiaochashu(String diaochashu) {
        this.diaochashu = diaochashu;
    }

    public String getXiangquanchuli() {
        return xiangquanchuli;
    }

    public void setXiangquanchuli(String xiangquanchuli) {
        this.xiangquanchuli = xiangquanchuli;
    }

    public String getDianhua() {
        return dianhua;
    }

    public void setDianhua(String dianhua) {
        this.dianhua = dianhua;
    }

    public String getDianhuahaoma() {
        return dianhuahaoma;
    }

    public void setDianhuahaoma(String dianhuahaoma) {
        this.dianhuahaoma = dianhuahaoma;
    }

    public String getZhuxiaoshijian() {
        return zhuxiaoshijian;
    }

    public void setZhuxiaoshijian(String zhuxiaoshijian) {
        this.zhuxiaoshijian = zhuxiaoshijian;
    }

    public String getZhuxiaoyuanyin() {
        return zhuxiaoyuanyin;
    }

    public void setZhuxiaoyuanyin(String zhuxiaoyuanyin) {
        this.zhuxiaoyuanyin = zhuxiaoyuanyin;
    }

    public String getFangyiyuanxingming() {
        return fangyiyuanxingming;
    }

    public void setFangyiyuanxingming(String fangyiyuanxingming) {
        this.fangyiyuanxingming = fangyiyuanxingming;
    }

    public String getQuerenbangdin() {
        return querenbangdin;
    }

    public void setQuerenbangdin(String querenbangdin) {
        this.querenbangdin = querenbangdin;
    }

    public String getWeidin() {
        return weidin;
    }

    public void setWeidin(String weidin) {
        this.weidin = weidin;
    }

    public String getScan_bind_device() {
        return scan_bind_device;
    }

    public void setScan_bind_device(String scan_bind_device) {
        this.scan_bind_device = scan_bind_device;
    }

    public String getZanbubangdin() {
        return zanbubangdin;
    }

    public void setZanbubangdin(String zanbubangdin) {
        this.zanbubangdin = zanbubangdin;
    }

    public String getDinweichaxun() {
        return dinweichaxun;
    }

    public void setDinweichaxun(String dinweichaxun) {
        this.dinweichaxun = dinweichaxun;
    }

    public String getGuijichaxun() {
        return guijichaxun;
    }

    public void setGuijichaxun(String guijichaxun) {
        this.guijichaxun = guijichaxun;
    }

    public String getRiqi() {
        return riqi;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }

    public String getWeiduxiaoxi() {
        return weiduxiaoxi;
    }

    public void setWeiduxiaoxi(String weiduxiaoxi) {
        this.weiduxiaoxi = weiduxiaoxi;
    }

    public String getYiduxiaoxi() {
        return yiduxiaoxi;
    }

    public void setYiduxiaoxi(String yiduxiaoxi) {
        this.yiduxiaoxi = yiduxiaoxi;
    }

    public String getXiangqing() {
        return xiangqing;
    }

    public void setXiangqing(String xiangqing) {
        this.xiangqing = xiangqing;
    }

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    public String getGenghuanwei() {
        return genghuanwei;
    }

    public void setGenghuanwei(String genghuanwei) {
        this.genghuanwei = genghuanwei;
    }

    public String getGenghuanshijian() {
        return genghuanshijian;
    }

    public void setGenghuanshijian(String genghuanshijian) {
        this.genghuanshijian = genghuanshijian;
    }

    public String getGenghuanyuanyin() {
        return genghuanyuanyin;
    }

    public void setGenghuanyuanyin(String genghuanyuanyin) {
        this.genghuanyuanyin = genghuanyuanyin;
    }

    public String getXinshebeibianhao() {
        return xinshebeibianhao;
    }

    public void setXinshebeibianhao(String xinshebeibianhao) {
        this.xinshebeibianhao = xinshebeibianhao;
    }

    public String getJiushebeibianhao() {
        return jiushebeibianhao;
    }

    public void setJiushebeibianhao(String jiushebeibianhao) {
        this.jiushebeibianhao = jiushebeibianhao;
    }

    public String getYuanxiangquanchuli() {
        return yuanxiangquanchuli;
    }

    public void setYuanxiangquanchuli(String yuanxiangquanchuli) {
        this.yuanxiangquanchuli = yuanxiangquanchuli;
    }

    public String getXinxiangquan() {
        return xinxiangquan;
    }

    public void setXinxiangquan(String xinxiangquan) {
        this.xinxiangquan = xinxiangquan;
    }

    public String getXinquanzhu() {
        return xinquanzhu;
    }

    public void setXinquanzhu(String xinquanzhu) {
        this.xinquanzhu = xinquanzhu;
    }

    public String getJiuquanzhu() {
        return jiuquanzhu;
    }

    public void setJiuquanzhu(String jiuquanzhu) {
        this.jiuquanzhu = jiuquanzhu;
    }

    public String getYuyanshezhi() {
        return yuyanshezhi;
    }

    public void setYuyanshezhi(String yuyanshezhi) {
        this.yuyanshezhi = yuyanshezhi;
    }

    public String getKaiqi() {
        return kaiqi;
    }

    public void setKaiqi(String kaiqi) {
        this.kaiqi = kaiqi;
    }

    public String getGuanbi() {
        return guanbi;
    }

    public void setGuanbi(String guanbi) {
        this.guanbi = guanbi;
    }

    public String getXiangquangenghuancishu() {
        return xiangquangenghuancishu;
    }

    public void setXiangquangenghuancishu(String xiangquangenghuancishu) {
        this.xiangquangenghuancishu = xiangquangenghuancishu;
    }

    public String getFengbianchulicishu() {
        return fengbianchulicishu;
    }

    public void setFengbianchulicishu(String fengbianchulicishu) {
        this.fengbianchulicishu = fengbianchulicishu;
    }

    public String getQuanshichulishuliang() {
        return quanshichulishuliang;
    }

    public void setQuanshichulishuliang(String quanshichulishuliang) {
        this.quanshichulishuliang = quanshichulishuliang;
    }

    public String getXinzengquanzhushuliang() {
        return xinzengquanzhushuliang;
    }

    public void setXinzengquanzhushuliang(String xinzengquanzhushuliang) {
        this.xinzengquanzhushuliang = xinzengquanzhushuliang;
    }

    public String getZhuxiaoquanzhishuliang() {
        return zhuxiaoquanzhishuliang;
    }

    public void setZhuxiaoquanzhishuliang(String zhuxiaoquanzhishuliang) {
        this.zhuxiaoquanzhishuliang = zhuxiaoquanzhishuliang;
    }

    public String getXinzengquanzhishuliang() {
        return xinzengquanzhishuliang;
    }

    public void setXinzengquanzhishuliang(String xinzengquanzhishuliang) {
        this.xinzengquanzhishuliang = xinzengquanzhishuliang;
    }

    public String getChaxun() {
        return chaxun;
    }

    public void setChaxun(String chaxun) {
        this.chaxun = chaxun;
    }

    public String getZhi() {
        return zhi;
    }

    public void setZhi(String zhi) {
        this.zhi = zhi;
    }

    public String getFangchanzhe() {
        return fangchanzhe;
    }

    public void setFangchanzhe(String fangchanzhe) {
        this.fangchanzhe = fangchanzhe;
    }

    public String getWodexiaoxi() {
        return wodexiaoxi;
    }

    public void setWodexiaoxi(String wodexiaoxi) {
        this.wodexiaoxi = wodexiaoxi;
    }

    public String getDinweiguiji() {
        return dinweiguiji;
    }

    public void setDinweiguiji(String dinweiguiji) {
        this.dinweiguiji = dinweiguiji;
    }

    public String getQuanzhizhuxiao() {
        return quanzhizhuxiao;
    }

    public void setQuanzhizhuxiao(String quanzhizhuxiao) {
        this.quanzhizhuxiao = quanzhizhuxiao;
    }

    public String getXiangquangenghuan() {
        return xiangquangenghuan;
    }

    public void setXiangquangenghuan(String xiangquangenghuan) {
        this.xiangquangenghuan = xiangquangenghuan;
    }

    public String getLiulangquanchuli() {
        return liulangquanchuli;
    }

    public void setLiulangquanchuli(String liulangquanchuli) {
        this.liulangquanchuli = liulangquanchuli;
    }

    public String getFenbianchuli() {
        return fenbianchuli;
    }

    public void setFenbianchuli(String fenbianchuli) {
        this.fenbianchuli = fenbianchuli;
    }

    public String getQuedin() {
        return quedin;
    }

    public void setQuedin(String quedin) {
        this.quedin = quedin;
    }

    public String getJubanshijian() {
        return jubanshijian;
    }

    public void setJubanshijian(String jubanshijian) {
        this.jubanshijian = jubanshijian;
    }

    public String getGengduo() {
        return gengduo;
    }

    public void setGengduo(String gengduo) {
        this.gengduo = gengduo;
    }

    public String getXuanzhequyu() {
        return xuanzhequyu;
    }

    public void setXuanzhequyu(String xuanzhequyu) {
        this.xuanzhequyu = xuanzhequyu;
    }

    public String getNianling() {
        return nianling;
    }

    public void setNianling(String nianling) {
        this.nianling = nianling;
    }

    public String getShoujihao() {
        return shoujihao;
    }

    public void setShoujihao(String shoujihao) {
        this.shoujihao = shoujihao;
    }

    public String getFangyidengji() {
        return fangyidengji;
    }

    public void setFangyidengji(String fangyidengji) {
        this.fangyidengji = fangyidengji;
    }

    public String getXinzengquanzhu() {
        return xinzengquanzhu;
    }

    public void setXinzengquanzhu(String xinzengquanzhu) {
        this.xinzengquanzhu = xinzengquanzhu;
    }

    public String getXiangquanbangdin() {
        return xiangquanbangdin;
    }

    public void setXiangquanbangdin(String xiangquanbangdin) {
        this.xiangquanbangdin = xiangquanbangdin;
    }

    public String getXinzengquanzxhi() {
        return xinzengquanzxhi;
    }

    public void setXinzengquanzxhi(String xinzengquanzxhi) {
        this.xinzengquanzxhi = xinzengquanzxhi;
    }

    public String getChakanxiangqing() {
        return chakanxiangqing;
    }

    public void setChakanxiangqing(String chakanxiangqing) {
        this.chakanxiangqing = chakanxiangqing;
    }

    public String getQuanzhuxiangqing() {
        return quanzhuxiangqing;
    }

    public void setQuanzhuxiangqing(String quanzhuxiangqing) {
        this.quanzhuxiangqing = quanzhuxiangqing;
    }

    public String getQuanzhuxinxilvru() {
        return quanzhuxinxilvru;
    }

    public void setQuanzhuxinxilvru(String quanzhuxinxilvru) {
        this.quanzhuxinxilvru = quanzhuxinxilvru;
    }

    public String getQuanzhixinxilvru() {
        return quanzhixinxilvru;
    }

    public void setQuanzhixinxilvru(String quanzhixinxilvru) {
        this.quanzhixinxilvru = quanzhixinxilvru;
    }

    public String getBangdinxiangquan() {
        return bangdinxiangquan;
    }

    public void setBangdinxiangquan(String bangdinxiangquan) {
        this.bangdinxiangquan = bangdinxiangquan;
    }

    public String getFangyixinxilvru() {
        return fangyixinxilvru;
    }

    public void setFangyixinxilvru(String fangyixinxilvru) {
        this.fangyixinxilvru = fangyixinxilvru;
    }

    public String getShitiwuhaihuachuli() {
        return shitiwuhaihuachuli;
    }

    public void setShitiwuhaihuachuli(String shitiwuhaihuachuli) {
        this.shitiwuhaihuachuli = shitiwuhaihuachuli;
    }

    public String getKaishishijian() {
        return kaishishijian;
    }

    public void setKaishishijian(String kaishishijian) {
        this.kaishishijian = kaishishijian;
    }

    public String getJieshushijian() {
        return jieshushijian;
    }

    public void setJieshushijian(String jieshushijian) {
        this.jieshushijian = jieshushijian;
    }

    public String getLiulangquanchulilvru() {
        return liulangquanchulilvru;
    }

    public void setLiulangquanchulilvru(String liulangquanchulilvru) {
        this.liulangquanchulilvru = liulangquanchulilvru;
    }

    public String getDenglv() {
        return denglv;
    }

    public void setDenglv(String denglv) {
        this.denglv = denglv;
    }

    public String getWeinengshibieerweima() {
        return weinengshibieerweima;
    }

    public void setWeinengshibieerweima(String weinengshibieerweima) {
        this.weinengshibieerweima = weinengshibieerweima;
    }

    public String getTishi() {
        return tishi;
    }

    public void setTishi(String tishi) {
        this.tishi = tishi;
    }

    public String getGaisuyuanidbucunzai() {
        return gaisuyuanidbucunzai;
    }

    public void setGaisuyuanidbucunzai(String gaisuyuanidbucunzai) {
        this.gaisuyuanidbucunzai = gaisuyuanidbucunzai;
    }


    public String getZheshibitianziduan() {
        return zheshibitianziduan;
    }

    public void setZheshibitianziduan(String zheshibitianziduan) {
        this.zheshibitianziduan = zheshibitianziduan;
    }

    public String getQingshuruyouxiaodedianziyoujiandizhi() {
        return qingshuruyouxiaodedianziyoujiandizhi;
    }

    public void setQingshuruyouxiaodedianziyoujiandizhi(String qingshuruyouxiaodedianziyoujiandizhi) {
        this.qingshuruyouxiaodedianziyoujiandizhi = qingshuruyouxiaodedianziyoujiandizhi;
    }

    public String getQingshuru11weishoujihao() {
        return qingshuru11weishoujihao;
    }

    public void setQingshuru11weishoujihao(String qingshuru11weishoujihao) {
        this.qingshuru11weishoujihao = qingshuru11weishoujihao;
    }

    public String getQingshuruyouxiaodewangzhi() {
        return qingshuruyouxiaodewangzhi;
    }

    public void setQingshuruyouxiaodewangzhi(String qingshuruyouxiaodewangzhi) {
        this.qingshuruyouxiaodewangzhi = qingshuruyouxiaodewangzhi;
    }

    public String getQingshuruyouxiaoriqi() {
        return qingshuruyouxiaoriqi;
    }

    public void setQingshuruyouxiaoriqi(String qingshuruyouxiaoriqi) {
        this.qingshuruyouxiaoriqi = qingshuruyouxiaoriqi;
    }

    public String getLiru() {
        return liru;
    }

    public void setLiru(String liru) {
        this.liru = liru;
    }

    public String getZhinengshurushuzi() {
        return zhinengshurushuzi;
    }

    public void setZhinengshurushuzi(String zhinengshurushuzi) {
        this.zhinengshurushuzi = zhinengshurushuzi;
    }

    public String getQingshuru18weiyouxiaozhenghao() {
        return qingshuru18weiyouxiaozhenghao;
    }

    public void setQingshuru18weiyouxiaozhenghao(String qingshuru18weiyouxiaozhenghao) {
        this.qingshuru18weiyouxiaozhenghao = qingshuru18weiyouxiaozhenghao;
    }

    public String getShuruzhibixudengyu() {
        return shuruzhibixudengyu;
    }

    public void setShuruzhibixudengyu(String shuruzhibixudengyu) {
        this.shuruzhibixudengyu = shuruzhibixudengyu;
    }

    public String getShuruzhibixubaohan() {
        return shuruzhibixubaohan;
    }

    public void setShuruzhibixubaohan(String shuruzhibixubaohan) {
        this.shuruzhibixubaohan = shuruzhibixubaohan;
    }

    public String getShuruzuixiaochangduwei() {
        return shuruzuixiaochangduwei;
    }

    public void setShuruzuixiaochangduwei(String shuruzuixiaochangduwei) {
        this.shuruzuixiaochangduwei = shuruzuixiaochangduwei;
    }

    public String getShuruzuidachangduwei() {
        return shuruzuidachangduwei;
    }

    public void setShuruzuidachangduwei(String shuruzuidachangduwei) {
        this.shuruzuidachangduwei = shuruzuidachangduwei;
    }

    public String getGezifu() {
        return gezifu;
    }

    public void setGezifu(String gezifu) {
        this.gezifu = gezifu;
    }

    public String getShuruchangdubixuwei() {
        return shuruchangdubixuwei;
    }

    public void setShuruchangdubixuwei(String shuruchangdubixuwei) {
        this.shuruchangdubixuwei = shuruchangdubixuwei;
    }

    public String getQingshurubuxiaoyu() {
        return qingshurubuxiaoyu;
    }

    public void setQingshurubuxiaoyu(String qingshurubuxiaoyu) {
        this.qingshurubuxiaoyu = qingshurubuxiaoyu;
    }

    public String getQingshurubudayu() {
        return qingshurubudayu;
    }

    public void setQingshurubudayu(String qingshurubudayu) {
        this.qingshurubudayu = qingshurubudayu;
    }

    public String getShurufanweizai() {
        return shurufanweizai;
    }

    public void setShurufanweizai(String shurufanweizai) {
        this.shurufanweizai = shurufanweizai;
    }

    public String getQingtianxiejianceshu() {
        return qingtianxiejianceshu;
    }

    public void setQingtianxiejianceshu(String qingtianxiejianceshu) {
        this.qingtianxiejianceshu = qingtianxiejianceshu;
    }

    public String getJianceshutianxiebuzhengque() {
        return jianceshutianxiebuzhengque;
    }

    public void setJianceshutianxiebuzhengque(String jianceshutianxiebuzhengque) {
        this.jianceshutianxiebuzhengque = jianceshutianxiebuzhengque;
    }

    public String getQingtianxieyangxingshu() {
        return qingtianxieyangxingshu;
    }

    public void setQingtianxieyangxingshu(String qingtianxieyangxingshu) {
        this.qingtianxieyangxingshu = qingtianxieyangxingshu;
    }

    public String getYangxingshutianxiebuzhengque() {
        return yangxingshutianxiebuzhengque;
    }

    public void setYangxingshutianxiebuzhengque(String yangxingshutianxiebuzhengque) {
        this.yangxingshutianxiebuzhengque = yangxingshutianxiebuzhengque;
    }

    public String getQingtianxieyangxinglv() {
        return qingtianxieyangxinglv;
    }

    public void setQingtianxieyangxinglv(String qingtianxieyangxinglv) {
        this.qingtianxieyangxinglv = qingtianxieyangxinglv;
    }

    public String getYangxinglvtianxiebuzhengque() {
        return yangxinglvtianxiebuzhengque;
    }

    public void setYangxinglvtianxiebuzhengque(String yangxinglvtianxiebuzhengque) {
        this.yangxinglvtianxiebuzhengque = yangxinglvtianxiebuzhengque;
    }

    public String getQingtianxiewuhaihuachulishu() {
        return qingtianxiewuhaihuachulishu;
    }

    public void setQingtianxiewuhaihuachulishu(String qingtianxiewuhaihuachulishu) {
        this.qingtianxiewuhaihuachulishu = qingtianxiewuhaihuachulishu;
    }

    public String getWuhaihuachulishutianxiebuzhengque() {
        return wuhaihuachulishutianxiebuzhengque;
    }

    public void setWuhaihuachulishutianxiebuzhengque(String wuhaihuachulishutianxiebuzhengque) {
        this.wuhaihuachulishutianxiebuzhengque = wuhaihuachulishutianxiebuzhengque;
    }

    public String getQingtianxiehegeshu() {
        return qingtianxiehegeshu;
    }

    public void setQingtianxiehegeshu(String qingtianxiehegeshu) {
        this.qingtianxiehegeshu = qingtianxiehegeshu;
    }

    public String getHegeshutianxiebuzhengque() {
        return hegeshutianxiebuzhengque;
    }

    public void setHegeshutianxiebuzhengque(String hegeshutianxiebuzhengque) {
        this.hegeshutianxiebuzhengque = hegeshutianxiebuzhengque;
    }

    public String getQingtianxieshuyuanID() {
        return qingtianxieshuyuanID;
    }

    public void setQingtianxieshuyuanID(String qingtianxieshuyuanID) {
        this.qingtianxieshuyuanID = qingtianxieshuyuanID;
    }

    public String getQingshuruzhengquedeshuliang() {
        return qingshuruzhengquedeshuliang;
    }

    public void setQingshuruzhengquedeshuliang(String qingshuruzhengquedeshuliang) {
        this.qingshuruzhengquedeshuliang = qingshuruzhengquedeshuliang;
    }

    public String getWeifangyiquanzhi() {
        return weifangyiquanzhi;
    }

    public void setWeifangyiquanzhi(String weifangyiquanzhi) {
        this.weifangyiquanzhi = weifangyiquanzhi;
    }

    public String getYifangyiquanzhi() {
        return yifangyiquanzhi;
    }

    public void setYifangyiquanzhi(String yifangyiquanzhi) {
        this.yifangyiquanzhi = yifangyiquanzhi;
    }

    public String getQingtianxiedidian() {
        return qingtianxiedidian;
    }

    public void setQingtianxiedidian(String qingtianxiedidian) {
        this.qingtianxiedidian = qingtianxiedidian;
    }

    public String getQingtianxiezhuti() {
        return qingtianxiezhuti;
    }

    public void setQingtianxiezhuti(String qingtianxiezhuti) {
        this.qingtianxiezhuti = qingtianxiezhuti;
    }

    public String getQingtianxiexuanchuanppingshuliang() {
        return qingtianxiexuanchuanppingshuliang;
    }

    public void setQingtianxiexuanchuanppingshuliang(String qingtianxiexuanchuanppingshuliang) {
        this.qingtianxiexuanchuanppingshuliang = qingtianxiexuanchuanppingshuliang;
    }

    public String getXuanchuanpingchuliangbuzhenfgque() {
        return xuanchuanpingchuliangbuzhenfgque;
    }

    public void setXuanchuanpingchuliangbuzhenfgque(String xuanchuanpingchuliangbuzhenfgque) {
        this.xuanchuanpingchuliangbuzhenfgque = xuanchuanpingchuliangbuzhenfgque;
    }

    public String getQingtianxieneirong() {
        return qingtianxieneirong;
    }

    public void setQingtianxieneirong(String qingtianxieneirong) {
        this.qingtianxieneirong = qingtianxieneirong;
    }

    public String getJiumimabunengweikong() {
        return jiumimabunengweikong;
    }

    public void setJiumimabunengweikong(String jiumimabunengweikong) {
        this.jiumimabunengweikong = jiumimabunengweikong;
    }

    public String getXinmimabunengweikong() {
        return xinmimabunengweikong;
    }

    public void setXinmimabunengweikong(String xinmimabunengweikong) {
        this.xinmimabunengweikong = xinmimabunengweikong;
    }

    public String getQuerenmimabunengweikong() {
        return querenmimabunengweikong;
    }

    public void setQuerenmimabunengweikong(String querenmimabunengweikong) {
        this.querenmimabunengweikong = querenmimabunengweikong;
    }

    public String getQingtianxiechulifangshi() {
        return qingtianxiechulifangshi;
    }

    public void setQingtianxiechulifangshi(String qingtianxiechulifangshi) {
        this.qingtianxiechulifangshi = qingtianxiechulifangshi;
    }

    public String getQingtianxiecanyurenshu() {
        return qingtianxiecanyurenshu;
    }

    public void setQingtianxiecanyurenshu(String qingtianxiecanyurenshu) {
        this.qingtianxiecanyurenshu = qingtianxiecanyurenshu;
    }

    public String getCanshurenshutianxiebuzhengque() {
        return canshurenshutianxiebuzhengque;
    }

    public void setCanshurenshutianxiebuzhengque(String canshurenshutianxiebuzhengque) {
        this.canshurenshutianxiebuzhengque = canshurenshutianxiebuzhengque;
    }

    public String getQingtianxiepeixunduixiang() {
        return qingtianxiepeixunduixiang;
    }

    public void setQingtianxiepeixunduixiang(String qingtianxiepeixunduixiang) {
        this.qingtianxiepeixunduixiang = qingtianxiepeixunduixiang;
    }

    public String getQingtianxiedianchashu() {
        return qingtianxiedianchashu;
    }

    public void setQingtianxiedianchashu(String qingtianxiedianchashu) {
        this.qingtianxiedianchashu = qingtianxiedianchashu;
    }

    public String getDianchashutianxiebuzhengque() {
        return dianchashutianxiebuzhengque;
    }

    public void setDianchashutianxiebuzhengque(String dianchashutianxiebuzhengque) {
        this.dianchashutianxiebuzhengque = dianchashutianxiebuzhengque;
    }

    public String getQingtianxieganranshu() {
        return qingtianxieganranshu;
    }

    public void setQingtianxieganranshu(String qingtianxieganranshu) {
        this.qingtianxieganranshu = qingtianxieganranshu;
    }

    public String getGanranshutianxiebuzhengque() {
        return ganranshutianxiebuzhengque;
    }

    public void setGanranshutianxiebuzhengque(String ganranshutianxiebuzhengque) {
        this.ganranshutianxiebuzhengque = ganranshutianxiebuzhengque;
    }

    public String getQingtianxiebingbianchulishu() {
        return qingtianxiebingbianchulishu;
    }

    public void setQingtianxiebingbianchulishu(String qingtianxiebingbianchulishu) {
        this.qingtianxiebingbianchulishu = qingtianxiebingbianchulishu;
    }

    public String getBingbianshutianxiebuzhengque() {
        return bingbianshutianxiebuzhengque;
    }

    public void setBingbianshutianxiebuzhengque(String bingbianshutianxiebuzhengque) {
        this.bingbianshutianxiebuzhengque = bingbianshutianxiebuzhengque;
    }

    public String getQingshuruquanming() {
        return qingshuruquanming;
    }

    public void setQingshuruquanming(String qingshuruquanming) {
        this.qingshuruquanming = qingshuruquanming;
    }

    public String getQingtianxiechulifangfa() {
        return qingtianxiechulifangfa;
    }

    public void setQingtianxiechulifangfa(String qingtianxiechulifangfa) {
        this.qingtianxiechulifangfa = qingtianxiechulifangfa;
    }

    public String getQingtianxiefangfashuoming() {
        return qingtianxiefangfashuoming;
    }

    public void setQingtianxiefangfashuoming(String qingtianxiefangfashuoming) {
        this.qingtianxiefangfashuoming = qingtianxiefangfashuoming;
    }

    public String getQingtianxiexinxiangquanbianhao() {
        return qingtianxiexinxiangquanbianhao;
    }

    public void setQingtianxiexinxiangquanbianhao(String qingtianxiexinxiangquanbianhao) {
        this.qingtianxiexinxiangquanbianhao = qingtianxiexinxiangquanbianhao;
    }

    public String getTianxiexinxiangquanbianhao() {
        return tianxiexinxiangquanbianhao;
    }

    public void setTianxiexinxiangquanbianhao(String tianxiexinxiangquanbianhao) {
        this.tianxiexinxiangquanbianhao = tianxiexinxiangquanbianhao;
    }

    public String getQingtianxiequanzhuxinming() {
        return qingtianxiequanzhuxinming;
    }

    public void setQingtianxiequanzhuxinming(String qingtianxiequanzhuxinming) {
        this.qingtianxiequanzhuxinming = qingtianxiequanzhuxinming;
    }

    public String getQingtianxiedianhuahao() {
        return qingtianxiedianhuahao;
    }

    public void setQingtianxiedianhuahao(String qingtianxiedianhuahao) {
        this.qingtianxiedianhuahao = qingtianxiedianhuahao;
    }

    public String getQingshuruzhengquededianhuahaoma() {
        return qingshuruzhengquededianhuahaoma;
    }

    public void setQingshuruzhengquededianhuahaoma(String qingshuruzhengquededianhuahaoma) {
        this.qingshuruzhengquededianhuahaoma = qingshuruzhengquededianhuahaoma;
    }

    public String getQingtianxiezhengjianhaoma() {
        return qingtianxiezhengjianhaoma;
    }

    public void setQingtianxiezhengjianhaoma(String qingtianxiezhengjianhaoma) {
        this.qingtianxiezhengjianhaoma = qingtianxiezhengjianhaoma;
    }

    public String getQingshuruzhegnquedezhengjianhaoma() {
        return qingshuruzhegnquedezhengjianhaoma;
    }

    public void setQingshuruzhegnquedezhengjianhaoma(String qingshuruzhegnquedezhengjianhaoma) {
        this.qingshuruzhegnquedezhengjianhaoma = qingshuruzhegnquedezhengjianhaoma;
    }

    public String getZhanghaobunengweikong() {
        return zhanghaobunengweikong;
    }

    public void setZhanghaobunengweikong(String zhanghaobunengweikong) {
        this.zhanghaobunengweikong = zhanghaobunengweikong;
    }

    public String getMimabunengweikong() {
        return mimabunengweikong;
    }

    public void setMimabunengweikong(String mimabunengweikong) {
        this.mimabunengweikong = mimabunengweikong;
    }

    public String getZhijin() {
        return zhijin;
    }

    public void setZhijin(String zhijin) {
        this.zhijin = zhijin;
    }

    public String getQingxuanzekaishishijian() {
        return qingxuanzekaishishijian;
    }

    public void setQingxuanzekaishishijian(String qingxuanzekaishishijian) {
        this.qingxuanzekaishishijian = qingxuanzekaishishijian;
    }

    public String getQuanbu() {
        return quanbu;
    }

    public void setQuanbu(String quanbu) {
        this.quanbu = quanbu;
    }

    public String getJingqingqidai() {
        return jingqingqidai;
    }

    public void setJingqingqidai(String jingqingqidai) {
        this.jingqingqidai = jingqingqidai;
    }

    public String getBuxian() {
        return buxian;
    }

    public void setBuxian(String buxian) {
        this.buxian = buxian;
    }

    public String getGaishebeiyidiushi() {
        return gaishebeiyidiushi;
    }

    public void setGaishebeiyidiushi(String gaishebeiyidiushi) {
        this.gaishebeiyidiushi = gaishebeiyidiushi;
    }

    public String getGaishebeiyisunhuai() {
        return gaishebeiyisunhuai;
    }

    public void setGaishebeiyisunhuai(String gaishebeiyisunhuai) {
        this.gaishebeiyisunhuai = gaishebeiyisunhuai;
    }

    public String getYonghuminghuomimacuowu() {
        return yonghuminghuomimacuowu;
    }

    public void setYonghuminghuomimacuowu(String yonghuminghuomimacuowu) {
        this.yonghuminghuomimacuowu = yonghuminghuomimacuowu;
    }

    public String getYangxinshubunengdayujianceshu() {
        return yangxinshubunengdayujianceshu;
    }

    public void setYangxinshubunengdayujianceshu(String yangxinshubunengdayujianceshu) {
        this.yangxinshubunengdayujianceshu = yangxinshubunengdayujianceshu;
    }

    public String getHegeshubunengdayujianceshu() {
        return hegeshubunengdayujianceshu;
    }

    public void setHegeshubunengdayujianceshu(String hegeshubunengdayujianceshu) {
        this.hegeshubunengdayujianceshu = hegeshubunengdayujianceshu;
    }

    public String getXinzengfanghuwuzi() {
        return xinzengfanghuwuzi;
    }

    public void setXinzengfanghuwuzi(String xinzengfanghuwuzi) {
        this.xinzengfanghuwuzi = xinzengfanghuwuzi;
    }

    public String getQingtianxiewuzimingcheng() {
        return qingtianxiewuzimingcheng;
    }

    public void setQingtianxiewuzimingcheng(String qingtianxiewuzimingcheng) {
        this.qingtianxiewuzimingcheng = qingtianxiewuzimingcheng;
    }

    public String getQingtianxiefafangshuliang() {
        return qingtianxiefafangshuliang;
    }

    public void setQingtianxiefafangshuliang(String qingtianxiefafangshuliang) {
        this.qingtianxiefafangshuliang = qingtianxiefafangshuliang;
    }

    public String getShuliangtianxiebuzhengque() {
        return shuliangtianxiebuzhengque;
    }

    public void setShuliangtianxiebuzhengque(String shuliangtianxiebuzhengque) {
        this.shuliangtianxiebuzhengque = shuliangtianxiebuzhengque;
    }

    public String getShurudejiumimayouwu() {
        return shurudejiumimayouwu;
    }

    public void setShurudejiumimayouwu(String shurudejiumimayouwu) {
        this.shurudejiumimayouwu = shurudejiumimayouwu;
    }

    public String getQuedingyaoshanchucitupianma() {
        return quedingyaoshanchucitupianma;
    }

    public void setQuedingyaoshanchucitupianma(String quedingyaoshanchucitupianma) {
        this.quedingyaoshanchucitupianma = quedingyaoshanchucitupianma;
    }

    public String getZuiduoshangchuansanzhang() {
        return zuiduoshangchuansanzhang;
    }

    public void setZuiduoshangchuansanzhang(String zuiduoshangchuansanzhang) {
        this.zuiduoshangchuansanzhang = zuiduoshangchuansanzhang;
    }

    public String getHanzu() {
        return hanzu;
    }

    public void setHanzu(String hanzu) {
        this.hanzu = hanzu;
    }

    public String getZangzu() {
        return zangzu;
    }

    public void setZangzu(String zangzu) {
        this.zangzu = zangzu;
    }

    public String getXiaoxue() {
        return xiaoxue;
    }

    public void setXiaoxue(String xiaoxue) {
        this.xiaoxue = xiaoxue;
    }

    public String getChuzhong() {
        return chuzhong;
    }

    public void setChuzhong(String chuzhong) {
        this.chuzhong = chuzhong;
    }

    public String getGaozhong() {
        return gaozhong;
    }

    public void setGaozhong(String gaozhong) {
        this.gaozhong = gaozhong;
    }

    public String getZhongzhuan() {
        return zhongzhuan;
    }

    public void setZhongzhuan(String zhongzhuan) {
        this.zhongzhuan = zhongzhuan;
    }

    public String getDazhuan() {
        return dazhuan;
    }

    public void setDazhuan(String dazhuan) {
        this.dazhuan = dazhuan;
    }

    public String getBenkejiyishang() {
        return benkejiyishang;
    }

    public void setBenkejiyishang(String benkejiyishang) {
        this.benkejiyishang = benkejiyishang;
    }

    public String getZhengchang() {
        return zhengchang;
    }

    public void setZhengchang(String zhengchang) {
        this.zhengchang = zhengchang;
    }

    public String getMianyichaoqi() {
        return mianyichaoqi;
    }

    public void setMianyichaoqi(String mianyichaoqi) {
        this.mianyichaoqi = mianyichaoqi;
    }

    public String getZanweibangdinxiangquan() {
        return zanweibangdinxiangquan;
    }

    public void setZanweibangdinxiangquan(String zanweibangdinxiangquan) {
        this.zanweibangdinxiangquan = zanweibangdinxiangquan;
    }

    public String getChunfang() {
        return chunfang;
    }

    public void setChunfang(String chunfang) {
        this.chunfang = chunfang;
    }

    public String getQiufang() {
        return qiufang;
    }

    public void setQiufang(String qiufang) {
        this.qiufang = qiufang;
    }

    public String getYueyuetouyao() {
        return yueyuetouyao;
    }

    public void setYueyuetouyao(String yueyuetouyao) {
        this.yueyuetouyao = yueyuetouyao;
    }

    public String getXiazaizhong() {
        return xiazaizhong;
    }

    public void setXiazaizhong(String xiazaizhong) {
        this.xiazaizhong = xiazaizhong;
    }

    public String getGengxintishi() {
        return gengxintishi;
    }

    public void setGengxintishi(String gengxintishi) {
        this.gengxintishi = gengxintishi;
    }

    public String getDowloadAndupdate() {
        return dowloadAndupdate;
    }

    public void setDowloadAndupdate(String dowloadAndupdate) {
        this.dowloadAndupdate = dowloadAndupdate;
    }

    public String getDowloadFailAndCheckd() {
        return dowloadFailAndCheckd;
    }

    public void setDowloadFailAndCheckd(String dowloadFailAndCheckd) {
        this.dowloadFailAndCheckd = dowloadFailAndCheckd;
    }

    public String getTianjiachenggong() {
        return tianjiachenggong;
    }

    public void setTianjiachenggong(String tianjiachenggong) {
        this.tianjiachenggong = tianjiachenggong;
    }

    public String getQuanzhijiepao() {
        return quanzhijiepao;
    }

    public void setQuanzhijiepao(String quanzhijiepao) {
        this.quanzhijiepao = quanzhijiepao;
    }

    public String getXinzengquanzhijiepao() {
        return xinzengquanzhijiepao;
    }

    public void setXinzengquanzhijiepao(String xinzengquanzhijiepao) {
        this.xinzengquanzhijiepao = xinzengquanzhijiepao;
    }

    public String getXinzengquanfenkanyuanjiance() {
        return xinzengquanfenkanyuanjiance;
    }

    public void setXinzengquanfenkanyuanjiance(String xinzengquanfenkanyuanjiance) {
        this.xinzengquanfenkanyuanjiance = xinzengquanfenkanyuanjiance;
    }

    public String getXinzengniuyangkanyuanjiance() {
        return xinzengniuyangkanyuanjiance;
    }

    public void setXinzengniuyangkanyuanjiance(String xinzengniuyangkanyuanjiance) {
        this.xinzengniuyangkanyuanjiance = xinzengniuyangkanyuanjiance;
    }

    public String getXiangquanfafang() {
        return xiangquanfafang;
    }

    public void setXiangquanfafang(String xiangquanfafang) {
        this.xiangquanfafang = xiangquanfafang;
    }

    public String getXinzengxuanchuanhuodong() {
        return xinzengxuanchuanhuodong;
    }

    public void setXinzengxuanchuanhuodong(String xinzengxuanchuanhuodong) {
        this.xinzengxuanchuanhuodong = xinzengxuanchuanhuodong;
    }

    public String getShezhi() {
        return shezhi;
    }

    public void setShezhi(String shezhi) {
        this.shezhi = shezhi;
    }

    public String getXinzengpeixunhuodong() {
        return xinzengpeixunhuodong;
    }

    public void setXinzengpeixunhuodong(String xinzengpeixunhuodong) {
        this.xinzengpeixunhuodong = xinzengpeixunhuodong;
    }

    public String getXinzengniuyangzangqichuli() {
        return xinzengniuyangzangqichuli;
    }

    public void setXinzengniuyangzangqichuli(String xinzengniuyangzangqichuli) {
        this.xinzengniuyangzangqichuli = xinzengniuyangzangqichuli;
    }

    public String getFangyitongji() {
        return fangyitongji;
    }

    public void setFangyitongji(String fangyitongji) {
        this.fangyitongji = fangyitongji;
    }

    public String getXinzeng() {
        return xinzeng;
    }

    public void setXinzeng(String xinzeng) {
        this.xinzeng = xinzeng;
    }

    public String getJiazaizhong() {
        return jiazaizhong;
    }

    public void setJiazaizhong(String jiazaizhong) {
        this.jiazaizhong = jiazaizhong;
    }

    public String getDenglvchaoshi() {
        return denglvchaoshi;
    }

    public void setDenglvchaoshi(String denglvchaoshi) {
        this.denglvchaoshi = denglvchaoshi;
    }

    public String getChongxindenglv() {
        return chongxindenglv;
    }

    public void setChongxindenglv(String chongxindenglv) {
        this.chongxindenglv = chongxindenglv;
    }

    public String getQingshuruyouxiaoshuzi() {
        return qingshuruyouxiaoshuzi;
    }

    public void setQingshuruyouxiaoshuzi(String qingshuruyouxiaoshuzi) {
        this.qingshuruyouxiaoshuzi = qingshuruyouxiaoshuzi;
    }

    public String getQingshuruzhegnqueshuzi() {
        return qingshuruzhegnqueshuzi;
    }

    public void setQingshuruzhegnqueshuzi(String qingshuruzhegnqueshuzi) {
        this.qingshuruzhegnqueshuzi = qingshuruzhegnqueshuzi;
    }

    public String getQingshurushujucanshu() {
        return qingshurushujucanshu;
    }

    public void setQingshurushujucanshu(String qingshurushujucanshu) {
        this.qingshurushujucanshu = qingshurushujucanshu;
    }

    public String getWodeweizhi() {
        return wodeweizhi;
    }

    public void setWodeweizhi(String wodeweizhi) {
        this.wodeweizhi = wodeweizhi;
    }

    public String getGenghuanmimachengg() {
        return genghuanmimachengg;
    }

    public void setGenghuanmimachengg(String genghuanmimachengg) {
        this.genghuanmimachengg = genghuanmimachengg;
    }

    public String getQingchongxdenglv() {
        return qingchongxdenglv;
    }

    public void setQingchongxdenglv(String qingchongxdenglv) {
        this.qingchongxdenglv = qingchongxdenglv;
    }

    public String getLiangcimimabuyizhi() {
        return liangcimimabuyizhi;
    }

    public void setLiangcimimabuyizhi(String liangcimimabuyizhi) {
        this.liangcimimabuyizhi = liangcimimabuyizhi;
    }

    public String getQingchongxinshuru() {
        return qingchongxinshuru;
    }

    public void setQingchongxinshuru(String qingchongxinshuru) {
        this.qingchongxinshuru = qingchongxinshuru;
    }

    public String getGanranshubixuxiaoyutiaochashu() {
        return ganranshubixuxiaoyutiaochashu;
    }

    public void setGanranshubixuxiaoyutiaochashu(String ganranshubixuxiaoyutiaochashu) {
        this.ganranshubixuxiaoyutiaochashu = ganranshubixuxiaoyutiaochashu;
    }

    public String getXiugaichenggong() {
        return xiugaichenggong;
    }

    public void setXiugaichenggong(String xiugaichenggong) {
        this.xiugaichenggong = xiugaichenggong;
    }

    public String getXiugaishibai() {
        return xiugaishibai;
    }

    public void setXiugaishibai(String xiugaishibai) {
        this.xiugaishibai = xiugaishibai;
    }

    public String getGaishebeibucunzai() {
        return gaishebeibucunzai;
    }

    public void setGaishebeibucunzai(String gaishebeibucunzai) {
        this.gaishebeibucunzai = gaishebeibucunzai;
    }

    public String getGaishebeiyibeibangdin() {
        return gaishebeiyibeibangdin;
    }

    public void setGaishebeiyibeibangdin(String gaishebeiyibeibangdin) {
        this.gaishebeiyibeibangdin = gaishebeiyibeibangdin;
    }

    public String getQingbangdinxiangquan() {
        return qingbangdinxiangquan;
    }

    public void setQingbangdinxiangquan(String qingbangdinxiangquan) {
        this.qingbangdinxiangquan = qingbangdinxiangquan;
    }

    public String getBingdinchenggong() {
        return bingdinchenggong;
    }

    public void setBingdinchenggong(String bingdinchenggong) {
        this.bingdinchenggong = bingdinchenggong;
    }

    public String getBingdinshibai() {
        return bingdinshibai;
    }

    public void setBingdinshibai(String bingdinshibai) {
        this.bingdinshibai = bingdinshibai;
    }

    public String getWuquanzhidinweixinxi() {
        return wuquanzhidinweixinxi;
    }

    public void setWuquanzhidinweixinxi(String wuquanzhidinweixinxi) {
        this.wuquanzhidinweixinxi = wuquanzhidinweixinxi;
    }

    public String getWudangtianshuju() {
        return wudangtianshuju;
    }

    public void setWudangtianshuju(String wudangtianshuju) {
        this.wudangtianshuju = wudangtianshuju;
    }

    public String getWuduiyinquanzhiquanzhu() {
        return wuduiyinquanzhiquanzhu;
    }

    public void setWuduiyinquanzhiquanzhu(String wuduiyinquanzhiquanzhu) {
        this.wuduiyinquanzhiquanzhu = wuduiyinquanzhiquanzhu;
    }

    public String getTianjiashibai() {
        return tianjiashibai;
    }

    public void setTianjiashibai(String tianjiashibai) {
        this.tianjiashibai = tianjiashibai;
    }

    public String getQingshaohouzaishi() {
        return qingshaohouzaishi;
    }

    public void setQingshaohouzaishi(String qingshaohouzaishi) {
        this.qingshaohouzaishi = qingshaohouzaishi;
    }

    public String getShibai() {
        return shibai;
    }

    public void setShibai(String shibai) {
        this.shibai = shibai;
    }

    public String getGenghuanchenggong() {
        return genghuanchenggong;
    }

    public void setGenghuanchenggong(String genghuanchenggong) {
        this.genghuanchenggong = genghuanchenggong;
    }

    public String getZhengjianhaomayicunzai() {
        return zhengjianhaomayicunzai;
    }

    public void setZhengjianhaomayicunzai(String zhengjianhaomayicunzai) {
        this.zhengjianhaomayicunzai = zhengjianhaomayicunzai;
    }

    public String getDianhuahaomayicunzai() {
        return dianhuahaomayicunzai;
    }

    public void setDianhuahaomayicunzai(String dianhuahaomayicunzai) {
        this.dianhuahaomayicunzai = dianhuahaomayicunzai;
    }

    public String getCaozuoshibai() {
        return caozuoshibai;
    }

    public void setCaozuoshibai(String caozuoshibai) {
        this.caozuoshibai = caozuoshibai;
    }

    public String getYonghudianhuahaomachongfu() {
        return yonghudianhuahaomachongfu;
    }

    public void setYonghudianhuahaomachongfu(String yonghudianhuahaomachongfu) {
        this.yonghudianhuahaomachongfu = yonghudianhuahaomachongfu;
    }

    public String getGaishebeiweibangdinquanzhi() {
        return gaishebeiweibangdinquanzhi;
    }

    public void setGaishebeiweibangdinquanzhi(String gaishebeiweibangdinquanzhi) {
        this.gaishebeiweibangdinquanzhi = gaishebeiweibangdinquanzhi;
    }
}
