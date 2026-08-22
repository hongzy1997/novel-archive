import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import NovelForm from '../components/NovelForm'
import MESSAGES from '../i18n/messages'

function NovelCreatePage({ language }) {
  const messages = MESSAGES[language]
  const navigate = useNavigate()

  // 入力値
  const [title, setTitle] = useState('')
  const [author, setAuthor] = useState('')
  const [readingStatus, setReadingStatus] = useState(0)
  const [rating, setRating] = useState(null)
  const [memo, setMemo] = useState('')

  // バリデーションエラー
  const [errors, setErrors] = useState({})

  // API登録エラー
  const [submitError, setSubmitError] = useState(false)
  const [duplicateError, setDuplicateError] = useState(false)

  /**
   * 入力内容を初期状態に戻す
   */
  function handleClear() {
    setTitle('')
    setAuthor('')
    setReadingStatus(0)
    setRating(null)
    setMemo('')
    setErrors({})
    setSubmitError(false)
    setDuplicateError(false)
  }

  /**
   * 入力チェック
   */
  function validate() {
    const newErrors = {}

    if (!title.trim()) {
      newErrors.title = messages.create.titleRequired
    }

    if (readingStatus !== 0 && rating === null) {
      newErrors.rating = messages.common.ratingRequired
    }

    setErrors(newErrors)

    return Object.keys(newErrors).length === 0
  }

  /**
   * 小説登録
   */
  async function handleSubmit() {
    if (!validate()) {
      return
    }

    setSubmitError(false)
    setDuplicateError(false)

    try {
      const response = await fetch(
        'http://localhost:8080/api/novels',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            title: title.trim(),
            author: author.trim(),
            readingStatus,
            rating,
            memo: memo.trim(),
          }),
        }
      )

      // 同一タイトルが既に存在
      if (response.status === 409) {
        setDuplicateError(true)
        return
      }

      if (!response.ok) {
        throw new Error(`HTTP Error: ${response.status}`)
      }

      const data = await response.json()

      navigate(`/novels/${data.novelId}`)
    } catch (error) {
      console.error('小説の登録に失敗しました。', error)
      setSubmitError(true)
    }
  }

  return (
    <main className="main">
      <h2 className="form-title">
        {messages.create.title}
      </h2>

      <NovelForm
        messages={messages}
        title={title}
        setTitle={setTitle}
        author={author}
        setAuthor={setAuthor}
        readingStatus={readingStatus}
        setReadingStatus={setReadingStatus}
        rating={rating}
        setRating={setRating}
        memo={memo}
        setMemo={setMemo}
        errors={errors}
      />

      {duplicateError && (
        <p className="submit-error">
          {messages.create.duplicateError}
        </p>
      )}

      {submitError && (
        <p className="submit-error">
          {messages.create.error}
        </p>
      )}

      <div className="form-actions">
        <Link
          to="/novels"
          className="form-back-link"
        >
          {messages.common.backToList}
        </Link>

        <button
          type="button"
          onClick={handleClear}
        >
          {messages.common.clear}
        </button>

        <button
          type="button"
          onClick={handleSubmit}
        >
          {messages.create.submit}
        </button>
      </div>
    </main>
  )
}

export default NovelCreatePage