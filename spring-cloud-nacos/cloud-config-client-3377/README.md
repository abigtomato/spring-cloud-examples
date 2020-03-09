# nacos配置的Data Id设置
* `${spring.application.name}-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}`
    * spring.application.name: 项目名
    * spring.profiles.active: 当前环境（dev，prod，test）
    * spring.cloud.nacos.config.file-extension: 配置文件的格式（json，yaml，...）