
# Secure Socket Shell (SSH)
là cơ chế kết nối, đặc biệt khi so sánh với HTTPS khi remote tới GitLab, GitHub or BitBucket.

## Thao tác cài đặt
1. **Open terminal/cmd:**
```sh
ssh-keygen -t rsa -C "your@email.com"
```
Output: key files được lưu trong C:/User/.ssh
```sh
├── id_rsa
├── id_rsa.pub
├── known_hosts
└── known_hosts.old
```

2. **Đăng ký public key vào github setting**

* Link: https://gitlab.com/-/user_settings/ssh_keys
* Sử dụng: id_rsa.pub