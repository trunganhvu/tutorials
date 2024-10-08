# Abstract equality operator (==)
> Return true nếu giá trị bằng nhau và ngược lại

Steps:
1. Check kiểu. cùng kiểu hay khác kiểu đểu thực hiện step sau đó (Require)
2. Convert về cùng kiểu (Optional)
3. Compare return true/false (Require)
# The strict equality operator (===) 
> Return true nếu cùng kiểu giá trị và giá trị giống nhau. Các trường hợp còn lại return false

Steps:
1. Check kiểu: Cùng kiểu thì thực hiện step 2, khác thì return (Require)
2. Compare return true/false (Require)

# Phân biệt Abstract equality operator (==) vs The strict equality operator (===)
> Do strict equality có thể trả về kết quả luôn mà không cần convert nên hiệu năng sẽ cao hơn

Code minh hoạ
```js
function testPerformance() {
    const iterations = 1000000; // Số lần lặp
    const a = 5;
    const b = '5';
    const c = null;
    const d = undefined;
    const e = 0;
    const f = false;

    // Kiểm tra hiệu suất cho ==
    console.time('== Performance');
    for (let i = 0; i < iterations; i++) {
        const result1 = (a == b);
        const result2 = (c == d);
        const result3 = (e == f);
    }
    console.timeEnd('== Performance');

    // Kiểm tra hiệu suất cho ===
    console.time('=== Performance');
    for (let i = 0; i < iterations; i++) {
        const result1 = (a === b);
        const result2 = (c === d);
        const result3 = (e === f);
    }
    console.timeEnd('=== Performance');
}

// Chạy bài test
testPerformance();
```

Output:
```sh
== Performance: 24.886ms
=== Performance: 3.362ms
```

Minh hoạ không sử dụng loop
```js
function testPerformance() {
    const iterations = 1; // Số lần lặp
    const a = 5;
    const b = '5';
    const c = null;
    const d = undefined;
    const e = 0;
    const f = false;

    // Kiểm tra hiệu suất cho ==
    console.time('== Performance');
    const result1 = (a == b);
    const result2 = (c == d);
    const result3 = (e == f);
    // }
    console.timeEnd('== Performance');

    // Kiểm tra hiệu suất cho ===
    console.time('=== Performance');
    const result4 = (a === b);
    const result5 = (c === d);
    const result6 = (e === f);
    console.timeEnd('=== Performance');
}

// Chạy bài test
testPerformance();
```

Output vẫn có sự khác biệt nhiều về thời gian
```sh
== Performance: 0.069ms
=== Performance: 0.006ms
```