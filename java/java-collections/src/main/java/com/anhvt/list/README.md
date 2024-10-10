![List.png](../../../../../../images/List.png)

# ArrayList
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
> 
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