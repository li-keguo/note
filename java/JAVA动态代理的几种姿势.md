## JAVA动态代理的几种姿势

#### 常规的JDK动态代理

首先实现一个简单的InvocationHandler

```java
class AopServiceInvocationHandler implements InvocationHandler {

        private final AopService aopService;

        private final List<String> proxyMethodName;

        public AopServiceInvocationHandler(AopService aopService) {
            this.aopService = aopService;
            proxyMethodName = new ArrayList<String>(4);
        }

        public void addProxyMethod(String methodName) {
            proxyMethodName.add(methodName);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object invokeReturn;
            if (proxyMethodName.contains(method.getName())) {
                System.out.printf("[Thread:--- %s] proxy method(%s) before%n", Thread.currentThread().getName(), method.getName());
                invokeReturn = method.invoke(aopService, args);
                System.out.printf("[Thread:--- %s] proxy method(%s) after%n", Thread.currentThread().getName(), method.getName());
                return invokeReturn;
            }
            invokeReturn = method.invoke(aopService, args);
            return invokeReturn;
        }
    }
```

对已有的对象进行代理

```java
   public static void main(String[] args) {
        AopServiceInvocationHandler invocationHandler = new AopServiceInvocationHandler(new AopServiceImpl());
        invocationHandler.addProxyMethod("execute");
        invocationHandler.addProxyMethod("start");
        AopService proxyAopService = (AopService) Proxy.newProxyInstance(AopServiceInvocationHandler.class.getClassLoader(), AopServiceImpl.class.getInterfaces(), invocationHandler);
        proxyAopService.execute();
        proxyAopService.run();
        proxyAopService.start();
    }
```

对接口进行扩展代理

```java
   public static void main(String[] args) {
     // invocationHandler 不需要传入被代理的实现类的对象
        AopService proxyAopService = (AopService) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, invocationHandler);
        proxyAopService.execute();
        proxyAopService.run();
        proxyAopService.start();
    }
```

