import MESSAGES from '../i18n/messages'

function Header({ language, setLanguage }) {
  const messages = MESSAGES[language]

  return (
    <header className="header">
      {/* タイトルを中央配置するためのレイアウト用要素 */}
      <div></div>

      <div className="header-title">
        <h1>Novel Archive</h1>
        <p>{messages.common.systemName}</p>
      </div>

      {/* 表示言語切替 */}
      <div className="language-switcher">
        <button
          type="button"
          className={language === 'ja' ? 'active' : ''}
          onClick={() => setLanguage('ja')}
        >
          日本語
        </button>

        <span>|</span>

        <button
          type="button"
          className={language === 'zh' ? 'active' : ''}
          onClick={() => setLanguage('zh')}
        >
          中文
        </button>
      </div>
    </header>
  )
}

export default Header