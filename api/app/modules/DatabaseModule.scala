package modules

import com.google.inject.{ AbstractModule, Inject, Provider, Singleton }
import com.google.inject.name.Names
import com.zaxxer.hikari.HikariDataSource
import ixias.slick.model.DataSourceName
import play.api.inject.ApplicationLifecycle
import ixias.slick.jdbc.MySQLProfile.api._
import ixias.slick.builder._

import scala.concurrent.Future

class DatabaseModule extends AbstractModule {
  // 以下のサイトを参考に実装してみましょう
  // https://nextbeat-dev.github.io/ixias/03-Slick.html

  override def configure(): Unit = {
    bind(classOf[Database])
      .annotatedWith(Names.named("master"))
      .toProvider(classOf[MasterDatabaseProvider])
      .asEagerSingleton()
    bind(classOf[Database])
      .annotatedWith(Names.named("slave"))
      .toProvider(classOf[SlaveDatabaseProvider])
      .asEagerSingleton()
  }
}

@Singleton
class MasterDatabaseProvider @Inject() (
  lifecycle: ApplicationLifecycle
) extends Provider[Database] {

  private val hikariConfigBuilder = HikariConfigBuilder.default(DataSourceName("ixias.db.mysql://master/to_do"))
  private val hikariConfig        = hikariConfigBuilder.build()
  hikariConfig.validate()

  private val dataSource = new HikariDataSource(hikariConfig)

  lifecycle.addStopHook { () =>
    Future.successful(dataSource.close())
  }

  override def get(): Database = DatabaseBuilder.fromHikariDataSource(dataSource)
}

@Singleton
class SlaveDatabaseProvider @Inject() (
  lifecycle: ApplicationLifecycle
) extends Provider[Database] {

  private val hikariConfigBuilder = HikariConfigBuilder.default(DataSourceName("ixias.db.mysql://slave/to_do"))
  private val hikariConfig        = hikariConfigBuilder.build()
  hikariConfig.validate()

  private val dataSource = new HikariDataSource(hikariConfig)

  lifecycle.addStopHook { () =>
    Future.successful(dataSource.close())
  }

  override def get(): Database = DatabaseBuilder.fromHikariDataSource(dataSource)
}
