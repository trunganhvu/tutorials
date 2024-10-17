# Prototype Pattern 
> là một mẫu thiết kế tạo ra, cho phép bạn sao chép đối tượng mà không cần biết cấu trúc của nó. 
> Mẫu này thường được sử dụng khi việc khởi tạo một đối tượng mới tốn kém về thời gian hoặc tài nguyên.

Đối tượng employee chứa method clone giúp nhân bản đối tượng mà không cần quan tâm đến những attributes như nào
```java
Employees eRoot = new Employees();
eRoot.loadData();

//Use the clone method to get the Employee object
Employees eClone1 = (Employees) eRoot.clone();
Employees eClone2 = (Employees) eRoot.clone();
List<String> list1 = eClone1.getEmpList();
list1.add("E");
List<String> list2 = eClone2.getEmpList();
list2.remove("A");

System.out.println("eRoot List: " + eRoot.getEmpList());    // eRoot List: [A, B, C, D]
System.out.println("eClone1 List: " + list1);               // eClone1 List: [A, B, C, D, E]
System.out.println("eClone2 List: " + list2);               // eClone2 List: [B, C, D]
```


