# Java CompletableFuture
CompletableFuture là một lớp trong Java cung cấp một cách để thực hiện các nhiệm vụ theo thứ tự hoặc song song. Nó có thể được sử dụng để thực hiện các nhiệm vụ theo thứ tự, chẳng hạn như tải dữ liệu từ hai nguồn khác nhau và sau đó kết hợp chúng lại với nhau. Hoặc, nó có thể được sử dụng để thực hiện các nhiệm vụ song song, chẳng hạn như xử lý nhiều yêu cầu cùng một lúc.

CompletableFuture có một số phương pháp hữu ích, chẳng hạn như:

- **thenApply:** Sử dụng một hàm để xử lý kết quả của một nhiệm vụ và trả về một CompletableFuture với kết quả của hàm.
- **thenCompose:** Sử dụng một hàm để tạo một CompletableFuture mới từ kết quả của một nhiệm vụ.
- **allOf:** Thực hiện đồng thời nhiều nhiệm vụ và trả về một CompletableFuture được hoàn thành khi tất cả các nhiệm vụ đều hoàn thành.
- **anyOf:** Thực hiện đồng thời nhiều nhiệm vụ và trả về một CompletableFuture được hoàn thành khi bất kỳ nhiệm vụ nào hoàn thành.

Dưới đây là một ví dụ về cách sử dụng CompletableFuture để thực hiện hai nhiệm vụ theo thứ tự:

Java

```
CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
  // Thực hiện nhiệm vụ 1  return "Nhiệm vụ 1 hoàn thành";
});

CompletableFuture<String> task2 = task1.thenApply(result -> {
  // Sử dụng kết quả của nhiệm vụ 1 để thực hiện nhiệm vụ 2  return "Nhiệm vụ 2 hoàn thành với kết quả từ nhiệm vụ 1: " + result;
});
// Hiển thị kết quả của nhiệm vụ 2
System.out.println(task2.join());

```

Trong ví dụ này, chúng tôi sử dụng `CompletableFuture.supplyAsync()` để thực hiện nhiệm vụ 1. Sau khi nhiệm vụ 1 hoàn thành, chúng tôi sử dụng `thenApply()` để thực hiện nhiệm vụ 2 với kết quả của nhiệm vụ 1. Cuối cùng, chúng tôi sử dụng `join()` để đợi nhiệm vụ 2 hoàn thành và hiển thị kết quả.

Dưới đây là một ví dụ về cách sử dụng CompletableFuture để thực hiện nhiều nhiệm vụ song song:

Java

```
CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
  // Thực hiện nhiệm vụ 1  return "Nhiệm vụ 1 hoàn thành";
});

CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
  // Thực hiện nhiệm vụ 2  return "Nhiệm vụ 2 hoàn thành";
});
// Thực hiện đồng thời nhiệm vụ 1 và nhiệm vụ 2CompletableFuture<Void> allTasksCompleted = CompletableFuture.allOf(task1, task2);
// Hiển thị kết quả khi tất cả các nhiệm vụ đều hoàn thànhallTasksCompleted.thenAccept(result -> {
  System.out.println("Tất cả các nhiệm vụ đã hoàn thành");
});

```

Trong ví dụ này, chúng tôi sử dụng `CompletableFuture.supplyAsync()` để thực hiện hai nhiệm vụ song song. Sau đó, chúng tôi sử dụng `allOf()` để thực hiện đồng thời hai nhiệm vụ và trả về một CompletableFuture được hoàn thành khi tất cả các nhiệm vụ đều hoàn thành. Cuối cùng, chúng tôi sử dụng `thenAccept()` để hiển thị một thông báo khi tất cả các nhiệm vụ đều hoàn thành.

CompletableFuture là một công cụ hữu ích cho việc thực hiện các nhiệm vụ theo thứ tự hoặc song song. Nó có thể giúp chúng ta viết mã hiệu quả hơn và dễ quản lý hơn.

Phương thức `thenCombine()` trong CompletableFuture có thể được sử dụng để kết hợp kết quả của hai CompletableFutures thành một CompletableFuture mới. Phương thức này nhận hai tham số: một CompletableFuture và một hàm kết hợp.

Hàm kết hợp nhận hai đối số là kết quả của hai CompletableFutures và trả về một đối tượng. Đối tượng này sẽ là kết quả của CompletableFuture mới được tạo bởi phương thức `thenCombine()`.

Dưới đây là một ví dụ về cách sử dụng phương thức `thenCombine()`:

Java

```
CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
  // Thực hiện nhiệm vụ 1  return "Nhiệm vụ 1 hoàn thành";
});

CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
  // Thực hiện nhiệm vụ 2  return "Nhiệm vụ 2 hoàn thành";
});
// Kết hợp kết quả của hai nhiệm vụCompletableFuture<String> combinedTask = task1.thenCombine(task2, (result1, result2) -> {
  // Kết hợp hai kết quả thành một kết quả mới  return result1 + " và " + result2;
});

// Hiển thị kết quả của nhiệm vụ kết hợp
System.out.println(combinedTask.join());

```

Trong ví dụ này, chúng tôi sử dụng `CompletableFuture.supplyAsync()` để thực hiện hai nhiệm vụ song song. Sau đó, chúng tôi sử dụng `thenCombine()` để kết hợp kết quả của hai nhiệm vụ thành một kết quả mới. Kết quả mới là chuỗi "Nhiệm vụ 1 hoàn thành và Nhiệm vụ 2 hoàn thành".

Giải thích thêm về phương thức `thenCombine()`:

- Phương thức `thenCombine()` được sử dụng để kết hợp kết quả của hai CompletableFutures thành một CompletableFuture mới.
- Hàm kết hợp nhận hai đối số là kết quả của hai CompletableFutures và trả về một đối tượng.
- Đối tượng này sẽ là kết quả của CompletableFuture mới được tạo bởi phương thức `thenCombine()`.
- Phương thức `thenCombine()` có thể được sử dụng để thực hiện các nhiệm vụ phức tạp, chẳng hạn như kết hợp dữ liệu từ hai nguồn khác nhau.