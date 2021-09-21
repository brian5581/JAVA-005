#### 配置主节点

mysql命令登录到主节点：mysql -uroot -hlocalhost -P3316

mysql> CREATE USER 'repl'@'%' IDENTIFIED BY '123456';
Query OK, 0 rows affected (0.11 sec)

mysql> GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
Query OK, 0 rows affected (0.12 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.10 sec)

mysql> show master status;


创建数据库：create database store_demo;

#### 配置从节点
mysql > CHANGE MASTER TO MASTER_HOST='localhost',
                         MASTER_PORT=3316,
						 MASTER_USER='repl',
                         MASTER_PASSWORD='123456',
						 MASTER_LOG_FILE='mysql-bin.000005',
                         MASTER_LOG_POS=154;

创建数据库：create database store_demo;


#### 查看命令

可以通过show master status\G, show slave status\G 查看状态