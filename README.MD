## NeuroSimulation

一款运用神经网络和相应算法的RTS游戏, 用来作为沙盒供学生进行学习和实验. 用游戏激发人们对于AI技术的兴趣.



## 开发者

邱江坤 孔令仪 卢琨炀 李子恒

##  架构

NeuroSimulationMain -> GameScreen -> SystemManager -> XXXSystem

- UISystem  管理UI

- EntitySystem 管理所有的实体和提供相应操作

- RendererSystem - 渲染每一个实体
  - Renderer - 为每一类实体渲染,需要注册到RendererSystem中

- InputSystem - 管理鼠标键盘的输入
  - SimpleInputProcessor 响应特定的输入操作,需要注册到InputSystem.InputStack中



Entity - 实体的基类

- Living - 生物类
  - Processor - 接受一个Package,输出一个Package
  - Sensory - 感受周围环境,可自定义.输出一个Package
  - Effector - 根据 Processor 提供的 Package, 作出相应的动作

- Structure - 非生物类



Package - 多个float,可以理解为`ArrayList<Float>`

## Features

- [ ] 帮派
- [ ] 玩家控制
- [ ] 天灾
- [ ] 优胜劣汰
- [ ] 进化
- [ ] 巢穴
- [ ] 设计场景
- [ ] 食物
- [ ] 垃圾

