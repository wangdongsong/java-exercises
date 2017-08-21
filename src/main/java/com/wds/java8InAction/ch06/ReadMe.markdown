# 第6章，用流收集数据

* 用Collectors类创建和使用收集器
* 将数据流归约为一个值
* 汇总：归约的特殊情况
* 数据分组和分区
* 开发自己的定义收集器


```java
counting
joining
maxBy
```
以上Java8中预定义的所有收集器，都是一个可以用reducing工厂方法定义的归约过程的特殊情况而已。Collectors.reducing工厂方法是所有
这些特殊情况的一般化。可以使用reducing工厂方法创建收集器替换以上所有的。