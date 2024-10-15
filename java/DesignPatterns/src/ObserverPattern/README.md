# Observer Pattern
* là một mẫu thiết kế hành vi cho phép một đối tượng (gọi là Subject) thông báo cho các đối tượng khác (gọi là Observers) khi có sự thay đổi trong trạng thái của nó
* Subject: Lớp chính, chứa danh sách các Observer và có các phương thức để thêm, xóa và thông báo cho các Observer.
* Observer: Giao diện hoặc lớp trừu tượng mà các đối tượng cần thực hiện để trở thành Observer.
* ConcreteSubject: Lớp cụ thể thực hiện Subject, lưu trữ trạng thái và thông báo cho các Observer khi có thay đổi.
* ConcreteObserver: Lớp cụ thể thực hiện Observer, nhận thông báo từ ConcreteSubject và cập nhật trạng thái của nó dựa trên thông tin nhận được