# 踩的坑
操作数据（UPDATE，DELETE）的时候必须要在@Query注解上再添加一个@Modifying的注解，不然会返回‘could not extract ResultSet’的错误——坑了老子两三个小时才发现原因。
