# 
<h1 align="center"><a href="https://github.com/YuanSim/" target="_blank">Spring Design Pattern Demo</a></h1>


<p align="center">
  <span>中文 | <a href="./README.en.md">English</a></span>
</p>



## 项目简介

查看 [TODO](./TODO.md) 文件

`spring design pattern demo` 是一个用来深度学习与实战 `设计模式 + spring容器` 的项目

## 开发环境

- **JDK1.8 +**
- **IntelliJ IDEA ULTIMATE 2019.3 +** (*注意：务必使用 IDEA 开发，同时保证安装 `lombok` 插件*)
- **Mysql 5.7 +** (*尽量保证使用 5.7 版本以上，因为 5.7 版本加了一些新特性，同时不向下兼容。本 demo 里会尽量避免这种不兼容的地方，但还是建议尽量保证 5.7 版本以上*)

## 模块介绍

| Module 名称                                                  | Module 介绍                                                    |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [sim-framework-template-demo](./sim-framework-template-demo) | 架构模块pom                                                       |
| [spring-design-pattern-demo-dependencies](./spring-design-pattern-demo-dependencies)|   包管理                               |
| [spring-strategy-pattern-demo](./spring-strategy-pattern-demo)|   策略模式pom                                                 |                                        |
| [spring-responsibility-chain-demo](./spring-responsibility-chain-demo)|   责任链模式                                           |
| [spring-http-template-demo](./spring-http-template-demo) | 关于请求与返回的有些全局配置                                             |
