![List.png](../../../../../../images/List.png)

# ArrayList
> Mục đích để get. Khi chèn phần tử thì bộ nhớ sẽ phải tìm toàn bộ rồi chuyển data vùng dữ liệu sang chỗ mới
> Arraylist không ưu tiên cho việc save mà ưu tiên cho get
## Các methods chính
* add(e)
* add(index, e)
* addAll(collection)
* addAll(index, collection)
* remove(index)
* remove(Object)
* removeAll(Object)
* removeAll(Collection) // removeAll(List.of(...))
* get(index)
* isEmpty()
* contains(Object)
* containsAll(Collection) // containsAll(List.of(...))
* indexOf(Object)
* lastIndexOf(Object)
* retainAll(Collection)  // Changed common elements and return true if changed, otherwise false 
* removeIf(Predicate)
* replaceAll(UnaryOperator)
* sort()

## Lưu ý
* Có thể insert duplicate element
* Có thể chèn null

# LinkedList
> Dữ liệu được lưu phân nhánh riêng biệt và liên bởi địa chỉ của phần tử tiếp theo.
> Khi chèn phần tử thì phải travel đến vị trí cần chèn và thay đổi địa chỉ xung quanh đó.
> Linkedlist ưu tiên cho việc lưu chứ không phải get 
## Các methods chính
* set(index, e)
* add(e)
* add(index, e)
* addAll(collection)
* addAll(index, collection)
* remove(index)
* remove(Object)
* removeAll(Object)
* removeAll(Collection) // removeAll(List.of(...))
* get(index)
* isEmpty()
* contains(Object)
* containsAll(Collection) // containsAll(List.of(...))
* indexOf(Object)
* lastIndexOf(Object)
* retainAll(Collection)  // Changed common elements and return true if changed, otherwise false
* removeIf(Predicate)
* replaceAll(UnaryOperator)
* sort()

## Lưu ý
* Có thể insert duplicate element
* Có thể chèn null

# Vector
> Tương tự ArrayList nhưng được thực Vector được đồng bộ hóa còn ArrayList thì không
## Các methods chính
* add(e)
* add(index, e)
* addAll(collection)
* addAll(index, collection)
* remove(index)
* remove(Object)
* removeAll(Object)
* removeAll(Collection) // removeAll(List.of(...))
* get(index)
* isEmpty()
* contains(Object)
* containsAll(Collection) // containsAll(List.of(...))
* indexOf(Object)
* lastIndexOf(Object)
* retainAll(Collection)  // Changed common elements and return true if changed, otherwise false
* removeIf(Predicate)
* replaceAll(UnaryOperator)
* sort()

## Lưu ý
* Có thể insert duplicate element
* Có thể chèn null

# Stack
> Ngăn xếp các phần tử theo thứ tự vào trước ra sau.
## Các methods chính
* push(e)
* pop()
* remove(index)
* remove(Object)
* removeAll(Collection)
* removeElement(Object)
* removeAllElements()
* removeElementAt(index)
* search(Object)  // return indexOf
* isEmpty()
* contains(Object)
* containsAll(Collection) // containsAll(List.of(...))
* set(index, element)
* retainAll(Collection)  // Changed common elements and return true if changed, otherwise false
* removeIf(Predicate)
* sort(Comparator)

## Lưu ý
* Có thể insert duplicate element
* Có thể chèn null