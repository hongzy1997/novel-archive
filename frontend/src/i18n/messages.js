const MESSAGES = {
  ja: {
    common: {
      systemName: '小説管理システム',
      title: 'タイトル',
      author: '作者',
      rating: '評価',
      notRated: '未評価',
      unknownAuthor: '不明',
      none: 'なし',
      ratingNote: '（未読の場合は評価できません）',
      readingStatus: '読書状態',
      memo: 'メモ',
      backToList: '一覧へ戻る',
      clear: 'クリア',
      ratingRequired: '未読以外の場合、評価は必須です。',
      loading: '読込中...',
      cancel: 'キャンセル',
      back: '戻る',

      readingStatusLabels: {
        0: '未読',
        1: '読書中',
        2: '読了',
        3: '中断',
      },
    },

    list: {
      title: '小説一覧',
      create: '新規登録',
      search: '小説名を検索',
      empty: '該当する小説がありません。',
      noData: '登録されている小説がありません。',
      error: '小説一覧の取得に失敗しました。',
    },

    detail: {
      title: '小説詳細',
      edit: '更新',
      delete: '削除',
      notFound: '小説が見つかりません。',
      error: '小説詳細の取得に失敗しました。',
      deleteError: '小説の削除に失敗しました。',
      deleteConfirm: 'この小説を削除しますか？',
    },

    create: {
      title: '小説登録',
      submit: '登録',
      titleRequired: 'タイトルは必須です。',
      duplicateError: '同じタイトルの小説が既に登録されています。',
      error: '小説の登録に失敗しました。',
    },

    edit: {
      title: '小説更新',
      reset: 'リセット',
      submit: '更新',
      titleLabel: 'タイトル',
      titleRequired: 'タイトルは必須です。',
      loadError: '小説情報の取得に失敗しました。',
      updateError: '小説の更新に失敗しました。',
      duplicateError: '同じタイトルの小説が既に登録されています。',
    },
  },

  zh: {
    common: {
      systemName: '小说管理系统',
      title: '标题',
      author: '作者',
      rating: '评价',
      notRated: '未评价',
      unknownAuthor: '未知',
      none: '无',
      ratingNote: '（未读时无法评价）',
      readingStatus: '阅读状态',
      memo: '备注',
      ratingRequired: '非未读状态下，评价为必填项。',

      loading: '加载中...',
      cancel: '取消',
      back: '返回',
      backToList: '返回列表',
      clear: '清空',

      readingStatusLabels: {
        0: '未读',
        1: '阅读中',
        2: '已读',
        3: '中断',
      },
    },

    list: {
      title: '小说列表',
      create: '新增小说',
      search: '搜索小说名',
      empty: '没有符合条件的小说。',
      noData: '暂无已登记的小说。',
      error: '小说列表获取失败。',
    },

    detail: {
      title: '小说详情',
      edit: '编辑',
      delete: '删除',

      notFound: '未找到该小说。',
      error: '小说详情获取失败。',
      deleteError: '小说删除失败。',

      deleteConfirm: '确定要删除这本小说吗？',
    },

    create: {
      title: '新增小说',
      submit: '新增',
      titleRequired: '请输入标题。',
      duplicateError: '已存在相同标题的小说。',
      error: '小说新增失败。',
    },

    edit: {
      title: '编辑小说',
      reset: '重置',
      submit: '更新',
      titleLabel: '标题',
      titleRequired: '请输入标题。',
      loadError: '小说信息获取失败。',
      updateError: '小说更新失败。',
      duplicateError: '已存在相同标题的小说。',
    },
  },
}

export default MESSAGES