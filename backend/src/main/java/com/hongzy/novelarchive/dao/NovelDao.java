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

@Dao
@ConfigAutowireable
public interface NovelDao {

    @Select
    List<Novel> selectAll();

    @Select
    Optional<Novel> selectById(Long novelId);

    @Insert
    int insert(Novel novel);

    @Update
    int update(Novel novel);

    @Delete
    int delete(Novel novel);

}