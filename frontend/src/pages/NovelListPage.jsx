import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import NovelCard from '../components/NovelCard'
import MESSAGES from '../i18n/messages'

function NovelListPage({ language }) {
  const messages = MESSAGES[language]

  // 小説一覧
  const [novels, setNovels] = useState([])

  // 検索文字
  const [searchText, setSearchText] = useState('')

  // 一覧取得状態
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(false)

  // 入力された検索文字でタイトルを絞り込み
  const filteredNovels = novels.filter(
    novel => novel.title.includes(searchText.trim())
  )

  /**
   * 検索文字変更
   */
  function handleSearchChange(event) {
    setSearchText(event.target.value)
  }

  /**
   * 初回表示時に小説一覧を取得
   */
  useEffect(() => {
    async function loadNovels() {
      try {
        const response = await fetch(
          'http://localhost:8080/api/novels'
        )

        if (!response.ok) {
          throw new Error(`HTTP Error: ${response.status}`)
        }

        const data = await response.json()
        setNovels(data)
      } catch (error) {
        console.error('小説一覧の取得に失敗しました。', error)
        setError(true)
      } finally {
        setLoading(false)
      }
    }

    loadNovels()
  }, [])

  return (
    <main className="main">
      {/* 一覧画面ヘッダー */}
      <div className="list-header">
        <input
          type="text"
          placeholder={messages.list.search}
          value={searchText}
          onChange={handleSearchChange}
        />

        <h2>{messages.list.title}</h2>

        <Link
          to="/novels/new"
          className="create-link"
        >
          {messages.list.create}
        </Link>
      </div>

      {/* 小説一覧 */}
      <div className="novel-list">
        {loading ? (
          <p>{messages.common.loading}</p>
        ) : error ? (
          <p>{messages.list.error}</p>
        ) : novels.length === 0 ? (
          <p>{messages.list.noData}</p>
        ) : filteredNovels.length === 0 ? (
          <p>{messages.list.empty}</p>
        ) : (
          filteredNovels.map(novel => (
            <NovelCard
              key={novel.novelId}
              novelId={novel.novelId}
              title={novel.title}
              author={novel.author}
              rating={novel.rating}
              readingStatus={novel.readingStatus}
              language={language}
            />
          ))
        )}
      </div>
    </main>
  )
}

export default NovelListPage