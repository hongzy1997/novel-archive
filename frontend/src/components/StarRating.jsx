/**
 * 評価を5段階の星で表示
 * ratingは1～10のため、2で割って5段階に変換する。
 */
function StarRating({
  rating,
  editable = false,
  onChange,
}) {
  const starValue = rating / 2

  return (
    <div className="star-rating">
      {[1, 2, 3, 4, 5].map(star => {
        // 実星・半星・空星を判定
        const isFull = star <= starValue
        const isHalf = star - 0.5 === starValue

        return (
          <span
            key={star}
            className="star"
          >
            {editable && (
              <>
                {/* 星の左半分：奇数評価（1, 3, 5, 7, 9） */}
                <span
                  className="star-click-area left"
                  onClick={() => onChange(star * 2 - 1)}
                />

                {/* 星の右半分：偶数評価（2, 4, 6, 8, 10） */}
                <span
                  className="star-click-area right"
                  onClick={() => onChange(star * 2)}
                />
              </>
            )}

            {isFull ? '★' : isHalf ? <HalfStar /> : '☆'}
          </span>
        )
      })}
    </div>
  )
}

export default StarRating

/**
 * 半星表示
 * 空星の上に実星を重ね、左半分のみ表示する。
 */
function HalfStar() {
  return (
    <span className="half-star">
      <span className="empty-star">☆</span>
      <span className="filled-star">★</span>
    </span>
  )
}