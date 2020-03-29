# hadoop

hadoop的分布集群
1. core-site.xml
      <property>
        <name>fs.defaultFS</name>
        <value>hdfs://hadoop100:9000</value>
      </property>
      <property>
        <name>hadoop.tmp.dir/name>
        <value>/opt/module/hadoop/data/tmp</value>
      </property>

2. hdfs-site.xml
      <property> 
        <name>dfs.namenode.secondary.http-address</name> 
        <value>node12:50090</value> 
      </property>
      <property>
        <name>dfs.replication</name>
        <value>3</value>
      </property>


3. 修改hadoop-env.sh中的java_home


3. 配置slaves
    各个节点的ip地址

    3.1 将hadoop文件夹拷贝到每个节点上
    3.2 格式化namenode
        hadoop namenode -format
        
        启动hdfs集群
        bin/start-dfs.sh


        如果有一个数据被分成了两块，如何查看
        cat  第一块文件  >>  临时文件
        cat  第二块文件  >> 同一个临时文件

    3.3 查看hadoop页面
        http://192.168.5.128:50070/

4. 配置etc/hadoop/yarn-env.sh(环境变量配置文件)
    #java_home
    配置java_home的环境变量
    export java_home=/usr/local/java/jdk1.7.0_80
    

5. 配置mapred-env.sh（也是需要配置java_home）
    export java_home=/usr/local/java/jdk1.7.0_80

6. 配置yarn-site.xml
        <!--nomenodeManager获取数据的方式是shuffle-->
        <property>
                <name>yarn.nodemanager.aux-services</name>
                <value>mapreduce_shuffle</value>
        </property>
        <!--指定Yarn的老大(ResourceManager)的地址，就是自己的ip主机 node1需要配置到/etc/hosts-->     
        <property>
            <name>yarn.resourcemanager.hostname</name>
            <value>hadoop101</value>
        </property>

7. 配置mapred-site.xml.template，需要将后缀名改掉
    mv mapred-site.xml.template mapred-site.xml
    进行配置文件（这个运行的默认值是local）
        <!--告诉hadoop以后MR(Map/Reduce)运行在YARN上-->
        <property>
              <name>mapreduce.framework.name</name>
              <value>yarn</value>
        </property>
8. 启动集群：（在集群启动之前，需要确保namenode和datanode已经启动成功）
    ./start-yarn.sh

    查看ui: 
          http://192.168.5.128:8088/