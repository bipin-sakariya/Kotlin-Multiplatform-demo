package com.example.myapplication.cache.shared

import com.example.myapplication.cache.AppDatabase
import com.example.myapplication.cache.AppDatabaseQueries
import com.example.myapplication.cache.Hello
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlDriver.Schema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), AppDatabase {
  public override val appDatabaseQueries: AppDatabaseQueriesImpl = AppDatabaseQueriesImpl(this,
      driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE  hello(
          |    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
          |    date TEXT NOT NULL,
          |    name TEXT NOT NULL,
          |    project TEXT NOT NULL
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class AppDatabaseQueriesImpl(
  private val database: AppDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), AppDatabaseQueries {
  internal val selectAll: MutableList<Query<*>> = copyOnWriteList()

  internal val selectAddData: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> selectAll(mapper: (
    id: Long,
    date: String,
    name: String,
    project: String
  ) -> T): Query<T> = Query(1606411086, selectAll, driver, "AppDatabase.sq", "selectAll", """
  |SELECT *
  |FROM hello
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!
    )
  }

  public override fun selectAll(): Query<Hello> = selectAll { id, date, name, project ->
    Hello(
      id,
      date,
      name,
      project
    )
  }

  public override fun <T : Any> selectAddData(mapper: (
    id: Long,
    date: String,
    name: String,
    project: String
  ) -> T): Query<T> = Query(-580227304, selectAddData, driver, "AppDatabase.sq", "selectAddData",
      "SELECT * FROM hello") { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!
    )
  }

  public override fun selectAddData(): Query<Hello> = selectAddData { id, date, name, project ->
    Hello(
      id,
      date,
      name,
      project
    )
  }

  public override fun insertHello(
    date: String,
    name: String,
    project: String
  ): Unit {
    driver.execute(1555450786, """
    |INSERT OR REPLACE
    |INTO hello (
    |    date,
    |    name,
    |    project
    |) VALUES ( ? ,?,?)
    """.trimMargin(), 3) {
      bindString(1, date)
      bindString(2, name)
      bindString(3, project)
    }
    notifyQueries(1555450786, {database.appDatabaseQueries.selectAddData +
        database.appDatabaseQueries.selectAll})
  }

  public override fun DeleteSingleRecord(id: Long): Unit {
    driver.execute(58666491, """DELETE FROM hello WHERE id = ?""", 1) {
      bindLong(1, id)
    }
    notifyQueries(58666491, {database.appDatabaseQueries.selectAddData +
        database.appDatabaseQueries.selectAll})
  }

  public override fun UpdateData(
    date: String,
    name: String,
    project: String,
    id: Long
  ): Unit {
    driver.execute(1612705034, """
    |UPDATE hello
    |SET date = ?,
    |    name = ?,
    |    project = ?
    |WHERE
    |    id = ?
    """.trimMargin(), 4) {
      bindString(1, date)
      bindString(2, name)
      bindString(3, project)
      bindLong(4, id)
    }
    notifyQueries(1612705034, {database.appDatabaseQueries.selectAddData +
        database.appDatabaseQueries.selectAll})
  }
}
