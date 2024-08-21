# Transient 
Thiết định rằng một biến không nên được lưu trữ khi đối tượng của class đó được tuần tự hóa (serialized).
Khi deserialized giá trị của biến đó sẽ được đặt về giá trị mặc định (thường là null cho các đối tượng, 0 cho các kiểu nguyên thủy).
