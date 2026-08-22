import { useState } from 'react'
import {
  Navigate,
  Route,
  Routes
} from 'react-router-dom'
import './App.css'
import Footer from './components/Footer'
import Header from './components/Header'
import NovelCreatePage from './pages/NovelCreatePage'
import NovelDetailPage from './pages/NovelDetailPage'
import NovelEditPage from './pages/NovelEditPage'
import NovelListPage from './pages/NovelListPage'

function App() {
  // 表示言語（ja：日本語、zh：中国語）
  const [language, setLanguage] = useState('ja')

  return (
    <div className="app">
      <Header
        language={language}
        setLanguage={setLanguage}
      />

      {/* 画面ルーティング */}
      <Routes>
        <Route
          path="/"
          element={<Navigate to="/novels" replace />}
        />

        <Route
          path="/novels"
          element={<NovelListPage language={language} />}
        />

        <Route
          path="/novels/new"
          element={<NovelCreatePage language={language} />}
        />

        <Route
          path="/novels/:novelId"
          element={<NovelDetailPage language={language} />}
        />

        <Route
          path="/novels/:novelId/edit"
          element={<NovelEditPage language={language} />}
        />
      </Routes>

      <Footer />
    </div>
  )
}

export default App