package com.hongzy.novelarchive.dao;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.hongzy.novelarchive.entity.Novel;

/**
 * 小説情報DAO
 */
@Dao
@ConfigAutowireable
public interface NovelDao {

    /** 小説一覧取得 */
    @Select
    List<Novel> selectAll();

    /** 小説詳細取得 */
    @Select
    Optional<Novel> selectById(Long novelId);

    /** 同一タイトルの小説存在チェック */
    @Select
    boolean existsByTitle(String title);

    /** 他の小説で同一タイトルが存在するかチェック */
    @Select
    boolean existsByTitleAndNovelIdNot(
            String title,
            Long novelId);

    /**
     * 小説登録
     * null項目をINSERT対象外とし、DBのDEFAULT値を利用する。
     */
    @Insert(excludeNull = true)
    int insert(Novel novel);

    /** 小説更新 */
    @Update
    int update(Novel novel);

    /** 小説削除 */
    @Delete
    int delete(Novel novel);
}