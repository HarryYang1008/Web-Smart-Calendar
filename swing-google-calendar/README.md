# Swing Google Calendar 桌面示例

这是一个面向 **Swing 初学者** 的完整示例：
- 采用分层架构：`UI + Controller + Service + Model`
- 包含完整按钮事件：今天、查询、新增、编辑、删除
- 包含完整弹窗表单与事件列表渲染
- 包含本地打包与分发流程（无需依赖 Maven 仓库）

## 目录结构

```text
swing-google-calendar/
├── pom.xml
├── README.md
├── dist/                           # 打包产物目录（运行脚本生成，不入库）
├── scripts/
│   ├── package-local.sh            # Linux/macOS 本地打包
│   ├── package-local.bat           # Windows 本地打包
│   ├── run.sh                      # Linux/macOS 启动
│   └── run.bat                     # Windows 启动
└── src/main/java/com/example/calendar/
    ├── app/CalendarApplication.java
    ├── controller/CalendarController.java
    ├── model/CalendarEvent.java
    ├── service/CalendarService.java
    ├── ui/MainFrame.java
    ├── ui/EventFormDialog.java
    └── util/DateUtils.java
```

---

## 一、本地部署（推荐给初学者）

### 1) 环境准备
- JDK 17 或以上（`java -version`、`javac -version`）
- Linux/macOS 或 Windows

### 2) 打包 ZIP

#### Linux/macOS
```bash
cd swing-google-calendar
bash scripts/package-local.sh
```

#### Windows（CMD）
```bat
cd swing-google-calendar
scripts\package-local.bat
```

打包完成后会得到：
- `dist/swing-google-calendar-1.0.0.jar`
- `dist/swing-google-calendar-demo.zip`

### 3) 运行程序

#### Linux/macOS
```bash
cd swing-google-calendar
bash scripts/run.sh
```

#### Windows
```bat
cd swing-google-calendar
scripts\run.bat
```

---

## 二、把 ZIP 发给别人如何使用

1. 把 `dist/swing-google-calendar-demo.zip` 发给对方。  
2. 对方解压后确保机器安装 JDK 17+。  
3. 运行：
   - Linux/macOS：`java -jar swing-google-calendar-1.0.0.jar`
   - Windows：`java -jar swing-google-calendar-1.0.0.jar`

---

## 三、可选：Maven 方式

如本地网络可访问 Maven Central，也可以：

```bash
mvn clean package
java -jar target/swing-google-calendar-1.0.0.jar
```
