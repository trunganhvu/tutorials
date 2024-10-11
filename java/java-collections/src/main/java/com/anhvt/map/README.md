# HashMap
* Lưu trữ dữ liệu dưới dạng cặp key-value.
* **Không duy trì thứ tự** của các phần tử 
* Key là duy nhất, chỉ có update value . 
* Có thể có một **key null** và nhiều **value null**.
* Tốc độ truy cập O(1)

# TreeMap
* Lưu trữ các cặp key-value trong một cấu trúc dữ liệu theo dạng **cây nhị phân tìm kiếm**
* Được **sắp xếp các khóa** theo thứ tự tự nhiên hoặc theo một Comparator được chỉ định.
* Key là duy nhất, chỉ có update value
* Tốc độ thêm, xoá, truy xuất: O(log n)

# LinkedHashMap 
* là một biến thể của HashMap, giữ lại **thứ tự của các phần tử thêm vào** (điểm khác nhất với hashmap)
* Key là duy nhất, chỉ có update value
* Tốc độ thêm, xoá, truy xuất: O(1)

# ConcurrentHashMap
* Nó là một phiên bản đồng bộ hóa của HashMap
* Được thiết kế để cho phép truy cập đồng thời từ nhiều luồng mà không cần sử dụng các cơ chế đồng bộ hóa toàn bộ