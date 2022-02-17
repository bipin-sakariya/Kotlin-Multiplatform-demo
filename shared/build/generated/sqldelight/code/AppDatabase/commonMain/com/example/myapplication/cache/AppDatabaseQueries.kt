package com.example.myapplication.cache

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String
import kotlin.Unit

public interface AppDatabaseQueries : Transacter {
  public fun <T : Any> selectAll(mapper: (
    id: Long,
    date: String,
    name: String,
    project: String
  ) -> T): Query<T>

  public fun selectAll(): Query<Hello>

  public fun <T : Any> selectAddData(mapper: (
    id: Long,
    date: String,
    name: String,
    project: String
  ) -> T): Query<T>

  public fun selectAddData(): Query<Hello>

  public fun insertHello(
    date: String,
    name: String,
    project: String
  ): Unit

  public fun DeleteSingleRecord(id: Long): Unit

  public fun UpdateData(
    date: String,
    name: String,
    project: String,
    id: Long
  ): Unit
}
