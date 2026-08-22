import StarRating from './StarRating'

/**
 * 小説登録・更新共通フォーム
 */
function NovelForm({
  messages,
  title,
  setTitle,
  author,
  setAuthor,
  readingStatus,
  setReadingStatus,
  rating,
  setRating,
  memo,
  setMemo,
  errors,
}) {
  return (
    <div className="novel-form">
      {/* タイトル */}
      <label className="form-field">
        {messages.common.title}

        <input
          type="text"
          value={title}
          maxLength={200}
          onChange={event => setTitle(event.target.value)}
        />

        {errors.title && (
          <span className="form-error">
            {errors.title}
          </span>
        )}
      </label>

      {/* 作者 */}
      <label className="form-field">
        {messages.common.author}

        <input
          type="text"
          value={author}
          maxLength={100}
          onChange={event => setAuthor(event.target.value)}
        />
      </label>

      {/* 読書状態 */}
      <label className="form-field">
        {messages.common.readingStatus}

        <select
          value={readingStatus}
          onChange={event => {
            const status = Number(event.target.value)

            setReadingStatus(status)

            // 未読に変更した場合は評価をクリア
            if (status === 0) {
              setRating(null)
            }
          }}
        >
          {[0, 1, 2, 3].map(status => (
            <option
              key={status}
              value={status}
            >
              {messages.common.readingStatusLabels[status]}
            </option>
          ))}
        </select>
      </label>

      {/* 評価 */}
      <label className="form-field">
        <span>
          {messages.common.rating}

          <span
            className="rating-help"
            title={messages.common.ratingNote}
          >
            ⓘ
          </span>
        </span>

        <StarRating
          rating={rating}
          editable={readingStatus !== 0}
          onChange={setRating}
        />

        {errors.rating && (
          <span className="form-error">
            {errors.rating}
          </span>
        )}
      </label>

      {/* メモ */}
      <label className="form-field form-field-last">
        {messages.common.memo}

        <textarea
          value={memo}
          maxLength={1000}
          onChange={event => setMemo(event.target.value)}
        />
      </label>
    </div>
  )
}

export default NovelForm