# k8s集群内中间件集群方案demo
## 搭建部署方案
### mysql主从方案
- <https://kubernetes.io/docs/tasks/run-application/run-replicated-stateful-application/>
### mongo副本集方案
- 本方案采用副本集架构（Replica Set）搭建mongodb集群。为了防止单点故障就需要引入副本（Replication），当发生硬件故障或者其他原因造成的宕机时，可以使用副本进行恢复，最好能够自动的故障转移（failover）。有时引入副本是为了读写分离，将读的请求分流到副本上，减轻主（Primary）的
读压力。而MongoDb的Replica Set都能满足这些要求。
- Replica Set的一堆mongod的实例集合，它们有着同样的数据内容。包含三类角色：- 主节点（Primary）：接收所有的写请求，然后把修改同步到所有Secondary。一个Replica Set只能有一个Primary节点，当Primary挂掉后，其他Secondary或者Arbiter节点会重新选举出来一个Primary。默认读请求也是发到Primary节点处理的，需要转发到Secondary需要客户端修改一下连接配置。
- 副本节点（Secondary）：与主节点保持同样的数据集。当主节点挂掉的时候，参与选主。
注意：Secondary宕机不受影响，Primary宕机才会进行重新选主。一个自动failover的Replica Set节点必须为奇数，目的是选主投票的时候要有一个大多数才能进行选主决策。
- <https://github.com/cvallance/mongo-k8s-sidecar>
### rabbitmq集群方案
- <https://github.com/rabbitmq/rabbitmq-peer-discovery-k8s>
- <https://github.com/rabbitmq/rabbitmq-autocluster>
### kafka集群方案
- <https://github.com/kubernetes/contrib/tree/master/statefulsets>
### es集群方案
- <https://github.com/kubernetes/kubernetes/tree/master/cluster/addons/fluentd-elasticsearch>
## 集群内调试及使用
