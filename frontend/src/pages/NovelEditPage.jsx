import { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import NovelForm from '../components/NovelForm'
import MESSAGES from '../i18n/messages'

function NovelEditPage({ language }) {
  const messages = MESSAGES[language]
  const { novelId } = useParams()
  const navigate = useNavigate()

  // 入力値
  const [title, setTitle] = useState('')
  const [author, setAuthor] = useState('')
  const [readingStatus, setReadingStatus] = useState(0)
  const [rating, setRating] = useState(null)
  const [memo, setMemo] = useState('')

  // 詳細取得状態
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(false)

  // バリデーションエラー
  const [errors, setErrors] = useState({})

  // リセット用の初期データ
  const [initialData, setInitialData] = useState(null)

  // API更新エラー
  const [submitError, setSubmitError] = useState(false)
  const [duplicateError, setDuplicateError] = useState(false)

  /**
   * 更新対象の小説情報を取得
   */
  useEffect(() => {
    async function loadNovel() {
      try {
        const response = await fetch(
          `http://localhost:8080/api/novels/${novelId}`
        )

        if (!response.ok) {
          throw new Error(`HTTP Error: ${response.status}`)
        }

        const data = await response.json()

        setInitialData(data)

        setTitle(data.title)
        setAuthor(data.author ?? '')
        setReadingStatus(data.readingStatus)
        setRating(data.rating)
        setMemo(data.memo ?? '')
      } catch (error) {
        console.error('小説詳細の取得に失敗しました。', error)
        setError(true)
      } finally {
        setLoading(false)
      }
    }

    loadNovel()
  }, [novelId])

  /**
   * 入力チェック
   */
  function validate() {
    const newErrors = {}

    if (!title.trim()) {
      newErrors.title = messages.edit.titleRequired
    }

    if (readingStatus !== 0 && rating === null) {
      newErrors.rating = messages.common.ratingRequired
    }

    setErrors(newErrors)

    return Object.keys(newErrors).length === 0
  }

  /**
   * 小説更新
   */
  async function handleUpdate() {
    if (!validate()) {
      return
    }

    setSubmitError(false)
    setDuplicateError(false)

    try {
      const response = await fetch(
        `http://localhost:8080/api/novels/${novelId}`,
        {
          method: 'PUT',
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

      // 他の小説と同一タイトルの場合
      if (response.status === 409) {
        setDuplicateError(true)
        return
      }

      if (!response.ok) {
        throw new Error(`HTTP Error: ${response.status}`)
      }

      navigate(`/novels/${novelId}`)
    } catch (error) {
      console.error('小説の更新に失敗しました。', error)
      setSubmitError(true)
    }
  }

  /**
   * 入力内容を更新前の状態に戻す
   */
  function handleReset() {
    if (!initialData) {
      return
    }

    setTitle(initialData.title)
    setAuthor(initialData.author ?? '')
    setReadingStatus(initialData.readingStatus)
    setRating(initialData.rating)
    setMemo(initialData.memo ?? '')

    setErrors({})
    setSubmitError(false)
    setDuplicateError(false)
  }

  return (
    <main className="main">
      {loading ? (
        <p>{messages.common.loading}</p>
      ) : error ? (
        <p>{messages.edit.loadError}</p>
      ) : (
        <>
          <h2 className="form-title">
            {messages.edit.title}
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
              {messages.edit.duplicateError}
            </p>
          )}

          {submitError && (
            <p className="submit-error">
              {messages.edit.updateError}
            </p>
          )}

          <div className="form-actions">
            <Link to={`/novels/${novelId}`}>
              {messages.common.back}
            </Link>

            <button
              type="button"
              onClick={handleReset}
            >
              {messages.edit.reset}
            </button>

            <button
              type="button"
              onClick={handleUpdate}
            >
              {messages.edit.submit}
            </button>
          </div>
        </>
      )}
    </main>
  )
}

export default NovelEditPage