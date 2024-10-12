# TreeSet
* lưu trữ các phần tử **duy nhất** có **được sắp xếp** (Có thể triển khai Comparator) 
* Triển khai từ interface NavigableSet
* Kế thừa từ AbstractSet
* Thứ tự mặc định tăng dần. Nếu một Comparator được cung cấp, nó sẽ sử dụng để xác định thứ tự của các phần tử.
* Tốc độ thêm, xóa và kiểm tra phần tử là O(log n)

# HashSet 
* Lưu trữ các phần tử **duy nhất** và **được sắp xếp** 
* Không thay đổi thứ tự chèn (**không thể triển khai Comparator**)
* Thứ tự mặc định tăng dần
* Triển khai interface Set
* Nếu bạn cố gắng thêm một phần tử đã tồn tại, HashSet sẽ không thay đổi và không ném ra ngoại lệ.
* Tốc độ thêm, xóa và kiểm tra phần tử là **O(1)**

# LinkedHashSet
* Kết hợp tính năng của HashSet và LinkedHashMap
* Lưu trữ các phần tử **duy nhất** và **duy trì thứ tự lúc chèn**
* Tốc độ thêm, xóa và kiểm tra phần tử là **O(1)**





