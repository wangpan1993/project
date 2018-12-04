package com.wp.project.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangPan on 2017/6/13 13:48
 */

public class JokeBean implements Serializable {
    /**
     * stat : 1
     * data : [{"uniquekey":"81773037a311473207783acd439f6e03","title":"深蹲一天做多少个好","date":"2019-01-02 18:30","category":"头条","author_name":"乐哈科普","url":"http://mini.eastday.com/mobile/190102183023786.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20190102/20190102183023_14c1345703567b1ac0a0790c54e932e2_1_mwpm_03200403.jpg"},{"uniquekey":"db57c92b1953cf98dde1f76d6fca49c6","title":"哭得\u201c梨花带雨\u201d！男子酒驾被查：饶了我吧，真的不想再考试了！","date":"2018-12-04 15:34","category":"头条","author_name":"东方头条","url":"http://mini.eastday.com/mobile/181204153420560.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20181204/20181204_2c071f0f0919e4843b8103c6f745adce_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20181204/20181204_815a44ad005dced81eb59d50da401228_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20181204/20181204_7783b0c9ac936a66d2e6e75cd3291a0b_cover_mwpm_03200403.jpg"},{"uniquekey":"6bb913d94fe2a15697077df9466b0821","title":"法新社：法国总理将宣布暂停增加燃油税","date":"2018-12-04 15:19","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/181204151906334.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20181204/20181204151906_fd22929d18294029c60c098389e00c70_1_mwpm_03200403.jpg"},{"uniquekey":"2cb7498cec4f635bc8560b45e94fa929","title":"开车需谨慎，翻车被击杀你怕不怕？见人秒爬打法，萌新学到了没？","date":"2018-12-04 15:08","category":"头条","author_name":"骚狐君","url":"http://mini.eastday.com/mobile/181204150822874.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20181204/20181204150822_c244940fb9b6b5024ff4cabbf16f9b49_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20181204/20181204150822_c244940fb9b6b5024ff4cabbf16f9b49_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20181204/20181204150822_c244940fb9b6b5024ff4cabbf16f9b49_1_mwpm_03200403.jpg"},{"uniquekey":"5eef5b2ac01ceff61086b391d5c8b11d","title":"火箭91:103森林狼！遭19分逆转，德帅爆内情，保罗遭炮轰","date":"2018-12-04 15:07","category":"头条","author_name":"体坛就你秀","url":"http://mini.eastday.com/mobile/181204150707027.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20181204/20181204_d1de6fbd5976eb03db297d2c86715174_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20181204/20181204_ac28f58c72db26383fd0089585422034_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20181204/20181204_0071ad5d710b06c69dc220920170f039_cover_mwpm_03200403.jpg"},{"uniquekey":"050b42d4c8b9e8079a9bd849328f82eb","title":"【中央纪委国家监委】数说落实中央八项规定精神六周年","date":"2018-12-04 15:06","category":"头条","author_name":"国际在线","url":"http://mini.eastday.com/mobile/181204150658784.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20181204/20181204150658_9e6bc9587ffc4d6ba3cd5fc2ee2d7063_1_mwpm_03200403.jpg"},{"uniquekey":"73157856e0bc35343887d4aa4076fca9","title":"杭州滨江宁波北仑两区任命两位代区长","date":"2018-12-04 15:05","category":"头条","author_name":"浙江在线","url":"http://mini.eastday.com/mobile/181204150550703.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20181204/20181204150550_d4bde2e66753f8daf0327c9b2be3c9ea_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20181204/20181204150550_d4bde2e66753f8daf0327c9b2be3c9ea_2_mwpm_03200403.jpg"},{"uniquekey":"930dff4d4c7baffb03646fbd70ffa1b2","title":"少见的贰角纸币，别再花掉了，一张价值200元！","date":"2018-12-04 15:04","category":"头条","author_name":"历史小咖秀","url":"http://mini.eastday.com/mobile/181204150442283.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20181204/20181204_0c6906c1b4c473f13b8f44cfb7971a2d_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20181204/20181204_207a83e7a624cd9022d5d89757d5b9ef_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20181204/20181204_c58c685666a1cd677f7fbc33d5e50107_mwpm_03200403.jpg"},{"uniquekey":"ebd31a2161f864f8a55ef415296a7dbd","title":"阴历这几月出生的女人，生完孩子后运势变好，婚后越过越富裕","date":"2018-12-04 15:03","category":"头条","author_name":"星座终点站","url":"http://mini.eastday.com/mobile/181204150343378.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20181204/20181204_5303a28cdea28c5eb4078d3059f5ea09_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20181204/20181204_a011bb5ba6680d6d306e21ae7157ec7f_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20181204/20181204_a233ba431a035b485f541e00a2dada4f_cover_mwpm_03200403.jpg"},{"uniquekey":"83584f441b19bb910e332311331f710c","title":"19中10、7中1，小卡的苦，德罗赞深有体会","date":"2018-12-04 15:03","category":"头条","author_name":"体育大嘴猴","url":"http://mini.eastday.com/mobile/181204150321542.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20181204/20181204_99ba0e05f78edffaece4dabd770a8eda_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20181204/20181204_0b38191c8b35300290475fb728d60eba_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20181204/20181204_db527be520ef9420164865ab2f199c99_cover_mwpm_03200403.jpg"},{"uniquekey":"5e454e7bc7ca24e5a2be08d78a09b7dc","title":"生日尾数是这个数的人，天生好命，财运旺盛，注定不是穷人命","date":"2018-12-04 14:57","category":"头条","author_name":"星座终点站","url":"http://mini.eastday.com/mobile/181204145742906.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20181204/20181204_9d9b9d59bd0b3477744ec5e3c619d129_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20181204/20181204_a4632f7e1c5f08d64266ecdf2fce7971_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20181204/20181204_00e9cc440a4438dda56b014f546d6bed_cover_mwpm_03200403.jpg"},{"uniquekey":"fa59aae0ae7a3908391882a0a344b069","title":"街拍女神：红色T恤搭配白色热裤，彰显时尚美女的气质","date":"2018-12-04 14:56","category":"头条","author_name":"美肤小霸王","url":"http://mini.eastday.com/mobile/181204145655202.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20181204/20181204_2752a8a562237fb35b4f19f5ac9c9386_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20181204/20181204_f04b0344c4731a640d97379fa740f54b_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20181204/20181204_7504e929a7704c5b824ecd9bd11725cf_mwpm_03200403.jpg"},{"uniquekey":"3f38971b63db502295d59fb027fefc64","title":"11名中国公民在日被捕，另有40余人下落不明！或涉非法务工","date":"2018-12-04 15:50","category":"头条","author_name":"纵相新闻","url":"http://mini.eastday.com/mobile/181204145629107.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20181204/20181204_c634513b898530d76da62b7df1c5b9eb_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20181204/20181204_679fd49613a032fe439ed8fabc1e5787_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday.com/mobile/20181204/20181204_815589b189b4fb9bcee370a75dfd231c_cover_mwpm_03200403.jpg"},{"uniquekey":"23f19ab6573b91bf25451d71367be746","title":"外媒昨日爆出一则重磅消息：欧盟将公布挑战美元霸主地位的\u201c大计划\u201d","date":"2018-12-04 14:56","category":"头条","author_name":"FX168财经","url":"http://mini.eastday.com/mobile/181204145601730.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20181204/20181204145601_19f580dff5faefef1361f0d0e9dc5614_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20181204/20181204145601_19f580dff5faefef1361f0d0e9dc5614_1_mwpm_03200403.jpg"},{"uniquekey":"890323af65b7a5d8ca89e544d87ce3ce","title":"张常宁和龚翔宇是江苏女排顶梁柱 但八强赛她们表现差强人意","date":"2018-12-04 14:55","category":"头条","author_name":"mvp喷","url":"http://mini.eastday.com/mobile/181204145511087.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20181204/20181204145511_d41d8cd98f00b204e9800998ecf8427e_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20181204/20181204145511_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20181204/20181204145511_d41d8cd98f00b204e9800998ecf8427e_5_mwpm_03200403.jpg"},{"uniquekey":"45b2860b9f45cf047412da07fd29a87b","title":"冬天养这6种\u201c耐冻花\u201d，大雪天\u201c开花\u201d3个月，养1盆就值了！","date":"2018-12-04 14:55","category":"头条","author_name":"石头花匠全网","url":"http://mini.eastday.com/mobile/181204145509378.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20181204/20181204_3a5c940193d0623db78e36c4563f8ab0_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05imgmini.eastday.com/mobile/20181204/20181204_7a159091fddf0e012a553fd8601bf915_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05imgmini.eastday.com/mobile/20181204/20181204_fa196e24eadd26221cb084e5a1f953d7_mwpm_03200403.jpg"},{"uniquekey":"1d1f7138e4b30734b5870cfe68b5476c","title":"没有计算机的年代，美国人这样甄别共产党和间谍","date":"2018-12-04 14:54","category":"头条","author_name":"东方头条","url":"http://mini.eastday.com/mobile/181204145453680.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20181204/20181204_4ba306a754dd6dc3fb33c74f30dcbb26_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20181204/20181204_5449d6b6cb78fba6470558c3ad94aebd_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20181204/20181204_9d45a53693f0095e9c9916fc693538a8_mwpm_03200403.jpg"},{"uniquekey":"eaaa9a9eabf5efd0f4e2c0659c40454c","title":"开不烂的3款合资车，除卡罗拉外，这两款月薪1500也开得起！","date":"2018-12-04 14:54","category":"头条","author_name":"清晨评车","url":"http://mini.eastday.com/mobile/181204145422505.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20181204/20181204145422_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20181204/20181204145422_d41d8cd98f00b204e9800998ecf8427e_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20181204/20181204145422_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg"},{"uniquekey":"be3c7864c6c616d3ad42aeda56da48b8","title":"意甲焦点战，国米被罗马逼平，联赛三轮不胜","date":"2018-12-04 14:54","category":"头条","author_name":"酱铺动漫","url":"http://mini.eastday.com/mobile/181204145402838.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20181204/20181204145402_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05imgmini.eastday.com/mobile/20181204/20181204145402_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05imgmini.eastday.com/mobile/20181204/20181204145402_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg"},{"uniquekey":"b8545c3cb410257595314b225b431fe7","title":"森果创始人黄铁森：信息化提升中国果蔬产业竞争力","date":"2018-12-04 14:52","category":"头条","author_name":"中国经济网","url":"http://mini.eastday.com/mobile/181204145231979.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20181204/20181204145231_5f333b03e59d234d9136ed0de6a0b1b6_7_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20181204/20181204145231_5f333b03e59d234d9136ed0de6a0b1b6_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20181204/20181204145231_5f333b03e59d234d9136ed0de6a0b1b6_4_mwpm_03200403.jpg"},{"uniquekey":"0ee94c02da07ce80212b7570b3522a95","title":"使用助听器可有效减缓认知能力衰退","date":"2018-12-04 14:52","category":"头条","author_name":"即刻笔记","url":"http://mini.eastday.com/mobile/181204145228196.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20181204/20181204145228_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg"},{"uniquekey":"476e664fd708a88da5c47303f6bb5e39","title":"助力2018重庆国际半马：跑起来！持续付出不亚于任何人的努力！","date":"2018-12-04 14:49","category":"头条","author_name":"十五载餐饮","url":"http://mini.eastday.com/mobile/181204144912765.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20181204/20181204144912_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20181204/20181204144912_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20181204/20181204144912_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg"},{"uniquekey":"bd538c24c92d8d23fb839711a55c33d2","title":"自称\u201c潘瘦瘦\u201d的潘粤明听说真的瘦了？他的瘦身两字秘诀","date":"2018-12-04 14:48","category":"头条","author_name":"每天学点健身术","url":"http://mini.eastday.com/mobile/181204144826454.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20181204/20181204_a9c9909cb5397f9404a5a3dee5d73556_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20181204/20181204_61049d5c48a723f74f5a947d8121efd0_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20181204/20181204_7fb23815545c171c682063dac0df0e0b_cover_mwpm_03200403.jpg"},{"uniquekey":"8761661d2e97901613d88fcef5f65028","title":"全球唯一不再属于中国的大熊猫，它们再也无法回到祖国，叫人心疼","date":"2018-12-04 14:44","category":"头条","author_name":"奇趣妙招","url":"http://mini.eastday.com/mobile/181204144459998.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20181204/20181204144459_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20181204/20181204144459_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday.com/mobile/20181204/20181204144459_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg"},{"uniquekey":"541a5753d74394425b5a486b932648ba","title":"给钱都花不完！台当局倒贴百万却既招不回人也送不出去人\u2026\u2026","date":"2018-12-04 14:44","category":"头条","author_name":"中国台湾网","url":"http://mini.eastday.com/mobile/181204144401285.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20181204/20181204144401_e974fc7b3400cc92868b473fe8385ea9_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20181204/20181204144401_e974fc7b3400cc92868b473fe8385ea9_5_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20181204/20181204144401_e974fc7b3400cc92868b473fe8385ea9_2_mwpm_03200403.jpg"},{"uniquekey":"000113d292c6a9a2e45544df88a24ed8","title":"\u201c2018沪台青年创新创业法治论坛\u201d在上海举行","date":"2018-12-04 14:44","category":"头条","author_name":"中国台湾网","url":"http://mini.eastday.com/mobile/181204144401145.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20181204/20181204144401_85e3e91538f59854b4729d2a5da5aa0c_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20181204/20181204144401_85e3e91538f59854b4729d2a5da5aa0c_2_mwpm_03200403.jpg"},{"uniquekey":"67795fa6a935f3ae8090cc7e375031fd","title":"王者荣耀2018年度主播盘点 寒夜上榜，张大仙也只能排第二？","date":"2018-12-04 14:43","category":"头条","author_name":"王者荣耀瑶儿","url":"http://mini.eastday.com/mobile/181204144326690.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20181204/20181204_062765a34d2490fe797e0fdac491f4d8_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20181204/20181204_a541cf1c2e6ef7e2a498fbaecec5174d_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20181204/20181204_c4eb62da961e7c3ba7d070b3cea46b1d_cover_mwpm_03200403.jpg"},{"uniquekey":"4d84bf6441b7e8cc94b38c9501e4bc83","title":"男子偷铲车欲刺杀特朗普撞翻其专车 被判入狱5年","date":"2018-12-04 14:42","category":"头条","author_name":"看看新闻网","url":"http://mini.eastday.com/mobile/181204144256311.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20181204/20181204144256_f88b0b648fea9cb14eb4399db5a83348_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20181204/20181204144256_f88b0b648fea9cb14eb4399db5a83348_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20181204/20181204144256_f88b0b648fea9cb14eb4399db5a83348_1_mwpm_03200403.jpg"},{"uniquekey":"8e87f005e93c347f65a8933296ec424a","title":"2018亚太企业数字经济发展年会：软通动力获优秀企业奖","date":"2018-12-04 14:42","category":"头条","author_name":"中国经济导报网","url":"http://mini.eastday.com/mobile/181204144248561.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20181204/20181204144248_a8926ab736b37a028674d1e749737374_1_mwpm_03200403.jpg"},{"uniquekey":"f0d3c0105709faf74f17bf05ea864fa1","title":"中国\u201c空城率\u201d最高的3座城市, 省会一线城市均上榜, 有你家吗","date":"2018-12-04 14:42","category":"头条","author_name":"搞笑猴哥","url":"http://mini.eastday.com/mobile/181204144233400.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20181204/20181204144233_2e6d4bbec176906cf7471626cc247fed_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20181204/20181204144233_2e6d4bbec176906cf7471626cc247fed_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20181204/20181204144233_2e6d4bbec176906cf7471626cc247fed_2_mwpm_03200403.jpg"}]
     */

    private String stat;
    private List<DataBean> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * uniquekey : 81773037a311473207783acd439f6e03
         * title : 深蹲一天做多少个好
         * date : 2019-01-02 18:30
         * category : 头条
         * author_name : 乐哈科普
         * url : http://mini.eastday.com/mobile/190102183023786.html
         * thumbnail_pic_s : http://07imgmini.eastday.com/mobile/20190102/20190102183023_14c1345703567b1ac0a0790c54e932e2_1_mwpm_03200403.jpg
         * thumbnail_pic_s02 : http://09imgmini.eastday.com/mobile/20181204/20181204_815a44ad005dced81eb59d50da401228_cover_mwpm_03200403.jpg
         * thumbnail_pic_s03 : http://09imgmini.eastday.com/mobile/20181204/20181204_7783b0c9ac936a66d2e6e75cd3291a0b_cover_mwpm_03200403.jpg
         */

        private String uniquekey;
        private String title;
        private String date;
        private String category;
        private String author_name;
        private String url;
        private String thumbnail_pic_s;
        private String thumbnail_pic_s02;
        private String thumbnail_pic_s03;

        public String getUniquekey() {
            return uniquekey;
        }

        public void setUniquekey(String uniquekey) {
            this.uniquekey = uniquekey;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
        }

        public String getThumbnail_pic_s02() {
            return thumbnail_pic_s02;
        }

        public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
            this.thumbnail_pic_s02 = thumbnail_pic_s02;
        }

        public String getThumbnail_pic_s03() {
            return thumbnail_pic_s03;
        }

        public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
            this.thumbnail_pic_s03 = thumbnail_pic_s03;
        }
    }
}