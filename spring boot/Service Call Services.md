# Background:
> Khi bạn muốn Service của đối tượng A tương tác với đối tượng B để thực hiện các tác vụ như tìm kiếm, xóa, v.v., bạn nên gọi Service của đối tượng B thay vì trực tiếp gọi Repository của đối tượng B. Lý do là:

1. Separation of concerns (Phân chia trách nhiệm): Service của B chịu trách nhiệm xử lý logic nghiệp vụ liên quan đến đối tượng B, trong khi Repository của B chỉ xử lý việc giao tiếp với cơ sở dữ liệu. Gọi trực tiếp Repository của B từ Service của A sẽ phá vỡ nguyên tắc phân chia trách nhiệm và dẫn đến khó quản lý về lâu dài.
2. Encapsulation (Đóng gói): Service của B đóng gói logic nghiệp vụ của B, nên khi bạn cần thực hiện một hành động liên quan đến B, bạn nên gọi thông qua Service của B để đảm bảo tất cả các quy tắc, xử lý logic đều được áp dụng đúng cách.
3. Tái sử dụng (Reusability): Bằng cách gọi Service của B, bạn có thể tái sử dụng logic của Service đó cho nhiều nơi khác trong hệ thống mà không cần phải viết lại.

Ngoài ra, trong một số trường hợp bạn cũng có thể sử dụng Event-Driven Architecture hoặc Message Queue để hai đối tượng giao tiếp gián tiếp thông qua các sự kiện. Cách này thường được dùng khi hệ thống cần giảm thiểu sự phụ thuộc chặt chẽ giữa các service (tạo tính loosely coupled) hoặc khi các service thuộc các microservices khác nhau.

## Tóm lại nên:
```sh
- Service A > Repository A
- Service B > Repository B
- Service C > Service A > Repository A > Service B > Repository B > Repository C
```

## Không nên:
```sh
- Service A > Repository A
- Service B > Repository B
- Service C > Repository A > Repository B > Repository C
```

# Các cách thiết kế khác:

- Service-to-Service Communication: A gọi Service của B.
- Event-Driven Communication: A phát ra sự kiện, B lắng nghe và xử lý.
- Message Queue: A gửi tin nhắn, B nhận tin nhắn và xử lý