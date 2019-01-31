在本项目中需要注意的问题：
1：appliactionContext.xml文件和spring-servlet.xml文件存放在WEB-INF目录下，之前放在src下面出错，src应该也属于classpath路径啊，不知道怎么回事。查看资料发现：classpath是WEB-INF下的clases文件夹



盐值加密原理：
	我们知道，如果直接对密码进行散列，那么黑客可以对通过获得这个密码散列值，然后通过查散列值字典（例如MD5密码破解网站），得到某用户的密码。

　  　 加Salt可以一定程度上解决这一问题。所谓加Salt方法，就是加点“佐料”。其基本想法是这样的：当用户首次提供密码时（通常是注册时），由系统自动往这个密码里撒一些“佐料”，然后再散列。而当用户登录时，系统为用户提供的代码撒上同样的“佐料”，然后散列，再比较散列值，已确定密码是否正确。

　      这里的“佐料”被称作“Salt值”，这个值是由系统随机生成的，并且只有系统知道。这样，即便两个用户使用了同一个密码，由于系统为它们生成的salt值不同，他们的散列值也是不同的。即便黑客可以通过自己的密码和自己生成的散列值来找具有特定密码的用户，但这个几率太小了（密码和salt值都得和黑客使用的一样才行）。



1：为什么使用MD5盐值加密：
2：如何做到：
	1）：在doGetAuthenticationInfo方法返回值创建SimpleAuthenticationInfo对象的时候，需要使用
	SimpleHash(hashAlgorithmName, credentials, salt, hashIterations)构造器
	2）：使用ByteSource.Util.bytes()来计算盐值
	3）：盐值需要唯一：一般使用随机字符串或user id 
	4）：使用new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations) 来计算盐值加密后的密码的值
这样操作的话，即便2个用户的密码相同，但是存入数据库中的密码字符串也不相同，增加了安全性

1：如何把一个字符串加密为MD5（MD5加密是不可逆的）
2：替换当前Realm的credentialsMatcher属性，直接使用HashedCredentialsMather对象，并设置加密算法即可

密码的比对：
通过AuthenticationRealm的credentialsMatcher属性来进行密码的比对！



shiro认证思路分析：
1：获取当前的subject,调用SecurityUtils.getSubject();
2:测试当前的用户是否已经被认证，即是否已经登录，调用Subject的isAuthenticated()
3:若没有被认证,则用户名和密码封装为UsernamePasswordToken对象
	1）：创建一个表单页面
	2）：把请求提交到一个springmvc的hanlder
	3）：获取用户名和密码
4：执行登录，调用Subject的login(AuthenticationToken)方法
5：自定义Realm的方法，从数据库中获得对应的记录，返回给Shiro
	1）：实际上需要继承org.apache.shiro.realm.AuthenticationRealm类
	2）：实现doGetAuthenticationInfo(AuthenticationToken)方法
6：有shiro完成密码的比对




授权：
1：授权需要继承AuthorizingRealm类，并实现doGetAuthorizationInfo方法
2：AuthorizingRealm类继承自AuthenticationRealm,但并没有实现AuthenticatingRealm中的doGetAuthenticationInfo,
      所以认证和授权只需要继承AuthorizingRealm就可以了，同时实现它的2个抽象方法。
