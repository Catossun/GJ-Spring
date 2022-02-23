# Gjun Spring Class

## Coursework

### [Coursework 1](https://github.com/Catossun/GJ-Spring/tree/coursework1)

請使用 Java 8 stream 進行資料分析並取得 mary 的老師有誰? (印出 name)

Point code:
[Test1.java](./SpringCore/src/test/java/com/study/springcore/coursework1/Test1.java)

### [Coursework 2](https://github.com/Catossun/GJ-Spring/tree/coursework2)

1. 完成 JsonDB.java 如果 person 已存在則 return false (name、age、birth 皆與目前資料庫某一 person 資料相同)
2. 完成 PersonSystem.java 選項3 ~ 7資料分析與處理

Point code:
[JsonDB.java](./SpringCore/src/main/java/com/study/springcore/coursework2/JsonDB.java)
,
[PersonSystem.java](./SpringCore/src/main/java/com/study/springcore/coursework2/PersonSystem.java)

### [Coursework 3](https://github.com/Catossun/GJ-Spring/tree/coursework3)

將每次調用 查詢 queryAll() 方法的調用時間 Log 紀錄下來(透過切面導向程式設計 AOP)

Point code:
[QueryLogDao.java](./SpringCore/src/main/java/com/study/springcore/coursework3/template/QueryLogDao.java)
,
[QueryLogger.java](./SpringCore/src/main/java/com/study/springcore/coursework3/aop/QueryLogger.java)

### [Coursework 4](https://github.com/Catossun/GJ-Spring/tree/coursework4)

建立交易紀錄 order_log 資料表，試問: 資料表應如何創建 (注意: 若 book 的 price 欄位有變動，order_log 則不影響)，請完成整段資料庫 log 寫入機制 例如:

> Vincent在2020/01/23 PM 2:07:51 買了Java書2本共300元  
> Vincent在2020/01/23 PM 2:08:51 買了Python書2本共200元  
> Vincent在2020/01/23 PM 2:10:51 買了Java書4本共600元

Point code:
[OrderLogAspect.java](./SpringCore/src/main/java/com/study/springcore/coursework4/aop/OrderLogAspect.java)
,
[OrderLogDaoImpl.java](./SpringCore/src/main/java/com/study/springcore/coursework4/dao/OrderLogDaoImpl.java)

### [Coursework 5](https://github.com/Catossun/GJ-Spring/tree/coursework5)

統計每個號碼出現的次數，並依次數由大到小排列
E.g., 9:(19), 5:(6), 13(1)...

Demo: [Lotto](https://gj-springmvc.azurewebsites.net/mvc/coursework5/lotto/)

Point code:
[LottoService](./SpringMVC/src/main/java/com/study/springmvc/coursework5/service/LottoService.java)
,
[LottoController](./SpringMVC/src/main/java/com/study/springmvc/coursework5/controller/LottoController.java)
,
[show_lotto.jsp](./SpringMVC/src/main/webapp/WEB-INF/views/coursework5/show_lotto.jsp)

### [Coursework 6](https://github.com/Catossun/GJ-Spring/tree/coursework6)

將 Exam Form 裡面的 `<spform:checkbox>` 改成使用 `<spform:checkboxes>`，以及 `<spform:radiobutton>` 改成使用 `<spform:radiobuttons>`。

Demo: [Exam Form](https://gj-springmvc.azurewebsites.net/mvc/coursework6/exam/)

Point code:
[ExamController.java](./SpringMVC/src/main/java/com/study/springmvc/coursework6/controller/ExamController.java)
,
[Exam.java](./SpringMVC/src/main/java/com/study/springmvc/coursework6/entity/Exam.java)
,
[ExamSlot.java](./SpringMVC/src/main/java/com/study/springmvc/coursework6/entity/ExamSlot.java)
,
[ExamPayStatus.java](./SpringMVC/src/main/java/com/study/springmvc/coursework6/entity/ExamPayStatus.java)
,
[exam_form.jsp](./SpringMVC/src/main/webapp/WEB-INF/views/coursework6/exam_form.jspf)
