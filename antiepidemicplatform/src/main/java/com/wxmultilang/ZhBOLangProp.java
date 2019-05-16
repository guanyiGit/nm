package com.wxmultilang;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author linchong
 * @create 2019-04-22 10:27
 */
@Component
@ConfigurationProperties(prefix = "zh_BO")
@PropertySource(value = "multi_lang.properties")
public class ZhBOLangProp {


    /**
     * language : 语言
     * quanfangyi : 犬防疫
     * daodile : 到底了
     * weizhi : 未知
     * mu : 亩
     * xialagengduo : 下拉显示更多
     * gongjin : 公斤
     * year : 年
     * month : 月
     * day : 日
     * sui : 岁
     * pl_username : 请输入用户名
     * pl_passwrod : 请输入密码
     * submit : 提交
     * save : 保存
     * reset : 重置
     * cancel : 取消
     * quanzhi_quanzhu : 犬只犬主
     * quanzhu_guanli : 犬主管理
     * quanzhi_guanli : 犬只管理
     * liulangquan_guanli : 流浪犬管理
     * tongjixinxi : 统计信息
     * quanzhi_zhoubian : 周边犬只
     * fangzhi_guanli : 防治管理
     * quanzhi_fangyi : 犬只防疫
     * quanfen_chuli : 犬粪处理
     * quanshi_chuli : 犬尸处理
     * quanfenkangyuan_jiance : 犬粪抗原检测
     * quanhzi_jiepao : 犬只解刨
     * niuyangkangti_jiance : 牛羊抗体检测
     * niuyangzangqi_chuli : 牛羊脏器处理
     * tongji_xinxi : 统计信息
     * wuzi_guanli : 物资管理
     * fanghuwuzi : 防护物资
     * quanzhu_xianquan : 犬只项圈
     * xuanchuan_peixun : 宣传培训
     * xuanchuan_huodong : 宣传活动
     * peixunhuodong : 培训活动
     * quanzhu_xinxi : 犬主信息
     * quanzhu_xingming : 犬主姓名
     * xingbie : 性别
     * nan : 男
     * nv : 女
     * chushengriqi : 出生日期
     * mingzu : 名族
     * wenhuachengdu : 文化程度
     * lianxidianhua : 联系电话
     * zhengjian_leix : 证件类型
     * zhengjian_haoma : 证件号码
     * caochangmianji : 草场面积
     * suoshuxiangzhen : 所属乡镇
     * suoshucunweihui : 所属村委会
     * menpaihao : 门牌号
     * fangyiyuan : 防疫员
     * lvrushijian : 录入时间
     * zhaopian : 照片
     * miaoshu : 描述
     * quanzhi_xinxi : 犬只信息
     * weibangdxiangquan : 未绑定项圈
     * yangxinglv : 阳性率
     * jianceshijian : 检测时间
     * jiancedanwei : 检测单位
     * jianceshu : 检测数
     * yangxingshu : 阳性数
     * wuhaihua : 无害化
     * tupuai : 图片
     * shipin : 视频
     * fangyishijan : 防疫时间
     * quanming : 犬名
     * quanzhu : 犬主
     * suyuan_ID : 溯源ID
     * xiangquanbianhao : 项圈编号
     * quanzhudianhua : 犬主电话
     * quanzhushengfengzheng : 犬主身份证
     * shusuoxiangzheng : 所属乡镇
     * xiangxidizhi : 详细地址
     * fangyileixin : 防疫类型
     * fangyiyaopin : 防疫药品
     * fangyizhouqi : 防疫周期
     * fangyiyuandianhua : 防疫员电话
     * hegeshu : 合格数
     * hegelv : 合格率
     * chulishijian : 处理时间
     * siwangshijian : 死亡原因
     * chulifnagfa : 处理方法
     * fangfashuoming : 方法说明
     * chulirenyuan : 处理人员
     * jieshoudanwei : 接收单位
     * jieshouren : 接收人
     * fafangdanwei : 发放单位
     * fafang : 发放
     * fafangshuliang : 发放数量
     * fafangshijian : 发放时间
     * rulanshijian : 入栏时间
     * tizhong : 体重
     * maose : 毛色
     * quanling : 犬龄
     * quanzhong : 犬种
     * quanzhicunlanshuliang : 犬只存栏数量
     * suoshuzhou : 所属州
     * suoshuxian : 所属县
     * suoshuxiang : 所属乡
     * suoshufangyiyuan : 所属防疫员
     * shuliang : 数量
     * quanzhushuliang : 犬主数量
     * liulangquanchulishuliang : 流浪犬处理数量
     * fangyicishu : 防疫次数
     * shitichulicishu : 犬尸处理次数
     * quanfenchulicishu : 犬粪处理次数
     * quanfenkangyuancishu : 犬粪抗原检测次数
     * quanzhijiepaocishu : 犬只解剖次数
     * niuyangjiancecishu : 牛羊抗体检测次数
     * niuyangzangqichulicishu : 牛羊脏器处理次数
     * cishu : 次数
     * shebeibianhao : 设备编号
     * shenfengzhenghaoma : 身份证号码
     * wuzileixing : 物资类型
     * wuzimingcheng : 物资名称
     * fasongshijian : 发送时间
     * qumingshengfengzheng : 居民身份证
     * juminghukoubo : 居民户口簿
     * fenbianchulixinxi : 粪便处理信息
     * suoshumoweihui : 所属牧委会
     * dianziweilan : 电子围栏
     * huodongshijian : 活动时间
     * jibandanwei : 举办单位
     * neirong : 内容
     * fafangneirongshuliang : 发放宣传品数量
     * zhuti : 主题
     * didian : 地点
     * shijian : 时间
     * tuichudenglu : 退出登录
     * miamashezhi : 密码设置
     * gerenxinxi : 个人信息
     * querenmima : 确认密码
     * xinmima : 新密码
     * jiumima : 旧密码
     * quanzhishu : 犬只数
     * yifangyi : 已防疫
     * weifangyi : 未防疫
     * chulifangshi : 处理方式
     * gong_sex : 公
     * mu_sex : 母
     * chuliren : 处理人
     * pingzhong : 品种
     * liulangquanbianhao : 流浪犬编号
     * caiyurenshu : 参与人数
     * peixunduixiang : 培训对象
     * yonghuming : 用户名
     * xingming : 姓名
     * suoshuquyu : 所属区域
     * suoshuzuzhi : 所属组织
     * binbianzangqichuli : 病变脏器处理
     * binbianzangqichulishu : 病变脏器处理数
     * ganranshu : 感染数
     * diaochashu : 调查数
     * xiangquanchuli : 项圈处理
     * dianhua : 电话
     * dianhuahaoma : 电话号码
     * zhuxiaoshijian : 注销时间
     * zhuxiaoyuanyin : 注销原因
     * fangyiyuanxingming : 防疫员姓名
     * querenbangdin : 确认绑定
     * weidin : 未定
     * scan_bind_device : 请扫描二维码绑定项圈
     * zanbubangdin : 暂不绑定
     * dinweichaxun : 定位查询
     * guijichaxun : 轨迹查询
     * riqi : 日期
     * weiduxiaoxi : 未读消息
     * yiduxiaoxi : 已读消息
     * xiangqing : 详情
     * biaoti : 标题
     * genghuanwei : 更换为
     * genghuanshijian : 更换时间
     * genghuanyuanyin : 更换原因
     * xinshebeibianhao : 新设备编号
     * jiushebeibianhao : 旧设备编号
     * yuanxiangquanchuli : 原项圈处理
     * xinxiangquan : 新项圈
     * xinquanzhu : 新犬主
     * jiuquanzhu : 旧犬主
     * yuyanshezhi : 语言设置
     * kaiqi : 开启
     * guanbi : 关闭
     * xiangquangenghuancishu : 项圈更换次数
     * fengbianchulicishu : 粪便处理次数
     * quanshichulishuliang : 犬尸处理数量
     * xinzengquanzhushuliang : 新增犬主数量
     * zhuxiaoquanzhishuliang : 注销犬只数量
     * xinzengquanzhishuliang : 新增犬只数量
     * chaxun : 查询
     * zhi : 至
     * fangchanzhe : 房产证
     * wodexiaoxi : 我的消息
     * dinweiguiji : 定位轨迹
     * quanzhizhuxiao : 犬只注销
     * xiangquangenghuan : 项圈更换
     * liulangquanchuli : 流浪犬处理
     * fenbianchuli : 粪便处理
     * quedin : 确定
     * jubanshijian : 举办时间
     * gengduo : 更多
     * xuanzhequyu : 选择区域
     * nianling : 年龄
     * shoujihao : 手机号
     * fangyidengji : 防疫登记
     * xinzengquanzhu : 新增犬主
     * xiangquanbangdin : 项圈绑定
     * xinzengquanzxhi : 新增犬只
     * chakanxiangqing : 查看详情
     * quanzhuxiangqing : 犬主详情
     * quanzhuxinxilvru : 犬主信息录入
     * quanzhixinxilvru : 犬只信息录入
     * bangdinxiangquan : 绑定项圈
     * fangyixinxilvru : 防疫信息录入
     * shitiwuhaihuachuli : 尸体无害化处理
     * kaishishijian : 开始时间
     * jieshushijian : 结束时间
     * liulangquanchulilvru : 流浪犬处理录入
     * denglv : 登录
     * weinengshibieerweima : 未能识别二维码
     * tishi : 提示
     * gaisuyuanidbucunzai : 该溯源Id不存在
     * gaishebeiweibangdinquanzhi : 该设备未绑定任何犬只
     * tianjiachenggong : 添加成功
     * quanzhijiepao : 犬只解剖
     * xinzengquanzhijiepao : 新增犬只解剖
     * xinzengquanfenkanyuanjiance : 新增犬粪抗原检测
     * xinzengniuyangkanyuanjiance : 新增牛羊抗体检测
     * xiangquanfafang : 项圈发放
     * xinzengxuanchuanhuodong : 新增宣传活动
     * shezhi : 设置
     * xinzengpeixunhuodong : 新增培训活动
     * xinzengniuyangzangqichuli : 新增牛羊脏器处理
     * fangyitongji : 防疫统计
     * xinzeng : 新增
     * jiazaizhong : 加载中
     * denglvchaoshi : 登录超时
     * chongxindenglv : 请重新登录
     * qingshuruyouxiaoshuzi : 请输入有效的数字
     * qingshuruzhegnqueshuzi : 请输入正确的数字
     * qingshurushujucanshu : 请正确输入数据参数
     * wodeweizhi : 我的位置
     * genghuanmimachengg : 更改密码成功
     * qingchongxdenglv : 请重新登录
     * liangcimimabuyizhi : 两次输入密码不一致
     * qingchongxinshuru : 请重新输入
     * ganranshubixuxiaoyutiaochashu : 感染数必须小于调查数
     * xiugaichenggong : 修改成功
     * xiugaishibai : 修改失败
     * gaishebeibucunzai : 该设备不存在
     * gaishebeiyibeibangdin : 该设备已被绑定
     * qingbangdinxiangquan : 请绑定项圈
     * bingdinchenggong : 绑定成功
     * bingdinshibai : 绑定失败
     * wuquanzhidinweixinxi : 无犬只定位信息
     * wudangtianshuju : 当天无数据
     * wuduiyinquanzhiquanzhu : 无对应犬只或者犬主
     * tianjiashibai : 添加失败
     * qingshaohouzaishi : 请稍后再试
     * shibai : 失败
     * genghuanchenggong : 更换成功
     * zhengjianhaomayicunzai : 证件号码已存在
     * dianhuahaomayicunzai : 电话号码已存在
     * caozuoshibai : 操作失败
     * yonghudianhuahaomachongfu : 用户电话或证书号码重复
     */

    private String language;
    private String quanfangyi;
    private String daodile;
    private String weizhi;
    private String mu;
    private String xialagengduo;
    private String gongjin;
    private String year;
    private String month;
    private String day;
    private String sui;
    private String pl_username;
    private String pl_passwrod;
    private String submit;
    private String save;
    private String reset;
    private String cancel;
    private String quanzhi_quanzhu;
    private String quanzhu_guanli;
    private String quanzhi_guanli;
    private String liulangquan_guanli;
    private String tongjixinxi;
    private String quanzhi_zhoubian;
    private String fangzhi_guanli;
    private String quanzhi_fangyi;
    private String quanfen_chuli;
    private String quanshi_chuli;
    private String quanfenkangyuan_jiance;
    private String quanhzi_jiepao;
    private String niuyangkangti_jiance;
    private String niuyangzangqi_chuli;
    private String tongji_xinxi;
    private String wuzi_guanli;
    private String fanghuwuzi;
    private String quanzhu_xianquan;
    private String xuanchuan_peixun;
    private String xuanchuan_huodong;
    private String peixunhuodong;
    private String quanzhu_xinxi;
    private String quanzhu_xingming;
    private String xingbie;
    private String nan;
    private String nv;
    private String chushengriqi;
    private String mingzu;
    private String wenhuachengdu;
    private String lianxidianhua;
    private String zhengjian_leix;
    private String zhengjian_haoma;
    private String caochangmianji;
    private String suoshuxiangzhen;
    private String suoshucunweihui;
    private String menpaihao;
    private String fangyiyuan;
    private String lvrushijian;
    private String zhaopian;
    private String miaoshu;
    private String quanzhi_xinxi;
    private String weibangdxiangquan;
    private String yangxinglv;
    private String jianceshijian;
    private String jiancedanwei;
    private String jianceshu;
    private String yangxingshu;
    private String wuhaihua;
    private String tupuai;
    private String shipin;
    private String fangyishijan;
    private String quanming;
    private String quanzhu;
    private String suyuan_ID;
    private String xiangquanbianhao;
    private String quanzhudianhua;
    private String quanzhushengfengzheng;
    private String shusuoxiangzheng;
    private String xiangxidizhi;
    private String fangyileixin;
    private String fangyiyaopin;
    private String fangyizhouqi;
    private String fangyiyuandianhua;
    private String hegeshu;
    private String hegelv;
    private String chulishijian;
    private String siwangshijian;
    private String chulifnagfa;
    private String fangfashuoming;
    private String chulirenyuan;
    private String jieshoudanwei;
    private String jieshouren;
    private String fafangdanwei;
    private String fafang;
    private String fafangshuliang;
    private String fafangshijian;
    private String rulanshijian;
    private String tizhong;
    private String maose;
    private String quanling;
    private String quanzhong;
    private String quanzhicunlanshuliang;
    private String suoshuzhou;
    private String suoshuxian;
    private String suoshuxiang;
    private String suoshufangyiyuan;
    private String shuliang;
    private String quanzhushuliang;
    private String liulangquanchulishuliang;
    private String fangyicishu;
    private String shitichulicishu;
    private String quanfenchulicishu;
    private String quanfenkangyuancishu;
    private String quanzhijiepaocishu;
    private String niuyangjiancecishu;
    private String niuyangzangqichulicishu;
    private String cishu;
    private String shebeibianhao;
    private String shenfengzhenghaoma;
    private String wuzileixing;
    private String wuzimingcheng;
    private String fasongshijian;
    private String qumingshengfengzheng;
    private String juminghukoubo;
    private String fenbianchulixinxi;
    private String suoshumoweihui;
    private String dianziweilan;
    private String huodongshijian;
    private String jibandanwei;
    private String neirong;
    private String fafangneirongshuliang;
    private String zhuti;
    private String didian;
    private String shijian;
    private String tuichudenglu;
    private String miamashezhi;
    private String gerenxinxi;
    private String querenmima;
    private String xinmima;
    private String jiumima;
    private String quanzhishu;
    private String yifangyi;
    private String weifangyi;
    private String chulifangshi;
    private String gong_sex;
    private String mu_sex;
    private String chuliren;
    private String pingzhong;
    private String liulangquanbianhao;
    private String caiyurenshu;
    private String peixunduixiang;
    private String yonghuming;
    private String xingming;
    private String suoshuquyu;
    private String suoshuzuzhi;
    private String binbianzangqichuli;
    private String binbianzangqichulishu;
    private String ganranshu;
    private String diaochashu;
    private String xiangquanchuli;
    private String dianhua;
    private String dianhuahaoma;
    private String zhuxiaoshijian;
    private String zhuxiaoyuanyin;
    private String fangyiyuanxingming;
    private String querenbangdin;
    private String weidin;
    private String scan_bind_device;
    private String zanbubangdin;
    private String dinweichaxun;
    private String guijichaxun;
    private String riqi;
    private String weiduxiaoxi;
    private String yiduxiaoxi;
    private String xiangqing;
    private String biaoti;
    private String genghuanwei;
    private String genghuanshijian;
    private String genghuanyuanyin;
    private String xinshebeibianhao;
    private String jiushebeibianhao;
    private String yuanxiangquanchuli;
    private String xinxiangquan;
    private String xinquanzhu;
    private String jiuquanzhu;
    private String yuyanshezhi;
    private String kaiqi;
    private String guanbi;
    private String xiangquangenghuancishu;
    private String fengbianchulicishu;
    private String quanshichulishuliang;
    private String xinzengquanzhushuliang;
    private String zhuxiaoquanzhishuliang;
    private String xinzengquanzhishuliang;
    private String chaxun;
    private String zhi;
    private String fangchanzhe;
    private String wodexiaoxi;
    private String dinweiguiji;
    private String quanzhizhuxiao;
    private String xiangquangenghuan;
    private String liulangquanchuli;
    private String fenbianchuli;
    private String quedin;
    private String jubanshijian;
    private String gengduo;
    private String xuanzhequyu;
    private String nianling;
    private String shoujihao;
    private String fangyidengji;
    private String xinzengquanzhu;
    private String xiangquanbangdin;
    private String xinzengquanzxhi;
    private String chakanxiangqing;
    private String quanzhuxiangqing;
    private String quanzhuxinxilvru;
    private String quanzhixinxilvru;
    private String bangdinxiangquan;
    private String fangyixinxilvru;
    private String shitiwuhaihuachuli;
    private String kaishishijian;
    private String jieshushijian;
    private String liulangquanchulilvru;
    private String denglv;
    private String weinengshibieerweima;
    private String tishi;
    private String gaisuyuanidbucunzai;
    private String gaishebeiweibangdinquanzhi;
    private String tianjiachenggong;
    private String quanzhijiepao;
    private String xinzengquanzhijiepao;
    private String xinzengquanfenkanyuanjiance;
    private String xinzengniuyangkanyuanjiance;
    private String xiangquanfafang;
    private String xinzengxuanchuanhuodong;
    private String shezhi;
    private String xinzengpeixunhuodong;
    private String xinzengniuyangzangqichuli;
    private String fangyitongji;
    private String xinzeng;
    private String jiazaizhong;
    private String denglvchaoshi;
    private String chongxindenglv;
    private String qingshuruyouxiaoshuzi;
    private String qingshuruzhegnqueshuzi;
    private String qingshurushujucanshu;
    private String wodeweizhi;
    private String genghuanmimachengg;
    private String qingchongxdenglv;
    private String liangcimimabuyizhi;
    private String qingchongxinshuru;
    private String ganranshubixuxiaoyutiaochashu;
    private String xiugaichenggong;
    private String xiugaishibai;
    private String gaishebeibucunzai;
    private String gaishebeiyibeibangdin;
    private String qingbangdinxiangquan;
    private String bingdinchenggong;
    private String bingdinshibai;
    private String wuquanzhidinweixinxi;
    private String wudangtianshuju;
    private String wuduiyinquanzhiquanzhu;
    private String tianjiashibai;
    private String qingshaohouzaishi;
    private String shibai;
    private String genghuanchenggong;
    private String zhengjianhaomayicunzai;
    private String dianhuahaomayicunzai;
    private String caozuoshibai;
    private String yonghudianhuahaomachongfu;

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

    public String getQuanzhi_quanzhu() {
        return quanzhi_quanzhu;
    }

    public void setQuanzhi_quanzhu(String quanzhi_quanzhu) {
        this.quanzhi_quanzhu = quanzhi_quanzhu;
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

    public String getTupuai() {
        return tupuai;
    }

    public void setTupuai(String tupuai) {
        this.tupuai = tupuai;
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

    public String getChulifnagfa() {
        return chulifnagfa;
    }

    public void setChulifnagfa(String chulifnagfa) {
        this.chulifnagfa = chulifnagfa;
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

    public String getFafangneirongshuliang() {
        return fafangneirongshuliang;
    }

    public void setFafangneirongshuliang(String fafangneirongshuliang) {
        this.fafangneirongshuliang = fafangneirongshuliang;
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

    public String getGaishebeiweibangdinquanzhi() {
        return gaishebeiweibangdinquanzhi;
    }

    public void setGaishebeiweibangdinquanzhi(String gaishebeiweibangdinquanzhi) {
        this.gaishebeiweibangdinquanzhi = gaishebeiweibangdinquanzhi;
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

    @Override
    public String toString() {
        return "ZhBOLangProp{" +
                "language='" + language + '\'' +
                ", quanfangyi='" + quanfangyi + '\'' +
                ", daodile='" + daodile + '\'' +
                ", weizhi='" + weizhi + '\'' +
                ", mu='" + mu + '\'' +
                ", xialagengduo='" + xialagengduo + '\'' +
                ", gongjin='" + gongjin + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", sui='" + sui + '\'' +
                ", pl_username='" + pl_username + '\'' +
                ", pl_passwrod='" + pl_passwrod + '\'' +
                ", submit='" + submit + '\'' +
                ", save='" + save + '\'' +
                ", reset='" + reset + '\'' +
                ", cancel='" + cancel + '\'' +
                ", quanzhi_quanzhu='" + quanzhi_quanzhu + '\'' +
                ", quanzhu_guanli='" + quanzhu_guanli + '\'' +
                ", quanzhi_guanli='" + quanzhi_guanli + '\'' +
                ", liulangquan_guanli='" + liulangquan_guanli + '\'' +
                ", tongjixinxi='" + tongjixinxi + '\'' +
                ", quanzhi_zhoubian='" + quanzhi_zhoubian + '\'' +
                ", fangzhi_guanli='" + fangzhi_guanli + '\'' +
                ", quanzhi_fangyi='" + quanzhi_fangyi + '\'' +
                ", quanfen_chuli='" + quanfen_chuli + '\'' +
                ", quanshi_chuli='" + quanshi_chuli + '\'' +
                ", quanfenkangyuan_jiance='" + quanfenkangyuan_jiance + '\'' +
                ", quanhzi_jiepao='" + quanhzi_jiepao + '\'' +
                ", niuyangkangti_jiance='" + niuyangkangti_jiance + '\'' +
                ", niuyangzangqi_chuli='" + niuyangzangqi_chuli + '\'' +
                ", tongji_xinxi='" + tongji_xinxi + '\'' +
                ", wuzi_guanli='" + wuzi_guanli + '\'' +
                ", fanghuwuzi='" + fanghuwuzi + '\'' +
                ", quanzhu_xianquan='" + quanzhu_xianquan + '\'' +
                ", xuanchuan_peixun='" + xuanchuan_peixun + '\'' +
                ", xuanchuan_huodong='" + xuanchuan_huodong + '\'' +
                ", peixunhuodong='" + peixunhuodong + '\'' +
                ", quanzhu_xinxi='" + quanzhu_xinxi + '\'' +
                ", quanzhu_xingming='" + quanzhu_xingming + '\'' +
                ", xingbie='" + xingbie + '\'' +
                ", nan='" + nan + '\'' +
                ", nv='" + nv + '\'' +
                ", chushengriqi='" + chushengriqi + '\'' +
                ", mingzu='" + mingzu + '\'' +
                ", wenhuachengdu='" + wenhuachengdu + '\'' +
                ", lianxidianhua='" + lianxidianhua + '\'' +
                ", zhengjian_leix='" + zhengjian_leix + '\'' +
                ", zhengjian_haoma='" + zhengjian_haoma + '\'' +
                ", caochangmianji='" + caochangmianji + '\'' +
                ", suoshuxiangzhen='" + suoshuxiangzhen + '\'' +
                ", suoshucunweihui='" + suoshucunweihui + '\'' +
                ", menpaihao='" + menpaihao + '\'' +
                ", fangyiyuan='" + fangyiyuan + '\'' +
                ", lvrushijian='" + lvrushijian + '\'' +
                ", zhaopian='" + zhaopian + '\'' +
                ", miaoshu='" + miaoshu + '\'' +
                ", quanzhi_xinxi='" + quanzhi_xinxi + '\'' +
                ", weibangdxiangquan='" + weibangdxiangquan + '\'' +
                ", yangxinglv='" + yangxinglv + '\'' +
                ", jianceshijian='" + jianceshijian + '\'' +
                ", jiancedanwei='" + jiancedanwei + '\'' +
                ", jianceshu='" + jianceshu + '\'' +
                ", yangxingshu='" + yangxingshu + '\'' +
                ", wuhaihua='" + wuhaihua + '\'' +
                ", tupuai='" + tupuai + '\'' +
                ", shipin='" + shipin + '\'' +
                ", fangyishijan='" + fangyishijan + '\'' +
                ", quanming='" + quanming + '\'' +
                ", quanzhu='" + quanzhu + '\'' +
                ", suyuan_ID='" + suyuan_ID + '\'' +
                ", xiangquanbianhao='" + xiangquanbianhao + '\'' +
                ", quanzhudianhua='" + quanzhudianhua + '\'' +
                ", quanzhushengfengzheng='" + quanzhushengfengzheng + '\'' +
                ", shusuoxiangzheng='" + shusuoxiangzheng + '\'' +
                ", xiangxidizhi='" + xiangxidizhi + '\'' +
                ", fangyileixin='" + fangyileixin + '\'' +
                ", fangyiyaopin='" + fangyiyaopin + '\'' +
                ", fangyizhouqi='" + fangyizhouqi + '\'' +
                ", fangyiyuandianhua='" + fangyiyuandianhua + '\'' +
                ", hegeshu='" + hegeshu + '\'' +
                ", hegelv='" + hegelv + '\'' +
                ", chulishijian='" + chulishijian + '\'' +
                ", siwangshijian='" + siwangshijian + '\'' +
                ", chulifnagfa='" + chulifnagfa + '\'' +
                ", fangfashuoming='" + fangfashuoming + '\'' +
                ", chulirenyuan='" + chulirenyuan + '\'' +
                ", jieshoudanwei='" + jieshoudanwei + '\'' +
                ", jieshouren='" + jieshouren + '\'' +
                ", fafangdanwei='" + fafangdanwei + '\'' +
                ", fafang='" + fafang + '\'' +
                ", fafangshuliang='" + fafangshuliang + '\'' +
                ", fafangshijian='" + fafangshijian + '\'' +
                ", rulanshijian='" + rulanshijian + '\'' +
                ", tizhong='" + tizhong + '\'' +
                ", maose='" + maose + '\'' +
                ", quanling='" + quanling + '\'' +
                ", quanzhong='" + quanzhong + '\'' +
                ", quanzhicunlanshuliang='" + quanzhicunlanshuliang + '\'' +
                ", suoshuzhou='" + suoshuzhou + '\'' +
                ", suoshuxian='" + suoshuxian + '\'' +
                ", suoshuxiang='" + suoshuxiang + '\'' +
                ", suoshufangyiyuan='" + suoshufangyiyuan + '\'' +
                ", shuliang='" + shuliang + '\'' +
                ", quanzhushuliang='" + quanzhushuliang + '\'' +
                ", liulangquanchulishuliang='" + liulangquanchulishuliang + '\'' +
                ", fangyicishu='" + fangyicishu + '\'' +
                ", shitichulicishu='" + shitichulicishu + '\'' +
                ", quanfenchulicishu='" + quanfenchulicishu + '\'' +
                ", quanfenkangyuancishu='" + quanfenkangyuancishu + '\'' +
                ", quanzhijiepaocishu='" + quanzhijiepaocishu + '\'' +
                ", niuyangjiancecishu='" + niuyangjiancecishu + '\'' +
                ", niuyangzangqichulicishu='" + niuyangzangqichulicishu + '\'' +
                ", cishu='" + cishu + '\'' +
                ", shebeibianhao='" + shebeibianhao + '\'' +
                ", shenfengzhenghaoma='" + shenfengzhenghaoma + '\'' +
                ", wuzileixing='" + wuzileixing + '\'' +
                ", wuzimingcheng='" + wuzimingcheng + '\'' +
                ", fasongshijian='" + fasongshijian + '\'' +
                ", qumingshengfengzheng='" + qumingshengfengzheng + '\'' +
                ", juminghukoubo='" + juminghukoubo + '\'' +
                ", fenbianchulixinxi='" + fenbianchulixinxi + '\'' +
                ", suoshumoweihui='" + suoshumoweihui + '\'' +
                ", dianziweilan='" + dianziweilan + '\'' +
                ", huodongshijian='" + huodongshijian + '\'' +
                ", jibandanwei='" + jibandanwei + '\'' +
                ", neirong='" + neirong + '\'' +
                ", fafangneirongshuliang='" + fafangneirongshuliang + '\'' +
                ", zhuti='" + zhuti + '\'' +
                ", didian='" + didian + '\'' +
                ", shijian='" + shijian + '\'' +
                ", tuichudenglu='" + tuichudenglu + '\'' +
                ", miamashezhi='" + miamashezhi + '\'' +
                ", gerenxinxi='" + gerenxinxi + '\'' +
                ", querenmima='" + querenmima + '\'' +
                ", xinmima='" + xinmima + '\'' +
                ", jiumima='" + jiumima + '\'' +
                ", quanzhishu='" + quanzhishu + '\'' +
                ", yifangyi='" + yifangyi + '\'' +
                ", weifangyi='" + weifangyi + '\'' +
                ", chulifangshi='" + chulifangshi + '\'' +
                ", gong_sex='" + gong_sex + '\'' +
                ", mu_sex='" + mu_sex + '\'' +
                ", chuliren='" + chuliren + '\'' +
                ", pingzhong='" + pingzhong + '\'' +
                ", liulangquanbianhao='" + liulangquanbianhao + '\'' +
                ", caiyurenshu='" + caiyurenshu + '\'' +
                ", peixunduixiang='" + peixunduixiang + '\'' +
                ", yonghuming='" + yonghuming + '\'' +
                ", xingming='" + xingming + '\'' +
                ", suoshuquyu='" + suoshuquyu + '\'' +
                ", suoshuzuzhi='" + suoshuzuzhi + '\'' +
                ", binbianzangqichuli='" + binbianzangqichuli + '\'' +
                ", binbianzangqichulishu='" + binbianzangqichulishu + '\'' +
                ", ganranshu='" + ganranshu + '\'' +
                ", diaochashu='" + diaochashu + '\'' +
                ", xiangquanchuli='" + xiangquanchuli + '\'' +
                ", dianhua='" + dianhua + '\'' +
                ", dianhuahaoma='" + dianhuahaoma + '\'' +
                ", zhuxiaoshijian='" + zhuxiaoshijian + '\'' +
                ", zhuxiaoyuanyin='" + zhuxiaoyuanyin + '\'' +
                ", fangyiyuanxingming='" + fangyiyuanxingming + '\'' +
                ", querenbangdin='" + querenbangdin + '\'' +
                ", weidin='" + weidin + '\'' +
                ", scan_bind_device='" + scan_bind_device + '\'' +
                ", zanbubangdin='" + zanbubangdin + '\'' +
                ", dinweichaxun='" + dinweichaxun + '\'' +
                ", guijichaxun='" + guijichaxun + '\'' +
                ", riqi='" + riqi + '\'' +
                ", weiduxiaoxi='" + weiduxiaoxi + '\'' +
                ", yiduxiaoxi='" + yiduxiaoxi + '\'' +
                ", xiangqing='" + xiangqing + '\'' +
                ", biaoti='" + biaoti + '\'' +
                ", genghuanwei='" + genghuanwei + '\'' +
                ", genghuanshijian='" + genghuanshijian + '\'' +
                ", genghuanyuanyin='" + genghuanyuanyin + '\'' +
                ", xinshebeibianhao='" + xinshebeibianhao + '\'' +
                ", jiushebeibianhao='" + jiushebeibianhao + '\'' +
                ", yuanxiangquanchuli='" + yuanxiangquanchuli + '\'' +
                ", xinxiangquan='" + xinxiangquan + '\'' +
                ", xinquanzhu='" + xinquanzhu + '\'' +
                ", jiuquanzhu='" + jiuquanzhu + '\'' +
                ", yuyanshezhi='" + yuyanshezhi + '\'' +
                ", kaiqi='" + kaiqi + '\'' +
                ", guanbi='" + guanbi + '\'' +
                ", xiangquangenghuancishu='" + xiangquangenghuancishu + '\'' +
                ", fengbianchulicishu='" + fengbianchulicishu + '\'' +
                ", quanshichulishuliang='" + quanshichulishuliang + '\'' +
                ", xinzengquanzhushuliang='" + xinzengquanzhushuliang + '\'' +
                ", zhuxiaoquanzhishuliang='" + zhuxiaoquanzhishuliang + '\'' +
                ", xinzengquanzhishuliang='" + xinzengquanzhishuliang + '\'' +
                ", chaxun='" + chaxun + '\'' +
                ", zhi='" + zhi + '\'' +
                ", fangchanzhe='" + fangchanzhe + '\'' +
                ", wodexiaoxi='" + wodexiaoxi + '\'' +
                ", dinweiguiji='" + dinweiguiji + '\'' +
                ", quanzhizhuxiao='" + quanzhizhuxiao + '\'' +
                ", xiangquangenghuan='" + xiangquangenghuan + '\'' +
                ", liulangquanchuli='" + liulangquanchuli + '\'' +
                ", fenbianchuli='" + fenbianchuli + '\'' +
                ", quedin='" + quedin + '\'' +
                ", jubanshijian='" + jubanshijian + '\'' +
                ", gengduo='" + gengduo + '\'' +
                ", xuanzhequyu='" + xuanzhequyu + '\'' +
                ", nianling='" + nianling + '\'' +
                ", shoujihao='" + shoujihao + '\'' +
                ", fangyidengji='" + fangyidengji + '\'' +
                ", xinzengquanzhu='" + xinzengquanzhu + '\'' +
                ", xiangquanbangdin='" + xiangquanbangdin + '\'' +
                ", xinzengquanzxhi='" + xinzengquanzxhi + '\'' +
                ", chakanxiangqing='" + chakanxiangqing + '\'' +
                ", quanzhuxiangqing='" + quanzhuxiangqing + '\'' +
                ", quanzhuxinxilvru='" + quanzhuxinxilvru + '\'' +
                ", quanzhixinxilvru='" + quanzhixinxilvru + '\'' +
                ", bangdinxiangquan='" + bangdinxiangquan + '\'' +
                ", fangyixinxilvru='" + fangyixinxilvru + '\'' +
                ", shitiwuhaihuachuli='" + shitiwuhaihuachuli + '\'' +
                ", kaishishijian='" + kaishishijian + '\'' +
                ", jieshushijian='" + jieshushijian + '\'' +
                ", liulangquanchulilvru='" + liulangquanchulilvru + '\'' +
                ", denglv='" + denglv + '\'' +
                ", weinengshibieerweima='" + weinengshibieerweima + '\'' +
                ", tishi='" + tishi + '\'' +
                ", gaisuyuanidbucunzai='" + gaisuyuanidbucunzai + '\'' +
                ", gaishebeiweibangdinquanzhi='" + gaishebeiweibangdinquanzhi + '\'' +
                ", tianjiachenggong='" + tianjiachenggong + '\'' +
                ", quanzhijiepao='" + quanzhijiepao + '\'' +
                ", xinzengquanzhijiepao='" + xinzengquanzhijiepao + '\'' +
                ", xinzengquanfenkanyuanjiance='" + xinzengquanfenkanyuanjiance + '\'' +
                ", xinzengniuyangkanyuanjiance='" + xinzengniuyangkanyuanjiance + '\'' +
                ", xiangquanfafang='" + xiangquanfafang + '\'' +
                ", xinzengxuanchuanhuodong='" + xinzengxuanchuanhuodong + '\'' +
                ", shezhi='" + shezhi + '\'' +
                ", xinzengpeixunhuodong='" + xinzengpeixunhuodong + '\'' +
                ", xinzengniuyangzangqichuli='" + xinzengniuyangzangqichuli + '\'' +
                ", fangyitongji='" + fangyitongji + '\'' +
                ", xinzeng='" + xinzeng + '\'' +
                ", jiazaizhong='" + jiazaizhong + '\'' +
                ", denglvchaoshi='" + denglvchaoshi + '\'' +
                ", chongxindenglv='" + chongxindenglv + '\'' +
                ", qingshuruyouxiaoshuzi='" + qingshuruyouxiaoshuzi + '\'' +
                ", qingshuruzhegnqueshuzi='" + qingshuruzhegnqueshuzi + '\'' +
                ", qingshurushujucanshu='" + qingshurushujucanshu + '\'' +
                ", wodeweizhi='" + wodeweizhi + '\'' +
                ", genghuanmimachengg='" + genghuanmimachengg + '\'' +
                ", qingchongxdenglv='" + qingchongxdenglv + '\'' +
                ", liangcimimabuyizhi='" + liangcimimabuyizhi + '\'' +
                ", qingchongxinshuru='" + qingchongxinshuru + '\'' +
                ", ganranshubixuxiaoyutiaochashu='" + ganranshubixuxiaoyutiaochashu + '\'' +
                ", xiugaichenggong='" + xiugaichenggong + '\'' +
                ", xiugaishibai='" + xiugaishibai + '\'' +
                ", gaishebeibucunzai='" + gaishebeibucunzai + '\'' +
                ", gaishebeiyibeibangdin='" + gaishebeiyibeibangdin + '\'' +
                ", qingbangdinxiangquan='" + qingbangdinxiangquan + '\'' +
                ", bingdinchenggong='" + bingdinchenggong + '\'' +
                ", bingdinshibai='" + bingdinshibai + '\'' +
                ", wuquanzhidinweixinxi='" + wuquanzhidinweixinxi + '\'' +
                ", wudangtianshuju='" + wudangtianshuju + '\'' +
                ", wuduiyinquanzhiquanzhu='" + wuduiyinquanzhiquanzhu + '\'' +
                ", tianjiashibai='" + tianjiashibai + '\'' +
                ", qingshaohouzaishi='" + qingshaohouzaishi + '\'' +
                ", shibai='" + shibai + '\'' +
                ", genghuanchenggong='" + genghuanchenggong + '\'' +
                ", zhengjianhaomayicunzai='" + zhengjianhaomayicunzai + '\'' +
                ", dianhuahaomayicunzai='" + dianhuahaomayicunzai + '\'' +
                ", caozuoshibai='" + caozuoshibai + '\'' +
                ", yonghudianhuahaomachongfu='" + yonghudianhuahaomachongfu + '\'' +
                '}';
    }
}
