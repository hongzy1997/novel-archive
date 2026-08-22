import { Link } from 'react-router-dom'
import MESSAGES from '../i18n/messages'
import StarRating from './StarRating'

/**
 * 小説一覧に表示する小説カード
 */
function NovelCard({
  novelId,
  title,
  author,
  rating,
  readingStatus,
  language,
}) {
  const messages = MESSAGES[language]

  return (
    <Link
      to={`/novels/${novelId}`}
      className="novel-card-link"
    >
      <article className="novel-card">
        <h3>{title}</h3>

        {/* 作者 */}
        <p>
          {messages.common.author}：
          {author?.trim() || messages.common.unknownAuthor}
        </p>

        {/* 読書状況 */}
        <p>
          {messages.common.readingStatus}：
          {messages.common.readingStatusLabels[readingStatus]}
        </p>

        {/* 評価 */}
        <div>
          {messages.common.rating}：
          {rating === null ? (
            <span>{messages.common.notRated}</span>
          ) : (
            <StarRating rating={rating} />
          )}
        </div>
      </article>
    </Link>
  )
}

export default NovelCard