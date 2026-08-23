import { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import StarRating from '../components/StarRating'
import MESSAGES from '../i18n/messages'

function NovelDetailPage({ language }) {
  const messages = MESSAGES[language]
  const { novelId } = useParams()
  const navigate = useNavigate()

  // 小説詳細
  const [novel, setNovel] = useState(null)

  // 詳細取得状態
  const [loading, setLoading] = useState(true)
  const [errorType, setErrorType] = useState(null)

  // 削除エラー
  const [deleteError, setDeleteError] = useState(false)

  /**
   * 小説詳細取得
   */
  useEffect(() => {
    async function loadNovel() {
      try {
        const response = await fetch(
          `/api/novels/${novelId}`
        )

        if (response.status === 404) {
          setErrorType('notFound')
          return
        }

        if (!response.ok) {
          throw new Error(`HTTP Error: ${response.status}`)
        }

        const data = await response.json()
        setNovel(data)
      } catch (error) {
        console.error('小説詳細の取得に失敗しました。', error)
        setErrorType('error')
      } finally {
        setLoading(false)
      }
    }

    loadNovel()
  }, [novelId])

  /**
   * 小説削除
   */
  async function handleDelete() {
    const confirmed = window.confirm(
      messages.detail.deleteConfirm
    )

    if (!confirmed) {
      return
    }

    try {
      setDeleteError(false)

      const response = await fetch(
        `/api/novels/${novelId}`,
        {
          method: 'DELETE',
        }
      )

      if (!response.ok) {
        throw new Error(`HTTP Error: ${response.status}`)
      }

      navigate('/novels')
    } catch (error) {
      setDeleteError(true)
      console.error('小説の削除に失敗しました。', error)
    }
  }

  return (
    <main className="main">
      {loading ? (
        <p>{messages.common.loading}</p>
      ) : errorType === 'notFound' ? (
        <p>{messages.detail.notFound}</p>
      ) : errorType === 'error' ? (
        <p>{messages.detail.error}</p>
      ) : (
        <>
          <h2 className="detail-title">
            {messages.detail.title}
          </h2>

          <div className="novel-detail">
            <h3>{novel.title}</h3>

            {/* 作者 */}
            <p>
              {messages.common.author}：
              {novel.author?.trim() || messages.common.unknownAuthor}
            </p>

            {/* 読書状況 */}
            <p>
              {messages.common.readingStatus}：
              {messages.common.readingStatusLabels[novel.readingStatus]}
            </p>

            {/* 評価 */}
            <div>
              {messages.common.rating}：
              {novel.rating === null ? (
                <span>{messages.common.notRated}</span>
              ) : (
                <StarRating rating={novel.rating} />
              )}
            </div>

            {/* メモ */}
            <p>
              {messages.common.memo}：
              {novel.memo?.trim() || messages.common.none}
            </p>
          </div>

          {deleteError && (
            <p className="submit-error">
              {messages.detail.deleteError}
            </p>
          )}

          <div className="detail-actions">
            <Link to="/novels">
              {messages.common.backToList}
            </Link>

            <Link to={`/novels/${novelId}/edit`}>
              {messages.detail.edit}
            </Link>

            <button
              type="button"
              onClick={handleDelete}
            >
              {messages.detail.delete}
            </button>
          </div>
        </>
      )}
    </main>
  )
}

export default NovelDetailPage