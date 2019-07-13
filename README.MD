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



Package - 多个float,可以理解为`ArrayList<Float>`



Entity - 实体的基类

- Living - 生物类
  - Processor - 接受一个Package,输出一个Package
  - Sensory - 感受周围环境,可自定义.输出一个Package
  - Effector - 根据 Processor 提供的 Package, 作出相应的动作
- Agenda - 所属势力(包括中立敌对和中立友好)
  - Health - 会变化
  - HealthLimit - 会变化
  - Defence
  - Damage
- Structure - 建筑类

  - BaseCamp - 大本营
  - Nest - 巢穴
  - 
- Functional
  - Food





AgendaSystem - 阵营



FightingSystem - 随机战斗结果

- 需要伤害计算公式
- 

## Features

- [ ] 势力
- [ ] 天灾
- [ ] 进化
- [ ] 巢穴
- [ ] 建造
- [ ] 食物
- [ ] 实时
- [ ] 战斗
