function fn() {
  var config = {
    baseUrl: 'http://localhost:8080'
  };

  var dbConfig = {
    url: 'jdbc:postgresql://localhost:5433/booksite',
    username: 'postgres',
    password: 'postgres'
  };

  var DbUtils = Java.type('com.booksite.util.DbUtils');
  config.db = new DbUtils(dbConfig);

  return config;
}
