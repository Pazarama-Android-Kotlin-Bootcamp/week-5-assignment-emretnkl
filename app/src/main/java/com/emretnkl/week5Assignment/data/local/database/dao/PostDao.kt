package com.emretnkl.week5Assignment.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.emretnkl.week5Assignment.data.local.database.base.BaseDao
import com.emretnkl.week5Assignment.data.local.database.entity.PostEntity
import com.emretnkl.week5Assignment.utils.Constants


@Dao
interface PostDao : BaseDao<PostEntity> {
    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME}")
    fun getAllPosts(): List<PostEntity>

    @Query("DELETE FROM ${Constants.TABLE_POST_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME} WHERE postId = :postId")
    fun getPostById(postId: String): PostEntity?



}