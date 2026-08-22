-- ============================================================
-- Novel Archive
-- Version     : V0
-- File        : Insert_Sample_Data.sql
-- Description : 動作確認用サンプルデータの登録
-- ============================================================

/* =========================================================
   好き / 推薦
   READING_STATUS = 2（読了）
   ========================================================= */

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    'ようこそ実力至上主義の教室へ',
    '衣笠彰梧',
    2,
    9,
    '剧情9，人物8'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '全职高手',
    '蝴蝶蓝',
    2,
    10,
    '剧情10，人物9'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '天才俱乐部',
    '城城与蝉',
    2,
    10,
    '剧情9，人物10'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '捞尸人',
    '纯洁滴小龙',
    2,
    10,
    '剧情9，人物10'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '十日终焉',
    '杀虫队队员',
    2,
    10,
    '剧情10，人物9'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '序列：吃神者',
    '不要大脑要小脑',
    2,
    9,
    '剧情9，人物8'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '英雄联盟之谁与争锋',
    '乱',
    2,
    9,
    '剧情9，人物9'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '大主宰',
    '天蚕土豆',
    2,
    9,
    '剧情8，人物9'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '武道宗师',
    '爱潜水的乌贼',
    2,
    10,
    '剧情8，人物10'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '我的超能力每周刷新',
    '一片雪饼',
    2,
    9,
    '剧情8，人物9'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '第一序列',
    '会说话的肘子',
    2,
    10,
    '剧情10，人物9'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '惊悚乐园',
    '三天两觉',
    2,
    9,
    '剧情8，人物8'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '大王饶命',
    '会说话的肘子',
    2,
    9,
    '剧情8，人物8'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '从红月开始',
    '黑山老鬼',
    2,
    9,
    '剧情8，人物8'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '大道朝天',
    '猫腻',
    2,
    9,
    '剧情8，人物9'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '全职法师',
    '乱',
    2,
    9,
    '剧情8，人物9'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '择天记',
    '猫腻',
    2,
    9,
    '剧情8，人物8'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '长夜余火',
    '爱潜水的乌贼',
    2,
    9,
    '剧情8，人物8'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '谁让他修仙的',
    '最白的乌鸦',
    2,
    9,
    '剧情8，人物8'
);


/* =========================================================
   看过 / 基本看完 / 认真看过一段
   ========================================================= */

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '稳住别浪',
    '跳舞',
    3,
    5,
    '看过，多女主，弃了。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '不科学御兽',
    '轻泉流响',
    3,
    6,
    '看过。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '灵境行者',
    '卖报小郎君',
    3,
    6,
    '剧情可以，但现在感觉不会继续看，感情方面不太适配。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '术师手册',
    '听日',
    3,
    6,
    '前期可以，题材很新颖；后期有点无聊，主线感偏弱。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '赤心巡天',
    '情何以甚',
    3,
    6,
    '写得还可以，但成长路线不太喜欢。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '深海余烬',
    '远瞳',
    3,
    5,
    '看过，前期弃。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '这游戏也太真实了',
    '晨星LL',
    3,
    6,
    '中期开始乏味，感情方面也偏淡。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '我在精神病院学斩神',
    '三九音域',
    3,
    6,
    '中期开始失去继续阅读的欲望。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '修真四万年',
    '卧牛真人',
    3,
    5,
    '看过，弃坑原因已忘。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '天启预报',
    '风月',
    3,
    5,
    '看过，弃坑原因已忘。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '五行天',
    '方想',
    2,
    6,
    '基本读完。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '诡秘之主',
    '爱潜水的乌贼',
    2,
    9,
    '基本读完。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '将夜',
    '猫腻',
    3,
    5,
    '看过，原因不详。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '间客',
    '猫腻',
    3,
    5,
    '看过，原因不详。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '明克街13号',
    '纯洁滴小龙',
    3,
    5,
    '看过，原因不详。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '我有一座冒险屋',
    '我会修空调',
    3,
    5,
    '人物太单调。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '我的治愈系游戏',
    '我会修空调',
    3,
    5,
    '人物太单调。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '修真聊天群',
    '圣骑士的传说',
    3,
    5,
    '过于平淡，没什么推进的感觉。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '一世之尊',
    '爱潜水的乌贼',
    3,
    6,
    '中后期副本疲惫。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '冠军之心',
    '林海听涛',
    3,
    6,
    '前中期优秀。后期比赛成为主要爽点，日常和人物关系减少，约200万字弃。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '玩家凶猛',
    '黑灯夏火',
    3,
    6,
    '中后期副本疲惫。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '全球高武',
    '老鹰吃小鸡',
    3,
    6,
    '感情线偏薄，弃。'
);


/* =========================================================
   开头弃坑 / 没坚持下去 / 明确不适配
   ========================================================= */

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '卡徒',
    '方想',
    3,
    4,
    '看过一部分，比较压抑，弃。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '异常生物见闻录',
    '远瞳',
    3,
    4,
    '远瞳风格偏慢热、铺世界观，刺激密度不太适配。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '黎明之剑',
    '远瞳',
    3,
    4,
    '没坚持下去，远瞳风格不太适配。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '牧神记',
    '宅猪',
    3,
    4,
    '没看下去，早期可能因为女主或感情线不符合期待。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '魔法科高校の劣等生',
    '佐島勤',
    3,
    3,
    '序盤中断。人物名が多く、兄妹恋愛設定が合わなかった。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '我师兄实在太稳健了',
    '言归正传',
    3,
    3,
    '多女主、后宫倾向，不适配。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '凡人修仙传',
    '忘语',
    3,
    3,
    '传统升级文，人物关系薄弱，不喜欢。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '穹顶之上',
    '人间武库',
    3,
    4,
    '男主韩青禹、蔚蓝体系有印象；感情线较弱，不作为优先阅读作品。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '诸界末日在线',
    '烟火成城',
    3,
    3,
    '前期装逼打脸感较重，爽点偏直接，不符合喜欢的高级爽点风格。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '我真没想当训练家',
    '北川南海',
    3,
    3,
    '前期设定看不懂，弃。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '信息全知者',
    '魔性沧月',
    3,
    3,
    '没有代入感，弃。'
);

INSERT INTO NOVELS (
    NOVEL_ID, TITLE, AUTHOR, READING_STATUS, RATING, MEMO
) VALUES (
    novel_seq.NEXTVAL,
    '超神机械师',
    '齐佩甲',
    3,
    4,
    '玩家流、NPC流、势力经营流，不太有感觉。'
);

COMMIT;